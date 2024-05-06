let currentPage = 1;
const itemsPerPage = 2;

// Function to change the current page
function changePage(page) {
    currentPage = page;
    fetchAccountDetails();
}

// Function to fetch active accounts
function fetchAccountDetails() {
    $.ajax({
        url: "/account/list",
        type: "GET",
        dataType: "json",
        contentType: "application/json;charset=utf-8",
        success: function(response) {
            let totalItems = response.length;
            renderPagination(totalItems);

            $('#accounts').empty();
            var combinedHtml = "";

            // Pagination logic
            const startIndex = (currentPage - 1) * itemsPerPage;
            const endIndex = Math.min(startIndex + itemsPerPage, totalItems);
            const paginatedAccounts = response.slice(startIndex, endIndex);

            // Loop through paginated accounts using forEach
            paginatedAccounts.forEach(function (account) {

                if (account.accountStatus === "active") {
                    combinedHtml += "<div class='row'><div class='col-md-6 mb-4'>";
                    combinedHtml += "<div class='card' style='width: 400px; height: 250px; background-color:#182057;' >";
                    combinedHtml += "<div class='card-body'>";
                    combinedHtml += "<p class='card-text text-light'>Account Number: " + account.accountNumber + "</p>";
                    combinedHtml += "<div class='row'>";
                    combinedHtml += "<div class='col'>";
                    combinedHtml += "<img src='/images/Check.png'   height='30px' width='30px' class='status-icon'>";
                    combinedHtml += "&nbsp;";
                    combinedHtml += "<span class='card-text text-light'>" + account.accountStatus + "</span>";
                    combinedHtml += "</div>";
                    combinedHtml += "</div>";
                    combinedHtml += "<p class='card-text text-light'>Balance: " + account.accountBalance + "</p>";
                    combinedHtml += "<button class='btn btn-primary' onclick='viewDetails()'>View Details</button>";
                    combinedHtml += "</div></div></div>";
                } else if (account.accountStatus === "inactive") {
                    combinedHtml += "<div class='col-md-6 mb-4'>";
                    combinedHtml += "<div class='card' style='width: 400px; height: 250px;  background-color:#182057;''>";
                    combinedHtml += "<div class='card-body'>";
                    combinedHtml += "<p class='card-text text-light'>Account Number: " + account.accountNumber + "</p>";
                    combinedHtml += "<div class='row'>";
                    combinedHtml += "<div class='col'>";
                    combinedHtml += "<img src='/images/Cancel.png'   height='30px' width='30px' class='status-icon'>";
                    combinedHtml += "&nbsp;";
                    combinedHtml += "<span class='card-text text-light'>" + account.accountStatus + "</span>";
                    combinedHtml += "</div>";
                    combinedHtml += "</div>";
                    combinedHtml += "</div></div></div>";
                }
            });
            combinedHtml += "<div>"; // This line was originally at the end
            $("#accounts").append(combinedHtml);
        },
        error: function (xhr, status, error) {
            const errorCode = xhr.status;
            const errorMessage = xhr.responseText;
            window.location.href = `error?code=${errorCode}&message=${encodeURIComponent(errorMessage)}`;
        }
    });
}

function viewDetails(){
    window.location.href ="/card/view";
}
// Function to render pagination controls
function renderPagination(totalItems) {
    const totalPages = Math.ceil(totalItems / itemsPerPage);
    const paginationContainer = document.getElementById('pagination');
    paginationContainer.innerHTML = '';

    if (totalPages > 1) {
        let paginationHtml = '';

        // Left arrow for previous page
        paginationHtml += `
                <ul class="pagination justify-content-center">
                <li class="page-item ${currentPage === 1 ? 'disabled' : ''}">
                    <button class="page-link left-arrow" onclick="changePage(${currentPage - 1})" aria-label="Previous"></button>
                </li>
            `;

        // Middle section with current page number
        paginationHtml += `
                <li class="page-item disabled">
                    <button class="page-link">${currentPage}</button>
                </li>
            `;

        // Right arrow for next page
        paginationHtml += `
               <li class="page-item ${currentPage === totalPages ? 'disabled' : ''}">
                 <button class="page-link right-arrow" onclick="changePage(${currentPage + 1})" aria-label="Next"></button>
                </li>
            `;

        paginationContainer.innerHTML = paginationHtml;
    }
}
$(document).ready(() => {
    fetchAccountDetails();
});