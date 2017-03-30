package pkgRRcalc;

import java.util.ArrayList;
import java.util.Date;

public class WeekCalcStr {

	private int weekNum; 
	public int inOinC,erOinC,inOltC, erOltC,inO,erO; 
	public double weeklyVal, uptoWeekVal;
	public double uptofilenumber, uptodiff, uptocoments, uptocommits, uptocontributors; 
	public double avgDiffperFile, avgDiffperCommit, avgDiffperContributor, avgFileperCommit, avgFilepercontributor; 
	public double diffSDEVfile, diffSDEVcommit, diffSDEVcontributor, fileSDEVcontributor, fileSDEVcommit; 
	public double totalVal, normalTotalVal; 
	
	public ArrayList <String> uptoContributorsList = new ArrayList <String> (); 
	
	
	private Date weekStart, weekEnd; 
	
	public int getWeekNum() {
		return weekNum;
	}
	public void setWeekNum(int weekNum) {
		this.weekNum = weekNum;
	}
	public int getInOinC() {
		return inOinC;
	}
	public void setInOinC(int inOinC) {
		this.inOinC = inOinC;
	}
	public int getErOinC() {
		return erOinC;
	}
	public void setErOinC(int erOinC) {
		this.erOinC = erOinC;
	}
	public int getInOltC() {
		return inOltC;
	}
	public void setInOltC(int inOltC) {
		this.inOltC = inOltC;
	}
	public int getErOltC() {
		return erOltC;
	}
	public void setErOltC(int erOltC) {
		this.erOltC = erOltC;
	}
	public int getInO() {
		return inO;
	}
	public void setInO(int inO) {
		this.inO = inO;
	}
	public int getErO() {
		return erO;
	}
	public void setErO(int erO) {
		this.erO = erO;
	}
	public double getTotalVal() {
		return totalVal;
	}
	public void setTotalVal(double totalVal) {
		this.totalVal = totalVal;
	}
	public Date getWeekStart() {
		return weekStart;
	}
	public void setWeekStart(Date weekStart) {
		this.weekStart = weekStart;
	}
	public Date getWeekEnd() {
		return weekEnd;
	}
	public void setWeekEnd(Date weekEnd) {
		this.weekEnd = weekEnd;
	}
}
