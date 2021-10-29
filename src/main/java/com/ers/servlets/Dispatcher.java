package com.ers.servlets;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ers.controllers.InviteCodeController;
import com.ers.controllers.SignupController;
import com.ers.controllers.UpdateController;
import com.ers.controllers.reimbursements.ApprovedController;
import com.ers.controllers.reimbursements.DeniedController;
import com.ers.controllers.reimbursements.NewReimbursementController;
import com.ers.controllers.reimbursements.PendingController;
import com.ers.controllers.reimbursements.mPendingController;
import com.ers.controllers.sessions.CheckSession;
import com.ers.controllers.sessions.EndSession;
import com.ers.controllers.sessions.LargerSessionInfoController;
import com.ers.controllers.sessions.LoginController;
import com.ers.controllers.sessions.SessionInfoController;
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
			System.out.println("routed to largersessioninfocontroller");
			LargerSessionInfoController.largerSession(req, res);
			break;
			
		case "/ERS/api/update":
			System.out.println("routed to updatecontroller");
			UpdateController.update(req, res);
			break;
			
		case "/ERS/api/newreimbursement":
			System.out.println("routed to newreimbursementcontroller");
			NewReimbursementController.newReimbursement(req, res);
			break;
			
		case "/ERS/api/checksession":
			System.out.println("routed to checksessioncontroller");
			CheckSession.sessionCheck(req, res);
			break;
			
		case "/ERS/api/getpending":
			System.out.println("routed to getpendingcontroller");
			PendingController.pending(req, res);
			break;
			
		case "/ERS/api/getmpending":
			System.out.println("routed to managerpendingcontroller");
			mPendingController.mPending(req, res);
			break;
			
		case "/ERS/api/getapproved":
			System.out.println("routed to getapprovedcontroller");
			ApprovedController.approved(req, res);
			break;
			
		case "/ERS/api/getdenied":
			System.out.println("routed to getdeniedcontroller");
			DeniedController.denied(req, res);
			break;
			
		case "/ERS/api/logout":
			System.out.println("routed to logoutcontroller");
			EndSession.logout(req, res);
			break;
		}
	}

}
