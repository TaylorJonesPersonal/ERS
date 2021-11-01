package com.ers.services;

import java.util.ArrayList;

import com.ers.dao.reimbursementDao;
import com.ers.models.reimbursement;

public class ReimbursementService {
	
	private reimbursementDao rDao;

	public ReimbursementService(reimbursementDao rDao) {
		this.rDao = rDao;
	}
	
	public void createReimb(double amount, int typeId, String submittedDate, String description, int author) {
		reimbursement r = new reimbursement(amount, typeId, submittedDate, description, author);
		rDao.createReimbursement(r);
	}
	
	public ArrayList<reimbursement> getReimb(int id, String status) {
		ArrayList<reimbursement> rList = new ArrayList<reimbursement>();
		try {
			rList = rDao.getReimbursement(id, status);
			return rList;
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public ArrayList<reimbursement> getAllReimb(String status) {
		ArrayList<reimbursement> rList = new ArrayList<reimbursement>();
		try {
			System.out.println("in the getAllReimb service");
			rList = rDao.getAllReimbursement(status);
			return rList;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	public ArrayList<reimbursement> getSpecificReimb(String username, String status){
		ArrayList<reimbursement> rList = new ArrayList<reimbursement>();
		try {
			System.out.println("in the getSpecificReimb service");
			rList = rDao.getSpecificReimbursement(username, status);
			return rList;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void approveReimbursement(String dateTime) {
		try {
			System.out.println("In the approveReimbursement service");
			rDao.approveReim(dateTime);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void denyReimbursement(String dateTime) {
		try {
			System.out.println("In the denyReimbursement service");
			rDao.denyReim(dateTime);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
