package api.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	@DataProvider(name = "Data")
	public String[][] getData() throws IOException {
		String xFile = System.getProperty("user.dir")+"//testData//Userdata.xlsx";
		String xSheetName = "Sheet1";

		int rowCount = ExcelUtility.getRowCoount(xFile, xSheetName);
		int cellCount = ExcelUtility.getCellCount(xFile, xSheetName, rowCount);

		String[][] data = new String[rowCount][cellCount];

		for (int i = 1; i <= rowCount; i++) {
			for (int j = 0; j < cellCount; j++) {
				data[i - 1][j] = ExcelUtility.getCellData(xFile, xSheetName, i, j);

			}
		}
		return data;
	}
	@DataProvider(name="UserNames")
	public String[] getUserNames() throws IOException {
		String xFile = System.getProperty("user.dir")+"//testData//Userdata.xlsx";
		String xSheetName = "Sheet1";
		int rowCount = ExcelUtility.getRowCoount(xFile, xSheetName);
		String userName[] = new String[rowCount];
		for (int i=1;i<=rowCount;i++) {
			userName[i-1] = ExcelUtility.getCellData(xFile, xSheetName, i, 1);
			
		}
		return userName;
	}

}
