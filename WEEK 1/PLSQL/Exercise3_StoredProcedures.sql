SET SERVEROUTPUT ON;


-- Scenario 1:
-- Process Monthly Interest for Savings Accounts


CREATE OR REPLACE PROCEDURE ProcessMonthlyInterest
AS
BEGIN

    UPDATE Accounts
    SET Balance = Balance + (Balance * 0.01)
    WHERE UPPER(AccountType) = 'SAVINGS';

    COMMIT;

    DBMS_OUTPUT.PUT_LINE(
        SQL%ROWCOUNT ||
        ' savings account(s) updated with 1% interest.'
    );

EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE(
            'Error Processing Interest: ' || SQLERRM
        );
END;
/


-- Scenario 2:
-- Update Employee Bonus by Department


CREATE OR REPLACE PROCEDURE UpdateEmployeeBonus(
    p_Department IN VARCHAR2,
    p_BonusPercent IN NUMBER
)
AS
BEGIN

    UPDATE Employees
    SET Salary = Salary +
                 (Salary * p_BonusPercent / 100)
    WHERE UPPER(Department) =
          UPPER(p_Department);

    COMMIT;

    DBMS_OUTPUT.PUT_LINE(
        SQL%ROWCOUNT ||
        ' employee(s) received bonus in department '
        || p_Department
    );

EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE(
            'Error Updating Bonus: ' || SQLERRM
        );
END;
/


-- Scenario 3:
-- Transfer Funds Between Accounts


CREATE OR REPLACE PROCEDURE TransferFunds(
    p_FromAccount IN NUMBER,
    p_ToAccount   IN NUMBER,
    p_Amount      IN NUMBER
)
AS
    v_Balance NUMBER;
BEGIN

    SELECT Balance
    INTO v_Balance
    FROM Accounts
    WHERE AccountID = p_FromAccount;

    IF v_Balance < p_Amount THEN
        RAISE_APPLICATION_ERROR(
            -20003,
            'Insufficient Balance'
        );
    END IF;

    UPDATE Accounts
    SET Balance = Balance - p_Amount
    WHERE AccountID = p_FromAccount;

    UPDATE Accounts
    SET Balance = Balance + p_Amount
    WHERE AccountID = p_ToAccount;

    COMMIT;

    DBMS_OUTPUT.PUT_LINE(
        'Transfer Completed Successfully.'
    );

EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE(
            'Transfer Failed: ' || SQLERRM
        );
END;
/


-- TEST EXECUTION


-- Scenario 1 Test
BEGIN
    ProcessMonthlyInterest;
END;
/

-- Scenario 2 Test
BEGIN
    UpdateEmployeeBonus('IT', 10);
END;
/

-- Scenario 3 Test
BEGIN
    TransferFunds(1, 2, 100);
END;
/


-- VERIFICATION QUERIES


SELECT AccountID,
       AccountType,
       Balance
FROM Accounts;

SELECT EmployeeID,
       Name,
       Department,
       Salary
FROM Employees;