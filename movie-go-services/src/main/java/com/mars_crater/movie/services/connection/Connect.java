package com.mars_crater.movie.services.connection;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public final class Connect {
	
	private static final String USER_AGENT = "Mozilla/5.0";

	public static InputStream to(final String endpoint) throws IOException {
		final URL url = new URL(endpoint);
		final HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");

		connection.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = connection.getResponseCode();
		if (responseCode != 200) {
			throw new RuntimeException("Not Found");
		}
		
		return connection.getInputStream();
	}
	
}
