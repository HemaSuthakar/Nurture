-- Scenario 1: Calculate Customer Age


CREATE OR REPLACE FUNCTION CalculateAge(
    p_DOB IN DATE
)
RETURN NUMBER
IS
BEGIN
    RETURN FLOOR(MONTHS_BETWEEN(SYSDATE, p_DOB) / 12);
END;
/


-- Scenario 2: Calculate Monthly Installment


CREATE OR REPLACE FUNCTION CalculateMonthlyInstallment(
    p_LoanAmount   IN NUMBER,
    p_InterestRate IN NUMBER,
    p_Years        IN NUMBER
)
RETURN NUMBER
IS
    v_MonthlyRate NUMBER;
    v_Months NUMBER;
BEGIN

    v_MonthlyRate := (p_InterestRate / 100) / 12;
    v_Months := p_Years * 12;

    IF v_MonthlyRate = 0 THEN
        RETURN ROUND(p_LoanAmount / v_Months, 2);
    ELSE
        RETURN ROUND(
            (
                p_LoanAmount *
                (
                    v_MonthlyRate *
                    POWER(1 + v_MonthlyRate, v_Months)
                )
            ) /
            (
                POWER(1 + v_MonthlyRate, v_Months) - 1
            ),
            2
        );
    END IF;

END;
/


-- Scenario 3: Check Sufficient Balance


CREATE OR REPLACE FUNCTION HasSufficientBalance(
    p_AccountID IN NUMBER,
    p_Amount IN NUMBER
)
RETURN VARCHAR2
IS
    v_Balance NUMBER;
BEGIN

    SELECT Balance
    INTO v_Balance
    FROM Accounts
    WHERE AccountID = p_AccountID;

    IF v_Balance >= p_Amount THEN
        RETURN 'TRUE';
    ELSE
        RETURN 'FALSE';
    END IF;

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        RETURN 'ACCOUNT NOT FOUND';
END;
/


-- TEST QUERIES


-- Test Scenario 1
SELECT CalculateAge(
       TO_DATE('1985-05-15','YYYY-MM-DD')
       ) AS AGE
FROM DUAL;

-- Test Scenario 2
SELECT CalculateMonthlyInstallment(
       5000,
       5,
       5
       ) AS EMI
FROM DUAL;

-- Test Scenario 3
SELECT HasSufficientBalance(
       1,
       500
       ) AS RESULT
FROM DUAL;