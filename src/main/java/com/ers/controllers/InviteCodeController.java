package com.ers.controllers;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ers.dao.userDao;
import com.ers.dao.userDaoDB;
import com.ers.exceptions.WrongInviteCodeException;
import com.ers.models.user;
import com.ers.services.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class InviteCodeController {

	private static userDao uDao = new userDaoDB();
	private static UserService uServ = new UserService(uDao);

	public static void inviteCode(HttpServletRequest req, HttpServletResponse res)
			throws JsonProcessingException, IOException {
		
		System.out.println("In the InviteCodeController");
		
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
				System.out.println(data);

				ObjectMapper mapper = new ObjectMapper();
				JsonNode parsedObj = mapper.readTree(data);
				
				String username = parsedObj.get("username").asText();
				int retrievalCode = parsedObj.get("code").asInt();
				
				
				try {
					user u = uServ.checkCode(username, retrievalCode);
					System.out.println(u);
					res.setStatus(200);
					if(u == null) {
						res.getWriter().write(1);
					} else {
					res.getWriter().write(new ObjectMapper().writeValueAsString(u));
					}
				} catch(WrongInviteCodeException e) {
					res.setStatus(403);
				}

	}
	}
