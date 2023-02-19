package org.example.infrastructure.parsers;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.example.domain.enums.TaskState;
import org.example.infrastructure.entities.TaskEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public interface JsonParser extends Parser {
    default UUID getUUIDFromJsonObject(JsonObject jsonObject, String key) {
        return UUID.fromString(jsonObject.get(key).getAsString());
    }

    default LocalDateTime getLocalDateTimeFromJsonObject(JsonObject jsonObject, String key) {
        return jsonObject.get(key).isJsonNull() ? null : LocalDateTime.parse(jsonObject.get(key).getAsString());
    }

    default String getStringFromJsonObject(JsonObject jsonObject, String key) {
        return jsonObject.get(key).getAsString();
    }

    default TaskState getTaskStateFromJsonObject(JsonObject jsonObject, String key) {
        return TaskState.values()[jsonObject.get(key).getAsInt()];
    }
}