package com.crud.tasks.scheduler;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.SimpleEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EmailScheduler {

    private static final String SUBJECT = "Tasks: Once a day email";

    @Autowired
    private SimpleEmailService simpleEmailService;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    private AdminConfig adminConfig;

    @Scheduled(cron = "0 0 10 * * *")
//    @Scheduled(fixedDelay = 10000)
    public void sendInformationEmail() {
        long size = taskRepository.count();
        simpleEmailService.send(prepareMessageData(size));
    }

    private Mail prepareMessageData(long size) {
        List<TaskDto> taskList = taskMapper.mapToTaskDtoList(taskRepository.findAll());
        return new Mail
                .MailBuilder()
                .mailTo(adminConfig.getAdminMail())
                .subject(SUBJECT)
                .templateName("mail/once-a-day-email")
                .context("message", "Currently in database you got: " + size + (size == 1 ? " task" : " tasks"))
                .context("is_friend", true)
                .context("button", "Visit website")
                .context("show_tasks", taskList.size() > 0)
                .context("tasks", taskList)
                .context("admin_config", adminConfig)
                .context("goodbye_message", "Stay tuned!")
                .build();
    }
}