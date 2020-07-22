package com.crud.tasks.mapper;

import com.crud.tasks.domain.trello.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TrelloMapper {

    public Board mapToBoard(BoardDto boardDto) {
        return new Board(boardDto.getName(), boardDto.getId(), mapToLists(boardDto.getLists()));
    }

    public BoardDto mapToBoardDto(Board board) {
        return new BoardDto(board.getId(), board.getName(), mapToListDtos(board.getLists()));
    }

    public List<Board> mapToBoards(List<BoardDto> boardDtos) {
        return boardDtos.stream()
                .map(this::mapToBoard)
                .collect(Collectors.toList());
    }

    public List<BoardDto> mapToBoardDtos(List<Board> boards) {
        return boards.stream()
                .map(this::mapToBoardDto)
                .collect(Collectors.toList());
    }

    public List<TrelloList> mapToLists(List<TrelloListDto> listDtos) {
        return listDtos.stream()
                .map(listDto -> new TrelloList(listDto.getId(), listDto.getName(), listDto.isClosed()))
                .collect(Collectors.toList());
    }

    public List<TrelloListDto> mapToListDtos(List<TrelloList> lists) {
        return lists.stream()
                .map(list -> new TrelloListDto(list.getId(), list.getName(), list.isClosed()))
                .collect(Collectors.toList());
    }

    public CardDto mapToCardDto (Card card) {
        return new CardDto(card.getName(), card.getDescription(), card.getPos(), card.getListId());
    }

    public Card mapToCard(CardDto cardDto) {
        return new Card(cardDto.getName(), cardDto.getDescription(), cardDto.getPos(), cardDto.getListId());
    }
}
