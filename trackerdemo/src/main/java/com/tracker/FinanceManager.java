package com.tracker;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;



public class FinanceManager implements Serializable
{
     /*Variables */
    private LinkedHashMap<String, Transactions> map = new LinkedHashMap<>();
    FileHandler fh = new FileHandler();


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
        fh.uploadData(map);
    }

    public void viewLastSave()
    {
        // int count =1;
        // LinkedHashMap<String,Transactions> lastSave = fh.loadData("save.json");
        // for(Map.Entry<String,Transactions> entry: lastSave.entrySet())
        //     {
        //         System.out.printf(count + ". " + entry.getValue().getType() + ": (" + entry.getValue().getCategory()+ ") \n\t" + 
        //         entry.getValue().getName() + ": $%.2f\n\n",entry.getValue().getAmount());
        //         count++;
        //     }
        fh.displayData("save.json");
    }
    public void summaryMenu()
    {
        
    }
    
}
