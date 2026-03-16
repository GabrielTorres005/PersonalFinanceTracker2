package main.java.com.tracker;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

import PersonalFinanceTracker.Transaction;

import java.io.Serializable;


public class FinanceManager implements Serializable
{
     /*Variables */
    private LinkedHashMap<String, Transaction> map = new LinkedHashMap<>();


    /*Functions */
    public void menu()
    {
        System.out.println("-------Welcome to your Personal Finance Tracker (Choose one of the options)-------\n"
            + "1. Add Budget\n2. Add Expense\n3. View Summary\n4. Save\n5. Exit");
    }

    /*Choice options */

    public void addTransaction(Transaction t)
    {
        map.put(t.getType(), t);
        System.out.println(t.getType()+ ": "+ t.getName()+ " has been added to tracker.");

    }

    public void viewSummary()
    {
        int count = 1;
        System.out.println("-------Transactions-------\n");
        for(Map.Entry<String,Transaction> entry: map.entrySet())
            {
                System.out.printf(count + ". " + entry.getValue().getType() + ": (" + entry.getValue().getCategory()+ ") \n\t" + 
                entry.getValue().getName() + ": $%.2f\n\n",entry.getValue().getAmount());
                count++;
            }

    }

    public void save(LinkedHashMap<String,Transaction> savemap)
    {
        try(ObjectOutputStream write = new ObjectOutputStream(new FileOutputStream("user.ser")))
        {
            
        }
        catch(IOException e)
        {
            System.out.println(e.getMessage());
        }

    }

    public void summaryMenu()
    {
        
    }
    
}
