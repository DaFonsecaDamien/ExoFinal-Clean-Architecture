package org.example.infrastructure.repositories;
import org.example.infrastructure.parsers.JsonParser;
import org.example.infrastructure.entities.TaskEntity;
import org.example.infrastructure.parsers.Parser;

import java.util.List;

public class TaskFileRepository implements TaskRepository {

    Parser parser = new JsonParser();

    @Override
    public List<TaskEntity> getAll() {
        return parser.parse();
    }

    @Override
    public void post(TaskEntity taskEntity){
    }

}

