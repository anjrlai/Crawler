package com.jvax.test;


import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class BasicCSVReader {
    private static final String SAMPLE_CSV_FILE_PATH = "users.csv";

    public static void main(String[] args) 
    {
        try 
        {
            
            Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH));
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
            for (CSVRecord csvRecord : csvParser) {
                // Accessing Values by Column Index
                System.out.println("csvRecord.size()::"+csvRecord.size());
                String name = csvRecord.get(0);
                String email = csvRecord.get(1);
                String phone = csvRecord.get(2);
                String country = csvRecord.get(3);

                System.out.println("Record No - " + csvRecord.getRecordNumber());
                System.out.println("---------------");
                System.out.print("Name : " + name);
                System.out.print("\tEmail : " + email);
                System.out.print("\tPhone : " + phone);
                System.out.print("\tCountry : " + country+"\n");
                System.out.println("---------------");
            }
        }
        catch(Exception e){
            e.printStackTrace();    
        }
        
    }
}
