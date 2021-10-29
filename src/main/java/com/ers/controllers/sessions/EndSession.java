package com.ers.controllers.sessions;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.core.JsonProcessingException;

public class EndSession {

	public static void logout(HttpServletRequest req, HttpServletResponse res)
			throws JsonProcessingException, IOException {
		
		try {
			
			HttpSession session = req.getSession();
			session.invalidate();
			res.setStatus(200);
			res.getWriter().write("Logout Successful");
			
		}catch(Exception e) {
			res.setStatus(403);
			e.printStackTrace();
		}
}
}
