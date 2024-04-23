package com.product.service.impl;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.product.repo.ProductRepo;
import com.product.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepo repo;
	
	@Override
	public String readData(MultipartFile file) throws IOException {
		
		XSSFWorkbook workBook=new XSSFWorkbook(file.getInputStream());
		
		XSSFSheet sheet1=workBook.getSheetAt(0);
		
		int rows=sheet1.getLastRowNum();
		int cols=sheet1.getRow(0).getLastCellNum();
		
		for(int r=0;r<rows;r++) {
			
			XSSFRow row=sheet1.getRow(r);
			
			for(int c=0;c<cols;c++) {
				XSSFCell cell=row.getCell(c);	
				switch(cell.getCellType()) {
				case STRING :	
					System.out.println(  
					cell.getStringCellValue());
					cell.setCellValue("Jahn");	
					break;
					
				case NUMERIC:
					System.out.println(cell.getNumericCellValue());
					
					if(isDouble(cell.getNumericCellValue())) {
						
					}
					break;
				 default:
					break;   
				}
				
			}
		}
		
		FileOutputStream fos=new FileOutputStream("C:\\Users\\talar\\OneDrive\\Desktop\\Acintyo Tech\\products.xlsx");
		
		workBook.write(fos);
		
		workBook.close();
		
		fos.close();
		
		return "data loaded";
	}
	public boolean isDouble(Double d) 
	{
		if(d>0&&d%1!=0) {
			return true;
		}
		
		return false;
	}

}
