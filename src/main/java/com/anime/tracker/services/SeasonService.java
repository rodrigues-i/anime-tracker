package com.anime.tracker.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.anime.tracker.entities.Season;
import com.anime.tracker.exceptions.SeasonNotFoundException;
import com.anime.tracker.repositories.SeasonRepository;

@Service
public class SeasonService {

    private SeasonRepository seasonRepository;

    public SeasonService(SeasonRepository seasonRepository) {
        this.seasonRepository = seasonRepository;
    }

    public List<Season> getSeasons() {
        return seasonRepository.findAll();
    }

    public Season getSeasonById(Long seasonId) {
        return seasonRepository.findById(seasonId)
                                .orElseThrow(() -> new SeasonNotFoundException(seasonId));
    }

    public Season createSeason(Season season) {
        return seasonRepository.save(season);
    }

    public Season updateSeason(Long seasonId, Season season) {
        Season existingSeason = seasonRepository.findById(seasonId)
                                        .orElseThrow(() -> new SeasonNotFoundException(seasonId));
        if(season.getAnime() != null)
            existingSeason.setAnime(season.getAnime());
        if(season.getEpisodes() != null)
            existingSeason.setEpisodes(season.getEpisodes());
        if(season.getTitle() != null)
            existingSeason.setTitle(season.getTitle());
        return seasonRepository.save(existingSeason);
    }

    public void deleteSeason(Long seasonId) {
        seasonRepository.findById(seasonId)
                        .orElseThrow(() -> new SeasonNotFoundException(seasonId));
        seasonRepository.deleteById(seasonId);
    }

}
