"use strict";

// KANBAN JAVASCRIPT START

// $( "#username" ).click(function() {
//     alert( "Handler for .click() called." );
// });

$('#appliedJobs').draggable();

// DRAG FUNCTION FOR JOB APPS ON KANBAN BOARD
$( function() {
    $( "#appliedJobs, #contactedJobs, #interviewNum1, #interviewNum2, #interviewNum3, #offeredJobs").sortable({
        cursor: 'move',
        connectWith: ".connectedSortable",

    }).$('form#statusUpdate').submit().disableSelection();
} );



// EXPAND INTERVIEW COLUMNS
// $( function() {
    $( "#moreInterviews").onclick(function() {
        $(this).css('color', 'hotpink');
    });
// } );

// onclick="document.getElementById('form1').submit();
// POSSIBLE SAVE BUTTON ON HTML SO THAT WHEN IT SUBMIT IT CREATES A TIMESTAMP


// function handle_mousedown(e){
//     window.my_dragging = {};
//     my_dragging.pageX0 = e.pageX;
//     my_dragging.pageY0 = e.pageY;
//     my_dragging.elem = this;
//     my_dragging.offset0 = $(this).offset();
//     function handle_dragging(e){
//         var left = my_dragging.offset0.left + (e.pageX - my_dragging.pageX0);
//         var top = my_dragging.offset0.top + (e.pageY - my_dragging.pageY0);
//         $(my_dragging.elem)
//             .offset({top: top, left: left});
//     }
//     function handle_mouseup(e){
//         $('body')
//             .off('mousemove', handle_dragging)
//             .off('mouseup', handle_mouseup);
//     }
//     $('body')
//         .on('mouseup', handle_mouseup)
//         .on('mousemove', handle_dragging);
// }
// $('.draggable').mousedown(handle_mousedown);

// THIS IS WHERE KANBAN JAVASCRIPT ENDS

// hannah's mad experiments

//search box
//get image based off of search (see weather map location search for reference)
//feed into an image as test
//need to somehow gather this data and use a method to inject into database
