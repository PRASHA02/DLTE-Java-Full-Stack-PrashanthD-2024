$(document).ready(function() {
    // function populateForm() {
    //     //const debitCardData = JSON.parse(sessionStorage.getItem('debitCardData'));
    //     // Populate form fields
    //     $('#debitCardNumber').val(debitCardData.debitCardNumber);
    //     $('#accountNumber').val(debitCardData.accountNumber);
    //     $('#debitCardCvv').val([[${sendCardDetails.debitCardCvv}]]);
    //     $('#debitCardPin').val(debitCardData.debitCardPin);
    //     $('#debitCardExpiry').val(debitCardData.debitCardExpiry);
    //     $('#debitCardStatus').val(debitCardData.debitCardStatus);
    //     sessionStorage.clear();
    // }
    //
    // populateForm();


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
                if (xhr.status === 409) {
                    // Extract the response body from xhr.responseText
                    const responseBody = xhr.responseText;
                    $("#errorMessage").text(responseBody);
                    $("#errorModal").modal("show");
                }else {
                    const errorCode = xhr.status;
                    const errorMessage = xhr.responseText;
                    const errorPageUrl = `/error?code=${errorCode}&message=${encodeURIComponent(errorMessage)}`;

                    // Navigate to the error page
                    window.location.href = errorPageUrl;

                }

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