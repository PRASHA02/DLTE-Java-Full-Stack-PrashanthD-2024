function updatePage() {

    function updateForm() {
        // Get form data from session storage
        const debitCardData = JSON.parse(sessionStorage.getItem('debitCardData'));
        // Populate form fields
        $('#debitCardNumber').val(debitCardData.debitCardNumber);
        $('#accountNumber').val(debitCardData.accountNumber);
        $('#debitCardExpiry').val(debitCardData.debitCardExpiry);
        $('#debitCardStatus').val(debitCardData.debitCardStatus);
    }

    updateForm();

    $("#update").click(function(event) {
        event.preventDefault(); // Prevent the form from submitting normally

        // Validate the form
        if (!validateForm()) {
            return; // If validation fails, return early
        }

        // Proceed with form submission logic
        const cardNum = $("#debitCardNumber").val();
        const accountNum = $("#accountNumber").val();
        const expiry = $("#debitCardExpiry").val();
        const status = $("#debitCardStatus").val();
        const domLimit = $("#domesticLimit").val();
        const intLimit = $("#internationalLimit").val();
        const request = {
            "debitCardNumber": cardNum,
            "accountNumber": accountNum,
            "debitCardExpiry": expiry,
            "debitCardStatus": status,
            "domesticLimit": domLimit,
            "internationalLimit": intLimit
        };

        // AJAX request to submit form data
        $.ajax({
            url: "http://localhost:8082/update/limit",
            type: "PUT",
            dataType: "text",
            contentType: "application/json;charset=utf-8",
            data: JSON.stringify(request),
            success: function(response) {
                sessionStorage.clear();
                $("#successMessage").text(response.toString());
                $("#updateModal").modal("show");
            },
            error: function(xhr, status, error) {
                const errorCode = xhr.status;
                const errorMessage = xhr.responseText;
                window.location.href = `error?code=${errorCode}&message=${encodeURIComponent(errorMessage)}`;
            }
        });
    });

    // Function to handle cancellation
    $("#cancel").click(function() {
        window.history.back()
    });

    // Function to handle redirect to home
    $("#backHome").click(function() {
        // Redirect to the home page
        window.location.href = "/card/dashboard";
    });
}

// Call the initializePage function when the document is ready
$(document).ready(function() {
    updatePage();
});




const validateForm = () => {
    var isValid = true;


    var form = document.forms['Application'];
    const cardNum = form.debitCardNumber.value;
    const accountNum = form.accountNumber.value;
    const expiry = form.debitCardExpiry.value;
    const status = form.debitCardStatus.value;
    const domLimit = form.domesticLimit.value;
    const intLimit = form.internationalLimit.value;

    var cardNumErr = document.getElementById("debitCardNumberError");
    var accountNumErr = document.getElementById("accountNumberError");
    var expiryErr = document.getElementById("debitCardExpiryError");
    var statusErr = document.getElementById("debitCardStatusError");
    var domLimitErr = document.getElementById("domesticLimitError");
    var intLimitErr = document.getElementById("internationalLimitError");

    domLimitErr.innerHTML = "";
    intLimitErr.innerHTML = "";

    // Debit Card Number Validation
    try {
        if (!cardNum) {
            throw "<p style='color: red;'>Debit Card Number is required.</p>";
        } else if (cardNum.length !== 16 || !/^\d+$/.test(cardNum)) {
            throw "<p style='color: red;'>Invalid Debit Card Number.</p>";
        }
    } catch (message) {
        isValid = false;
        cardNumErr.innerHTML = message;
    }

    // Account Number Validation
    try {
        if (!accountNum) {
            throw "<p style='color: red;'>Account Number is required.</p>";
        } else if (accountNum.length !== 14 || !/^\d+$/.test(accountNum)) {
            throw "<p style='color: red;'>Invalid Account Number. Account doesn't exist.</p>";
        }
    } catch (message) {
        isValid = false;
        accountNumErr.innerHTML = message;
    }

    // Expiry Date Validation
    try {
        if (!expiry) {
            throw "<p style='color: red;'>Expiry Date is required.</p>";
        } else {
            const [month, year] = expiry.split('/');
            const today = new Date();
            const expiryDate = new Date(`${month}/01/${year}`);
            if (expiryDate < today) {
                throw "<p style='color: red;'>Expiry Date must be in the future.</p>";
            }
        }
    } catch (message) {
        isValid = false;
        expiryErr.innerHTML = message;
    }

    // Card Status Validation
    try {
        if (!status) {
            throw "<p style='color: red;'>Card Status is required.</p>";
        } else if (status !== "active") {
            throw "<p style='color: red;'>Invalid Card Status(Status must be active).</p>";
        }
    } catch (message) {
        isValid = false;
        statusErr.innerHTML = message;
    }

    // Domestic Limit Validation
    try {
        if (!domLimit) {
            throw "<p style='color: red;'>Domestic Limit is required.</p>";
        } else if (domLimit < 100 || domLimit > 100000) {
            throw "<p style='color: red;'>Domestic Limit must be between 100 and 100000.</p>";
        }
    } catch (message) {
        isValid = false;
        domLimitErr.innerHTML = message;
    }

    // International Limit Validation
    try {
        if (!intLimit) {
            throw "<p style='color: red;'>International Limit is required.</p>";
        } else if (intLimit < 100 || intLimit > 50000) {
            throw "<p style='color: red;'>International Limit must be between 100 and 50000.</p>";
        }
    } catch (message) {
        isValid = false;
        intLimitErr.innerHTML = message;
    }

    return isValid;
};
