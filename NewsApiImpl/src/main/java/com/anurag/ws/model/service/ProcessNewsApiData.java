package com.anurag.ws.model.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.anurag.ws.model.data.NewsData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ProcessNewsApiData {

	@Bean
	public RestTemplate getBean() {
		return new RestTemplate();
	}

	@Autowired
	RestTemplate restTemplate;

	public HashMap<String, Integer> getNewsDataAllArticle(String description, int count) throws JsonMappingException, JsonProcessingException {
		String newsUrl = "http://newsapi.org/v2/everything?q="+ description +"&sortBy=publishedAt&apiKey=9bceadafe5fa48b5bd00293477ccca59";
		String str = restTemplate.exchange(newsUrl,HttpMethod.GET,null,String.class).getBody();
		System.out.println(str);
		
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		NewsData news = objectMapper.readValue(str, NewsData.class);
		
		// get all article
		Object[] articles = (Object[]) news.getArticles();
		System.out.println("number of articles = " + articles.length);
		ArrayList<String> list = new ArrayList<String>(); 
		
		// get source name
		for(Object ob : articles) {
			LinkedHashMap<?, ?> article = (LinkedHashMap<?, ?>) ob;
			HashMap<?,?> map = (HashMap<?,?>) article.get("source");
	        list.add(map.get("name").toString()); 
		}
		
		// get frequency of source names
		HashMap<String, Integer> map = countFrequencies(list);
		
		// reverse hashMap and get desired results
		LinkedHashMap<String, Integer> reverseSortedMap = new LinkedHashMap<>();
		 
		//Use Comparator.reverseOrder() for reverse ordering
		map.entrySet()
		    .stream()
		    .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).limit(count)
		    .forEachOrdered(x -> reverseSortedMap.put(x.getKey(), x.getValue()));
		
		return reverseSortedMap;
	}
	
	private HashMap<String, Integer> countFrequencies(ArrayList<String> list) 
    { 
        Set<String> st = new HashSet<String>(list); 
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        for (String s : st) { 
            map.put(s, Collections.frequency(list, s));
        }
        System.out.println("map: " + map);
        return map;
    } 

}
