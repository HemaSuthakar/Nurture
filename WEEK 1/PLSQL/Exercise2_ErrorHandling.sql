SET SERVEROUTPUT ON;


-- Scenario 1 : SafeTransferFunds


CREATE OR REPLACE PROCEDURE SafeTransferFunds(
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
            -20001,
            'Insufficient Funds'
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
        'Funds transferred successfully.'
    );

EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE(
            'Transfer Failed: ' || SQLERRM
        );
END;
/


-- Scenario 2 : UpdateSalary


CREATE OR REPLACE PROCEDURE UpdateSalary(
    p_EmployeeID IN NUMBER,
    p_Percentage IN NUMBER
)
AS
BEGIN

    UPDATE Employees
    SET Salary = Salary + (Salary * p_Percentage / 100)
    WHERE EmployeeID = p_EmployeeID;

    IF SQL%ROWCOUNT = 0 THEN
        RAISE_APPLICATION_ERROR(
            -20002,
            'Employee ID does not exist'
        );
    END IF;

    COMMIT;

    DBMS_OUTPUT.PUT_LINE(
        'Salary Updated Successfully'
    );

EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE(
            'Error: ' || SQLERRM
        );
END;
/


-- Scenario 3 : AddNewCustomer


CREATE OR REPLACE PROCEDURE AddNewCustomer(
    p_CustomerID IN NUMBER,
    p_Name       IN VARCHAR2,
    p_DOB        IN DATE,
    p_Balance    IN NUMBER
)
AS
BEGIN

    INSERT INTO Customers
    (
        CustomerID,
        Name,
        DOB,
        Balance,
        LastModified
    )
    VALUES
    (
        p_CustomerID,
        p_Name,
        p_DOB,
        p_Balance,
        SYSDATE
    );

    COMMIT;

    DBMS_OUTPUT.PUT_LINE(
        'Customer Added Successfully'
    );

EXCEPTION
    WHEN DUP_VAL_ON_INDEX THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE(
            'Error: Customer ID already exists.'
        );

    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE(
            'Error: ' || SQLERRM
        );
END;
/


-- TEST EXECUTION


BEGIN
    SafeTransferFunds(1, 2, 100);
END;
/

BEGIN
    UpdateSalary(1, 10);
END;
/

BEGIN
    AddNewCustomer(
        3,
        'Robert King',
        TO_DATE('1988-04-10','YYYY-MM-DD'),
        5000
    );
END;
/