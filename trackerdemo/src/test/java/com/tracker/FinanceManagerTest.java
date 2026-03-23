package com.tracker;

import org.junit.Before;
import org.junit.Test;
import java.util.LinkedList;

import static org.junit.Assert.*;

public class FinanceManagerTest {
    
    private FinanceManager financeManager;
    private Transactions budgetTransaction;
    private Transactions expenseTransaction;
    
    @Before
    public void setUp() {
        financeManager = new FinanceManager();
        budgetTransaction = new Transactions("Budget", "Monthly Income", "2026-03-23", "Salary", 5000.00);
        expenseTransaction = new Transactions("Expense", "Grocery Shopping", "2026-03-23", "Food", 150.00);
    }
    
    // Test addTransaction method
    @Test
    public void testAddTransaction() {
        financeManager.addTransaction(budgetTransaction);
        financeManager.addTransaction(expenseTransaction);
        // If no exception is thrown and method completes, test passes
        assertTrue(true);
    }
    
    @Test
    public void testAddTransactionWithBudget() {
        Transactions budget = new Transactions("Budget", "Paycheck", "2026-03-23", "Income", 3000.00);
        financeManager.addTransaction(budget);
        assertTrue(true);
    }
    
    @Test
    public void testAddTransactionWithExpense() {
        Transactions expense = new Transactions("Expense", "Gas", "2026-03-23", "Transportation", 50.00);
        financeManager.addTransaction(expense);
        assertTrue(true);
    }
    
    // Test calculateMoneySummary method
    @Test
    public void testCalculateMoneySummaryWithEmptyList() {
        LinkedList<Transactions> emptyList = new LinkedList<>();
        double[] result = financeManager.calculateMoneySummary(emptyList);
        
        assertEquals(0.0, result[0], 0.01); // Total budget
        assertEquals(0.0, result[1], 0.01); // Total expense
        assertEquals(0.0, result[2], 0.01); // Total balance
    }
    
    @Test
    public void testCalculateMoneySummaryWithBudgetOnly() {
        LinkedList<Transactions> list = new LinkedList<>();
        list.add(new Transactions("Budget", "Income", "2026-03-23", "Salary", 5000.00));
        list.add(new Transactions("Budget", "Bonus", "2026-03-23", "Bonus", 1000.00));
        
        double[] result = financeManager.calculateMoneySummary(list);
        
        assertEquals(6000.0, result[0], 0.01); // Total budget
        assertEquals(0.0, result[1], 0.01);   // Total expense
        assertEquals(6000.0, result[2], 0.01); // Total balance
    }
    
    @Test
    public void testCalculateMoneySummaryWithExpenseOnly() {
        LinkedList<Transactions> list = new LinkedList<>();
        list.add(new Transactions("Expense", "Food", "2026-03-23", "Groceries", 150.00));
        list.add(new Transactions("Expense", "Gas", "2026-03-23", "Transportation", 50.00));
        
        double[] result = financeManager.calculateMoneySummary(list);
        
        assertEquals(0.0, result[0], 0.01);   // Total budget
        assertEquals(200.0, result[1], 0.01); // Total expense
        assertEquals(-200.0, result[2], 0.01); // Total balance (negative)
    }
    
    @Test
    public void testCalculateMoneySummaryWithMixedTransactions() {
        LinkedList<Transactions> list = new LinkedList<>();
        list.add(new Transactions("Budget", "Salary", "2026-03-23", "Income", 5000.00));
        list.add(new Transactions("Expense", "Rent", "2026-03-23", "Housing", 1200.00));
        list.add(new Transactions("Expense", "Food", "2026-03-23", "Groceries", 300.00));
        
        double[] result = financeManager.calculateMoneySummary(list);
        
        assertEquals(5000.0, result[0], 0.01);  // Total budget
        assertEquals(1500.0, result[1], 0.01);  // Total expense
        assertEquals(3500.0, result[2], 0.01);  // Total balance
    }
    
    @Test
    public void testCalculateMoneySummaryReturnsArray() {
        LinkedList<Transactions> list = new LinkedList<>();
        list.add(budgetTransaction);
        
        double[] result = financeManager.calculateMoneySummary(list);
        
        assertNotNull(result);
        assertEquals(3, result.length); // Should return array of size 3
    }
    
    // Test menu method
    @Test
    public void testMenuPrintsMenu() {
        // Menu method prints to console, so we just verify it doesn't throw an exception
        try {
            financeManager.menu();
            assertTrue(true);
        } catch (Exception e) {
            fail("Menu method should not throw an exception");
        }
    }
    
    // Test viewSummary method
    @Test
    public void testViewSummaryWithNoTransactions() {
        try {
            financeManager.viewSummary();
            assertTrue(true);
        } catch (Exception e) {
            fail("ViewSummary should not throw an exception with empty list");
        }
    }
    
    @Test
    public void testViewSummaryWithTransactions() {
        financeManager.addTransaction(budgetTransaction);
        financeManager.addTransaction(expenseTransaction);
        
        try {
            financeManager.viewSummary();
            assertTrue(true);
        } catch (Exception e) {
            fail("ViewSummary should not throw an exception");
        }
    }
    
    // Test save method
    @Test
    public void testSaveMethod() {
        financeManager.addTransaction(budgetTransaction);
        
        try {
            financeManager.save();
            assertTrue(true);
        } catch (Exception e) {
            fail("Save method should not throw an exception");
        }
    }
    
    @Test
    public void testSaveWithMultipleTransactions() {
        financeManager.addTransaction(budgetTransaction);
        financeManager.addTransaction(expenseTransaction);
        
        try {
            financeManager.save();
            assertTrue(true);
        } catch (Exception e) {
            fail("Save method should handle multiple transactions");
        }
    }
}