package org.example.infrastructure.parsers;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.example.domain.enums.TaskState;
import org.example.infrastructure.entities.TaskEntity;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class JsonParser implements Parser{
    @Override
    public List<TaskEntity> parse() {

        List<TaskEntity> tasks = new ArrayList<>();

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
                        jsonObject.get("SubTasks").isJsonNull() ? null : parseSubTask(jsonObject.get("SubTasks").getAsJsonArray())
                );
                tasks.add(taskEntity);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return tasks;
    }

    private TaskEntity[] parseSubTask(JsonArray subTasks) {
        TaskEntity[] tasks = new TaskEntity[subTasks.size()];
        for (int i = 0; i < subTasks.size(); i++) {
            JsonObject jsonObject = subTasks.get(i).getAsJsonObject();
            tasks[i] = new TaskEntity(
                    UUID.fromString(jsonObject.get("UUID").getAsString()),
                    jsonObject.get("Created").isJsonNull() ? null : LocalDateTime.parse(jsonObject.get("Created").getAsString()),
                    jsonObject.get("DueDate").isJsonNull() ? null : LocalDateTime.parse(jsonObject.get("DueDate").getAsString()),
                    jsonObject.get("CloseDate").isJsonNull() ? null : LocalDateTime.parse(jsonObject.get("CloseDate").getAsString()),
                    jsonObject.get("Description").getAsString(),
                    TaskState.values()[jsonObject.get("State").getAsInt()],
                    jsonObject.get("SubTasks").isJsonNull() ? null : parseSubTask(jsonObject.get("SubTasks").getAsJsonArray())
            );
        }
        return tasks;
    }
}
