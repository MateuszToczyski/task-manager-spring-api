package com.crud.tasks.trello.validator;

import com.crud.tasks.domain.trello.Board;
import com.crud.tasks.domain.trello.Card;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TrelloValidator {

    private static final Logger LOGGER = LoggerFactory.getLogger(TrelloValidator.class);

    public List<Board> validateBoards(List<Board> boards) {
        LOGGER.info("Starting filtering boards");
        List<Board> filteredBoards = boards.stream()
                .filter(board -> !board.getName().equalsIgnoreCase("test"))
                .collect(Collectors.toList());
        LOGGER.info("List size after filtering:" + filteredBoards.size());
        return filteredBoards;
    }

    public void validateCard(Card card) {
        if(card.getName().contains("test")) {
            LOGGER.info("Application in test mode");
        } else {
            LOGGER.info("Application in production mode");
        }
    }
}
