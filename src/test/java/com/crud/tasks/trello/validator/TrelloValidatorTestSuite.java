package com.crud.tasks.trello.validator;

import com.crud.tasks.domain.trello.Board;
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
public class TrelloValidatorTestSuite {

    @Autowired private TrelloValidator validator;

    @Test
    public void testValidateBoards() {
        List<Board> boards = Arrays.asList(
                new Board("1", "test", new ArrayList<>()),
                new Board("2", "prod", new ArrayList<>())
        );
        List<Board> filteredBoards = validator.validateBoards(boards);
        assertEquals(1, filteredBoards.size());
    }
}
