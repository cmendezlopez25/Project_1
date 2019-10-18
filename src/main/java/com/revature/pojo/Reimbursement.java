package com.revature.pojo;

import java.time.temporal.Temporal;

public class Reimbursement {
	enum ReimbursementType {
		UNIVERSITY_COURSES, SEMINARS, CERTIFICATION_PREPARATION_CLASSES, CERTIFICATION, TECHNICAL_TRAINING, OTHER
	};

	enum ReimbursementStatus {
		PEND_DS, PEND_DH, PEND_BENCO, REQUESTED_INFO, PEND_PROOF, REJECTED, AWARDED, EXCEEDED
	}

	private int id;
	private ReimbursementType type;
	private double amount;
	private ReimbursementStatus status;
	// private String[] files;
	private Temporal dateCreated;
	private Temporal dateLastModified;

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
		this.amount = amount;
	}

	public ReimbursementStatus getStatus() {
		return status;
	}

	public void setStatus(ReimbursementStatus status) {
		this.status = status;
	}

	public Temporal getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Temporal dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Temporal getDateLastModified() {
		return dateLastModified;
	}

	public void setDateLastModified(Temporal dateLastModified) {
		this.dateLastModified = dateLastModified;
	}

	public Reimbursement() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Reimbursement(int id, ReimbursementType type, double amount, ReimbursementStatus status,
			Temporal dateCreated, Temporal dateLastModified) {
		super();
		this.id = id;
		this.type = type;
		this.amount = amount;
		this.status = status;
		this.dateCreated = dateCreated;
		this.dateLastModified = dateLastModified;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((dateCreated == null) ? 0 : dateCreated.hashCode());
		result = prime * result + ((dateLastModified == null) ? 0 : dateLastModified.hashCode());
		result = prime * result + id;
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
		if (dateLastModified == null) {
			if (other.dateLastModified != null)
				return false;
		} else if (!dateLastModified.equals(other.dateLastModified))
			return false;
		if (id != other.id)
			return false;
		if (status != other.status)
			return false;
		if (type != other.type)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Reimbursement [id=" + id + ", type=" + type + ", amount=" + amount + ", status=" + status
				+ ", dateCreated=" + dateCreated + ", dateLastModified=" + dateLastModified + "]";
	}
}