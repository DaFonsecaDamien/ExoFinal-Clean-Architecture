package org.example.infrastructure.repositories;
import org.example.domain.entities.TaskEntity;
import java.util.List;

public class TaskFileRepository implements TaskRepository {

    @Override
    public List<TaskEntity> getAll() {
        return null;
    }

    @Override
    public void post(TaskEntity taskEntity){
    }

}

