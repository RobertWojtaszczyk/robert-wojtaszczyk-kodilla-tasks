package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TrelloMapper {

    public List<TrelloBoard> mapToBoards(final List<TrelloBoardDto> trelloBoardDto) {
        return trelloBoardDto.stream()
                .map(this::mapToBoard)
                .collect(Collectors.toList());
    }

    public List<TrelloBoardDto> mapToBoardsDto(final List<TrelloBoard> trelloBoards) {
        return trelloBoards.stream()
                .map(this::mapToTrelloBoardDto)
                .collect(Collectors.toList());
    }

    public List<TrelloList> mapToList(final List<TrelloListDto> trelloListDto) {
        return trelloListDto.stream()
                .map(this::mapToTrelloList)
                .collect(Collectors.toList());
    }

    public List<TrelloListDto> mapToListDto(final List<TrelloList> trelloLists) {
        return trelloLists.stream()
                .map(this::mapToTrelloListDto)
                .collect(Collectors.toList());
    }

    public TrelloCardDto mapToCardDto(final TrelloCard trelloCard) {
        return new TrelloCardDto(
                trelloCard.getName(),
                trelloCard.getDescription(),
                trelloCard.getPos(),
                trelloCard.getListId());
    }

    public TrelloCard mapToCard(final TrelloCardDto trelloCardDto) {
        return new TrelloCard(
                trelloCardDto.getName(),
                trelloCardDto.getDescription(),
                trelloCardDto.getPos(),
                trelloCardDto.getListId());
    }

    private TrelloBoard mapToBoard(final TrelloBoardDto trelloBoardDto) {
        return new TrelloBoard(
                trelloBoardDto.getId(),
                trelloBoardDto.getName(),
                mapToList(trelloBoardDto.getLists()));
    }

    private TrelloBoardDto mapToTrelloBoardDto(TrelloBoard trelloBoard) {
        return new TrelloBoardDto(
                trelloBoard.getId(),
                trelloBoard.getName(),
                mapToListDto(trelloBoard.getLists()));
    }

    private TrelloList mapToTrelloList(TrelloListDto trelloList) {
        return new TrelloList(
                trelloList.getId(),
                trelloList.getName(),
                trelloList.isClosed());
    }

    private TrelloListDto mapToTrelloListDto(TrelloList trelloList) {
        return new TrelloListDto(
                trelloList.getId(),
                trelloList.getName(),
                trelloList.isClosed());
    }
}
