package dataInput;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class topicAnalysis {

	
	public static void copyTrainingFiles (int trainingWeeks, String filePath){
		for (int i=1;i<=trainingWeeks;i++){
			try {
				File source = new File(filePath+"Topic backup files/"+i+".txt");
				File dest = new File(filePath+"Topic files/"+i+".txt");
				
				copyFile(source, dest);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
	}
	
	
	
	public static void topicAnalysis (int trainingWeeks, int uptoWeek, String filePath,  int numTopics, double taAlpha, int taIteration){
		for (int i=trainingWeeks;i<=uptoWeek;i++){
			
			File source = new File(filePath+"Topic backup files/"+i+".txt");
			File dest = new File(filePath+"Topic files/"+i+".txt");
			
			try {
				copyFile(source, dest);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			
			try {
				malletTopicAnalysis mTA = new malletTopicAnalysis(); 
				mTA.topicAnalysis (filePath+"Topic files", filePath+"Results/", i+".xls",  numTopics, taAlpha, taIteration);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
	}
	
	
	private static void copyFile(File sourceFile, File destFile)
			throws IOException {
		if (!sourceFile.exists()) {
			return;
		}
		if (!destFile.exists()) {
			destFile.createNewFile();
		}
		FileChannel source = null;
		FileChannel destination = null;
		source = new FileInputStream(sourceFile).getChannel();
		destination = new FileOutputStream(destFile).getChannel();
		if (destination != null && source != null) {
			destination.transferFrom(source, 0, source.size());
		}
		if (source != null) {
			source.close();
		}
		if (destination != null) {
			destination.close();
		}
		System.out.println("Copying file " + sourceFile.getName());

	}
	
	
	
}
