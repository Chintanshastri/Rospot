package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class readfile {

	public void alldataread() {
	}

	@DataProvider(name = "excelData")
	public String[][] getExcelData(Method m) throws IOException 
	{
		String excelname = m.getName();
		String filePath = "C:\\Users\\LENOVO\\eclipse-workspace\\Ronspot\\src\\test\\resources\\testdata\\TestData.xlsx";
		FileInputStream fis = new FileInputStream(new File(filePath));
		Workbook workbook = new XSSFWorkbook(fis);
		Sheet sheet = workbook.getSheet(excelname);

		// Rows and columns count
		int rowCount = sheet.getLastRowNum();
		System.out.println("Total RowCount = " + rowCount);
		Row rowcell = sheet.getRow(rowCount);
		int totalcell = rowcell.getLastCellNum();
		System.out.println("Total CellCount = " + totalcell);

		DataFormatter format = new DataFormatter();
		String testdata[][] = new String[rowCount][totalcell];

		for (int i = 1; i < rowCount; i++) { // Start from 1 to skip the header row
			for (int j = 0; j < totalcell; j++) 
			{
				testdata[i - 1][j] = format.formatCellValue(sheet.getRow(i).getCell(j));
				System.out.println(testdata[i - 1][j]);

			}
		}
		return testdata;
	}
}
