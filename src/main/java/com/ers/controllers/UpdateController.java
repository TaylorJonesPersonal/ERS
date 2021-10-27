package com.ers.controllers;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ers.dao.userDao;
import com.ers.dao.userDaoDB;
import com.ers.exceptions.UserDoesNotExistException;
import com.ers.services.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UpdateController {
	
	private static userDao uDao = new userDaoDB();
	private static UserService uServ = new UserService(uDao);
	
	public static void update(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException{
		
		System.out.println("In the update controller.");
		// To read in stringified JSON data from a POST request is a little more
		// complicated than reading form data
		StringBuilder buffer = new StringBuilder();

		// The buffered reader will read the json data line by line
		BufferedReader reader = req.getReader();

		String line;
		while ((line = reader.readLine()) != null) {
			buffer.append(line);
			buffer.append(System.lineSeparator());
		}

		String data = buffer.toString();

		ObjectMapper mapper = new ObjectMapper();
		JsonNode parsedObj = mapper.readTree(data);
		
		String username = parsedObj.get("username").asText();
		String nameOfField = parsedObj.get("nameOfField").asText();
		String change = parsedObj.get("change").asText();
		

		
		
		try {
			uServ.update(username, nameOfField, change);
			HttpSession session = req.getSession();
			session.setAttribute(nameOfField, change);
			System.out.println(session.getAttribute(nameOfField));
			res.setStatus(200);
			res.getWriter().write(change);
		}catch(Exception e) {
			res.setStatus(403);
			e.printStackTrace();
		}
		
	}
}
