package com.project.ecommerce.service;

import java.io.*;



import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class Read_Json_File_Service {
	
	private static final ObjectMapper objectMapper = new ObjectMapper();
	
	public Read_Json_File_Service(){}
	
	
	public static JsonNode getItemFromJson() throws IOException{
	
		JsonNode json;
	
		try (InputStream inputStream = new FileInputStream(new File("src/main/resources/dummy.json"))){	
			json = objectMapper.readValue(inputStream, JsonNode.class);
		}catch(IOException e) {
			throw new RuntimeException("failed to read JSON data");
		}
		
		return json;
	}
}