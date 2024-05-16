package com.project.ecommerce.controllers;

import java.io.IOException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.project.ecommerce.service.Read_Json_File_Service;

@RestController
public class Product_Controller {

	@GetMapping(value = "/get_products")
	 public JsonNode getProducts() {
		
		Read_Json_File_Service read_Json_File_Service = new Read_Json_File_Service();
		JsonNode json = null;
		
		try {
			json = read_Json_File_Service.getItemFromJson();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return json;
	}
}
