package com.revature.util;

import com.revature.pojo.Reimbursement.ReimbursementStatus;
import com.revature.pojo.Reimbursement.ReimbursementType;

public class FakeReimbursement {
	private int id;
	private String ownerUserName;
	private ReimbursementType type;
	private double amount;
	private ReimbursementStatus status;
	private String dateCreated;
	private String dateLastModified;
	private String message;
	private int passGrade;
	private String dateEvent;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOwnerUserName() {
		return ownerUserName;
	}

	public void setOwnerUserName(String ownerUserName) {
		this.ownerUserName = ownerUserName;
	}

	public ReimbursementType getType() {
		return type;
	}

	public void setType(ReimbursementType type) {
		this.type = type;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public ReimbursementStatus getStatus() {
		return status;
	}

	public void setStatus(ReimbursementStatus status) {
		this.status = status;
	}

	public String getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getDateLastModified() {
		return dateLastModified;
	}

	public void setDateLastModified(String dateLastModified) {
		this.dateLastModified = dateLastModified;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getPassGrade() {
		return passGrade;
	}

	public void setPassGrade(int passGrade) {
		this.passGrade = passGrade;
	}

	public String getDateEvent() {
		return dateEvent;
	}

	public void setDateEvent(String dateEvent) {
		this.dateEvent = dateEvent;
	}

	public FakeReimbursement(int id, String ownerUserName, ReimbursementType type, double amount,
			ReimbursementStatus status, String dateCreated, String dateLastModified, String message, int passGrade,
			String dateEvent) {
		super();
		this.id = id;
		this.ownerUserName = ownerUserName;
		this.type = type;
		this.amount = amount;
		this.status = status;
		this.dateCreated = dateCreated;
		this.dateLastModified = dateLastModified;
		this.message = message;
		this.passGrade = passGrade;
		this.dateEvent = dateEvent;
	}

	public FakeReimbursement() {
		super();
		// TODO Auto-generated constructor stub
	}
}
