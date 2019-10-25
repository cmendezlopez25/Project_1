$(document).ready(function() {
    showReimbDetail();
});

function reject() {
    let notif = new Announcement();
    createRejectNotif(notif);
    updateRejectReimb(notif);
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() {
        if (xhr.readyState == 4) {
            if (xhr.status == 200) {
                window.location.replace("home");
            }
        }
    }
    xhr.open("GET", "login", true);
    xhr.send();
}

function createRejectNotif(notif){
    notif.msg = document.getElementById("reviewComment").value;
    notif.reimbursementId = document.getElementById("reimb-id").innerHTML;
    notif.status = "NEW";
    console.log(notif.msg + " " + notif.reimbursementId);
    let xhrNotif = new XMLHttpRequest();

    xhrNotif.onreadystatechange = function(){
        if (xhrNotif.readyState === 4){
            if(xhrNotif.status === 200){
                //TODO: Redirect back to home and create a notification to the assigned supervisor
                console.log("notification created!");
            }
        }
    }
    xhrNotif.open("POST", "notification", true);
    xhrNotif.send(JSON.stringify(notif));
}

function updateRejectReimb(notif) {
    notif.msg = document.getElementById("reviewComment").value;
    notif.reimbursementId = document.getElementById("reimb-id").innerHTML;
    notif.status = "NEW";
    let xhrReimb = new XMLHttpRequest();
    xhrReimb.onreadystatechange = function(){
        if (xhrReimb.readyState === 4){
            if(xhrReimb.status === 200){
                //TODO: Redirect back to home and create a notification to the assigned supervisor
                console.log("notification created!");
            }
        }
    }
    xhrReimb.open("PUT", "reimbursement", true);
    xhrReimb.send("reject"+"\n"+notif.reimbursementId);
}

function showReimbDetail() {
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
                let reimb = JSON.parse(xhr.responseText)
                displayReimbDetail(reimb);
            } else {
                console.log("status != 200");
            }
        } else {
            console.log("Fetching reimbursements");
        }
    }
    xhr.open("GET", "reimbursedetail", true);
    xhr.send();
}

function displayReimbDetail(reimb) {
    console.log(reimb);
    document.getElementById("reimb-id").innerHTML = reimb.id;
    document.getElementById("create-date").innerHTML = reimb.dateCreated.monthValue + "/" + reimb.dateCreated.dayOfMonth + "/" + reimb.dateCreated.year;
    document.getElementById("reimb-type").innerHTML = reimb.type;
    document.getElementById("reimb-reqCommt").innerHTML = reimb.message;
    document.getElementById("reimb-eventdate").innerHTML = reimb.dateEvent.monthValue + "/" + reimb.dateEvent.dayOfMonth + "/" + reimb.dateEvent.year;
    document.getElementById("reimb-amt").innerHTML = reimb.amount;
}