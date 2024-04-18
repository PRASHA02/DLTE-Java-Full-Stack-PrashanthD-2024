const valid = () => {
    var isValid = true;

    var form = document.forms['Application'];
    const accountNumber = form.accountNumber.value;
    const accountHolder = form.accountHolder.value;
    const dateOfApply = form.dateOfApply.value;
    const address = form.address.value;
    const signature = form.signature.value;
    const contact = form.contactNumber.value;
    const email = form.email.value;

    var nameErr = document.getElementById("nameErr");
    var numberErr = document.getElementById("numberErr");
    var dateErr = document.getElementById("dateErr");
    var addressErr = document.getElementById("addressErr");
    var signErr = document.getElementById("signatureErr");
    var contactErr = document.getElementById("contactErr");
    var emailErr = document.getElementById("emailErr");

    // name validation
    try {
        if (!accountHolder.trim()) {
            throw "Account Holder name is required";
        }
    } catch (message) {
        isValid = false;
        nameErr.innerHTML = message;
    }

    // number validation
    try {
        if (!(/^\d{12}$/).test(accountNumber)) {
            throw "Account Number requires exactly 12 digits";
        }
    } catch (message) {
        isValid = false;
        numberErr.innerHTML = message;
    }

    // date validation
    try {
        if (!dateOfApply) {
            throw "Date of Apply is required";
        }
    } catch (message) {
        isValid = false;
        dateErr.innerHTML = message;
    }

    // address validation
    try {
        if (!address.trim()) {
            throw "Address is required";
        }
    } catch (message) {
        isValid = false;
        addressErr.innerHTML = message;
    }

    // signature validation
    try {
        if (!signature.trim()) {
            throw "Signature is required";
        }
    } catch (message) {
        isValid = false;
        signErr.innerHTML = message;
    }

    // contact number validation
    try {
        if (!(/^\d{10}$/).test(contact)) {
            throw "Contact Number requires exactly 10 digits";
        }
    } catch (message) {
        isValid = false;
        contactErr.innerHTML = message;
    }

    // email validation
    try {
        if (!email.trim() || !/^\S+@\S+\.\S+$/.test(email)) {
            throw "Invalid email format";
        }
    } catch (message) {
        isValid = false;
        emailErr.innerHTML = message;
    }

    return isValid;
}
