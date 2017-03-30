package dataInput;



import cc.mallet.util.*;
import cc.mallet.types.*;
import cc.mallet.fst.HMM;
import cc.mallet.pipe.*;
import cc.mallet.pipe.iterator.*;
import cc.mallet.topics.*;

import java.util.*;
import java.util.regex.*;
import java.io.*;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;



public class malletTopicAnalysis {

	
	 Pipe pipe;

	    public malletTopicAnalysis() {
	        pipe = buildPipe();
	    }

	    public Pipe buildPipe() {
	        ArrayList pipeList = new ArrayList();

	        // Read data from File objects
	        pipeList.add(new Input2CharSequence("UTF-8"));

	        // Regular expression for what constitutes a token.
	        //  This pattern includes Unicode letters, Unicode numbers, 
	        //   and the underscore character. Alternatives:
	        //    "\\S+"   (anything not whitespace)
	        //    "\\w+"    ( A-Z, a-z, 0-9, _ )
	        //    "[\\p{L}\\p{N}_]+|[\\p{P}]+"   (a group of only letters and numbers OR
	        //                                    a group of only punctuation marks)
	        Pattern tokenPattern =
	            Pattern.compile("[\\p{L}\\p{N}_]+");

	        // Tokenize raw strings
	        pipeList.add(new CharSequence2TokenSequence(tokenPattern));

	        // Normalize all tokens to all lowercase
	        pipeList.add(new TokenSequenceLowercase());

	        // Remove stopwords from a standard English stoplist.
	        //  options: [case sensitive] [mark deletions]
	        pipeList.add(new TokenSequenceRemoveStopwords(false, false));
	        pipeList.add(new TokenSequenceRemoveNonAlpha()); 
	       

	        // Rather than storing tokens as strings, convert 
	        //  them to integers by looking them up in an alphabet.
	        pipeList.add(new TokenSequence2FeatureSequence());

	        // Do the same thing for the "target" field: 
	        //  convert a class label string to a Label object,
	        //  which has an index in a Label alphabet.
	        pipeList.add(new Target2Label());
	        
	       

	        // Now convert the sequence of features to a sparse vector,
	        //  mapping feature IDs to counts.
	      //  pipeList.add(new FeatureSequence2FeatureVector());

	        // Print out the features and the label
	       // pipeList.add(new PrintInputAndTarget());

	        return new SerialPipes(pipeList);
	    }

	    public InstanceList readDirectory(File directory) {
	        return readDirectories(new File[] {directory});
	    }

	    public InstanceList readDirectories(File[] directories) {
	        
	        // Construct a file iterator, starting with the 
	        //  specified directories, and recursing through subdirectories.
	        // The second argument specifies a FileFilter to use to select
	        //  files within a directory.
	        // The third argument is a Pattern that is applied to the 
	        //   filename to produce a class label. In this case, I've 
	        //   asked it to use the last directory name in the path.
	        FileIterator iterator =
	            new FileIterator(directories,
	                             new TxtFilter(),
	                             FileIterator.LAST_DIRECTORY);

	        // Construct a new instance list, passing it the pipe
	        //  we want to use to process instances.
	        InstanceList instances = new InstanceList(pipe);

	        // Now process each instance provided by the iterator.
	        instances.addThruPipe(iterator);

	        return instances;
	    }
	    
	    
	    public String identifyFileName (String fileSource){
        	String fileName = null; 
        	fileName= fileSource.substring(fileSource.lastIndexOf("/")+1); 
        	return fileName ; 
        }
	    

