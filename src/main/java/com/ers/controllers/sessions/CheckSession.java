package com.ers.controllers.sessions;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.core.JsonProcessingException;

public class CheckSession {

	public static void sessionCheck(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException{
		HttpSession session = req.getSession();
		
		try {
		if(session.getAttribute("id") == null) {
			res.setStatus(200);
			res.getWriter().write("0");
		} else {
			res.setStatus(200);
			res.getWriter().write("1");
		}
		}catch(Exception e) {
			e.printStackTrace();
			res.setStatus(403);
		}
	}
}
