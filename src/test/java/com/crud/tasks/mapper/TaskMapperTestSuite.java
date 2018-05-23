package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskMapperTestSuite {
    @Autowired
    private TaskMapper taskMapper;

    @Test
    public void testMapToTask() {
        //Given
        TaskDto taskDto = new TaskDto(1L, "Test", "test");
        //When
        Task task = taskMapper.mapToTask(taskDto);
        //Then
        assertEquals(java.util.Optional.of(1L), java.util.Optional.ofNullable(task.getId()));
        assertEquals("Test", task.getTitle());
        assertEquals("test", task.getContent());
    }

    @Test
    public void testMapToTaskDto() {
        //Given
        Task task = new Task(1L, "Test", "test");
        //When
        TaskDto taskDto = taskMapper.mapToTaskDto(task);
        //Then
        assertEquals(java.util.Optional.of(1L), java.util.Optional.ofNullable(taskDto.getId()));
        assertEquals("Test", taskDto.getTitle());
        assertEquals("test", taskDto.getContent());
    }

    @Test
    public void testMapToTaskDtoList() {
        //Given
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task(1L, "Test1", "test1"));
        tasks.add(new Task(2L, "Test2", "test2"));
        //When
        List<TaskDto> taskDtos = taskMapper.mapToTaskDtoList(tasks);
        //Then
        assertEquals(2, taskDtos.size());
        assertEquals(java.util.Optional.of(1L), java.util.Optional.ofNullable(taskDtos.get(0).getId()));
        assertEquals("Test1", taskDtos.get(0).getTitle());
        assertEquals("test1", taskDtos.get(0).getContent());
        assertEquals(java.util.Optional.of(2L), java.util.Optional.ofNullable(taskDtos.get(1).getId()));
        assertEquals("Test2", taskDtos.get(1).getTitle());
        assertEquals("test2", taskDtos.get(1).getContent());
    }
}