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

    /**
    *Prints out the main menu selection 
    
    */
    public void menu()
    {
        System.out.println("-------Welcome to your Personal Finance Tracker (Choose one of the options)-------\n"
            + "1. Add Budget\n2. Add Expense\n3. View Summary\n4. Save\n5. Delete(Budget/Expense)\n6. Exit");
    }

    /**
    *Adds transaction to Transactions array list
     */
    public void addTransaction(Transactions t)
    {
        list.add(t);
        System.out.println(t.getType()+ ": "+ t.getName()+ " has been added to tracker.");

    }

    /**
     * Deletes Transaction from Transaction arraylist
     */
    public void deleteTransaction()
    {
        LinkedList<Transactions> completeList = fh.loadData(FILEPATH);
        int choice;
        fh.displayData(FILEPATH);
        System.out.println("Which transaction would you like to delete (1-" + completeList.size() + ")");
        choice = (input.nextInt()-1);
        fh.removeData(choice, FILEPATH);
    }
   
    /**
     * Prints formatted Tracsaction arraylist
     */
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

    /**
     * Sends current Transactions arraylist to FileHandler function uploadData 
     * to save data to JSON file
     */
    public void save()
    {
        fh.uploadData(list,FILEPATH);
    }

    /**
     * Calculates numberical data
     * @param list Transaction arraylist
     * @return Double array containing data at different idex
     */

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
    /**
     * Displays menu for summary for sorting and searching transactions, processes input 
     * and calls appropirate TransactionSearchAnsSort class functions
     * @param advanceChoice Inputed choice from user
     */

    public void displayAdvanceOptions(String advanceChoice)
    {
        int [] choicesArray = new int[3];
        /*
            Idexes of choices array
            0. Search or sort choice
            1.SortChoice
        */
        if(advanceChoice.equals("y"))
            {
                System.out.println("1. Sort");
                System.out.println("2. Search");
                choicesArray[0] = input.nextInt();
            }
        else
        {
            //display for when they don't want to use advance options
        }

        switch (choicesArray[0]) 
        {
            case 1:
                {
                    //Sorting
                    System.out.println("\tSort by");
                    System.out.println("1. Name\n2. Category\n3. Date\n4. Amount");
                    choicesArray[1] = input.nextInt();

                    switch(choicesArray[1])
                    {
                        //Display by name
                        case 1:
                            {
                                sorter.mergeSort(list, SortCriteria.BY_NAME, false);
                                save();
                                fh.displayData("save.json");

                            }
                            
                    }

                }
           
        }
     
    }
    
}

