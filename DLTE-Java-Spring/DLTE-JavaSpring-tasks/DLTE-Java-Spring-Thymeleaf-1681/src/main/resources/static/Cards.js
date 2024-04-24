const cardsDetails = {
    "78909876543530": [
        {
            "debitCardNumber": 3692468135796670,
            "customerId": 123670,
            "debitCardCvv": 123,
            "debitCardPin": 1234,
            "debitCardExpiry": "03-APR-24",
            "debitCardStatus": "active",
            "domesticLimit": 100000,
            "internationalLimit": 500000
        },
        {
            "debitCardNumber": 3692468135796671,
            "customerId": 123671,
            "debitCardCvv": 127,
            "debitCardPin": 1234,
            "debitCardExpiry": "11-MAY-24",
            "debitCardStatus": "block",
            "domesticLimit": 200000,
            "internationalLimit": 300000
        },
        {
            "debitCardNumber": 3692468135796672,
            "customerId": 123672,
            "debitCardCvv": 556,
            "debitCardPin": 1234,
            "debitCardExpiry": "26-APR-29",
            "debitCardStatus": "inactive",
            "domesticLimit": 50000,
            "internationalLimit": 200000
        },
        {
            "debitCardNumber": 3692468135796673,
            "customerId": 123673,
            "debitCardCvv": 999,
            "debitCardPin": 1234,
            "debitCardExpiry": "12-JAN-28",
            "debitCardStatus": "inactive",
            "domesticLimit": 50000,
            "internationalLimit": 200000
        },
        {
            "debitCardNumber": 3692468135796674,
            "customerId": 123674,
            "debitCardCvv": 111,
            "debitCardPin": 1234,
            "debitCardExpiry": "12-FEB-28",
            "debitCardStatus": "block",
            "domesticLimit": 50000,
            "internationalLimit": 200000
        }
    ],

    "11109876543890": [
        {
            "debitCardNumber": 3692468135796670,
            "customerId": 123670,
            "debitCardCvv": 123,
            "debitCardPin": 1234,
            "debitCardExpiry": "03-APR-24",
            "debitCardStatus": "block",
            "domesticLimit": 100000,
            "internationalLimit": 500000
        },
        {
            "debitCardNumber": 3692468135796671,
            "customerId": 123671,
            "debitCardCvv": 127,
            "debitCardPin": 1234,
            "debitCardExpiry": "11-MAY-24",
            "debitCardStatus": "active",
            "domesticLimit": 200000,
            "internationalLimit": 300000
        },
        {
            "debitCardNumber": 3692468135796672,
            "customerId": 123672,
            "debitCardCvv": 556,
            "debitCardPin": 1234,
            "debitCardExpiry": "26-APR-29",
            "debitCardStatus": "inactive",
            "domesticLimit": 50000,
            "internationalLimit": 200000
        },
        {
            "debitCardNumber": 3692468135796673,
            "customerId": 123673,
            "debitCardCvv": 999,
            "debitCardPin": 1234,
            "debitCardExpiry": "12-JAN-28",
            "debitCardStatus": "inactive",
            "domesticLimit": 50000,
            "internationalLimit": 200000
        },
        {
            "debitCardNumber": 3692468135796674,
            "customerId": 123674,
            "debitCardCvv": 111,
            "debitCardPin": 1234,
            "debitCardExpiry": "12-FEB-28",
            "debitCardStatus": "inactive",
            "domesticLimit": 50000,
            "internationalLimit": 200000
        }
    ]
};

function searchCards() {
    const accountNumber = document.getElementById('accountNumber').value.trim();
    const cardListContainer = document.getElementById('cardList');
    cardListContainer.innerHTML = '';

    if (accountNumber in cardsDetails) {
        const cards = cardsDetails[accountNumber];
        cards.forEach((card, index) => {
            const cardHtml = `
          <br/>
              <div class="col-lg-4 col-md-6 mb-4 d-flex">
                  <div class="card border-primary p-6 mx-auto text-no-wrap justify-content-center shadow-sm">
                      <div class="card-body">
                          <h5 class="card-title text-primary">${card.debitCardNumber}</h5>
                          <div class="card-details">
                              <p><strong>Debit card Number:</strong> ${card.debitCardNumber}</p>
                              <p><strong>Customer ID:</strong> ${card.customerId}</p>
                              <p><strong>CVV:</strong> ${card.debitCardCvv}</p>
                              <p><strong>PIN:</strong> ${card.debitCardPin}</p>
                              <p><strong>Status:</strong> ${card.debitCardStatus}</p>
                              <p><strong>Domestic Limit:</strong> ${card.domesticLimit}</p>
                              <p><strong>International Limit:</strong> ${card.internationalLimit}</p>
                          </div>
                      </div>
                  </div>
              </div>
          `;
            cardListContainer.innerHTML += cardHtml;
        });
    } else {
        cardListContainer.innerHTML = `<p class="text-danger">No debit cards found for this account number.</p>`;
    }
}


