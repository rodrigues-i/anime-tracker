package com.anime.tracker.entities;

import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.Entity;

@Entity
public class Anime extends Item
{
	private String author;
	private String genre;
	private Integer numSeasons;
	private LocalDate creation_year;
	private boolean is_ongoing;

	public Anime(String author, String genre, Integer numSeasons, LocalDate creation_year, boolean is_ongoing)
	{
		this.author = author;
		this.genre = genre;
		this.numSeasons = numSeasons;
		this.creation_year = creation_year;
		this.is_ongoing = is_ongoing;
	}

	public String getAuthor()
	{
		return this.author;
	}

	public String getGenre()
	{
		return this.genre;
	}

	public Integer getNumSeasons()
	{
		return this.numSeasons;
	}

	public LocalDate getCreationYear()
	{
		return this.creation_year;
	}

	public boolean getOnGoing()
	{
		return this.is_ongoing;
	}

	public void setAuthor(String author)
	{
		this.author = author;
	}

	public void setGenre(String genre)
	{
		this.genre = genre;
	}

	public void setNumSeasons(Integer numSeasons)
	{
		this.numSeasons = numSeasons;
	}

	public void setCreationgYear(LocalDate creation_year)
	{
		this.creation_year = creation_year;
	}

	public void setIsOnGoing(boolean is_ongoing)
	{
		this.is_ongoing = is_ongoing;
	}

	@Override
	public boolean equals(Object o)
	{
		if(this == o)
			return true;
		if(o == null || getClass() != o.getClass())
			return false;
		Anime anime = (Anime) o;
		return Objects.equals(this.author, anime.author)
				&& Objects.equals(this.genre, anime.genre) && Objects.equals(this.numSeasons, anime.numSeasons)
				&& Objects.equals(this.creation_year, anime.creation_year) && Objects.equals(this.is_ongoing, anime.is_ongoing);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(this.author, this.genre, this.numSeasons, this.creation_year, this.is_ongoing);
	}

	@Override
	public String toString()
	{
		return "Anime{" + "id=" + this.getId() + ", name'" + this.getName() + '\'' + ", author='" + this.author + '\''
				+ ", genre='" + this.genre + '\'' + ", numSeasons=" + this.numSeasons + ", creation_date=" + this.creation_year
				+ ", is_ongoing" + this.is_ongoing + '}';
	}
}
