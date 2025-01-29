package com.anime.tracker.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private Gender gender;
	@Column(nullable = false, unique = true)
	private String username;
	@Column(nullable = false)
	private String password;
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "users_roles",
			joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
	private Set<Role> roles;
	private int totalWatchedEpisodes;
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<WatchList> watchList = new ArrayList<>();

	public User() {}

	public User(String name, Gender gender, String username, String password, Set<Role> roles, int totalWatchedEpisodes) {
		super();
		this.name = name;
		this.gender = gender;
		this.totalWatchedEpisodes = totalWatchedEpisodes;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public Set<Role> getRoles() {
		return this.roles;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Gender getGender() {
		return gender;
	}

	public String getUsername() {
		return this.username;
	}

	public String getPassword() {
		return this.password;
	}

	public int getTotalWatchedEpisodes() {
		return this.totalWatchedEpisodes;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setRoles(Set<Role> roles)
	{
		this.roles = roles;
	}

	public void setTotalWatchedEpisodes(int totalWatchedEpisodes) {
		this.totalWatchedEpisodes = totalWatchedEpisodes;
	}

	@Override
	public int hashCode() {
		return Objects.hash(gender, id, name, roles);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return gender == other.gender && Objects.equals(id, other.id) && Objects.equals(name, other.name)
				&& Objects.equals(roles, other.roles) && Objects.equals(totalWatchedEpisodes, other.totalWatchedEpisodes);
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", gender=" + gender + ", role=" + roles + ", totalWatchedEpisodes=" + totalWatchedEpisodes + "]";
	}
}