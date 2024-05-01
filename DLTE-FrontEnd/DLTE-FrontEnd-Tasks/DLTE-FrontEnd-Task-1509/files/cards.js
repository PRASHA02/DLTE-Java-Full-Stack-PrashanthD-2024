const cardsDetails = {
    "100000": [
        {
          "debitCardNumber": 3692468135796670,
          "accountNumber": 78909876543530,
          "customerId": 123670,
          "debitCardCvv": 123,
          "debitCardPin": 1234,
          "debitCardExpiry": "03-APR-24",
          "debitCardStatus": "active",
          "internationalLimit": 500000
        },
        {
            "debitCardNumber": 3692468135796671,
            "accountNumber": 78909876543530,
            "customerId": 123671,
            "debitCardCvv": 127,
            "debitCardPin": 1234,
            "debitCardExpiry": "11-MAY-24",
            "debitCardStatus": "block",
            "internationalLimit": 300000
          },
          {
            "debitCardNumber": 3692468135796672,
            "accountNumber": 78909876543530,
            "customerId": 123672,
            "debitCardCvv": 556,
            "debitCardPin": 1234,
            "debitCardExpiry": "26-APR-29",
            "debitCardStatus": "inactive",
            "internationalLimit": 200000
          },
          {
            "debitCardNumber": 3692468135796673,
            "accountNumber": 78909876543530,
            "customerId": 123673,
            "debitCardCvv": 999,
            "debitCardPin": 1234,
            "debitCardExpiry": "12-JAN-28",
            "debitCardStatus": "inactive",
            "internationalLimit": 200000
          },
          {
            "debitCardNumber": 3692468135796674,
            "accountNumber": 78909876543530,
            "customerId": 123674,
            "debitCardCvv": 111,
            "debitCardPin": 1234,
            "debitCardExpiry": "12-FEB-28",
            "debitCardStatus": "block",
            "internationalLimit": 200000
          }
      ],
   
    "500000": [
        {
            "debitCardNumber": 3692468135796670,
            "accountNumber": 11109876543890,
            "customerId": 123670,
            "debitCardCvv": 123,
            "debitCardPin": 1234,
            "debitCardExpiry": "03-APR-24",
            "debitCardStatus": "block",
            "internationalLimit": 500000
          },
          {
              "debitCardNumber": 3692468135796671,
              "accountNumber": 11109876543890,
              "customerId": 123671,
              "debitCardCvv": 127,
              "debitCardPin": 1234,
              "debitCardExpiry": "11-MAY-24",
              "debitCardStatus": "active",
              "internationalLimit": 300000
            },
            {
              "debitCardNumber": 3692468135796672,
              "accountNumber": 11109876543890,
              "customerId": 123672,
              "debitCardCvv": 556,
              "debitCardPin": 1234,
              "debitCardExpiry": "26-APR-29",
              "debitCardStatus": "inactive",
            
              "internationalLimit": 200000
            },
            {
              "debitCardNumber": 3692468135796673,
              "accountNumber": 11109876543890,
              "customerId": 123673,
              "debitCardCvv": 999,
              "debitCardPin": 1234,
              "debitCardExpiry": "12-JAN-28",
              "debitCardStatus": "inactive",
             
              "internationalLimit": 200000
            },
            {
              "debitCardNumber": 3692468135796674,
              "accountNumber": 11109876543890,
              "customerId": 123674,
              "debitCardCvv": 111,
              "debitCardPin": 1234,
              "debitCardExpiry": "12-FEB-28",
              "debitCardStatus": "inactive",
            
              "internationalLimit": 200000
            }
    ]
};

const itemsPerPage = 2;
let currentPage = 1;

function searchCards() {
    const domesticLimit = document.getElementById('domesticLimit').value.trim();
    const cardListContainer = document.getElementById('cardList');
    cardListContainer.innerHTML = '';

    if (domesticLimit in cardsDetails) {
        const cards = cardsDetails[domesticLimit];
        const startIndex = (currentPage - 1) * itemsPerPage;
        const paginatedCards = cards.slice(startIndex, startIndex + itemsPerPage);
        
        paginatedCards.forEach(card => {
            const cardHtml = `
            <br/>
            <div class="col-lg-5 col-md-7 md-2 d-flex">
                <div class="card border-primary p-6 mx-auto text-no-wrap justify-content-center shadow-sm">
                    <div class="card-body">
                        <p><strong>Debit card Number:</strong> ${card.debitCardNumber}</p>
                        <p><strong>Account Number:</strong> ${card.accountNumber}</p>
                        <p><strong>Customer ID:</strong> ${card.customerId}</p>
                        <div class="card-details">
                            <p><strong>Debit card Number:</strong> ${card.debitCardNumber}</p>
                            <p><strong>Account Number:</strong> ${card.accountNumber}</p>
                            <p><strong>Customer ID:</strong> ${card.customerId}</p>
                            <p><strong>CVV:</strong> ${card.debitCardCvv}</p>
                            <p><strong>PIN:</strong> ${card.debitCardPin}</p>
                            <p><strong>Expiry:</strong>${card.debitCardExpiry}</p>
                            <p><strong>Status:</strong> ${card.debitCardStatus}</p>
                            <p><strong>International Limit:</strong> ${card.internationalLimit}</p>
                        </div>
                    </div>
                </div>
            </div>
            `;
            cardListContainer.innerHTML += cardHtml;
        });

        renderPagination(cards.length); // Update pagination based on filtered cards
    } else {
        cardListContainer.innerHTML = `<p class="text-danger">No debit cards found for this domestic limit.</p>`;
    }
}

function renderPagination(totalItems) {
  const totalPages = Math.ceil(totalItems / itemsPerPage);
  let paginationHtml = "";

  for (let i = 1; i <= totalPages; i++) {
      paginationHtml += `<button class="btn btn-sm btn-outline-primary mx-1" onclick="changePage(${i})">${i}</button>`;
  }

  $("#pagination").html(paginationHtml);
}



function changePage(page) {
    currentPage = page;
    searchCards(); // Call searchCards again when page changes
}



