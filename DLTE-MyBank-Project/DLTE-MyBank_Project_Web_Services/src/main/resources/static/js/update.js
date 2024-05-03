$(document).ready(function() {
    // Function to handle form submission
    $("#update").click(function(event) {
        event.preventDefault(); // Prevent the form from submitting normally

        const cardNum = $("#debitCardNumber").val();
        const accountNum = $("#accountNumber").val();
        const cvv = $("#debitCardCvv").val();
        const pin = $("#debitCardPin").val();
        const expiry = $("#debitCardExpiry").val();
        const status = $("#debitCardStatus").val();
        const domLimit = $("#domesticLimit").val();
        const intLimit = $("#internationalLimit").val();

        const request = {
            "debitCardNumber":cardNum,
            "accountNumber":accountNum,
            "debitCardCvv":cvv,
            "debitCardPin":pin,
            "debitCardExpiry":expiry,
            "debitCardStatus":status,
            "domesticLimit":domLimit,
            "internationalLimit":intLimit

        };


        // AJAX request to submit form data
        $.ajax({
            url: "http://localhost:8082/update/limit",
            type: "PUT",
            dataType: "text",
            contentType: "application/json;charset=utf-8",
            data: JSON.stringify(request),
            success: function(response) {
                // Access the insuranceId property from the response

                $("#successMessage").text(`Debit Card Limit Updated Successfully`);
                $("#updateModal").modal("show");
            },
            error: function(xhr, status, error) {
                const errorCode = xhr.status;
                const errorMessage = xhr.responseText;
                window.location.href = `error?code=${errorCode}&message=${encodeURIComponent(errorMessage)}`;
            }
        });
    });
    $("#backHome").click(function() {
        // Redirect to the home page
        window.location.href = "/card/dashboard";
    });

    // Function to handle form cancellation
    $("#cancel").click(function() {
        $("#domesticLimit").val("");
        $("#internationalLimit").val("");
    });

});