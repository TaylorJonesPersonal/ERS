package com.ers.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

import com.ers.models.reimbursement;
import com.ers.utils.ConnectingUtil;

public class reimbursementDaoDB implements reimbursementDao {
	ConnectingUtil conUtil = ConnectingUtil.getConnectionUtil();

	// Crud operations: CREATE

	public void createReimbursement(reimbursement r) {
		Connection con = conUtil.getConnection();

		try {

			Timestamp timestamp = Timestamp.valueOf(r.getSubmittedDate());

			String sql = "INSERT into ers_reimbursement(reimb_amount,reimb_submitted,reimb_description,reimb_author,reimb_status_id,reimb_type_id) values"
					+ "(?,?,?,?,?,?);";

			PreparedStatement ps = con.prepareStatement(sql);

			ps.setDouble(1, r.getAmount());
			ps.setTimestamp(2, timestamp);
			ps.setString(3, r.getDescription());
			ps.setInt(4, r.getAuthor());
			ps.setInt(5, r.getStatusId());
			ps.setInt(6, r.getTypeId());
			ps.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<reimbursement> getReimbursement(int id, String status) {
		Connection con = conUtil.getConnection();

		NumberFormat formatter = new DecimalFormat("#0.00");

		reimbursement r = new reimbursement();
		ArrayList<reimbursement> rList = new ArrayList<reimbursement>();
		int arrayCounter = 0;

		try {

			String sql = "SELECT reimb_submitted, reimb_amount, reimb_description, reimb_status from ers_reimbursement inner join ers_reimbursement_status on ers_reimbursement.reimb_status_id = ers_reimbursement_status.reimb_status_id where reimb_author = '"
					+ id + "' and ers_reimbursement_status.reimb_status = '" + status + "';";

			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql);

			while (rs.next()) {

				String submittedDate = (rs.getTimestamp(1).toString());
				double amount = rs.getDouble(2);
				String description = rs.getString(3);

				rList.add(new reimbursement(submittedDate, amount, description, status));
			}

			for (int i = 0; i < rList.size(); i++) {
				System.out.println(rList.get(i));
			}

			return rList;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public ArrayList<reimbursement> getSpecificReimbursement(String username, String status) {
		Connection con = conUtil.getConnection();

		NumberFormat formatter = new DecimalFormat("#0.00");

		reimbursement r = new reimbursement();
		ArrayList<reimbursement> rList = new ArrayList<reimbursement>();
		int arrayCounter = 0;

		try {

			String sql = "select user_first_name, user_last_name, reimb_submitted, reimb_amount, reimb_description, reimb_status, user_username from ers_users inner join ers_reimbursement on ers_users.user_id = ers_reimbursement.reimb_author inner join ers_reimbursement_status on ers_reimbursement.reimb_status_id = ers_reimbursement_status.reimb_status_id where user_username = '" + username + "' and reimb_status = '" + status + "';";

			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql);

			while (rs.next()) {

				String firstName = rs.getString(1);
				String lastName = rs.getString(2);
				String submittedDate = (rs.getTimestamp(3).toString());
				double amount = rs.getDouble(4);
				String description = rs.getString(5);

				rList.add(new reimbursement(firstName, lastName, submittedDate, amount, description, status));
			}

			for (int i = 0; i < rList.size(); i++) {
				System.out.println(rList.get(i));
			}

			return rList;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public ArrayList<reimbursement> getAllReimbursement(String status) {
		Connection con = conUtil.getConnection();

		ArrayList<reimbursement> rList = new ArrayList<reimbursement>();
		int arrayCounter = 0;

		try {

			String sql = "select user_first_name, user_last_name, reimb_submitted, reimb_amount, reimb_description, reimb_status from ers_users inner join ers_reimbursement on ers_users.user_id = ers_reimbursement.reimb_author inner join ers_reimbursement_status on ers_reimbursement.reimb_status_id = ers_reimbursement_status.reimb_status_id where ers_reimbursement_status.reimb_status = '"
					+ status + "';";

			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql);

			while (rs.next()) {

				String firstName = rs.getString(1);
				String lastName = rs.getString(2);
				String submittedDate = (rs.getTimestamp(3).toString());
				double amount = rs.getDouble(4);
				String description = rs.getString(5);

				rList.add(new reimbursement(firstName, lastName, submittedDate, amount, description, status));
			}

			for (int i = 0; i < rList.size(); i++) {
				System.out.println(rList.get(i));
			}

			return rList;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public void approveReim(String dateTime) {
		Connection con = conUtil.getConnection();

		try {

			Timestamp timestamp = Timestamp.valueOf(dateTime);

			String sql = "update ers_reimbursement set reimb_status_id = '2' where reimb_submitted = '" + timestamp
					+ "';";

			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void denyReim(String dateTime) {
		Connection con = conUtil.getConnection();

		try {

			Timestamp timestamp = Timestamp.valueOf(dateTime);

			String sql = "update ers_reimbursement set reimb_status_id = '3' where reimb_submitted = '" + timestamp
					+ "';";

			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
