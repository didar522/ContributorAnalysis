package dataInput;

import java.util.ArrayList;

import dataInput.finalResultsWR.finalResultListStr;

public class precisionRecallMsr {

	class classifyLabel {
		double weekNum, actualWeekVal, curBestVal=100, curAvgVal, ftrBestVal=100, ftrAvgVal; 
		String str_actualWeekVal, str_curBestVal, str_curAvgVal, str_ftrBestVal, str_ftrAvgVal; 
	}
	
	
	class precisionData {
		String labelType; 
		int actual, avg_curpredict, avg_curmatch, avg_ftrpredict, avg_ftrmatch, best_curpredict, best_curmatch, best_ftrpredict, best_ftrmatch ; 
	}
	
	public void precisionRecallMsrMain (ArrayList <finalResultListStr> list_finalResultListStr, String var, ArrayList <classifyLabel> RRLabel, ArrayList <precisionData> precisionData){
		
		int varToConsider=-1; 
		
		if (var.equals("BFR")) varToConsider=0; 			
		else if (var.equals("DFR")) varToConsider=1;
		else if (var.equals("ICR")) varToConsider=2;
		else if (var.equals("PCR")) varToConsider=3;
		else if (var.equals("CCR")) varToConsider=4;
		else if (var.equals("FCR")) varToConsider=5;
		else if (var.equals("RRVal")) varToConsider=6;
		else if (var.equals("AggregatedRR")) varToConsider=7;
		else if (var.equals("RegressionRR")) varToConsider=8;
		
		
		
		outLabelling (list_finalResultListStr, RRLabel, varToConsider); 
		precisioncalc (precisionData, RRLabel); 
		
	}
	
	
	
	
	
