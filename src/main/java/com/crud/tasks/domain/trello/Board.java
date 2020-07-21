package com.crud.tasks.domain.trello;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class Board {
    private String id;
    private String name;
    private List<TrelloList> lists;
}
