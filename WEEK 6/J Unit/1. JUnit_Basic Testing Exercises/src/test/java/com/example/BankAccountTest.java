package com.example;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Exercise 4: Arrange-Act-Assert (AAA) Pattern, Test Fixtures,
 * Setup and Teardown Methods in JUnit.
 *
 * @Before runs before every test method (setup / fixture creation).
 * @After runs after every test method (teardown / cleanup).
 */
public class BankAccountTest {

    private BankAccount account;

    @Before
    public void setUp() {
        // This runs before each test - creates a fresh fixture
        account = new BankAccount(100.0);
        System.out.println("Setting up: new BankAccount with balance 100.0");
    }

    @After
    public void tearDown() {
        // This runs after each test - cleans up the fixture
        account = null;
        System.out.println("Tearing down: account reference cleared");
    }

    @Test
    public void testDeposit() {
        // Arrange
        double depositAmount = 50.0;

        // Act
        account.deposit(depositAmount);

        // Assert
        assertEquals(150.0, account.getBalance(), 0.0001);
    }

    @Test
    public void testWithdraw() {
        // Arrange
        double withdrawAmount = 30.0;

        // Act
        account.withdraw(withdrawAmount);

        // Assert
        assertEquals(70.0, account.getBalance(), 0.0001);
    }

    @Test(expected = IllegalStateException.class)
    public void testWithdrawInsufficientFunds() {
        // Arrange
        double withdrawAmount = 1000.0;

        // Act
        account.withdraw(withdrawAmount);

        // Assert is handled by the "expected" attribute
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDepositNegativeAmount() {
        // Arrange
        double depositAmount = -10.0;

        // Act
        account.deposit(depositAmount);

        // Assert is handled by the "expected" attribute
    }
}
