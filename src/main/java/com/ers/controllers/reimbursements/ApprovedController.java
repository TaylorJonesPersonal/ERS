package com.ers.controllers.reimbursements;

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
import com.fasterxml.jackson.databind.ObjectMapper;

public class ApprovedController {

	private static reimbursementDao rDao = new reimbursementDaoDB();
	private static ReimbursementService rServ = new ReimbursementService(rDao);
	
	public static void approved(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException{
		
		ArrayList<reimbursement> rList = new ArrayList<reimbursement>();
		
		HttpSession session = req.getSession();
		int id = (int) session.getAttribute("id");
		try {
			rList = rServ.getReimb(id, "APPROVED");
			res.setStatus(200);
			res.getWriter().write(new ObjectMapper().writeValueAsString(rList));
			System.out.println(new ObjectMapper().writeValueAsString(rList));
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}

