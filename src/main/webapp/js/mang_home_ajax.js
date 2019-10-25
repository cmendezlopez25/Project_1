$(document).ready(function() {
    getAllApprovalReimbursement()
});

function getAllApprovalReimbursement(){
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
                reimbList = JSON.parse(xhr.responseText)
                displayApprovalReimbursement(reimbList[1]);
            } else {
                console.log("status != 200");
            }
        } else {
            console.log("Fetching reimbursements");
        }
    }
    xhr.open("GET", "reimbursement", true);
    xhr.send();
}

function displayApprovalReimbursement(appReimbList) {
    let list = document.getElementById("employ-reimb-list");
    console.log(appReimbList);
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
        let dateCreated = r.dateCreated.monthValue + "/" + r.dateCreated.dayOfMonth + "/" + r.dateCreated.year;
        createCol.innerHTML = dateCreated;
        let modifyCol = document.createElement('td');
        modifyCol.innerHTML = r.ownerUserName;
        tableRow.appendChild(idCol);
        tableRow.appendChild(typeCol);
        tableRow.appendChild(statusCol);
        tableRow.appendChild(amtCol);
        tableRow.appendChild(createCol);
        tableRow.appendChild(modifyCol);
        list.appendChild(tableRow);
    }
}