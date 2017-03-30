package dataInput;

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

import dataInput.precisionRecallMsr.classifyLabel;
import dataInput.precisionRecallMsr.precisionData;
import pkgRRcalc.IssueListStr;

public class finalResultsWR {

	
	precisionRecallMsr prMSR = new precisionRecallMsr(); 
	ArrayList <finalResultListStr> list_finalResultListStr = new  ArrayList <finalResultListStr> ();
	
	
	
	ArrayList <classifyLabel> RRLabel_RR = new ArrayList<classifyLabel>(); 
	ArrayList <classifyLabel> RRLabel_BFR = new ArrayList<classifyLabel>(); 
	ArrayList <classifyLabel> RRLabel_FCR = new ArrayList<classifyLabel>(); 
	ArrayList <classifyLabel> RRLabel_ICR = new ArrayList<classifyLabel>(); 
	ArrayList <classifyLabel> RRLabel_PCR = new ArrayList<classifyLabel>(); 
	ArrayList <classifyLabel> RRLabel_DFR = new ArrayList<classifyLabel>(); 
	ArrayList <classifyLabel> RRLabel_CCR = new ArrayList<classifyLabel>(); 
	ArrayList <classifyLabel> RRLabel_AggregatedRR = new ArrayList<classifyLabel>(); 
	ArrayList <classifyLabel> RRLabel_RegressionRR = new ArrayList<classifyLabel>(); 
	
	
	ArrayList <precisionData> precisionData_RR = new ArrayList<precisionData>(); 
	ArrayList <precisionData> precisionData_BFR = new ArrayList<precisionData>(); 
	ArrayList <precisionData> precisionData_FCR = new ArrayList<precisionData>(); 
	ArrayList <precisionData> precisionData_ICR = new ArrayList<precisionData>(); 
	ArrayList <precisionData> precisionData_PCR = new ArrayList<precisionData>(); 
	ArrayList <precisionData> precisionData_DFR = new ArrayList<precisionData>(); 
	ArrayList <precisionData> precisionData_CCR = new ArrayList<precisionData>();
	ArrayList <precisionData> precisionData_AggregatedRR = new ArrayList<precisionData>(); 
	ArrayList <precisionData> precisionData_RegressionRR = new ArrayList<precisionData>(); 
	
	
 
	class finalResultListStr {
		double currentWeekNum; 
		double resultWeek; 
		double resultEucl; 
		double curRates[]=new double [9]; //BFR, DFR, ICR, PCR, CCR, FCR, RR, aggregatedRR, regressionRR;
		double ftrRates[]=new double [9];//ftrBFR, ftrDFR, ftrICR, ftrPCR, ftrCCR, ftrFCR, ftrRR, aggregatedRR, regressionRR; 
		double orgRates[]=new double [9];//anyBFR, anyDFR, anyICR, anyPCR, anyCCR, anyFCR, anyRR, aggregatedRR, regressionRR; 
		
	}
	
	class weekRateInputListStr {
		double weekNum; 
		double resultData; 
	}
	
	
	
	
	
	
	
	
	
