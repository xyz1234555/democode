package com.studentApp.StudentApplication;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

public class Readingdatafromexcel {

    @Test
    public void brokenLink() {
        FileInputStream in = null;
        try {
            in = new FileInputStream("C:\\Users\\user\\Documents\\Automation\\data.xlsx");
            XSSFWorkbook wb=new	XSSFWorkbook(in);
            XSSFSheet sh=wb.getSheet("Sheet1");
            int totalRow=sh.getLastRowNum();
            int totalcolumn=sh.getRow(1).getLastCellNum();// Add logic to read the file here
            System.out.println(totalRow);
            System.out.println(totalcolumn);
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        } 
    }
}
