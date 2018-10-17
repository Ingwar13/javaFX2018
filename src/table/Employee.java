package table;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Employee {

    private final StringProperty name;
    private final IntegerProperty age;
    private final IntegerProperty salary;


    public Employee(String name, Integer age, Integer salary){
        this.name = new SimpleStringProperty(name);
        this.age = new SimpleIntegerProperty(age);
        this.salary = new SimpleIntegerProperty(salary);
    }

    public Employee(){
        this.name = null; //new SimpleStringProperty(name);
        this.age = null;
        this.salary = null;
    }

    public String getName(){
        return name.get();
    }

    public void setName(String name){
        this.name.set(name);
    }

    public StringProperty nameProperty(){
        return name;
    }

    public Integer getAge(){
        return age.get();
    }

    public void setAge(int age){
        this.age.set(age);
    }

    public IntegerProperty ageProperty(){
        return age;
    }

    public Integer getSalary() {
        return salary.get();
    }

    public void setSalary(int salary){
        this.salary.set(salary);
    }

    public IntegerProperty salaryProperty(){
        return salary;
    }


}
