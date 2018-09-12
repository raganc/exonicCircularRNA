import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Vector;

/** Create summary file for the predicted circRNA junctions containing unique refSeq exon IDs **/

public class Summary {	
	public static void makeSummary(String inFile1, String inFile2, String outFile) {
		int loc = 0;
		int num = 0;
		String line = " ";
		String entryArray[];
		String lineArray[];
		Vector<String> exonIDVec = new Vector<String>();
		Vector<Integer> numExonVec = new Vector<Integer>();
		Vector<String> entryVec = new Vector<String>();
		try {
			BufferedReader inBR = new BufferedReader(new FileReader(inFile1));
			line = inBR.readLine();
			lineArray = line.split("\t");
			exonIDVec.addElement(lineArray[4] + "\t" + lineArray[5]);
			numExonVec.addElement(1);
			entryVec.addElement(line);
			while ((line = inBR.readLine()) != null) {
				lineArray = line.split("\t");
				loc = Collections.binarySearch(exonIDVec, (lineArray[4] + "\t" + lineArray[5]));
				if (loc >= 0) {
					num = numExonVec.elementAt(loc);
					numExonVec.set(loc, (num+1)); 
				}
				else {
					loc = Math.abs(loc) -1;
					exonIDVec.insertElementAt((lineArray[4] + "\t" + lineArray[5]), loc);
					entryVec.insertElementAt(line, loc);
					numExonVec.insertElementAt(1, loc);
				}
			}		
	        inBR.close();
		} catch (IOException e) {
			System.out.println(e);
		}
		try {
			BufferedReader inBR = new BufferedReader(new FileReader(inFile2));
			while ((line = inBR.readLine()) != null) {
				lineArray = line.split("\t");
				loc = Collections.binarySearch(exonIDVec, (lineArray[4] + "\t" + lineArray[5]));
				if (loc >= 0) {
					num = numExonVec.elementAt(loc);
					numExonVec.set(loc, (num+1)); 
				}
				else {
					loc = Math.abs(loc) -1;
					exonIDVec.insertElementAt((lineArray[4] + "\t" + lineArray[5]), loc);
					entryVec.insertElementAt(line, loc);
					numExonVec.insertElementAt(1, loc);
				}
			}		
	        inBR.close();
		} catch (IOException e) {
			System.out.println(e);
		}
		try {
			PrintWriter pw = new PrintWriter(new FileWriter(outFile));			
			for (int i=0; i<exonIDVec.size(); i++) {
				entryArray = (entryVec.elementAt(i)).split("\t");
				pw.println(entryArray[1] + "\t" + entryArray[2] + "\t" + entryArray[3] + "\t" + exonIDVec.elementAt(i) + "\t" + 
						   entryArray[7] + "\t" + entryArray[8] + "\t" + numExonVec.elementAt(i));
			}
	        pw.close();
		} catch (IOException e) {
			System.out.println(e);
		}
	}
}
