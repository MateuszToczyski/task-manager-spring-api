package com.crud.tasks.trello.facade;

import com.crud.tasks.domain.trello.Board;
import com.crud.tasks.domain.trello.BoardDto;
import com.crud.tasks.domain.trello.TrelloList;
import com.crud.tasks.domain.trello.TrelloListDto;
import com.crud.tasks.mapper.TrelloMapper;
import com.crud.tasks.service.TrelloService;
import com.crud.tasks.trello.validator.TrelloValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrelloFacadeTestSuite {

    @InjectMocks private TrelloFacade facade;
    @Mock private TrelloService service;
    @Mock private TrelloValidator validator;
    @Mock private TrelloMapper mapper;

    @Test
    public void shouldFetchEmptyList() {
        List<TrelloListDto> listDtos = new ArrayList<>();
        listDtos.add(new TrelloListDto("1", "test_list", false));

        List<BoardDto> boardDtos = new ArrayList<>();
        boardDtos.add(new BoardDto("1", "test", listDtos));

        List<TrelloList> mappedLists = new ArrayList<>();
        mappedLists.add(new TrelloList("1", "test_list", false));

        List<Board> mappedBoards = new ArrayList<>();
        mappedBoards.add(new Board("1", "test", mappedLists));

        when(service.fetchBoards()).thenReturn(boardDtos);
        when(mapper.mapToBoards(boardDtos)).thenReturn(mappedBoards);
        when(mapper.mapToBoardDtos(anyList())).thenReturn(new ArrayList<>());
        when(validator.validateBoards(mappedBoards)).thenReturn(new ArrayList<>());

        List<BoardDto> fetchedBoardDtos = facade.fetchTrelloBoards();

        assertNotNull(fetchedBoardDtos);
        assertEquals(0, fetchedBoardDtos.size());
    }

    @Test
    public void shouldFetchTrelloBoards() {
        List<TrelloListDto> listDtos = new ArrayList<>();
        listDtos.add(new TrelloListDto("1", "test_list", false));

        List<BoardDto> boardDtos = new ArrayList<>();
        boardDtos.add(new BoardDto("1", "test", listDtos));

        List<TrelloList> mappedLists = new ArrayList<>();
        mappedLists.add(new TrelloList("1", "test_list", false));

        List<Board> mappedBoards = new ArrayList<>();
        mappedBoards.add(new Board("1", "test", mappedLists));

        when(service.fetchBoards()).thenReturn(boardDtos);
        when(mapper.mapToBoards(boardDtos)).thenReturn(mappedBoards);
        when(mapper.mapToBoardDtos(anyList())).thenReturn(boardDtos);
        when(validator.validateBoards(mappedBoards)).thenReturn(mappedBoards);

        List<BoardDto> fetchedBoardDtos = facade.fetchTrelloBoards();

        assertNotNull(fetchedBoardDtos);
        assertEquals(1, fetchedBoardDtos.size());

        boardDtos.forEach(boardDto -> {
            assertEquals("1", boardDto.getId());
            assertEquals("test", boardDto.getName());
            boardDto.getLists().forEach(listDto -> {
                assertEquals("1", listDto.getId());
                assertEquals("test_list", listDto.getName());
                assertFalse(listDto.isClosed());
            });
        });
    }
}
