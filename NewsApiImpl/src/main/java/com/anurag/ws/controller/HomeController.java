package com.anurag.ws.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.anurag.ws.model.data.News;
import com.anurag.ws.model.service.ProcessNewsApiData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@Controller
public class HomeController {
	
	@Autowired
	ProcessNewsApiData processNewsApiData;
	
	@RequestMapping(value="/home")
	public String home() {
		return "home.jsp";
	}
	
	@RequestMapping(value="/getNews")
	@ResponseBody
	public HashMap<String, Integer> getNews(@RequestParam String description, @RequestParam int count) {
		News news = new News();
		HashMap<String, Integer> map = null;
		
		news.setDescription(description);
		news.setSource_count(count);
		
		//process data and get source
		try {
			map = processNewsApiData.getNewsDataAllArticle(description, count);
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return map;
	}
}
