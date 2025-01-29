package com.anime.tracker.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.anime.tracker.entities.AnimeEntry;
import com.anime.tracker.exceptions.AnimeEntryNotFoundException;
import com.anime.tracker.repositories.AnimeEntryRepository;

@Service
public class AnimeEntryService {

	private AnimeEntryRepository animeEntryRepository;

	public AnimeEntryService(AnimeEntryRepository animeEntryRepository) {
		this.animeEntryRepository = animeEntryRepository;
	}

	public List<AnimeEntry> getAnimeEntries() {

		return animeEntryRepository.findAll();
	}

	public Optional<AnimeEntry> getAnimeEntryById(Long animeEntryId) {

		return animeEntryRepository.findById(animeEntryId);
	}

	public AnimeEntry createAnimeEntry(AnimeEntry animeEntry) {
		return animeEntryRepository.save(animeEntry);
	}

	public AnimeEntry updateAnimeEntry(Long animeEntryId, AnimeEntry animeEntry) {
		AnimeEntry existingAnimeEntry = animeEntryRepository.findById(animeEntryId)
				.orElseThrow(() -> new AnimeEntryNotFoundException(animeEntryId));

		if(animeEntry.getFinishedWatching() != null)
			existingAnimeEntry.setFinishedWatching(animeEntry.getFinishedWatching());
		if(animeEntry.getStartedWatching() != null)
			existingAnimeEntry.setStartedWatching(animeEntry.getStartedWatching());
		if(animeEntry.getStatus() != null)
			existingAnimeEntry.setStatus(animeEntry.getStatus());
		if(animeEntry.getWatchedEpisodes() != 0)
			existingAnimeEntry.setWatchedEpisodes(animeEntry.getWatchedEpisodes());
		if(animeEntry.getWatchList() != null)
			existingAnimeEntry.setWatchList(animeEntry.getWatchList());
		return animeEntryRepository.save(existingAnimeEntry);
	}

	public void deleteAnimeEntry(Long animeEntryId) {

		Optional<AnimeEntry> animeEntry = animeEntryRepository.findById(animeEntryId);
		if(animeEntry.isEmpty())
			throw new AnimeEntryNotFoundException(animeEntryId);
		animeEntryRepository.deleteById(animeEntryId);
	}
}