package com.anime.tracker.entities;

import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class Item
{

	private String name;

	public String getName()
	{
		return this.name;
	}


	public void setName(String name)
	{
		this.name = name;
	}
}