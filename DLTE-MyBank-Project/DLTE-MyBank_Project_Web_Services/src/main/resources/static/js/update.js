$(document).ready(() => {
    $("#cancel").click(() => {
        $("#cardNumber").val("");
        $("#accountNumber").val("");
        $("#cardCVV").val("");
        $("#cardExpiry").val("");
        $("#cardStatus").val("");
        $("#domesticLimit").val("");
        $("#internationalLimit").val("");
    });

    $("#update").click(() => {
        const cardNum = $("#cardNumber").val();
        const accountNum = $("#accountNumber").val();
        const cvv = $("#cardCVV").val();
        const expiry = $("#cardExpiry").val();
        const status = $("#cardStatus").val();
        const domLimit = $("#domesticLimit").val();
        const intLimit = $("#internationalLimit").val();

        const request = {
            "debitCardNumber":cardNum,
            "accountNumber":accountNum,
            "debitCardCvv":cvv,
            "debitCardExpiry":expiry,
            "debitCardStatus":status,
            "domesticLimit":domLimit,
            "internationalLimit":intLimit

        };

        $.ajax({
            url: "http://localhost:8082/update/limit",
            type: "PUT",
            dataType: "text",
            contentType: "application/json;charset=utf-8",
            data: JSON.stringify(request),
            success:function(response){
                let element = $("#status")
                element.append(`<h1>Debit Card Limit has Updated Successfully</h1>`)
            },
            error:function(err){
                let element = $("#status")
                element.append(`<h1>${err.status}</h1>`)
            }
        });
    });
});