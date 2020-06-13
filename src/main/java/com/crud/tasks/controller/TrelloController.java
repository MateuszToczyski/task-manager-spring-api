package com.crud.tasks.controller;

import com.crud.tasks.domain.trello.CreatedCard;
import com.crud.tasks.domain.trello.BoardDto;
import com.crud.tasks.domain.trello.CardDto;
import com.crud.tasks.trello.client.TrelloClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/trello")
@CrossOrigin(origins = "*")
public class TrelloController {

    @Autowired
    private TrelloClient trelloClient;

    @RequestMapping(method = RequestMethod.GET, value = "getTrelloBoards")
    public List<BoardDto> getTrelloBoards() {
        return trelloClient.getTrelloBoards();
    }

    @RequestMapping(method = RequestMethod.POST, value = "createTrelloCard")
    public CreatedCard createTrelloCard(@RequestBody CardDto cardDto) {
        return trelloClient.createNewCard(cardDto);
    }
}