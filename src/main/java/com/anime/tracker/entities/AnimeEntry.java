package com.anime.tracker.entities;

import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class AnimeEntry {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	private Anime anime;
	@ManyToOne
	private WatchList watchList;
	private int watchedEpisodes;
	private LocalDate startedWatching;
	private LocalDate finishedWatching;
	private String status; // Watching, Complete

	public AnimeEntry() {}

	public AnimeEntry(Long id, Anime anime, WatchList watchList, int watchedEpisodes, LocalDate startedWatching,
			LocalDate finishedWatching, String status) {
		super();
		this.id = id;
		this.anime = anime;
		this.watchList = watchList;
		this.watchedEpisodes = watchedEpisodes;
		this.startedWatching = startedWatching;
		this.finishedWatching = finishedWatching;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Anime getAnime() {
		return anime;
	}

	public void setAnime(Anime anime) {
		this.anime = anime;
	}

	public WatchList getWatchList() {
		return watchList;
	}

	public void setWatchList(WatchList watchList) {
		this.watchList = watchList;
	}

	public int getWatchedEpisodes() {
		return watchedEpisodes;
	}

	public void setWatchedEpisodes(int watchedEpisodes) {
		this.watchedEpisodes = watchedEpisodes;
	}

	public LocalDate getStartedWatching() {
		return startedWatching;
	}

	public void setStartedWatching(LocalDate startedWatching) {
		this.startedWatching = startedWatching;
	}

	public LocalDate getFinishedWatching() {
		return finishedWatching;
	}

	public void setFinishedWatching(LocalDate finishedWatching) {
		this.finishedWatching = finishedWatching;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		return Objects.hash(anime, finishedWatching, id, startedWatching, status, watchList, watchedEpisodes);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AnimeEntry other = (AnimeEntry) obj;
		return Objects.equals(anime, other.anime) && Objects.equals(finishedWatching, other.finishedWatching)
				&& Objects.equals(id, other.id) && Objects.equals(startedWatching, other.startedWatching)
				&& Objects.equals(status, other.status) && Objects.equals(watchList, other.watchList)
				&& watchedEpisodes == other.watchedEpisodes;
	}

	@Override
	public String toString() {
		return "AnimeEntry [id=" + id + ", anime=" + anime + ", watchList=" + watchList + ", watchedEpisodes="
				+ watchedEpisodes + ", startedWatching=" + startedWatching + ", finishedWatching=" + finishedWatching
				+ ", status=" + status + "]";
	}
}