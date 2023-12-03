var calendarInstance1 = new calendarJs( "calendar", {
    manualEditingEnabled: true
    // All your options can be set here
} );

// OR
var calendarElement = document.getElementById( "calendar" );
var calendarInstance2 = new calendarJs( calendarElement, {
    manualEditingEnabled: true
    // All your options can be set here
} );