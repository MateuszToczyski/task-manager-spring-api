package com.crud.tasks.controller;

import com.crud.tasks.domain.trello.CreatedCard;
import com.crud.tasks.domain.trello.BoardDto;
import com.crud.tasks.domain.trello.CardDto;
import com.crud.tasks.service.TrelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/trello")
@CrossOrigin(origins = "*")
public class TrelloController {

    @Autowired
    private TrelloService trelloService;

    @RequestMapping(method = RequestMethod.GET, value = "getTrelloBoards")
    public List<BoardDto> getTrelloBoards() {
        return trelloService.fetchBoards();
    }

    @RequestMapping(method = RequestMethod.POST, value = "createTrelloCard")
    public CreatedCard createTrelloCard(@RequestBody CardDto cardDto) {
        return trelloService.createCard(cardDto);
    }
}