$(document).ready(function() {
    

});

function reject(){
    let notif = new Announcement();
    notif.msg = document.getElementById("reviewComment").value;
    notif.reimbursementId = document.getElementById("reimb-id").innerHTML;
    notif.status = "NEW";
    console.log(notif.msg + " " + notif.reimbursementId);
        let xhrNotif = new XMLHttpRequest();
        let xhrReimb = new XMLHttpRequest();

        xhrNotif.onreadystatechange = function(){
        if (xhrNotif.readyState === 4){
            if(xhrNotif.status === 200){
                //TODO: Redirect back to home and create a notification to the assigned supervisor
                console.log("notification created!");
            }
        }
        xhrReimb.onreadystatechange = function(){
        if (xhrReimb.readyState === 4){
            if(xhrReimb.status === 200){
                //TODO: Redirect back to home and create a notification to the assigned supervisor
                console.log("notification created!");
            }
        }
        xhrReimb.open("PUT", "reimbursement", true);
        xhrNotif.open("POST", "notification", true);
        xhrNotif.send(JSON.stringify(notif));
        xhrReimb.send("reject"+"\n"+notif.reimbursementId);
    }
}