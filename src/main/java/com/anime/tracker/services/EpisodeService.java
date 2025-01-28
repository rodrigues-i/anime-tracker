package com.anime.tracker.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.anime.tracker.entities.Episode;
import com.anime.tracker.exceptions.EpisodeNotFoundException;
import com.anime.tracker.repositories.EpisodeRepository;

@Service
public class EpisodeService {

	private EpisodeRepository episodeRepository;

	public EpisodeService(EpisodeRepository episodeRepository) {
		this.episodeRepository = episodeRepository;
	}

	public List<Episode> getEpisodes() {
		return episodeRepository.findAll();
	}

	public Episode getEpisodeById(Long episodeId) {
		return episodeRepository.findById(episodeId)
				.orElseThrow(() -> new EpisodeNotFoundException(episodeId));
	}

	public Episode createEpisode(Episode episode) {
		return episodeRepository.save(episode);
	}

	public Episode updateEpisode(Episode episode, Long episodeId) {
		Episode existingEpisode = episodeRepository.findById(episodeId)
				.orElseThrow(() -> new EpisodeNotFoundException(episodeId));

		if(episode.getTitle() != null)
			existingEpisode.setTitle(episode.getTitle());
		if(episode.getSeason() != null)
			existingEpisode.setSeason(episode.getSeason());
		if(episode.getReleaseDate() != null)
			existingEpisode.setReleaseDate(episode.getReleaseDate());
		if(episode.getEpisodeNumber() != 0)
			existingEpisode.setEpisodeNumber(episode.getEpisodeNumber());
		if(episode.getDescription() != null)
			existingEpisode.setDescription(episode.getDescription());

		return episodeRepository.save(existingEpisode);
	}

	public void deleteEpisode(Long episodeId) {

		episodeRepository.findById(episodeId)
		.orElseThrow(() -> new EpisodeNotFoundException(episodeId));

		episodeRepository.deleteById(episodeId);
	}
}