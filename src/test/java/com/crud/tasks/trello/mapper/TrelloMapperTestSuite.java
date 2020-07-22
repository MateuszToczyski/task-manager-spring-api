package com.crud.tasks.trello.mapper;

import com.crud.tasks.domain.trello.Board;
import com.crud.tasks.domain.trello.BoardDto;
import com.crud.tasks.domain.trello.Card;
import com.crud.tasks.domain.trello.CardDto;
import com.crud.tasks.mapper.TrelloMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrelloMapperTestSuite {

    @Autowired
    private TrelloMapper mapper;

    @Test
    public void testMapToBoards() {
        List<BoardDto> boardDtos = Arrays.asList(
                new BoardDto("Board 1", "1", new ArrayList<>()),
                new BoardDto("Board 2", "2", new ArrayList<>())
        );
        List<Board> boards = mapper.mapToBoards(boardDtos);
        assertEquals(2, boards.size());
        assertEquals("Board 1", boards.get(0).getName());
    }

    @Test
    public void testMapToBoardDtos() {
        List<Board> boards = Arrays.asList(
                new Board("Board 1", "1", new ArrayList<>()),
                new Board("Board 2", "2", new ArrayList<>())
        );
        List<BoardDto> boardDtos = mapper.mapToBoardDtos(boards);
        assertEquals(2, boardDtos.size());
        assertEquals("Board 1", boardDtos.get(0).getName());
    }

    @Test
    public void testMapToCard() {
        CardDto cardDto = new CardDto("Card 1", "Description", "pos", "1");
        Card card = mapper.mapToCard(cardDto);
        assertEquals("Card 1", card.getName());
        assertEquals("Description", card.getDescription());
    }

    @Test
    public void testMapToCardDto() {
        Card card = new Card("Card 1", "Description", "pos", "1");
        CardDto cardDto = mapper.mapToCardDto(card);
        assertEquals("Card 1", cardDto.getName());
        assertEquals("Description", cardDto.getDescription());
    }
}
