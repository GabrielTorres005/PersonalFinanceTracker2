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

        int choice;
        double amount;
        String name, category;
        boolean status = true;

        
        
        while(status)
            {

                manager.menu();
                choice = input.nextInt();

                /*Process choice */
        

                switch(choice)
                {
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
                    case 3:
                        fh.displayData("save.json");
                        break;
                    case 4:
                        manager.save();
                        break;
                    case 5:
                        manager.deleteTransaction();
                        break;
                    case 6:
                        status = false;
            }
        }

        input.close();

        /*main methods */
        
    }
}