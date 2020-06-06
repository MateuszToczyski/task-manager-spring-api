package com.crud.tasks.trello.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class TrelloConfig {

    @Value("${trello.api.endpoint.prod}")
    private String apiEndpoint;

    @Value("${trello.app.key}")
    private String appKey;

    @Value("${trello.app.token}")
    private String token;

    @Value("${trello.app.username}")
    private String username;
}
