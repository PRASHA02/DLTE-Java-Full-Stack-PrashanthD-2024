--1).Customer

CREATE SEQUENCE mybank_app_customer_seq START WITH 123670 INCREMENT BY 1;

CREATE TABLE mybank_app_customer (
    customer_id NUMBER primary key,
    customer_name VARCHAR(255),
    customer_address VARCHAR(255),
    customer_status VARCHAR(50),
    customer_contact NUMBER(10,0),
    username VARCHAR(50) UNIQUE not null,
    password VARCHAR(50)
);

CREATE OR REPLACE TRIGGER mybank_app_customer_trigger
BEFORE INSERT ON mybank_app_customer_seq
FOR EACH ROW
BEGIN
 SELECT mybank_app_customer_seq.NEXTVAL INTO :NEW.customer_id FROM dual;
END;
/
--2).Account

create sequence mybank_app_account_seq start with 100001 increment by 1;

CREATE TABLE mybank_app_account (
    account_id NUMBER PRIMARY KEY,
    customer_id NUMBER,
    account_type VARCHAR(50),
    account_number NUMBER UNIQUE,
    account_status VARCHAR(50),
    foreign key (customer_id) REFERENCES  mybank_app_customer(customer_id) on delete cascade
);

CREATE OR REPLACE TRIGGER mybank_app_account_trigger
BEFORE INSERT ON mybank_app_account
FOR EACH ROW
BEGIN
SELECT mybank_app_account_seq.NEXTVAL INTO :NEW.account_id FROM dual;
END;
/
--3).KYC

create sequence mybank_app_kyc_seq start with 200001 increment by 1;

CREATE TABLE mybank_app_kyc (
    kyc_number NUMBER PRIMARY KEY,
    customer_id NUMBER ,
    kyc_pan VARCHAR(50) UNIQUE,
    kyc_aadhaar NUMBER UNIQUE,
    kyc_status VARCHAR(50),
    foreign key (customer_id) REFERENCES  mybank_app_customer(customer_id) on delete cascade
);


CREATE OR REPLACE TRIGGER mybank_app_kyc_trigger
BEFORE INSERT ON mybank_app_kyc
FOR EACH ROW
SELECT mybank_app_kyc_seq.NEXTVAL INTO :NEW.kyc_number FROM dual; 
END;
/

--4).Transaction

create sequence mybank_app_transaction_seq start with 300001 increment by 1;

CREATE TABLE mybank_app_transaction (
    transaction_id NUMBER PRIMARY KEY,
    account_id NUMBER,
    transaction_type VARCHAR(50),
    transaction_from VARCHAR(255),
    transaction_to VARCHAR(255),
    transaction_date DATE,
    transaction_amount DECIMAL(15,2),
    transaction_status VARCHAR(50),
    foreign key (account_id) REFERENCES  mybank_app_account(account_id) on delete cascade
);

CREATE OR REPLACE TRIGGER mybank_app_transaction_trigger
BEFORE INSERT ON mybank_app_transaction
FOR EACH ROW
DECLARE
  account_id_count NUMBER;
BEGIN   
    SELECT mybank_app_transaction_seq.NEXTVAL INTO :NEW.transaction_id FROM dual; 
END;
/
--5).Payee


create sequence mybank_app_payee_seq start with 400001 increment by 1;

CREATE TABLE MYBANK_APP_Payee (
    payee_id NUMBER PRIMARY KEY,
    customer_id NUMBER,
    account_id NUMBER,
    payee_name VARCHAR(255),
    foreign key (customer_id) REFERENCES  mybank_app_customer(customer_id) on delete cascade
    foreign key (account_id) REFERENCES  mybank_app_account(account_id) on delete cascade

);

CREATE OR REPLACE TRIGGER mybank_app_payee_trigger
BEFORE INSERT ON mybank_app_payee
FOR EACH ROW
DECLARE
  account_id_count NUMBER;
BEGIN   
    SELECT mybank_app_transaction_seq.NEXTVAL INTO :NEW.payee_id FROM dual;
END;
/

--6).Deposits Available

create sequence mybank_app_depositsavail_seq start with 500001 increment by 1;

CREATE TABLE mybank_app_depositsavailable (
    deposit_id NUMBER PRIMARY KEY,
    deposit_name VARCHAR(255),
    deposit_roi DECIMAL(20,2),
    deposit_type VARCHAR(50),
    deposit_description VARCHAR(255)
);

CREATE OR REPLACE TRIGGER mybank_app_depositsavail_tgr
BEFORE INSERT ON mybank_app_depositsavailable 
FOR EACH ROW
BEGIN
    SELECT mybank_app_depositsavail_seq.NEXTVAL INTO :NEW.deposit_id FROM dual;
END;
/
 


--7).Deposits Availed
create sequence mybank_app_depositsavailed_seq start with 600001 increment by 1;
 
