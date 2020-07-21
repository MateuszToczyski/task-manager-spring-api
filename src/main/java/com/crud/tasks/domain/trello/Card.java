package com.crud.tasks.domain.trello;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Card {
    private String name;
    private String description;
    private String pos;
    private String listId;
}
