package com.anime.tracker.entities;

import java.time.LocalDate;
import java.util.Objects;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Review {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String content;
	private int rating; // 1 - 10
	@ManyToOne
	private User user;
	@ManyToOne
	@JoinColumn(name = "anime_id")
	private Anime anime;
	@CreationTimestamp
	private LocalDate reviewDate;

	public Review() {}

	public Review(Long id, String content, int rating, User user, Anime anime, LocalDate reviewDate) {
		super();
		this.id = id;
		this.content = content;
		this.rating = rating;
		this.user = user;
		this.anime = anime;
		this.reviewDate = reviewDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Anime getAnime() {
		return anime;
	}

	public void setAnime(Anime anime) {
		this.anime = anime;
	}

	public LocalDate getReviewDate() {
		return reviewDate;
	}

	public void setReviewDate(LocalDate reviewDate) {
		this.reviewDate = reviewDate;
	}

	@Override
	public int hashCode() {
		return Objects.hash(anime, content, id, rating, reviewDate, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Review other = (Review) obj;
		return Objects.equals(anime, other.anime) && Objects.equals(content, other.content)
				&& Objects.equals(id, other.id) && rating == other.rating
				&& Objects.equals(reviewDate, other.reviewDate) && Objects.equals(user, other.user);
	}

	@Override
	public String toString() {
		return "Review [id=" + id + ", content=" + content + ", rating=" + rating + ", user=" + user + ", anime="
				+ anime + ", reviewDate=" + reviewDate + "]";
	}
}