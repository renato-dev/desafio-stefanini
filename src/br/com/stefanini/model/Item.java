package br.com.stefanini.model;

public class Item {
    long Id;
    int Quantity;
    Double price;

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "\nItem{" +
                "Id=" + Id +
                ", Quantity=" + Quantity +
                ", price=" + price +
                '}';
    }
}
