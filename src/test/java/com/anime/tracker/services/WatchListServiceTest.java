package com.anime.tracker.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.anime.tracker.entities.WatchList;
import com.anime.tracker.exceptions.WatchListNotFoundException;
import com.anime.tracker.repositories.WatchListRepository;

@ExtendWith(MockitoExtension.class)
public class WatchListServiceTest {

    @Mock
    private WatchListRepository watchListRepository;

    @InjectMocks
    private WatchListService watchListService;

    @Test
    public void testGetWatchLists() {
        WatchList watchList1 = new WatchList();
        WatchList watchList2 = new WatchList();

        when(watchListRepository.findAll()).thenReturn(Arrays.asList(watchList1, watchList2));

        List<WatchList> result = watchListService.getAllWatchLists();

        assertEquals(2, result.size());
        verify(watchListRepository, times(1)).findAll();
    }

    @Test
    public void testGetWachListById() {
        WatchList watchList = new WatchList();
        watchList.setId(1L);

        when(watchListRepository.findById(1L)).thenReturn(Optional.of(watchList));

        WatchList result = watchListService.getWatchListById(1L);

        assertNotNull(result);
        verify(watchListRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetWatchListByIdNotFound() {
        when(watchListRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(WatchListNotFoundException.class, () -> {
            watchListService.getWatchListById(1L);
        });
    }

    @Test
    public void testCreateWatchList() {
        WatchList watchList = new WatchList();

        when(watchListRepository.save(watchList)).thenReturn(watchList);

        WatchList result = watchListService.createWatchList(watchList);

        assertNotNull(result);
        verify(watchListRepository, times(1)).save(watchList);
    }

    @Test
    public void testUpdateWachList() {
        WatchList existingWatchList = new WatchList();
        existingWatchList.setId(1L);
        existingWatchList.setName("watching");

        WatchList updateWatchList = new WatchList();
        updateWatchList.setName("completed");

        when(watchListRepository.findById(1L)).thenReturn(Optional.of(existingWatchList));
        when(watchListRepository.save(existingWatchList)).thenReturn(existingWatchList);

        WatchList result = watchListService.updateWatchList(1L, updateWatchList);

        assertEquals("completed", result.getName());
        verify(watchListRepository, times(1)).findById(1L);
        verify(watchListRepository, times(1)).save(existingWatchList);
    }

    @Test
    public void testUpdateWatchListNotFound() {

        WatchList updateWatchList = new WatchList();
        when(watchListRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(WatchListNotFoundException.class, () -> {
            watchListService.updateWatchList(1L, updateWatchList);
        });
    }

    @Test
    public void testDeleteWatchList() {
        WatchList watchList = new WatchList();
        when(watchListRepository.findById(1L)).thenReturn(Optional.of(watchList));

        watchListService.deleteWatchListById(1L);

        verify(watchListRepository, times(1)).findById(1L);
        verify(watchListRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testDeleteWatchListNotFound() {
        when(watchListRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(WatchListNotFoundException.class, () -> {
            watchListService.deleteWatchListById(1L);
        });
    }
}
