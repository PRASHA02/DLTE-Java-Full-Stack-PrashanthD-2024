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
 
function searchCards() {
  const domesticLimit = document.getElementById('domesticLimit').value.trim();
  const cardListContainer = document.getElementById('cardList');
  cardListContainer.innerHTML = '';

  if (domesticLimit in cardsDetails) {
      const cards = cardsDetails[domesticLimit];
      cards.forEach((card, index) => {
          const cardHtml = `
          <br/>
              <div class="col-lg-4 col-md-6 mb-4 d-flex">
                  <div class="card border-primary p-6 mx-auto text-no-wrap justify-content-center shadow-sm">
                      <div class="card-body">
                          <h5 class="card-title text-primary">${card.debitCardNumber}</h5>
                          <div class="card-details">
                              <p><strong>Debit card Number:</strong> ${card.debitCardNumber}</p>
                              <p><strong>Domestic Limit:</strong> ${card.accountNumber}</p>
                              <p><strong>Customer ID:</strong> ${card.customerId}</p>
                              <p><strong>CVV:</strong> ${card.debitCardCvv}</p>
                              <p><strong>PIN:</strong> ${card.debitCardPin}</p>
                              <p><strong>Status:</strong> ${card.debitCardStatus}</p>
                              <p><strong>International Limit:</strong> ${card.internationalLimit}</p>
                          </div>
                      </div>
                  </div>
              </div>
          `;
          cardListContainer.innerHTML += cardHtml;
      });
  } else {
      cardListContainer.innerHTML = `<p class="text-danger">No debit cards found for this debit card limit.</p>`;
  }
}

// Define global variables
let recordsPerPage = 2;
let currentPage = 1;

// Function to display records based on the current page
const viewRecords = () => {
    const begin = (currentPage - 1) * recordsPerPage;
    const end = begin + recordsPerPage;
    const keys = Object.keys(cardsDetails);

    let cardListContainer = $("#cardList");
    cardListContainer.empty();

    for (let i = begin; i < end && i < keys.length; i++) {
        const domesticLimit = keys[i];
        const cards = cardsDetails[domesticLimit];

        cards.forEach((card) => {
            const cardHtml = `
            <div class="col-lg-4 col-md-6 mb-4 d-flex">
                <div class="card border-primary p-6 mx-auto text-no-wrap justify-content-center shadow-sm">
                    <div class="card-body">
                        <h5 class="card-title text-primary">${card.debitCardNumber}</h5>
                        <div class="card-details">
                            <p><strong>Debit card Number:</strong> ${card.debitCardNumber}</p>
                            <p><strong>Domestic Limit:</strong> ${card.accountNumber}</p>
                            <p><strong>Customer ID:</strong> ${card.customerId}</p>
                            <p><strong>CVV:</strong> ${card.debitCardCvv}</p>
                            <p><strong>Status:</strong> ${card.debitCardStatus}</p>
                            <p><strong>International Limit:</strong> ${card.internationalLimit}</p>
                        </div>
                    </div>
                </div>
            </div>
            `;
            cardListContainer.append(cardHtml);
        });
    }
};

// Function to update pagination buttons
const updatingPage = () => {
    const keys = Object.keys(cardsDetails);
    const totalPages = Math.ceil(keys.length / recordsPerPage);
    let paging = $("#pagination");
    paging.empty();

    // Previous button
    if (currentPage > 1) {
        paging.append('<button class="previous col-1 m-3 btn btn-outline-danger"><h4 class="bi bi-arrow-left-circle-fill"></h4></button>');
    }

    // Current page number
    paging.append('<button class="current col-1 m-3 btn btn-outline-danger">' + currentPage + '</button>');

    // Next button
    if (currentPage < totalPages) {
        paging.append('<button class="next col-1 m-3 btn btn-outline-danger"><h4 class="bi bi-arrow-right-circle-fill"></h4></button>');
    }

    // Attach event listeners to pagination buttons
    $("button").click(function () {
        const currentButton = $(this);
        if (currentButton.hasClass("previous")) {
            currentPage--;
        } else if (currentButton.hasClass("next")) {
            currentPage++;
        }
        viewRecords();
        updatingPage();
    });
};

// Initial function call to display records and update pagination
viewRecords




