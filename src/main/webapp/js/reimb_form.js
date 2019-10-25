$(document).ready(function() {
    let createDate = document.getElementById("create-date");
    let currentDate = new Date();
    createDate.innerHTML = (currentDate.getMonth() +1) + "-" + currentDate.getDate() + "-" + currentDate.getFullYear();

    // toggle grade input
    $('#inputState').on('change', function() {
        console.log($(this).val());
        if ($(this).val() != 'SEMINARS' && $(this).val() != "")
        {
          $("#divGrade").show();
        }
        else
        {
          $("#divGrade").hide();
        }
    });
});

$('#attachment').change(updateList);

let updateListItems = [];

function createReimbursement(){
    let reimbursement = new Reimbursement();
    reimbursement.id = document.getElementById("reimb-id").innerHTML;
    reimbursement.dateCreated = reimbursement.dateLastModified = document.getElementById("create-date").innerHTML;
    reimbursement.type = document.getElementById("inputState").value.toUpperCase();
    reimbursement.message = document.getElementById("comment").value;
    reimbursement.amount = document.getElementById("amount").value;
    reimbursement.status = "PEND_DS";
    //reimbursement.passgrade = document.getElementById("passGrade").value;
    if (inputValidation(reimbursement)){
        let xhr = new XMLHttpRequest();

        xhr.onreadystatechange = function(){
            if (xhr.readyState === 4){
                if(xhr.status === 200){
                    //TODO: Redirect back to home and create a notification to the assigned supervisor
                    console.log("Reimbursement created!");
                }
            }
        }

        xhr.open("POST", "reimbursement", true);
        xhr.send(JSON.stringify(reimbursement) + "\n" + JSON.stringify(updateListItems));
    }
}

function inputValidation(reimbursement){
    let isValid = true;
    if(reimbursement.type === "Choose..."){
        //TODO: Let user know what's required.
        isValid = false;
    }

    if(reimbursement.amount <= 0.0){
        isValid = false;
    }

    return isValid;
}


 // show uploaded filename in list
 function updateList() {
     let input = document.getElementById('attachment');
     if (input && input.files){
         // read selected file
         for (let i = 0; i < input.files.length; ++i) {
             updateListItems.push(input.files.item(i));
         }
     }
     displayAttachments();
 }
 function displayAttachments() {
     let unorderedList = document.getElementById("attachmentList");
     while (unorderedList.firstChild) {
         unorderedList.removeChild(unorderedList.firstChild);
     }
     // show file in list
     for (let i = 0; i < updateListItems.length; ++i){
         let itemDiv = document.createElement('div');
         let listItem = document.createElement('li');
         let itemBtn = document.createElement('button');
         listItem.setAttribute("class", "list-group-item");
         listItem.setAttribute("id", i+"_attachment");
         itemBtn.setAttribute("class", "btn btn-danger to-right");
 
         itemBtn.innerHTML = 'X';
         listItem.innerHTML = updateListItems[i].name;
         itemDiv.appendChild(listItem);
         listItem.appendChild(itemBtn);
 
         itemBtn.addEventListener('click', deleteAttachment);
         unorderedList.appendChild(itemDiv);
     }
 }
 
function deleteAttachment(event) {
    let btn = event.target;
    let parent = btn.parentElement;
    let index = parseInt(parent.id.split("_")[0], 10);
    updateListItems.splice(index,1);
    displayAttachments();
}
