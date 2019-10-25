
class Reimbursement {
    constructor(id, type, amount, status, dateCreated, dateLastModified, ownerUserName, message, passGrade, dateEvent) {
        this.id = id;
        this.type = type;
        this.amount = amount;
        this.status = status;
        this.dateCreated = dateCreated;
        this.dateLastModified = dateLastModified;
        this.ownerUserName = ownerUserName;
        this.message = message;
        this.passGrade = passGrade;
        this.dateEvent = dateEvent;
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