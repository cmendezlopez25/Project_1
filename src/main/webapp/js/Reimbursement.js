

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
r = new Reimbursement();

function displayReimbursements(reimbList) {
    let list = document.getElementById("reimb-list");
    let tableRow = document.createElement("tr");
    tableRow.setAttribute("class", "reimb-row");
    list.innerHTML = "";
    for (r of reimbList) {
        n += 1;
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
        tableRow.appendChild(numCol);
        tableRow.appendChild(typeCol);
        tableRow.appendChild(statusCol);
        tableRow.appendChild(amtCol);
        tableRow.appendChild(createCol);
        tableRow.appendChild(modifyCol);
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

function getReimbursementById(event) {
    let id = event.target.firstChild.innerHTML;
    console.log(id);
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
                document.getElementById("search-result").innerHTML = (xhr.responseText);
            } else {
                document.getElementById("search-result").innerHTML = "Failed to retireve furniture :(";
            }
        } else {
            document.getElementById("search-result").innerHTML = "Fetching Request";
        }
    }
    xhr.open("GET", "reimbursement/"+id, true);
    xhr.send();
}

(function ($){
    $('.reimb-row').click(function () {
        getReimbursementById();
    });
})