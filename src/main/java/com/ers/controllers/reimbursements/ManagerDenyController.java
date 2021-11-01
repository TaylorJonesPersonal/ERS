package com.ers.controllers.reimbursements;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ers.dao.reimbursementDao;
import com.ers.dao.reimbursementDaoDB;
import com.ers.services.ReimbursementService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ManagerDenyController {

	private static reimbursementDao rDao = new reimbursementDaoDB();
	private static ReimbursementService rServ = new ReimbursementService(rDao);
	
	public static void denied(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException{
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

		String dateTime = parsedObj.get("uniqueDate").asText();
		
		try {
			System.out.println("before the denyReimbursementService");
			rServ.denyReimbursement(dateTime);
			System.out.println("after the denyReimbursementService");
			res.setStatus(200);
			res.getWriter().write("Successfully Updated Request");
		}catch(Exception e) {
			res.setStatus(403);
			e.printStackTrace();
		}
	}
}
