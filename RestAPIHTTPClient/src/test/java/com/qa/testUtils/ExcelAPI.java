package com.qa.testUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.util.HSSFColor.HSSFColorPredefined;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.qa.base.RestBase;

public class ExcelAPI extends RestBase {

	FileInputStream finput = null;
	FileOutputStream foutput = null;
	XSSFWorkbook workbook = null;
	XSSFSheet sheet = null;
	XSSFRow row = null;
	XSSFCell cell = null;

	public ExcelAPI() {

		try {
			finput = new FileInputStream(filepath);
			workbook = new XSSFWorkbook(finput);
			sheet = workbook.getSheet(sheetName);
			finput.close();

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public Object[][] getAllCelldata()

	{

		Object[][] data = new Object[sheet.getLastRowNum() - 1][sheet.getRow(0).getLastCellNum() -3];
		for (int i = 0; i < sheet.getLastRowNum() - 1; i++) {
			for (int j = 0; j < sheet.getRow(i).getLastCellNum() - 3; j++) {
				cell = sheet.getRow(i + 2).getCell(j);
				if (cell.getCellTypeEnum() == CellType.NUMERIC) {
					data[i][j] = (int) cell.getNumericCellValue();
				} else if (cell.getCellTypeEnum() == CellType.BLANK)
					data[i][j] = "";
				else
					data[i][j] = cell.getStringCellValue();

			}
		}
		return data;
	}

	public XSSFCell writeCelldata(int rowCount, String colName, String value) {
		try {
			int colNum = -1;

			row = sheet.getRow(1);
			for (int i = 0; i < row.getLastCellNum(); i++) {
				if (row.getCell(i).getStringCellValue().trim().equals(colName))
					colNum = i;
			}

			row = sheet.getRow(rowCount);
			if (row == null) {
				System.out.println("Row xreated");
				row = sheet.createRow(rowCount);
			}
			cell = row.getCell(colNum);
			if (cell == null) {
				System.out.println("Cell xreated");
				cell = row.createCell(colNum);
			}

			cell.setCellValue(value);

			foutput = new FileOutputStream(filepath);
			workbook.write(foutput);
			foutput.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
		}
		return cell;
	}

	public XSSFCell writeCelldata(int rowCount, String colName, int value) {
		int colNum = -1;

		row = sheet.getRow(1);
		for (int i = 0; i < row.getLastCellNum(); i++) {
			if (row.getCell(i).getStringCellValue().trim().equals(colName))
				colNum = i;
		}
		row = sheet.getRow(rowCount);
		if (row == null) {
			System.out.println("Row xreated");
			row = sheet.createRow(rowCount);
		}
		cell = row.getCell(colNum);
		if (cell == null) {
			System.out.println("cell xreated");
			cell = row.createCell(colNum);
		}

		cell.setCellValue(value);
		try {
			foutput = new FileOutputStream(filepath);
			workbook.write(foutput);
			foutput.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cell;
	}

	public void setupCell(XSSFCell cell, short color) {
		try {
		XSSFCellStyle style = workbook.createCellStyle();
		style.setFillForegroundColor(color);
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		cell.setCellStyle(style);
		foutput = new FileOutputStream(filepath);
		workbook.write(foutput);
		foutput.close();
		}
		 catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}
