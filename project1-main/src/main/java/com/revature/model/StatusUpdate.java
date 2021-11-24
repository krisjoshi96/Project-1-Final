package com.revature.model;

import java.util.List;

public class StatusUpdate {
	private String status;
	private List<Integer> ids;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Integer> getIds() {
		return ids;
	}

	public void setIds(List<Integer> ids) {
		this.ids = ids;
	}

}
