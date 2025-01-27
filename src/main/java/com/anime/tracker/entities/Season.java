package com.anime.tracker.entities;

import java.util.List;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Season {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	@ManyToOne
	private Anime anime;
	@OneToMany(mappedBy = "season")
	private List<Episode> episodes;

	public Season() {}

	public Season(Long id, String title, Anime anime, List<Episode> episodes) {
		super();
		this.id = id;
		this.title = title;
		this.anime = anime;
		this.episodes = episodes;
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

	public Anime getAnime() {
		return anime;
	}

	public void setAnime(Anime anime) {
		this.anime = anime;
	}

	public List<Episode> getEpisodes() {
		return episodes;
	}

	public void setEpisodes(List<Episode> episodes) {
		this.episodes = episodes;
	}

	@Override
	public int hashCode() {
		return Objects.hash(anime, id, title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Season other = (Season) obj;
		return Objects.equals(anime, other.anime) && Objects.equals(id, other.id) && Objects.equals(title, other.title);
	}

	@Override
	public String toString() {
		return "Season [id=" + id + ", title=" + title + ", anime=" + anime + "]";
	}
}