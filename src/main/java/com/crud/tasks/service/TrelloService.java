package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.domain.trello.BoardDto;
import com.crud.tasks.domain.trello.CardDto;
import com.crud.tasks.domain.trello.CreatedCardDto;
import com.crud.tasks.trello.client.TrelloClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrelloService {

    private static final String SUBJECT = "Tasks: New Trello Board";

    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    private TrelloClient trelloClient;

    @Autowired
    private SimpleEmailService emailService;

    public List<BoardDto> fetchBoards() {
        return trelloClient.getTrelloBoards();
    }

    public CreatedCardDto createCard(CardDto cardDto) {

        CreatedCardDto newCard = trelloClient.createNewCard(cardDto);

        Optional.ofNullable(newCard).ifPresent(card -> emailService.send(new Mail(adminConfig.getAdminMail(), null,
                SUBJECT, "New card: " + card.getName() + " has been created on your Trello account")));

        return newCard;
    }
}
