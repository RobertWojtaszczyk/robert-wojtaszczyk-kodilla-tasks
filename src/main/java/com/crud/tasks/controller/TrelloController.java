package com.crud.tasks.controller;

import com.crud.tasks.domain.CreatedTrelloCard;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.trello.client.TrelloClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/trello")
@CrossOrigin(origins = "*")
//@CrossOrigin("*")
public class TrelloController {

    @Autowired
    private TrelloClient trelloClient;

    @RequestMapping(method = RequestMethod.GET, value = "getTrelloBoards")
    public List<TrelloBoardDto> getTrelloBoards() {
        return trelloClient.getTrelloBoards().stream()
//                .filter(trelloBoardDto -> !Objects.equals(trelloBoardDto.getId(), null) && !Objects.equals(trelloBoardDto.getName(), null))
                .filter(this::isNotNull)
                .filter(this::isNotEmpty)
//                .filter(trelloBoardDto -> trelloBoardDto.getName().toLowerCase().contains("kodilla"))
                .collect(Collectors.toList());
    }

    @RequestMapping(method = RequestMethod.POST, value = "createTrelloCard")
    public CreatedTrelloCard createTrelloCard(@RequestBody TrelloCardDto trelloCardDto) {
        return trelloClient.createNewCard(trelloCardDto);
    }

    private boolean isNotNull(TrelloBoardDto trelloBoardDto) {
        return trelloBoardDto.getName() != null && trelloBoardDto.getId() != null;
    }

    private boolean isNotEmpty(TrelloBoardDto trelloBoardDto) {
        return !trelloBoardDto.getName().isEmpty() && !trelloBoardDto.getId().isEmpty();
    }
}
