package com.anime.tracker.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.anime.tracker.entities.Season;
import com.anime.tracker.exceptions.SeasonNotFoundException;
import com.anime.tracker.repositories.SeasonRepository;

@ExtendWith(MockitoExtension.class)
public class SeasonServiceTest {

    @Mock
    private SeasonRepository seasonRepository;

    @InjectMocks
    private SeasonService seasonService;

    @Test
    public void testGetSeasons() {
        Season season1 = new Season();
        Season season2 = new Season();
        when(seasonRepository.findAll()).thenReturn(List.of(season1, season2));

        List<Season> seasons = seasonService.getSeasons();

        assertEquals(2, seasons.size());
        verify(seasonRepository, times(1)).findAll();
    }

    @Test
    public void testGetSeasonById() {
        Season season = new Season();
        when(seasonRepository.findById(1L)).thenReturn(Optional.of(season));

        Season result = seasonService.getSeasonById(1L);

        assertNotNull(result);
        assertEquals(season, result);
        verify(seasonRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetSeasonByIdNotFound() {
        when(seasonRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(SeasonNotFoundException.class, () -> {
            seasonService.getSeasonById(1L);
        });
    }

    @Test
    public void testCreateSeason() {
        Season season = new Season();
        when(seasonRepository.save(season)).thenReturn(season);

        Season result = seasonService.createSeason(season);

        assertNotNull(result);
        assertEquals(season, result);
        verify(seasonRepository, times(1)).save(season);
    }

    @Test
    public void testUpdateSeason() {
        Season existingSeason = new Season();
        existingSeason.setId(1L);
        existingSeason.setTitle("Old Nmae");

        Season updateSeason = new Season();
        updateSeason.setId(1L);
        updateSeason.setTitle("New Name");

        when(seasonRepository.findById(1L)).thenReturn(Optional.of(existingSeason));
        when(seasonRepository.save(existingSeason)).thenReturn(existingSeason);

        Season result = seasonService.updateSeason(1L, updateSeason);

        assertEquals("New Name", result.getTitle());
        verify(seasonRepository, times(1)).findById(1L);
        verify(seasonRepository, times(1)).save(existingSeason);
    }

    @Test
    public void testUpdateSeasonNotFound() {
        Season updateSeason = new Season();
        updateSeason.setId(1L);

        when(seasonRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(SeasonNotFoundException.class, () -> {
            seasonService.updateSeason(1L, updateSeason);
        });
    }

    @Test
    public void testDeleteSeason() {
        Season season = new Season();
        when(seasonRepository.findById(1L)).thenReturn(Optional.of(season));

        seasonService.deleteSeason(1L);

        verify(seasonRepository, times(1)).deleteById(1L);
    }
}
