package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrelloMapperTestSuite {
    @Autowired
    private TrelloMapper trelloMapper;

    @Test
    public void testMapToBoards() {
        //Given
        List<TrelloBoardDto> trelloBoardDtos = new ArrayList<>();
        List<TrelloListDto> trelloListDtos = new ArrayList<>();
        trelloListDtos.add(new TrelloListDto("1","List1",true));
        trelloListDtos.add(new TrelloListDto("2","List2",false));
        trelloBoardDtos.add(new TrelloBoardDto("1","Board1",trelloListDtos));
        trelloBoardDtos.add(new TrelloBoardDto("2","Board2",trelloListDtos));
        //When
        List<TrelloBoard> trelloBoards = trelloMapper.mapToBoards(trelloBoardDtos);
        //Then
        assertEquals(2, trelloBoards.size());
        assertEquals("1",trelloBoards.get(0).getId());
        assertEquals("Board1",trelloBoards.get(0).getName());
        assertEquals(2, trelloBoards.get(0).getLists().size());
        assertEquals("2",trelloBoards.get(1).getId());
        assertEquals("Board2",trelloBoards.get(1).getName());
        assertEquals(2, trelloBoards.get(1).getLists().size());
    }

    @Test
    public void testMapToBoardsDto() {
        //Given
        List<TrelloBoard> trelloBoards = new ArrayList<>();
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloList("1","List1",true));
        trelloLists.add(new TrelloList("2","List2",false));
        trelloBoards.add(new TrelloBoard("1","Board1",trelloLists));
        trelloBoards.add(new TrelloBoard("2","Board2",trelloLists));
        //When
        List<TrelloBoardDto> trelloBoardDtos = trelloMapper.mapToBoardsDto(trelloBoards);
        //Then
        assertEquals(2, trelloBoardDtos.size());
        assertEquals("1",trelloBoardDtos.get(0).getId());
        assertEquals("Board1",trelloBoardDtos.get(0).getName());
        assertEquals(2, trelloBoardDtos.get(0).getLists().size());
        assertEquals("2",trelloBoardDtos.get(1).getId());
        assertEquals("Board2",trelloBoardDtos.get(1).getName());
        assertEquals(2, trelloBoardDtos.get(1).getLists().size());
    }

    @Test
    public void testMapToList() {
        //Given
        List<TrelloListDto> trelloListDtos = new ArrayList<>();
        trelloListDtos.add(new TrelloListDto("1","List1",true));
        trelloListDtos.add(new TrelloListDto("2","List2",false));
        //When
        List<TrelloList> trelloLists = trelloMapper.mapToList(trelloListDtos);
        //Then
        assertEquals(2, trelloLists.size());
        assertEquals("1", trelloLists.get(0).getId());
        assertEquals("List1", trelloLists.get(0).getName());
        assertTrue(trelloLists.get(0).isClosed());
        assertEquals("2", trelloLists.get(1).getId());
        assertEquals("List2", trelloLists.get(1).getName());
        assertFalse(trelloLists.get(1).isClosed());
    }

    @Test
    public void testMapToListDto() {
        //Given
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloList("1","List1",true));
        trelloLists.add(new TrelloList("2","List2",false));
        //When
        List<TrelloListDto> trelloListDtos = trelloMapper.mapToListDto(trelloLists);
        //Then
        assertEquals(2, trelloListDtos.size());
        assertEquals("1", trelloListDtos.get(0).getId());
        assertEquals("List1", trelloListDtos.get(0).getName());
        assertTrue(trelloListDtos.get(0).isClosed());
        assertEquals("2", trelloListDtos.get(1).getId());
        assertEquals("List2", trelloListDtos.get(1).getName());
        assertFalse(trelloListDtos.get(1).isClosed());
    }

    @Test
    public void testMapToCardDto() {
        //Given
        TrelloCard trelloCard = new TrelloCard("Card","card","top","1");
        //When
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);
        //Then
        assertEquals("Card",trelloCardDto.getName());
        assertEquals("card",trelloCardDto.getDescription());
        assertEquals("top",trelloCardDto.getPos());
        assertEquals("1",trelloCardDto.getListId());
    }

    @Test
    public void testMapToCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("Card","card","top","1");
        //When
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);
        //Then
        assertEquals("Card",trelloCard.getName());
        assertEquals("card",trelloCard.getDescription());
        assertEquals("top",trelloCard.getPos());
        assertEquals("1",trelloCard.getListId());
    }
}