package com.mars_crater.movie.services.synology.entities;

public class SynoApiInfo {

	private int minVersion;

	private int maxVersion;

	private String path;

	private String requestFormat;

	public int getMinVersion() {
		return minVersion;
	}

	public void setMinVersion(int minVersion) {
		this.minVersion = minVersion;
	}

	public int getMaxVersion() {
		return maxVersion;
	}

	public void setMaxVersion(int maxVersion) {
		this.maxVersion = maxVersion;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getRequestFormat() {
		return requestFormat;
	}

	public void setRequestFormat(String requestFormat) {
		this.requestFormat = requestFormat;
	}

}
