package com.tracker;
import java.util.Scanner;
import java.time.LocalDate;
import com.fasterxml.jackson.databind.ObjectMapper;



public class Main {
    public static void main(String[] args) 
    {
         /*Variable */
        FinanceManager fm = new FinanceManager();
        Scanner kbd = new Scanner(System.in);
        LocalDate today = LocalDate.now();
        FinanceManager manager = new FinanceManager();

        int choice;
        double amount;
        String name, category;
        boolean status = true;

        
        
        while(status)
            {

                fm.menu();
                choice = kbd.nextInt();

                /*Process choice */
        

                switch(choice)
                {
                    case 1:
                        
                        System.out.println("How much would you like to add to the budget: ");
                        amount = kbd.nextDouble();
                        kbd.nextLine();
                        System.out.println("What budget is this for (eg. Overral, Groceries, Utilities): ");
                        category = kbd.nextLine();
                        System.out.println("What would you like to name this budget entry: ");
                        name = kbd.nextLine();
                        
                        
                        Transactions budget = new Transactions("Budget", name, today.toString(), category, amount);
                        manager.addTransaction(budget);

                        break;
                    case 2:
                        System.out.println("How much would you like to add to expenses: ");
                        amount = kbd.nextDouble();
                        kbd.nextLine();
                        System.out.println("What budget is this for (eg. Overral, Groceries, Utilities): ");
                        category = kbd.nextLine();
                        System.out.println("What would you to name this expense entry: ");
                        name = kbd.nextLine();
                        
                        
                        
                        Transactions expense = new Transactions("Expense", name, today.toString(), category, amount);
                        manager.addTransaction(expense);
                        break;
                    case 3:
                        manager.viewSummary();
                        break;
                    case 4:
                        manager.save();
                    case 5:
                        status = false;



            }
        }

        kbd.close();

        /*main methods */
        
    }
}