package dataCollection;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class combineAccuracyData {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int globalRowIterator=0; 
		String filePath = "C:/Users/S.M.Didar/Dropbox/Didar DBPC/PhD Research/Winter 2015/ICSE 2016/Experiment/ExpV4/"; 
		String fileName = "AstanaAccuracy.xls"; 
		String sheetName = "AstanaAccuracy"; 
		int maxCells = 6; 
		
		String folder []= new String [16];
		folder [0]="Automattic,socket.io";
		folder [1]="berkshelf,berkshelf";
		folder [2]="cgeo,cgeo";
		folder [3]="clinton-hall,nzbToMedia";
		folder [4]="FortAwesome,Font-Awesome";
		folder [5]="GoldenCheetah,GoldenCheetah";
		folder [6]="gravitystorm,openstreetmap-carto";
		folder [7]="hawtio,hawtio";
		folder [8]="jashkenas,backbone";
		folder [9]="joey711,phyloseq";
		folder [10]="mbostock,d3";
		folder [11]="moment,moment";
		folder [12]="mybb,mybb";
		folder [13]="Orientechnologies,Orientdb";
		folder [14]="python-pillow,Pillow";
		folder [15]="travis-ci,travis-ci";
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		try{	
			FileOutputStream fileOutAccuracy=new FileOutputStream(filePath+"combinedAstanaAccuracy.xls");
			HSSFWorkbook workbookAccuacy=new HSSFWorkbook();
			HSSFSheet TempAccuracy = workbookAccuacy.createSheet("Accuracy");
//			HSSFSheet AstanaAccuracy = workbookAccuacy.createSheet("AstanaAccuracy");
			
			
		
			
			
		for (int folderIterator=0;folderIterator<folder.length;folderIterator++)	{
			globalRowIterator++; 
			HSSFRow rowWR= TempAccuracy.createRow(globalRowIterator);
        	HSSFCell cellWR= rowWR.createCell(0);
        	cellWR.setCellValue(folder[folderIterator]);
        	
			try{
				
				System.out.println("Reading Excel file sheet astana"+ folder[folderIterator]);
		        //String tempAddress = address+fileName; 
				FileInputStream file = new FileInputStream(new File(filePath+folder[folderIterator]+"/"+fileName));		
				//Create Workbook instance holding reference to .xlsx file
		        HSSFWorkbook workbook = new HSSFWorkbook(file);
		        //Get first/desired sheet from the workbook
		        HSSFSheet sheet = workbook.getSheet(sheetName);        
		        
		        
		        Iterator<Row> rowIterator = sheet.iterator();
		        	        
		        while (rowIterator.hasNext()) {
		        	Row row = rowIterator.next();
		        	globalRowIterator++; 
		        	rowWR= TempAccuracy.createRow(globalRowIterator);
		        	
				
		        	for (int i=0;i<maxCells;i++){
		        		if (row.getCell(i)!=null){
		        			cellWR= rowWR.createCell(i);				
		        			if (row.getCell(i).getCellType()==Cell.CELL_TYPE_STRING) {
		        				cellWR.setCellValue(row.getCell(i).getStringCellValue());
		        				System.out.println(i);
		        			}
		        			else if (row.getCell(i).getCellType()==Cell.CELL_TYPE_ERROR) {
		        				cellWR.setCellValue("ERROR");
		        			}
		        			
		        			else {
		        				cellWR.setCellValue(row.getCell(i).getNumericCellValue());
		        				System.out.println(i);
		        			}
		        		}	
		        	}
		        }
		        
		        file.close();
			}
			catch (Exception e){
				e.printStackTrace (); 
				System.out.println("Problem in reading");
			}
		        
			
			
		}
			
			
			
	    	
			workbookAccuacy.write(fileOutAccuracy);
			fileOutAccuracy.flush();
			fileOutAccuracy.close();
			System.out.println("Success: written "+ "Accuracy.xls");
			
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("Error: writing "+ "Accuracy.xls");
		}catch (IOException e2){
			e2.printStackTrace();
			System.out.println("Error: writing "+ "Accuracy.xls");
		}
	}
}
