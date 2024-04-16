CREATE OR REPLACE PROCEDURE DELETE_PAYEE (
    p_payee_id IN MYBANK_APP_PAYEE.payee_id%TYPE,
    p_sender_account_number IN MYBANK_APP_PAYEE.sender_account_number%TYPE,
    p_payee_account_number IN MYBANK_APP_PAYEE.payee_account_number%TYPE,
    p_payee_name IN MYBANK_APP_PAYEE.payee_name%TYPE
) AS
    v_count NUMBER;
BEGIN
    -- Check if the payee exists
    SELECT COUNT(*)
    INTO v_count
    FROM MYBANK_APP_PAYEE
    WHERE payee_id = p_payee_id
    AND sender_account_number = p_sender_account_number
    AND payee_account_number = p_payee_account_number
    AND payee_name = p_payee_name;
 
    IF v_count = 0 THEN
        -- Payee not found, raise an exception
        RAISE_APPLICATION_ERROR(-20002, 'Payee not found');
    ELSE
        -- Delete the payee
        DELETE FROM MYBANK_APP_PAYEE
        WHERE payee_id = p_payee_id
        AND sender_account_number = p_sender_account_number
        AND payee_account_number = p_payee_account_number
        AND payee_name = p_payee_name;
    END IF;
END DELETE_PAYEE;
/
 
execute DELETE_PAYEE(104,225792454013,145792454013,'Avinash');


CREATE OR REPLACE PROCEDURE UPDATE_DEBITCARD_LIMIT (
    p_debitcard_number IN MYBANK_APP_DEBITCARD.debitcard_number%TYPE,
    p_domestic_limit IN MYBANK_APP_DEBITCARD.debitcard_domestic_limit%TYPE,
    p_international_limit IN MYBANK_APP_DEBITCARD.debitcard_international_limit%TYPE
   ) AS    
	v_count NUMBER;
BEGIN
	-- Check if the debit card exists
	SELECT COUNT(*)
    	INTO v_count
    	FROM MYBANK_APP_DEBITCARD d
    	JOIN MYBANK_APP_CUSTOMER c ON c.customer_id = d.customer_id
    	JOIN MYBANK_APP_ACCOUNT a ON a.customer_id = c.customer_id
    	WHERE c.customer_id = d.customer_id
          AND a.account_number = d.account_number
          AND c.customer_status = 'active'
          AND a.account_status = 'active'
          AND d.debitcard_status = 'active'
          AND d.debitcard_number = p_debitcard_number;

 
    IF v_count = 0 THEN
	-- Debit card not found, raise an exception       
	RAISE_APPLICATION_ERROR(-20002, 'Debit card not found');
    ELSE
	-- Update the debit card limit
        UPDATE mybank_app_debitcard d
        SET d.debitcard_domestic_limit = p_domestic_limit,
        d.debitcard_international_limit = p_international_limit
        WHERE EXISTS (
        SELECT *
        FROM mybank_app_customer c
        JOIN mybank_app_account a ON c.customer_id = a.customer_id
        WHERE c.customer_id = d.customer_id
          AND a.account_number = d.account_number
          AND c.customer_status = 'active'
          AND a.account_status = 'active'
          AND d.debitcard_status = 'active'
          AND d.debitcard_number = p_debitcard_number
    );

    END IF;
END UPDATE_DEBITCARD_LIMIT;
/