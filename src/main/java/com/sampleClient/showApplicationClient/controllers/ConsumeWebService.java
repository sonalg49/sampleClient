package com.sampleClient.showApplicationClient.controllers;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sampleClient.showApplicationClient.model.Show;

@RestController
public class ConsumeWebService {

	@Autowired
	RestTemplate restTemplate;
	final String ROOT_URI = "http://localhost:8081/shows";
	
	@RequestMapping(value = "/test/shows", method = RequestMethod.GET)
	public JsonNode getShowList() throws IOException{
		/*HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		//HttpEntity<String> element = new HttpEntity<String>(headers);
		
		
		ResponseEntity<Show[]> response =
				  restTemplate.getForEntity(
				  ROOT_URI,
				  Show[].class);
		Show[] shows = response.getBody();
		System.out.println(shows);

				
		//return restTemplate.exchange(ROOT_URI, HttpMethod.GET, element, String.class).getBody(); */
		
		String jsonString =  restTemplate.getForObject(ROOT_URI, String.class);
		ObjectMapper mapper = new ObjectMapper();

		return mapper.readTree(jsonString);
		
		
	}
	
	@RequestMapping(value ="test/shows/{id}", method = RequestMethod.GET)
	public JsonNode getShowById(@PathVariable("id") String id) throws IOException{
		String jsonString =  restTemplate.getForObject(ROOT_URI + "/{d}", String.class,id);
		ObjectMapper mapper = new ObjectMapper();

		return mapper.readTree(jsonString);
		
	}
	
	
	
	@RequestMapping(value = "/test/shows", method = RequestMethod.POST)
	public JsonNode addShow(@RequestBody Show show) throws IOException {
	      HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity<Show> element = new HttpEntity<Show>(show,headers);
	      
	      String jsonString = restTemplate.exchange(
	         ROOT_URI, HttpMethod.POST, element, String.class).getBody();
	      ObjectMapper mapper = new ObjectMapper();
	      return mapper.readTree(jsonString);
	 }
	 
	@RequestMapping(value = "test/shows/all", method = RequestMethod.POST)
	public JsonNode addAllShows(@RequestBody List<Show> shows) throws IOException
	{
		  HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity<List<Show>> element = new HttpEntity<List<Show>>(shows,headers);
	      
	      String jsonString = restTemplate.exchange(
	         ROOT_URI + "/all", HttpMethod.POST, element, String.class).getBody();
	      ObjectMapper mapper = new ObjectMapper();
	      return mapper.readTree(jsonString);
	}
	
	@RequestMapping(value = "test/shows", method = RequestMethod.PUT)
	public ResponseEntity<Show> updateShow(@RequestBody Show show)
	{
		  HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity<Show> element = new HttpEntity<Show>(show,headers);
	      
	      return restTemplate.exchange(
	         ROOT_URI, HttpMethod.POST, element, Show.class);
	}
	
	@RequestMapping(value = "test/shows/{id}", method = RequestMethod.DELETE)
	public String deleteShow(@PathVariable("id") String id)
	{
		  /*HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      //HttpEntity<Show> element = new HttpEntity<Show>(headers);
	      
	      return restTemplate.exchange(
	         ROOT_URI + "{/id}", HttpMethod.DELETE, null, Void.class, id); */
		
		return restTemplate.exchange(ROOT_URI + "/{id}",
                HttpMethod.DELETE,
                null,
                String.class,
                id).getBody();
	      
	     
	}
	
	@RequestMapping(value = "test/shows/title/{title}", method = RequestMethod.GET)
	public JsonNode findShowByTitle(@PathVariable("title") String title) throws IOException
	{
		String jsonString =  restTemplate.getForObject(ROOT_URI +"/title/{title}", String.class, title);
		ObjectMapper mapper = new ObjectMapper();

		return mapper.readTree(jsonString);
	}
	
}
