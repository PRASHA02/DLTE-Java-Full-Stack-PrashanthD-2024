// let currentPage = 1;
// const itemsPerPage = 1; // Adjust as needed
//
// // Function to change the current page
// function changePage(page) {
//     currentPage = page;
//     getDebitCardDetails();
// }
//
// const getDebitCardDetails = () => {
//     let soapRequest = `<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:deb="http://debitcard.links">
//             <soapenv:Header/>
//             <soapenv:Body>
//                 <deb:viewDebitCardRequest/>
//             </soapenv:Body>
//         </soapenv:Envelope>`;
//
//     $.ajax({
//         url: "http://localhost:8082/debitcardrepo/debitcard.wsdl",
//         type: "POST",
//         dataType: "xml",
//         contentType: "text/xml;charset=utf-8",
//         data: soapRequest,
//         success: function(response) {
//
//             // Clear existing cards
//             $('#debit').empty();
//             var debitCards = $(response).find('ns2\\:debitCard');
//
//             if(debitCards.length==0){
//                 const errorCode = 200;
//                 const errorMessage = "No Debits Cards Available";
//                 window.location.href = `error?code=${errorCode}&message=${encodeURIComponent(errorMessage)}`;
//             }
//             // Parse XML response
//
//             var startIndex = (currentPage - 1) * itemsPerPage;
//             var endIndex = startIndex + itemsPerPage;
//
//             // Loop through debit cards for current page
//             debitCards.each(function(index) {
//                 if (index >= startIndex && index < endIndex) {
//                     var debitCardNumber = $(this).find('ns2\\:debitCardNumber').text();
//                     var accountNumber = $(this).find('ns2\\:accountNumber').text();
//                     var debitCardExpiry = $(this).find('ns2\\:debitCardExpiry').text();
//                     var debitCardStatus = $(this).find('ns2\\:debitCardStatus').text();
//                     var domesticLimit = $(this).find('ns2\\:domesticLimit').text();
//                     var internationalLimit = $(this).find('ns2\\:internationalLimit').text();
//                     // Render pagination
//                     renderPagination(debitCards.length);
//                     // Create card HTML
//                     var cardHtml = `
//                                 <div  class='col-md-12 mb-4'>
//                                     <div class="card"  style="background-color:#182057;">
//                                         <div class="card-body">
//                                             <p class="card-text text-light">DebitCard Number: ${debitCardNumber}</p>
//                                             <p class="card-text text-light">Account Number: ${accountNumber}</p>
//                                             <p class="card-text text-light">Expiry: ${debitCardExpiry}</p>
//                                             <p class="card-text text-light">Status: ${debitCardStatus}</p>
//                                             <p class="card-text text-light">Domestic Limit: ${domesticLimit}</p>
//                                             <p class="card-text text-light">International Limit: ${internationalLimit}</p>
//                                             <div class="flex-row justify-content-center">`;
//
//                     if (debitCardStatus === 'active') {
//                         // Include all buttons if status is active
//                         cardHtml += `
//                             <button class="btn btn-outline-white rounded-pill" style="background-color: #f7f7f7;color: #182052;">
//                                 Activate
//                             </button>
//                             <button type="button" class="btn btn-outline-white rounded-pill"  style="background-color: #f7f7f7;color: #182052;"
//                                 data-debit-card-number="${debitCardNumber}"
//                                 data-account-number="${accountNumber}"
//                                 data-debit-card-expiry="${debitCardExpiry}"
//                                 data-debit-card-status="${debitCardStatus}"
//                                 onclick="sendData(this)">
//                                 Update
//                             </button>
//                             <button  class="btn btn-outline-white rounded-pill"  style="background-color: #f7f7f7;color: #182052;">
//                                 Block
//                             </button>`;
//                     }
//
//                     cardHtml += `</div>
//                     </div>
//                 </div>
//             </div>`;
//                     // Append card to debit
//                     $('#debit').append(cardHtml);
//                 }
//             });
//         },
//         error: function(xhr, status, error) {
//             const errorCode = xhr.status;
//             const errorMessage = xhr.responseText;
//             window.location.href = `error?code=${errorCode}&message=${encodeURIComponent(errorMessage)}`;
//         }
//     });
// };
//
// function renderPagination(totalPages) {
//     const paginationHTML = `
//         <nav aria-label="Page navigation">
//             <ul class="pagination justify-content-center">
//                 <li class="page-item ${currentPage === 1 ? 'disabled' : ''}">
//                     <button class="page-link" onclick="changePage(${currentPage - 1})" aria-label="Previous">
//                         <span aria-hidden="true">&laquo;</span>
//                     </button>
//                 </li>
//                 ${generatePageNumbers(totalPages)}
//                 <li class="page-item ${currentPage === totalPages ? 'disabled' : ''}">
//                     <button class="page-link" onclick="changePage(${currentPage + 1})" aria-label="Next">
//                         <span aria-hidden="true">&raquo;</span>
//                     </button>
//                 </li>
//             </ul>
//         </nav>
//     `;
//     $("#pagination").html(paginationHTML);
// }
//
// function generatePageNumbers(totalPages) {
//     let pageNumbersHTML = "";
//
//     const visiblePages = 1; // Adjust the number of visible pages as needed
//     const halfVisible = Math.floor(visiblePages / 2);
//     let startPage = Math.max(1, currentPage - halfVisible);
//     let endPage = Math.min(totalPages, startPage + visiblePages - 1);
//
//     if (endPage - startPage + 1 < visiblePages) {
//         startPage = Math.max(1, endPage - visiblePages + 1);
//     }
//
//     for (let i = startPage; i <= endPage; i++) {
//         const liClass = (i === currentPage) ? "page-item active" : "page-item";
//         pageNumbersHTML += `
//             <li class="${liClass}">
//                 <button class="page-link" onclick="changePage(${i})">${i}</button>
//             </li>
//         `;
//     }
//
//     return pageNumbersHTML;
// }
//
//
// $(document).ready(function() {
//     getDebitCardDetails();
// });
//
// window.sendData = function(button) {
//     // Retrieve debit card data from the clicked button's attributes
//     const debitCardNumber = button.getAttribute('data-debit-card-number');
//     const accountNumber = button.getAttribute('data-account-number');
//     const debitCardExpiry = button.getAttribute('data-debit-card-expiry');
//     const debitCardStatus = button.getAttribute('data-debit-card-status');
//
//     // Store form data in session storage
//     const debitCardData = {
//         debitCardNumber: debitCardNumber,
//         accountNumber: accountNumber,
//         debitCardExpiry: debitCardExpiry,
//         debitCardStatus: debitCardStatus,
//     };
//
//     sessionStorage.setItem('debitCardData', JSON.stringify(debitCardData));
//
//     // Redirect to the update page
//     window.location.href = '../card/update';
// };
//
//
// // Function to fetch and display debit card details

