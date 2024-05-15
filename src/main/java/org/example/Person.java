package org.example;

import java.io.*;

public class Person {
    // Fields
    private double salary;
    private String name;
    private String address;
    private String phoneNumber;

    // Constructor
    public Person(String name, double salary, String address, String phoneNumber) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.salary = salary;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public double getSalary() {
        return salary;
    }

    public double calculate_tax() {
        return salary * 0.4;
    }

    public void inputPerson() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Name: ");
            name = reader.readLine();
            System.out.println("Address: ");
            address = reader.readLine();
            System.out.println("Phone number: ");
            phoneNumber = reader.readLine();
            System.out.println("Salary: ");
            salary = Integer.parseInt(reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void getAll() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("persons.json"));
            String line;
            StringBuilder jsonContent = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                jsonContent.append(line);
            }
            reader.close();
            System.out.println(jsonContent);
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    public void print() {
        System.out.println("Name: " + name);
        System.out.println("Address: " + address);
        System.out.println("Phone number: " + phoneNumber);
        System.out.println("Salary: " + salary);
    }



    public static void getByName(String name) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("persons.json"));
            String line;
            StringBuilder jsonContent = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                jsonContent.append(line);
            }
            reader.close();
            String[] persons = jsonContent.toString().split("}");
            for (String person : persons) {
                if (person.contains(name)) {
                    System.out.println(person);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}