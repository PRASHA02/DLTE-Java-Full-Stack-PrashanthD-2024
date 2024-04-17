create or replace PROCEDURE  UPDATE_DEBITCARD_LIMIT (
    p_account_number IN MYBANK_APP_DEBITCARD.account_number%TYPE,
    p_domestic_limit IN MYBANK_APP_DEBITCARD.debitcard_domestic_limit%TYPE,
    p_international_limit IN MYBANK_APP_DEBITCARD.debitcard_international_limit%TYPE,
    status OUT VARCHAR2
) AS    
    v_customer_status VARCHAR2(255);
    v_account_status VARCHAR2(255);
    v_count NUMBER;
    v_debitcard_status VARCHAR2(255);
BEGIN 
        -- Check if the associated customer is active
        SELECT customer_status INTO v_customer_status
        FROM mybank_app_customer c
        JOIN mybank_app_debitcard d ON c.customer_id = d.customer_id
        WHERE d.account_number = p_account_number;

        -- Check if the associated account is active
        SELECT account_status INTO v_account_status
        FROM mybank_app_account a
        JOIN mybank_app_debitcard d ON a.account_number = d.account_number
        WHERE d.account_number = p_account_number;

        -- Check if the debit card exists
        SELECT COUNT(*) INTO v_count
        FROM mybank_app_debitcard
        WHERE account_number = p_account_number;


        -- Check if the debit card exists and is active
        SELECT debitcard_status INTO v_debitcard_status
        FROM mybank_app_debitcard 
        WHERE account_number = p_account_number;

        -- Update the debit card limit
        IF v_customer_status = 'active' AND v_account_status = 'active' AND v_debitcard_status = 'active' THEN
            IF v_count > 0 THEN
                UPDATE mybank_app_debitcard d
                SET d.debitcard_domestic_limit = p_domestic_limit,
                    d.debitcard_international_limit = p_international_limit
                WHERE d.account_number = p_account_number;
                COMMIT;
                status := 'SQLCODE-000';
            END IF;
        ELSIF v_customer_status != 'active' THEN
            status := 'SQLCODE-001'; -- Error code for "Customer is not active"
        ELSIF v_account_status != 'active' THEN
            status := 'SQLCODE-002'; -- Error code for "Account is not active"
        ELSE 
            status := 'SQLCODE-003'; -- Error code for "Debit card limit update failed"
        END IF;
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            status := 'SQLCODE-004'; -- Error code for "No data found"
        WHEN OTHERS THEN
            status := 'SQLCODE-005'||SQLERRM; -- Error code for other exceptions
END UPDATE_DEBITCARD_LIMIT;