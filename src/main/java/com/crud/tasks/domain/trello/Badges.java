package com.crud.tasks.domain.trello;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Badges {
    private int votes;
    private AttachmentsByType attachmentsByType;
}
