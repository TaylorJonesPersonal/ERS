package com.ers.servlets;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ers.controllers.InviteCodeController;
import com.ers.controllers.LargerSessionInfoController;
import com.ers.controllers.LoginController;
import com.ers.controllers.SessionInfoController;
import com.ers.controllers.SignupController;
import com.fasterxml.jackson.core.JsonProcessingException;

public class Dispatcher {

	public static void process(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException {
		System.out.println("In the Servlet Dispatcher with URI: " + req.getRequestURI());
		
		switch(req.getRequestURI()) {
		case "/ERS/api/login":
			System.out.println("routed to logincontroller");
			LoginController.login(req, res);
			break;
		
		case "/ERS/api/signup":
			System.out.println("routed to signupcontroller");
			SignupController.signUp(req, res);
			break;
			
		case "/ERS/api/invitecode":
			System.out.println("routed to invitecodecontroller");
			InviteCodeController.inviteCode(req, res);
			break;
			
		case "/ERS/api/sessioninfo":
			System.out.println("routed to sessioninfocontroller");
			SessionInfoController.sessionInfo(req, res);
			break;
			
		case "/ERS/api/largersessioninfo":
			System.out.println("routed to largersessioninfo");
			LargerSessionInfoController.largerSession(req, res);
			break;
			
		}
	}

}
