package com.ers.models;

import java.sql.Timestamp;

public class reimbursement {
	
	private int id;
	private double amount;
	
	
	//submit timestamp deconstructed
	private String submittedDate;
	
	//resolved timestamp deconstructed
	private String resolvedDate;
	
	private String description;
	private int author;
	private String resolver;
	private int statusId;
	private String status;
	private int typeId;
	private String authorFirstName;
	private String authorLastName;
	

	public reimbursement(double amount, int typeId, String submittedDate, String description, int author) {
		this.amount = amount;
		this.typeId = typeId;
		this.submittedDate = submittedDate;
		this.description = description;
		this.author = author;
		this.statusId = 1;
	}
	
	public reimbursement(String submittedDate, double amount, String description, String status) {
		this.submittedDate = submittedDate;
		this.amount = amount;
		this.description = description;
		this.status = status;
	}
	
	public reimbursement(String authorFirstName, String authorLastName, String submittedDate, double amount, String description, String status) {
		this.authorFirstName = authorFirstName;
		this.authorLastName = authorLastName;
		this.submittedDate = submittedDate;
		this.amount = amount;
		this.description = description;
		this.status = status;
	}
	
	
	public reimbursement() {
		
	}
	


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public double getAmount() {
		return amount;
	}


	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public int getAuthor() {
		return author;
	}


	public void setAuthor(int author) {
		this.author = author;
	}


	public String getResolver() {
		return resolver;
	}


	public void setResolver(String resolver) {
		this.resolver = resolver;
	}


	public int getStatusId() {
		return statusId;
	}


	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}



	public int getTypeId() {
		return typeId;
	}


	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}


	public String getSubmittedDate() {
		return submittedDate;
	}


	public void setSubmittedDate(String submittedDate) {
		this.submittedDate = submittedDate;
	}


	public String getResolvedDate() {
		return resolvedDate;
	}


	public void setResolvedDate(String resolvedDate) {
		this.resolvedDate = resolvedDate;
	}


	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	

	@Override
	public String toString() {
		return "reimbursement [id=" + id + ", amount=" + amount + ", submittedDate=" + submittedDate + ", resolvedDate="
				+ resolvedDate + ", description=" + description + ", author=" + author + ", resolver=" + resolver
				+ ", statusId=" + statusId + ", status=" + status + ", typeId=" + typeId + ", authorFirstName="
				+ authorFirstName + ", authorLastName=" + authorLastName + "]";
	}

	public String getAuthorFirstName() {
		return authorFirstName;
	}

	public void setAuthorFirstName(String authorFirstName) {
		this.authorFirstName = authorFirstName;
	}

	public String getAuthorLastName() {
		return authorLastName;
	}

	public void setAuthorLastName(String authorLastName) {
		this.authorLastName = authorLastName;
	}
	
	

}
