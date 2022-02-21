package com.flipkart.tasks.util;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelReader {
	
	public static Object[][] excelData(String path) {
						System.out.println("path is "+ path);
			Workbook wb = null;
					
					try
					{
						wb = WorkbookFactory.create(new FileInputStream(path));
					} 
					catch (Exception e) {
						e.printStackTrace();
					}
					
					
					int RowCount =  wb.getSheet("searchData").getLastRowNum();
					int ColumnCount =  wb.getSheet("searchData").getRow(0).getLastCellNum();
					System.out.println("Row Count  :"+RowCount+"  Column Count : "+ColumnCount);
					Object input[][]=new Object[RowCount][ColumnCount];
					
					for(int i= 1; i<=RowCount; i++)
						  for(int j= 0;j<ColumnCount;j++) 
							  input[i-1][j] = wb.getSheet("searchData").getRow(i).getCell(j).getStringCellValue() ; 
						  
					return input;
		}

	
}