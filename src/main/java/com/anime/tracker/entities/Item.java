package com.anime.tracker.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class Item
{

	@Id
	@GeneratedValue
	private Long id;
	private String name;

	public Long getId()
	{
		return this.id;
	}

	public String getName()
	{
		return this.name;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public void setName(String name)
	{
		this.name = name;
	}
}