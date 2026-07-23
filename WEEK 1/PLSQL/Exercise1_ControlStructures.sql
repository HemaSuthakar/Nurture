SET SERVEROUTPUT ON;

DECLARE
    v_name Customers.Name%TYPE;
BEGIN

    -- Scenario 1
    FOR cust IN (
        SELECT CustomerID, DOB
        FROM Customers
    ) LOOP

        IF FLOOR(MONTHS_BETWEEN(SYSDATE, cust.DOB)/12) > 60 THEN

            UPDATE Loans
            SET InterestRate = InterestRate - 1
            WHERE CustomerID = cust.CustomerID;

            DBMS_OUTPUT.PUT_LINE(
                'Interest discount applied to Customer ID '
                || cust.CustomerID
            );

        END IF;

    END LOOP;

    -- Scenario 2
    FOR cust IN (
        SELECT CustomerID, Balance
        FROM Customers
    ) LOOP

        IF cust.Balance > 10000 THEN

            UPDATE Customers
            SET IsVIP = 'TRUE'
            WHERE CustomerID = cust.CustomerID;

            DBMS_OUTPUT.PUT_LINE(
                'Customer ID '
                || cust.CustomerID
                || ' promoted to VIP.'
            );

        ELSE

            UPDATE Customers
            SET IsVIP = 'FALSE'
            WHERE CustomerID = cust.CustomerID;

        END IF;

    END LOOP;

    -- Scenario 3
    FOR loan_rec IN (
        SELECT LoanID,
               CustomerID,
               EndDate
        FROM Loans
        WHERE EndDate BETWEEN SYSDATE
                          AND SYSDATE + 30
    ) LOOP

        SELECT Name
        INTO v_name
        FROM Customers
        WHERE CustomerID = loan_rec.CustomerID;

        DBMS_OUTPUT.PUT_LINE(
            'Reminder: Loan ID '
            || loan_rec.LoanID
            || ' for customer '
            || v_name
            || ' is due on '
            || TO_CHAR(loan_rec.EndDate,'DD-MON-YYYY')
        );

    END LOOP;

    COMMIT;

    DBMS_OUTPUT.PUT_LINE('Exercise 1 completed successfully.');

END;
/