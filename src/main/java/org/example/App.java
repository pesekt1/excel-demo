package org.example;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main(String[] args) {
        createSomeMembers();

        userInputMenu();

    }

    private static void userInputMenu() {
        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            System.out.println();
            System.out.println("Welcome to the Swimming Club Management System!");
            System.out.println("Please choose an option:");
            System.out.println("*************************************************");
            System.out.println("1. Insert a new member");
            System.out.println("2. Update a members swimming time");
            System.out.println("3. Register a payment");
            System.out.println("4. Print all members");
            System.out.println("5. Print members with outstanding balance");
            System.out.println("6. Print top 5 fastest breaststroke swimmers");
            System.out.println("7. Print top 5 fastest butterfly swimmers");
            System.out.println("8. Print top 5 fastest backstroke swimmers");
            System.out.println("9. Exit");
            System.out.println("*************************************************");

            option = scanner.nextInt();

            switch (option) {
                case 1:
                    // Call the method to insert a new member
                    ExcelMemberManager.saveMemberFromConsole();
                    break;
                case 2:
                    // Call the method to update a member
                    ExcelMemberManager.updateSwimTimeFromConsole();
                    break;
                case 3:
                    // Call the method to delete a member
                    ExcelMemberManager.registerPaymentFromConsole();
                    break;
                case 4:
                    ExcelMemberManager.printAllMembers();
                    break;
                case 5:
                    ExcelMemberManager.printMembersWithBalance();
                    break;
                case 6:
                    ExcelMemberManager.printTop5FastestBreaststrokeSwimmers();
                    break;
                case 7:
                    ExcelMemberManager.printTop5FastestButterflySwimmers();
                    break;
                case 8:
                    ExcelMemberManager.printTop5FastestBackstrokeSwimmers();
                    break;
                case 9:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid option. Please choose a number between 1 and 4.");
                    break;
            }
        } while (option != 9);
    }


    private static void createSomeMembers() {
        boolean isEmpty = false;
        try {
            isEmpty = ExcelMemberManager.isFileEmpty();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (!isEmpty) {
            return;
        }

        ArrayList<Member> members = new ArrayList<>();

        members.add(new Member("John Doe", "01/01/1990", true, SwimType.COMPETITIVE));
        members.add(new Member("Jane Doe", "01/01/1991", true, SwimType.COMPETITIVE));
        members.add(new Member("Tom Smith", "01/01/1992", true, SwimType.COMPETITIVE));
        members.add(new Member("Jerry Seinfeld", "01/01/1993", true, SwimType.COMPETITIVE));
        members.add(new Member("Elaine Benes", "01/01/1994", true, SwimType.COMPETITIVE));
        members.add(new Member("George Costanza", "01/01/1995", true, SwimType.NONCOMPETITIVE));
        members.add(new Member("Cosmo Kramer", "01/01/1996", true, SwimType.COMPETITIVE));

        for (Member member : members) {
            Random random = new Random();

            member.setBackstrokeTime(20 + random.nextDouble() * 180);
            member.setBreaststrokeTime(20 + random.nextDouble() * 180);
            member.setButterflyTime(20 + random.nextDouble() * 180);

            ExcelMemberManager.saveMember(member);
        }
    }
}
