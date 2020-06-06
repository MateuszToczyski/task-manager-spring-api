package com.crud.tasks.trello.client;

import com.crud.tasks.domain.trello.CreatedCard;
import com.crud.tasks.domain.trello.BoardDto;
import com.crud.tasks.domain.trello.CardDto;
import com.crud.tasks.trello.config.TrelloConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class TrelloClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(TrelloClient.class);

    @Autowired
    private TrelloConfig trelloConfig;

    @Autowired
    private RestTemplate restTemplate;

    public List<BoardDto> getTrelloBoards() {

        URI url = boardsUri(trelloConfig.getUsername(), trelloConfig.getAppKey(), trelloConfig.getToken(),
                "name,id", "all");

        try {
            BoardDto[] boardsResponse = restTemplate.getForObject(url, BoardDto[].class);
            return Arrays.asList(Optional.ofNullable(boardsResponse).orElse(new BoardDto[0]));
        } catch(RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return new ArrayList<>();
        }
    }

    public CreatedCard createNewCard(CardDto cardDto) {

        URI url = cardCreateUri(trelloConfig.getAppKey(), trelloConfig.getToken(), cardDto.getName(),
                cardDto.getDescription(), cardDto.getPos(), cardDto.getListId());

        return restTemplate.postForObject(url, null, CreatedCard.class);
    }

    private URI boardsUri(String username, String key, String token, String fields, String lists) {
        return UriComponentsBuilder.fromHttpUrl(trelloConfig.getApiEndpoint() + "/members/" + username + "/boards")
                .queryParam("key", key)
                .queryParam("token", token)
                .queryParam("fields", fields)
                .queryParam("lists", lists)
                .build().encode().toUri();
    }

    private URI cardCreateUri(String key, String token, String name, String desc, String pos, String idList) {
        return UriComponentsBuilder.fromHttpUrl(trelloConfig.getApiEndpoint() + "/cards")
                .queryParam("key", key)
                .queryParam("token", token)
                .queryParam("name", name)
                .queryParam("desc", desc)
                .queryParam("pos", pos)
                .queryParam("idList", idList)
                .build().encode().toUri();
    }
}
