package com.ers.controllers.sessions;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ers.models.user;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class LargerSessionInfoController {

public static void largerSession(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException {
	HttpSession session = req.getSession();

	
	user u = new user();
	u.setFirstname((String)session.getAttribute("firstname"));
	u.setLastname((String)session.getAttribute("lastname"));
	u.setEmail((String)session.getAttribute("email"));
	u.setUsername((String)session.getAttribute("username"));
	u.setPassword((String)session.getAttribute("password"));
	u.setRoleID((int)session.getAttribute("role"));
	System.out.println(u.getRoleID());
	
	try {
		res.setStatus(200);
		res.getWriter().write(new ObjectMapper().writeValueAsString(u));
	} catch (Exception e) {
		res.setStatus(403);
	}

}

}
