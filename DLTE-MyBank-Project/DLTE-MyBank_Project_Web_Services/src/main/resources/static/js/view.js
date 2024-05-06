let currentPage = 1;
const itemsPerPage = 1; // Adjust as needed

// Function to change the current page
function changePage(page) {
    currentPage = page;
    getDebitCardDetails();
}

const getDebitCardDetails = () => {
    let soapRequest = `<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:deb="http://debitcard.links">
            <soapenv:Header/>
            <soapenv:Body>
                <deb:viewDebitCardRequest/>
            </soapenv:Body>
        </soapenv:Envelope>`;

    $.ajax({
        url: "http://localhost:8082/debitcardrepo/debitcard.wsdl",
        type: "POST",
        dataType: "xml",
        contentType: "text/xml;charset=utf-8",
        data: soapRequest,
        success: function(response) {

            // Clear existing cards
            $('#debit').empty();
            var debitCards = $(response).find('ns2\\:debitCard');

            if(debitCards.length==0){
                const errorCode = 200;
                const errorMessage = "No Debits Cards Available";
                window.location.href = `error?code=${errorCode}&message=${encodeURIComponent(errorMessage)}`;
            }
            // Parse XML response

            var startIndex = (currentPage - 1) * itemsPerPage;
            var endIndex = startIndex + itemsPerPage;

            // Loop through debit cards for current page
            debitCards.each(function(index) {
                if (index >= startIndex && index < endIndex) {
                    var debitCardNumber = $(this).find('ns2\\:debitCardNumber').text();
                    var accountNumber = $(this).find('ns2\\:accountNumber').text();
                    var debitCardExpiry = $(this).find('ns2\\:debitCardExpiry').text();
                    var debitCardStatus = $(this).find('ns2\\:debitCardStatus').text();
                    var domesticLimit = $(this).find('ns2\\:domesticLimit').text();
                    var internationalLimit = $(this).find('ns2\\:internationalLimit').text();
                    // Render pagination
                    renderPagination(debitCards.length);
                    // Create card HTML
                    var cardHtml = `
                                <div  class='col-md-12 mb-4'>
                                    <div class="card"  style="background-color:#182057;">
                                        <div class="card-body">
                                            <p class="card-text text-light">DebitCard Number: ${debitCardNumber}</p>
                                            <p class="card-text text-light">Account Number: ${accountNumber}</p>
                                            <p class="card-text text-light">Expiry: ${debitCardExpiry}</p>
                                            <p class="card-text text-light">Status: ${debitCardStatus}</p>
                                            <p class="card-text text-light">Domestic Limit: ${domesticLimit}</p>
                                            <p class="card-text text-light">International Limit: ${internationalLimit}</p>
                                            <div class="flex-row justify-content-center">`;

                    if (debitCardStatus === 'active') {
                        // Include all buttons if status is active
                        cardHtml += `
                            <button class="btn btn-outline-white rounded-pill" style="background-color: #f7f7f7;color: #182052;">
                                Activate
                            </button>
                            <button type="button" class="btn btn-outline-white rounded-pill"  style="background-color: #f7f7f7;color: #182052;"
                                data-debit-card-number="${debitCardNumber}"
                                data-account-number="${accountNumber}"
                                data-debit-card-expiry="${debitCardExpiry}"
                                data-debit-card-status="${debitCardStatus}"
                                onclick="sendData(this)">
                                Update
                            </button>
                            <button  class="btn btn-outline-white rounded-pill"  style="background-color: #f7f7f7;color: #182052;">
                                Block
                            </button>`;
                    }

                    cardHtml += `</div>
                    </div>
                </div>
            </div>`;
                    // Append card to debit
                    $('#debit').append(cardHtml);
                }
            });
        },
        error: function(xhr, status, error) {
            const errorCode = xhr.status;
            const errorMessage = xhr.responseText;
            window.location.href = `error?code=${errorCode}&message=${encodeURIComponent(errorMessage)}`;
        }
    });
};

function renderPagination(totalPages) {
    const paginationHTML = `
        <nav aria-label="Page navigation">
            <ul class="pagination justify-content-center">
                <li class="page-item ${currentPage === 1 ? 'disabled' : ''}">
                    <button class="page-link" onclick="changePage(${currentPage - 1})" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </button>
                </li>
                ${generatePageNumbers(totalPages)}
                <li class="page-item ${currentPage === totalPages ? 'disabled' : ''}">
                    <button class="page-link" onclick="changePage(${currentPage + 1})" aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                    </button>
                </li>
            </ul>
        </nav>
    `;
    $("#pagination").html(paginationHTML);
}

function generatePageNumbers(totalPages) {
    let pageNumbersHTML = "";

    const visiblePages = 1; // Adjust the number of visible pages as needed
    const halfVisible = Math.floor(visiblePages / 2);
    let startPage = Math.max(1, currentPage - halfVisible);
    let endPage = Math.min(totalPages, startPage + visiblePages - 1);

    if (endPage - startPage + 1 < visiblePages) {
        startPage = Math.max(1, endPage - visiblePages + 1);
    }

    for (let i = startPage; i <= endPage; i++) {
        const liClass = (i === currentPage) ? "page-item active" : "page-item";
        pageNumbersHTML += `
            <li class="${liClass}">
                <button class="page-link" onclick="changePage(${i})">${i}</button>
            </li>
        `;
    }

    return pageNumbersHTML;
}


$(document).ready(function() {
    getDebitCardDetails();
});

window.sendData = function(button) {
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
};


// Function to fetch and display debit card details
