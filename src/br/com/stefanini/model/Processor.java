package br.com.stefanini.model;

import br.com.stefanini.db.Database;
import br.com.stefanini.exception.SalesmanNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class Processor {
    private Database db;

    public Processor(Database db){
        this.db =db;
    }

    public void  processSalesmanData(String [] salesmanData){
        Salesman salesman = new Salesman();
        salesman.setName(salesmanData[1]);
        salesman.setSalary(Double.parseDouble(salesmanData[2]));
        db.addSalesman(salesman);

    }
    public void  processCustomerData(String [] customerData){
        Customer customer = new Customer();
        customer.setCPF(customerData[1]);
        customer.setName(customerData[2]);
        customer.setAge(Integer.parseInt(customerData[3]));
        db.addCustomer(customer);
    }
    public Sale processSaleData(String [] saleData) throws SalesmanNotFoundException {
        long saleId = Long.parseLong(saleData[1]);

        // search for existing sale
        Sale sale = db.findSaleById(saleId);

        if(sale == null){
             sale = new Sale();
             sale.setId(saleId);
            sale.addItems(processItemsData(saleData[2]));

             Salesman salesman = db.findSalesmanByName(saleData[3]);
             if(salesman != null){
                 sale.setSalesman(db.findSalesmanByName(saleData[3]));
             }else{
                 throw new SalesmanNotFoundException();
             }
            db.addSale(sale);
        }else {
            // if we found one , add the items on it
           sale.addItems(processItemsData(saleData[2]));
        }

        return sale;
    }
    private List<Item> processItemsData(String itemDataStr){
        String[] allitemsData =  itemDataStr.replace("[","")
                                        .replace("]","")
                                        .split(",");

        List<Item> itemList = new ArrayList<>();
        for(String singleItemStr : allitemsData){
            String[] singleItemData= singleItemStr.split("-");
            Item item = new Item();
            item.setId(Long.parseLong(singleItemData[0]));
            item.setQuantity(Integer.parseInt(singleItemData[1]));
            item.setPrice(Double.parseDouble(singleItemData[2]));

            itemList.add(item);
        }

        return itemList;

    }
}
