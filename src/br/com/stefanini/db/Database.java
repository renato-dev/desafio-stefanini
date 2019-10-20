package br.com.stefanini.db;

import br.com.stefanini.model.Customer;
import br.com.stefanini.model.Processor;
import br.com.stefanini.model.Sale;
import br.com.stefanini.model.Salesman;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Database {

    private static List<Salesman> salesmanList;
    private static List<Customer> customerList;
    private static List<Sale> salesList;
    private static List<String> errorList;

    private static int successfullyImportedLines;

    public Database(){}

    public Database(String filePath) {
        salesmanList = new ArrayList<>();
        customerList = new ArrayList<>();
        errorList = new ArrayList<>();
        salesList = new ArrayList<>();
        importDataFromFile(filePath);
    }

    private void importDataFromFile(String filePath) {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(filePath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Processor processor = new Processor(this);

        int  lineIndex = 1;
        int errorNumber = 1;
        while (scanner.hasNextLine()) {
            //process each line
            String line = scanner.nextLine();
            String[] lineValues = line.split(";");
            String entityId = lineValues[0];
            try{
            switch(entityId) {
                    case "001":
                        processor.processSalesmanData(lineValues);
                        break;
                    case "002":
                        processor.processCustomerData(lineValues);
                        break;
                    case "003":
                        processor.processSaleData(lineValues);
                        break;
                }
            }catch (Exception e ) {
               errorList.add("Error " + errorNumber + " " + e.getClass().getSimpleName()+" on line " + lineIndex);
                errorNumber++;
            }
            lineIndex++;
        }
        successfullyImportedLines = lineIndex - errorNumber;
    }

    public  List<Salesman> getAllSalesmen() {
        return salesmanList;
    }
    public  void addSalesman (Salesman salesman){
        salesmanList.add(salesman);
    }
    public  List<Customer> getAllCustomers() {
        return customerList;
    }
    public  void addCustomer (Customer customer){
        customerList.add(customer);
    }
    public List<Sale> getAllSales(){
        return salesList;
    }
    public  void addSale (Sale sale){
        salesList.add(sale);
    }


    public static int getSuccessfullyImportedLines() {
        return successfullyImportedLines;
    }
    public Sale findSaleById(long saleId){
        for(Sale sale : salesList){
            if(sale.getId()==saleId)
                return sale;
        }
        return null;
    }
    public Salesman findSalesmanByName(String salesmanName){
        for(Salesman salesman : salesmanList){
            if(salesman.getName().equals(salesmanName))
                return salesman;
        }
        return null;
    }

    public static List<String> getErrorList() {
        return errorList;
    }
}