	    public void topicAnalysis (String inputFilePath, String outputFilePath, String outputFileName, int numTopics, double taAlpha, int taIteration) throws IOException {

	    	FileOutputStream fileOut = new FileOutputStream(outputFilePath+outputFileName);  //"C:/Users/S.M.Didar/Dropbox/Didar DBPC/PhD Research/Winter 2015/ICSE 2016/Experiment/E V2/"+"topic analysis.xls"
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet worksheetTA = workbook.createSheet("TA");
			HSSFSheet worksheetTopics = workbook.createSheet("Topics");
			HSSFRow row;
			HSSFCell cell; 	
	    	
	    	
	    	malletTopicAnalysis importer = new malletTopicAnalysis();
	        InstanceList instances = importer.readDirectory(new File(inputFilePath));
	     //   instances.save(new File("C:/Users/S.M.Didar/Dropbox/Didar DBPC/PhD Research/Winter 2015/ICSE 2016/Experiment/E V2/sample-data2.txt"));

	        
	        ParallelTopicModel model = new ParallelTopicModel(numTopics, 0.5, 0.01);
   
	        model.addInstances(instances);
	        // Use two parallel samplers, which each look at one half the corpus and combine statistics after every iteration.
	        model.setNumThreads(2);

	        // Run the model for 50 iterations and stop (this is for testing only, for real applications, use 1000 to 2000 iterations)
	        model.setNumIterations(100);
	        model.estimate();

	        // The data alphabet maps word IDs to strings
	        Alphabet dataAlphabet = instances.getDataAlphabet();
	        
	        FeatureSequence tokens = (FeatureSequence) model.getData().get(0).instance.getData();
	        LabelSequence topics = model.getData().get(0).topicSequence;
	        
	        for (int i=0;i<instances.size();i++){
	        	row= worksheetTA.createRow(i); 
	        	cell = row.createCell(0); 
	        	
	        
	        	double[] topicDistribution = model.getTopicProbabilities(i);
	        	cell.setCellValue(instances.get(i).getName().toString()); 
	        	cell = row.createCell(1);    	
	        	cell.setCellValue(identifyFileName (row.getCell(0).getStringCellValue()));
	        	cell = row.createCell(2); 
	        	cell.setCellValue(Double.parseDouble(row.getCell(1).getStringCellValue().replace(".txt", "")));
	        			
	        	int cellCounter=3; 
	        	 for (int topic = 0; topic < numTopics; topic++) {
//	        		 cell=row.createCell(cellCounter);
//	        		 cell.setCellValue(topic);
//	        		 cellCounter++; 
	        		 cell=row.createCell(cellCounter);
	        		 cell.setCellValue(topicDistribution[topic]);
	        		 cellCounter++;
	        	  }
	         }
	        
	         System.out.println("Success: topic distribution calculated");
	        
	         ArrayList<TreeSet<IDSorter>> topicSortedWords = model.getSortedWords();
	        
	         for (int topic = 0; topic < numTopics; topic++) {
	            Iterator<IDSorter> iterator = topicSortedWords.get(topic).iterator();
	            row=worksheetTopics.createRow(topic); 
	            cell=row.createCell(0); 
	            cell.setCellValue(topic);
	         
	            int rank = 0, cellCounter=0;
	            while (iterator.hasNext() && rank < 5) {
	                IDSorter idCountPair = iterator.next();
	               
	                 cell=row.createCell(cellCounter);
	        		 cell.setCellValue(dataAlphabet.lookupObject(idCountPair.getID()).toString());
	        		 cellCounter++; 
	        		 cell=row.createCell(cellCounter);
	        		 cell.setCellValue(idCountPair.getWeight());
	        		 cellCounter++;
	                 rank++;
	            }
	            
	        }
	        
	        
	        
	        
	        workbook.write(fileOut);
			fileOut.flush();
			fileOut.close();
			System.out.println("Success: Topic analysis written");
		}
	    
	   
  
	    
	    /** This class illustrates how to build a simple file filter */
	    class TxtFilter implements FileFilter {

	        /** Test whether the string representation of the file 
	         *   ends with the correct extension. Note that {@ref FileIterator}
	         *   will only call this filter if the file is not a directory,
	         *   so we do not need to test that it is a file.
	         */
	        public boolean accept(File file) {
	            return file.toString().endsWith(".txt");
	        }
	    }
}