CREATE TABLE mybank_app_depositsavailed (
    deposit_avail_id NUMBER PRIMARY KEY,
    customer_id NUMBER,
    deposit_id NUMBER,
    deposited_amount DECIMAL(20,2),
    deposited_duration NUMBER,
    deposit_maturity DATE,
    foreign key (customer_id) REFERENCES  mybank_app_customer(customer_id) on delete cascade,
    foreign key (deposit_id) REFERENCES  mybank_app_depositsavailable(deposit_id) on delete cascade
);

CREATE OR REPLACE TRIGGER mybank_app_depositsavailed_tgr
BEFORE INSERT ON mybank_app_depositsavailed
FOR EACH ROW
BEGIN
SELECT mybank_app_depositsavailed_seq.NEXTVAL INTO :NEW.deposit_avail_id FROM dual;
END;
/


--8).Insurance Available

create sequence mybank_app_insuranceavail_seq start with 700001 increment by 1;
 
CREATE TABLE mybank_app_insuranceavailable (
    insurance_id NUMBER PRIMARY KEY,
    insurance_type VARCHAR(50),
    insurance_name VARCHAR(255),
    insurance_key_benefits VARCHAR(255),
    insurance_lifetime NUMBER
);

CREATE OR REPLACE TRIGGER mybank_app_insuranceavail_tgr
BEFORE INSERT ON mybank_app_insuranceavailable
FOR EACH ROW
BEGIN
 SELECT mybank_app_insuranceavail_seq.NEXTVAL INTO :NEW.insurance_id FROM dual;
END;
/
 

--9).Insurance Availed

create sequence mybank_app_insuranceavd_seq start with 800001 increment by 1;


 
CREATE TABLE mybank_app_insuranceavailed (
    insurance_availed_id NUMBER PRIMARY KEY,
    customer_id NUMBER,
    insurance_id NUMBER,
    insurance_coverage DECIMAL(20,2),
    insurance_premium DECIMAL(20,2),
    foreign key (customer_id) REFERENCES  mybank_app_customer(customer_id) on delete cascade,
    foreign key (insurance_id) REFERENCES  mybank_app_insuranceavailable(insurance_id) on delete cascade

);

CREATE OR REPLACE TRIGGER mybank_app_insuranceavailed_tgr
BEFORE INSERT ON mybank_app_insuranceavailed
FOR EACH ROW
BEGIN
 SELECT mybank_app_insuranceavd_seq.NEXTVAL INTO :NEW.insurance_availed_id FROM dual;
END;
/


--10).Debit card Available

create sequence mybank_app_debitcard_seq start with 900001 increment by 1;

 
CREATE TABLE mybank_app_debitcard (
    debitcard_number NUMBER PRIMARY KEY,
    account_id NUMBER,
    customer_id NUMBER,
    debitcard_cvv NUMBER(3),
    debitcard_pin NUMBER,
    debitcard_expiry DATE,
    debitcard_status VARCHAR(50),
    debitcard_domestic_limit DECIMAL(20,2),
    debitcard_international_limit DECIMAL(20,2),
    foreign key (customer_id) REFERENCES  mybank_app_customer(customer_id) on delete cascade
    foreign key (account_id) REFERENCES  mybank_app_account(account_id) on delete cascade

);

CREATE OR REPLACE TRIGGER mybank_app_debitcard_trigger
BEFORE INSERT ON mybank_app_debitcard
FOR EACH ROW
BEGIN
SELECT my_bank_app_seq_debitcard.NEXTVAL INTO :NEW.debitcard_number FROM dual;
END;
/

 
--11).Loans Available


create sequence mybank_app_loanavailable_seq start with 1230001 increment by 1;

CREATE TABLE mybank_app_loanavailable (
    loan_id NUMBER PRIMARY KEY,
    loan_type VARCHAR(50),
    loan_name VARCHAR(255),
    loan_description VARCHAR(255),
    loan_roi DECIMAL(20,2)
);

CREATE OR REPLACE TRIGGER mybank_app_loanavailable_tgr
BEFORE INSERT ON mybank_app_loanavailable
FOR EACH ROW
BEGIN
SELECT mybank_app_loanavailable_seq.NEXTVAL INTO :NEW.loan_id FROM dual;
END;
/

--12).Loans Availed

create sequence mybank_app_loanavailed_seq start with 1270001 increment by 1;
 
CREATE TABLE mybank_app_loanavailed (
    loan_app_id NUMBER PRIMARY KEY,
    customer_id NUMBER,
    loan_id NUMBER,
    loan_amount DECIMAL(20,2),
    loan_emi DECIMAL(20,2),
    loan_tenure NUMBER
);

CREATE OR REPLACE TRIGGER mybank_app_loanavailed_tgr
BEFORE INSERT ON mybank_app_loanavailed
FOR EACH ROW
BEGIN
SELECT mybank_app_loanavailed_seq.NEXTVAL INTO :NEW.loan_app_id FROM dual;
END;
/




