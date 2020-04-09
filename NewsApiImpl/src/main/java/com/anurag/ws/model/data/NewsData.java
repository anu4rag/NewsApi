package com.anurag.ws.model.data;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NewsData {
	private Object status;
	private double totalresults;
	private Object[] articles;
	
	public Object getStatus() {
		return status;
	}
	public void setStatus(Object status) {
		this.status = status;
	}
	public double getTotalresults() {
		return totalresults;
	}
	public void setTotalresults(double totalresults) {
		this.totalresults = totalresults;
	}
	public Object[] getArticles() {
		return articles;
	}
	public Object getArticlesByIndex(int index) {
		return articles[index];
	}
	public void setArticles(Object[] articles) {
		this.articles = articles;
	}
	@Override
	public String toString() {
		return "NewsData [status=" + status + ", totalresults=" + totalresults + ", articles="
				+ Arrays.toString(articles) + "]";
	}
	
}
