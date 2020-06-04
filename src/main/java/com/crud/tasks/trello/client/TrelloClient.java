package com.crud.tasks.trello.client;

import com.crud.tasks.domain.trello.CreatedCard;
import com.crud.tasks.domain.trello.BoardDto;
import com.crud.tasks.domain.trello.CardDto;
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

    public List<BoardDto> getTrelloBoards() {

        URI url = boardsUri(trelloUsername, trelloAppKey, trelloToken, "name,id", "all");

        Optional<BoardDto[]> boardsResponse =
                Optional.ofNullable(restTemplate.getForObject(url, BoardDto[].class));

        return boardsResponse.map(Arrays::asList).orElseGet(ArrayList::new);
    }

    public CreatedCard createNewCard(CardDto cardDto) {

        URI url = cardCreateUri(trelloAppKey, trelloToken, cardDto.getName(), cardDto.getDescription(),
                cardDto.getPos(), cardDto.getListId());

        return restTemplate.postForObject(url, null, CreatedCard.class);
    }

    private URI boardsUri(String username, String key, String token, String fields, String lists) {
        return UriComponentsBuilder.fromHttpUrl(trelloApiEndpoint + "/members/" + username + "/boards")
                .queryParam("key", key)
                .queryParam("token", token)
                .queryParam("fields", fields)
                .queryParam("lists", lists)
                .build().encode().toUri();
    }

    private URI cardCreateUri(String key, String token, String name, String desc, String pos, String idList) {
        return UriComponentsBuilder.fromHttpUrl(trelloApiEndpoint + "/cards")
                .queryParam("key", key)
                .queryParam("token", token)
                .queryParam("name", name)
                .queryParam("desc", desc)
                .queryParam("pos", pos)
                .queryParam("idList", idList)
                .build().encode().toUri();
    }
}
