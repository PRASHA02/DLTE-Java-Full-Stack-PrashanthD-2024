$(document).ready(function () {
    function getUserName() {
        $.ajax({
            type: "GET",
            url: "/card/name", // Assuming your endpoint is mapped to /web/name
            dataType: 'text', // Expecting text response
            contentType:"application/json;charset=utf-8",

            success: function (response) {
                $('#Username').text("Hey, " + response); // Display the name
            },
            error: function (xhr, status, error) {
                console.error(xhr.responseText);
                $('#Username').text("Not able to get the Username");
            }
        });
    }
    getUserName();
});