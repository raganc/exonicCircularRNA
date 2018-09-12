import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

/** For each start codon, find the end codons and compute the length of reading frames **/

public class ComputeDistance {
		public static void main(String args[]) {
		String fileHeader = "/directory name/";		
		String inFile1 = fileHeader + "circRNA-3circle.fa";
		String inFile2 = fileHeader + "start-codon-1stF.txt";
		String outFile1 = fileHeader + "codon-ORF-dis.txt";
		String outFile2 = fileHeader + "codon-IORF.txt";
		String outFile3 = fileHeader + "codon-noORF.txt";
		String codon1 = "TAG";
		String codon2 = "TAA";
		String codon3 = "TGA";
		boolean findFlg = false;
		boolean findCodon = false;
		int numRead = 0;
		int numORF = 0;
		int numIORF = 0;
		int numNoORF = 0;
		int count = 0;
		int dis = 0;
		int pos = 0;
		int codonPos = 0;
		int startPos = 0;
		char seqArray[];
		char readArray[] = new char[3];
		String seqExon = " ";
		String seq = " ";
		String key = " ";
		String codon = " ";
		String line = " ";
		String lineArray[];
		Vector<String> keyVec = new Vector<String>();
		Vector<String> seqVec = new Vector<String>();
		try {
			BufferedReader inBR = new BufferedReader(new FileReader(inFile1));
			while ((line = inBR.readLine()) != null) {
				lineArray = line.split("\t");
				keyVec.addElement(lineArray[1] + ":" + lineArray[2]);
				line = inBR.readLine();
				seqVec.addElement(line);
			}
	        inBR.close();
		} catch (IOException e) {
			System.out.println(e);
		}			
		try {
			BufferedReader inBR = new BufferedReader(new FileReader(inFile2));
			PrintWriter pw1 = new PrintWriter(new FileWriter(outFile1));
			PrintWriter pw2 = new PrintWriter(new FileWriter(outFile2));
			PrintWriter pw3 = new PrintWriter(new FileWriter(outFile3));
			while ((line = inBR.readLine()) != null) {
				lineArray = line.split("\t");
				key = lineArray[1] + ":" + lineArray[2];
				codonPos = Integer.parseInt(lineArray[10]);
				findFlg = false;
				count = 0;
				while (!findFlg && count<keyVec.size()) {
					if (key.equals(keyVec.elementAt(count))) {
						numRead++;
						findFlg = true;
						startPos = codonPos + 6;
						seqExon = seqVec.elementAt(count);
						seqArray = seqExon.toCharArray();
						findCodon = false;
						dis = startPos;
						while ((!findCodon) && (dis<seqArray.length)) {
							if (pos<2) {
								readArray[pos] = seqArray[dis];
								pos++;
							}
							else {
								readArray[pos] = seqArray[dis];
								seq = String.valueOf(readArray);
								pos = 0;
								if (seq.equals(codon1)) {
									findCodon = true;
									codon = codon1;
								}
								else if (seq.equals(codon2)) {
									findCodon = true;
									codon = codon2;
								}
								else if (seq.equals(codon3)) {
									findCodon = true;
									codon = codon3;
								}
							}
							dis++;
						}
						if (findCodon) {
							if ((dis-startPos-3) == 0) {
								pw3.println(line);
								numNoORF++;
							}
							else {
								if (((dis-startPos-3) % 3) == 0) {
									seq = String.valueOf(seqArray[codonPos]);
									if ((dis+1) >= seqArray.length) {
										dis = seqArray.length - 1;
									}
									for (int j=(codonPos+1); j<dis; j++) {
										seq = seq + seqArray[j];
									}
									pw1.println(line + "\t" + codon + "\t" + dis + "\t" + (dis-startPos-3) + "\t" + seq);
									numORF++;
								}
								else {
									pw2.println(line);
									numIORF++;
								}
							}
						}
						else {
							pw2.println(line);
							numIORF++;
						}
					}
					else {
						count++;
					}
				}
				if (!findFlg) {
					System.out.println(line);
				}
			}
			System.out.println(numRead + "\t" + numIORF + "\t" + numORF + "\t" + numNoORF);
	        inBR.close();
	        pw1.close();
	        pw2.close();
	        pw3.close();
		} catch (IOException e) {
			System.out.println(e);
		}
	}
}
