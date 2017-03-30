package dataInput;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;



public class postTopicAnalysis {

	
	public void readTopicAnalysis (String filePath, String wtFilePath, int trainingWeeks, int uptoWeek, int numOfTopics){
		
		double distMat [][];
		try {
			
			FileOutputStream fileOut=new FileOutputStream(wtFilePath+"Top Results.xls");// In later version top results is read directly from mother folder insted of results folder. fix it before running. 
			HSSFWorkbook outworkbook = new HSSFWorkbook();
			HSSFSheet outworksheet = outworkbook.createSheet("results");
			HSSFRow row; 
			HSSFCell cell;
			int rowCounter =0; 
			
			
			for (int i=trainingWeeks;i<=uptoWeek;i++){ //uptoWeek
				
					System.out.println("Reading Excel file sheet"+ i+".txt");
					ArrayList <topicAnalysisListStr > taList = new ArrayList<topicAnalysisListStr> (); 
					topicAnalysisListStr tempTAList = new topicAnalysisListStr (); 
										        
					FileInputStream file = new FileInputStream(new File(filePath+i+".xls"));		
					//Create Workbook instance holding reference to .xlsx file
				    HSSFWorkbook workbook = new HSSFWorkbook(file);
				    //Get first/desired sheet from the workbook
				    HSSFSheet sheet = workbook.getSheet("TA");
				    
				    tempTAList= readData (sheet, taList, numOfTopics, i); 
				     
				    file.close();
				    
				    
				distMat = new double [taList.size()][2];
				System.out.println("--------------------"+ i+"------------------");
			  	eucleanCalc (taList, tempTAList, distMat); 
			   
			  	double[][] values = new double [distMat.length][2]; //			   
				values= findTopNValues(distMat, 10,i); 
				   
				 for (int j=0;j<values.length;j++){
					 row = outworksheet.createRow(rowCounter); 
					 rowCounter++;
					 cell= row.createCell(0); 
					 cell.setCellValue(i);
					 
					 cell= row.createCell(1); 
					 cell.setCellValue(values[j][0]);
					 
					 cell= row.createCell(2); 
					 cell.setCellValue(values[j][1]);
					 
					 //System.out.println(values[j][0]+"---"+values[j][1]);
				   }
			
			}
			
			outworkbook.write(fileOut);
			fileOut.flush();
			fileOut.close();
			System.out.println("Success: written "+ "Top Results.xls");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	
	
	
	
	
	public topicAnalysisListStr readData (HSSFSheet sheet, ArrayList <topicAnalysisListStr > taList, int numOfTopics, int i){
		Iterator<Row> rowIterator = sheet.iterator();
	    
	    int taListCounter = -1 ; 
	    topicAnalysisListStr tempTAList = new topicAnalysisListStr ();
	    
	    while (rowIterator.hasNext()) {
	    	taListCounter++;
	    	Row row = rowIterator.next();
	    	taList.add(new topicAnalysisListStr()); 
	    	taList.get(taListCounter).fileSource = row.getCell(0).getStringCellValue(); 
	    	taList.get(taListCounter).fileName = row.getCell(1).getStringCellValue();
		    taList.get(taListCounter).weekNum =  row.getCell(2).getNumericCellValue();
	    	
	    	int topicCounter =4; 
	    	for (int j=0;j<numOfTopics;j++){
	    		
	    		taList.get(taListCounter).topicDist.add(row.getCell(topicCounter).getNumericCellValue()); 
	    		topicCounter= topicCounter+2; 
	    	}
	    	
	    	
		    
		    if (taList.get(taListCounter).weekNum == (double)i){
		    	tempTAList= taList.get(taListCounter);
		    }
		     
		    
	    }
	
//	    System.out.println(tempTAList.fileSource); 
//	    System.out.println(tempTAList.fileName); 
//	    System.out.println(tempTAList.weekNum); 
//	    System.out.println(tempTAList.topicDist.get(3)); 
//	    System.out.println(tempTAList.topicDist.get(5)); 
//	    System.out.println("------------------"); 
	    return tempTAList;
	    
	}
	
	
	public static void eucleanCalc (ArrayList <topicAnalysisListStr > taList, topicAnalysisListStr tempTAList, double distMat[][]){
		
		
		for (int i=0;i<taList.size();i++){
			double eucledeanValue = 0; 
			
			for (int j=0;j<taList.get(i).topicDist.size();j++){
				eucledeanValue = eucledeanValue + Math.abs(taList.get(i).topicDist.get(j)-tempTAList.topicDist.get(j)); 
			}
			eucledeanValue = Math.sqrt(eucledeanValue); 
			taList.get(i).eucDistance= eucledeanValue; 
			
			
			distMat[i][0]= taList.get(i).weekNum;
			distMat[i][1]= taList.get(i).eucDistance; 
			
			System.out.println(distMat[i][0]+ ", "+ distMat[i][1]); 
			
			
//			System.out.println("@@@@@@@@"+ distMat[i][0]+ "------"+distMat[i][1] );
		}
	}
	 
    
    public double[][] findTopNValues(double distMat[][], int n, int currentWeek) {

    	double distMatEucl [] = new double [distMat.length];
    	double distMatWeek [] = new double [distMat.length]; 
    	double tempdistMat[][] = new double [distMat.length][2]; 
    	
    	for (int j=0;j<distMat.length;j++){
    			distMatWeek [j]=distMat[j][0];
    			distMatEucl [j]=distMat[j][1];
    			//System.out.println(distMatWeek [j]+"()()()()"+distMatEucl [j]);
    		}
    	
        int length = distMatEucl.length;
        for (int i=1; i<length; i++) {
        	if (distMat[i][0] != currentWeek){
        		int curPos = i;
                while ((curPos > 0) && (distMatEucl[i] < distMatEucl[curPos-1])) {
                    curPos--;
                }

                if (curPos != i) {
                    double element = distMatEucl[i];
                    double week = distMatWeek[i];
                    System.arraycopy(distMatEucl, curPos, distMatEucl, curPos+1, (i-curPos));
                    System.arraycopy(distMatWeek, curPos, distMatWeek, curPos+1, (i-curPos));
                    distMatEucl[curPos] = element;
                    distMatWeek[curPos] = week;
                }
        	}
        }       
        
        for (int i=0;i<distMat.length;i++){
        	tempdistMat[i][0]= distMatWeek[i]; 
        	tempdistMat[i][1]= distMatEucl[i]; 
        }

        return Arrays.copyOf(tempdistMat, n);        
    }   
    
    
   
    
    
    
    
    

}
