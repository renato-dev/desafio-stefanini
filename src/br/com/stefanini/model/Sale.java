package br.com.stefanini.model;

import java.util.ArrayList;
import java.util.List;

public class Sale {
    private long Id;
    private List<Item> itemList;
    private Salesman salesman;
    private double totalPrice;

    public Sale(){
        itemList = new ArrayList<>();
    }
    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public List<Item> getItemList() {
        return itemList;
    }
    public Salesman getSalesman() {
        return salesman;
    }
    public void addItems(List<Item> itemList){
        for(Item item : itemList){
            addItem(item);
        }
    }
    public void addItem(Item  item) {
        this.totalPrice += ((double)item.getQuantity()) * item.getPrice();
        this.itemList.add(item);
    }
    public void setSalesman(Salesman salesman) {
        this.salesman = salesman;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    @Override
    public String toString() {
        return "Sale{" +
                "Id=" + Id +
                ", itemlist=" + (itemList != null ? itemList.toString() : "[]") +
                ", salesman='" +(salesman != null ?  salesman.toString() : "[]") +'\'' +
                ", totalPrice='" + this.totalPrice +
                '}';
    }
}
