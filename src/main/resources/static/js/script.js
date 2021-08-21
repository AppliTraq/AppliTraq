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


$( function() {
    $( "#appliedJobs").sortable({
        cursor: 'move',
        connectWith: "#contactedJobs",
        revert: true,
        remove: function(event, ui) {
            var movedItem = ui.item;
            var jobIdGrabbed = $('#dynamicId');
            var currentID = parseInt(movedItem.children(":first").val());
            jobIdGrabbed.val(currentID);
            $('#statusUpdate').submit();
        }
    });
    $( "#appliedJobs").disableSelection();
});

$( function() {
    $( "#contactedJobs").sortable({
        cursor: 'move',
        connectWith: "#interviewNum1",
        revert: true,
        remove: function(event, ui) {
             var movedItem = ui.item;
            var jobIdGrabbed = $('#dynamicId');
            var currentID = parseInt(movedItem.children(":first").val());
            jobIdGrabbed.val(currentID);
            $('#statusUpdate').submit();
        }
    });
    $( "#contactedJobs").disableSelection();
});

$( function() {
    $( "#interviewNum1").sortable({
        cursor: 'move',
        connectWith: "#offeredJobs",
        revert: true,
        remove: function(event, ui) {
             var movedItem = ui.item;
            var jobIdGrabbed = $('#dynamicId');
            var currentID = parseInt(movedItem.children(":first").val());
            jobIdGrabbed.val(currentID);
            $('#statusUpdate').submit();
        }
    });
    $( "#interviewNum1").disableSelection();
});



// EXPAND INTERVIEW COLUMNS
// $( function() {
    $( "#moreInterviews").onclick(function() {
        $(this).css('color', 'hotpink');
    });
// } );

// THIS IS WHERE KANBAN JAVASCRIPT ENDS

// hannah's mad experiments

//search box
//get image based off of search (see weather map location search for reference)
//feed into an image as test
//need to somehow gather this data and use a method to inject into database

//TIMELINE JAVASCRIPT START

function KanbanStatus() {

}
