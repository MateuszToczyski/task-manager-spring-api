package com.crud.tasks.service;

import com.crud.tasks.controller.TaskNotFoundException;
import com.crud.tasks.domain.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DbServiceTestSuite {

    @Autowired private DbService dbService;

    @Test
    public void testDbOperations() {
        Task task1 = new Task("task 1", "Content 1");
        Task task2 = new Task("task 2", "Content 2");

        try {
            List<Task> tasksBefore = dbService.getAllTasks();
            dbService.saveTask(task1);
            dbService.saveTask(task2);
            List<Task> tasksAfter = dbService.getAllTasks();
            assertEquals(tasksBefore.size() + 2, tasksAfter.size());
        } finally {
            dbService.deleteTask(task1.getId());
            dbService.deleteTask(task2.getId());
        }
    }

    @Test
    public void testGetTask() {
        Task task = new Task("task 1", "Content 1");

        try {
            dbService.saveTask(task);
            Task retrievedTask = dbService.getTask(task.getId()).orElseThrow(TaskNotFoundException::new);
            assertEquals(task.getTitle(), retrievedTask.getTitle());
        } finally {
            dbService.deleteTask(task.getId());
        }
    }
}
