package dataInput;

//Access to DitHub Accounts
//"didar522", "Git@Hub123"  
//"naziabenozir", "ammu69GITHUB05ma"  
//"shawniut", "shawonma151" 
//"adnan522", "ammu6905ma"





import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.eclipse.egit.github.core.CommitComment;
import org.eclipse.egit.github.core.Comment;
import org.eclipse.egit.github.core.CommitFile;
import org.eclipse.egit.github.core.Issue;
import org.eclipse.egit.github.core.PullRequest;
import org.eclipse.egit.github.core.RepositoryCommit;
import org.eclipse.egit.github.core.RepositoryId;
import org.eclipse.egit.github.core.RepositoryIssue;
import org.eclipse.egit.github.core.service.CommitService;
import org.eclipse.egit.github.core.service.IssueService;
import org.eclipse.egit.github.core.service.PullRequestService;
import org.eclipse.egit.github.core.service.RepositoryService;

public class FetchCntbtrMessages {
	
	public static HSSFWorkbook writeBookPullCommitData = new HSSFWorkbook();
	public static HSSFSheet writeSheetIssueData =  writeBookPullCommitData.createSheet("IssueData");
	public static Row row,rowcommit,rowPull; 
	public static Cell cell, cellcommit,cellPull; 
	public static int pullNumber =0, issueNumber=0;  
	public static String str[] = new String [31]; 
	public static String tempString; 
	
	//-------------------------To Do -------
    // change file save location
	
//	public static String savedFileLocation = "C:/Users/S.M.Didar/Dropbox/Didar DBPC/PhD Research/Winter 2015/ICSE 2016/Experiment/E V2/";
		
	//-------------------------To Do -------
	// Last issue number for issueservice works - after issue service line initialization -- must need to specify the last number of the issue. 
		
//	public static int lastIssueNumber = 40; // need to specify the last number of the issue.  
	public static int uptoIssueNumber = 0; // need to specify the upto number of the issue.
	
//	public static String repoOwner = "Orientechnologies";
//	public static String repoName = "Orientdb";
	
	 

	
	
	public FetchCntbtrMessages() {
		// TODO Auto-generated constructor stub
	}

	public void fetchMsgData (String user, String pwd, String repoOwner, String repoName, String savedFileLocation, int  lastIssueNumber) throws IOException {
		// TODO Auto-generated method stub
		
		// -----------------------To do ----------
		// Common for all cases change access infor and repo names. 
				
		RepositoryService repservice = new RepositoryService();
		repservice.getClient().setCredentials(user, pwd);
        RepositoryId repo = new RepositoryId(repoOwner,repoName); 
        
		
        // -----------------------To do ----------
		// Select the type of service wanted. 
        
        IssueService issueservice = new IssueService ();
        List<RepositoryIssue> issueList = null;
        List<Comment> commentList = null; 
        
	    try {
	    	//----------------------To do --------------------------------// Provide github access
	    	issueservice.getClient().setCredentials(user, pwd);
	    } catch (Exception e) {
          e.printStackTrace();
		}
	    
 	    try{	    
		int rowCounter = 0; 
		row = writeSheetIssueData.createRow(rowCounter); 
				
		for (int i=lastIssueNumber;i>uptoIssueNumber;i--){ //lastIssueNumber
	    	
	    	Issue IssueTemp=null;
	    	issueNumber = i; 
	    	System.out.println("Processing "+ issueNumber);
	    	
	    	try{
	    		
	    		commentList= issueservice.getComments(repo, issueNumber); 	    		
	    		IssueTemp = issueservice.getIssue(repo, issueNumber); 
	    	}
	    	catch (Exception EX){
	    		System.out.println("Issue number "+ issueNumber +"was not found");
	    		continue;
	    	}
	    	
	    	cell = row.createCell(0);
    		cell.setCellValue(issueNumber);
    		cell = row.createCell(1);
    		cell.setCellValue(IssueTemp.getCreatedAt());
    		cell = row.createCell(2);
	    	String state =IssueTemp.getState(); 
	    	cell.setCellValue(state);
	    	
	    	if (state.matches("closed")){
	    		cell = row.createCell(3);
		    	cell.setCellValue(IssueTemp.getClosedAt());
	    	}
	    	else cell.setCellValue(" ");
	    	cell = row.createCell(4);
	    	cell.setCellValue(IssueTemp.getComments());
	    	
	    	cell = row.createCell(7);
			cell.setCellValue(IssueTemp.getTitle());
			
			rowCounter++;	
	    	row = writeSheetIssueData.createRow(rowCounter); 
	    	
	    	String strIssueBody = IssueTemp.getBody(); 
    		//System.out.println(strIssueBody.length()); 
    		if (strIssueBody != null){	    	
		    	if (strIssueBody.length()>32500){
	    			
	    			//System.out.println("too big to consider");
	    			tempString = strIssueBody.substring(0, 2000);
	    			cell = row.createCell(7);
	    			cell.setCellValue(tempString);
	    			
	    			//System.out.println(tempString);
	    		}
	    		else {
	    			cell = row.createCell(7);
	    			cell.setCellValue(IssueTemp.getBody());
	    		}
    		}
    		
    		cell = row.createCell(8);
	    	cell.setCellValue(IssueTemp.getUrl());
	    	
	    	
	    	rowCounter++;	
	    	row = writeSheetIssueData.createRow(rowCounter); 
	    	
	    	// writing all the comments associated with this issue. 
	    	
	    	for (int commentCount=0;commentCount<commentList.size();commentCount++){
				
	    		
	    		cell = row.createCell(5);
	    		cell.setCellValue(commentList.get(commentCount).getId());	    				
	    		cell = row.createCell(6);
	    		cell.setCellValue(commentList.get(commentCount).getCreatedAt());
	    		
	    		String strCommentBody = commentList.get(commentCount).getBody(); 
	    		//System.out.println(strCommentBody.length()); 
	    		if (strCommentBody.length()>32500){
	    			
	    			//System.out.println("too big to consider");
	    			tempString = strCommentBody.substring(0, 2000);
	    			cell = row.createCell(7);
	    			cell.setCellValue(tempString);
	    		}
	    		else {
	    			cell = row.createCell(7);
	    			cell.setCellValue(commentList.get(commentCount).getBody());
	    		}
	    		
	    		cell = row.createCell(8);
	    		cell.setCellValue(commentList.get(commentCount).getUrl());
	    		cell = row.createCell(9);
	    		cell.setCellValue(commentList.get(commentCount).getUser().getId());
	    		
	    		rowCounter++;	    		
	    		row = writeSheetIssueData.createRow(rowCounter); 
				
			}
	   } 	
	}
	catch(Exception ex){
		
		System.out.println(ex.getMessage());
		ex.printStackTrace();
		
		try {
		    FileOutputStream out = new FileOutputStream(new File(savedFileLocation+"Commentdata.xls"));
		    writeBookPullCommitData.write(out);
		    out.close();
		    System.out.println("Excel File Created Successfully under catch primary IO");
		     
		} catch (FileNotFoundException e) {
		    e.printStackTrace();
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}
	   
		
///////////////////////////////////  Common print results in files    ////////////////////////////////	
	
	
	try {
		    FileOutputStream out = new FileOutputStream(new File(savedFileLocation+repoOwner+","+repoName+".xls"));
		    writeBookPullCommitData.write(out);
		    out.close();
		    System.out.println("Excel File Created Successfully under catch - File output action");
		     
		} catch (FileNotFoundException e) {
		    e.printStackTrace();
		} catch (IOException e) {
		    e.printStackTrace();
		}
		
	}
	
}




























