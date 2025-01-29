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

import com.anime.tracker.entities.Episode;
import com.anime.tracker.exceptions.EpisodeNotFoundException;
import com.anime.tracker.repositories.EpisodeRepository;

@ExtendWith(MockitoExtension.class)
public class EpisodeServiceTest {

    @Mock
    private EpisodeRepository episodeRepository;

    @InjectMocks
    private EpisodeService episodeService;

    @BeforeEach
    public void setUp() {
        // No need to initialize mocks manually, MockitoExtension does it automatically
    }

    @Test
    public void testGetAllEpisodes() {
        Episode episode1 = new Episode();
        Episode episode2 = new Episode();

        when(episodeRepository.findAll()).thenReturn(Arrays.asList(episode1, episode2));

        List<Episode> result = episodeService.getEpisodes();

        assertEquals(2, result.size());
        verify(episodeRepository, times(1)).findAll();
    }

    @Test
    public void testGetEpisodeById() {
        Episode episode = new Episode();
        when(episodeRepository.findById(1L)).thenReturn(Optional.of(episode));

        Episode result = episodeService.getEpisodeById(1L);

        assertNotNull(result);
        assertEquals(episode, result);
        verify(episodeRepository, times(1)).findById(1l);
    }

    @Test
    public void testCreateEpisode() {
        Episode episode = new Episode();
        when(episodeRepository.save(episode)).thenReturn(episode);

        Episode result = episodeService.createEpisode(episode);

        assertNotNull(result);
        verify(episodeRepository, times(1)).save(episode);
    }

    @Test
    public void testUpdateEpisode() {
        Episode existingEpisode = new Episode();
        existingEpisode.setId(1L);
        existingEpisode.setTitle("Old Title");
        existingEpisode.setEpisodeNumber(20);

        Episode updateEpisode = new Episode();
        updateEpisode.setId(1L);
        updateEpisode.setTitle("New Title");
        updateEpisode.setEpisodeNumber(25);

        when(episodeRepository.findById(1L)).thenReturn(Optional.of(existingEpisode));
        when(episodeRepository.save(existingEpisode)).thenReturn(existingEpisode);

        Episode result = episodeService.updateEpisode(updateEpisode, 1L);

        assertEquals("New Title", result.getTitle());
        assertEquals(25, result.getEpisodeNumber());
        verify(episodeRepository, times(1)).findById(1L);
        verify(episodeRepository, times(1)).save(existingEpisode);
    }

    @Test
    public void testUpdateEpisodeNotFound() {
        Episode updateEpisode = new Episode();
        updateEpisode.setTitle("New Title");
        updateEpisode.setEpisodeNumber(25);

        when(episodeRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EpisodeNotFoundException.class, () -> {
            episodeService.updateEpisode(updateEpisode, 1L);
        });
    }

    @Test
    public void testDeleteEpisode() {
        Episode episode = new Episode();
        episode.setId(1L);
        when(episodeRepository.findById(1L)).thenReturn(Optional.of(episode));

        episodeService.deleteEpisode(1L);;

        verify(episodeRepository, times(1)).findById(1l);
        verify(episodeRepository, times(1)).deleteById(1L);
    }
}
