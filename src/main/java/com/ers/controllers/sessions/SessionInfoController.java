package com.ers.controllers.sessions;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ers.models.user;
import com.fasterxml.jackson.core.JsonProcessingException;

public class SessionInfoController{

public static void sessionInfo(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException {
	HttpSession session = req.getSession();

	
	String firstname = (String)(session.getAttribute("firstname"));
	
	res.setContentType("text/html");
	res.getWriter().write(firstname);
}

}
