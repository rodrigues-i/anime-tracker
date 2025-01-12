package com.anime.tracker.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.anime.tracker.entities.Anime;
import com.anime.tracker.repositories.AnimeRepository;

@Service
public class AnimeService
{
	private AnimeRepository animeRepository;

	public AnimeService(AnimeRepository animeRepository)
	{
		this.animeRepository = animeRepository;
	}

	public List<Anime> getAnime()
	{
		List<Anime> animes = this.animeRepository.findAll();
		return animes;
	}

	public Optional<Anime> getAnimeById(Long id) {
		return this.animeRepository.findById(id);
	}

	public Anime createAnime(Anime anime) {
		return this.animeRepository.save(anime);
	}

	public Optional<Anime> updateAnime(Anime anime, Long id)
	{
		Optional<Anime> optionalItem = this.animeRepository.findById(id);
		if(optionalItem.isEmpty())
			return optionalItem;
		Anime animeToBeUpdated = optionalItem.get();
		if(anime.getName() != null)
			animeToBeUpdated.setName(anime.getName());
		if(anime.getAuthor() != null)
			animeToBeUpdated.setAuthor(anime.getAuthor());
		if(anime.getGenre() != null)
			animeToBeUpdated.setGenre(anime.getGenre());
		if(anime.getCreationYear() != null)
			animeToBeUpdated.setCreationYear(anime.getCreationYear());
		if(anime.getNumSeasons() != null)
			animeToBeUpdated.setNumSeasons(anime.getNumSeasons());
		if(anime.getOnGoing() != null)
			animeToBeUpdated.setIsOnGoing(anime.getOnGoing());

		Anime updatedAnime = this.animeRepository.save(animeToBeUpdated);
		return Optional.of(updatedAnime);
	}

	public void deleteAnime(Long id) {
		this.animeRepository.deleteById(id);

	}
}