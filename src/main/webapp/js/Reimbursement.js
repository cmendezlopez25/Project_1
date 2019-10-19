

class Reimbursement {
    constructor(id, type, amount, status, dateCreated, dateLastModified) {
        this.id = id;
        this.type = type;
        this.amount = amount;
        this.status = status;
        this.dateCreated = dateCreated;
        this.dateLastModified = dateLastModified;
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

let reimbList = [
    new Reimbursement(1, "UNIVERSITY_COURSES", 100, "PEND_DS", "07/12/2019", "07/13/2019"),
    new Reimbursement(2, "SEMINARS", 50, "PEND_BENCO", "08/03/2019", "08/09/2019"), 
    new Reimbursement(3, "SEMINARS", 80, "PEND_BENCO", "09/03/2019", "09/09/2019"), 
];

let annList = [
    new Announcement("first read msg", "you supervisor", "07/13/2019", 2, "READ"),
    new Announcement("first unread msg", "some body", "07/14/2019", 3, "UNREAD"),
    new Announcement("second unread msg", "other supervisor", "07/16/2019", 5, "READ"),
];

$(document).ready(function() {
    // getAllReimbursement();
    displayReimbursements(reimbList);     // just for test, uncomment the line above when got servlet
    getAllAnnouncement(annList);
    $('.reimb-row').click(function () {
        console.log("called");
        getReimbursementById($(this));
    });
});


function displayReimbursements(reimbList) {
    let list = document.getElementById("reimb-list");
    
    list.innerHTML = "";
    for (r of reimbList) {
        let tableRow = document.createElement("tr");
        tableRow.setAttribute("class", "reimb-row");
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
        modifyCol.innerHTML = r.dateLastModified;
        tableRow.appendChild(idCol);
        tableRow.appendChild(typeCol);
        tableRow.appendChild(statusCol);
        tableRow.appendChild(amtCol);
        tableRow.appendChild(createCol);
        tableRow.appendChild(modifyCol);
        list.appendChild(tableRow);
    }
}

function getAllReimbursement() {
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
                displayReimbursements(JSON.parse(xhr.responseText));
            } else {
                console.log("status != 200")
            }
        } else {
            console.log("Fetching reimbursements")
        }
    }
    xhr.open("GET", "home", true);
    xhr.send();
}

function getReimbursementById(node) {
    let id = node.children().first().html();
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
                $("#search-result").html(xhr.responseText);
            } else {
                $("#search-result").html("Failed to retrieve furniture :(");
            }
        } else {
            $("#search-result").html("Fetching Request");
        }
    }
    xhr.open("GET", "reimbursement/"+id, true);
    xhr.send();
}

// fetch all READ notification to display in Announcement
function getAllAnnouncement(announceList) {
    let list = document.getElementById("ann-list");

    list.innerHTML = "";

    for(a of announceList) {
        // check if the notification is reader not
        if(a.status === "READ") {
            let item = document.createElement("li");
            item.setAttribute("class", "list-group-item announcement");
            item.innerHTML = a.msg;
            list.appendChild(item);
        }
    }
}