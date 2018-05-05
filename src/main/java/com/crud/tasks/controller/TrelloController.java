package com.crud.tasks.controller;

import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.trello.client.TrelloClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/trello")
@CrossOrigin(origins = "*")
public class TrelloController {

    @Autowired
    private TrelloClient trelloClient;

    @RequestMapping(method = RequestMethod.GET, value = "getTrelloBoards")
    public void getTrelloBoards() {
        List<TrelloBoardDto> trelloBoards = trelloClient.getTrelloBoards();
        trelloBoards.stream()
//                .filter(trelloBoardDto -> !Objects.equals(trelloBoardDto.getId(), null) && !Objects.equals(trelloBoardDto.getName(), null))
                .filter(TrelloController::isNotNull)
                .filter(TrelloController::isNotEmpty)
                .filter(trelloBoardDto -> trelloBoardDto.getName().toLowerCase().contains("kodilla"))
                .forEach(trelloBoardDto -> System.out.println(trelloBoardDto.getId() + " " + trelloBoardDto.getName()));
    }

    private static boolean isNotNull(TrelloBoardDto trelloBoardDto) {
        return trelloBoardDto.getName() != null && trelloBoardDto.getId() != null;
    }

    private static boolean isNotEmpty(TrelloBoardDto trelloBoardDto) {
        return !trelloBoardDto.getName().isEmpty() && !trelloBoardDto.getId().isEmpty();
    }
}