	public void finalResultWRMain (String filePath, String fileName){
		readTopResults (filePath); 
		readWeekRateSheets (filePath, "BFR", 0, 10); 
		readWeekRateSheets (filePath, "FCR", 0, 10);
		readWeekRateSheets (filePath, "ICR", 0, 10);
		readWeekRateSheets (filePath, "PCR", 0, 10);
		readWeekRateSheets (filePath, "DFR", 0, 10);
		readWeekRateSheets (filePath, "CCR", 0, 5);
		readWeekRateSheets (filePath, "RRVal", 0, 3);
		
		calculateAggregateRegressionRR (); 
		
		
		outputFinalResults (filePath, fileName); 
		
		
		
		
		
		
		prMSR.precisionRecallMsrMain(list_finalResultListStr, "RRVal", RRLabel_RR, precisionData_RR);
		prMSR.precisionRecallMsrMain(list_finalResultListStr, "BFR", RRLabel_BFR, precisionData_BFR);
		prMSR.precisionRecallMsrMain(list_finalResultListStr, "FCR", RRLabel_FCR, precisionData_FCR);
		prMSR.precisionRecallMsrMain(list_finalResultListStr, "ICR", RRLabel_ICR, precisionData_ICR);
		prMSR.precisionRecallMsrMain(list_finalResultListStr, "PCR", RRLabel_PCR, precisionData_PCR);
		prMSR.precisionRecallMsrMain(list_finalResultListStr, "DFR", RRLabel_DFR, precisionData_DFR);
		prMSR.precisionRecallMsrMain(list_finalResultListStr, "CCR", RRLabel_CCR, precisionData_CCR);
		prMSR.precisionRecallMsrMain(list_finalResultListStr, "AggregatedRR", RRLabel_AggregatedRR, precisionData_AggregatedRR);
		prMSR.precisionRecallMsrMain(list_finalResultListStr, "RegressionRR", RRLabel_RegressionRR, precisionData_RegressionRR);
		
		outputAccuracy (filePath); 
		
		

		
		
		
	}
	
	
	
	
	public void readTopResults (String filePath){
		try {
			//FileInputStream topResultsfile = new FileInputStream(new File(filePath+"/Results/"+"Top Results.xls"));
			FileInputStream topResultsfile = new FileInputStream(new File(filePath+"Top Results.xls"));
			HSSFWorkbook topResultsWorkbook = new HSSFWorkbook(topResultsfile);
		    //Get first/desired sheet from the workbook
		    HSSFSheet topResultsSheet = topResultsWorkbook.getSheet("results");
		    
		    Iterator<Row> rowIterator = topResultsSheet.iterator();
		     
	        
	        while (rowIterator.hasNext()) {
	        	Row row = rowIterator.next();
	        	
	        	finalResultListStr temp_finalResultListSt= new finalResultListStr(); 
	        	if (row.getCell(0)!=null) temp_finalResultListSt.currentWeekNum= row.getCell(0).getNumericCellValue(); 
	        	if (row.getCell(1)!=null) temp_finalResultListSt.resultWeek= row.getCell(1).getNumericCellValue(); 
	        	if (row.getCell(2)!=null) temp_finalResultListSt.resultEucl= row.getCell(2).getNumericCellValue(); 
	        	
	        	list_finalResultListStr.add(temp_finalResultListSt); 
	        }
	        
	        topResultsfile.close();
	        
	        
	    } catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	
	
	public void readWeekRateSheets (String filePath, String sheetName, int weeknum, int resultData){
		try {
			FileInputStream weeklyRateFile = new FileInputStream(new File(filePath+"weeklyRate.xls"));
			HSSFWorkbook weeklyRateWorkbook = new HSSFWorkbook(weeklyRateFile);
		    //Get first/desired sheet from the workbook
		    HSSFSheet weeklyRateSheet = weeklyRateWorkbook.getSheet(sheetName);
		    
		    Iterator<Row> rowIterator = weeklyRateSheet.iterator();
		   
		    ArrayList <weekRateInputListStr> list_weekRateInputListStr  = new  ArrayList <weekRateInputListStr> (); 
	        
	        while (rowIterator.hasNext()) {
	        	Row row = rowIterator.next();
	        	
	        	weekRateInputListStr temp_weekRateInputListStr= new weekRateInputListStr(); 
	        	if (row.getCell(weeknum)!=null) temp_weekRateInputListStr.weekNum= row.getCell(weeknum).getNumericCellValue(); 
	        	if (row.getCell(resultData)!=null) temp_weekRateInputListStr.resultData= row.getCell(resultData).getNumericCellValue(); 
	        	
	        	
	        	list_weekRateInputListStr.add(temp_weekRateInputListStr); 
	        }
	        
	        weeklyRateFile.close();
	        
	        fill_list_finalResultListStr (list_weekRateInputListStr, sheetName); 
	    
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public void  fill_list_finalResultListStr (ArrayList <weekRateInputListStr> list_weekRateInputListStr, String sheetName){
		
		
		for (int i=0;i<list_finalResultListStr.size();i++ ){
			double currentweekdata=0,futureweekdata=0,analysisWeekdata=0; 
			
			double resultweek = list_finalResultListStr.get(i).resultWeek; 
			double currentweek= list_finalResultListStr.get(i).currentWeekNum;  
			
			for (int j=0;j<list_weekRateInputListStr.size();j++){
				if (list_weekRateInputListStr.get(j).weekNum==resultweek){
					currentweekdata= list_weekRateInputListStr.get(j).resultData;
				}
				if (resultweek+1<currentweek){
					if (list_weekRateInputListStr.get(j).weekNum==(resultweek+1)){
						futureweekdata= list_weekRateInputListStr.get(j).resultData;
					}
				}
				if (list_weekRateInputListStr.get(j).weekNum==currentweek){
					analysisWeekdata= list_weekRateInputListStr.get(j).resultData;
				}
			}
			
			
			
			
			int varToConsider=-1; 
			
			if (sheetName.equals("BFR")) varToConsider=0; 			
			else if (sheetName.equals("DFR")) varToConsider=1;
			else if (sheetName.equals("ICR")) varToConsider=2;
			else if (sheetName.equals("PCR")) varToConsider=3;
			else if (sheetName.equals("CCR")) varToConsider=4;
			else if (sheetName.equals("FCR")) varToConsider=5;
			else if (sheetName.equals("RRVal")) varToConsider=6;
			
		
			list_finalResultListStr.get(i).curRates[varToConsider]= currentweekdata; 
			list_finalResultListStr.get(i).ftrRates[varToConsider]= futureweekdata; 
			list_finalResultListStr.get(i).orgRates[varToConsider]= analysisWeekdata;
			
			
			
			
			
//			if (sheetName.equals("BFR")) {
//				list_finalResultListStr.get(i).BFR = currentweekdata; 
//				list_finalResultListStr.get(i).ftrBFR= futureweekdata; 
//				list_finalResultListStr.get(i).anyBFR= analysisWeekdata; 
//			}
//			else if (sheetName.equals("FCR")){
//				list_finalResultListStr.get(i).FCR = currentweekdata; 
//				list_finalResultListStr.get(i).ftrFCR= futureweekdata;  
//				list_finalResultListStr.get(i).anyFCR= analysisWeekdata; 
//			}
//			else if (sheetName.equals("ICR")) {
//				list_finalResultListStr.get(i).ICR = currentweekdata; 
//				list_finalResultListStr.get(i).ftrICR= futureweekdata;  
//				list_finalResultListStr.get(i).anyICR= analysisWeekdata; 
//			}
//			else if (sheetName.equals("PCR")) {
//				list_finalResultListStr.get(i).PCR = currentweekdata; 
//				list_finalResultListStr.get(i).ftrPCR= futureweekdata; 
//				list_finalResultListStr.get(i).anyPCR= analysisWeekdata; 
//			}
//			else if (sheetName.equals("CCR")) {
//				list_finalResultListStr.get(i).CCR = currentweekdata; 
//				list_finalResultListStr.get(i).ftrCCR= futureweekdata; 
//				list_finalResultListStr.get(i).anyCCR= analysisWeekdata; 
//			}
//			else if (sheetName.equals("DFR")) {
//				list_finalResultListStr.get(i).DFR = currentweekdata; 
//				list_finalResultListStr.get(i).ftrDFR= futureweekdata; 
//				list_finalResultListStr.get(i).anyDFR= analysisWeekdata; 
//			}
//			else if (sheetName.equals("RRVal")) {
//				list_finalResultListStr.get(i).RR = currentweekdata; 
//				list_finalResultListStr.get(i).ftrRR= futureweekdata;  
//				list_finalResultListStr.get(i).anyRR= analysisWeekdata; 
//			}
		}
	}


	public void outputFinalResults (String filePath, String fileName){
		
		try {
			FileOutputStream fileOut=new FileOutputStream(filePath+fileName);
			HSSFWorkbook workbook=new HSSFWorkbook();
			HSSFSheet curwkWorksheet = workbook.createSheet("current week");
			HSSFSheet ftrwkWorksheet = workbook.createSheet("future week");
			HSSFSheet anlwkWorksheet = workbook.createSheet("analysis week");
			HSSFRow row; 
			HSSFCell cell;
			
			
			
			
			for (int i=0;i<list_finalResultListStr.size();i++){
				
				row= curwkWorksheet.createRow(i); 
				cell= row.createCell(0);	cell.setCellValue(list_finalResultListStr.get(i).currentWeekNum);
				
				cell= row.createCell(2);	cell.setCellValue(list_finalResultListStr.get(i).resultWeek);
				cell= row.createCell(3);	cell.setCellValue(list_finalResultListStr.get(i).resultEucl);
				
				cell= row.createCell(5);	cell.setCellValue(list_finalResultListStr.get(i).curRates[6]);
				//BFR, DFR, ICR, PCR, CCR, FCR, RR;
				cell= row.createCell(7);	cell.setCellValue(list_finalResultListStr.get(i).curRates[0]);
				cell= row.createCell(8);	cell.setCellValue(list_finalResultListStr.get(i).curRates[1]);
				cell= row.createCell(9);	cell.setCellValue(list_finalResultListStr.get(i).curRates[2]);
				cell= row.createCell(10);	cell.setCellValue(list_finalResultListStr.get(i).curRates[3]);
				cell= row.createCell(11);	cell.setCellValue(list_finalResultListStr.get(i).curRates[4]);
				cell= row.createCell(12);	cell.setCellValue(list_finalResultListStr.get(i).curRates[5]);
								
				
				//aggregated RR calculation and filling up arraylist 
//				list_finalResultListStr.get(i).curRates[7] = (list_finalResultListStr.get(i).curRates[0]+
//																list_finalResultListStr.get(i).curRates[1]+
//																	list_finalResultListStr.get(i).curRates[2]+
//																		list_finalResultListStr.get(i).curRates[3]+
//																			list_finalResultListStr.get(i).curRates[4]+
//																				list_finalResultListStr.get(i).curRates[5])/6; 
			
				cell= row.createCell(14);	cell.setCellValue(list_finalResultListStr.get(i).curRates[7]);
				
				//regression RR calculation and filling up arraylist
//				list_finalResultListStr.get(i).curRates[8] = (1*list_finalResultListStr.get(i).curRates[0])+
//																(1*list_finalResultListStr.get(i).curRates[1])+
//																	(1*list_finalResultListStr.get(i).curRates[2])+
//																		(1*list_finalResultListStr.get(i).curRates[3])+
//																				(1*list_finalResultListStr.get(i).curRates[4])+
//																						(1*list_finalResultListStr.get(i).curRates[5]); 
				
				cell= row.createCell(15);	cell.setCellValue(list_finalResultListStr.get(i).curRates[8]);
				
				
				
			}
			
			for (int i=0;i<list_finalResultListStr.size();i++){
				
				row= ftrwkWorksheet.createRow(i); 
				cell= row.createCell(0);	cell.setCellValue(list_finalResultListStr.get(i).currentWeekNum);
				
				cell= row.createCell(2);	cell.setCellValue(list_finalResultListStr.get(i).resultWeek);
				cell= row.createCell(3);	cell.setCellValue(list_finalResultListStr.get(i).resultEucl);
				cell= row.createCell(4);	cell.setCellValue(list_finalResultListStr.get(i).resultWeek+1);
				
				cell= row.createCell(6);	cell.setCellValue(list_finalResultListStr.get(i).ftrRates[6]);
				//BFR, DFR, ICR, PCR, CCR, FCR, RR;
				cell= row.createCell(8);	cell.setCellValue(list_finalResultListStr.get(i).ftrRates[0]);
				cell= row.createCell(9);	cell.setCellValue(list_finalResultListStr.get(i).ftrRates[1]);
				cell= row.createCell(10);	cell.setCellValue(list_finalResultListStr.get(i).ftrRates[2]);
				cell= row.createCell(11);	cell.setCellValue(list_finalResultListStr.get(i).ftrRates[3]);
				cell= row.createCell(12);	cell.setCellValue(list_finalResultListStr.get(i).ftrRates[4]);
				cell= row.createCell(13);	cell.setCellValue(list_finalResultListStr.get(i).ftrRates[5]);
				
				
				//aggregated RR calculation and filling up arraylist 
//				list_finalResultListStr.get(i).ftrRates[7] = (list_finalResultListStr.get(i).ftrRates[0]+
//																list_finalResultListStr.get(i).ftrRates[1]+
//																	list_finalResultListStr.get(i).ftrRates[2]+
//																		list_finalResultListStr.get(i).ftrRates[3]+
//																			list_finalResultListStr.get(i).ftrRates[4]+
//																				list_finalResultListStr.get(i).ftrRates[5])/6; 
			
				cell= row.createCell(15);	cell.setCellValue(list_finalResultListStr.get(i).ftrRates[7]);
				
				//regression RR calculation and filling up arraylist
//				list_finalResultListStr.get(i).ftrRates[8] = (1*list_finalResultListStr.get(i).ftrRates[0])+
//																(1*list_finalResultListStr.get(i).ftrRates[1])+
//																	(1*list_finalResultListStr.get(i).ftrRates[2])+
//																		(1*list_finalResultListStr.get(i).ftrRates[3])+
//																				(1*list_finalResultListStr.get(i).ftrRates[4])+
//																						(1*list_finalResultListStr.get(i).ftrRates[5]); 
				
				cell= row.createCell(16);	cell.setCellValue(list_finalResultListStr.get(i).ftrRates[8]);
				
			}
			
			double tempweektracker=0;
			int tempWeekCounter=0; 
			for (int i=0;i<list_finalResultListStr.size();i++){
				
				if(tempweektracker != list_finalResultListStr.get(i).currentWeekNum){
					tempweektracker= list_finalResultListStr.get(i).currentWeekNum; 
					row= anlwkWorksheet.createRow(tempWeekCounter); 
					cell= row.createCell(0);	cell.setCellValue(list_finalResultListStr.get(i).currentWeekNum);
					
					cell= row.createCell(2);	cell.setCellValue(list_finalResultListStr.get(i).orgRates[6]);
					
					cell= row.createCell(3);	cell.setCellValue(list_finalResultListStr.get(i).orgRates[0]);
					cell= row.createCell(4);	cell.setCellValue(list_finalResultListStr.get(i).orgRates[1]);
					cell= row.createCell(5);	cell.setCellValue(list_finalResultListStr.get(i).orgRates[2]);
					cell= row.createCell(6);	cell.setCellValue(list_finalResultListStr.get(i).orgRates[3]);
					cell= row.createCell(7);	cell.setCellValue(list_finalResultListStr.get(i).orgRates[4]);
					cell= row.createCell(8);	cell.setCellValue(list_finalResultListStr.get(i).orgRates[5]);
					tempWeekCounter++;
				}			
			}
			
			workbook.write(fileOut);
			fileOut.flush();
			fileOut.close();
			System.out.println("Success: written "+ fileName);
			
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("Error: writing "+ fileName);
		}catch (IOException e2){
			e2.printStackTrace();
			System.out.println("Error: writing "+ fileName);
		}
		
		
		
		
	}
	
	
	
	
	
	public void calculateAggregateRegressionRR (){
		
		double averageBFR=0, averageDFR=0, averageICR=0, averagePCR=0, averageCCR=0, averageFCR=0;
		double regressionCopnstants []= new double [7];
		double divisionValue = 30; //60;  // in normal cases
		
		
		// for training set upto 60 weeks 
		
//		regressionCopnstants [0] = 0.126; 
//		regressionCopnstants [1] = 0.1580; 
//		regressionCopnstants [2] = 0.134; 
//		regressionCopnstants [3] = 0.168; 
//		regressionCopnstants [4] = 0.130; 
//		regressionCopnstants [5] = 0.229; 
//		regressionCopnstants [6] = 0.001010; 
//		
		
		// for training set upto 80 weeks 
		
		regressionCopnstants [0] = 1; 
		regressionCopnstants [1] = 1; 
		regressionCopnstants [2] = 0; 
		regressionCopnstants [3] = 0; 
		regressionCopnstants [4] = 1; 
		regressionCopnstants [5] = 0; 
		regressionCopnstants [6] = 0; 
		
	//	averageBFR=0; averageDFR=0; averageICR=0;  averagePCR=0; averageCCR=0; averageFCR=0;		
		
		// for training set upto 70 weeks 
		
//		regressionCopnstants [0] = 0.123; 
//		regressionCopnstants [1] = 0.1604; 
//		regressionCopnstants [2] = 0.1680; 
//		regressionCopnstants [3] = 0.135; 
//		regressionCopnstants [4] = 0.1330; 
//		regressionCopnstants [5] = 0.230; 
//		regressionCopnstants [6] = -0.0009378; 
//		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		for (int i=0;i<list_finalResultListStr.size();i=i+10){
			for (int j=i;j<i+10;j++){
				averageBFR=averageBFR+list_finalResultListStr.get(j).curRates[0]; 
				averageDFR=averageDFR+list_finalResultListStr.get(j).curRates[1]; 
				averageICR=averageICR+list_finalResultListStr.get(j).curRates[2]; 
				averagePCR=averagePCR+list_finalResultListStr.get(j).curRates[3]; 
				averageCCR=averageCCR+list_finalResultListStr.get(j).curRates[4]; 
				averageFCR=averageFCR+list_finalResultListStr.get(j).curRates[5]; 
			}
			
			for (int j=i;j<i+10;j++){
				list_finalResultListStr.get(j).curRates[7]=(averageBFR+ averageDFR+ averageICR+ averagePCR+averageCCR+averageFCR)/60; 
			//	System.out.println("agg cur rate" + list_finalResultListStr.get(j).curRates[7]);
				
				///in real regression it should be 10 not 60 for division
				list_finalResultListStr.get(j).curRates[8]=(regressionCopnstants [0]*averageBFR+ regressionCopnstants [1]*averageDFR+ regressionCopnstants [2]*averageICR+ regressionCopnstants [3]*averagePCR+regressionCopnstants [4]*averageCCR+regressionCopnstants [5]*averageFCR)/divisionValue + regressionCopnstants [6];
			}
			averageBFR=0; averageDFR=0; averageICR=0;  averagePCR=0; averageCCR=0; averageFCR=0;		
		}
		
		averageBFR=0; averageDFR=0; averageICR=0;  averagePCR=0; averageCCR=0; averageFCR=0;		
		
		for (int i=0;i<list_finalResultListStr.size();i=i+10){
			for (int j=i;j<i+10;j++){
				averageBFR=averageBFR+list_finalResultListStr.get(j).ftrRates[0]; 
				averageDFR=averageDFR+list_finalResultListStr.get(j).ftrRates[1]; 
				averageICR=averageICR+list_finalResultListStr.get(j).ftrRates[2]; 
				averagePCR=averagePCR+list_finalResultListStr.get(j).ftrRates[3]; 
				averageCCR=averageCCR+list_finalResultListStr.get(j).ftrRates[4]; 
				averageFCR=averageFCR+list_finalResultListStr.get(j).ftrRates[5]; 
			}
			
			for (int j=i;j<i+10;j++){
				list_finalResultListStr.get(j).ftrRates[7]=(averageBFR+ averageDFR+ averageICR+ averagePCR+averageCCR+averageFCR)/60; 
		//		System.out.println("agg cur rate" + list_finalResultListStr.get(j).ftrRates[7]);
				list_finalResultListStr.get(j).ftrRates[8]=(regressionCopnstants [0]*averageBFR+ regressionCopnstants [1]*averageDFR+ regressionCopnstants [2]*averageICR+ regressionCopnstants [3]*averagePCR+regressionCopnstants [4]*averageCCR+regressionCopnstants [5]*averageFCR)/divisionValue+regressionCopnstants [6];
			}
			averageBFR=0; averageDFR=0; averageICR=0;  averagePCR=0; averageCCR=0; averageFCR=0;		
			
			for (int j=i;j<i+10;j++){
				list_finalResultListStr.get(j).orgRates[7]=list_finalResultListStr.get(j).orgRates[6];
				list_finalResultListStr.get(j).orgRates[8]=list_finalResultListStr.get(j).orgRates[6];
			}
		}
		
		
		
		
		
		
	}
	
	
	void outputAccuracy (String filePath){
		try{	
			FileOutputStream fileOutAccuracy=new FileOutputStream(filePath+"Accuracy.xls");
			HSSFWorkbook workbookAccuacy=new HSSFWorkbook();
			HSSFSheet RRWorksheet = workbookAccuacy.createSheet("RRval");
			HSSFSheet BFRWorksheet = workbookAccuacy.createSheet("BFR");
			HSSFSheet FCRWorksheet = workbookAccuacy.createSheet("FCR");
			HSSFSheet ICRWorksheet = workbookAccuacy.createSheet("ICR");
			HSSFSheet PCRWorksheet = workbookAccuacy.createSheet("PCR");
			HSSFSheet DFRWorksheet = workbookAccuacy.createSheet("DFR");
			HSSFSheet CCRWorksheet = workbookAccuacy.createSheet("CCR");
			HSSFSheet AggregatedRRRWorksheet = workbookAccuacy.createSheet("AggregatedRR");
			HSSFSheet RegressionRRWorksheet = workbookAccuacy.createSheet("RegressionRR");
			
			
			heading (RRWorksheet); 			
			body (RRWorksheet,precisionData_RR );
			
			heading (BFRWorksheet); 			
			body (BFRWorksheet,precisionData_BFR);
			heading (FCRWorksheet); 			
			body (FCRWorksheet,precisionData_FCR);
			heading (ICRWorksheet); 			
			body (ICRWorksheet,precisionData_ICR);
			heading (PCRWorksheet); 			
			body (PCRWorksheet,precisionData_PCR);
			heading (DFRWorksheet); 			
			body (DFRWorksheet,precisionData_DFR);
			heading (CCRWorksheet); 			
			body (CCRWorksheet,precisionData_CCR);
			heading (AggregatedRRRWorksheet); 			
			body (AggregatedRRRWorksheet,precisionData_AggregatedRR);
			heading (RegressionRRWorksheet); 			
			body (RegressionRRWorksheet,precisionData_RegressionRR);
			
			
			
			
			
			
			
			
//			ArrayList <precisionData> precisionData_RR = new ArrayList<precisionData>(); 
//	ArrayList <precisionData> precisionData_BFR = new ArrayList<precisionData>(); 
//	ArrayList <precisionData> precisionData_FCR = new ArrayList<precisionData>(); 
//	ArrayList <precisionData> precisionData_ICR = new ArrayList<precisionData>(); 
//	ArrayList <precisionData> precisionData_PCR = new ArrayList<precisionData>(); 
//	ArrayList <precisionData> precisionData_DFR = new ArrayList<precisionData>(); 
//	ArrayList <precisionData> precisionData_CCR = new ArrayList<precisionData>(); 
//			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
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
	
	
	void heading ( HSSFSheet curwkWorksheet){
		
		HSSFRow row= curwkWorksheet.createRow(0);
		HSSFCell cell;
		cell= row.createCell(0); cell.setCellValue("Label Type");			
		cell= row.createCell(1); cell.setCellValue("Actual");
		
		cell= row.createCell(2); cell.setCellValue("avg_curpredict");
		cell= row.createCell(3); cell.setCellValue("avg_curmatch");
		
		cell= row.createCell(4); cell.setCellValue("best_curpredict");
		cell= row.createCell(5); cell.setCellValue("best_curmatch");
		
		cell= row.createCell(6); cell.setCellValue("avg_ftrpredict");
		cell= row.createCell(7); cell.setCellValue("avg_ftrmatch");
		
		cell= row.createCell(8); cell.setCellValue("best_ftrpredict");
		cell= row.createCell(9); cell.setCellValue("best_ftrmatch");
		
		
		cell= row.createCell(12); cell.setCellValue("avg_cur_precision");		
		cell= row.createCell(13); cell.setCellValue("avg_cur_recall");
		cell= row.createCell(14); cell.setCellValue("avg_cur_fmeasure");
		
		cell= row.createCell(17); cell.setCellValue("best_cur_precision");		
		cell= row.createCell(18); cell.setCellValue("best_cur_recall");
		cell= row.createCell(19); cell.setCellValue("best_cur_fmeasure");
		
		cell= row.createCell(22); cell.setCellValue("avg_ftr_precision");		
		cell= row.createCell(23); cell.setCellValue("avg_ftr_recall");
		cell= row.createCell(24); cell.setCellValue("avg_ftr_fmeasure");
		
		cell= row.createCell(27); cell.setCellValue("best_ftr_precision");		
		cell= row.createCell(28); cell.setCellValue("best_ftr_recall");
		cell= row.createCell(29); cell.setCellValue("best_ftr_fmeasure");
		
		
		
		
		
	}

//	

	
	void body ( HSSFSheet curwkWorksheet, ArrayList <precisionData> precisionData_temp) {
		
		HSSFRow row;
		double precision, recall, fmeasure; 
		for (int i=0;i<precisionData_RR.size();i++){
			row= curwkWorksheet.createRow(i+1); 
			HSSFCell cell; 
			cell= row.createCell(0); cell.setCellValue(precisionData_temp.get(i).labelType);			
			cell= row.createCell(1); cell.setCellValue(precisionData_temp.get(i).actual);
			
			cell= row.createCell(2); cell.setCellValue(precisionData_temp.get(i).avg_curpredict);
			cell= row.createCell(3); cell.setCellValue(precisionData_temp.get(i).avg_curmatch);
			
			cell= row.createCell(4); cell.setCellValue(precisionData_temp.get(i).best_curpredict);
			cell= row.createCell(5); cell.setCellValue(precisionData_temp.get(i).best_curmatch);
			
			cell= row.createCell(6); cell.setCellValue(precisionData_temp.get(i).avg_ftrpredict);
			cell= row.createCell(7); cell.setCellValue(precisionData_temp.get(i).avg_ftrmatch);
			
			cell= row.createCell(8); cell.setCellValue(precisionData_temp.get(i).best_ftrpredict);
			cell= row.createCell(9); cell.setCellValue(precisionData_temp.get(i).best_ftrmatch);
			
			
			try {
				
				if (precisionData_temp.get(i).avg_curpredict==0) {
					cell= row.createCell(12); cell.setCellValue("NA");
					precision=-1;
				}
				else {
					precision = (double)(precisionData_temp.get(i).avg_curmatch) / (double)(precisionData_temp.get(i).avg_curpredict); 
					cell= row.createCell(12); cell.setCellValue(precision);
				}
				if (precisionData_temp.get(i).actual==0){
					cell= row.createCell(13); cell.setCellValue("NA");
					recall=-1; 
				}
				else {
					recall = (double)(precisionData_temp.get(i).avg_curmatch) / (double)(precisionData_temp.get(i).actual); 
					cell= row.createCell(13); cell.setCellValue(recall);
				}
				
				if (precision == -1 || recall == -1){
					cell= row.createCell(14); cell.setCellValue("NA");
				}
				else {
					fmeasure= (2*precision*recall)/(precision+recall);  
					cell= row.createCell(14); cell.setCellValue(fmeasure);
				}
				
				
				///////////////////////////////
				if (precisionData_temp.get(i).best_curpredict==0) {
					cell= row.createCell(17); cell.setCellValue("NA");
					precision=-1;
				}
				else {
					precision =(double)(precisionData_temp.get(i).best_curmatch) /(double)(precisionData_temp.get(i).best_curpredict); 
					cell= row.createCell(17); cell.setCellValue(precision);
				}
				if (precisionData_temp.get(i).actual==0){
					cell= row.createCell(18); cell.setCellValue("NA");
					recall=-1; 
				}
				else {
					recall = (double)(precisionData_temp.get(i).best_curmatch) / (double)(precisionData_temp.get(i).actual);
					cell= row.createCell(18); cell.setCellValue(recall);
				}
				
				if (precision == -1 || recall == -1){
					cell= row.createCell(19); cell.setCellValue("NA");
				}
				else {
					fmeasure= (2*precision*recall)/(precision+recall);  
					cell= row.createCell(19); cell.setCellValue(fmeasure);
				}
				
				//////////////////////////
				
				
						
				if (precisionData_temp.get(i).avg_ftrpredict==0) {
					cell= row.createCell(22); cell.setCellValue("NA");
					precision=-1;
				}
				else {
					precision = (double)(precisionData_temp.get(i).avg_ftrmatch) / (double)(precisionData_temp.get(i).avg_ftrpredict);
					cell= row.createCell(22); cell.setCellValue(precision);
				}
				if (precisionData_temp.get(i).actual==0){
					cell= row.createCell(23); cell.setCellValue("NA");
					recall=-1;
				}
				else {
					recall = (double)(precisionData_temp.get(i).avg_ftrmatch) / (double)(precisionData_temp.get(i).actual); 
					cell= row.createCell(23); cell.setCellValue(recall);
				}
				
				if (precision == -1 || recall == -1){
					cell= row.createCell(24); cell.setCellValue("NA");
				}
				else {
					fmeasure= (2*precision*recall)/(precision+recall);  
					cell= row.createCell(24); cell.setCellValue(fmeasure);
				}
				
				////////////////////////////////////
				
				
			
				if (precisionData_temp.get(i).best_ftrpredict==0) {
					cell= row.createCell(27); cell.setCellValue("NA");
					precision=-1; 
				}
				else {
					precision = (double)(precisionData_temp.get(i).best_ftrmatch)/ (double)(precisionData_temp.get(i).best_ftrpredict); 
					cell= row.createCell(27); cell.setCellValue(precision);
				}
				if ( precisionData_temp.get(i).actual==0){
					cell= row.createCell(28); cell.setCellValue("NA");
					recall=-1;
				}
				else {
					recall = (double)(precisionData_temp.get(i).best_ftrmatch) / (double)(precisionData_temp.get(i).actual); 
					cell= row.createCell(28); cell.setCellValue(recall);
				}
				
				if (precision == -1 || recall == -1){
					cell= row.createCell(29); cell.setCellValue("NA");
				}
				else {
					fmeasure= (2*precision*recall)/(precision+recall); 
					cell= row.createCell(29); cell.setCellValue(fmeasure);
				}
								
				
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}





}//end of main class	
	
	
	
	
	

