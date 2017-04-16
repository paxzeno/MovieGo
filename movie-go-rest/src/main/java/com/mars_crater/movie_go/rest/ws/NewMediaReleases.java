package com.mars_crater.movie_go.rest.ws;

import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.mars_crater.movie.services.releases.consumer.FeedReader;
import com.mars_crater.movie.services.releases.consumer.GetTorrent;

@Path("/NewMediaReleases")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class NewMediaReleases {

	@GET
	public Map<String, Object> getUsers() {
		return FeedReader.read();
	}

	@PUT
	@Path("/update/{id}")
	public void update(@PathParam(value = "id") String mediaId, Map<String, Object> media) {
		Boolean download = (Boolean) media.get("check");
		
		if (download != null && download) {
			GetTorrent.getTorrentUrl(mediaId);
		}
		System.out.println(media);
	}

}
