package com.crud.tasks.trello.client;

import com.crud.tasks.domain.TrelloBoardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class TrelloClient {

    @Value("${trello.api.endpoint.prod}")
    private String trelloApiEndpoint;

    @Value("${trello.app.key}")
    private String trelloAppKey;

    @Value("${trello.app.token}")
    private String trelloToken;

    @Value("${trello.app.username}")
    private String trelloUsername;

    @Autowired
    private RestTemplate restTemplate;

    public List<TrelloBoardDto> getTrelloBoards() {

        URI url = boardsUri(trelloUsername, trelloAppKey, trelloToken, "name,id");

        Optional<TrelloBoardDto[]> boardsResponse =
                Optional.ofNullable(restTemplate.getForObject(url, TrelloBoardDto[].class));

        return boardsResponse.map(Arrays::asList).orElseGet(ArrayList::new);
    }

    private URI boardsUri(String username, String key, String token, String fields) {
        return UriComponentsBuilder.fromHttpUrl(trelloApiEndpoint + "/members/" + username + "/boards")
                .queryParam("key", key)
                .queryParam("token", token)
                .queryParam("fields", fields)
                .build().encode().toUri();
    }
}
