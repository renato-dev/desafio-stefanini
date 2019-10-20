package br.com.stefanini;

import br.com.stefanini.model.Sale;
import br.com.stefanini.model.Salesman;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Validate {
    static public boolean isCPFValid(String cpf){
       String[] cpfsplit = cpf.split("\\.");
       String[] lastPart = cpfsplit[2].split("-");

       if(cpfsplit[0].length() != 3 ||cpfsplit[1].length() != 3 ||lastPart[0].length() != 3 || lastPart[1].length() != 2){
           return false;
       }
       try {
           Integer.parseInt(cpfsplit[0]);
           Integer.parseInt(cpfsplit[1]);
           Integer.parseInt(lastPart[0]);
           Integer.parseInt(lastPart[1]);
       }catch (Exception e){
           return false;
       }
        return true;
    }
    static public Salesman getWorstSalesmanFromList(List<Sale> saleList){
        HashMap<Salesman, Double> salesTotalPerSalesman = new HashMap<>();
        //<Salesman, totalSales>
        //<Diego, 120>
        //<Renata, 300>
        for(Sale sale: saleList){
            if(salesTotalPerSalesman.containsKey(sale.getSalesman())){
                salesTotalPerSalesman.put(sale.getSalesman(), salesTotalPerSalesman.get(sale.getSalesman()) + sale.getTotalPrice());
            } else {
                salesTotalPerSalesman.put(sale.getSalesman(), sale.getTotalPrice());
            }
        }
        Salesman firstSalesman = (Salesman) salesTotalPerSalesman.entrySet().iterator().next().getKey();
        double worstSalesTotal = salesTotalPerSalesman.get(firstSalesman);
        Salesman worstSalesmanEver = firstSalesman;

        for(Map.Entry entry : salesTotalPerSalesman.entrySet()){
            if((double)entry.getValue() < worstSalesTotal){
                worstSalesTotal = (double) entry.getValue();
                worstSalesmanEver = (Salesman) entry.getKey();
            }
        }

        return worstSalesmanEver;
    }
    static public Sale getMostExpensiveSaleFromList(List<Sale> saleList){
        double mostExpensiveSaleValue = 0;
        Sale mostExpensiveSale = null;
        for (Sale sale:saleList){
          if(sale.getTotalPrice() > mostExpensiveSaleValue){
            mostExpensiveSaleValue = sale.getTotalPrice();
            mostExpensiveSale = sale;
          }
        }
        return mostExpensiveSale;
    }
}
