package org.example;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.*;

public class ExcelMemberManager {
    private static final String FILE_NAME = "Members.xlsx";

        public static void saveMemberFromConsole() {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Enter member name:");
            String name = scanner.nextLine();

            System.out.println("Enter birth date (dd/MM/yyyy):");
            String birthDate = scanner.nextLine();

            System.out.println("Is the membership active? (true/false):");
            boolean activeMembership = scanner.nextBoolean();
            scanner.nextLine(); // consume newline left-over

            System.out.println("Enter swim type (COMPETITIVE/NONCOMPETITIVE):");
            SwimType swimType = SwimType.valueOf(scanner.nextLine().toUpperCase());

            Member member = new Member(name, birthDate, activeMembership, swimType);

            saveMember(member);
        }

    public static void saveMember(Member member) {
        Workbook workbook;
        File file = new File(FILE_NAME);
        FileInputStream fis = null;

        if (file.exists()) {
            // If file exists, open and use it
            try {
                fis = new FileInputStream(file);
                workbook = new XSSFWorkbook(fis);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            // If file does not exist, create a new one
            workbook = new XSSFWorkbook();
        }

        Sheet sheet = workbook.getSheet("Members");
        if (sheet == null) {
            // If sheet does not exist, create a new one
            sheet = workbook.createSheet("Members");

            // Create header row
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Name");
            headerRow.createCell(1).setCellValue("BirthDate");
            headerRow.createCell(2).setCellValue("ActiveMembership");
            headerRow.createCell(3).setCellValue("AgeType");
            headerRow.createCell(4).setCellValue("SwimType");
            headerRow.createCell(5).setCellValue("SubscriptionFee");
            headerRow.createCell(6).setCellValue("RegistrationDate");
            headerRow.createCell(7).setCellValue("TrainerName");
            headerRow.createCell(8).setCellValue("ButterflyTime");
            headerRow.createCell(9).setCellValue("ButterflyDate");
            headerRow.createCell(10).setCellValue("BackstrokeTime");
            headerRow.createCell(11).setCellValue("BackstrokeDate");
            headerRow.createCell(12).setCellValue("BreaststrokeTime");
            headerRow.createCell(13).setCellValue("BreaststrokeDate");
            headerRow.createCell(14).setCellValue("Balance");
        }

        // Add data row at the end of the sheet
        int lastRowNum = sheet.getLastRowNum();
        Row row = sheet.createRow(lastRowNum + 1);
        row.createCell(0).setCellValue(member.getName());
        row.createCell(1).setCellValue(member.getBirthDate());
        row.createCell(2).setCellValue(member.isActiveMembership());
        row.createCell(3).setCellValue(member.getAgeType().toString());
        row.createCell(4).setCellValue(member.getSwimType().toString());
        row.createCell(5).setCellValue(member.getSubscriptionFee());
        row.createCell(6).setCellValue(member.getRegistrationDate());
        row.createCell(7).setCellValue(member.getTrainerName());
        row.createCell(8).setCellValue(member.getButterflyTime());
        row.createCell(9).setCellValue(member.getButterflyDate());
        row.createCell(10).setCellValue(member.getBackstrokeTime());
        row.createCell(11).setCellValue(member.getBackstrokeDate());
        row.createCell(12).setCellValue(member.getBreaststrokeTime());
        row.createCell(13).setCellValue(member.getBreaststrokeDate());
        row.createCell(14).setCellValue(member.getBalance());

        // Write to file
        try (FileOutputStream fos = new FileOutputStream(FILE_NAME)) {
            workbook.write(fos);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static ArrayList<Member> getAllMembers() {
        ArrayList<Member> members = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(FILE_NAME);
             Workbook workbook = new XSSFWorkbook(fis)) {
            Sheet sheet = workbook.getSheetAt(0);

            Iterator<Row> iterator = sheet.iterator();
            while (iterator.hasNext()) {
                Row row = iterator.next();
                if (row.getRowNum() == 0) {
                    continue; // Skip header row
                }
                Member member = getMember(row);
                members.add(member);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return members;
    }

    private static ArrayList<Member> getTop5FastestButterflySwimmers() {
        ArrayList<Member> members = getAllMembers();
        members.removeIf(member -> member.getButterflyTime() == 0);
        members.sort(Comparator.comparing(Member::getButterflyTime));
        return new ArrayList<>(members.subList(0, Math.min(5, members.size())));
    }

    public static void printTop5FastestButterflySwimmers() {
        ArrayList<Member> members = getTop5FastestButterflySwimmers();
        System.out.println();
        System.out.println("Top 5 Fastest Butterfly Swimmers:");
        for (Member member : members) {
            System.out.println();
            System.out.println("Member:");
            System.out.println("Member Name: " + member.getName());
            System.out.println("Butterfly Date: " + member.getButterflyDate());
            System.out.println("Butterfly Time: " + member.getButterflyTime());
        }
    }

    private static ArrayList<Member> getTop5FastestBackstrokeSwimmers() {
        ArrayList<Member> members = getAllMembers();
        members.removeIf(member -> member.getBackstrokeTime() == 0);
        members.sort(Comparator.comparing(Member::getBackstrokeTime));
        return new ArrayList<>(members.subList(0, Math.min(5, members.size())));
    }

    public static void printTop5FastestBackstrokeSwimmers() {
        ArrayList<Member> members = getTop5FastestBackstrokeSwimmers();
        System.out.println();
        System.out.println("Top 5 Fastest Backstroke Swimmers:");
        for (Member member : members) {
            System.out.println();
            System.out.println("Member:");
            System.out.println("Member Name: " + member.getName());
            System.out.println("Backstroke Date: " + member.getBackstrokeDate());
            System.out.println("Backstroke Time: " + member.getBackstrokeTime());
        }
    }

    private static ArrayList<Member> getTop5FastestBreaststrokeSwimmers() {
        ArrayList<Member> members = getAllMembers();
        members.removeIf(member -> member.getBreaststrokeTime() == 0);
        members.sort(Comparator.comparing(Member::getBreaststrokeTime));
        return new ArrayList<>(members.subList(0, Math.min(5, members.size())));
    }

    public static void printTop5FastestBreaststrokeSwimmers() {
        ArrayList<Member> members = getTop5FastestBreaststrokeSwimmers();
        System.out.println();
        System.out.println("Top 5 Fastest Breaststroke Swimmers:");
        for (Member member : members) {
            System.out.println();
            System.out.println("Member:");
            System.out.println("Member Name: " + member.getName());
            System.out.println("Breaststroke Date: " + member.getBreaststrokeDate());
            System.out.println("Breaststroke Time: " + member.getBreaststrokeTime());
        }
    }

    public static void printAllMembers() {
        System.out.println();
        System.out.println("All Members:");
        ArrayList<Member> members = getAllMembers();
        for (Member member : members) {
            System.out.println();
            System.out.println("Member:");
            member.print();
        }
    }

    public static Member getMemberByName(String name) {
        ArrayList<Member> members = getAllMembers();
        for (Member member : members) {
            if (member.getName().equals(name)) {
                return member;
            }
        }
        return null;
    }

    private static void updateMemberBalance(String name, int payment) {
        try (FileInputStream fis = new FileInputStream(FILE_NAME);
             Workbook workbook = new XSSFWorkbook(fis)) {
            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                Cell cell = row.getCell(0);
                if (cell != null && cell.getStringCellValue().equalsIgnoreCase(name)) {
                    int balanceCellIndex = 14; // Assuming the balance is in the 15th column (0-indexed)
                    Cell balanceCell = row.getCell(balanceCellIndex);
                    if (balanceCell == null) {
                        balanceCell = row.createCell(balanceCellIndex);
                    }
                    int currentBalance = (int) balanceCell.getNumericCellValue();
                    balanceCell.setCellValue(currentBalance - payment);

                    try (FileOutputStream fos = new FileOutputStream(FILE_NAME)) {
                        workbook.write(fos);
                    }
                    return;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void registerPaymentFromConsole() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter member name:");
        String name = scanner.nextLine();

        System.out.println("Enter payment amount:");
        int payment = scanner.nextInt();

        updateMemberBalance(name, payment);
    }

    public static void updateSwimTimeFromConsole() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter member name:");
        String name = scanner.nextLine();

        System.out.println("Enter swim type (Butterfly/Breaststroke/Backstroke):");
        String swimType = scanner.nextLine();

        System.out.println("Enter time:");
        double time = scanner.nextDouble();

        try (FileInputStream fis = new FileInputStream(FILE_NAME);
             Workbook workbook = new XSSFWorkbook(fis)) {
            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                if (row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue().equals(name)) {
                    switch (swimType.toLowerCase()) {
                        case "butterfly":
                            row.getCell(8, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(time);
                            row.getCell(9, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(new Date().toString());
                            break;
                        case "breaststroke":
                            row.getCell(12, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(time);
                            row.getCell(13, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(new Date().toString());
                            break;
                        case "backstroke":
                            row.getCell(10, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(time);
                            row.getCell(11, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(new Date().toString());
                            break;
                        default:
                            System.out.println("Invalid swim type entered. Please enter either Butterfly, Breaststroke, or Backstroke.");
                            return;
                    }
                    try (FileOutputStream fos = new FileOutputStream(FILE_NAME)) {
                        workbook.write(fos);
                    }
                    return;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static Member getMember(Row row) {
        Member member = new Member();
        member.setName(row.getCell(0).getStringCellValue());
        member.setBirthDate(row.getCell(1).getStringCellValue());
        member.setActiveMembership(row.getCell(2).getBooleanCellValue());
        member.setAgeType(AgeType.valueOf(row.getCell(3).getStringCellValue()));
        member.setSwimType(SwimType.valueOf(row.getCell(4).getStringCellValue()));
        member.setSubscriptionFee((int) row.getCell(5).getNumericCellValue());
        member.setRegistrationDate(row.getCell(6).getStringCellValue());
        member.setTrainerName(row.getCell(7).getStringCellValue());
        member.setButterflyTime(row.getCell(8).getNumericCellValue());
        member.setButterflyDate(row.getCell(9).getStringCellValue());
        member.setBackstrokeTime(row.getCell(10).getNumericCellValue());
        member.setBackstrokeDate(row.getCell(11).getStringCellValue());
        member.setBreaststrokeTime(row.getCell(12).getNumericCellValue());
        member.setBreaststrokeDate(row.getCell(13).getStringCellValue());
        member.setBalance((int) row.getCell(14).getNumericCellValue());
        return member;
    }

    public static boolean isFileEmpty() throws IOException {
        try (FileInputStream fis = new FileInputStream(FILE_NAME);
             Workbook workbook = new XSSFWorkbook(fis)) {
            Sheet sheet = workbook.getSheetAt(0);
            // If the file only contains the header row, it is considered empty
            return sheet.getPhysicalNumberOfRows() <= 1;
        }
    }

    private static ArrayList<Member> getMembersWithBalance() {
        ArrayList<Member> members = getAllMembers();
        members.removeIf(member -> member.getBalance() <= 0);
        return members;
    }

    public static void printMembersWithBalance(){
        ArrayList<Member> members = getMembersWithBalance();
        System.out.println();
        System.out.println("Members with Balance:");
        for (Member member : members) {
            System.out.println();
            System.out.println("Member:");
            member.print();
        }
    }
}