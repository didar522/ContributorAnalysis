package pkgRRcalc;

import java.util.Date;

    public class IssueListStr {
	private double issueNumber; 
	private Date issueCreatedAt;  
	private boolean isClosed; 
	private Date issueClosedAt; 
	private double numofComments; 
	private String issueLabels; 
	private Date issueUpdatedAt;
	public double getIssueNumber() {
		return issueNumber;
	}
	public void setIssueNumber(double issueNumber) {
		this.issueNumber = issueNumber;
	}
	public Date getIssueCreatedAt() {
		return issueCreatedAt;
	}
	public void setIssueCreatedAt(Date issueCreatedAt) {
		this.issueCreatedAt = issueCreatedAt;
	}
	public boolean isClosed() {
		return isClosed;
	}
	public void setClosed(boolean isClosed) {
		this.isClosed = isClosed;
	}
	public Date getIssueClosedAt() {
		return issueClosedAt;
	}
	public void setIssueClosedAt(Date issueClosedAt) {
		this.issueClosedAt = issueClosedAt;
	}
	public double getNumofComments() {
		return numofComments;
	}
	public void setNumofComments(double numofComments) {
		this.numofComments = numofComments;
	}
	public String getIssueLabels() {
		return issueLabels;
	}
	public void setIssueLabels(String issueLabels) {
		this.issueLabels = issueLabels;
	}
	public Date getIssueUpdatedAt() {
		return issueUpdatedAt;
	}
	public void setIssueUpdatedAt(Date issueUpdatedAt) {
		this.issueUpdatedAt = issueUpdatedAt;
	} 
}
