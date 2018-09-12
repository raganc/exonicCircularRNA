import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Vector;
/** Find pairs that one read contains junction and another is located inside the backspliced junction **/
public class CombineReadPairs {
	public static void findPairs(String inFile1, String inFile2, String outFile) {
		int loc = 0;
		int tS = 0;
		int tE = 0;
		String line = " ";
		String lineArray[];
		Vector<String> IDVec = new Vector<String>();
		Vector<Integer> tsVec = new Vector<Integer>();
		Vector<Integer> teVec = new Vector<Integer>();
		try {
			BufferedReader inBR = new BufferedReader(new FileReader(inFile1));
			while ((line = inBR.readLine()) != null) {
				lineArray = line.split("\t");
				IDVec.addElement(lineArray[0]);
				tsVec.addElement(Integer.parseInt(lineArray[2]));
				teVec.addElement(Integer.parseInt(lineArray[3]));
			}
	        inBR.close();
		} catch (IOException e) {
			System.out.println(e);
		}
		try {
			BufferedReader inBR = new BufferedReader(new FileReader(inFile2));
			PrintWriter pw = new PrintWriter(new FileWriter(outFile));
			while ((line = inBR.readLine()) != null) {
				lineArray = line.split("\t");
				loc = Collections.binarySearch(IDVec, lineArray[0]);
				if (loc >= 0) {
					tS = Integer.parseInt(lineArray[2]);
					tE = Integer.parseInt(lineArray[3]);
					if ((tS<tsVec.elementAt(loc)) && (teVec.elementAt(loc)<tE)) {
						pw.println(line);
					}
				}
			}
	        inBR.close();
	        pw.close();
		} catch (IOException e) {
			System.out.println(e);
		}
	}
}
