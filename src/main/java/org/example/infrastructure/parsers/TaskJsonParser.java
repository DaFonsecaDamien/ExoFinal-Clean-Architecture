package org.example.infrastructure.parsers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.example.infrastructure.entities.TaskEntity;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TaskJsonParser implements JsonParser {

    public String filePath = "users/test/.consoleagenda/data.json";

    @Override
    public List<TaskEntity> parse() {
        return taskEntityListFromFile();
    }

    private List<TaskEntity> taskEntityListFromFile() {
        try (FileReader fileReader = new FileReader(filePath)) {
            return jsonArrayToTaskEntityList(new Gson().fromJson(fileReader, JsonArray.class));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private List<TaskEntity> jsonArrayToTaskEntityList(JsonArray jsonArray) {
        List<TaskEntity> tasks = new ArrayList<>();
        jsonArray.forEach(jsonElement -> tasks.add(taskEntityFromJsonObject(jsonElement.getAsJsonObject())));
        return tasks;
    }

    private TaskEntity taskEntityFromJsonObject(JsonObject jsonObject) {
        return new TaskEntity(
                getUUIDFromJsonObject(jsonObject, "UUID"),
                getLocalDateTimeFromJsonObject(jsonObject, "Created"),
                getLocalDateTimeFromJsonObject(jsonObject, "DueDate"),
                getLocalDateTimeFromJsonObject(jsonObject, "CloseDate"),
                getStringFromJsonObject(jsonObject, "Description"),
                getTaskStateFromJsonObject(jsonObject, "State"),
                getTaskEntityListFromJsonObject(jsonObject, "SubTasks")
        );
    }

    private List<TaskEntity> getTaskEntityListFromJsonObject(JsonObject jsonObject, String key) {
        return jsonObject.get(key).isJsonNull() ? null : jsonArrayToTaskEntityList(jsonObject.get(key).getAsJsonArray());
    }

    public JsonArray jsonArrayFromTaskEntityList(List<TaskEntity> tasks) {
        JsonArray jsonArray = new JsonArray();
        tasks.forEach(task -> jsonArray.add(parseTask(task)));
        return jsonArray;
    }

    public JsonObject parseTask(TaskEntity taskEntity) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("UUID", taskEntity.getUUID().toString());
        jsonObject.addProperty("Created", taskEntity.getCreationDate().toString());
        jsonObject.addProperty("DueDate", getLocalDateTimeAsString(taskEntity.getDueDate()));
        jsonObject.addProperty("CloseDate", getLocalDateTimeAsString(taskEntity.getCloseDate()));
        jsonObject.addProperty("Description", taskEntity.getDescription());
        jsonObject.addProperty("State", taskEntity.getState().ordinal());
        JsonArray subtasksArray = null;
        if (taskEntity.getSubTasks() != null) {
            JsonArray jsonArray = new JsonArray();
            taskEntity.getSubTasks().forEach(subTask -> jsonArray.add(parseTask(subTask)));
            subtasksArray = jsonArray;
        }
        jsonObject.add("SubTasks", subtasksArray);
        return jsonObject;
    }

    private String getLocalDateTimeAsString(LocalDateTime localDateTime) {
        return localDateTime == null ? null : localDateTime.toString();
    }

    @Override
    public void writeTaskEntitiesToFile(List<TaskEntity> tasks) {
        JsonArray jsonArray = jsonArrayFromTaskEntityList(tasks);
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .serializeNulls()
                    .create();
            gson.toJson(jsonArray, fileWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
