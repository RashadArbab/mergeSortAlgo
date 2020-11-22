import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner ; 

public class Main {
	public static void main(String args []) throws FileNotFoundException {
		File fileA = new File("fileA.txt") ;
		File fileB = new File("fileB.txt") ;
		File outputFile = new File("outputFile.txt") ;
		File outputFile2 = new File ("outputFile2.txt") ;
		File fileC = new File("fileC.txt"); 
		
		mergeFiles(fileA, fileB, outputFile); 
		System.out.println("Merge 1 completed");
		mergeFiles(outputFile, fileC, outputFile2); 
	}
	public static void mergeFiles(File fileA, File fileB , File outputFile) throws FileNotFoundException {
		PrintStream output = new PrintStream(outputFile); 
		Scanner scanA = new Scanner(fileA); 
	    Scanner scanB = new Scanner(fileB); 
	    
	    System.out.println("CheckPointA") ;
	        
	    while(scanA.hasNext() || scanB.hasNext()){
	    	if(scanA.hasNext() && scanB.hasNext()) {
		    	if(peekAtNextInt(scanA) >= peekAtNextInt(scanB)) {
		    		output.println(peekAtNextInt(scanB)); 
		    		scanB.next(); 
		    	}
		    	else {
		    		output.println(peekAtNextInt(scanA));
		    		scanA.next(); 
		    	}
	    	}
	    	else if (scanA.hasNext()) {
	    		output.println(peekAtNextInt(scanA)); 
	    		scanA.next(); 
	    	}
	    	else if (scanB.hasNext()) {
	    		output.println(peekAtNextInt(scanB));
	    		scanB.next() ; 
	    	}
	            
	    }
	     
	     scanA.close();
	     scanB.close(); 
	     output.close();
	}
	public static Integer peekAtNextInt(Scanner scanner) {
        try {
            scanner.hasNext(".*");
            return Integer.parseInt(scanner.match().group(0));
        } catch (NumberFormatException e) {
            throw new InputMismatchException(
                "Next token is not an integer");
        } catch (IllegalStateException e) {
            throw new NoSuchElementException(
                "No more tokens available or Scanner already closed");
        }
    }
}
