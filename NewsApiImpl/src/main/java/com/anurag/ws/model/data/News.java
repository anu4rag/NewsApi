package com.anurag.ws.model.data;

public class News {

	private String description;
	private int source_count;
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getSource_count() {
		return source_count;
	}
	public void setSource_count(int source_count) {
		this.source_count = source_count;
	}
	
	@Override
	public String toString() {
		return "News [description=" + description + ", source_count=" + source_count + "]";
	}
}
