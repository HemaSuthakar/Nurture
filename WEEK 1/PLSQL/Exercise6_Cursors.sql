-- Scenario 1:
-- Generate Monthly Statements


DECLARE

    CURSOR c_transactions IS
        SELECT t.TransactionID,
               t.AccountID,
               t.TransactionDate,
               t.Amount,
               t.TransactionType,
               c.Name
        FROM Transactions t
        JOIN Accounts a
            ON t.AccountID = a.AccountID
        JOIN Customers c
            ON a.CustomerID = c.CustomerID
        WHERE EXTRACT(MONTH FROM t.TransactionDate)
              = EXTRACT(MONTH FROM SYSDATE)
          AND EXTRACT(YEAR FROM t.TransactionDate)
              = EXTRACT(YEAR FROM SYSDATE);

BEGIN

    DBMS_OUTPUT.PUT_LINE(
        'MONTHLY CUSTOMER STATEMENTS'
    );

    FOR rec IN c_transactions LOOP

        DBMS_OUTPUT.PUT_LINE(
            'Customer: ' || rec.Name ||
            ' | Transaction ID: ' || rec.TransactionID ||
            ' | Type: ' || rec.TransactionType ||
            ' | Amount: ' || rec.Amount ||
            ' | Date: ' || TO_CHAR(rec.TransactionDate,'DD-MON-YYYY')
        );

    END LOOP;

END;
/

-- Scenario 2:
-- Apply Annual Maintenance Fee


DECLARE

    CURSOR c_accounts IS
        SELECT AccountID,
               Balance
        FROM Accounts;

    v_fee NUMBER := 100;

BEGIN

    FOR rec IN c_accounts LOOP

        UPDATE Accounts
        SET Balance = Balance - v_fee
        WHERE AccountID = rec.AccountID;

        DBMS_OUTPUT.PUT_LINE(
            'Annual fee deducted from Account '
            || rec.AccountID
        );

    END LOOP;

    COMMIT;

END;
/

-- Scenario 3:
-- Update Loan Interest Rates


DECLARE

    CURSOR c_loans IS
        SELECT LoanID,
               InterestRate
        FROM Loans;

BEGIN

    FOR rec IN c_loans LOOP

        UPDATE Loans
        SET InterestRate = InterestRate + 0.5
        WHERE LoanID = rec.LoanID;

        DBMS_OUTPUT.PUT_LINE(
            'Loan '
            || rec.LoanID
            || ' interest rate updated.'
        );

    END LOOP;

    COMMIT;

END;
/

-- VERIFICATION QUERIES


SELECT AccountID,
       Balance
FROM Accounts;

SELECT LoanID,
       InterestRate
FROM Loans;