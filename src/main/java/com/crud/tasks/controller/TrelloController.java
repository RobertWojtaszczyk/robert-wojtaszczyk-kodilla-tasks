package com.crud.tasks.controller;

import com.crud.tasks.domain.CreatedTrelloCard;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.service.TrelloService;
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
    private TrelloService trelloService;
//    private TrelloClient trelloClient;

    @RequestMapping(method = RequestMethod.GET, value = "/getTrelloBoards")
    public List<TrelloBoardDto> getTrelloBoards() {
        return trelloService.fetchTrelloBoards().stream()
                .filter(this::isNotNull)
                .filter(this::isNotEmpty)
                .collect(Collectors.toList());
    }

    @RequestMapping(method = RequestMethod.POST, value = "/createTrelloCard")
    public CreatedTrelloCard createTrelloCard(@RequestBody TrelloCardDto trelloCardDto) {
        return trelloService.createTrelloCard(trelloCardDto);
    }

    private boolean isNotNull(TrelloBoardDto trelloBoardDto) {
        return trelloBoardDto.getName() != null && trelloBoardDto.getId() != null;
    }

    private boolean isNotEmpty(TrelloBoardDto trelloBoardDto) {
        return !trelloBoardDto.getName().isEmpty() && !trelloBoardDto.getId().isEmpty();
    }
}