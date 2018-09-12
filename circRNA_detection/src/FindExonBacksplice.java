/** Find circRNA junctions overlap with RefSeq exon junctions **/
public class FindExonBacksplice {
	public static String findBacksplice(String line1, String line2) {
		int posC = 0;
		int posE = 0;
		String entry = "-";
		String lineArray1[];
		String lineArray2[];
		lineArray1 = line1.split("\t");
		posC = Integer.parseInt(lineArray1[1]);
		posE = Integer.parseInt(lineArray1[7]);
		if ((posC == posE) || ((posC+1) == posE) || ((posC-1) == posE)) {
			lineArray2 = line2.split("\t");
			posC = Integer.parseInt(lineArray2[2]);
			posE = Integer.parseInt(lineArray2[8]);
			if ((posC == posE) || ((posC+1) == posE) || ((posC-1) == posE)) {
				entry = lineArray1[3] + "\t" + lineArray1[6] + "\t" + lineArray1[7] + "\t" + lineArray2[8] + "\t" + lineArray1[9]
						 + "\t" + lineArray2[9] + "\t" + lineArray1[10] + "\t" + lineArray1[12] + "\t" + lineArray1[11];
			}
		}
		return entry;
	}
}
