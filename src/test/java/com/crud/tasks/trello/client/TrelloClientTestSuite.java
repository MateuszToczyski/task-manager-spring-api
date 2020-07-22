package com.crud.tasks.trello.client;

import com.crud.tasks.domain.trello.Badges;
import com.crud.tasks.domain.trello.BoardDto;
import com.crud.tasks.domain.trello.CardDto;
import com.crud.tasks.domain.trello.CreatedCardDto;
import com.crud.tasks.trello.config.TrelloConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrelloClientTestSuite {

    @InjectMocks
    private TrelloClient trelloClient;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private TrelloConfig trelloConfig;

    @Before
    public void init() {
        when(trelloConfig.getApiEndpoint()).thenReturn("http://test.com");
        when(trelloConfig.getAppKey()).thenReturn("test");
        when(trelloConfig.getToken()).thenReturn("test");
        when(trelloConfig.getUsername()).thenReturn("testUsername");
    }

    @Test
    public void shouldFetchTrelloBoards() throws URISyntaxException {

        BoardDto[] boardDtos = new BoardDto[1];

        boardDtos[0] = new BoardDto("test_board", "test_id", new ArrayList<>());

        URI uri = new URI("http://test.com/members/testUsername/boards?key=test&token=test&fields=name,id&lists=all");

        when(restTemplate.getForObject(uri, BoardDto[].class)).thenReturn(boardDtos);

        List<BoardDto> fetchedTrelloBoards = trelloClient.getTrelloBoards();

        assertEquals(1, fetchedTrelloBoards.size());
        assertEquals("test_id", fetchedTrelloBoards.get(0).getName());
        assertEquals("test_board", fetchedTrelloBoards.get(0).getId());
        assertEquals(new ArrayList<>(), fetchedTrelloBoards.get(0).getLists());
    }

    @Test
    public void shouldCreateCard() throws URISyntaxException {

        CardDto cardDto = new CardDto("Test task", "Test Description", "top", "test_id");

        URI uri = new URI("http://test.com/cards?key=test&token=test&name=Test%20task&desc=Test%20Description&pos=top&idList=test_id");

        CreatedCardDto createdTrelloCard = new CreatedCardDto("1", "Test task",
                "http://test.com", new Badges());

        when(restTemplate.postForObject(uri, null, CreatedCardDto.class)).thenReturn(createdTrelloCard);

        CreatedCardDto newCard = trelloClient.createNewCard(cardDto);

        assertEquals("1", newCard.getId());
        assertEquals("Test task", newCard.getName());
        assertEquals("http://test.com", newCard.getShortUrl());
    }

    @Test
    public void shouldReturnEmptyList() throws URISyntaxException {

        URI uri = new URI("http://test.com/members/testUsername/boards?key=test&token=test&fields=name,id&lists=all");

        when(restTemplate.getForObject(uri, BoardDto[].class)).thenReturn(null);

        List<BoardDto> fetchedTrelloBoards = trelloClient.getTrelloBoards();

        assertEquals(0, fetchedTrelloBoards.size());
    }
}