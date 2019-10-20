package br.com.stefanini.model;

public class Salesman {
    String name;
    Double salary;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Salesman{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                '}';
    }
}
