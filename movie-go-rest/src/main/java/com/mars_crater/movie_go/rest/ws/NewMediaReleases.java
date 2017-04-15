package com.mars_crater.movie_go.rest.ws;

import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.mars_crater.movie.releases.consumer.FeedReader;

@Path("/NewMediaReleases")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class NewMediaReleases {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object> getUsers() {
		return FeedReader.read();
	}
}
