package com.crud.tasks.trello.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.trello.Badges;
import com.crud.tasks.domain.trello.CardDto;
import com.crud.tasks.domain.trello.CreatedCardDto;
import com.crud.tasks.service.SimpleEmailService;
import com.crud.tasks.service.TrelloService;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class TrelloServiceTestSuite {

    @Mock private TrelloClient trelloClient;
    @Mock private SimpleEmailService emailService;
    @Mock private AdminConfig adminConfig;
    @InjectMocks private TrelloService trelloService;

    @Test
    public void testFetchBoards() {
        when(trelloClient.getTrelloBoards()).thenReturn(new ArrayList<>());
        assertEquals(0, trelloService.fetchBoards().size());
    }

    @Test
    public void testCreateCard() {
        CardDto cardDto = new CardDto("test_card", "des", "pos", "listId");
        doNothing().when(emailService).send(any());
        when(trelloClient.createNewCard(any())).thenReturn(
                new CreatedCardDto("1", "test_card", "url", new Badges()));
        CreatedCardDto createdCardDto = trelloService.createCard(cardDto);
        assertEquals("test_card", createdCardDto.getName());
    }
}
