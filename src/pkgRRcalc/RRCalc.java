package pkgRRcalc;

import java.awt.PageAttributes.OriginType;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import org.apache.poi.hssf.record.FilePassRecord;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;

import cc.mallet.util.FileUtils;
import dataCollection.CommitDataCollection;
import dataCollection.IssueDataCollection;
import dataCollection.PullDataCollection;
import dataInput.FetchCntbtrMessages;
import dataInput.commentDBListStructure;
import dataInput.createTopicFiles;
import dataInput.finalResultsWR;
import dataInput.malletTopicAnalysis;
import dataInput.postTopicAnalysis;
import dataInput.topicAnalysis;
import dataInput.topicAnalysisListStr;







import dataInput.topicPreProcessing;

import java.nio.file.Files;



public class RRCalc {

	
	//---------------------To do: data update for each project 
	
	
//	public static String filePath = "C:/Users/S.M.Didar/Dropbox/Didar DBPC/PhD Research/Winter 2015/ICSE 2016/Experiment/Workspace/";
	
	
	
//	for comment analysis 
//	public static String filePath = "C:/Users/S.M.Didar/Dropbox/Didar DBPC/PhD Research/Winter 2016/Journal 2016/Analysis/V8 Getting New Metrics Data/V8.2 Data Collection Prepare weekly rate data/Slider/";
//	public static String filePath = "C:/Users/S.M.Didar/OneDrive/Didar DBPC/PhD Research/Fall 2016/Work Packages/V3- Dataset preparation/";
	public static String filePath = "C:/Users/Didar/Desktop/BS GitHub Platform Export 09.05.17/";

	
	//	Project mbostock data
	
//	public static String repoOwner = "mbostock";
//	public static String repoName = "d3";
//	public static int lastIssueNumber =1947; 
//	public static String strStartDate= "2012-04-15"; 
//	public static String strEndDate = "2014-05-23";
	

//	Project cgeo data
	
//	public static String repoOwner = "brightsquid";
//	public static String repoName = "securehealthexchange.ca";

	public static String repoOwner = "publify";
	public static String repoName = "publify";
	
	
	//	public static int lastIssueNumber =100; //1947; 
	public static String strStartDate= "2012-04-15"; 
	public static String strEndDate = "2014-07-17";
	
	
	
//	public static int numTrainingWeeks=2;//=80;   /// change also in precision recall msr file outlebelling function 
//	public static int commentAnalysisUptoWeek=2; //= 110; 
	
//	public static int numTopics=20; 
//	public static double taAlpha = 0.1; 
//	public static int taIteration = 500; 
	
	
	
