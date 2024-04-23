package com.product.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.product.entity.Product;
@Service
public class ProductServiceImpl {

	@Value(value = "${headers.list}")
	private List<String> heders;

	@Value(value = "${structure.data.filelocation}")
	private String organizedDataFileLocation;

	@Value(value = "${doutefull.data.filelocation}")
	private String douteFullDataFileLocation;

	List<Product> listOfProducts = new ArrayList<>();
	
	List<Product> stractureData=new ArrayList<>();
	List<Product> douteFullData=new ArrayList<>();

	public List<Product> readFileDataAndSegregateDataBasedOnCondition(MultipartFile file) throws IOException {

		XSSFWorkbook workBook = new XSSFWorkbook(file.getInputStream());
		int index = 0;
		XSSFSheet sheet1 = workBook.getSheetAt(0);
		Product p = new Product();
		int rows = sheet1.getLastRowNum();
		int columns = sheet1.getRow(0).getLastCellNum();
		XSSFRow row1 = sheet1.getRow(0);
		XSSFCell cell1 = row1.getCell(0);
		cell1.setCellValue(heders.get(index));
		XSSFCell cell2 = row1.getCell(1);
		cell2.setCellValue(heders.get(++index));
		XSSFCell cell3 = row1.getCell(2);
		cell3.setCellValue(heders.get(++index));
		FileOutputStream fos1 = new FileOutputStream(".\\ExcelFiles\\demo.xlsx");
		workBook.write(fos1);
		
		for (Cell cell : sheet1.getRow(0)) {
			Product product = new Product();
			for (int i = 0; i < 3; i++) {
				if (cell.getCellType() == CellType.STRING) {
					if (i == 0) {
						product.setProductId(cell.getStringCellValue());
					} else if (i == 1) {
						product.setProductName(cell.getStringCellValue());
					} else if (i == 2) {
					}
				}
			}
		}
		
		for (Row row : sheet1) {
			Product product = new Product();
			for (int i = 0; i < 3; i++) {
				Cell cell = row.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
				if (cell.getCellType() == CellType.STRING) {
					if (i == 0) {
						product.setProductId(cell.getStringCellValue());
					} else if (i == 1) {
						product.setProductName(cell.getStringCellValue());
					}
				} else if (cell.getCellType() == CellType.NUMERIC) {
					if (i == 2) {
						product.setProductPrice(cell.getNumericCellValue());
					}
				}
			}
			listOfProducts.add(product);
		}
		
		Map<String, Product> productMap = listOfProducts.stream()
				.collect(Collectors.toMap(Product::getProductId, p1 -> p1,(existing, replacement) -> existing ));
		Hashtable<String, Product> productTable = new Hashtable<>(productMap);
		
		for(Product product:productTable.values()) {
			if(product.getProductPrice()!=null) {
				boolean flag=isDoubleOrNot(product.getProductPrice());
				if(flag) {
					stractureData.add(product);
				}else {
					douteFullData.add(product);
				}
			}
		}
		
		XSSFSheet sheet2=workBook.createSheet("organized data");
		
		 try (Workbook workbook = new XSSFWorkbook()) {
	            Sheet sheet = workbook.createSheet("Products");

	            // Create header row
	            Row headerRow = sheet.createRow(0);
	            headerRow.createCell(0).setCellValue("Product ID");
	            headerRow.createCell(1).setCellValue("Product Name");
	            headerRow.createCell(2).setCellValue("Product Price");

	            // Populate data rows
	            int rowNum = 1;
	            for (Product product : douteFullData) {
	                Row row = sheet.createRow(rowNum++);
	                row.createCell(0).setCellValue(product.getProductId());
	                row.createCell(1).setCellValue(product.getProductName());
	                row.createCell(2).setCellValue(product.getProductPrice());
	            }

	            // Write to file
	            try (FileOutputStream outputStream = new FileOutputStream(douteFullDataFileLocation)) {
	                workbook.write(outputStream);
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    
		
		 System.out.println("done doute full data stored currect path");
		
		
		return stractureData;
	}
	public boolean isDoubleOrNot(Double d) {
		if (d != null && d > 0 && d % 1 != 0) {
			return true;
		}
		return false;
	}
}


