package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.of;
import static java.util.Optional.ofNullable;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DbServiceTest {
    @InjectMocks
    private DbService dbService;

    @Mock
    private TaskRepository repository;

    @Test
    public void shouldFetchTaskList() {
        //Given
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task(1L, "Task", "task"));
        when(repository.findAll()).thenReturn(tasks);
        //When
        List<Task> taskList = dbService.getAllTasks();
        //Then
        assertNotNull(taskList);
        assertEquals(1, taskList.size());
        assertEquals(of(1L), ofNullable(taskList.get(0).getId()));
        assertEquals("Task", taskList.get(0).getTitle());
        assertEquals("task", taskList.get(0).getContent());
    }

    @Test
    public void shouldFetchOneTask() {
        //Given
        Task task = new Task(1L, "Task", "task");
        when(repository.findById(1L)).thenReturn(of(task));
        //When
        Optional<Task> task1 = dbService.getTask(1L);
        //Then
        assertNotNull(task1);
        assertEquals(of(1L), of(task1.get().getId()));
        assertEquals(of("Task"), of(task1.get().getTitle()));
        assertEquals(of("task"), of(task1.get().getContent()));
    }

    @Test
    public void shouldSaveTask() {
        //Given
        Task task = new Task(1L, "Task", "task");
        when(repository.save(task)).thenReturn(task);
        //When
        Task task1 = dbService.saveTask(task);
        //Then
        assertNotNull(task1);
        assertEquals(of(1L), ofNullable(task1.getId()));
        assertEquals("Task", task1.getTitle());
        assertEquals("task", task1.getContent());
    }

    @Test
    public void shouldCheckIfTaskExist() {
        //Given
        Task task = new Task(1L, "Task", "task");
        when(repository.exists(task.getId())).thenReturn(true);
        //When
        Boolean taskExist = dbService.isValidTaskId(task.getId());
        //Then
        assertTrue(taskExist);
    }
}