	public static void releasInfo(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		
		try {
			
			
			
			// Release not spring info Aurora -----------
			
//			releaseInfo.add(sdf.parse("2013-10-22"));		
//			releaseInfo.add(sdf.parse("2013-12-17"));		
//			releaseInfo.add(sdf.parse("2014-01-02"));		
//			releaseInfo.add(sdf.parse("2014-08-01"));		
//			releaseInfo.add(sdf.parse("2014-11-25"));
//			releaseInfo.add(sdf.parse("2015-02-09"));
//			releaseInfo.add(sdf.parse("2015-05-13"));
//			releaseInfo.add(sdf.parse("2015-07-24"));
//			releaseInfo.add(sdf.parse("2015-11-15"));
//			releaseInfo.add(sdf.parse("2015-12-22"));
//			releaseInfo.add(sdf.parse("2016-02-07"));
//			releaseInfo.add(sdf.parse("2016-04-14"));
//			releaseInfo.add(sdf.parse("2016-06-14"));
			
			
			
			
			
			
			
			
			//			Release info Slider 
			
			
			
//			releaseInfo.add(sdf.parse("2014-05-05"));		
//			releaseInfo.add(sdf.parse("2014-05-21"));
//			releaseInfo.add(sdf.parse("2014-06-03"));
//			releaseInfo.add(sdf.parse("2014-06-18"));
//			releaseInfo.add(sdf.parse("2014-07-01"));
//			releaseInfo.add(sdf.parse("2014-08-04"));
//			releaseInfo.add(sdf.parse("2014-08-21"));
//			releaseInfo.add(sdf.parse("2014-09-04"));
//			releaseInfo.add(sdf.parse("2014-10-02"));
//		releaseInfo.add(sdf.parse("2014-10-16"));
//	releaseInfo.add(sdf.parse("2014-11-04"));
//	releaseInfo.add(sdf.parse("2014-11-17"));
//	releaseInfo.add(sdf.parse("2014-12-11"));
//	releaseInfo.add(sdf.parse("2015-01-06"));
//		releaseInfo.add(sdf.parse("2015-01-27"));
//		releaseInfo.add(sdf.parse("2015-02-19"));
//	releaseInfo.add(sdf.parse("2015-03-08"));
//	releaseInfo.add(sdf.parse("2015-09-02"));
//	releaseInfo.add(sdf.parse("2015-09-11"));
//	releaseInfo.add(sdf.parse("2016-08-01"));

			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
//			Project Usergrid release info

			
//			releaseInfo.add(sdf.parse("2015-01-05"));	
//			releaseInfo.add(sdf.parse("2015-01-12"));
//			releaseInfo.add(sdf.parse("2015-01-20"));
//			releaseInfo.add(sdf.parse("2015-01-26"));
//			releaseInfo.add(sdf.parse("2015-02-02"));
//			releaseInfo.add(sdf.parse("2015-02-09"));
//			releaseInfo.add(sdf.parse("2015-02-16"));
//			releaseInfo.add(sdf.parse("2015-02-18"));
//			releaseInfo.add(sdf.parse("2015-03-04"));
//		releaseInfo.add(sdf.parse("2015-03-11"));
//	releaseInfo.add(sdf.parse("2015-03-16"));
//	releaseInfo.add(sdf.parse("2015-03-24"));
//	releaseInfo.add(sdf.parse("2015-04-01"));
//	releaseInfo.add(sdf.parse("2015-04-08"));
//		releaseInfo.add(sdf.parse("2015-04-21"));
//		releaseInfo.add(sdf.parse("2015-04-29"));
//	releaseInfo.add(sdf.parse("2015-05-06"));
//	releaseInfo.add(sdf.parse("2015-05-13"));
//	releaseInfo.add(sdf.parse("2015-05-20"));
//	releaseInfo.add(sdf.parse("2015-05-27"));
//			releaseInfo.add(sdf.parse("2015-06-03"));
//			releaseInfo.add(sdf.parse("2015-06-10"));
//			releaseInfo.add(sdf.parse("2015-06-17"));
//			releaseInfo.add(sdf.parse("2015-07-01"));
//		releaseInfo.add(sdf.parse("2015-07-08"));
//	releaseInfo.add(sdf.parse("2015-07-22"));
//	releaseInfo.add(sdf.parse("2015-07-29"));
//	releaseInfo.add(sdf.parse("2015-08-25"));
//	releaseInfo.add(sdf.parse("2015-09-02"));
//		releaseInfo.add(sdf.parse("2015-09-18"));
//		releaseInfo.add(sdf.parse("2015-10-27"));
//	releaseInfo.add(sdf.parse("2015-11-09"));
//	releaseInfo.add(sdf.parse("2016-01-05"));
//	releaseInfo.add(sdf.parse("2016-01-21"));


			
			
			
			
			
			
//			Release infor Project Aurora 
			
//			releaseInfo.add(sdf.parse("2014-05-12"));	
//			releaseInfo.add(sdf.parse("2014-05-19")); 
//			releaseInfo.add(sdf.parse("2014-06-02")); 
//			releaseInfo.add(sdf.parse("2014-07-14")); 
//			releaseInfo.add(sdf.parse("2014-07-25")); 
//			releaseInfo.add(sdf.parse("2014-09-07"));
//			releaseInfo.add(sdf.parse("2014-09-23")); 
//			releaseInfo.add(sdf.parse("2014-10-06")); 
//			releaseInfo.add(sdf.parse("2014-10-20")); 
//		releaseInfo.add(sdf.parse("2014-11-04")); 
//	releaseInfo.add(sdf.parse("2014-11-17")); 
//	releaseInfo.add(sdf.parse("2014-12-01")); 
//	releaseInfo.add(sdf.parse("2014-12-15")); 
//	releaseInfo.add(sdf.parse("2015-01-17")); 
//		releaseInfo.add(sdf.parse("2015-01-31")); 
//		releaseInfo.add(sdf.parse("2015-02-17")); 
//	releaseInfo.add(sdf.parse("2015-03-02")); 
//	releaseInfo.add(sdf.parse("2015-03-16")); 
//	releaseInfo.add(sdf.parse("2015-03-31")); 
//	releaseInfo.add(sdf.parse("2015-04-13")); 
//	releaseInfo.add(sdf.parse("2015-04-27")); 
//		releaseInfo.add(sdf.parse("2015-05-11")); 
//		releaseInfo.add(sdf.parse("2015-05-26")); 
//	releaseInfo.add(sdf.parse("2015-06-05")); 
//	releaseInfo.add(sdf.parse("2015-06-22"));
//	releaseInfo.add(sdf.parse("2015-07-06")); 
//	releaseInfo.add(sdf.parse("2015-07-20")); 
//	releaseInfo.add(sdf.parse("2015-08-03")); 
//	releaseInfo.add(sdf.parse("2015-08-17")); 
//	releaseInfo.add(sdf.parse("2015-08-31")); 
//	releaseInfo.add(sdf.parse("2015-09-14")); 
//	releaseInfo.add(sdf.parse("2015-09-28"));
//	releaseInfo.add(sdf.parse("2015-10-09")); 
//	releaseInfo.add(sdf.parse("2015-10-26")); 
//	releaseInfo.add(sdf.parse("2015-11-06")); 
//		releaseInfo.add(sdf.parse("2015-11-21"));

			
			
			
			
			
			
//			Project mbostock data
			
//			releaseInfo.add(sdf.parse("2012-04-15")); 
//			releaseInfo.add(sdf.parse("2012-12-21")); 			
//			releaseInfo.add(sdf.parse("2013-04-21")); 			
//			releaseInfo.add(sdf.parse("2013-06-13")); 			
//			releaseInfo.add(sdf.parse("2013-08-21"));
//			releaseInfo.add(sdf.parse("2014-01-10"));
//			releaseInfo.add(sdf.parse("2014-05-23"));

			
			
//			Project cgeo data
			
//			releaseInfo.add(sdf.parse("2012-04-15"));
//			releaseInfo.add(sdf.parse("2012-12-21"));
//			releaseInfo.add(sdf.parse("2013-06-13"));
//			releaseInfo.add(sdf.parse("2013-08-21"));
//			releaseInfo.add(sdf.parse("2014-01-10"));
//			releaseInfo.add(sdf.parse("2014-05-23"));
//			releaseInfo.add(sdf.parse("2014-06-14"));
//			releaseInfo.add(sdf.parse("2014-07-12"));
//			releaseInfo.add(sdf.parse("2014-08-02"));					
//		
//			
//			releaseInfo.add(sdf.parse("2014-02-21"));
//			releaseInfo.add(sdf.parse("2014-05-21"));
//			releaseInfo.add(sdf.parse("2014-06-30"));
//			releaseInfo.add(sdf.parse("2014-12-22"));
//			releaseInfo.add(sdf.parse("2014-01-09"));
//			releaseInfo.add(sdf.parse("2014-01-09"));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	
	//---------------------To do: data update for each project 
	
	
	
	public static ArrayList<IssueListStr> IssueData = new ArrayList<IssueListStr>();  
	public static ArrayList<IssueListStr> bugIssueData = new ArrayList<IssueListStr>(); 
	public static ArrayList<IssueListStr> ftrIssueData = new ArrayList<IssueListStr>(); 
	public static ArrayList<IssueListStr> impIssueData = new ArrayList<IssueListStr>(); 
	public static ArrayList<IssueListStr> PullData = new ArrayList<IssueListStr>();  
	  
	
	public static ArrayList<WeekCalcStr> BFRData = new ArrayList<WeekCalcStr>();
	public static ArrayList<WeekCalcStr> FCRData = new ArrayList<WeekCalcStr>();
	public static ArrayList<WeekCalcStr> ICRData = new ArrayList<WeekCalcStr>();
	public static ArrayList<WeekCalcStr> PCRData = new ArrayList<WeekCalcStr>();
	public static ArrayList<WeekCalcStr> CCRData = new ArrayList<WeekCalcStr>();
	public static ArrayList<WeekCalcStr> DFRData = new ArrayList<WeekCalcStr>();
	
	public static ArrayList <commitListStr> commitData = new ArrayList<commitListStr>(); 
	
	public static ArrayList<Date> releaseInfo = new ArrayList<Date> (); 
	public static ArrayList<RRListStr> RRValues = new ArrayList<RRListStr> (); 
	
	
	 
	
	public static ArrayList <String> bugLabels = new ArrayList<String>();
	public static ArrayList <String> ftrLabels = new ArrayList<String>();
	
	public static Date startDate = new Date(); 
	public static Date endDate = new Date(); 
	
	public static String [][] GitCredits = {
											{"adnan522", "ammu6905ma"},
											{"didar5222", "ammu69GITHUB05ma"},
											{"naziabenozir", "ammu69GITHUB05ma"},
											{"shawniut", "shawonma151"}, 
											}; 
	
	
	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String fileName = "commitdata.xls";
		FetchCntbtrMessages fetchCntbtrMessages = new FetchCntbtrMessages ();
		createTopicFiles objCreateTopicFiles = new createTopicFiles();
		
		
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			startDate = sdf.parse(strStartDate);
			endDate = sdf.parse(strEndDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
//		-------------------Level 0: Download all datasets 
		
		try {
////			new IssueDataCollection().issueCollection(filePath, fileName,100);
			new CommitDataCollection().commitCollection(filePath, fileName);
////			new PullDataCollection().pullCollection(filePath, fileName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
//		------------------Level 1: Identify all labels
		
//		readExcelFile (filePath+"Issuedata.xls", "IssueData"); 
//		identifyUniqueLabels(); 
		
		
//		------------------Level 2: Manually distributes labels between Buf, Feature and improvement 
		
//		------------------Level 3: Distribute all issues in three categories
	
//		readExcelFile (filePath+"Issuedata.xls", "IssueData"); 
//		readUniqueLabels (); 
//		diffBugvsFtr (filePath,"IssueData.xls");
		
		
		//------------------Level 4: Fetch all contributor message data 
		
		
//		try {
//			fetchCntbtrMessages.fetchMsgData (GitCredits[1][0], GitCredits[1][1], repoOwner, repoName, filePath, lastIssueNumber);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
		
		
		
		
//		------------------Level 5: Perform rest of the calculation until RR values 
		
//		readExcelFile (filePath+"Issuedata.xls", "IssueData"); 
//		readExcelFile (filePath+"Issuedata.xls", "Issue_Bug"); 
//		readExcelFile (filePath+"Issuedata.xls", "Issue_Ftr"); 
//		readExcelFile (filePath+"Issuedata.xls", "Issue_Imp"); 
//		readExcelFile (filePath+"Pulldata.xls", "PullData");
//		readExcelFile (filePath+"Commitdata.xls", "CommitData");
//		releasInfo();
		
		
//		weekDateCalc (BFRData);
//		weeklyRateCalc ("BFR",BFRData,bugIssueData, true);
//		weekDateCalc (FCRData);
//		weeklyRateCalc ("FCR",FCRData,ftrIssueData, false);
//		weekDateCalc (ICRData);
//		weeklyRateCalc ("ICR",ICRData,impIssueData, false);
//		weekDateCalc (PCRData);
//		weeklyRateCalc ("PCR",PCRData,PullData, false);
//		weekDateCalc (CCRData);
//		weeklyCCRCalc ("CCR",CCRData, commitData); 
//		weekDateCalc (DFRData);
//		weeklyRateCalc ("DFR", DFRData, bugIssueData, false); 
		
//		calcRRVal ("RRVal"); 
//
//		
//		
//		
//		
//		
//				
//		File f = new File (filePath+"/Topic preprocess files"); 
//		
//		if (!f.exists() || !f.isDirectory()){
//				new File(filePath+"/Topic preprocess files").mkdir();
//		}
//		objCreateTopicFiles.createTopics (filePath, repoOwner, repoName, strStartDate, strEndDate, BFRData); 
	
	
		
//		------------------Level 6:  PreProcessing all documents and copying from Pre-Process to Backup files.  	
		
		
//		File BackUpTopics = new File (filePath+"/Topic backup files"); 
//		if (!BackUpTopics.exists() || !BackUpTopics.isDirectory()){
//			new File(filePath+"/Topic backup files").mkdir();
//		}
//		
//		topicPreProcessing objTopicPreProcessing = new topicPreProcessing (); 
//		objTopicPreProcessing.preProcessing(1, commentAnalysisUptoWeek, filePath);
		
	
//		------------------Level 7:  Copy all training files, copy analysis files and perform topic analysis 		
		
		
//		File topics = new File (filePath+"/Topic files"); 
//		File results = new File (filePath+"/Results"); 
//		
//		if (!topics.exists() || !topics.isDirectory()){
//				new File(filePath+"/Topic files").mkdir();
//		}
//		if (!results.exists() || !results.isDirectory()){
//			new File(filePath+"/Results").mkdir();
//	}
//
//		
//		topicAnalysis TA = new topicAnalysis(); 
//		
//		
//		TA.copyTrainingFiles(numTrainingWeeks, filePath); 
//		TA.topicAnalysis (numTrainingWeeks, commentAnalysisUptoWeek, filePath,  numTopics, taAlpha, taIteration); 
		
		

		
		
//		------------------Level 8:  Get the best results  
		
		
//		postTopicAnalysis pTA = new postTopicAnalysis (); 
//		pTA.readTopicAnalysis (filePath+ "/Results/", filePath, numTrainingWeeks, commentAnalysisUptoWeek, numTopics); 
////		
		
//		------------------Level 9:  Get the final results
		
//		finalResultsWR frWR = new finalResultsWR (); //		
//		frWR.finalResultWRMain(filePath, "Final_Results_NV.xls");
		
	
	
	}// end of main method
	
	
	
	
	
	
	public static void readExcelFile (String fileName, String sheetName){
		try {
			
			System.out.println("Reading Excel file sheet"+ sheetName);
	        
			FileInputStream file = new FileInputStream(new File(fileName));		
			//Create Workbook instance holding reference to .xlsx file
	        HSSFWorkbook workbook = new HSSFWorkbook(file);
	        //Get first/desired sheet from the workbook
	        HSSFSheet sheet = workbook.getSheet(sheetName);
	        
	        
	        if (sheetName.equals("IssueData")){
        		fillIssueData (sheet, IssueData); 
        	}
        	
        	if (sheetName.equals("PullData")){
        		fillPullData (sheet, PullData); 
        	}
        	if (sheetName.equals("CommitData")){
        		fillCommitData(sheet, commitData); 
        	}
        	if (sheetName.equals("Issue_Bug")){
        		fillIssueData (sheet, bugIssueData); 
        	}
        	if (sheetName.equals("Issue_Ftr")){
        		fillIssueData (sheet, ftrIssueData); 
        	}
        	if (sheetName.equals("Issue_Imp")){
        		fillIssueData (sheet, impIssueData); 
        	}
	        
	        file.close();
		}
		catch (Exception e){
			e.printStackTrace (); 
			System.out.println("Problem in reading");
		}
	} 
	
	public static void fillPullData (HSSFSheet sheet, ArrayList <IssueListStr> TempIssues){
		Iterator<Row> rowIterator = sheet.iterator();
        
        while (rowIterator.hasNext()) {
        	Row row = rowIterator.next();
        	
	        IssueListStr tempIssueData = new IssueListStr(); 
			
			if (row.getCell(0)!=null) tempIssueData.setIssueNumber(row.getCell(0).getNumericCellValue());		
			if (row.getCell(1)!=null) tempIssueData.setIssueCreatedAt(DateUtil.getJavaDate(row.getCell(1).getNumericCellValue()));
			
			
			
			tempIssueData.setClosed(true);
//			changed on 15 March 2016 
			if (row.getCell(2)!=null) tempIssueData.setIssueClosedAt(DateUtil.getJavaDate(row.getCell(2).getNumericCellValue()));
//			if (row.getCell(3)!=null) tempIssueData.setIssueClosedAt(DateUtil.getJavaDate(row.getCell(3).getNumericCellValue()));
		
				
//			if (row.getCell(4)!=null) tempIssueData.setNumofComments(row.getCell(4).getNumericCellValue());
//			if (row.getCell(5)!=null) tempIssueData.setIssueLabels(row.getCell(5).getStringCellValue());
//			if (row.getCell(6)!=null) tempIssueData.setIssueUpdatedAt(DateUtil.getJavaDate(row.getCell(6).getNumericCellValue()));
			
			TempIssues.add(tempIssueData); 
        }
	}
	
	
	
	public static void fillIssueData (HSSFSheet sheet, ArrayList <IssueListStr> TempIssues){
		Iterator<Row> rowIterator = sheet.iterator();
        
        while (rowIterator.hasNext()) {
        	Row row = rowIterator.next();
        	
	        IssueListStr tempIssueData = new IssueListStr(); 
			
			if (row.getCell(0)!=null) tempIssueData.setIssueNumber(row.getCell(0).getNumericCellValue());		
			if (row.getCell(1)!=null) tempIssueData.setIssueCreatedAt(DateUtil.getJavaDate(row.getCell(1).getNumericCellValue()));
			
			
			if (row.getCell(2)!=null){
				if (row.getCell(2).getStringCellValue().equals("closed")) 			tempIssueData.setClosed(true);
			}
			if (row.getCell(3)!=null) tempIssueData.setIssueClosedAt(DateUtil.getJavaDate(row.getCell(3).getNumericCellValue()));
			if (row.getCell(4)!=null) tempIssueData.setNumofComments(row.getCell(4).getNumericCellValue());
			if (row.getCell(5)!=null) tempIssueData.setIssueLabels(row.getCell(5).getStringCellValue());
			if (row.getCell(6)!=null) tempIssueData.setIssueUpdatedAt(DateUtil.getJavaDate(row.getCell(6).getNumericCellValue()));
			
			TempIssues.add(tempIssueData); 
        }
	}
	
	
	
	
	
	
	
	
	public static void fillCommitData (HSSFSheet sheet, ArrayList <commitListStr> TempCommits){
		
		Iterator<Row> rowIterator = sheet.iterator();
        
        while (rowIterator.hasNext()) {
        	Row row = rowIterator.next();
        	
	        commitListStr tempCommitData = new commitListStr(); 
			
			if (row.getCell(0)!=null) tempCommitData.shaValue=row.getCell(0).getStringCellValue(); 
			if (row.getCell(1)!=null) tempCommitData.author= row.getCell(1).getStringCellValue(); 
			if (row.getCell(2)!=null) tempCommitData.commitDate = DateUtil.getJavaDate(row.getCell(2).getNumericCellValue());
			if (row.getCell(3)!=null) tempCommitData.numofFiles= row.getCell(3).getNumericCellValue();
			if (row.getCell(4)!=null) tempCommitData.codeChurn= row.getCell(4).getNumericCellValue();
			if (row.getCell(5)!=null) tempCommitData.numfComments= row.getCell(5).getNumericCellValue();
			if (row.getCell(6)!=null) tempCommitData.description= row.getCell(6).getStringCellValue();
			
			TempCommits.add(tempCommitData); 
		}
	}
	
	
	
	
	
	public static void identifyUniqueLabels (){
		ArrayList<String> uniqueLabelsStr = new ArrayList<String> (); 
		
		for (int i=0;i<IssueData.size();i++){
			boolean flagUniqueLabel = true; 
			String tempLabel = IssueData.get(i).getIssueLabels(); 
			for (int j=0;j<uniqueLabelsStr.size();j++){
				if (uniqueLabelsStr.get(j).equalsIgnoreCase(tempLabel)){
					
					flagUniqueLabel = false; 
				}
			}
			
			if (flagUniqueLabel==true){
			uniqueLabelsStr.add(tempLabel); 	
			}
		}
		
		
		try {
			FileOutputStream fileOut = new FileOutputStream(filePath+"uniqueLabel.xls");
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet worksheetBug = workbook.createSheet("Bug");
			HSSFSheet worksheetFeature = workbook.createSheet("Feature");			
			int bugShtCounter=0, ftrShtCounter=0; 
			
			for (int i=0;i<uniqueLabelsStr.size();i++){
				HSSFRow row; 
				
				if (uniqueLabelsStr.get(i).contains("Bug") || uniqueLabelsStr.get(i).contains("bug")|| uniqueLabelsStr.get(i).contains("BUG")){
					row = worksheetBug.createRow(bugShtCounter);
					bugShtCounter++;
				}
				else {
					row = worksheetFeature.createRow(ftrShtCounter);
					ftrShtCounter++;
				}
				
				HSSFCell cell = row.createCell(0);
				cell.setCellValue(uniqueLabelsStr.get(i));
			}
			
			workbook.write(fileOut);
			fileOut.flush();
			fileOut.close();
			System.out.println("Success: Unique labels written");
			
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("Error: Unique labels writing");
		}catch (IOException e2){
			e2.printStackTrace();
			System.out.println("Error: Unique labels writing");
		}
	}

	public static void readUniqueLabels (){
		
		
		
		try {
			System.out.println("Reading readUniqueLabel Excel file");
	        
			FileInputStream labelFile = new FileInputStream(new File(filePath+"uniqueLabel.xls"));		
			//Create Workbook instance holding reference to .xlsx file
	        HSSFWorkbook labelWorkbook = new HSSFWorkbook(labelFile);
	        //Get first/desired sheet from the workbook
	        HSSFSheet bugSheet = labelWorkbook.getSheetAt(0);
	        HSSFSheet ftrSheet = labelWorkbook.getSheetAt(1);
	        
	        //Iterate through each rows one by one
	        Iterator<Row> rowIteratorBug = bugSheet.iterator();
	        
	        while (rowIteratorBug.hasNext()) {
	        	Row row = rowIteratorBug.next();
	        	bugLabels.add(row.getCell(0).getStringCellValue());
	        }	
	        
	        
	        Iterator<Row> rowIteratorFtr = ftrSheet.iterator();
	        while (rowIteratorBug.hasNext()) {
	        	Row row = rowIteratorFtr.next();
	        	ftrLabels.add(row.getCell(0).getStringCellValue());
	        }
	        labelWorkbook.close();
	        labelFile.close();
	        System.out.println("Success: Completed reading unique labels");
		}
		catch (Exception e){
			e.printStackTrace (); 
			System.out.println("Problem in reading");
		}
	}
	
	public static void diffBugvsFtr (String filePath,String fileName){
		try {
			FileInputStream inputInitialData = new FileInputStream(new File(filePath+ fileName));
			//Create Workbook instance holding reference to .xlsx file
	        HSSFWorkbook workbookInitial = new HSSFWorkbook(inputInitialData);
	        
	        HSSFSheet worksheetBug = null; 
	        HSSFSheet worksheetImp = null; 
	        HSSFSheet worksheetFeature = null; 
	        Row row= null; 
	        int ctrBugSheet=0,ctrFtrSheet =0, ctrImpSheet=0; 
	        
	        if (workbookInitial.getSheet("Issue_Bug")==null && workbookInitial.getSheet("Issue_Ftr")==null && workbookInitial.getSheet("Issue_Imp")==null) {
	        	
	        	worksheetBug = workbookInitial.createSheet("Issue_Bug");
	        	worksheetFeature = workbookInitial.createSheet("Issue_Ftr");
	        	worksheetImp = workbookInitial.createSheet("Issue_Imp");
	        	
	        	
	        	
	        	for (int i=0;i<IssueData.size();i++){
	        		String tempLabel = IssueData.get(i).getIssueLabels(); 
	        		boolean flagIsBug = false; 
	        		boolean flagIsImp = false; 
	        		row = null; 
	        		
	        		if (tempLabel.equals("[]")){
	        			flagIsImp = true; 
	        		}
	        		
	        		if (flagIsImp ==false){
	        			for (int j=0;j<bugLabels.size();j++){
		        			if (bugLabels.get(j).equals(tempLabel)){
		        				flagIsBug = true; 
		        			}
		        		}
	        		}
	        		
	        		if (flagIsImp==true){
	        			row = worksheetImp.createRow(ctrImpSheet); 
	        			ctrImpSheet++;
	        		}
	        		else if (flagIsBug==true){
	        			row = worksheetBug.createRow(ctrBugSheet); 
	        			ctrBugSheet++;
        			}
	        		else{
	        			row = worksheetFeature.createRow(ctrFtrSheet);    
	        			ctrFtrSheet++;
	        		}
	        		
	        		
	        		row.createCell(0).setCellValue(IssueData.get(i).getIssueNumber());
    				row.createCell(1).setCellValue(IssueData.get(i).getIssueCreatedAt());
    				
    				
    				if (IssueData.get(i).isClosed()==true){
    					row.createCell(2).setCellValue("Closed");
    				}
    				
    				
    				
    				if (IssueData.get(i).getIssueClosedAt() != null){
    					row.createCell(3).setCellValue(IssueData.get(i).getIssueClosedAt());
    				}
    					
    					
    				
    				
    				row.createCell(4).setCellValue(IssueData.get(i).getNumofComments());
    				row.createCell(5).setCellValue(IssueData.get(i).getIssueLabels());
    				row.createCell(6).setCellValue(IssueData.get(i).getIssueUpdatedAt());
	        	}
	        } else System.out.println("Issue_Bug Issue_Ftr sheet already exist");
			
	        inputInitialData.close();
			
			
			FileOutputStream outputInitialData = new FileOutputStream(filePath+fileName);
			workbookInitial.write(outputInitialData);
			//fileOut.flush();
			
			outputInitialData.close();
			System.out.println("Success: Issue Bug and Issue Ftr written");
			
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("Error: Issue Bug and Issue Ftr writing");
		}catch (IOException e2){
			e2.printStackTrace();
			System.out.println("Error: Issue Bug and Issue Ftr writing");
		}
	}

	public static void testIO (){
		try {
			//Read the spreadsheet that needs to be updated
			FileInputStream input_document = new FileInputStream(new File(filePath+"uniqueLabel.xls"));
			//Access the workbook
			HSSFWorkbook my_xls_workbook = new HSSFWorkbook(input_document); 
			//Access the worksheet, so that we can update / modify it.
			HSSFSheet my_worksheet = my_xls_workbook.getSheetAt(0);
			// declare a Cell object
			Cell cell = null; 
			// Access the cell first to update the value
			cell = my_worksheet.getRow(1).getCell(1);
			// Get current value and then add 5 to it 
			cell.setCellValue(232323);
			
			my_xls_workbook.createSheet("5555555");
			
			
			
			//Close the InputStream
			input_document.close();
			//Open FileOutputStream to write updates
			FileOutputStream output_file =new FileOutputStream(new File(filePath+"uniqueLabel.xls"));
			//write changes
			
			
			my_xls_workbook.write(output_file);
			//close the stream
			output_file.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}         
	}
	
	
	
	public static Date addDate (int numofDays, Date startDate){
		Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);
        cal.add(Calendar.DATE, numofDays); //minus number would decrement the days
        return cal.getTime(); 
    }
	
	public static void weekDateCalc (ArrayList<WeekCalcStr> weekRateData){
		
		Date weekStart= startDate; 
		
		
		int weekNumber = 0,ctrWeekRateData=-1, releaseNum=1; 
		
		
		
		while (addDate((weekNumber+1), weekStart).compareTo(endDate)<=0){
			weekRateData.add(new WeekCalcStr());
			ctrWeekRateData++;
			
			
			weekRateData.get(ctrWeekRateData).setWeekStart(weekStart);			
			//System.out.print(" "+ DateFormat.getDateInstance(DateFormat.SHORT).format(weekRateData.get(ctrWeekRateData).getWeekStart()));
			
			
			weekRateData.get(ctrWeekRateData).setWeekNum(ctrWeekRateData+1);
			//System.out.print("--wn-- "+ weekRateData.get(ctrWeekRateData).getWeekNum());
			
			weekNumber++; 
			//System.out.print("--rW -- " + weekNumber);
			//System.out.println("checking date ------"+ addDate(7*weekNumber, weekStart));
			if (addDate(weekNumber, weekStart).compareTo(releaseInfo.get(releaseNum))<0){
				weekRateData.get(ctrWeekRateData).setWeekEnd(addDate(weekNumber, weekStart));
				
				//System.out.print("----"+ DateFormat.getDateInstance(DateFormat.SHORT).format(weekRateData.get(ctrWeekRateData).getWeekEnd()));
				//System.out.println();
			}
			else {
				weekRateData.get(ctrWeekRateData).setWeekEnd(releaseInfo.get(releaseNum)); 
				//System.out.print("-//--"+ DateFormat.getDateInstance(DateFormat.SHORT).format(weekRateData.get(ctrWeekRateData).getWeekEnd()));
				//System.out.println();
				
				weekNumber=0; 
				weekStart=addDate(1, releaseInfo.get(releaseNum)); 
				releaseNum++;
				
				//System.out.println("changing for release --------- " + releaseNum);
			}
		}
	}
		
	public static void weeklyRateCalc (String fileName, ArrayList<WeekCalcStr> weekRateData, ArrayList<IssueListStr> tempIssueData, boolean fileCreate){
		
		String startDateCompare=null; 
		String closeDateCompare=null; 
		String startWKDateCompare=null; 
		
		Date issueCreated, issueClosed, weekStart, weekEnd, lastWeekEnd=null; 
		
		for (int i=0;i<weekRateData.size();i++){
			for (int j=0;j<tempIssueData.size();j++){
				
				issueCreated = tempIssueData.get(j).getIssueCreatedAt(); 
				issueClosed = tempIssueData.get(j).getIssueClosedAt(); 
				weekStart = weekRateData.get(i).getWeekStart(); 
				weekEnd = weekRateData.get(i).getWeekEnd(); 
				if (weekRateData.get(i).getWeekNum()>1){
					lastWeekEnd= weekRateData.get(i-1).getWeekEnd();
				}
				else if (weekRateData.get(i).getWeekNum()==1) {
					lastWeekEnd = weekStart;
				}
				
				if (issueCreated.before(weekStart)) startDateCompare="before";  // -1 means issue created before week
				else if (issueCreated.after (weekEnd)) startDateCompare="after";
				else if (issueCreated.after(weekStart) && 	issueCreated.before (weekEnd))  startDateCompare="inWeek"; 
				else if (issueCreated.equals(weekStart) || issueCreated.equals(weekEnd)) startDateCompare="inWeek"; 		
						
				
				if (issueClosed == null) closeDateCompare="open"; 
				else if (issueClosed.before(weekStart)) closeDateCompare="before";  // -1 means issue created before week
				else if (issueClosed.after (weekEnd)) closeDateCompare="after";
				else if (issueClosed.after(weekStart) && 	issueClosed.before (weekEnd))  closeDateCompare="inWeek"; 
				else if (issueClosed.equals(weekStart) || issueClosed.equals(weekEnd)) closeDateCompare="inWeek"; 	
				
				
				if(fileName.equals("DFR")){
					if (issueCreated.before(lastWeekEnd)) startWKDateCompare="sngbefore";  // -1 means issue created before week
					else if (issueCreated.after (weekEnd)) startWKDateCompare="sngafter";
					else if (issueCreated.after(lastWeekEnd) && 	issueCreated.before (weekEnd))  startWKDateCompare="snginWeek"; 
					else if (issueCreated.equals(lastWeekEnd) || issueCreated.equals(weekEnd)) startWKDateCompare="snginWeek"; 
					if (startWKDateCompare.equals("snginWeek")) weekRateData.get(i).weeklyVal++;
				}	
				
				if (startDateCompare.equals("inWeek") && closeDateCompare.equals("inWeek"))  weekRateData.get(i).inOinC++;
				if (startDateCompare.equals("before") && closeDateCompare.equals("inWeek")) weekRateData.get(i).erOinC++;
				if (startDateCompare.equals("inWeek") && closeDateCompare.equals("after")) weekRateData.get(i).inOltC++;
				if (startDateCompare.equals("before") && closeDateCompare.equals("after")) weekRateData.get(i).erOltC++;
				if (startDateCompare.equals("inWeek") && closeDateCompare.equals("open")) weekRateData.get(i).inO++; 
				if (startDateCompare.equals("before") && closeDateCompare.equals("open")) weekRateData.get(i).erO++;
				
				
			}
		
			double tempTotalVal=0, tempTotalVal2=0; 
			if (fileName.equals("DFR")) {
				tempTotalVal = weekRateData.get(i).weeklyVal; 
			}
			else {
				tempTotalVal = (weekRateData.get(i).inOinC+weekRateData.get(i).erOinC);
			}
			tempTotalVal2 = (weekRateData.get(i).inOinC+weekRateData.get(i).erOinC+ weekRateData.get(i).inOltC+weekRateData.get(i).erOltC+weekRateData.get(i).inO+weekRateData.get(i).erO);
			
			weekRateData.get(i).setTotalVal(tempTotalVal/tempTotalVal2);
			
			if (weekRateData.get(i).getTotalVal()>=0){
				
			}else{
				weekRateData.get(i).setTotalVal(0); 
			}
		}

//		for (int i=0;i<weekRateData.size();i++){
//			System.out.print(DateFormat.getDateInstance(DateFormat.SHORT).format(weekRateData.get(i).getWeekStart())+" --- ");
//			System.out.print(DateFormat.getDateInstance(DateFormat.SHORT).format(weekRateData.get(i).getWeekEnd()) +" --- ");
//			System.out.print(weekRateData.get(i).inOinC+" --- ");
//			System.out.print(weekRateData.get(i).erOinC+" --- ");
//			System.out.print(weekRateData.get(i).inOltC+" --- ");
//			System.out.print(weekRateData.get(i).erOltC+" --- ");
//			System.out.print(weekRateData.get(i).inO+" --- ");
//			System.out.print(weekRateData.get(i).erO+" --- ");
//			System.out.print(weekRateData.get(i).totalVal+" --- ");
//			System.out.println();
//		}
		
		calcNormalval (weekRateData);
		
		try {
			
			
			FileOutputStream fileOut=null; 
			FileInputStream inputWeeklyRateData=null; 
			HSSFWorkbook workbook=null; 
			
			if (fileCreate==true){
				fileOut = new FileOutputStream(filePath+"weeklyRate.xls");
				workbook = new HSSFWorkbook();
			}
			else {
				inputWeeklyRateData = new FileInputStream(new File(filePath+"weeklyRate.xls"));
				workbook = new HSSFWorkbook(inputWeeklyRateData);
				
			}
			
			HSSFSheet worksheetRate = workbook.createSheet(fileName);
			HSSFRow row; 
			HSSFCell cell;
			
			for (int i=0;i<weekRateData.size();i++){
				
				row= worksheetRate.createRow(i); 
				cell= row.createCell(0);	cell.setCellValue(weekRateData.get(i).getWeekNum());
				cell= row.createCell(1);	cell.setCellValue(weekRateData.get(i).getWeekStart());
				cell= row.createCell(2);	cell.setCellValue(weekRateData.get(i).getWeekEnd());
				cell= row.createCell(3);	cell.setCellValue(weekRateData.get(i).inOinC);
				cell= row.createCell(4);	cell.setCellValue(weekRateData.get(i).erOinC);
				cell= row.createCell(5);	cell.setCellValue(weekRateData.get(i).inOltC);
				cell= row.createCell(6);	cell.setCellValue(weekRateData.get(i).erOltC);
				cell= row.createCell(7);	cell.setCellValue(weekRateData.get(i).inO);
				cell= row.createCell(8);	cell.setCellValue(weekRateData.get(i).erO);
				cell= row.createCell(9);	cell.setCellValue(weekRateData.get(i).totalVal);
				cell= row.createCell(10);	cell.setCellValue(weekRateData.get(i).normalTotalVal);
								
				if (fileName.equals("DFR")){
					cell= row.createCell(10);	cell.setCellValue(weekRateData.get(i).weeklyVal/7);
				}
				
			}
			
			
			
			if (fileCreate!=true){
				inputWeeklyRateData.close();
				fileOut = new FileOutputStream(filePath+"weeklyRate.xls");
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

	public static void oldBUP_weeklyCCRCalc (String fileName, ArrayList<WeekCalcStr> weekRateData, ArrayList<commitListStr> tempCommitData){
		
		
		
		
//		String commitDateCompare=null; 
//		String commitWKDateCompare=null;
//		
//		Date commitCreated, weekStart, weekEnd, lastWeekEnd=null; 
//		
//		for (int i=0;i<weekRateData.size();i++){
//			for (int j=0;j<tempCommitData.size();j++){
//				
//				commitCreated = tempCommitData.get(j).commitDate; 
//				weekStart = weekRateData.get(i).getWeekStart(); 
//				weekEnd = weekRateData.get(i).getWeekEnd(); 
//				if (weekRateData.get(i).getWeekNum()>1){
//					lastWeekEnd= weekRateData.get(i-1).getWeekEnd();
//				}
//				else if (weekRateData.get(i).getWeekNum()==1) {
//					lastWeekEnd = weekStart;
//				}
//				
//				if (commitCreated.before(weekStart)) commitDateCompare="before";  // -1 means issue created before week
//				else if (commitCreated.after (weekEnd)) commitDateCompare="after";
//				else if (commitCreated.after(weekStart) && 	commitCreated.before (weekEnd))  commitDateCompare="inWeek"; 
//				else if (commitCreated.equals(weekStart) || commitCreated.equals(weekEnd)) commitDateCompare="inWeek"; 		
//						
//				
//				if (commitCreated.before(lastWeekEnd)) commitWKDateCompare="sngbefore";  // -1 means issue created before week
//				else if (commitCreated.after (weekEnd)) commitWKDateCompare="sngafter";
//				else if (commitCreated.after(lastWeekEnd) && 	commitCreated.before (weekEnd))  commitWKDateCompare="snginWeek"; 
//				else if (commitCreated.equals(lastWeekEnd) || commitCreated.equals(weekEnd)) commitWKDateCompare="snginWeek"; 	
//				
//				
//				
//				
//				if (commitDateCompare.equals("inWeek")) weekRateData.get(i).uptoWeekVal = weekRateData.get(i).uptoWeekVal + tempCommitData.get(j).codeChurn; 
//				if (commitWKDateCompare.equals("snginWeek")) weekRateData.get(i).weeklyVal = weekRateData.get(i).weeklyVal + tempCommitData.get(j).codeChurn; 
//			}
//			
//			double tempTotalVal = (weekRateData.get(i).inOinC+weekRateData.get(i).erOinC);
//			double tempTotalVal2 = (weekRateData.get(i).inOinC+weekRateData.get(i).erOinC+ weekRateData.get(i).inOltC+weekRateData.get(i).erOltC+weekRateData.get(i).inO+weekRateData.get(i).erO);
//			
//			weekRateData.get(i).setTotalVal(weekRateData.get(i).weeklyVal/weekRateData.get(i).uptoWeekVal);
//			if (weekRateData.get(i).getTotalVal()>=0){
//				
//			}else{
//				weekRateData.get(i).setTotalVal(0);
//			}
//			
//		}
//		
//		
//		calcNormalval (weekRateData);
//				
//		try {
//			FileOutputStream fileOut=null; 
//			FileInputStream inputWeeklyRateData=null; 
//			HSSFWorkbook workbook=null; 
//			
//			inputWeeklyRateData = new FileInputStream(new File(filePath+"weeklyRate.xls"));
//			workbook = new HSSFWorkbook(inputWeeklyRateData);
//			
//			HSSFSheet worksheetRate = workbook.createSheet(fileName);
//			HSSFRow row; 
//			HSSFCell cell;
//			
//			for (int i=0;i<weekRateData.size();i++){
//				
//				row= worksheetRate.createRow(i); 
//				cell= row.createCell(0);	cell.setCellValue(weekRateData.get(i).getWeekNum());
//				cell= row.createCell(1);	cell.setCellValue(weekRateData.get(i).getWeekStart());
//				cell= row.createCell(2);	cell.setCellValue(weekRateData.get(i).getWeekEnd());
//				cell= row.createCell(3);	cell.setCellValue(weekRateData.get(i).uptoWeekVal);
//				cell= row.createCell(4);	cell.setCellValue(weekRateData.get(i).weeklyVal);
//				cell= row.createCell(5);	cell.setCellValue(weekRateData.get(i).totalVal);
//				
//				
//				
//				
//			}
//			
//			inputWeeklyRateData.close();
//			fileOut = new FileOutputStream(filePath+"weeklyRate.xls");
//			
//			workbook.write(fileOut);
//			fileOut.flush();
//			fileOut.close();
//			System.out.println("Success: written "+ fileName);
//			
//		} catch (FileNotFoundException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//			System.out.println("Error: writing "+ fileName);
//		}catch (IOException e2){
//			e2.printStackTrace();
//			System.out.println("Error: writing "+ fileName);
//		}
		
	}
	
	
public static void weeklyCCRCalc (String fileName, ArrayList<WeekCalcStr> weekRateData, ArrayList<commitListStr> tempCommitData){
		
		
		
		
		String commitDateCompare=null; 
		String commitWKDateCompare=null;
		
		Date commitCreated, weekStart, weekEnd, lastWeekEnd=null; 
		
		for (int i=0;i<weekRateData.size();i++){
			for (int j=0;j<tempCommitData.size();j++){
				
				commitCreated = tempCommitData.get(j).commitDate; 
				weekStart = weekRateData.get(i).getWeekStart(); 
				weekEnd = weekRateData.get(i).getWeekEnd(); 
				if (weekRateData.get(i).getWeekNum()>1){
					lastWeekEnd= addDate(1, weekRateData.get(i-1).getWeekEnd());
					
					
					
				}
				else if (weekRateData.get(i).getWeekNum()==1) {
					lastWeekEnd = weekStart;
				}
				
				if (commitCreated.before(weekStart)) commitDateCompare="before";  // -1 means issue created before week
				else if (commitCreated.after (weekEnd)) commitDateCompare="after";
				else if (commitCreated.after(weekStart) && 	commitCreated.before (weekEnd))  commitDateCompare="inWeek"; 
				else if (commitCreated.equals(weekStart) || commitCreated.equals(weekEnd)) commitDateCompare="inWeek"; 		
						
				
//				if (commitCreated.before(lastWeekEnd)) commitWKDateCompare="sngbefore";  // -1 means issue created before week
//				else if (commitCreated.after (weekEnd)) commitWKDateCompare="sngafter";
//				else if (commitCreated.after(lastWeekEnd) && 	commitCreated.before (weekEnd))  commitWKDateCompare="snginWeek"; 
//				else if (commitCreated.equals(lastWeekEnd) || commitCreated.equals(weekEnd)) commitWKDateCompare="snginWeek"; 	
				
				
				
				
				
				
				
				
				
				
				if (commitDateCompare.equals("inWeek")) {
					
					
					
					
					
					
					
					weekRateData.get(i).uptodiff = weekRateData.get(i).uptodiff + tempCommitData.get(j).codeChurn; 
					weekRateData.get(i).uptofilenumber = weekRateData.get(i).uptofilenumber + tempCommitData.get(j).numofFiles; 
					weekRateData.get(i).uptocoments = weekRateData.get(i).uptocoments + tempCommitData.get(j).numfComments; 
					weekRateData.get(i).uptocommits = weekRateData.get(i).uptocommits + 1;  
				
					boolean bool_flagContributorMatch = false; 
					
					System.out.println(tempCommitData.get(j).author+"-----------");
					for (String contributorIterator : weekRateData.get(i).uptoContributorsList){
						if (contributorIterator.equalsIgnoreCase(tempCommitData.get(j).author)){
							bool_flagContributorMatch = true; 
							System.out.println("$$$$$$$$$$$$$$$$$$$$$");
							
							
						}
						
					}
					
					if (bool_flagContributorMatch == false){
						weekRateData.get(i).uptocontributors = weekRateData.get(i).uptocontributors + 1;
						weekRateData.get(i).uptoContributorsList.add(tempCommitData.get(j).author);
					}
					
					
					weekRateData.get(i).avgDiffperCommit = weekRateData.get(i).uptodiff/weekRateData.get(i).uptocommits; 
					weekRateData.get(i).avgDiffperContributor = weekRateData.get(i).uptodiff/weekRateData.get(i).uptocontributors; 
					weekRateData.get(i).avgDiffperFile = weekRateData.get(i).uptodiff/weekRateData.get(i).uptofilenumber; 
					weekRateData.get(i).avgFileperCommit = 	weekRateData.get(i).uptofilenumber/weekRateData.get(i).uptocommits;
					weekRateData.get(i).avgFilepercontributor = weekRateData.get(i).uptofilenumber/weekRateData.get(i).uptocontributors;
				
				}	 
				
								
//				if (commitWKDateCompare.equals("snginWeek")) weekRateData.get(i).weeklyVal = weekRateData.get(i).weeklyVal + tempCommitData.get(j).codeChurn; 
			}
			
//			double tempTotalVal = (weekRateData.get(i).inOinC+weekRateData.get(i).erOinC);
//			double tempTotalVal2 = (weekRateData.get(i).inOinC+weekRateData.get(i).erOinC+ weekRateData.get(i).inOltC+weekRateData.get(i).erOltC+weekRateData.get(i).inO+weekRateData.get(i).erO);
			
//			weekRateData.get(i).setTotalVal(weekRateData.get(i).weeklyVal/weekRateData.get(i).uptoWeekVal);
//			if (weekRateData.get(i).getTotalVal()>=0){
//				
//			}else{
//				weekRateData.get(i).setTotalVal(0);
//			}
			
		}
		
		
		calcNormalval (weekRateData);
				
		try {
			FileOutputStream fileOut=null; 
			FileInputStream inputWeeklyRateData=null; 
			HSSFWorkbook workbook=null; 
			
			inputWeeklyRateData = new FileInputStream(new File(filePath+"weeklyRate.xls"));
			workbook = new HSSFWorkbook(inputWeeklyRateData);
			
			HSSFSheet worksheetRate = workbook.createSheet(fileName);
			HSSFRow row; 
			HSSFCell cell;
			
				
			
				
				row= worksheetRate.createRow(0); 
				cell= row.createCell(0);	cell.setCellValue("Daynumber");
				cell= row.createCell(1);	cell.setCellValue("Week_start");
				cell= row.createCell(2);	cell.setCellValue("Week_end");
				cell= row.createCell(3);	cell.setCellValue("uptofilenumber");
//				cell= row.createCell(4);	cell.setCellValue(weekRateData.get(i).weeklyVal);
				cell= row.createCell(4);	cell.setCellValue("uptodiff");
				cell= row.createCell(5);	cell.setCellValue("uptocoments");
				cell= row.createCell(6);	cell.setCellValue("uptocommits");
				cell= row.createCell(7);	cell.setCellValue("uptocontributors");
				cell= row.createCell(8);	cell.setCellValue("avgDiffperFile");
				cell= row.createCell(9);	cell.setCellValue("avgDiffperCommit");
				cell= row.createCell(10);	cell.setCellValue("avgDiffperContributor");
				cell= row.createCell(11);	cell.setCellValue("avgFileperCommit");
				cell= row.createCell(12);	cell.setCellValue("avgFilepercontributor");
			
			
			
			
			
			
			
			for (int i=0;i<weekRateData.size();i++){
				
				row= worksheetRate.createRow(i+1); 
				cell= row.createCell(0);	cell.setCellValue(weekRateData.get(i).getWeekNum());
				cell= row.createCell(1);	cell.setCellValue(weekRateData.get(i).getWeekStart());
				cell= row.createCell(2);	cell.setCellValue(weekRateData.get(i).getWeekEnd());
				cell= row.createCell(3);	cell.setCellValue(weekRateData.get(i).uptofilenumber);
//				cell= row.createCell(4);	cell.setCellValue(weekRateData.get(i).weeklyVal);
				cell= row.createCell(4);	cell.setCellValue(weekRateData.get(i).uptodiff);
				cell= row.createCell(5);	cell.setCellValue(weekRateData.get(i).uptocoments);
				cell= row.createCell(6);	cell.setCellValue(weekRateData.get(i).uptocommits);
				cell= row.createCell(7);	cell.setCellValue(weekRateData.get(i).uptocontributors);
				cell= row.createCell(8);	cell.setCellValue(weekRateData.get(i).avgDiffperFile);
				cell= row.createCell(9);	cell.setCellValue(weekRateData.get(i).avgDiffperCommit);
				cell= row.createCell(10);	cell.setCellValue(weekRateData.get(i).avgDiffperContributor);
				cell= row.createCell(11);	cell.setCellValue(weekRateData.get(i).avgFileperCommit);
				cell= row.createCell(12);	cell.setCellValue(weekRateData.get(i).avgFilepercontributor);
			}
			
			inputWeeklyRateData.close();
			fileOut = new FileOutputStream(filePath+"weeklyRate.xls");
			
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
	
	
	
	
	
	
	
		
	public static void calcNormalval (ArrayList<WeekCalcStr> TempWFRIssues){
		double minVal=1, maxVal=0; 
		for (int i=0;i<TempWFRIssues.size();i++){
			if (TempWFRIssues.get(i).totalVal<minVal){
				minVal= TempWFRIssues.get(i).totalVal;
			}
			if (TempWFRIssues.get(i).totalVal>maxVal){
				maxVal=TempWFRIssues.get(i).totalVal; 
			}
		}
		
		for (int i=0; i<TempWFRIssues.size();i++){
			TempWFRIssues.get(i).normalTotalVal = (TempWFRIssues.get(i).totalVal-minVal)/(maxVal-minVal); 
			
			if (TempWFRIssues.get(i).normalTotalVal>=0){
				
			}
			else{
				TempWFRIssues.get(i).normalTotalVal=0;
			}
		}
		
		
		
	}
	
	public static void calcRRVal (String fileName){
		for (int i=0;i<BFRData.size();i++){
			RRValues.add(new RRListStr()); 
			RRValues.get(i).weekStart = BFRData.get(i).getWeekStart(); 
			RRValues.get(i).weekEnd = BFRData.get(i).getWeekEnd(); 
			RRValues.get(i).weeknum = BFRData.get(i).getWeekNum(); 
			
		
			// In normal case this is the RR value calculation for Weekly Rate 
			
//			RRValues.get(i).RRVal = (BFRData.get(i).normalTotalVal+FCRData.get(i).normalTotalVal+ICRData.get(i).normalTotalVal+
//					PCRData.get(i).normalTotalVal+CCRData.get(i).normalTotalVal+DFRData.get(i).normalTotalVal)/6; 
			
			// This is created just to skip a few attributes from consideration in weekly rates. Those values are multiplied with 0 
			
			RRValues.get(i).RRVal = (1*BFRData.get(i).normalTotalVal+
					0*FCRData.get(i).normalTotalVal+
					0*ICRData.get(i).normalTotalVal+
					0*PCRData.get(i).normalTotalVal+
					1*CCRData.get(i).normalTotalVal+
					1*DFRData.get(i).normalTotalVal)/3; 
			
			
		}
		
		try {
			FileOutputStream fileOut=null; 
			FileInputStream inputWeeklyRateData=null; 
			HSSFWorkbook workbook=null; 
			
			inputWeeklyRateData = new FileInputStream(new File(filePath+"weeklyRate.xls"));
			workbook = new HSSFWorkbook(inputWeeklyRateData);
			
			HSSFSheet worksheetRate = workbook.createSheet(fileName);
			HSSFRow row; 
			HSSFCell cell;
			
			for (int i=0;i<RRValues.size();i++){
				
				row= worksheetRate.createRow(i); 
				cell= row.createCell(0);	cell.setCellValue(RRValues.get(i).weeknum);
				cell= row.createCell(1);	cell.setCellValue(RRValues.get(i).weekStart);
				cell= row.createCell(2);	cell.setCellValue(RRValues.get(i).weekEnd);
				cell= row.createCell(3);	cell.setCellValue(RRValues.get(i).RRVal);
			}
			
			inputWeeklyRateData.close();
			fileOut = new FileOutputStream(filePath+"weeklyRate.xls");
			
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



	
		
		
	
	
	
		
		
		
		
		

        
        
        
        
        
	
	

}//End Class
