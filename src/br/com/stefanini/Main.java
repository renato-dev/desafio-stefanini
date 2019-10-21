package br.com.stefanini;

import br.com.stefanini.db.Database;
import br.com.stefanini.model.Customer;
import br.com.stefanini.model.Sale;
import br.com.stefanini.model.Salesman;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("File path:");
        String filePath = scanner.nextLine();

        Database db = new Database(filePath);
        Customer oldestCustomer=null;
        int numberOfCustomerInvalidCpf = 0;
        int biggerAge = 0;
        for(Customer customer : db.getAllCustomers()){
            if(!Validate.isCPFValid(customer.getCPF())){
                numberOfCustomerInvalidCpf++;
            }
            if(customer.getAge()>biggerAge){
                biggerAge = customer.getAge();
                oldestCustomer = customer;
            }
            System.out.println(customer.toString());
        }
        for(String erro : db.getErrorList()) {
            System.out.println(erro);
        }
        List<String> outputResult = new ArrayList<>();
        outputResult.add("OUTPUT:");
        outputResult.add("Linhas importadas com sucesso = "+db.getSuccessfullyImportedLines() );
        outputResult.add("Number Of Customer Invalid CPF: "+numberOfCustomerInvalidCpf);
        outputResult.add("Oldest Customer: "+oldestCustomer.getName());
        outputResult.add("Worst Salesman Ever : " +Validate.getWorstSalesmanFromList(db.getAllSales()).getName());
        outputResult.add("Most Expensive Sale ID : " + Validate.getMostExpensiveSaleFromList(db.getAllSales()).getId());

        Path file = Paths.get("output.txt");
        try {
            Files.write(file, outputResult, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
