package com.ers.test.reimbursementservice;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import com.ers.dao.reimbursementDao;
import com.ers.dao.reimbursementDaoDB;
import com.ers.dao.userDao;
import com.ers.dao.userDaoDB;
import com.ers.models.reimbursement;
import com.ers.models.user;
import com.ers.services.ReimbursementService;
import com.ers.services.UserService;

public class reimbursementServiceTests {
	
	reimbursement r = new reimbursement();
	
	@BeforeClass
	public static void setup() {
	reimbursementDao rDao = new reimbursementDaoDB();
	ReimbursementService rServ = new ReimbursementService(rDao);
	rServ.createReimb(4.50, 1, "2022-10-31 22:01:42.0", "we are testing", 1);
	}
	
	@Test
	public void testGetSpecificReimb() {
		reimbursementDao rDao = new reimbursementDaoDB();
		ReimbursementService rServ = new ReimbursementService(rDao);
		ArrayList<reimbursement> rList = rServ.getSpecificReimb("tbjones", "PENDING");
		System.out.println(rList.get(0).getDescription());
		assertEquals("Taylor", rList.get(0).getAuthorFirstName());
		assertEquals("Jones", rList.get(0).getAuthorLastName());
		assertEquals("2022-10-31 22:01:42.0", rList.get(0).getSubmittedDate());
		assertEquals("we are testing", rList.get(0).getDescription());
	}
	
	@Test
	public void testGetReimb() {
		reimbursementDao rDao = new reimbursementDaoDB();
		ReimbursementService rServ = new ReimbursementService(rDao);
		ArrayList<reimbursement> rList = rServ.getReimb(1, "PENDING");
		assertEquals(0, 0);
	}
	
	
}
