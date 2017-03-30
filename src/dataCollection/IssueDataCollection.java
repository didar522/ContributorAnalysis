package dataCollection;



//Access to DitHub Accounts
//"didar522", "Git@Hub123"  a6mGITHUB
//"naziabenozir", "ammu69GITHUB05ma"  
//"shawniut", "shawonma151" 
//"adnan522", "ammu6905ma"





import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.eclipse.egit.github.core.CommitFile;
import org.eclipse.egit.github.core.PullRequest;
import org.eclipse.egit.github.core.RepositoryCommit;
import org.eclipse.egit.github.core.RepositoryId;
import org.eclipse.egit.github.core.RepositoryIssue;
import org.eclipse.egit.github.core.service.CommitService;
import org.eclipse.egit.github.core.service.IssueService;
import org.eclipse.egit.github.core.service.PullRequestService;
import org.eclipse.egit.github.core.service.RepositoryService;

public class IssueDataCollection {
	
	
	
	
	
	public static HSSFWorkbook writeBookPullCommitData = new HSSFWorkbook();
	public static HSSFSheet writeSheetIssueData =  writeBookPullCommitData.createSheet("IssueData");
//	public static HSSFSheet writeSheetPullData =  writeBookPullCommitData.createSheet("PullData");
//	public static HSSFSheet writeSheetCommitData =  writeBookPullCommitData.createSheet("CommitData");
	public static Row row,rowcommit,rowPull; 
	public static Cell cell, cellcommit,cellPull; 
	public static int pullNumber =0, issueNumber=0;  
	public static String str[] = new String [31]; 
	public static int lastIssueNumber = 100;// need to specify the last number of the issue.  
	public static String [] GitCredits = {"adnan522", "ammu6905ma"};
	
	
//	public static String filePath= "C:/Users/S.M.Didar/Dropbox/Didar DBPC/PhD Research/Winter 2015/ICSE 2016/Experiment/E V2/";
//	public static String fileName = "pullcommitissuedata.xls"; 
	
	//To Do -------
	// Last issue number for issueservice works - after issue service line initialization. 
	
	
	
	
	
	public IssueDataCollection() {
		// TODO Auto-generated constructor stub
	}

