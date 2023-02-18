package org.example.infrastructure;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.example.domain.entities.Task;
import org.example.domain.enums.TaskState;
import org.example.infrastructure.entities.TaskEntity;
import org.example.infrastructure.entities.TaskMapper;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Parser {

    TaskMapper taskMapper = new TaskMapper();
    public List<Task> parse() {

        List<Task> tasks = new ArrayList<>();

        try (FileReader fileReader = new FileReader("src/main/java/org/example/users/test/consoleagenda/data.json")) {

            Gson gson = new Gson();
            JsonArray jsonArray = gson.fromJson(fileReader, JsonArray.class);

            for (int i = 0; i < jsonArray.size(); i++) {
                JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();
                TaskEntity taskEntity = new TaskEntity(
                        UUID.fromString(jsonObject.get("UUID").getAsString()),
                        jsonObject.get("Created").isJsonNull() ? null : LocalDateTime.parse(jsonObject.get("Created").getAsString()),
                        jsonObject.get("DueDate").isJsonNull() ? null : LocalDateTime.parse(jsonObject.get("DueDate").getAsString()),
                        jsonObject.get("CloseDate").isJsonNull() ? null : LocalDateTime.parse(jsonObject.get("CloseDate").getAsString()),
                        jsonObject.get("Description").getAsString(),
                        TaskState.values()[jsonObject.get("State").getAsInt()],
                        null
                );
                tasks.add(taskMapper.toDomain(taskEntity));
            }
            System.out.println(tasks.get(0).getCreationDate());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return tasks;
    }
}
