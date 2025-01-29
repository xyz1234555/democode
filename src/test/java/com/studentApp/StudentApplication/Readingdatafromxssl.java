package com.studentApp.StudentApplication;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Readingdatafromxssl {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

        FileInputStream in = null;
        try {
            in = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\java\\com\\studentApp\\StudentApplication\\\\files\\data.xlsx");
            XSSFWorkbook wb=new	XSSFWorkbook(in);
            XSSFSheet sh=wb.getSheet("Sheet1");
            int totalRow=sh.getLastRowNum();
            int totalcolumn=sh.getRow(1).getLastCellNum();// Add logic to read the file here
            System.out.println("total no of rows: "+(totalRow + 1));
            System.out.println("total no of Cols: "+totalcolumn);
            for (int r=0;r<=totalRow;r++) {
            	XSSFRow row=sh.getRow(r);
            	for(int c=0;c<totalcolumn;c++) {
            		XSSFCell cell=row.getCell(c);
            		CellType ctype=cell.getCellType();
            		switch(ctype) {
            		case STRING:System.out.print(cell.getStringCellValue());
            		            break;
            		case NUMERIC:System.out.print(cell.getNumericCellValue());
		            break;
            		case BOOLEAN:System.out.print(cell.getBooleanCellValue());
		            break;
            		}
            		System.out.print(" ");
            	}
            	System.out.println();
            }
            
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        } 
    
	}

}
