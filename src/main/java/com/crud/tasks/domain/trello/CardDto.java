package com.crud.tasks.domain.trello;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CardDto {
    private String name;
    private String description;
    private String pos;
    private String listId;
}
