package org.example.infrastructure.repositories;

import org.example.infrastructure.entities.TaskEntity;
import org.example.infrastructure.parsers.Parser;
import org.example.infrastructure.parsers.TaskJsonParser;

import java.util.List;
import java.util.UUID;

public class TaskFileRepository implements TaskRepository {

    TaskJsonParser parser = new TaskJsonParser();

    @Override
    public List<TaskEntity> getAll() {
        return parser.parse();
    }

    @Override
    public TaskEntity getOne(UUID uuid) {
        return parser.getOneTaskById(uuid);
    }


    @Override
    public void post(List<TaskEntity> taskEntity) {
        parser.writeTaskEntitiesToFile(taskEntity);
    }

    @Override
    public void delete(List<TaskEntity> taskEntity) {
        parser.writeTaskEntitiesToFile(taskEntity);
    }

    @Override
    public void update(List<TaskEntity> taskEntities) {
        parser.writeTaskEntitiesToFile(taskEntities);
    }

}

