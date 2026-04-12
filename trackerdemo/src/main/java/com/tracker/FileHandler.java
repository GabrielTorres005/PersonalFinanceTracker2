package com.tracker;
import java.util.LinkedList;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;



public class FileHandler 
{

    /*Variables */
    ObjectMapper mapper = new ObjectMapper();
    TransactionSearchAndSort sorter = new TransactionSearchAndSort();

    
    /**
     * Uploads data from current Transaction arraylist to JSON file
     * @param newUpload Transaction arraylist
     * @param filePath Filepath to JSON file
     */
    public void uploadData(LinkedList<Transactions> newUpload, String filePath)
    {
         LinkedList <Transactions> previousUpload = loadData(filePath);
        if(previousUpload.isEmpty())
            {
                try
                {
                    mapper.writerWithDefaultPrettyPrinter().writeValue(new File("save.json"),newUpload);
                    System.out.println("Data has been uploaded");
                }
                catch(IOException e)
                {
                    System.out.println(e.getMessage());
                }
            }
        else
            {              
                for (Transactions transactions : newUpload) 
                {
                    previousUpload.add(transactions);
                }

                try 
                {
                    File file = new File(filePath);
                    mapper.writerWithDefaultPrettyPrinter().writeValue(file, previousUpload);
                    System.out.println("Data successfully synced to " + filePath);
                } catch (IOException e) 
                {
                    System.err.println("Error saving data: " + e.getMessage());
                }
            }
    }

    /**
     * Loads existing data from JSON file to current session
     * @param filePath Filepath to JSON file
     * @return Transaction arraylist saved in JSON file
     */
    public LinkedList<Transactions> loadData(String filePath)
    {
        try
        {    
            File file = new File(filePath);
            if(!file.exists())
                {
                    return new LinkedList<>();
                }
            return mapper.readValue(file, new TypeReference<LinkedList<Transactions>>(){});   
        }
        catch(IOException e)
        {
            e.printStackTrace();
            return new LinkedList<>();
        }
    }

    /**
     * Deletes transaction from JSON file
     * @param indexToRemove Index at which the value to delete is at
     * @param filePath Filepath to JSON file
     */
    public void removeData(int indexToRemove, String filePath)
    {
        LinkedList <Transactions> data = loadData(filePath);
        if (indexToRemove >= 0 && indexToRemove < data.size()) 
            {
            data.remove(indexToRemove);
            // USE SAVE, NOT UPLOAD
            saveDeletedData(data, filePath); 
            System.out.println("Item deleted successfully.");
        }
    }

    /**
     * Helper function to removeData, it saves the new version of Transaction arraylist
     * during current session
     * @param list Transaction arraylist
     * @param filePath Filepath to JSON file
     */
    public void saveDeletedData(LinkedList<Transactions> list, String filePath) {
        try 
        {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(filePath), list);
        } catch (IOException e) {
            System.err.println("Save failed: " + e.getMessage());
        }
    }

    /**
     * Displays menu for view Summary function
     * @param filePath Filepath to JSON file
     */
    public void displayData(String filePath)
    {
        LinkedList<Transactions> data = loadData(filePath);

        if (data.isEmpty()) 
            {
            System.out.println("No data found in: " + filePath);
             } 
        else 
            {
                // Calculate column widths
                int maxTypeLength = 4;      // "Type"
                int maxNameLength = 4;      // "Name"
                int maxCategoryLength = 8;  // "Category"
                int maxAmountLength = 6;    // "Amount"

                for (Transactions transaction : data) 
                    {
                        if (transaction.getType() != null) 
                            {
                            maxTypeLength = Math.max(maxTypeLength, transaction.getType().length());
                        }
                        if (transaction.getName() != null) 
                            {
                            maxNameLength = Math.max(maxNameLength, transaction.getName().length());
                        }
                        if (transaction.getCategory() != null) 
                            {
                            maxCategoryLength = Math.max(maxCategoryLength, transaction.getCategory().length());
                        }
                        if (transaction.getAmount() != 0)
                            {
                                maxAmountLength = Math.max(maxAmountLength, (int)String.format("%.2f", transaction.getAmount()).length());
                            }
                    }

                // Print header
                printHeader(maxTypeLength, maxNameLength, maxCategoryLength, maxAmountLength);
                
                // Print dash line
                printDashLine(maxTypeLength, maxNameLength, maxCategoryLength, maxAmountLength);

                // Print data rows
                for (Transactions value : data) {
                    System.out.printf("%d. | %-" + maxTypeLength + "s | %-" + maxNameLength + "s | %-" + maxCategoryLength + "s | %," + maxAmountLength + ".2f\n",
                            (data.indexOf(value) + 1),
                            value.getType(),
                            value.getName(),
                            value.getCategory(),
                            value.getAmount());
                }

                // Print final dash line
                printDashLine(maxTypeLength, maxNameLength, maxCategoryLength, maxAmountLength);
                
                printData(maxTypeLength, maxNameLength, maxCategoryLength, maxAmountLength, data);
            }
    }

    /**
     * Helper function to displayData, it prints the header
     * @param typeWidth Widthpace for type of transaction
     * @param nameWidth Widthpace for name of transaction
     * @param categoryWidth Widthpace for category of transaction
     * @param amountWidth Widthpace for amount of transaction
     */
    private void printHeader(int typeWidth, int nameWidth, int categoryWidth, int amountWidth) {
        System.out.printf("%s  | %-" + typeWidth + "s | %-" + nameWidth + "s | %-" + categoryWidth + "s | %-" + amountWidth + "s\n",
                "#", "Type", "Name", "Category", "Amount");
    }

    /**
     * Helper function to displayData, it prints the dashed lines
     * @param typeWidth Widthpace for type of transaction
     * @param nameWidth Widthpace for name of transaction
     * @param categoryWidth Widthpace for name of transaction
     * @param amountWidth Widthpace for amount of transaction
     */
    private void printDashLine(int typeWidth, int nameWidth, int categoryWidth, int amountWidth) {
        int totalLength = 3 + 1 + typeWidth + 1 + 1 + nameWidth + 1 + 1 + categoryWidth + 1 + 1 + amountWidth + 1 + 3;
        System.out.println("-".repeat(totalLength));
    }
    /**
     * Helper function to displayData, it prints the data 
     * @param typeWidth Widthpace for type of transaction
     * @param nameWidth Widthpace for name of transaction
     * @param categoryWidth Widthpace for name of transaction
     * @param amountWidth Widthpace for amount of transaction
     * @param data Transaction arraylist containing transactions
     */
    private void printData(int typeWidth, int nameWidth, int categoryWidth, int amountWidth, LinkedList<Transactions> data)
    {
        FinanceManager fm = new FinanceManager();
        double[] totals = fm.calculateMoneySummary(data);
        System.out.printf("%s    %-" + typeWidth + "s   %-" + nameWidth + "s   %s%,.2f\n"," "," ".repeat(typeWidth)," ".repeat(nameWidth), "Total Budget:$",totals[0]);
        System.out.printf("%s    %-" + typeWidth + "s   %-" + nameWidth + "s   %s%,.2f\n"," "," ".repeat(typeWidth)," ".repeat(nameWidth), "Total Expenses:$",totals[1]);
        System.out.printf("%s    %-" + typeWidth + "s   %-" + nameWidth + "s   %s%,.2f\n"," "," ".repeat(typeWidth)," ".repeat(nameWidth), "Total Balance:$",totals[2]);

    }

}
