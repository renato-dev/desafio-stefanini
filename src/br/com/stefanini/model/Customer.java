package br.com.stefanini.model;

public class Customer {
    private String CPF;
    private String Name;
    private int age;

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "CPF='" + CPF + '\'' +
                ", Name='" + Name + '\'' +
                ", age=" + age +
                '}';
    }
}
