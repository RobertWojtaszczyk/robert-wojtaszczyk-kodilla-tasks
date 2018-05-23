package com.crud.tasks.trello.validator;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloCard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TrelloValidator {
    private static final Logger LOGGER = LoggerFactory.getLogger(TrelloValidator.class);

    public void validateCard(final TrelloCard trelloCard) {
        if (trelloCard.getName().contains("test")) {
            LOGGER.info("Someone is testing my application!");
        } else {
            LOGGER.info("Semms that my application is used proper way.");
        }
    }

    public List<TrelloBoard> validateTrelloBoards(final List<TrelloBoard> trelloBoards) {
        LOGGER.info("Starting filtering boards...");
        List<TrelloBoard> filteredBoards = trelloBoards.stream()
                .filter(this::isNotNull)
                .filter(this::isNotEmpty)
                .filter(trelloBoard -> !trelloBoard.getName().equalsIgnoreCase("test"))
                .collect(Collectors.toList());
        LOGGER.info("Boards have been filtered. Current list size: " + filteredBoards.size());
        return filteredBoards;
    }

    private boolean isNotNull(TrelloBoard trelloBoard) {
        return trelloBoard.getName() != null && trelloBoard.getId() != null;
    }

    private boolean isNotEmpty(TrelloBoard trelloBoard) {
        return !trelloBoard.getName().isEmpty() && !trelloBoard.getId().isEmpty();
    }
}
