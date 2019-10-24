
class Reimbursement {
    constructor(id, type, amount, status, dateCreated, dateLastModified, employeeName, message, passgrade) {
        this.id = id;
        this.type = type;
        this.amount = amount;
        this.status = status;
        this.dateCreated = dateCreated;
        this.dateLastModified = dateLastModified;
        this.employeeName = employeeName;
        this.message = message;
        this.passgrade = passgrade;
    }
}


class Announcement {
    constructor(msg, sender, dateCreated, reimbursementId, status) {
        this.msg = msg;
        this.sender = sender;
        this.dateCreated = dateCreated;
        this.reimbursementId = reimbursementId;
        this.status = status;
    }
}