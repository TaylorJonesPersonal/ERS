package com.ers.controllers.reimbursements;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ers.dao.reimbursementDao;
import com.ers.dao.reimbursementDaoDB;
import com.ers.models.reimbursement;
import com.ers.services.ReimbursementService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class mGetSpecificPendingController {

	private static reimbursementDao rDao = new reimbursementDaoDB();
	private static ReimbursementService rServ = new ReimbursementService(rDao);
	
	public static void getSpecificPending(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException{
		
		ArrayList<reimbursement> rList = new ArrayList<reimbursement>();
		

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
		try {
			rList = rServ.getSpecificReimb(username, "PENDING");
			res.setStatus(200);
			System.out.println(new ObjectMapper().writeValueAsString(rList));
			res.getWriter().write(new ObjectMapper().writeValueAsString(rList));
		}catch(Exception e) {
			res.setStatus(403);
			e.printStackTrace();
		}
	}
}
