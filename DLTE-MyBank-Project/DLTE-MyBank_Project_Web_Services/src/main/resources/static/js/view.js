function fetchDebitCard(page, pageSize) {
    $.ajax({
        url: 'http://localhost:8082/debitcardrepo/debitcard.wsdl',
        type: 'POST',
        dataType: 'xml',
        contentType: 'text/xml; charset=utf-8',
        data: `
                <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:deb="http://debitcard.links">
                    <soapenv:Header/>
                    <soapenv:Body>
                        <deb:viewDebitCardRequest/>
                    </soapenv:Body>
                </soapenv:Envelope>`,
        success: function (response) {
            $(response).find(`ns2\\:serviceStatus`).each(function () {
                $('#cardContainer').empty(); // Clear existing cards
                const debitCards = $(response).find('ns2\\:debitCard');
                const totalDebitCards = debitCards.length;

                if (totalDebitCards == 0) {
                    const errorMessage = $(this).text($(this).find("ns2\\:message").text())
                    window.location.href = `error?message=${encodeURIComponent(errorMessage.toString())}`;
                    return;
                }else {
                    const totalPages = Math.ceil(totalDebitCards / pageSize);
                    debitCards.slice((page - 1) * pageSize, page * pageSize).each(function () {
                        const debitCardNumber = $(this).find('ns2\\:debitCardNumber').text();
                        const accountNumber = $(this).find('ns2\\:accountNumber').text();
                        const debitCardStatus = $(this).find('ns2\\:debitCardStatus').text();
                        const debitCardExpiry = $(this).find('ns2\\:debitCardExpiry').text();
                        const domesticLimit = $(this).find('ns2\\:domesticLimit').text();
                        const internationalLimit = $(this).find('ns2\\:internationalLimit').text();
                        const cardHtml = `
                                <div class="col-md-6 mb-4">
                                    <div class="card text-white text-center" id="card-${debitCardNumber}" style="background-color:#182057;">
                                        <div class="card-body">
                                            <h5 class="card-title">DebitCard Number: ${debitCardNumber}</h5>
                                            <p class="card-text">Account Number: ${accountNumber}</p>
                                            <p class="card-text">Expiry: ${debitCardExpiry}</p>
                                            <p class="card-text">Domestic Limit: ${domesticLimit}</p>
                                            <p class="card-text">International Limit: ${internationalLimit}</p>
                                            <p class="card-text">Status: ${debitCardStatus}</p>
                                            <button class="btn btn-light mb-3" style="border-radius: 25px; width:6rem" onclick="pageError()">Activate</button>
                                            ${debitCardStatus === 'active' ?
                            `<button class="btn btn-light mb-3 text-center" style="border-radius: 25px; width: 6rem"  data-debit-card-number="${debitCardNumber}"
                                    data-account-number="${accountNumber}"
                                   data-debit-card-expiry="${debitCardExpiry}"
                                   data-debit-card-status="${debitCardStatus}"
                                   onclick="sendData(this)">Update</button>` :
                            `<button class="btn btn-light mb-3 text-center" style="border-radius: 25px;width: 6rem" onclick="errorPage()">Update</button>`
                        }
                                            <button class="btn btn-light mb-3" style="border-radius: 25px;width:6rem" onclick="pageError()">Block</button>

                                        </div>
                                    </div>
                                </div>`;
                        $('#cardContainer').append(cardHtml);
                    });
                    $('#pagination').empty();


                    // Add page buttons
                    for (let i = 1; i <= totalPages; i++) {
                        $('#pagination').append(`
                                    <li class=" justify-content-center page-item ${i === page ? 'active' : ''}">
                                        <a class="page-link" href="#" onclick="fetchDebitCard(${i}, ${pageSize})">${i}</a>
                                    </li>
                                `);
                    }
                }

            })
        },
        error: function (xhr, status, error) {
            const info = $(error).find(`ns2\\:serviceStatus`).find("ns2\\:status").text();
            const errorCode = xhr.status;
            const errorMessage = $(error).find(`ns2\\:serviceStatus`).find("ns2\\:message").text();
            window.location.href = `error?code=${info}&message=${encodeURIComponent(errorMessage)}`;
            console.log(errorMessage);
            window.location.href = `error?code=${errorCode}&message=${encodeURIComponent(errorMessage)}`;
            console.error('Error fetching debit cards:', error);
        }
    });
}


function sendData(button) {
    // Retrieve debit card data from the clicked button's attributes
    const debitCardNumber = button.getAttribute('data-debit-card-number');
    const accountNumber = button.getAttribute('data-account-number');
    const debitCardExpiry = button.getAttribute('data-debit-card-expiry');
    const debitCardStatus = button.getAttribute('data-debit-card-status');

    // Store form data in session storage
    const debitCardData = {
        debitCardNumber: debitCardNumber,
        accountNumber: accountNumber,
        debitCardExpiry: debitCardExpiry,
        debitCardStatus: debitCardStatus,
    };

    sessionStorage.setItem('debitCardData', JSON.stringify(debitCardData));

    // Redirect to the update page
    window.location.href = '../card/update';
}
function errorPage(){
    const errorMessage = "Failed to update the Card";
    window.location.href = `error?message=${encodeURIComponent(errorMessage)}`;

}

function pageError(){
    window.location.href = `error?code=400&message=Requested page is not available, Page Under Construction`;
}

$(document).ready(() => {
    let page = 1;
    const pageSize = 2;
    fetchDebitCard(page, pageSize);

});
