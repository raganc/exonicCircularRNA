import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;
/** Sort files in current directory **/
public class SortFiles {
	public static void sort4105(String inFile, String outFile) {	
		int exitNum = 0;
		String command = "sort -k4,4 -k10,10 -k5,5n " + inFile;
		String inLine;
		FileInputStream fis;
		try {
			PrintWriter pw = new PrintWriter(new FileWriter(outFile));
			fis = new FileInputStream(new File(inFile));  
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
		} catch (InterruptedException ie) {
						System.out.println("InrerruptedExeception raised:  " + ie.getMessage());
		} catch (IOException ioe) {
			System.out.println(ioe);	
		}
	}
}
