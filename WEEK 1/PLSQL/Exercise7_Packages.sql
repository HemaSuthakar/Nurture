-- PACKAGE 1 : CustomerManagement


CREATE OR REPLACE PACKAGE CustomerManagement AS

    PROCEDURE AddCustomer(
        p_CustomerID NUMBER,
        p_Name VARCHAR2,
        p_DOB DATE,
        p_Balance NUMBER
    );

    PROCEDURE UpdateCustomer(
        p_CustomerID NUMBER,
        p_Name VARCHAR2
    );

    FUNCTION GetCustomerBalance(
        p_CustomerID NUMBER
    ) RETURN NUMBER;

END CustomerManagement;
/

CREATE OR REPLACE PACKAGE BODY CustomerManagement AS

    PROCEDURE AddCustomer(
        p_CustomerID NUMBER,
        p_Name VARCHAR2,
        p_DOB DATE,
        p_Balance NUMBER
    )
    IS
    BEGIN
        INSERT INTO Customers
        (
            CustomerID,
            Name,
            DOB,
            Balance,
            LastModified,
            IsVIP
        )
        VALUES
        (
            p_CustomerID,
            p_Name,
            p_DOB,
            p_Balance,
            SYSDATE,
            'FALSE'
        );
    END AddCustomer;

    PROCEDURE UpdateCustomer(
        p_CustomerID NUMBER,
        p_Name VARCHAR2
    )
    IS
    BEGIN
        UPDATE Customers
        SET Name = p_Name
        WHERE CustomerID = p_CustomerID;
    END UpdateCustomer;

    FUNCTION GetCustomerBalance(
        p_CustomerID NUMBER
    )
    RETURN NUMBER
    IS
        v_Balance NUMBER;
    BEGIN
        SELECT Balance
        INTO v_Balance
        FROM Customers
        WHERE CustomerID = p_CustomerID;

        RETURN v_Balance;

    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            RETURN 0;
    END GetCustomerBalance;

END CustomerManagement;
/


-- PACKAGE 2 : EmployeeManagement


CREATE OR REPLACE PACKAGE EmployeeManagement AS

    PROCEDURE HireEmployee(
        p_EmployeeID NUMBER,
        p_Name VARCHAR2,
        p_Position VARCHAR2,
        p_Salary NUMBER,
        p_Department VARCHAR2,
        p_HireDate DATE
    );

    PROCEDURE UpdateEmployee(
        p_EmployeeID NUMBER,
        p_Salary NUMBER
    );

    FUNCTION CalculateAnnualSalary(
        p_EmployeeID NUMBER
    ) RETURN NUMBER;

END EmployeeManagement;
/

CREATE OR REPLACE PACKAGE BODY EmployeeManagement AS

    PROCEDURE HireEmployee(
        p_EmployeeID NUMBER,
        p_Name VARCHAR2,
        p_Position VARCHAR2,
        p_Salary NUMBER,
        p_Department VARCHAR2,
        p_HireDate DATE
    )
    IS
    BEGIN
        INSERT INTO Employees
        (
            EmployeeID,
            Name,
            Position,
            Salary,
            Department,
            HireDate
        )
        VALUES
        (
            p_EmployeeID,
            p_Name,
            p_Position,
            p_Salary,
            p_Department,
            p_HireDate
        );
    END HireEmployee;

    PROCEDURE UpdateEmployee(
        p_EmployeeID NUMBER,
        p_Salary NUMBER
    )
    IS
    BEGIN
        UPDATE Employees
        SET Salary = p_Salary
        WHERE EmployeeID = p_EmployeeID;
    END UpdateEmployee;

    FUNCTION CalculateAnnualSalary(
        p_EmployeeID NUMBER
    )
    RETURN NUMBER
    IS
        v_Salary NUMBER;
    BEGIN
        SELECT Salary
        INTO v_Salary
        FROM Employees
        WHERE EmployeeID = p_EmployeeID;

        RETURN v_Salary * 12;

    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            RETURN 0;
    END CalculateAnnualSalary;

END EmployeeManagement;
/


-- PACKAGE 3 : AccountOperations


CREATE OR REPLACE PACKAGE AccountOperations AS

    PROCEDURE OpenAccount(
        p_AccountID NUMBER,
        p_CustomerID NUMBER,
        p_AccountType VARCHAR2,
        p_Balance NUMBER
    );

    PROCEDURE CloseAccount(
        p_AccountID NUMBER
    );

    FUNCTION GetTotalBalance(
        p_CustomerID NUMBER
    ) RETURN NUMBER;

END AccountOperations;
/

CREATE OR REPLACE PACKAGE BODY AccountOperations AS

    PROCEDURE OpenAccount(
        p_AccountID NUMBER,
        p_CustomerID NUMBER,
        p_AccountType VARCHAR2,
        p_Balance NUMBER
    )
    IS
    BEGIN
        INSERT INTO Accounts
        (
            AccountID,
            CustomerID,
            AccountType,
            Balance,
            LastModified
        )
        VALUES
        (
            p_AccountID,
            p_CustomerID,
            p_AccountType,
            p_Balance,
            SYSDATE
        );
    END OpenAccount;

    PROCEDURE CloseAccount(
        p_AccountID NUMBER
    )
    IS
    BEGIN
        DELETE FROM Accounts
        WHERE AccountID = p_AccountID;
    END CloseAccount;

    FUNCTION GetTotalBalance(
        p_CustomerID NUMBER
    )
    RETURN NUMBER
    IS
        v_Total NUMBER;
    BEGIN
        SELECT NVL(SUM(Balance),0)
        INTO v_Total
        FROM Accounts
        WHERE CustomerID = p_CustomerID;

        RETURN v_Total;
    END GetTotalBalance;

END AccountOperations;
/


-- TESTING PACKAGE 1


BEGIN
    CustomerManagement.AddCustomer(
        10,
        'David Wilson',
        DATE '1995-08-15',
        8000
    );
END;
/

SELECT CustomerManagement.GetCustomerBalance(10)
AS CUSTOMER_BALANCE
FROM DUAL;


-- TESTING PACKAGE 2


BEGIN
    EmployeeManagement.HireEmployee(
        10,
        'Chris Evans',
        'Analyst',
        50000,
        'Finance',
        SYSDATE
    );
END;
/

SELECT EmployeeManagement.CalculateAnnualSalary(10)
AS ANNUAL_SALARY
FROM DUAL;


-- TESTING PACKAGE 3


BEGIN
    AccountOperations.OpenAccount(
        10,
        10,
        'Savings',
        3000
    );
END;
/

SELECT AccountOperations.GetTotalBalance(10)
AS TOTAL_BALANCE
FROM DUAL;


-- VERIFY DATA


SELECT * FROM Customers WHERE CustomerID = 10;

SELECT * FROM Employees WHERE EmployeeID = 10;

SELECT * FROM Accounts WHERE CustomerID = 10;