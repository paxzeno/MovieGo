package com.mars_crater.movie.services.synology;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mars_crater.movie.services.connection.Connect;
import com.mars_crater.movie.services.synology.entities.SynoApiInfo;
import com.mars_crater.movie.services.synology.entities.SynologyMessage;
import com.mars_crater.movie.services.synology.utils.EndpointBuilder;

public class SynologyApi {

	private final static String SYNOLOGY_ENDPOINT = "http://192.168.1.174:5000/"/*
																				 * TODO
																				 * "http://paxzeno.quickconnect.to/"
																				 */;

	public static SynologyMessage getSynoApiInfo() {
		try {
			final String apiInfoAllRequest = SYNOLOGY_ENDPOINT + "webapi/query.cgi?api=SYNO.API.Info&version=1&method=query&query=all";
			ObjectMapper mapper = new ObjectMapper();
			return mapper.readValue(Connect.to(apiInfoAllRequest), SynologyMessage.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public static SynologyMessage request(final String apiName, final String path, final int version, final String method, Map<String, String[]> params) {
		try {
			final EndpointBuilder endpointBuilder = new EndpointBuilder(apiName, path, version, method, params);
			final ObjectMapper mapper = new ObjectMapper();
			final String url = endpointBuilder.url(SYNOLOGY_ENDPOINT);
			System.out.println(url);
			return mapper.readValue(Connect.to(url), SynologyMessage.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static SynologyMessage createTask(final String apiName, final String path, final int version, final String method, Map<String, String[]> params) {
		// /webapi/DownloadStation/task.cgi?api=SYNO.DownloadStation.Task&version=3&method=create&uri=https://yts.ag/torrent/download/365534B4A05D523DDC03FA3603832648B4FA3745
		try {
			final EndpointBuilder endpointBuilder = new EndpointBuilder(apiName, path, version, method, params);
			final ObjectMapper mapper = new ObjectMapper();
			final String url = endpointBuilder.url(SYNOLOGY_ENDPOINT);
			System.out.println(url);
			return mapper.readValue(Connect.to(url), SynologyMessage.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static SynologyMessage logout(final String apiName, final String path, final int version, final String method, Map<String, String[]> params) {
		try {
			final EndpointBuilder endpointBuilder = new EndpointBuilder(apiName, path, version, method, params);
			final ObjectMapper mapper = new ObjectMapper();
			final String url = endpointBuilder.url(SYNOLOGY_ENDPOINT);
			System.out.println(url);
			return mapper.readValue(Connect.to(url), SynologyMessage.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private static String getStringFromInputStream(InputStream is) {

		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();

		String line;
		try {

			br = new BufferedReader(new InputStreamReader(is));
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return sb.toString();

	}

	public static boolean download(final String url) throws Exception {
		final SynologyMessage synoApiINFO = SynologyApi.getSynoApiInfo();
		
		String apiAuth = "SYNO.API.Auth";
		ObjectMapper mapper = new ObjectMapper();
		SynoApiInfo apiAuthInfo = mapper.convertValue(synoApiINFO.getData().get(apiAuth), SynoApiInfo.class);
		Map<String, String[]> params = new HashMap<>();
		params.put("account", new String[] { "paxzeno" });
		params.put("passwd", new String[] { "mozarT8074" });
		params.put("session", new String[] { "DownloadStation" });
		params.put("format", new String[] { "cookie" });
		// /webapi/auth.cgi?api=SYNO.API.Auth&version=2&method=login&account=admin&passwd=12345&session=DownloadStation&format=cookie
		SynologyMessage request = SynologyApi.request(apiAuth, apiAuthInfo.getPath(), apiAuthInfo.getMaxVersion(), "login", params);
		if (request.getError() != null) {
			throw new Exception(request.getError().get("code").toString());
		}
		
		String apiDownloadTask = "SYNO.DownloadStation.Task";
		SynoApiInfo apiDownloadTaskInfo = mapper.convertValue(synoApiINFO.getData().get(apiDownloadTask), SynoApiInfo.class);
		params.clear();
		params.put("uri", new String[] { url });
		params.put("_sid", new String[] { (String) request.getData().get("sid") });
		// /webapi/DownloadStation/task.cgi?api=SYNO.DownloadStation.Task&version=3&method=create&uri=https://yts.ag/torrent/download/365534B4A05D523DDC03FA3603832648B4FA3745
		request = SynologyApi.createTask(apiDownloadTask, apiDownloadTaskInfo.getPath(), apiDownloadTaskInfo.getMaxVersion(), "create", params);
		if (request.getError() != null) {
			throw new Exception(request.getError().get("code").toString());
		}
		
		params.clear();
		params.put("session", new String[] { "DownloadStation" });
		// /webapi/auth.cgi?api=SYNO.API.Auth&version=1&method=logout&session=DownloadStation
		request = SynologyApi.logout(apiAuth, apiAuthInfo.getPath(), apiAuthInfo.getMaxVersion(), "logout", params);
		if (request.getError() != null) {
			throw new Exception(request.getError().get("code").toString());
		}
		return Boolean.TRUE;
	}
}
