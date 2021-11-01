package com.ers.controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ers.dao.reimbursementDao;
import com.ers.dao.reimbursementDaoDB;
import com.ers.dao.userDao;
import com.ers.dao.userDaoDB;
import com.ers.models.user;
import com.ers.services.ReimbursementService;
import com.ers.services.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class mGetEmployeesController {

	private static userDao uDao = new userDaoDB();
	private static UserService uServ = new UserService(uDao);
	
	public static void mPending(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException{
		
		ArrayList<user> uList = new ArrayList<user>();
		
		try {
			uList = uServ.getAllEmp();
			res.setStatus(200);
			res.getWriter().write(new ObjectMapper().writeValueAsString(uList));
			System.out.println(new ObjectMapper().writeValueAsString(uList));
		} catch(Exception e) {
			res.setStatus(403);
			e.printStackTrace();
		}
}
}
