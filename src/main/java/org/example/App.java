package org.example;

import java.util.ArrayList;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main(String[] args) {
        // Creating some persons
        //createSomePersons();


        // Retrieving all persons
        ArrayList<Person> allPersons = ExcelPersonManager.getAllPersons();
        System.out.println("All Persons:");
        for (Person person : allPersons) {
            System.out.println("person: ");
            person.print();
        }

        // Retrieving a person by name
        Person personByName = ExcelPersonManager.getPersonByName("John Doe");
        System.out.println("\nPerson by Name: ");
        personByName.print();

        // Retrieving top 5 persons with highest salaries
        ArrayList<Person> top5HighestSalaryPersons = ExcelPersonManager.getTop5HighestSalaryPersons();
        System.out.println("\nTop 5 Persons with Highest Salaries:");
        for (Person person : top5HighestSalaryPersons) {
            System.out.println("person: ");
            person.print();
        }
    }

    public static void createSomePersons(){
        ExcelPersonManager.savePerson(new Person("John Doe", 50000, "123 Main St", "555-1234"));
        ExcelPersonManager.savePerson(new Person("Jane Doe", 60000, "123 Main St", "555-1234"));
        ExcelPersonManager.savePerson(new Person("Tom Smith", 70000, "123 Main St", "555-1234"));
        ExcelPersonManager.savePerson(new Person("Jerry Seinfeld", 80000, "123 Main St", "555-1234"));
        ExcelPersonManager.savePerson(new Person("Elaine Benes", 90000, "123 Main St", "555-1234"));
        ExcelPersonManager.savePerson(new Person("George Costanza", 100000, "123 Main St", "555-1234"));
        ExcelPersonManager.savePerson(new Person("Cosmo Kramer", 110000, "123 Main St", "555-1234"));
    }
}
