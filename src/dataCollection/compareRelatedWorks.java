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
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;

import pkgRRcalc.IssueListStr;
import pkgRRcalc.WeekCalcStr;


class precisionData {
	String labelType; 
	int actual, avg_curpredict, avg_curmatch; 
}

class RRData {
	double RRVal; 
	String RRLevel; 
}

public class compareRelatedWorks {
	
	public static ArrayList<RRData> astanaData = new ArrayList<RRData>();
	public static ArrayList<RRData> hpData = new ArrayList<RRData>();
	public static ArrayList<precisionData> hpPrecision = new ArrayList<precisionData>(); 
	public static ArrayList<precisionData> astanaPrecision = new ArrayList<precisionData>(); 
	public static String labels [] = {"verylow", "low", "mid", "high", "veryhigh"}; 

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 String astanaFile = "C:/Users/S.M.Didar/Dropbox/Didar DBPC/PhD Research/Winter 2015/ICSE 2016/Experiment/ExpV4/mbostock,d3/"; 
		 String hpFile = "C:/Users/S.M.Didar/Dropbox/Didar DBPC/PhD Research/Winter 2015/ICSE 2016/Experiment/ExpV4/mbostock,d3/HP/"; 
		 String fileName = "weeklyRate.xls"; 
		 String sheetName = "RRVal"; 
		 
		 
		 readExcelFile (astanaFile, fileName, sheetName, astanaData,80); 
		 readExcelFile (hpFile , fileName, sheetName, hpData,80); 
		
//		 System.out.println(astanaData.size()+"ggggggggggggggggggggg");
//		 for (int i=0;i<astanaData.size();i++){
//			System.out.println(astanaData.get(i).RRVal); 
//			System.out.println(astanaData.get(i).RRLevel); 
//		 }
		 
		 
//		 for (int i=0;i<hpData.size();i++){
//				System.out.println(hpData.get(i).RRVal); 
//				System.out.println(hpData.get(i).RRLevel); 
//			 }
		 
		 
		  precisioncalc (); 
		 
		 
		 resultsCalc (astanaFile, hpPrecision, "HPAccuracy"); 
		 resultsCalc (astanaFile, astanaPrecision, "AstanaAccuracy" ); 

	}
	
	
	public static String identifyFiveLabel (Double value){
		if (value>=0 && value<0.2) return "verylow"; 
		else if (value>=0.2 && value<0.4) return "low"; 
		else if (value>=0.4 && value<0.6) return "mid"; 
		else if (value>=0.6 && value<0.8) return "high"; 
		else if (value>=0.8 && value<=1.00) return "veryhigh"; 
		else return "NA";  
	}
	

	
	
	public static void readExcelFile (String address, String fileName, String sheetName, ArrayList <RRData> workingData, int startingRow){
		try {
			
			System.out.println("Reading Excel file sheet astana"+ sheetName);
	        //String tempAddress = address+fileName; 
			FileInputStream file = new FileInputStream(new File(address+""+fileName));		
			//Create Workbook instance holding reference to .xlsx file
	        HSSFWorkbook workbook = new HSSFWorkbook(file);
	        //Get first/desired sheet from the workbook
	        HSSFSheet sheet = workbook.getSheet(sheetName);        
	        
	        
	        Iterator<Row> rowIterator = sheet.iterator();
	        RRData temp = new RRData();
	        int readingCounter=0; 
	        
	        
	        while (rowIterator.hasNext()) {
	        	Row row = rowIterator.next();
	        	readingCounter++; 
	        	
	        	if (readingCounter>=startingRow){
	        		if (row.getCell(3)!=null){
	        	
		        		workingData.add(new RRData()); 
		        		workingData.get(workingData.size()-1).RRVal = row.getCell(3).getNumericCellValue(); 
		        		workingData.get(workingData.size()-1).RRLevel= identifyFiveLabel (workingData.get(workingData.size()-1).RRVal ); 
	        		
//		        		System.out.println(workingData.get(workingData.size()-1).RRVal+ "%%%%%%%%%%%%%%%%%%%%%");
//		        		System.out.println(astanaData.get(astanaData.size()-1).RRVal+ "####################");
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
	

	public static void precisioncalc (){
		
		
		for (int j=0;j<labels.length;j++){
			
			hpPrecision.add(new precisionData());
			hpPrecision.get(j).labelType= labels[j]; 
			
			
			for (int i=0; i<astanaData.size();i++){
				if (astanaData.get(i).RRLevel.equals(labels [j])) hpPrecision.get(j).actual++; 
				
				if (hpData.get(i).RRLevel.equals(labels [j])) hpPrecision.get(j).avg_curpredict++;
				if (astanaData.get(i).RRLevel.equals(labels [j]) && hpData.get(i).RRLevel.equals(labels [j])) hpPrecision.get(j).avg_curmatch++; 
			}
		}
		
		
		for (int j=0;j<labels.length;j++){
			
			astanaPrecision.add(new precisionData());
			astanaPrecision.get(j).labelType= labels[j]; 
			
			
			for (int i=0; i<astanaData.size();i++){
				if (hpData.get(i).RRLevel.equals(labels [j])) astanaPrecision.get(j).actual++; 
				
				if (astanaData.get(i).RRLevel.equals(labels [j])) astanaPrecision.get(j).avg_curpredict++;
				if (astanaData.get(i).RRLevel.equals(labels [j]) && hpData.get(i).RRLevel.equals(labels [j])) astanaPrecision.get(j).avg_curmatch++; 
			}
		}
	}
	
	public static void resultsCalc (String filePath, ArrayList<precisionData> tempPrecision, String fileName ){
	
		double precision, recall, fmeasure;
		
		try{	
			FileOutputStream fileOutAccuracy=new FileOutputStream(filePath+fileName+".xls");
			HSSFWorkbook workbookAccuacy=new HSSFWorkbook();
			HSSFSheet TempAccuracy = workbookAccuacy.createSheet(fileName);
//			HSSFSheet AstanaAccuracy = workbookAccuacy.createSheet("AstanaAccuracy");
		
		
			for (int precisionCounter =0; precisionCounter<labels.length;precisionCounter++ ){
				HSSFRow row= TempAccuracy.createRow(precisionCounter);
				HSSFCell cell;
				 
				cell= row.createCell(0);				cell.setCellValue(tempPrecision.get(precisionCounter).actual);
				cell= row.createCell(1);				cell.setCellValue(tempPrecision.get(precisionCounter).avg_curpredict);
				cell= row.createCell(2);				cell.setCellValue(tempPrecision.get(precisionCounter).avg_curmatch);
				
								
				if (tempPrecision.get(precisionCounter).avg_curpredict==0) {
					cell= row.createCell(3);
					cell.setCellValue("NA");
					precision = -1;
				}
				else {
					precision = (double)(tempPrecision.get(precisionCounter).avg_curmatch) / (double)(tempPrecision.get(precisionCounter).avg_curpredict); 
					cell= row.createCell(3); cell.setCellValue(precision);
				}
				
				
				
				if (tempPrecision.get(precisionCounter).actual==0){
					cell= row.createCell(4); cell.setCellValue("NA");
					recall = -1;
				}
				else {
					recall = (double)(tempPrecision.get(precisionCounter).avg_curmatch) / (double)(tempPrecision.get(precisionCounter).actual); 
					cell= row.createCell(4); cell.setCellValue(recall);
				}
				
				if (precision == -1 || recall == -1){
					cell= row.createCell(5); cell.setCellValue("NA");
				}
				else {
					fmeasure= (2*precision*recall)/(precision+recall);  
					cell= row.createCell(5); cell.setCellValue(fmeasure);
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







