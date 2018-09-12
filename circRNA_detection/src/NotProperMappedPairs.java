import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
/** Select read pairs that both mapped to the reference but not on proper coordinates **/
public class NotProperMappedPairs {	
	public static void findMapped(int numSeq, String inFile, String outFile1, String outFile2) {
		int seqEnd = 0;
		String line = " ";
		String lineArray[];
		try {
			BufferedReader inBR = new BufferedReader(new FileReader(inFile));			
			PrintWriter pw1 = new PrintWriter(new FileWriter(outFile1));
			PrintWriter pw2 = new PrintWriter(new FileWriter(outFile2));
			while ((line = inBR.readLine()) != null) {
				lineArray = line.split("\t");
				if (lineArray[6].equals("=")) {
					if (line.contains("XF:")) {
						pw1.println(line);
					}
					else {
						seqEnd = Integer.parseInt(lineArray[3]) + numSeq;
						pw2.println(lineArray[0] + "\t" + lineArray[2] + "\t" + lineArray[3] + "\t" + seqEnd);
					}
				}
			}
	        inBR.close();
	        pw1.close();
	        pw2.close();
		} catch (IOException e) {
			System.out.println(e);
		}
	}
}
