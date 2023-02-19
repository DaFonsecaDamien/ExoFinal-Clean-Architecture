package org.example.infrastructure.parsers;

import org.example.infrastructure.entities.TaskEntity;

import java.util.List;

public interface Parser {

    List<TaskEntity> parse();

    void writeTaskEntitiesToFile(List<TaskEntity> tasks);
}
