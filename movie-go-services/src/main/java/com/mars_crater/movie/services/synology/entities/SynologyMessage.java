package com.mars_crater.movie.services.synology.entities;

import java.util.Map;

public class SynologyMessage {

	private Map<String, Object> data;

	private boolean success;

	private Map<String, Integer> error;

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(final Map<String, Object> data) {
		this.data = data;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public Map<String, Integer> getError() {
		return error;
	}

	public void setError(Map<String, Integer> error) {
		this.error = error;
	}

}
