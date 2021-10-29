package com.ers.dao;

import java.util.ArrayList;

import com.ers.models.reimbursement;

public interface reimbursementDao {
	public void createReimbursement(reimbursement r);
	public ArrayList<reimbursement> getReimbursement(int id, String status);
	public ArrayList<reimbursement> getAllReimbursement(String status);
}
