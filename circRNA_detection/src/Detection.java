public class Detection {	
	public static void main(String args[]) {
		String ANNOTATION = " ";
		String UNMAPPED1 = " ";
		String UNMAPPED2 = " ";
		String MAPPED1 = " ";
		String MAPPED2 = " ";
		String[] inputs;
		String file11 = "pairedJunctions-read1.bed";
		String file12 = "pairedJunctions-read2.bed";
		String file21 = "overlap-exon-read1.bed";
		String file22 = "overlap-exon-read2.bed";
		String file31 = "overlap-exon-read1-sorted.bed";
		String file32 = "overlap-exon-read2-sorted.bed";
		String file41 = "backsplice-exon-read1.txt";
		String file42 = "backsplice-exon-read2.txt";
		String file51 = "junctionReads-exon-read1.txt";
		String file52 = "junctionReads-exon-read2.txt";
		String file60 = "exon-backspliced-circRNAs.txt";		
		
		inputs = InputFiles.readInputs_2(args);
		ANNOTATION = inputs[0];
		UNMAPPED1 = inputs[1];
		UNMAPPED2 = inputs[2];
		MAPPED1 = inputs[3];
		MAPPED2 = inputs[4];
		CombineJunctionPairs.combine(UNMAPPED1, file11);
		CombineJunctionPairs.combine(UNMAPPED2, file12);
		BedTools.overlapReads(file11, ANNOTATION, file21);
		BedTools.overlapReads(file12, ANNOTATION, file22);
		SortFiles.sort4105(file21, file31);
		SortFiles.sort4105(file22, file32);
		FindJunctionPairs.findJunction(file31, file41);
		FindJunctionPairs.findJunction(file32, file42);
		CombineReadPairs.findPairs(MAPPED2, file41, file51);
		CombineReadPairs.findPairs(MAPPED1, file42, file52);
		Summary.makeSummary(file51, file52, file60);		
		
		/** Remove files **/
		Files.deleteFile("pairedJunctions-read1.bed");
		Files.deleteFile("pairedJunctions-read2.bed");		
		Files.deleteFile("overlap-exon-read1.bed");		
		Files.deleteFile("overlap-exon-read2.bed");
		Files.deleteFile("overlap-exon-read1-sorted.bed");
		Files.deleteFile("overlap-exon-read2-sorted.bed");
		Files.deleteFile("backsplice-exon-read1.txt");
		Files.deleteFile("backsplice-exon-read2.txt");
		Files.deleteFile("junctionReads-exon-read1.txt");
		Files.deleteFile("junctionReads-exon-read2.txt");
	}
}