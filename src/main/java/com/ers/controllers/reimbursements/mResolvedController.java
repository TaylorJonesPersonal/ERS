package com.ers.controllers.reimbursements;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ers.dao.reimbursementDao;
import com.ers.dao.reimbursementDaoDB;
import com.ers.models.reimbursement;
import com.ers.services.ReimbursementService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class mResolvedController {

	private static reimbursementDao rDao = new reimbursementDaoDB();
	private static ReimbursementService rServ = new ReimbursementService(rDao);
	
	public static void mApproved(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException{
		
		ArrayList<reimbursement> rList = new ArrayList<reimbursement>();
		
		try {
			rList = rServ.getAllReimb("APPROVED");
			res.setStatus(200);
			res.getWriter().write(new ObjectMapper().writeValueAsString(rList));
			System.out.println(new ObjectMapper().writeValueAsString(rList));
		}catch(Exception e) {
			res.setStatus(403);
			e.printStackTrace();
		}
	}
	
	public static void mDenied(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException{
		
		ArrayList<reimbursement> rList = new ArrayList<reimbursement>();
		
		try {
			rList = rServ.getAllReimb("DENIED");
			res.setStatus(200);
			res.getWriter().write(new ObjectMapper().writeValueAsString(rList));
			System.out.println(new ObjectMapper().writeValueAsString(rList));
		}catch(Exception e) {
			res.setStatus(403);
			e.printStackTrace();
		}
	}

}
