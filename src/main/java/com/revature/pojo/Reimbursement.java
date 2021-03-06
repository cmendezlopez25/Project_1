package com.revature.pojo;

import java.time.LocalDate;

public class Reimbursement {
	public enum ReimbursementType {
		UNIVERSITY_COURSES, SEMINARS, CERTIFICATION_PREPARATION_CLASSES, CERTIFICATION, TECHNICAL_TRAINING, OTHER
	};

	public enum ReimbursementStatus {
		PEND_DS, PEND_DH, PEND_BENCO, REQUESTED_INFO, PEND_PROOF, REJECTED, AWARDED, EXCEEDED
	}

	private int id;
	private String ownerUserName;
	private ReimbursementType type;
	private double amount;
	private ReimbursementStatus status;
	private LocalDate dateCreated;
	private LocalDate dateLastModified;
	private String message;
	private int passGrade;
	private LocalDate dateEvent;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
		double roundedAmount = amount * 100;
		roundedAmount = Math.round(roundedAmount);
		this.amount = roundedAmount / 100.0;
	}

	public ReimbursementStatus getStatus() {
		return status;
	}

	public void setStatus(ReimbursementStatus status) {
		this.status = status;
	}

	public LocalDate getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(LocalDate dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getOwnerUserName() {
		return ownerUserName;
	}

	public void setOwnerUserName(String ownerUserName) {
		this.ownerUserName = ownerUserName;
	}

	public LocalDate getDateLastModified() {
		return dateLastModified;
	}

	public void setDateLastModified(LocalDate dateLastModified) {
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

	public LocalDate getDateEvent() {
		return dateEvent;
	}

	public void setDateEvent(LocalDate dateEvent) {
		this.dateEvent = dateEvent;
	}

	public Reimbursement() {
		super();
	}

	public Reimbursement(int id, String ownerUserName, ReimbursementType type, double amount,
			ReimbursementStatus status, LocalDate dateCreated, LocalDate dateLastModified, String message,
			int passGrade, LocalDate dateEvent) {
		super();
		this.id = id;
		this.ownerUserName = ownerUserName;
		this.type = type;
		setAmount(amount);
		this.status = status;
		this.dateCreated = dateCreated;
		this.dateLastModified = dateLastModified;
		this.message = message;
		this.passGrade = passGrade;
		this.dateEvent = dateEvent;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((dateCreated == null) ? 0 : dateCreated.hashCode());
		result = prime * result + ((dateEvent == null) ? 0 : dateEvent.hashCode());
		result = prime * result + ((dateLastModified == null) ? 0 : dateLastModified.hashCode());
		result = prime * result + id;
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		result = prime * result + ((ownerUserName == null) ? 0 : ownerUserName.hashCode());
		result = prime * result + passGrade;
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reimbursement other = (Reimbursement) obj;
		if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
			return false;
		if (dateCreated == null) {
			if (other.dateCreated != null)
				return false;
		} else if (!dateCreated.equals(other.dateCreated))
			return false;
		if (dateEvent == null) {
			if (other.dateEvent != null)
				return false;
		} else if (!dateEvent.equals(other.dateEvent))
			return false;
		if (dateLastModified == null) {
			if (other.dateLastModified != null)
				return false;
		} else if (!dateLastModified.equals(other.dateLastModified))
			return false;
		if (id != other.id)
			return false;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		if (ownerUserName == null) {
			if (other.ownerUserName != null)
				return false;
		} else if (!ownerUserName.equals(other.ownerUserName))
			return false;
		if (passGrade != other.passGrade)
			return false;
		if (status != other.status)
			return false;
		if (type != other.type)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Reimbursement [id=" + id + ", ownerUserName=" + ownerUserName + ", type=" + type + ", amount=" + amount
				+ ", status=" + status + ", dateCreated=" + dateCreated + ", dateLastModified=" + dateLastModified
				+ ", message=" + message + ", passGrade=" + passGrade + ", dateEvent=" + dateEvent + "]";
	}
}
