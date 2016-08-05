/**
 * Created by enver on 8/2/16.
 */
$(document).ready(function () {
    $('.forgot-pass').click(function(event) {
        $(".pr-wrap").toggleClass("show-pass-reset");
    });

    $('.pass-reset-submit').click(function(event) {
        $(".pr-wrap").removeClass("show-pass-reset");
    });

    $.post( "http://localhost:8080/myEvent/webapi/user/login?ID=domi&password=hehe" ).done(function( data ) {
        alert( "Data Loaded: " + data );
    });
});