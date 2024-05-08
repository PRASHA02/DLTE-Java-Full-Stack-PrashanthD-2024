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
        -- Check if the debit card exists
        SELECT COUNT(*) INTO v_count
        FROM mybank_app_debitcard
        WHERE account_number = p_account_number;


        -- Check if the debit card exists and is active
        SELECT debitcard_status INTO v_debitcard_status
        FROM mybank_app_debitcard 
        WHERE account_number = p_account_number;

        -- Update the debit card limit
        IF  v_debitcard_status = 'active' THEN
            IF v_count > 0 THEN
                UPDATE mybank_app_debitcard d
                SET d.debitcard_domestic_limit = p_domestic_limit,
                    d.debitcard_international_limit = p_international_limit
                WHERE d.account_number = p_account_number;
                COMMIT;
                status := 'SQLCODE-000';
            END IF;
        ELSE 
            status := 'SQLCODE-003'; -- Error code for "Debit card limit update failed"
        END IF;
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            status := 'SQLCODE-004'; -- Error code for "No data found"
        WHEN OTHERS THEN
            status := 'SQLCODE-005'||SQLERRM; -- Error code for other exceptions
END UPDATE_DEBITCARD_LIMIT;