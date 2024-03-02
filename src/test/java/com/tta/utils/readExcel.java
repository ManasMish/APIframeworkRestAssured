package com.tta.utils;


import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.DataProvider;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class readExcel {
    static Workbook book;
    static Sheet sheet;
    public static String file_name="C:\\Users\\manas\\IdeaProjects\\APIframeworkRestAssured\\src\\test\\java\\resources\\InOutData.xlsx";
    public static Object[][] getTestData(String sheetname){
        FileInputStream file=null;
        try{
            file=new FileInputStream(file_name);
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        try{
            book= WorkbookFactory.create(file);
        }
        catch (IOException e){
            e.printStackTrace();
        }
        sheet=book.getSheet(sheetname);
        Object[][] data=new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
        for(int i=0;i<sheet.getLastRowNum();i++){
            for(int j=0;j<sheet.getRow(0).getLastCellNum();j++){
                data[i][j]=sheet.getRow(i+1).getCell(j).toString();
            }
        }
        return data;
    }
    @DataProvider
    public Object[][] getData(){
        return getTestData("app.vwo(Credentials)");
    }
}
