package org.example;
import com.google.gson.JsonArray;
import org.example.domain.entities.Task;
import org.example.domain.services.TaskService;
import org.example.infrastructure.entities.TaskEntity;
import org.example.infrastructure.parsers.JsonParser;
import org.example.infrastructure.parsers.Parser;
import org.example.infrastructure.repositories.TaskFileRepository;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {

        TaskService taskService = new TaskService();
        JsonParser parser = new JsonParser();
        TaskFileRepository taskFileRepository = new TaskFileRepository();
        List<Task> tasks = taskService.getTasks();

        JsonArray jsonArray = new JsonArray();
        List<TaskEntity> taskEntities = taskFileRepository.getAll();
        jsonArray = parser.parseTasks(taskEntities);

        System.out.println(jsonArray);

//        tasks = taskService.orderByCreationDate();
//        for(Task task : tasks){
//            System.out.println(task.toString());
//        }


    }
}
