package dataInput;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;

import pkgRRcalc.WeekCalcStr;

import java.io.*; 
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class createTopicFiles {
	
	
	static String strBegindDate= "10/12/2012"; 
	static String strEndDate = "24/04/2013"; 
	static Date startDate, stopDate, weekStartDate, weekStopDate, tempDate, issueCreatedAt ; 	
	static String filePath, outputFileName, strDateMonth; 
	static ArrayList <commentDBListStructure> plexinaCommentDBList = new ArrayList <commentDBListStructure> ();
	static int fileIterator =0; 

	
	
	
	
	
	
	
	public void createTopics (String filePath, String repoOwner, String repoName, String strBegindDate, String strEndDate, ArrayList<WeekCalcStr> tempWeekData){
	String inputFileName = filePath+repoOwner+","+repoName+".xls";  
	DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
	Calendar c = Calendar.getInstance();    
	
	try {
		startDate = format.parse(strBegindDate);
		stopDate = format.parse(strEndDate); 
	} catch (ParseException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	
	readExcelFile (inputFileName); 
	System.out.println("Reading excel file successfully done");
	
	
	for (int j=0;j<tempWeekData.size(); j++){
		
		outputFileName = filePath+"/Topic preprocess files/"+tempWeekData.get(j).getWeekNum()+".txt";
		try {
			PrintStream out = new PrintStream(new FileOutputStream(outputFileName));
			System.setOut(out);
			for (int i=0;i<plexinaCommentDBList.size();i++){
				tempDate = plexinaCommentDBList.get(i).commentCreatedAt; 
				if (tempDate.after(tempWeekData.get(j).getWeekStart()) && tempDate.before(tempWeekData.get(j).getWeekEnd())){
					out.println(plexinaCommentDBList.get(i).commentBody);
				}
				
			}
			out.close();
		}		
				
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(outputFileName + " Caused problem");
		}
		
		System.out.println(" Created successfully");
		//outputFileName = filePath+" "+c.get(Calendar.DAY_OF_MONTH) +" "+  (c.get(Calendar.MONTH)+1)+" "+  c.get(Calendar.YEAR)+ ".txt"; 
		
	}
}


public static void readExcelFile (String fileName){
	try {
		
		System.out.println("Reading Excel file");
        FileInputStream file = new FileInputStream(new File(fileName));		
		//Create Workbook instance holding reference to .xlsx file
        HSSFWorkbook workbook = new HSSFWorkbook(file);
        //Get first/desired sheet from the workbook
        HSSFSheet sheet = workbook.getSheetAt(0);
        
        //Iterate through each rows one by one
        Iterator<Row> rowIterator = sheet.iterator();
        while (rowIterator.hasNext()) {
        	Row row = rowIterator.next();
        	plexinaCommentDBList.add( new commentDBListStructure()); 
        	
        	if (row.getCell(6)==null){
        		if (row.getCell(1)!=null){
        			plexinaCommentDBList.get(row.getRowNum()).commentCreatedAt = DateUtil.getJavaDate(row.getCell(1).getNumericCellValue());
        			issueCreatedAt= DateUtil.getJavaDate(row.getCell(1).getNumericCellValue());
        		}
        		
        		else {
        			plexinaCommentDBList.get(row.getRowNum()).commentCreatedAt = issueCreatedAt;
        		}
        	}
        	else {
        		plexinaCommentDBList.get(row.getRowNum()).commentCreatedAt = DateUtil.getJavaDate(row.getCell(6).getNumericCellValue());
        	}
        	
        	if (row.getCell(7)!=null){
        		plexinaCommentDBList.get(row.getRowNum()).commentBody = row.getCell(7).getStringCellValue();
        	}
        	//plexinaCommentDBList.get(row.getRowNum()).commentUrl = row.getCell(8).getStringCellValue();
        	//plexinaCommentDBList.get(row.getRowNum()).commentUser = row.getCell(9).getNumericCellValue();
 //        	plexinaCommentDBList.get(row.getRowNum()).commentID = row.getCell(5).getNumericCellValue();
////        	System.out.println(row.getRowNum());
//        	plexinaCommentDBList.get(row.getRowNum()).commentCreatedAt = DateUtil.getJavaDate(row.getCell(6).getNumericCellValue());
//        	plexinaCommentDBList.get(row.getRowNum()).commentBody = row.getCell(7).getStringCellValue();
//        	plexinaCommentDBList.get(row.getRowNum()).commentUrl = row.getCell(8).getStringCellValue();
//        	plexinaCommentDBList.get(row.getRowNum()).commentUser = row.getCell(9).getNumericCellValue();
        }	
        	
        	  	
        file.close();
	}
	catch (Exception e){
		e.printStackTrace (); 
		System.out.println("Problem in reading");
	}
	
	
            	
} // end of read excel method





} // End of class 