package com.tracker;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;

import java.util.LinkedList;


public class FileHandlerTest {



    private Transactions budgetTransaction;
    private Transactions expenseTransaction;
    private FileHandler fileHandler;
    LinkedList<Transactions> list;


    @Before
    public void setUp() {
        budgetTransaction = new Transactions("Budget", "Monthly Income", "2026-03-23", "Salary", 5000.00);
        expenseTransaction = new Transactions("Expense", "Grocery Shopping", "2026-03-23", "Food", 150.00);
       list = new LinkedList<>();
        list.add(budgetTransaction);
        list.add(expenseTransaction);
         fileHandler = new FileHandler();

    }
    @Test
    public void testUploadData() {
        // Setup
        // Execution
        boolean result = fileHandler.uploadData(list,"saveTest");
        // Verification
        assertTrue(result);
        // Additional assertions can be added to verify the data storage behavior
    }

    @Test
    public void testLoadData() {
        // Setup
        String expectedData = "Sample data";
        fileHandler.uploadData(expectedData);
        // Execution
        List<String> result = fileHandler.loadData();
        // Verification
        assertEquals(1, result.size());
        assertEquals(expectedData, result.get(0));
    }

    @Test
    public void testRemoveData() {
        // Setup
        String dataToRemove = "Sample data";
        fileHandler.uploadData(dataToRemove);
        // Execution
        boolean result = fileHandler.removeData(dataToRemove);
        // Verification
        assertTrue(result);
        assertFalse(fileHandler.loadData().contains(dataToRemove));
    }

    @Test
    public void testSaveDeletedData() {
        // Setup
        String deletedData = "Deleted data";
        fileHandler.uploadData(deletedData);
        fileHandler.removeData(deletedData);
        // Execution
        boolean result = fileHandler.saveDeletedData();
        // Verification
        assertTrue(result);
        // Additional assertions for saved data can be added here
    }

    @Test
    public void testDisplayData() {
        // Setup
        String testData = "Sample data";
        fileHandler.uploadData(testData);
        // Execution
        String displayedData = fileHandler.displayData();
        // Verification
        assertTrue(displayedData.contains(testData));
    }

    // Helper methods tests can be added here
    // Example:
    @Test
    public void testHelperMethod() {
        // Implementation of helper method test
        // Setup, Execution, and Assertions
    }
}