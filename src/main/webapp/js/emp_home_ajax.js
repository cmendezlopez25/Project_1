
let reimbList = [];

let annList = [
    new Announcement("first read msg", "you supervisor", "07/13/2019", 2, "READ"),
    new Announcement("first unread msg", "some body", "07/14/2019", 3, "UNREAD"),
    new Announcement("second unread msg", "other supervisor", "07/16/2019", 5, "READ"),
    new Announcement("first new msg", "other supervisor", "07/16/2019", 5, "NEW"),
];

$(document).ready(function() {
    getAllReimbursement();
    getAllAnnouncement();
    // displayAnnouncement(annList);
    // displayNotification(annList);
    
    $('.reimb-row').click(function () {
        getReimbursementById($(this));
    });
});


function displayReimbursements(reimbList) {
    let list = document.getElementById("reimb-list");
    console.log("displaying reimbursement table")
    
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
        let dateCreated = r.dateCreated.monthValue + "/" + r.dateCreated.dayOfMonth + "/" + r.dateCreated.year;
        createCol.innerHTML = dateCreated;
        let modifyCol = document.createElement('td');
        let dateLastModified = r.dateLastModified.monthValue + "/" + r.dateLastModified.dayOfMonth + "/" + r.dateLastModified.year;
        modifyCol.innerHTML = dateLastModified;
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
                reimbList = JSON.parse(xhr.responseText)
                displayReimbursements(reimbList[0]);
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

function getReimbursementById(node) {
    let id = node.children().first().html();
    console.log(id);
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

function getAllAnnouncement() {
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
                announceList = JSON.parse(xhr.responseText)
                displayAnnouncement(announceList[0]);
                displayNotification(announceList[1]);
            } else {
                console.log("status != 200");
            }
        } else {
            console.log("Fetching reimbursements");
        }
    }
    xhr.open("GET", "notification", true);
    xhr.send();
}


function displayAnnouncement(announceList) {
    let list = document.getElementById("ann-list");

    list.innerHTML = "";

    for(a of announceList) {
        let item = document.createElement("li");
        console.log(a);
        if (a.status == "NEW") continue
        if (a.status == "UNREAD") { 
            console.log("unread");
            item.setAttribute("class", "list-group-item announcement unread");
        } 
        else {
            item.setAttribute("class", "list-group-item announcement");
        }
        item.innerHTML = a.msg;
        list.appendChild(item);
    }
}

function getAllNotification() {
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
                displayAnnouncement(JSON.parse(xhr.responseText));
            } else {
                console.log("status != 200");
            }
        } else {
            console.log("Fetching reimbursements");
        }
    }
    xhr.open("GET", "home", true);
    xhr.send();
}

function displayNotification(notifList) {
    let list = document.getElementById("notif-list");

    list.innerHTML = "";
    console.log('displaying notification');
    for(n of notifList) {
        let item = document.createElement("li");
        item.setAttribute("class", "list-group-item notification unread");
        item.innerHTML = n.msg;
        list.appendChild(item);
    }
}