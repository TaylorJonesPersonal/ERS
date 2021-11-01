package com.ers.dao;

import java.util.ArrayList;

import com.ers.models.reimbursement;

public interface reimbursementDao {
	public void createReimbursement(reimbursement r);
	public ArrayList<reimbursement> getReimbursement(int id, String status);
	public ArrayList<reimbursement> getAllReimbursement(String status);
	public ArrayList<reimbursement> getSpecificReimbursement(String username, String status);
	public void approveReim(String dateTime);
	public void denyReim(String dateTime);
}
