package com.anime.tracker.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.anime.tracker.entities.Anime;
import com.anime.tracker.exceptions.AnimeNotFoundException;
import com.anime.tracker.repositories.AnimeRepository;

@Service
public class AnimeService
{
	private AnimeRepository animeRepository;

	public AnimeService(AnimeRepository animeRepository)
	{
		this.animeRepository = animeRepository;
	}

	public List<Anime> getAnimes()
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
			throw new AnimeNotFoundException(id);
		Anime animeToBeUpdated = optionalItem.get();
		if(anime.getTitle() != null)
			animeToBeUpdated.setTitle(anime.getTitle());
		if(anime.getAuthor() != null)
			animeToBeUpdated.setAuthor(anime.getAuthor());
		if(anime.getGenre() != null)
			animeToBeUpdated.setGenre(anime.getGenre());
		if(anime.getReleaseYear() != null)
			animeToBeUpdated.setReleaseYear(null);
		if(anime.getNumSeasons() != 0)
			animeToBeUpdated.setNumSeasons(anime.getNumSeasons());
		if(anime.getIs_ongoing() != null)
			animeToBeUpdated.setIs_ongoing(anime.getIs_ongoing());

		Anime updatedAnime = this.animeRepository.save(animeToBeUpdated);
		return Optional.of(updatedAnime);
	}

	public void deleteAnime(Long id) {
		Optional<Anime> optionalItem = this.animeRepository.findById(id);
		if(optionalItem.isEmpty())
			throw new AnimeNotFoundException(id);

		this.animeRepository.deleteById(id);

	}
}