// Function to confirm and block debit card using modal for PIN entry
function confirmAndBlock(accountNumber, debitCardNumber, debitCardExpiry,debitCardStatus, domesticLimit, internationalLimit) {
    $('#pinModal').modal('show');
    $('#pinModal').on('show.bs.modal', function (e) {
        $('#pinError').text('');
    });
    $('#submitPin').click(()=>{
        const pin = $('#pinInput').val();
        $('#pinError').text('');
        if (!pin || isNaN(pin) || pin!=debitCardPin) {
            $('#pinError').text('Incorrect Pin');
            $('#pinInput').val('');
            $('#pinError').show(); // Display error message

            return; // Exit function if PIN is invalid
        }
        $('#pinInput').val(''); // Clear PIN input field after submission
        $('#pinError').text(''); // Clear any previous error message
        if (pin) {
            const debitCard = {
                accountNumber: accountNumber,
                debitCardNumber: debitCardNumber,
                debitCardCvv: debitCardCVV,
                debitCardPin: pin,
                debitCardStatus: 'block',
                domesticLimit:domesticLimit,
                internationalLimit:internationalLimit
            };
            // AJAX call to update debit card status
            $.ajax({
                url: '/update/status',
                type: 'PUT',
                contentType: 'application/json',
                data: JSON.stringify(debitCard),
                success: function (response) {
                    $('#pinModal').modal('hide');
                    $('#success').text(response);
                    $(`#card-${debitCardNumber}`).find('.card-text:contains("Status:")').text(`Status: ${debitCard.debitCardStatus}`);
                    $('#blockSuccessModal').modal('show');

                },
                error: function (xhr, textStatus, error) {
                    const errorCode = xhr.status;
                    const errorMessage = xhr.responseText;
                    window.location.href = `error?code=${errorCode}&message=${encodeURIComponent(errorMessage)}`;
                    console.error('Error blocking debit card:', error, textStatus);

                }
            });
        } else {
            $('#pinError').text('Please enter a PIN.'); // Display error for empty PIN
        }
    });
}

// Function to fetch and display debit card details

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
                        const debitCardPin = $(this).find('ns2\\:debitCardPin').text()
                        const debitCardCVV = $(this).find('ns2\\:debitCardCvv').text();
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
                            `<button class="btn btn-light mb-3 text-center" style="border-radius: 25px; width: 6rem" onclick="confirmAndBlock('${accountNumber}', '${debitCardNumber}', '${debitCardPin}','${debitCardCVV}', '${domesticLimit}', '${internationalLimit}')">Update</button>` :
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

function errorPage(){
    const errorMessage = "Failed to block the card";
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