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

    /*Functions */
    public void uploadData(LinkedList<Transactions> currentData, String filePath)
    {
         LinkedList <Transactions> newData = loadData(filePath);
        //If JSON file is empty
        if(newData.isEmpty())
            {
                try
                {
                    mapper.writerWithDefaultPrettyPrinter().writeValue(new File("save.json"),currentData);
                    System.out.println("Data has been uploaded");
                }
                catch(IOException e)
                {
                    System.out.println(e.getMessage());
                }
            }
        else
            {              
                for (Transactions transactions : currentData) 
                {
                    newData.add(transactions);
                }

                try 
                {
                    File file = new File(filePath);
                    mapper.writerWithDefaultPrettyPrinter().writeValue(file, newData);
                    System.out.println("Data successfully synced to " + filePath);
                } catch (IOException e) 
                {
                    System.err.println("Error saving data: " + e.getMessage());
                }
            }

        //If JSON file is not empty


    }

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

    public void saveDeletedData(LinkedList<Transactions> list, String filePath) {
        try 
        {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(filePath), list);
        } catch (IOException e) {
            System.err.println("Save failed: " + e.getMessage());
        }
    }

    public void displayData(String filePath)
    {
 
    LinkedList<Transactions> data = loadData(filePath);

    if (data.isEmpty()) {
        System.out.println("No data found in: " + filePath);
    } else {
        for (Transactions value: data) 
            {
                System.out.printf((data.indexOf(value) + 1) + ".%-10s | %-20s | $%.2f%n", value.getType(), value.getName(), value.getAmount());
        }
    }
    }  
    
    
        
}

