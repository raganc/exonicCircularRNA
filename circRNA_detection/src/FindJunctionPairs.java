import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
/** Match reads with same ID and mapped to the same exon **/
public class FindJunctionPairs {	
	public static void findJunction(String inFile, String outFile) {
		int pos1 = 0;
		int pos2 = 0;
		String entry = "-";
		String  ID1 = " ";
		String  ID2 = " ";
		String line1 = " ";
		String line2 = " ";
		String mRNA1 = " ";
		String mRNA2 = " ";
		String lineArray[];
		String mRNAArray[];
		try {
			BufferedReader inBR = new BufferedReader(new FileReader(inFile));			
			PrintWriter pw = new PrintWriter(new FileWriter(outFile));
			line1 = inBR.readLine();
			lineArray = line1.split("\t");
			ID1 = lineArray[3];
			pos1 = Integer.parseInt(lineArray[4]);
			mRNAArray = lineArray[9].split("_");
			mRNA1 = mRNAArray[1];
			while ((line2 = inBR.readLine()) != null) {
				lineArray = line2.split("\t");
				ID2 = lineArray[3];
				pos2 = Integer.parseInt(lineArray[4]);
				mRNAArray = lineArray[9].split("_");
				mRNA2 = mRNAArray[1];
				if (ID1.equals(ID2)) {
					if (mRNA1.equals(mRNA2)) {
						if (((pos1 == 1) && (pos2 == 2)) || ((pos2 == 1) && (pos1 == 2))) {
							if (pos1 == 1) {
								entry = FindExonBacksplice.findBacksplice(line1, line2);
								if (entry.equals("-")) {
									/* do nothing */
								}
								else {
									pw.println(entry);
								}
							}
							else {
								entry = FindExonBacksplice.findBacksplice(line2, line1);
								if (entry.equals("-")) {
									/* do nothing */
								}
								else {
									pw.println(entry);
								}
							}
							if ((line1 = inBR.readLine()) != null) {
								lineArray = line1.split("\t");
								ID1 = lineArray[3];
								pos1 = Integer.parseInt(lineArray[4]);
								mRNAArray = lineArray[9].split("_");
								mRNA1 = mRNAArray[1];
							}
						}
						else {
							line1 = line2;
							ID1 = ID2;
							mRNA1 = mRNA2;
							pos1 = pos2;
						}
					}
					else {
						line1 = line2;
						ID1 = ID2;
						mRNA1 = mRNA2;
						pos1 = pos2;	
					}
				}
				else {
					line1 = line2;
					ID1 = ID2;
					mRNA1 = mRNA2;
					pos1 = pos2;
				}
			}
	        inBR.close();
	        pw.close();
		} catch (IOException e) {
			System.out.println(e);
		}
	}
}
