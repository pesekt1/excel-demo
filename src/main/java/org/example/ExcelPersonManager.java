package org.example;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ExcelPersonManager {
    private static final String FILE_NAME = "persons.xlsx";

    public static void savePerson(Person person) {

        File file = new File(FILE_NAME);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try (FileInputStream fis = new FileInputStream(FILE_NAME);
             Workbook workbook = fis.available() > 0 ? new XSSFWorkbook(fis) : new XSSFWorkbook()) {
            Sheet sheet = workbook.getSheetAt(0);
            int lastRowIndex = sheet.getLastRowNum();
            Row newRow = sheet.createRow(lastRowIndex + 1);
            newRow.createCell(0).setCellValue(person.getName());
            newRow.createCell(1).setCellValue(person.getSalary());
            newRow.createCell(2).setCellValue(person.getAddress());
            newRow.createCell(3).setCellValue(person.getPhoneNumber());

            try (FileOutputStream fos = new FileOutputStream(FILE_NAME)) {
                workbook.write(fos);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Person> getAllPersons() {
        ArrayList<Person> persons = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(FILE_NAME);
             Workbook workbook = new XSSFWorkbook(fis)) {
            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue; // Skip header row
                Person person = new Person(
                        row.getCell(0).getStringCellValue(),
                        row.getCell(1).getNumericCellValue(),
                        row.getCell(2).getStringCellValue(),
                        row.getCell(3).getStringCellValue()
                );
                persons.add(person);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Sort persons by salary in descending order
        Collections.sort(persons, Comparator.comparingDouble(Person::getSalary).reversed());

        return persons;
    }

    public static ArrayList<Person> getTop5HighestSalaryPersons() {
        ArrayList<Person> allPersons = getAllPersons();
        ArrayList<Person> top5Persons = new ArrayList<>();
        for (int i = 0; i < Math.min(5, allPersons.size()); i++) {
            top5Persons.add(allPersons.get(i));
        }
        return top5Persons;
    }

    public static Person getPersonByName(String name) {
        try (FileInputStream fis = new FileInputStream(FILE_NAME);
             Workbook workbook = new XSSFWorkbook(fis)) {
            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                Cell cell = row.getCell(0); // Assuming name is in the first column
                if (cell != null && cell.getCellType() == CellType.STRING && cell.getStringCellValue().equals(name)) {
                    return new Person(
                            cell.getStringCellValue(),
                            row.getCell(1).getNumericCellValue(),
                            row.getCell(2).getStringCellValue(),
                            row.getCell(3).getStringCellValue()
                    );
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null; // If person with given name not found
    }

}
