import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

/** Find Start codons with different consensus motifs **/

public class FindStartCodons {
		public static void main(String args[]) {
		String fileHeader = "/directory name/";
		String motifFile = fileHeader + "motif-all.txt";  /* motif file */
		String inFile = fileHeader + "circRNA-3circle.fa";	/* circRNA sequence file */
		String outFile = fileHeader + "start-codon.txt";
		int pos = 0;
		int startPos = 0;
		int codonPos = 0;
		int length = 0;
		char seqArray[];
		char readArray[];
		String seq = " ";
		String line = " ";
		String lineSeq = " ";
		String lineArray[];
		Vector <String>codonNameVec = new Vector<String>();
		Vector <String>motifVec = new Vector<String>();
		try {
			BufferedReader inBR = new BufferedReader(new FileReader(motifFile));
			while ((line = inBR.readLine()) != null) {
				lineArray = line.split("\t");
				codonNameVec.addElement(lineArray[0]);
				motifVec.addElement(lineArray[1]);
			}
	        inBR.close();
		} catch (IOException e) {
			System.out.println(e);
		}
		try {
			BufferedReader inBR = new BufferedReader(new FileReader(inFile));
			PrintWriter pw = new PrintWriter(new FileWriter(outFile));
			while ((line = inBR.readLine()) != null) {
				lineSeq = inBR.readLine();
				seqArray = lineSeq.toCharArray();
				length = seqArray.length / 3;
				for (int i=0; i<motifVec.size(); i++) {
					startPos = (lineSeq.toUpperCase()).indexOf(motifVec.elementAt(i));
					if (startPos > -1) {
						pw.println(line + "\t" + codonNameVec.elementAt(i) + "\t" + motifVec.elementAt(i) + "\t" + startPos + "\t" + length);
						while ((startPos>=0) && (startPos < (seqArray.length-7))) {
							readArray = new char[seqArray.length-(startPos+7)];
							pos = 0;
							for (int j=(startPos+7); j<seqArray.length; j++) {
								readArray[pos] = seqArray[j];
								pos++;
							}
							seq = String.valueOf(readArray);
							codonPos = seq.toUpperCase().indexOf(motifVec.elementAt(i));
							if (codonPos < 0) {
								//pw.println(line + "\t" + codonPos);
								startPos = -1;
							}
							else if (codonPos == 0) {
								startPos = startPos + 7;
								pw.println(line + "\t" + codonNameVec.elementAt(i) + "\t" + motifVec.elementAt(i) + "\t" + startPos + "\t" + length);
							}
							else {
								startPos = startPos + 7 + codonPos;
								pw.println(line + "\t" + codonNameVec.elementAt(i) + "\t" + motifVec.elementAt(i) + "\t" + startPos + "\t" + length);
							}
						}
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
