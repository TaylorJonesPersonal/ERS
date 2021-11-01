package com.ers.services;

import java.sql.SQLException;
import java.util.ArrayList;

import com.aspose.email.MailAddress;
import com.aspose.email.MailMessage;
import com.aspose.email.SmtpClient;
import com.ers.dao.userDao;
import com.ers.exceptions.IncorrectCredentialsException;
import com.ers.exceptions.InviteCodeNotValidatedException;
import com.ers.exceptions.SignUpFailedException;
import com.ers.exceptions.UserDoesNotExistException;
import com.ers.exceptions.WrongInviteCodeException;
import com.ers.logging.Logging;
import com.ers.models.user;

public class UserService {

	private userDao uDao;

	public UserService(userDao uDao) {
		this.uDao = uDao;
	}

	public user signUp(String firstname, String lastname, String email, String username, String password, int roleId)
			throws SignUpFailedException {
		user u = new user(firstname, lastname, email, username, password, roleId);

		try {
			uDao.createUser(u);
		} catch (SQLException e) {
			Logging.logger.warn("User is either already signed up or has provided invalid data for signup.");
			e.printStackTrace();
			throw new SignUpFailedException();
		}
		System.out.println(u);
		return u;
	}

	public user login(String username, String password) throws UserDoesNotExistException, IncorrectCredentialsException, InviteCodeNotValidatedException {
		try {
			user newUser = uDao.getUser(username, password);
			
			if(newUser.getId() == 0) {
				Logging.logger.warn("Username entered does not exist");
				throw new UserDoesNotExistException();
			} else if(!password.equals(newUser.getPassword())) {
				Logging.logger.warn("Incorrect Credentials provided.");
				throw new IncorrectCredentialsException();
			} else if(newUser.getPending() == "y") {
				Logging.logger.warn("User has not validated retrieval code");
				throw new InviteCodeNotValidatedException();
			}
			else {
				return newUser;
			}
			} catch(Exception e) {
				e.printStackTrace();
			}
		return null;
	}
	
	public int sendInvite(String email) {
		//Create a new instance of MailMessage class
		int randomCode = (int) Math.floor(Math.random()*(100000-10000+1) + 10000);
		MailMessage message = new MailMessage();
		
		//Set subject of the message
		message.setSubject("Your invitation code from the Reimbursement System");
		
		//Set HTML body
		message.setHtmlBody("<h3>Your unique ID is: </h3><br>");
		message.setHtmlBody("<p><b>" + randomCode + "</b></p>");
		
		// Specify From address
		message.setFrom(new MailAddress("jonesftwingp@outlook.com"));
		
		// Add TO recipients
		message.getTo().addMailAddress(new MailAddress(email));
		
		//Create an instance of SmtpClient Class and specify your mailing host server, username, password, port
		SmtpClient client = new SmtpClient();
		client.setHost("smtp-mail.outlook.com");
		client.setUsername("jonesftwingp@outlook.com");
		client.setPassword("Unhackable9651");
		client.setPort(587);
		
		try {
			//Client.Send will send this message
			client.send(message);
			System.out.println("Message Sent!");
		} catch(Exception e)
		{
			e.printStackTrace();
		}
		System.out.println(randomCode);
		return randomCode;
	}
	
	
	public void update(String username, String fieldname, String change) {
		System.out.println(username);
		uDao.updateUser(username, fieldname, change);
	}
	
	public void postInvite(String username, int inviteCode) {
		user u = new user(username, inviteCode);
		try {
		uDao.postInviteCode(username, inviteCode);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public user checkCode(String username, int inviteCode) throws WrongInviteCodeException {
		user u = new user(username, inviteCode);
		try {
			if(uDao.checkInviteCode(username, inviteCode) == false) {
				Logging.logger.warn("User input incorrect invite code.");
				throw new WrongInviteCodeException();
			} else {
				return u;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<user> getAllEmp() {
		ArrayList<user> uList = new ArrayList<user>();
		try {
			uList = uDao.getAllUsers();
			return uList;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	

}
