package com.revature.pojo;

import java.time.LocalDate;

public class Notification {
	public enum NotificationStatus {
		NEW, UNREAD, READ
	};

	private String msg;
	private User sender;
	private LocalDate dateCreated;
	private int reimbursementId;
	private NotificationStatus status;
	private User receiver;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public LocalDate getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(LocalDate dateCreated) {
		this.dateCreated = dateCreated;
	}

	public int getReimbursementId() {
		return reimbursementId;
	}

	public void setReimbursementId(int reimbursementId) {
		this.reimbursementId = reimbursementId;
	}

	public NotificationStatus getStatus() {
		return status;
	}

	public void setStatus(NotificationStatus status) {
		this.status = status;
	}
	
	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public User getReceiver() {
		return receiver;
	}

	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}

	public Notification() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Notification(String msg, User sender, LocalDate dateCreated, int reimbursementId, User receiver, NotificationStatus status) {
		super();
		this.msg = msg;
		this.sender = sender;
		this.dateCreated = dateCreated;
		this.reimbursementId = reimbursementId;
		this.status = status;
		this.receiver = receiver;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateCreated == null) ? 0 : dateCreated.hashCode());
		result = prime * result + ((msg == null) ? 0 : msg.hashCode());
		result = prime * result + reimbursementId;
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		Notification other = (Notification) obj;
		if (dateCreated == null) {
			if (other.dateCreated != null)
				return false;
		} else if (!dateCreated.equals(other.dateCreated))
			return false;
		if (msg == null) {
			if (other.msg != null)
				return false;
		} else if (!msg.equals(other.msg))
			return false;
		if (reimbursementId != other.reimbursementId)
			return false;
		if (status != other.status)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Notification [msg=" + msg + ", dateCreated=" + dateCreated + ", reimbursementId=" + reimbursementId
				+ ", status=" + status + "]";
	}
}
