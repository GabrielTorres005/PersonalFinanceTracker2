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

    //Saves Data to JSON
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

    //Loads Data from last save
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

    //Deletes an Entry
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

    //Helper function for removeData, removes deleted entry from JSON file
    public void saveDeletedData(LinkedList<Transactions> list, String filePath) {
        try 
        {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(filePath), list);
        } catch (IOException e) {
            System.err.println("Save failed: " + e.getMessage());
        }
    }

    //Displays Summarry is JSON file after last save
    public void displayData(String filePath)
    {
 
    LinkedList<Transactions> data = loadData(filePath);

    if (data.isEmpty()) {
        System.out.println("No data found in: " + filePath);
    } else 
        {
            System.out.printf("%10s %3s %5s %6s %-5s\n","Type","|", "Name","|", "Amount");

            for (Transactions value: data) 
                {
                    System.out.println("-------------------------------------");

                    System.out.printf((data.indexOf(value) + 1) + ".%-10s | %-10s | $%.2f%n", value.getType(), value.getName(), value.getAmount());
                    
                }
                System.out.println("-------------------------------------");
                System.out.printf("%30s\n%30s\n" , "Total Budget:$ ", "Total Expenses:$ ");
    }
    }  
    
    
        
}

