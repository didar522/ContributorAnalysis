package dataInput;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.FileChannel;

import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.InvalidFormatException;

import org.tartarus.snowball.ext.EnglishStemmer;
import org.tartarus.snowball.ext.PorterStemmer;




public class topicPreProcessing {

	
	public static File source, dest; 
	public static String eachFile, eachFileFilterPunc; 
	
	public static void main (String args[]){
		String filePath = "C:/Users/S.M.Didar/Documents/correlate/"; 
		preProcessing (1,3, filePath); 
	}
	
	public static void preProcessing (int trainingWeeks, int uptoWeek, String filePath){	
		for (int i=trainingWeeks;i<=uptoWeek;i++){
			
			source = new File(filePath+"Topic preprocess files/"+i+".txt");
			dest = new File(filePath+"Topic backup files/"+i+".txt");
			
			try {
				BufferedReader br = new BufferedReader(new FileReader(source));
				StringBuilder sb = new StringBuilder();
				String line = br.readLine();

				while (line != null) {
				    sb.append(line);
				    sb.append(System.lineSeparator());
				    line = br.readLine();
				}
				eachFile = sb.toString();
			
				eachFileFilterPunc= eachFile.replaceAll("[^a-zA-Z ]", ""); 
			
				BufferedWriter out = new BufferedWriter(new FileWriter(dest));
				
				InputStream is = new FileInputStream("bin/models/en-token.bin");
				 
				TokenizerModel model = new TokenizerModel(is);
	 
				Tokenizer tokenizer = new TokenizerME(model);
	 
				String tokens[] = tokenizer.tokenize(eachFileFilterPunc);
	 
				for (String a : tokens){
					EnglishStemmer stemmer = new EnglishStemmer();
					//PorterStemmer stemmer = new PorterStemmer();
					stemmer.setCurrent(a); //set string you need to stem
					stemmer.stem();  //stem the word
					
					out.write(stemmer.getCurrent());
		            out.newLine();
					
					
					//System.out.println(stemmer.getCurrent());//get the stemmed word
				}
					//System.out.println(a);
	 
				is.close();
				out.close(); 
				
			
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
		}
		
	    System.out.println("Preprocessing completed successfully");
	
	}
}
