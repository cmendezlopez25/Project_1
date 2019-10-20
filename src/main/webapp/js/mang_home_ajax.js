
let appReimbList = [
    new Reimbursement(1, "UNIVERSITY_COURSES", 100, "PEND_DS", "07/12/2019", "07/13/2019", "empolyee one"),
    new Reimbursement(2, "SEMINARS", 50, "PEND_BENCO", "08/03/2019", "08/09/2019", "veryveryvery loneanme"), 
    new Reimbursement(3, "SEMINARS", 80, "PEND_BENCO", "09/03/2019", "09/09/2019", "employee two"), 
];

$(document).ready(function() {
    getAllApprovalReimbursement()
    displayApprovalReimbursement(appReimbList);
});

function getAllApprovalReimbursement(){

}

function displayApprovalReimbursement(appReimbList) {
    let list = document.getElementById("employ-reimb-list");

    list.innerHTML = "";
    console.log('displaying approval reimbursement table');
    for(r of appReimbList) {
        let tableRow = document.createElement("tr");
        tableRow.setAttribute("class", "approval-reimb-row");
        let idCol = document.createElement("th");
        idCol.innerHTML = r.id;
        let typeCol = document.createElement('td');
        typeCol.innerHTML = r.type;
        let statusCol = document.createElement('td');
        statusCol.innerHTML = r.status;
        let amtCol = document.createElement('td');
        amtCol.innerHTML = r.amount;
        let createCol = document.createElement('td');
        createCol.innerHTML = r.dateCreated;
        let modifyCol = document.createElement('td');
        modifyCol.innerHTML = r.employeeName;
        tableRow.appendChild(idCol);
        tableRow.appendChild(typeCol);
        tableRow.appendChild(statusCol);
        tableRow.appendChild(amtCol);
        tableRow.appendChild(createCol);
        tableRow.appendChild(modifyCol);
        list.appendChild(tableRow);
    }
}