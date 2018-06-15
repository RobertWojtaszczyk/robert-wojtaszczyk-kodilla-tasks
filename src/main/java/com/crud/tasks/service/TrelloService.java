package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.trello.client.TrelloClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TrelloService {
    private static final String SUBJECT = "Tasks: New Trello card created";

    @Autowired
    private TrelloClient trelloClient;

    @Autowired
    private SimpleEmailService simpleEmailService;

    @Autowired
    private AdminConfig adminConfig;

    public List<TrelloBoardDto> fetchTrelloBoards() {
        return trelloClient.getTrelloBoards();
    }

    public CreatedTrelloCardDto createTrelloCard(final TrelloCardDto trelloCardDto) {
        CreatedTrelloCardDto newCard = trelloClient.createNewCard(trelloCardDto);
        Optional.ofNullable(newCard)
                .ifPresent(card -> simpleEmailService.send(prepareMessageData(card.getName())));
        return newCard;
    }

    private Mail prepareMessageData(String cardName) {
        List<String> functionality = new ArrayList<>();
        functionality.add("You can manage your tasks");
        functionality.add("Provides connection with Trello Account");
        functionality.add("Application allows sending tasks to Trello");

        return new Mail
                .MailBuilder()
                .mailTo(adminConfig.getAdminMail())
                .subject(SUBJECT)
                .templateName("mail/created-trello-card-mail")
                .context("message", "New card: " + cardName + " has been created on your Trello account")
                .context("tasks_url", "http://localhost:8888/tasks_frontend/")
                .context("button", "Visit website")
                .context("goodbye_message", "Stay tuned!")
                .context("admin_config", adminConfig)
                .context("show_button", true)
                .context("is_friend", true)
                .context("application_functionality", functionality)
                .build();
    }
}
/*
                        new Mail(
                    adminConfig.getAdminMail(),
                    SUBJECT,
                    "New card: " + card.getName() + " has been created on your Trello account",
                    "mail/created-trello-card-mail")

List<String> functionality = new ArrayList<>();
        functionality.add("You can manage your tasks");
        functionality.add("Provides connection with Trello Account");
        functionality.add("Application allows sending tasks to Trello");

        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "http://localhost:8888/tasks_frontend/");
        context.setVariable("button", "Visit website");
        context.setVariable("goodbye_message", "Stay tuned,");
        context.setVariable("admin_config", adminConfig);
        context.setVariable("show_button", true);
        context.setVariable("is_friend", true);
        context.setVariable("application_functionality", functionality);*/