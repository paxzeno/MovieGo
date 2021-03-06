package com.mars_crater.movie.services.releases.consumer;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class GetTorrent {

	private static final String USER_AGENT = "Mozilla/5.0";

	public static String getTorrentUrl(final String imdbId) {
		try {
			final URL url = new URL("https://yts.ag/api/v2/list_movies.json?query_term=" + imdbId);
			final HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");

			connection.setRequestProperty("User-Agent", USER_AGENT);

			int responseCode = connection.getResponseCode();
			if (responseCode != 200) {
				throw new RuntimeException("Not Found");
			}

			ObjectMapper mapper = new ObjectMapper();

			Map<String, Object> map = new HashMap<String, Object>();

			// convert JSON string to Map
			map = mapper.readValue(connection.getInputStream(), new TypeReference<Map<String, Object>>() {
			});

			map.forEach((k, v) -> {
				System.out.print("[" + k + "," + v + "] -- ");
			});

			// TODO create objects to represent this.
			final StringBuilder urlBuilder = new StringBuilder();
			List<Map<String, Object>> torrents = (List<Map<String, Object>>) ((Map<String,Object>)((List<Object>)(((Map<String,Object>) map.get("data")).get("movies"))).get(0)).get("torrents");
			torrents.forEach( torrent -> {
					if ("1080p".equals(torrent.get("quality"))) {
						urlBuilder.append((String) torrent.get("url"));
					}
			});
			
			return urlBuilder.toString();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
