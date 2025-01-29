package com.anime.tracker.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.anime.tracker.entities.AnimeEntry;
import com.anime.tracker.exceptions.AnimeEntryNotFoundException;
import com.anime.tracker.repositories.AnimeEntryRepository;

@ExtendWith(MockitoExtension.class)
public class AnimeEntryServiceTest {

    @Mock
    private AnimeEntryRepository animeEntryRepository;

    @InjectMocks
    private AnimeEntryService animeEntryService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAnimeEntries() {
        AnimeEntry animeEntry1 = new AnimeEntry();
        AnimeEntry animeEntry2 = new AnimeEntry();

        when(animeEntryRepository.findAll()).thenReturn(Arrays.asList(animeEntry1, animeEntry2));

        List<AnimeEntry> result = animeEntryService.getAnimeEntries();

        assertEquals(2, result.size());
        verify(animeEntryRepository, times(1)).findAll();
    }

    @Test
    public void testGetAnimeEntryById() {
        AnimeEntry animeEntry = new AnimeEntry();
        when(animeEntryRepository.findById(1L)).thenReturn(Optional.of(animeEntry));

        Optional<AnimeEntry> result = animeEntryService.getAnimeEntryById(1L);

        assertTrue(result.isPresent());
        verify(animeEntryRepository, times(1)).findById(1L);
    }

    @Test
    public void testCreateAnimeEntry() {
        AnimeEntry animeEntry = new AnimeEntry();
        when(animeEntryRepository.save(animeEntry)).thenReturn(animeEntry);

        AnimeEntry result = animeEntryService.createAnimeEntry(animeEntry);

        assertNotNull(result);
        verify(animeEntryRepository, times(1)).save(animeEntry);
    }

    @Test
    public void testUpdateAnimeEntry() {
        AnimeEntry existingAnimeEntry = new AnimeEntry();
        existingAnimeEntry.setId(1L);;

        AnimeEntry updateAnimeEntry = new AnimeEntry();
        LocalDate dateFinished = LocalDate.now();
        updateAnimeEntry.setFinishedWatching(dateFinished);

        when(animeEntryRepository.findById(1L)).thenReturn(Optional.of(existingAnimeEntry));
        when(animeEntryRepository.save(existingAnimeEntry)).thenReturn(existingAnimeEntry);

        AnimeEntry result = animeEntryService.updateAnimeEntry(1L, updateAnimeEntry);

        assertTrue(result.getFinishedWatching().equals(dateFinished));
        verify(animeEntryRepository, times(1)).findById(1L);
        verify(animeEntryRepository, times(1)).save(existingAnimeEntry);
    }

    @Test
    public void testDeleteAnimeEntry() {
        AnimeEntry animeEntry = new AnimeEntry();
        when(animeEntryRepository.findById(1L)).thenReturn(Optional.of(animeEntry));

        animeEntryService.deleteAnimeEntry(1L);

        verify(animeEntryRepository, times(1)).findById(1L);
        verify(animeEntryRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testDeleteAnimeEntryNotFound() {
        when(animeEntryRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(AnimeEntryNotFoundException.class, () -> {
            animeEntryService.deleteAnimeEntry(1L);
        });
    }
}
