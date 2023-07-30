package api.utilities;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {


	public static FileInputStream fileLoc;
	public static XSSFWorkbook wBook;
	public static XSSFSheet xSheet;
	public static XSSFRow row;
	public static XSSFCell cell;



	// 1.Method that can get the row count.
	public static int getRowCoount(String xFile,String xSheetName) throws IOException {
		fileLoc = new FileInputStream(xFile);
		wBook = new XSSFWorkbook(fileLoc);
		xSheet = wBook.getSheet(xSheetName);
		int rowCount = xSheet.getLastRowNum();
		return rowCount;

	}


	// 2. Method that can get cell count for each row.

	public static int getCellCount(String xFile, String xSheetName, int rowNum ) throws IOException {
		fileLoc = new FileInputStream(xFile);
		wBook = new XSSFWorkbook(fileLoc);
		xSheet = wBook.getSheet(xSheetName);
		row = xSheet.getRow(rowNum);
		int cellCount = row.getLastCellNum();
		return cellCount;

	}
	// 3.Method to get the cell data from each cell in a String format.
	public static String getCellData(String xFile, String xSheetName, int rowNum , int celNum) throws IOException {
		fileLoc = new FileInputStream(xFile);
		wBook = new XSSFWorkbook(fileLoc);
		xSheet=  wBook.getSheet(xSheetName);
		row  = xSheet.getRow(rowNum);
		cell = row.getCell(celNum);

		DataFormatter format = new DataFormatter();
		String cellData = format.formatCellValue(cell);

		fileLoc.close();
		wBook.close();

		return cellData;
	}

}
