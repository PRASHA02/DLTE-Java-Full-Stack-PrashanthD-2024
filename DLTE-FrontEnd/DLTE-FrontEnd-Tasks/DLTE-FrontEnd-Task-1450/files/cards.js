const cardData = {
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
 
    if (accountNumber in cardData) {
        const cards = cardData[accountNumber];
        cards.forEach((card, index) => {
            const cardHtml = `
            <br/>
                <div class="col-lg-4 col-md-6 mb-4 d-flex ">
                    <div class="card border-primary p-3  mx-auto text-no-wrap justify-content-center shadow-sm" onclick="showCardDetails('${card.debitCardNumber}', '${card.customerId}', '${card.debitCardCvv}', '${card.debitCardPin}', '${card.debitCardStatus}', '${card.domesticLimit}', '${card.internationalLimit}')">
                        <div class="card-body">
                            <h5 class="card-title text-primary">${card.debitCardNumber}</h5>
                        </div>
                    </div>
                </div>
            `;
            cardListContainer.innerHTML += cardHtml;
        });
    } else {
        cardListContainer.innerHTML = '<p class="text-danger">No debit cards found for this account number.</p>';
    }
}
 
function showCardDetails(debitCardNumber, customerId, debitCardCvv, debitCardPin, debitCardStatus, domesticLimit, internationalLimit) {
    const cardTitle = `${debitCardNumber}`;
    const cardDetails = `
        <p><strong>Customer ID:</strong> ${customerId}</p>
        <p><strong>CVV:</strong> ${debitCardCvv}</p>
        <p><strong>PIN:</strong> ${debitCardPin}</p>
        <p><strong>Status:</strong> ${debitCardStatus}</p>
        <p><strong>Domestic Limit:</strong> ${domesticLimit}</p>
        <p><strong>International Limit:</strong> ${internationalLimit}</p>
    `;
 
 
    const TitleElement = document.getElementById('title');
    const BodyElement = document.getElementById('details');
 
    TitleElement.innerText = cardTitle;
    BodyElement.innerHTML = cardDetails;
 
   
    const modal = new bootstrap.Modal(document.getElementById('cardDetailsModal'));
    modal.show();
}


