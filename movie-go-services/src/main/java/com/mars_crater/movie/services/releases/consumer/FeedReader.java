package com.mars_crater.movie.services.releases.consumer;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FeedReader {

	private static final String USER_AGENT = "Mozilla/5.0";

	private static String getCharacterDataFromElement(Element e) {
		Node child = e.getFirstChild();
		if (child instanceof CharacterData) {
			CharacterData cd = (CharacterData) child;
			return cd.getData();
		}
		return "";
	}

	private static List<String> readFeed() {
		try {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();

			final URL url = new URL("http://rss.imdb.com/list/ls016522954/");
			final HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");

			connection.setRequestProperty("User-Agent", USER_AGENT);

			int responseCode = connection.getResponseCode();
			System.out.println("\nSending 'GET' request to URL : " + url);
			System.out.println("Response Code : " + responseCode);

			Document doc = builder.parse(connection.getInputStream());

			final List<String> imdbLinks = new ArrayList<>();
			final Pattern pattern = Pattern.compile("(?<=\\/title\\/)\\w+");
			Matcher matcher = null;
			NodeList nodes = doc.getElementsByTagName("item");
			for (int i = 0; i < nodes.getLength(); i++) {
				Element element = (Element) nodes.item(i);
				NodeList title = element.getElementsByTagName("guid");
				Element line = (Element) title.item(0);
				matcher = pattern.matcher(getCharacterDataFromElement(line));
				matcher.find();
				System.out.println("guid: " + matcher.group(0));
				imdbLinks.add(matcher.group(0));
			}

			return imdbLinks;

		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	private static Map<String, Object> fetchInfo(final List<String> readFeed) {

		Map<String, Object> newReleases = new HashMap<>();
		
		try {
			for (String movieId : readFeed) {

				URL url = new URL("http://www.omdbapi.com/?i=" + movieId);
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
				map = mapper.readValue(connection.getInputStream(), new TypeReference<Map<String, Object>>(){});
				
				map.forEach( (k,v) -> {
					System.out.print("[" + k + "," + v + "] -- ");
				});
				System.out.println("\n");
				newReleases.put(movieId, map);
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return newReleases;
	}

	public static Map<String, Object> read() {
		// TODO Auto-generated method stub
		List<String> readFeed = FeedReader.readFeed();
		return FeedReader.fetchInfo(readFeed);
	}

}
