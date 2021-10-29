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

}