	public void outLabelling (ArrayList <finalResultListStr> list_finalResultListStr, ArrayList <classifyLabel> RRLabel, int vartoConsider){
		
		double startingWeek = 80; /// change for training set 
		double finishWeek = 110; 
		
		for (int i=0;i<=(finishWeek-startingWeek);i++){
			RRLabel.add(new classifyLabel());
			RRLabel.get(i).weekNum=startingWeek+i; 
		}
		
		
		for (int i=0;i<list_finalResultListStr.size();i++){
			int labelCounter = (int)(list_finalResultListStr.get(i).currentWeekNum-startingWeek); 
			System.out.println(list_finalResultListStr.get(i).currentWeekNum  +" "+ startingWeek);
			RRLabel.get(labelCounter).actualWeekVal = list_finalResultListStr.get(i).orgRates[vartoConsider]; 
		
		}
		double tempstartweek1=0, tempstartweek2=0, flag=0; 
		for (int i=0;i<list_finalResultListStr.size();i++){
			int labelCounter = (int)(list_finalResultListStr.get(i).currentWeekNum-startingWeek); 
			
			
			
			RRLabel.get(labelCounter).curAvgVal= RRLabel.get(labelCounter).curAvgVal+list_finalResultListStr.get(i).curRates[vartoConsider]; 
			if (labelCounter==tempstartweek1){
				RRLabel.get(labelCounter).curBestVal= list_finalResultListStr.get(i).curRates[vartoConsider];
				tempstartweek1=tempstartweek1+1;
				
			}
			
			
			if (labelCounter<RRLabel.size()-1){
				RRLabel.get(labelCounter+1).ftrAvgVal= RRLabel.get(labelCounter+1).ftrAvgVal+list_finalResultListStr.get(i).ftrRates[vartoConsider]; 
				if (labelCounter==tempstartweek2){
					flag=flag+1; 
					if (flag==2){
						RRLabel.get(labelCounter+1).ftrBestVal= list_finalResultListStr.get(i).ftrRates[vartoConsider];
						tempstartweek2=tempstartweek2+1;
						flag=0;
					}
					
				}
			}
		}
		
		
		for (int i=0;i<RRLabel.size();i++){
			RRLabel.get(i).str_actualWeekVal= identifyFiveLabel(RRLabel.get(i).actualWeekVal); 
			RRLabel.get(i).curAvgVal=RRLabel.get(i).curAvgVal/10;
			RRLabel.get(i).str_curAvgVal= identifyFiveLabel(RRLabel.get(i).curAvgVal); 
			RRLabel.get(i).str_curBestVal= identifyFiveLabel(RRLabel.get(i).curBestVal); 
			RRLabel.get(i).ftrAvgVal=RRLabel.get(i).ftrAvgVal/10;
			RRLabel.get(i).str_ftrAvgVal= identifyFiveLabel(RRLabel.get(i).ftrAvgVal); 
			RRLabel.get(i).str_ftrBestVal= identifyFiveLabel(RRLabel.get(i).ftrBestVal); 
			 
		}
		
		
		
		
		
//		for (int i=0;i<(finishWeek-startingWeek);i++){
//			System.out.println(RRLabel.get(i).weekNum);
//			//System.out.print("----"+RRLabel.get(i).actualWeekVal); 
//			System.out.print("----"+RRLabel.get(i).str_actualWeekVal); 
//			//System.out.print("----"+RRLabel.get(i).curAvgVal);
//			System.out.print("----"+RRLabel.get(i).str_curAvgVal);
//			//System.out.print("----"+RRLabel.get(i).curBestVal);
//			System.out.print("----"+RRLabel.get(i).str_curBestVal);
//			//System.out.print("----"+RRLabel.get(i).ftrAvgVal);
//			System.out.print("----"+RRLabel.get(i).str_ftrAvgVal);
//			//System.out.print("----"+RRLabel.get(i).ftrBestVal);
//			System.out.print("----"+RRLabel.get(i).str_ftrBestVal);
//			
//		}
	}


public String identifyFiveLabel (Double value){
	if (value>=0 && value<0.2) return "verylow"; 
	else if (value>=0.2 && value<0.4) return "low"; 
	else if (value>=0.4 && value<0.6) return "mid"; 
	else if (value>=0.6 && value<0.8) return "high"; 
	else if (value>=0.8 && value<=1.00) return "veryhigh"; 
	else return "NA";  
}


public void precisioncalc (ArrayList <precisionData> precisionData, ArrayList <classifyLabel> RRLabel){
	
	String labels [] = {"verylow", "low", "mid", "high", "veryhigh"}; 
	
	
	for (int j=0;j< labels.length;j++){
		
		precisionData.add(new precisionData());
		precisionData.get(j).labelType= labels[j]; 
		
		
		for (int i=0; i<RRLabel.size();i++){
			if (RRLabel.get(i).str_actualWeekVal.equals(labels [j])) precisionData.get(j).actual++; 
			
			if (RRLabel.get(i).str_curAvgVal.equals(labels [j])) precisionData.get(j).avg_curpredict++;
			if (RRLabel.get(i).str_actualWeekVal.equals(labels [j]) && RRLabel.get(i).str_curAvgVal.equals(labels [j])) precisionData.get(j).avg_curmatch++; 
			
			if (RRLabel.get(i).str_curBestVal.equals(labels [j])) precisionData.get(j).best_curpredict++;
			if (RRLabel.get(i).str_actualWeekVal.equals(labels [j]) && RRLabel.get(i).str_curBestVal.equals(labels [j])) precisionData.get(j).best_curmatch++; 
			
			
			if (RRLabel.get(i).str_ftrAvgVal.equals(labels [j])) precisionData.get(j).avg_ftrpredict++;
			if (RRLabel.get(i).str_actualWeekVal.equals(labels [j]) && RRLabel.get(i).str_ftrAvgVal.equals(labels [j])) precisionData.get(j).avg_ftrmatch++; 
			
			if (RRLabel.get(i).str_ftrBestVal.equals(labels [j])) precisionData.get(j).best_ftrpredict++;
			if (RRLabel.get(i).str_actualWeekVal.equals(labels [j]) && RRLabel.get(i).str_ftrBestVal.equals(labels [j])) precisionData.get(j).best_ftrmatch++; 
		}
	}
	
	
	
	
}

	
	
}
