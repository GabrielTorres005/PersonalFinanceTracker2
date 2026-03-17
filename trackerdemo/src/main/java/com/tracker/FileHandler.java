package com.tracker;
import java.util.LinkedHashMap;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;


public class FileHandler 
{

    /*Variables */
    ObjectMapper mapper = new ObjectMapper();

    /*Functions */
    public void uploadData(LinkedHashMap <String,Transactions> data)
    {
        try
        {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File("save.json"),data);
            System.out.println("Data has been uploaded");
        }
        catch(IOException e)
        {
            System.out.println(e.getMessage());
        }

    }

    public LinkedHashMap<String,Transactions> loadData(String filePath)

    {
        try
        {    
            File file = new File(filePath);
            if(!file.exists())
                {

                    return new LinkedHashMap<>();

                }
            return mapper.readValue(file, new TypeReference <LinkedHashMap<String,Transactions>>(){});

        }
        catch(IOException e)
        {
            e.printStackTrace();
            return new LinkedHashMap<>();
        }

    }

    public void displayData(String filePath)
    {
       LinkedHashMap<String, Transactions> data = loadData(filePath);

       if(data.isEmpty())
        {
            System.out.println("No data found in: " + filePath);

            data.forEach((key, value) -> {
                System.out.printf("%-15s | %-20s | $%.2f%n", 
                key, 
                value.getName(), 
                value.getAmount()
            );
        });
        }
    }

   
    
        

        
                
        
}

