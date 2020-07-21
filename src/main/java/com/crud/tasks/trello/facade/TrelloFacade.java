package com.crud.tasks.trello.facade;

import com.crud.tasks.domain.trello.*;
import com.crud.tasks.mapper.TrelloMapper;
import com.crud.tasks.service.TrelloService;
import com.crud.tasks.trello.validator.TrelloValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TrelloFacade {

    @Autowired private TrelloService service;
    @Autowired private TrelloMapper mapper;
    @Autowired private TrelloValidator validator;

    public List<BoardDto> fetchTrelloBoards() {
        List<Board> boards = mapper.mapToBoards(service.fetchBoards());
        List<Board> filteredBoards = validator.validateBoards(boards);
        return mapper.mapToBoardDtos(filteredBoards);
    }

    public CreatedCardDto createCard(CardDto cardDto) {
        Card card = mapper.mapToCard(cardDto);
        validator.validateCard(card);
        return service.createCard(mapper.mapToCardDto(card));
    }
}
