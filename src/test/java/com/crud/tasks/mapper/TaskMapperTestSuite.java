package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskMapperTestSuite {

    @Autowired private TaskMapper mapper;

    @Test
    public void testSingleMapping() {
        Task task1 = new Task("task 1", "Content 1");
        TaskDto taskDto1 = mapper.mapToTaskDto(task1);
        Task mappedTask1 = mapper.mapToTask(taskDto1);
        assertEquals(task1.getTitle(), mappedTask1.getTitle());
    }

    @Test
    public void testListMapping() {
        List<Task> tasks = Arrays.asList(
                new Task("task 1", "Content 1"),
                new Task("task 2", "Content 2")
        );
        List<TaskDto> taskDtos = mapper.mapToTaskDtoList(tasks);
        assertEquals(2, taskDtos.size());
        assertEquals(taskDtos.get(0).getTitle(), tasks.get(0).getTitle());
    }
}
