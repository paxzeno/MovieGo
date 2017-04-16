package com.mars_crater.movie.services.synology.utils;

import java.util.Arrays;
import java.util.Map;

/**
 * /webapi/<CGI_PATH>?api=<API_NAME>&version=<VERSION>&method=<METHOD>[&<PARAMS>][&_sid=<SID>]
 * 
 * @author paxzeno
 *
 */
public class EndpointBuilder {

	private String cgiPath;

	private String apiName;

	private int version;

	private String method;

	private Map<String, String[]> params;

	public EndpointBuilder(String apiName, String path, int version, String method, Map<String, String[]> params) {
		this.cgiPath = path;
		this.apiName = apiName;
		this.version = version;
		this.method = method;
		this.params = params;
	}

	public String url(final String synologyEndpoint) {
		return synologyEndpoint + this.toString();
	}

	@Override
	public String toString() {
		return "webapi/" + this.cgiPath + "?api=" + this.apiName + "&version=" + this.version + "&method=" + this.method + this.getParams();
	}

	private String getParams() {
		if (this.params == null || this.params.isEmpty()) {
			return "";
		}

		final StringBuilder stringBuilder = new StringBuilder();
		this.params.forEach((k, v) -> {
			stringBuilder.append("&");
			stringBuilder.append(k);
			stringBuilder.append("=");
			Arrays.asList(v).forEach(param -> {
				stringBuilder.append(param);
				stringBuilder.append(",");
			});
			stringBuilder.deleteCharAt(stringBuilder.length() - 1);
		});

		return stringBuilder.toString();
	}

}
