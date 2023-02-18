package org.example.infrastructure.repositories;
import org.example.infrastructure.Parser;
import org.example.infrastructure.entities.TaskEntity;

import java.util.List;

public class TaskFileRepository implements TaskRepository {

    Parser parser = new Parser();

    @Override
    public List<TaskEntity> getAll() {
        return parser.parse();
    }

    @Override
    public void post(TaskEntity taskEntity){
    }

}

