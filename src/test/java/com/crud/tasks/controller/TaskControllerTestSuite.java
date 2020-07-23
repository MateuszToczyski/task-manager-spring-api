package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTestSuite {

    @Autowired private MockMvc mockMvc;
    @MockBean private DbService dbService;
    @SpyBean private TaskMapper taskMapper;

    @Before
    public void init() {
        Task task1 = new Task("title 1", "content 1");
        Task task2 = new Task("title 2", "content 2");

        when(dbService.getTask(any(Long.class))).thenReturn(java.util.Optional.of(task1));
        when(dbService.getAllTasks()).thenReturn(Arrays.asList(task1, task2));
        when(dbService.saveTask(any(Task.class))).thenReturn(task1);
        doNothing().when(dbService).deleteTask(any(Long.class));
    }

    @Test
    public void testGetTasks() throws Exception {
        mockMvc.perform(get("/v1/task/getTasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title", is("title 1")));
    }

    @Test
    public void testGetTask() throws Exception {
        mockMvc.perform(get("/v1/task/getTask?id=1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("title 1")));
    }

    @Test
    public void testDeleteTask() throws Exception {
        mockMvc.perform(delete("/v1/task/deleteTask?id=1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateTask() throws Exception {
        TaskDto taskDto = new TaskDto(1L, "title 1", "content 1");

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        mockMvc.perform(put("/v1/task/updateTask")
                    .contentType(MediaType.APPLICATION_JSON)
                    .characterEncoding("UTF-8")
                    .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("title 1")));
    }

    @Test
    public void testCreateTask() throws Exception {
        TaskDto taskDto = new TaskDto(1L, "title 1", "content 1");

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        mockMvc.perform(post("/v1/task/createTask")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }
}
