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

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.anime.tracker.entities.Anime;
import com.anime.tracker.exceptions.AnimeNotFoundException;
import com.anime.tracker.repositories.AnimeRepository;

@ExtendWith(MockitoExtension.class)
public class AnimeServiceTest {

    @Mock
    private AnimeRepository animeRepository;

    @InjectMocks
    private AnimeService animeService;

    @BeforeEach
    public void setUp() {
        // No need to initialize mocks manually, MockitoExtension does it automatically
    }

    @Test
    public void testGetAllAnimes() {
        Anime anime1 = new Anime();
        Anime anime2 = new Anime();

        when(animeRepository.findAll()).thenReturn(Arrays.asList(anime1, anime2));

        List<Anime> result = animeService.getAnimes();

        int expected = 2;
        assertEquals(expected, result.size());
        verify(animeRepository, times(1)).findAll();
    }

    @Test
    public void testGetAnimeById() {
        Anime anime = new Anime();
        when(animeRepository.findById(1L)).thenReturn(Optional.of(anime));

        Optional<Anime> result = animeService.getAnimeById(1L);

        assertNotNull(result);
        assertEquals(anime, result.get());
        verify(animeRepository, times(1)).findById(1L);
    }

    @Test
    public void testCreateAnime() {
        Anime anime = new Anime();
        when(animeRepository.save(anime)).thenReturn(anime);

        Anime result = animeService.createAnime(anime);

        assertNotNull(result);
        verify(animeRepository, times(1)).save(anime);
    }

    @Test
    public void testUpdateAnime() {
        Anime existingAnime = new Anime();
        existingAnime.setId(1L);
        existingAnime.setTitle("Old Name");
        existingAnime.setEpisodes(24);

        Anime updateAnime = new Anime();
        updateAnime.setTitle("New Name");
        updateAnime.setEpisodes(24);

        when(animeRepository.findById(1L)).thenReturn(Optional.of(existingAnime));
        when(animeRepository.save(existingAnime)).thenReturn(updateAnime);

        Anime result = animeService.updateAnime(updateAnime, 1L).get();

        assertEquals("New Name", result.getTitle());
        assertEquals(24, result.getEpisodes());
        verify(animeRepository, times(1)).findById(1L);
        verify(animeRepository, times(1)).save(existingAnime);
    }

    @Test
    public void testUpdateAnimeNotFound() {
        Anime updateAnime = new Anime();
        updateAnime.setTitle("New Name");
        updateAnime.setEpisodes(24);

        when(animeRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(AnimeNotFoundException.class, () -> {
            animeService.updateAnime(updateAnime, 1L);
        });
    }

    @Test
    public void testDeleteAnime() {
        Anime anime = new Anime();
        when(animeRepository.findById(1L)).thenReturn(Optional.of(anime));

        animeService.deleteAnime(1L);;

        verify(animeRepository, times(1)).findById(1L);
        verify(animeRepository, times(1)).deleteById(1L);
    }
}
