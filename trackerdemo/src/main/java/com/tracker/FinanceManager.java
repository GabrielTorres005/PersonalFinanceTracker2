package com.tracker;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;



public class FinanceManager implements Serializable
{
     /*Variables */
    private LinkedHashMap<String, Transactions> map = new LinkedHashMap<>();


    /*Functions */
    public void menu()
    {
        System.out.println("-------Welcome to your Personal Finance Tracker (Choose one of the options)-------\n"
            + "1. Add Budget\n2. Add Expense\n3. View Summary\n4. Save\n5. Exit");
    }

    /*Choice options */

    public void addTransaction(Transactions t)
    {
        map.put(t.getType(), t);
        System.out.println(t.getType()+ ": "+ t.getName()+ " has been added to tracker.");

    }

    public void viewSummary()
    {
        int count = 1;
        System.out.println("-------Transactions-------\n");
        for(Map.Entry<String,Transactions> entry: map.entrySet())
            {
                System.out.printf(count + ". " + entry.getValue().getType() + ": (" + entry.getValue().getCategory()+ ") \n\t" + 
                entry.getValue().getName() + ": $%.2f\n\n",entry.getValue().getAmount());
                count++;
            }

    }

    public void save()
    {

    }

    public void summaryMenu()
    {
        
    }
    
}
