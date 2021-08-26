"use strict";

// KANBAN JAVASCRIPT START

// $( "#username" ).click(function() {
//     alert( "Handler for .click() called." );
// });

// $('#appliedJobs').draggable();

// DRAG FUNCTION FOR JOB APPS ON KANBAN BOARD
// function submitForm (){
//     return $('form#statusUpdate').submit();
// }


$(function () {
    $("#appliedJobs").sortable({
        cursor: 'move',
        connectWith: "#contactedJobs",
        revert: true,
        placeholder: "jobCard-highlight",
        remove: function (event, ui) {
            var movedItem = ui.item;
            var jobIdGrabbed = $('#dynamicId');
            var currentID = parseInt(movedItem.children(":first").val());
            jobIdGrabbed.val(currentID);
            $('#statusUpdate').submit();
        }
    });
    $("#appliedJobs").disableSelection();
});

$(function () {
    $("#contactedJobs").sortable({
        cursor: 'move',
        connectWith: "#interviewNum1",
        revert: true,
        placeholder: "jobCard-highlight",
        remove: function (event, ui) {
            var movedItem = ui.item;
            var jobIdGrabbed = $('#dynamicId');
            var currentID = parseInt(movedItem.children(":first").val());
            jobIdGrabbed.val(currentID);
            $('#statusUpdate').submit();
        }
    });
    $("#contactedJobs").disableSelection();
});

$(function () {
    $("#interviewNum1").sortable({
        cursor: 'move',
        connectWith: "#offeredJobs",
        revert: true,
        placeholder: "jobCard-highlight",
        remove: function (event, ui) {
            var movedItem = ui.item;
            var jobIdGrabbed = $('#dynamicId');
            var currentID = parseInt(movedItem.children(":first").val());
            jobIdGrabbed.val(currentID);
            $('#statusUpdate').submit();
        }
    });
    $("#interviewNum1").disableSelection();
});

$(function () {
    $("#offeredJobs").sortable(
        {
            cursor: 'move',
            // connectWith: ".offeredJobs",
            revert: true,
            placeholder: "jobCard-highlight",
            remove: function (event, ui) {
                var movedItem = ui.item;
                var jobIdGrabbed = $('#dynamicId');
                var currentID = parseInt(movedItem.children(":first").val());
                jobIdGrabbed.val(currentID);
                $('#statusUpdate').submit();
            }
        }
    );
    $("#offeredJobs").disableSelection();
});


// EXPAND INTERVIEW COLUMNS
// $( function() {
/*    $( "#moreInterviews").onclick(function() {
        $(this).css('color', 'hotpink');
    });*/
// } );

// THIS IS WHERE KANBAN JAVASCRIPT ENDS

// hannah's mad experiments

//search box
//get image based off of search (see weather map location search for reference)
//feed into an image as test
//need to somehow gather this data and use a method to inject into database

//TIMELINE JAVASCRIPT START
/*the below code allows the timeline items displayed on the calendar view to be updated by the kanban status by color*/
var januaryStatus = $('#january').children();
var februaryStatus = $('#february').children();
var marchStatus = $('#march').children();
var aprilStatus = $('#april').children();
var mayStatus = $('#may').children();
var juneStatus = $('#june').children();
var julyStatus = $('#july').children();
var augustStatus = $('#august').children();
var septemberStatus = $('#september').children();
var octoberStatus = $('#october').children();
var novemberStatus = $('#november').children();
var decemberStatus = $('#december').children();

var arr = [];

for (let i = 1; i < januaryStatus.length; i++){
    arr.push(januaryStatus[i]);
}

for (let i = 1; i < februaryStatus.length; i++){
    arr.push(februaryStatus[i]);
}

for (let i = 1; i < marchStatus.length; i++){
    arr.push(marchStatus[i]);
}

for (let i = 1; i < aprilStatus.length; i++){
    arr.push(aprilStatus[i]);
}

for (let i = 1; i < mayStatus.length; i++){
    arr.push(mayStatus[i]);
}

for (let i = 1; i < juneStatus.length; i++){
    arr.push(juneStatus[i]);
}

for (let i = 1; i < julyStatus.length; i++){
    arr.push(julyStatus[i]);
}

for (let i = 1; i < augustStatus.length; i++) {
    arr.push(augustStatus[i]);
    console.log(augustStatus[i].lastElementChild.value);
}

for (let i = 1; i < septemberStatus.length; i++){
    arr.push(septemberStatus[i]);
}

for (let i = 1; i < octoberStatus.length; i++){
    arr.push(octoberStatus[i]);
}

for (let i = 1; i < novemberStatus.length; i++){
    arr.push(novemberStatus[i]);
}

for (let i = 1; i < decemberStatus.length; i++){
    arr.push(decemberStatus[i]);
}

for (let i = 0; i < arr.length; i++) {
    console.log("for loop")
    if (arr[i].lastElementChild.value == 1) {
        console.log("if statement")
        console.log(arr[i].lastElementChild.value == 1)
        $(arr[i]).addClass('status1');
    } else if (arr[i].lastElementChild.value == 2) {
        $(arr[i]).addClass('status2');
    } else if (arr[i].lastElementChild.value == 3) {
        $(arr[i]).addClass('status3');
    } else if (arr[i].lastElementChild.value == 4) {
        $(arr[i]).addClass('status4');
    }
}