	public static void issueCollection (String filePath, String fileName, int templastIssueNumber) throws Exception{
		// TODO Auto-generated method stub
		
		lastIssueNumber=templastIssueNumber; 
		RepositoryService repservice = new RepositoryService();
		repservice.getClient().setCredentials(GitCredits[0], GitCredits[1]);
        RepositoryId repo = new RepositoryId("publify", "publify");
		
		IssueService issueservice = new IssueService ();
//        CommitService commitservice = new CommitService ();
//		PullRequestService pullservice = new PullRequestService();
        
		List<PullRequest> pullList = null;
//		List<RepositoryCommit> commitList =null; 
//		List<RepositoryIssue> issueList = null;
		
	    try {
//	    	pullservice.getClient().setCredentials(GitCredits[0], GitCredits[1]);
//	    	pullList=pullservice.getPullRequests(repo, "closed");
//	    	commitservice.getClient().setCredentials(GitCredits[0], GitCredits[1]);
//	    	commitList=commitservice.getCommits(repo);
	    	issueservice.getClient().setCredentials(GitCredits[0], GitCredits[1]);
	    	
	    	
		} catch (Exception e) {
          e.printStackTrace();
		}
	    
//	    write pull data headings
//	    {
//	    	row = writeSheetPullData.createRow(0); 
//	    	cell = row.createCell(0);
//	    	cell.setCellValue("Number");
//	    	cell = row.createCell(1);
//	    	cell.setCellValue("Created At");
//	    	cell = row.createCell(2);
//	    	cell.setCellValue("Closed At");
//	    	cell = row.createCell(3);
//	    	cell.setCellValue("Additions");
//	    	cell = row.createCell(4);
//	    	cell.setCellValue("Deletion");
//	    	cell = row.createCell(5);
//	    	cell.setCellValue("Total Change");
//	    	cell = row.createCell(6);
//	    	cell.setCellValue("Changed files");
//	    	cell = row.createCell(7);
//	    	cell.setCellValue("Commits");
//	    	cell = row.createCell(8);
//	    	cell.setCellValue("Updated At");
//	    }
	    
	    // write pull request data
//	    for (int i=0;i<pullList.size();i++){ //pullList.size()
//	    	System.out.println("Processing "+ i);
//	    	rowPull = writeSheetPullData.createRow(i); 
//	    	pullNumber = pullList.get(i).getNumber();
//	    	
//	    	cellPull = rowPull.createCell(0);
//	    	cellPull.setCellValue(pullNumber);
//	    	cellPull = rowPull.createCell(1);
//	    	cellPull.setCellValue(pullservice.getPullRequest(repo, pullNumber).getCreatedAt());
//	    	cellPull = rowPull.createCell(2);
//	    	cellPull.setCellValue(pullservice.getPullRequest(repo, pullNumber).getClosedAt());
//	    	cellPull = rowPull.createCell(3);
//	    	cellPull.setCellValue(pullservice.getPullRequest(repo, pullNumber).getAdditions());
//	    	cellPull = rowPull.createCell(4);
//	    	cellPull.setCellValue(pullservice.getPullRequest(repo, pullNumber).getDeletions());
//	    	cellPull = rowPull.createCell(5);
//	    	cellPull.setCellValue(pullservice.getPullRequest(repo, pullNumber).getAdditions()+pullservice.getPullRequest(repo, pullNumber).getDeletions());
//	    	cellPull = rowPull.createCell(6);
//	    	cellPull.setCellValue(pullservice.getPullRequest(repo, pullNumber).getChangedFiles());
//	    	cellPull = rowPull.createCell(7);
//	    	cellPull.setCellValue(pullservice.getPullRequest(repo, pullNumber).getCommits());
//	    	cellPull = rowPull.createCell(8);
//	    	cellPull.setCellValue(pullservice.getPullRequest(repo, pullNumber).getUpdatedAt());
//	    }
//	    
	    
	    
	    
	    
	    
	    
	 // write issue data
	    for (int i=0;i<lastIssueNumber;i++){ //lastIssueNumber
	    	
	    	row = writeSheetIssueData.createRow(i); 
	    	
	    	issueNumber = lastIssueNumber - i; //   issueList.get(i).getNumber();
	    	System.out.println("Processing "+ issueNumber);
	    	
	    	
	    	cell = row.createCell(0);
	    	cell.setCellValue(issueNumber);
	    	cell = row.createCell(1);
	    	cell.setCellValue(issueservice.getIssue(repo, issueNumber).getCreatedAt());
	    	cell = row.createCell(2);
	    	cell.setCellValue(issueservice.getIssue(repo, issueNumber).getState());
	    	
	    	    	
	    	if (issueservice.getIssue(repo, issueNumber).getState().matches("closed")){
	    		cell = row.createCell(3);
		    	cell.setCellValue(issueservice.getIssue(repo, issueNumber).getClosedAt());
	    	}
	    	else cell.setCellValue(" ");
	    	
	    	cell = row.createCell(4);
	    	cell.setCellValue(issueservice.getIssue(repo, issueNumber).getComments());
	    	cell = row.createCell(5);
	    	cell.setCellValue(issueservice.getIssue(repo, issueNumber).getLabels().toString());
	    	cell = row.createCell(6);
	    	cell.setCellValue(issueservice.getIssue(repo, issueNumber).getUpdatedAt());
	    }
//	   
	    
	    
	    //write commit data labels
//	    {
//	    	rowcommit = writeSheetCommitData.createRow(0); 
//	    	cellcommit = rowcommit.createCell(0);
//	    	cellcommit.setCellValue("Sha");
//	    	cellcommit = rowcommit.createCell(0);
//	    	cellcommit.setCellValue("commiter email");
//	    	cellcommit = rowcommit.createCell(0);
//	    	cellcommit.setCellValue("created at");
//	    	cellcommit = rowcommit.createCell(0);
//	    	cellcommit.setCellValue("fileAdditions");
//	    	cellcommit = rowcommit.createCell(0);
//	    	cellcommit.setCellValue("fileDeletions");
//	    	cellcommit = rowcommit.createCell(0);
//	    	cellcommit.setCellValue("totalchanges");
//	    	cellcommit = rowcommit.createCell(0);
//	    	cellcommit.setCellValue("Sha");
//	    	
//	    }
	    
	    
	    
//	    str[0]= "7cf981ef688b7a76e936e73b3a2a38a76de31828";
//	    str[1]= "267e77d4d24e6b0ff1f69688f910adaec9e962b5";
//	    str[2]= "3b202e88025bca6af065d1796ff27a75a30a89b7";
//	    str[3]= "5ccb2d404060e159dadf30ba320d0edc748e0896";
//	    str[4]= "5fde82d12054704ecbec1826b8b38b5f0725d136";
//	    str[5]= "407cf54118a248db6cbbf18a5a990eea4e99b6a2";
//	    str[6]= "ba611b538d563c36a5fa87874af1709c98f50e9a";
//	    str[7]= "f1a6f67f295ac7d3e1b74bcca6449be9ee05d4c8";
//	    str[8]= "27a908a709533cc1ac9844a786bc4c46c56f7ed4";
//	    str[9]= "8a034613c57e248fc1880ed1c52c50e687bdb1a4";
//	    str[10]= "21fb58053811528dfc40faa9f3b11c6c8d47fd81";
//	    str[11]= "169bca795117254c7e689432f8f6e6a14677de94";
//	    str[12]= "72a91752cf6496f4d26f51d86d6c0ed6b8b6c008";
//	    str[13]= "63421ce4172aa413833ef1b0e79de782db0f739d";
//	    str[14]= "72723fbc940fd3e010f6e508fb0b5018a1d6bdde";
//	    str[15]= "6c52db21f1de2171d8eb2f8e715f118172b6c725";
//	    str[16]= "7d432f20427fa85149c6aed6a74131a860fcc801";
//	    str[17]= "fadce884711766714486eedf59a3f506f9a70f97";
//	    str[18]= "acaea56b01efe6c068a2adb0ff61e273d032b101";
//	    str[19]= "b3bc686e0b18a52652ccfd5163ae4390d38c30b4";
//	    str[20]= "f0aaf50da0a01e80c380d85eafa034fc475db613";
//	    str[21]= "23217cb7005023cef5a88c1cdd2aa9ddda1d0c35";
//	    str[22]= "14a7a191a82f62420a3a4c62ae5c0fa19e44a0ab";
//	    str[23]= "14a7a191a82f62420a3a4c62ae5c0fa19e44a0ab";
//	    str[24]= "f42e6e2f91c1ecacbdf5280bf9e3ff442398b723";
//	    str[25]= "f3d077f44e890d81b5c7b35e834160ef1cf38f01";
//	    str[26]= "f9b197c28a16283898f109a9a9441c3b7c0e6147";
//	    str[27]= "7311cfb0a2e70eda0c698bcee5221b43b99272d1";
//	    str[28]= "e9a5b4c975daa2fc5f588fba73a3513a585c5660";
//	    str[29]= "6de9bc0660348d2784b44478ef6ff816b3f6ec87";
//	    str[30]= "9f902b320baaff32ec9a2ad075d2ca4db39afc02";
	    
	    
	    //10-04-13---29/05-12
	    
	    
	      
	    //write commit data 
//	    for (int i=0;i<commitList.size();i++){       //5;i++){commitList.size()
//	    	System.out.println("Processing commit " + i);
//	    	rowcommit = writeSheetCommitData.createRow(i); 
//	    	String sha= commitList.get(i).getSha();
//	    	cellcommit = rowcommit.createCell(0);
//	    	cellcommit.setCellValue(sha);
//	    	cellcommit = rowcommit.createCell(1);
//	    	cellcommit.setCellValue(commitList.get(i).getCommit().getAuthor().getName());  	
//	    	cellcommit = rowcommit.createCell(2);
//	    	cellcommit.setCellValue(commitList.get(i).getCommit().getAuthor().getDate());  	
//	    	
//	    	List<CommitFile> fileList =null; 
//	    	int filechanges =0;
//	    	fileList = commitservice.getCommit(repo, sha).getFiles();
//	    	
//	    	cellcommit = rowcommit.createCell(3);
//	    	cellcommit.setCellValue(fileList.size());
//	    	
//		    	for (int j=0;j<fileList.size();j++){
//		    		filechanges = filechanges +  fileList.get(j).getChanges();
//		    	}
//	    	cellcommit = rowcommit.createCell(4);
//	    	cellcommit.setCellValue(filechanges);
//	    	
////	    	for (int k=0;k<str.length;k++){
////	    		if (str[k].matches(sha)){
////	    			cellcommit = rowcommit.createCell(4);
////	    	    	cellcommit.setCellValue("Match found");
////	    		}
////	    	}
//	    	
//	    	cellcommit = rowcommit.createCell(5);
//	    	cellcommit.setCellValue(commitList.get(i).getCommit().getCommentCount());
//	    	
//	    	
//	    	
//	    }
//	    
	    try {
		    FileOutputStream out = new FileOutputStream(new File(filePath+fileName));
		    writeBookPullCommitData.write(out);
		    out.close();
		    System.out.println("Excel File Created Successfully");
		     
		} catch (FileNotFoundException e) {
		    e.printStackTrace();
		} catch (IOException e) {
		    e.printStackTrace();
		}
		
	}
	
}
	
	
	


