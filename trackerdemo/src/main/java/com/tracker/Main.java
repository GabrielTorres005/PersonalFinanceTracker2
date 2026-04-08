package com.tracker;
import java.util.Scanner;
import java.time.LocalDate;



public class Main {
    public static void main(String[] args) 
    {
         /*Variable */
        Scanner input = new Scanner(System.in);
        LocalDate today = LocalDate.now();
        FinanceManager manager = new FinanceManager();
        FileHandler fh = new FileHandler();

        /*Primitive Variables */
        int choice;
        double amount;
        String name, category;
        boolean status = true;

        
        
        while(status)
            {

                //Prints menu and takes User Choice
                manager.menu();
                choice = input.nextInt();

                
                //Processes Input
                switch(choice)
                {
                    //Add Budget
                    case 1:
                        
                        System.out.println("How much would you like to add to the budget: ");
                        amount = input.nextDouble();
                        input.nextLine();
                        System.out.println("What budget is this for (eg. Overral, Groceries, Utilities): ");
                        category = input.nextLine();
                        System.out.println("What would you like to name this budget entry: ");
                        name = input.nextLine();
                        
                        
                        Transactions budget = new Transactions("Budget", name, today.toString(), category, amount);
                        manager.addTransaction(budget);

                        break;
                    //Add Expense
                    case 2:
                        System.out.println("How much would you like to add to expenses: ");
                        amount = input.nextDouble();
                        input.nextLine();
                        System.out.println("What budget is this for (eg. Overral, Groceries, Utilities): ");
                        category = input.nextLine();
                        System.out.println("What would you to name this expense entry: ");
                        name = input.nextLine();
                        
                        
                        
                        Transactions expense = new Transactions("Expense", name, today.toString(), category, amount);
                        manager.addTransaction(expense);
                        break;
                    //View Summary
                    case 3:
                        fh.displayData("save.json");
                        input.nextLine();
                        System.out.println("Advance options?(Y/N): ");
                        String advanceInput = input.nextLine();
                        manager.displayAdvanceOptions(advanceInput);
                        break;
                    //Save
                    case 4:
                        manager.save();
                        break;
                    //Delete(Budget/Expense)
                    case 5:
                        manager.deleteTransaction();

                        break;

                    //Exit
                    case 6:
                        status = false;
            }
        }
        //Scanner close
        input.close();

        
    }
}