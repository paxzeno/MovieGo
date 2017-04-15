package com.mars_crater.movie.releases.consumer.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ImdbMovie {
	
	@JsonProperty("Title")
	private String title;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
