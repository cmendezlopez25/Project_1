
(function ($) {
    "use strict";


    /*==================================================================
    [ Validate ]*/
    var input = $('.validate-input .input100');

    $('.validate-form').on('submit',function(){
        var check = true;

        for(var i=0; i<input.length; i++) {
            if(validate(input[i]) == false){
                showValidate(input[i]);
                check=false;
            }
        }

        return check;
    });


    $('.validate-form .input100').each(function(){
        $(this).focus(function(){
           hideValidate(this);
        });
    });

    function validate (input) {
        if($(input).attr('type') == 'email' || $(input).attr('name') == 'email') {
            if($(input).val().trim().match(/^([a-zA-Z0-9_\-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([a-zA-Z0-9\-]+\.)+))([a-zA-Z]{1,5}|[0-9]{1,3})(\]?)$/) == null) {
                return false;
            }
        }
        else {
            if($(input).val().trim() == ''){
                return false;
            }
        }
    }

    function showValidate(input) {
        var thisAlert = $(input).parent();

        $(thisAlert).addClass('alert-validate');
    }

    function hideValidate(input) {
        var thisAlert = $(input).parent();

        $(thisAlert).removeClass('alert-validate');
    }
    
    /*==================================================================
    [ Show pass ]*/
    var showPass = 0;
    $('.btn-show-pass').on('click', function(){
        if(showPass == 0) {
            $(this).next('input').attr('type','text');
            $(this).find('i').removeClass('fa-eye');
            $(this).find('i').addClass('fa-eye-slash');
            showPass = 1;
        }
        else {
            $(this).next('input').attr('type','password');
            $(this).find('i').removeClass('fa-eye-slash');
            $(this).find('i').addClass('fa-eye');
            showPass = 0;
        }
        
    });
        
    $('#attachment').change(updateList);
})(jQuery);
 // show uploaded filename in list
let updateListItems = [];
function updateList() {
    let input = document.getElementById('attachment');
    if (input && input.files){
        // read selected file
        for (let i = 0; i < input.files.length; ++i) {
            updateListItems.push(input.files.item(i));
        }
    }
    displayAttachments();
    // while (unorderedList.firstChild) {
    //     unorderedList.removeChild(unorderedList.firstChild);
    // }
    // // show file in list
    // for (let i = 0; i < updateListItems.length; ++i){
    //     let itemDiv = document.createElement('div');
    //     let listItem = document.createElement('li');
    //     let itemBtn = document.createElement('button');
    //     listItem.setAttribute("class", "list-group-item");
    //     itemBtn.setAttribute("class", "btn btn-danger to-right");

    //     itemBtn.innerHTML = 'X';
    //     listItem.innerHTML = updateListItems[i].name;
    //     itemDiv.appendChild(listItem);
    //     listItem.appendChild(itemBtn);

        

    //     unorderedList.appendChild(itemDiv);
    // }
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