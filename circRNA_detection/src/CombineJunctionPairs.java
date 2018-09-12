import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
/** Combine junction pairs that have same IDs **/
/** output files are .bed format **/

public class CombineJunctionPairs {	
	public static void combine(String inFile, String outFile) {
		int readStart = 0;
		int readEnd = 0;
		String line = " ";
		String chr = " ";
		String readID = " ";
		String lineArray[];
		String segmentArray[];
		try {
			BufferedReader inBR = new BufferedReader(new FileReader(inFile));			
			PrintWriter pw = new PrintWriter(new FileWriter(outFile));
			line = inBR.readLine();
			lineArray = line.split("\t");
			readID = lineArray[0];
			chr = lineArray[2];
			readStart = Integer.parseInt(lineArray[3]);
			segmentArray = lineArray[5].split("M");
			readEnd = readStart + Integer.parseInt(segmentArray[0]);
			while ((line = inBR.readLine()) != null) {
				lineArray = line.split("\t");
				if (readID.equals(lineArray[0])) {
					pw.println(chr + "\t" + readStart + "\t" + readEnd + "\t" + lineArray[0] + "\t1\t+");
					readStart = Integer.parseInt(lineArray[3]);
					segmentArray = lineArray[5].split("M");
					readEnd = readStart + Integer.parseInt(segmentArray[0]);						
					pw.println(lineArray[2] + "\t" + readStart + "\t" + readEnd + "\t" + lineArray[0] + "\t2\t+");
					if ((line = inBR.readLine()) != null) {				
						lineArray = line.split("\t");
						readID = lineArray[0];
						chr = lineArray[2];
						readStart = Integer.parseInt(lineArray[3]);
						segmentArray = lineArray[5].split("M");
						readEnd = readStart + Integer.parseInt(segmentArray[0]);
					}
				}
				else {
					lineArray = line.split("\t");
					readID = lineArray[0];
					chr = lineArray[2];
					readStart = Integer.parseInt(lineArray[3]);
					segmentArray = lineArray[5].split("M");
					readEnd = readStart + Integer.parseInt(segmentArray[0]);
				}
			}
	        inBR.close();
	        pw.close();
		} catch (IOException e) {
			System.out.println(e);
		}
	}
}
