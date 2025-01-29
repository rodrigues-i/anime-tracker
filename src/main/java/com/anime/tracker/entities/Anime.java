package com.anime.tracker.entities;

import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "animes")
public class Anime
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	private String author;
	private String genre;
	private String studio;
	private int episodes;
	private int numSeasons;
	private LocalDate releaseYear;
	private String synopsis;
	private Boolean is_ongoing;

	public Anime() {}

	public Anime(Long id, String title, String author, String genre, String studio, int episodes, int numSeasons,
			LocalDate releaseYear, String synopsis, Boolean is_ongoing) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.genre = genre;
		this.studio = studio;
		this.episodes = episodes;
		this.numSeasons = numSeasons;
		this.releaseYear = releaseYear;
		this.synopsis = synopsis;
		this.is_ongoing = is_ongoing;
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

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getStudio() {
		return studio;
	}

	public void setStudio(String studio) {
		this.studio = studio;
	}

	public int getEpisodes() {
		return episodes;
	}

	public void setEpisodes(int episodes) {
		this.episodes = episodes;
	}

	public int getNumSeasons() {
		return numSeasons;
	}

	public void setNumSeasons(int numSeasons) {
		this.numSeasons = numSeasons;
	}

	public LocalDate getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(LocalDate releaseYear) {
		this.releaseYear = releaseYear;
	}

	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	public Boolean getIs_ongoing() {
		return is_ongoing;
	}

	public void setIs_ongoing(Boolean is_ongoing) {
		this.is_ongoing = is_ongoing;
	}

	@Override
	public int hashCode() {
		return Objects.hash(author, episodes, genre, id, is_ongoing, numSeasons, releaseYear, studio, synopsis, title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Anime other = (Anime) obj;
		return Objects.equals(author, other.author) && episodes == other.episodes && Objects.equals(genre, other.genre)
				&& Objects.equals(id, other.id) && Objects.equals(is_ongoing, other.is_ongoing)
				&& numSeasons == other.numSeasons && Objects.equals(releaseYear, other.releaseYear)
				&& Objects.equals(studio, other.studio) && Objects.equals(synopsis, other.synopsis)
				&& Objects.equals(title, other.title);
	}

	@Override
	public String toString() {
		return "Anime [id=" + id + ", title=" + title + ", author=" + author + ", genre=" + genre + ", studio=" + studio
				+ ", episodes=" + episodes + ", numSeasons=" + numSeasons + ", releaseYear=" + releaseYear
				+ ", synopsis=" + synopsis + ", is_ongoing=" + is_ongoing + "]";
	}
}