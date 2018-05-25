package com.crud.tasks.trello.validator;

import com.crud.tasks.domain.TrelloBoard;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrelloValidatorTest {
    @Autowired
    private TrelloValidator trelloValidator;

    @Test
    public void shouldReturnTrelloBoardsList() {

        //Given
        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoard("1", null, null));
        trelloBoards.add(new TrelloBoard("2", "", null));
        trelloBoards.add(new TrelloBoard("3", "Test - should not pass", null));
        trelloBoards.add(new TrelloBoard("4", "Should pass", null));
        //When
        List<TrelloBoard> validatedTrelloBoardList = trelloValidator.validateTrelloBoards(trelloBoards);
        //Then
        assertEquals(1, validatedTrelloBoardList.size());
        assertEquals("4", validatedTrelloBoardList.get(0).getId());
    }


    //Given

    //When

    //Then
}