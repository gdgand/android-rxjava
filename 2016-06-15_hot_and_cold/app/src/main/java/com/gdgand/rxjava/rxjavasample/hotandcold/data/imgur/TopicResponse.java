package com.gdgand.rxjava.rxjavasample.hotandcold.data.imgur;

import java.util.List;

public class TopicResponse {

	private List<Topic> data;

	public void setData(List<Topic> data) {
		this.data = data;
	}

	public List<Topic> getData() {
		return data;
	}
}
