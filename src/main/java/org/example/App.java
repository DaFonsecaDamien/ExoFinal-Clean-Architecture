package org.example;
import org.example.domain.entities.Task;
import org.example.domain.services.TaskService;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {

        TaskService taskService = new TaskService();
        List<Task> tasks = taskService.getTasks();
        tasks = taskService.orderByCreationDate();
        for(Task task : tasks){
            System.out.println(task.toString());
        }



    }
}
