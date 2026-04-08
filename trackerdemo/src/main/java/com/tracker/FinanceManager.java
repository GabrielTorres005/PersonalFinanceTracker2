package com.tracker;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.Scanner;

import com.tracker.TransactionSearchAndSort.SortCriteria;


public class FinanceManager implements Serializable
{
    //Variables
    private static final String FILEPATH = "save.json";
    private static  LinkedList<Transactions> list = new LinkedList<>();
    FileHandler fh = new FileHandler();
    Scanner input = new Scanner(System.in);
    TransactionSearchAndSort sorter;

    //Prints Menu
    public void menu()
    {
        System.out.println("-------Welcome to your Personal Finance Tracker (Choose one of the options)-------\n"
            + "1. Add Budget\n2. Add Expense\n3. View Summary\n4. Save\n5. Delete(Budget/Expense)\n6. Exit");
    }

    //Adds Transaction
    public void addTransaction(Transactions t)
    {
        list.add(t);
        System.out.println(t.getType()+ ": "+ t.getName()+ " has been added to tracker.");

    }
    //Deletes Transaction
    public void deleteTransaction()
    {
        LinkedList<Transactions> completeList = fh.loadData(FILEPATH);
        int choice;
        fh.displayData(FILEPATH);
        System.out.println("Which transaction would you like to delete (1-" + completeList.size() + ")");
        choice = (input.nextInt()-1);
        fh.removeData(choice, FILEPATH);
    }
   
    //Displays Summary of curent list
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
    //Saves file to JSON
    public void save()
    {
        fh.uploadData(list,FILEPATH);
    }

    public double[] calculateMoneySummary(LinkedList <Transactions> list)
    {
        double totalbudget = 0.0;
        double totalExpense = 0.0;
        double totalBalance = 0.0;

        
        for(Transactions entry: list)
            {
                String type = entry.getType();
                if(type.equals("Budget"))
                {
                    totalbudget += entry.getAmount();
                }
                if (type.equals("Expense"))
                {
                    totalExpense += entry.getAmount();
                }
            }
        totalBalance = totalbudget - totalExpense;

        double[] calculationsArayy = {totalbudget, totalExpense, totalBalance}; // 0 = Budget, 1 = Expense, 2 = Balance
        
        return calculationsArayy;
    }
    

    private void displayAdvanceOptions()
    {
        System.out.println("Advance options?(Y/N): ");
        String choice = input.nextLine();
        /*
            Idexes of choices array
            0. Search or sort choice
            1.SortChoice
        */
        String [] choicesArray = new String[3];

        if(choice == "y")
            {
                System.out.println("1. Sort");
                System.out.println("2. Search");
                choicesArray[0] = input.nextLine();
            }
        else
        {

        }

        switch (choicesArray[0]) 
        {
            case "y":
                {
                    System.out.println("\tSort by");
                    System.out.println("1. Name\n2. Category\n3. Date\n4. Amount");
                    choicesArray[1] = input.nextLine();

                    switch(Integer.parseInt(choicesArray[1]))
                    {
                        case 1:
                            {
                                sorter.mergeSort(list,sorter.Sort)
                            }
                            
                    }

                }
           
        }
     
    }
    
}

