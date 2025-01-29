package com.anime.tracker.entities;

import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Episode {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	private int episodeNumber;
	private String description;
	private LocalDate releaseDate;
	@ManyToOne
	private Season season;

	public Episode() {}

	public Episode(Long id, String title, int episodeNumber, String description, LocalDate releaseDate, Season season) {
		super();
		this.id = id;
		this.title = title;
		this.episodeNumber = episodeNumber;
		this.description = description;
		this.releaseDate = releaseDate;
		this.season = season;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getEpisodeNumber() {
		return episodeNumber;
	}

	public void setEpisodeNumber(int episodeNumber) {
		this.episodeNumber = episodeNumber;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
	}

	public Season getSeason() {
		return season;
	}

	public void setSeason(Season season) {
		this.season = season;
	}

	@Override
	public int hashCode() {
		return Objects.hash(description, episodeNumber, id, releaseDate, season, title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Episode other = (Episode) obj;
		return Objects.equals(description, other.description) && episodeNumber == other.episodeNumber
				&& Objects.equals(id, other.id) && Objects.equals(releaseDate, other.releaseDate)
				&& Objects.equals(season, other.season) && Objects.equals(title, other.title);
	}

	@Override
	public String toString() {
		return "Episode [id=" + id + ", title=" + title + ", episodeNumber=" + episodeNumber + ", description="
				+ description + ", releaseDate=" + releaseDate + ", season=" + season + "]";
	}
}