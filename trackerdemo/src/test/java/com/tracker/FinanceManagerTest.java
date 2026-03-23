package com.tracker;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FinanceManagerTest {

    private FinanceManager financeManager;

    @BeforeEach
    public void setUp() {
        financeManager = new FinanceManager();
    }

    @Test
    public void testAddTransaction() {
        financeManager.addTransaction(100, "Income");
        assertEquals(1, financeManager.getTransactions().size(), "Transaction count should be 1.");
        assertEquals(100, financeManager.getTransactions().get(0).getAmount(), "Transaction amount should match.");
    }

    @Test
    public void testCalculateMoneySummary() {
        financeManager.addTransaction(100, "Income");
        financeManager.addTransaction(50, "Expense");
        double summary = financeManager.calculateMoneySummary();
        assertEquals(50, summary, "Money summary should be 50.");
    }

    @Test
    public void testViewSummary() {
        financeManager.addTransaction(200, "Income");
        financeManager.addTransaction(80, "Expense");
        String summary = financeManager.viewSummary();
        assertTrue(summary.contains("Income: 200"), "Summary should contain income information.");
        assertTrue(summary.contains("Expense: 80"), "Summary should contain expense information.");
    }

    @Test
    public void testMenu() {
        String menuOutput = financeManager.menu();
        assertTrue(menuOutput.contains("1. Add Transaction"), "Menu should contain option to add transaction.");
        assertTrue(menuOutput.contains("2. View Summary"), "Menu should contain option to view summary.");
    }
}