import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/** Screen start codons from 1st reading frames **/

public class ScreenSTin1stRF {
	public static void main(String args[]) {
		String fileHeader = "/directory name/";		
		String inFile = fileHeader + "start-codon.txt";
		String outFile = fileHeader + "start-codon-1stF.txt";
		int countRead = 0;
		int countNoDup = 0;
		int startPos = 0;
		int length = 0;
		String line = " ";
		String lineArray[];
		try {
			BufferedReader inBR = new BufferedReader(new FileReader(inFile));
			PrintWriter pw = new PrintWriter(new FileWriter(outFile));
			while ((line = inBR.readLine()) != null) {
				countRead++;
				lineArray = line.split("\t");
				startPos = Integer.parseInt(lineArray[10]);
				length = Integer.parseInt(lineArray[11]);
				if (startPos < length) {
					pw.println(line);
					countNoDup++;
				}
			}
		    inBR.close();
		    pw.close();    
		} catch (IOException e) {
			System.out.println(e);
		}
		System.out.println(countRead + "\t" + countNoDup);
	}
}
