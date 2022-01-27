package com.email.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {

	public static final String SAMPLE_XLS_FILE_PATH = "./sample-xls-file.xls";
	public static final String SAMPLE_XLSX_FILE_PATH = "./sample-xlsx-file.xlsx";

	private static FileInputStream fileInputStream;
	private static XSSFWorkbook workbook;
	private static XSSFSheet worksSheet;
	private static XSSFRow rowHeader;
	private static XSSFRow rowCurrent;
	private static Integer totalRows;

	private static String fileDir = new File(System.getProperty("user.dir")).getAbsolutePath() + File.separator + "src"
			+ File.separator + "test" + File.separator + "resources" + File.separator + "testdata" + File.separator;

//    public static void main(String[] args) throws IOException, InvalidFormatException {
//
//        // Creating a Workbook from an Excel file (.xls or .xlsx)
//        Workbook workbook = WorkbookFactory.create(new File(SAMPLE_XLSX_FILE_PATH));
//
//        // Retrieving the number of sheets in the Workbook
//        System.out.println("Workbook has " + workbook.getNumberOfSheets() + " Sheets : ");
//
//        /*
//           =============================================================
//           Iterating over all the sheets in the workbook (Multiple ways)
//           =============================================================
//        */
//
//        // 1. You can obtain a sheetIterator and iterate over it
//        Iterator<Sheet> sheetIterator = workbook.sheetIterator();
//        System.out.println("Retrieving Sheets using Iterator");
//        while (sheetIterator.hasNext()) {
//            Sheet sheet = sheetIterator.next();
//            System.out.println("=> " + sheet.getSheetName());
//        }
//
//        // 2. Or you can use a for-each loop
//        System.out.println("Retrieving Sheets using for-each loop");
//        for(Sheet sheet: workbook) {
//            System.out.println("=> " + sheet.getSheetName());
//        }
//
//        // 3. Or you can use a Java 8 forEach wih lambda
//        System.out.println("Retrieving Sheets using Java 8 forEach with lambda");
//        workbook.forEach(sheet -> {
//            System.out.println("=> " + sheet.getSheetName());
//        });
//
//        /*
//           ==================================================================
//           Iterating over all the rows and columns in a Sheet (Multiple ways)
//           ==================================================================
//        */
//
//        // Getting the Sheet at index zero
//        Sheet sheet = workbook.getSheetAt(0);
//
//        // Create a DataFormatter to format and get each cell's value as String
//        DataFormatter dataFormatter = new DataFormatter();
//
//        // 1. You can obtain a rowIterator and columnIterator and iterate over them
//        System.out.println("\n\nIterating over Rows and Columns using Iterator\n");
//        Iterator<Row> rowIterator = sheet.rowIterator();
//        while (rowIterator.hasNext()) {
//            Row row = rowIterator.next();
//
//            // Now let's iterate over the columns of the current row
//            Iterator<Cell> cellIterator = row.cellIterator();
//
//            while (cellIterator.hasNext()) {
//                Cell cell = cellIterator.next();
//                String cellValue = dataFormatter.formatCellValue(cell);
//                System.out.print(cellValue + "\t");
//            }
//            System.out.println();
//        }
//
//        // 2. Or you can use a for-each loop to iterate over the rows and columns
//        System.out.println("\n\nIterating over Rows and Columns using for-each loop\n");
//        for (Row row: sheet) {
//            for(Cell cell: row) {
//                String cellValue = dataFormatter.formatCellValue(cell);
//                System.out.print(cellValue + "\t");
//            }
//            System.out.println();
//        }
//
//        // 3. Or you can use Java 8 forEach loop with lambda
//        System.out.println("\n\nIterating over Rows and Columns using Java 8 forEach with lambda\n");
//        sheet.forEach(row -> {
//            row.forEach(cell -> {
//                printCellValue(cell);
//            });
//            System.out.println();
//        });
//
//        // Closing the workbook
//        workbook.close();
//        
//        // Create a Workbook
//        Workbook workbook = new XSSFWorkbook();     // new HSSFWorkbook() for generating `.xls` file
//
//        /* CreationHelper helps us create instances for various things like DataFormat,
//           Hyperlink, RichTextString etc in a format (HSSF, XSSF) independent way */
//        CreationHelper createHelper = workbook.getCreationHelper();
//
//        // Create a Sheet
//        Sheet sheet = workbook.createSheet("Employee");
//
//        // Create a Font for styling header cells
//        Font headerFont = workbook.createFont();
//        headerFont.setBold(true);
//        headerFont.setFontHeightInPoints((short) 14);
//        headerFont.setColor(IndexedColors.RED.getIndex());
//
//        // Create a CellStyle with the font
//        CellStyle headerCellStyle = workbook.createCellStyle();
//        headerCellStyle.setFont(headerFont);
//
//        // Create a Row
//        Row headerRow = sheet.createRow(0);
//
//        // Creating cells
//        for(int i = 0; i < columns.length; i++) {
//            Cell cell = headerRow.createCell(i);
//            cell.setCellValue(columns[i]);
//            cell.setCellStyle(headerCellStyle);
//        }
//
//        // Cell Style for formatting Date
//        CellStyle dateCellStyle = workbook.createCellStyle();
//        dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yyyy"));
//
//        // Create Other rows and cells with employees data
//        int rowNum = 1;
//        for(Employee employee: employees) {
//            Row row = sheet.createRow(rowNum++);
//
//            row.createCell(0)
//                    .setCellValue(employee.getName());
//
//            row.createCell(1)
//                    .setCellValue(employee.getEmail());
//
//            Cell dateOfBirthCell = row.createCell(2);
//            dateOfBirthCell.setCellValue(employee.getDateOfBirth());
//            dateOfBirthCell.setCellStyle(dateCellStyle);
//
//            row.createCell(3)
//                    .setCellValue(employee.getSalary());
//        }
//
//        // Resize all columns to fit the content size
//        for(int i = 0; i < columns.length; i++) {
//            sheet.autoSizeColumn(i);
//        }
//
//        // Write the output to a file
//        FileOutputStream fileOut = new FileOutputStream("poi-generated-file.xlsx");
//        workbook.write(fileOut);
//        fileOut.close();
//
//        workbook.close();
//    }

	private static void printCellValue(Cell cell) {
		switch (cell.getCellTypeEnum()) {
		case BOOLEAN:
			System.out.print(cell.getBooleanCellValue());
			break;
		case STRING:
			System.out.print(cell.getRichStringCellValue().getString());
			break;
		case NUMERIC:
			if (org.apache.poi.ss.usermodel.DateUtil.isCellDateFormatted(cell)) {
				System.out.print(cell.getDateCellValue());
			} else {
				System.out.print(cell.getNumericCellValue());
			}
			break;
		case FORMULA:
			System.out.print(cell.getCellFormula());
			break;
		case BLANK:
			System.out.print("");
			break;
		default:
			System.out.print("");
		}

		System.out.print("\t");
	}

	public String[][] readExcelData(String sheetName, String fileName) {
		String[][] data = null;
		try {

			fileInputStream = new FileInputStream(fileDir + fileName);
			workbook = new XSSFWorkbook(fileInputStream);
			worksSheet = workbook.getSheet(sheetName);
			int totalRows = worksSheet.getLastRowNum();
			int totalCoulmns = worksSheet.getRow(0).getLastCellNum();
			data = new String[totalRows][totalCoulmns];

			for (int i = 1; i <= totalRows; i++) {
				rowHeader = worksSheet.getRow(i);
				if (rowHeader != null) {
					for (int j = 0; j < totalCoulmns; j++) {
						if (rowHeader.getCell(j) != null) {
							String excelColumnData = "";

							switch (rowHeader.getCell(j).getCellTypeEnum()) {
							case BOOLEAN:
								excelColumnData = rowHeader.getCell(j).getBooleanCellValue() + "";
								break;
							case STRING:
								excelColumnData = rowHeader.getCell(j).getRichStringCellValue().getString();
								break;
							case NUMERIC:
								if (org.apache.poi.ss.usermodel.DateUtil.isCellDateFormatted(rowHeader.getCell(j))) {
									excelColumnData = rowHeader.getCell(j).getDateCellValue() + "";
								} else {
									excelColumnData = rowHeader.getCell(j).getNumericCellValue() + "";
								}
								break;
							case FORMULA:
								excelColumnData = rowHeader.getCell(j).getCellFormula();
								break;
							case BLANK:
								excelColumnData = "";
								break;
							default:
								excelColumnData = rowHeader.getCell(j).getStringCellValue();
							}
							data[i - 1][j] = excelColumnData;
						}
					}
				}
			}
		} catch (Exception e) {
		}

		try {
			fileInputStream.close();
			workbook.close();
		} catch (Exception e) {
		}
		return data;
	}

	private static String[] columns = { "Name", "Email", "Date Of Birth", "Salary" };

	private static List<Employee> employees = new ArrayList<>();

	static {
		Calendar dateOfBirth = Calendar.getInstance();
		dateOfBirth.set(1992, 7, 21);
		employees.add(new Employee("Rajeev Singh", "rajeev@example.com", dateOfBirth.getTime(), 1200000.0));

		dateOfBirth.set(1965, 10, 15);
		employees.add(new Employee("Thomas cook", "thomas@example.com", dateOfBirth.getTime(), 1500000.0));

		dateOfBirth.set(1987, 4, 18);
		employees.add(new Employee("Steve Maiden", "steve@example.com", dateOfBirth.getTime(), 1800000.0));
	}

	// Example to modify an existing excel file
	private static void modifyExistingWorkbook() throws InvalidFormatException, IOException {
		// Obtain a workbook from the excel file
		Workbook workbook = WorkbookFactory.create(new File("existing-spreadsheet.xlsx"));

		// Get Sheet at index 0
		Sheet sheet = workbook.getSheetAt(0);

		// Get Row at index 1
		Row row = sheet.getRow(1);

		// Get the Cell at index 2 from the above row
		Cell cell = row.getCell(2);

		// Create the cell if it doesn't exist
		if (cell == null)
			cell = row.createCell(2);

		// Update the cell's value
		cell.setCellType(CellType.STRING);
		cell.setCellValue("Updated Value");

		// Write the output to a file
		FileOutputStream fileOut = new FileOutputStream("existing-spreadsheet.xlsx");
		workbook.write(fileOut);
		fileOut.close();

		// Closing the workbook
		workbook.close();
	}
}

class Employee {
	private String name;

	private String email;

	private Date dateOfBirth;

	private double salary;

	public Employee(String name, String email, Date dateOfBirth, double salary) {
		this.name = name;
		this.email = email;
		this.dateOfBirth = dateOfBirth;
		this.salary = salary;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}
}
