package OO;

import java.sql.Array;

public  class Employeeee {
    private String name;
    private String post;
    private String email;
    private int phoneNum;
    private int salary;
    private int age;

    public Employeeee(String name, String post, String email, int phoneNum, int salary, int age) {
        this.name = name;
        this.post = post;
        this.email = email;
        this.phoneNum = phoneNum;
        this.salary = salary;
        this.age = age;

    }

    public String toString() {
        return String.format("Сотрудник:\n %s, занимает дожность %s. \nemail адрес : %s \nтелефон сотрудника : %s \nзарплата: %s $$ \nвозраст: %s\n", name, post, email, phoneNum, salary, age);

    }

    public int getAge() {
        return age;
    }

}

