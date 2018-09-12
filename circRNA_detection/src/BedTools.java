import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
/** Invoke BEDTools' programs **/
public class BedTools {
	public static void overlapReads1(String BEDTOOLS, String inFile1, String inFile2, String outFile) {
		int exitNum = 0;
		String command = BEDTOOLS + "intersectBed  -wa -wb -a " + inFile1 + " -b " + inFile2;
		String inLine;
		FileInputStream fis;
		try {
			PrintWriter pw = new PrintWriter(new FileWriter(outFile));
			fis = new FileInputStream(new File(inFile1));  
			exitNum = fis.read();
			fis.close();
			if (exitNum != -1)  {
				fis = new FileInputStream(new File(inFile2));  
				exitNum = fis.read();
				fis.close();
				if (exitNum != -1)  {
					Process proc = Runtime.getRuntime().exec(command);
					BufferedReader input = new BufferedReader(new InputStreamReader(proc.getInputStream()));
					while ((inLine = input.readLine()) != null) {
						pw.println(inLine);
					}
					pw.close();
					exitNum = proc.waitFor();
					if (exitNum != 0) {
						BufferedReader errMsg = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
						String word = errMsg.readLine();
						System.out.println("Error: intersectBed - " + word);
						System.exit(1);
					}
				}
			}
		} catch (InterruptedException ie) {
						System.out.println("InrerruptedExeception raised:  " + ie.getMessage());
		} catch (IOException ioe) {
			System.out.println(ioe);	
		}
	}	public static void overlapReads(String inFile1, String inFile2, String outFile) {
		int exitNum = 0;
		String command = "intersectBed  -wa -wb -a " + inFile1 + " -b " + inFile2;
		String inLine;
		FileInputStream fis;
		try {
			PrintWriter pw = new PrintWriter(new FileWriter(outFile));
			fis = new FileInputStream(new File(inFile1));  
			exitNum = fis.read();
			fis.close();
			if (exitNum != -1)  {
				fis = new FileInputStream(new File(inFile2));  
				exitNum = fis.read();
				fis.close();
				if (exitNum != -1)  {
					Process proc = Runtime.getRuntime().exec(command);
					BufferedReader input = new BufferedReader(new InputStreamReader(proc.getInputStream()));
					while ((inLine = input.readLine()) != null) {
						pw.println(inLine);
					}
					pw.close();
					exitNum = proc.waitFor();
					if (exitNum != 0) {
						BufferedReader errMsg = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
						String word = errMsg.readLine();
						System.out.println("Error: intersectBed - " + word);
						System.exit(1);
					}
				}
			}
		} catch (InterruptedException ie) {
						System.out.println("InrerruptedExeception raised:  " + ie.getMessage());
		} catch (IOException ioe) {
			System.out.println(ioe);	
		}
	}
}
