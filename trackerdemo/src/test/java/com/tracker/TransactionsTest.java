package com.tracker;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TransactionsTest {
    private Transactions transaction;

    @Before
    public void setUp() {
        transaction = new Transactions("Budget", "Salary", "2026-03-20", "Income", 5000.0);
    }

    @Test
    public void testTransactionConstructor() {
        assertNotNull(transaction);
        assertEquals("Budget", transaction.getType());
        assertEquals("Salary", transaction.getName());
        assertEquals("2026-03-20", transaction.getDate());
        assertEquals("Income", transaction.getCategory());
        assertEquals(5000.0, transaction.getAmount(), 0.01);
    }

    @Test
    public void testSetAmount() {
        transaction.setAmount(6000);
        assertEquals(6000.0, transaction.getAmount(), 0.01);
    }

    @Test
    public void testSetType() {
        transaction.setType("Expense");
        assertEquals("Expense", transaction.getType());
    }

    @Test
    public void testSetName() {
        transaction.setName("Bonus");
        assertEquals("Bonus", transaction.getName());
    }

    @Test
    public void testSetDate() {
        transaction.setDate("2026-04-01");
        assertEquals("2026-04-01", transaction.getDate());
    }

    @Test
    public void testSetCategory() {
        transaction.setCategory("Bonus");
        assertEquals("Bonus", transaction.getCategory());
    }

    @Test
    public void testToString() {
        String result = transaction.toString();
        assertTrue(result.contains("Budget"));
        assertTrue(result.contains("Salary"));
        assertTrue(result.contains("Income"));
    }
}