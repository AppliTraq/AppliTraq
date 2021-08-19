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


// $( function() {
//     $( "#appliedJobs, #contactedJobs, #interviewNum1, #interviewNum2, #interviewNum3, #offeredJobs").sortable({
//         cursor: 'move',
//         connectWith: ".connectedSortable"
//     }, function (){
//         $('#statusUpdate').submit( function (event){
//             event.preventDefault();
//             $('#kanban_status').val();
//         });
//     }).disableSelection()});

$( function() {
    $( "#appliedJobs, #contactedJobs, #interviewNum1, #interviewNum2, #interviewNum3, #offeredJobs").sortable({
        cursor: 'move',
        connectWith: ".connectedSortable",
        remove: function(event, ui) {
            $('#statusUpdate').submit();
        }
    });
    $( "#appliedJobs, #contactedJobs, #interviewNum1, #interviewNum2, #interviewNum3, #offeredJobs").disableSelection();
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
