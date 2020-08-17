package com.crud.tasks.controller;

import com.crud.tasks.domain.trello.CreatedCardDto;
import com.crud.tasks.domain.trello.BoardDto;
import com.crud.tasks.domain.trello.CardDto;
import com.crud.tasks.trello.facade.TrelloFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/trello")
@CrossOrigin(origins = "*")
public class TrelloController {

    @Autowired
    private TrelloFacade facade;

    @RequestMapping(method = RequestMethod.GET, value = "boards")
    public List<BoardDto> getTrelloBoards() {
        return facade.fetchTrelloBoards();
    }

    @RequestMapping(method = RequestMethod.POST, value = "cards")
    public CreatedCardDto createTrelloCard(@RequestBody CardDto cardDto) {
        return facade.createCard(cardDto);
    }
}