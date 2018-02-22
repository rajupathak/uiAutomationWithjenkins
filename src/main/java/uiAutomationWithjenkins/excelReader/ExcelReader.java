package uiAutomationWithjenkins.excelReader;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {
	public String path;
	public FileInputStream fis;
	public XSSFWorkbook wb;
	public XSSFSheet sheet;

	// create the xssfwork book object
	public ExcelReader(String path) {
		this.path = path;

		try {
			fis = new FileInputStream(path);
			wb = new XSSFWorkbook(fis);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// This method will return the 2D object data for each record in excel sheet

	public String[][] getDataFromSheet(String sheetName, String excelName) {
		String dataSets[][] = null;

		try {
			// Now get the sheet from Excel
			sheet = wb.getSheet(sheetName);

			// Count total Active Rows

			int totalRow = sheet.getLastRowNum() + 1;

			// Count total number of Active column in sheet

			int totalColum = sheet.getRow(0).getLastCellNum();

			// Now create array of row and column
			dataSets = new String[totalRow - 1][totalColum];

			// Now run the for loop and store the data in 2D array
			// this for loop will run the rows
			for (int i = 1; i < totalRow; i++) {
				XSSFRow rows = sheet.getRow(i);

				// this for loop will run all the column for that row

				for (int j = 0; j < totalColum; j++) {
					XSSFCell cell = rows.getCell(j);
					// If Cell of type String ,then this if condition will work
					if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
						dataSets[i - 1][j] = cell.getStringCellValue();
					}

					// If Cell of type Numeric ,then this if condition will work
					else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
						dataSets[i - 1][j] = String.valueOf(cell
								.getNumericCellValue());

					} else {
						// If Cell of type boolean ,then this condition will
						// work

						dataSets[i - 1][j] = String.valueOf(cell
								.getBooleanCellValue());
					}

				}

			}
			return dataSets;
		}

		catch (Exception e) {
			System.out.println("Exception in reading Excel file :-"
					+ e.getMessage());
			e.printStackTrace();
		}

		return dataSets;

	}

	@SuppressWarnings("deprecation")
	public String getCellData(String sheetName, String colName, int rowNum) {
		try {
			int col_num = 0;
			int index = wb.getSheetIndex(sheetName);
			sheet = wb.getSheetAt(index);
			XSSFRow row = sheet.getRow(0);

			for (int i = 0; i < row.getLastCellNum(); i++) {

				if (row.getCell(i).getStringCellValue().equals(colName)) {
					col_num = i;
					break;
				}

			}

			row = sheet.getRow(rowNum);

			XSSFCell cell = row.getCell(col_num);

			if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
				return cell.getStringCellValue();

			} else if (cell.getCellType() == Cell.CELL_TYPE_BLANK) {
				return "";
			} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
				return String.valueOf(cell.getNumericCellValue());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
