package com.tracker;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.Scanner;



public class FinanceManager implements Serializable
{
    private static final String FILEPATH = "save.json";
     /*Variables */
    private  static LinkedList<Transactions> list = new LinkedList<>();
    FileHandler fh = new FileHandler();
    Scanner input = new Scanner(System.in);


    /*Functions */
    public void menu()
    {
        System.out.println("-------Welcome to your Personal Finance Tracker (Choose one of the options)-------\n"
            + "1. Add Budget\n2. Add Expense\n3. View Summary\n4. Save\n5. Delete(Budget/Expense)\n6. Exit");
    }

    /*Choice options */

    public void addTransaction(Transactions t)
    {
        list.add(t);
        System.out.println(t.getType()+ ": "+ t.getName()+ " has been added to tracker.");

    }

    public void deleteTransaction()
    {
        LinkedList<Transactions> completeList = fh.loadData(FILEPATH);
        int choice;
        fh.displayData(FILEPATH);
        System.out.println("Which transaction would you like to delete (1-" + completeList.size() + ")");
        choice = (input.nextInt()-1);
        fh.removeData(choice, FILEPATH);
    }
   

    public void viewSummary()
    {
        int count = 1;
        System.out.println("-------Transactions-------\n");
        for(Transactions entry: list)
            {
                System.out.printf(count + ". " + entry.getType() + ": (" + entry.getCategory()+ ") \n\t" + 
                entry.getName() + ": $%.2f\n\n",entry.getAmount());
                count++;
            }

    }

    public void save()
    {
        fh.uploadData(list,FILEPATH);
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
