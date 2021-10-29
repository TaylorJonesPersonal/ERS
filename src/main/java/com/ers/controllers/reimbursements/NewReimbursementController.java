package com.ers.controllers.reimbursements;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ers.dao.reimbursementDao;
import com.ers.dao.reimbursementDaoDB;
import com.ers.dao.userDao;
import com.ers.dao.userDaoDB;
import com.ers.services.ReimbursementService;
import com.ers.services.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class NewReimbursementController {

	private static reimbursementDao rDao = new reimbursementDaoDB();
	private static ReimbursementService rServ = new ReimbursementService(rDao);
	
	public static void newReimbursement(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException {
		
		System.out.println("In the NewReimbursement Controller");
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
		
		double amount = parsedObj.get("amount").asDouble();
		String type = parsedObj.get("type").asText();
		int typeId=0;
		switch(type) {
		case "Lodging":
			typeId = 1;
			break;
		case "Travel":
			typeId = 2;
			break;
		case "Food":
			typeId = 3;
			break;
		case "Other":
			typeId = 4;
			break;
		}
		String submittedDate = parsedObj.get("time").asText();
		String description = parsedObj.get("description").asText();
		HttpSession session = req.getSession();
		int author = (int) session.getAttribute("id");
		
		try {
			rServ.createReimb(amount, typeId, submittedDate, description, author);
			res.setStatus(200);
			
		} catch(Exception e) {
			e.printStackTrace();
			res.setStatus(403);
		}
		
	}

}
