public class MappedPairs {	
	/** Find pairs that both reads are mapped to the genome but not on proper coordinates **/
	public static void main(String args[]) {
		String NUMSEQ = " ";
		String READ1 = " ";
		String READ2 = " ";
		String[] inputs;	
		String file11 = "unmapped-read1.sam";
		String file12 = "unmapped-read2.sam";
		String file21 = "notProperMappedRead1.txt";
		String file22 = "notProperMappedRead2.txt";
		int numSeq = 100;		
		/* Read arguments from a command line */
		inputs = InputFiles.readInputs_1(args);
		NUMSEQ = inputs[0];
		READ1 = inputs[1];
		READ2 = inputs[2];
		numSeq = Integer.parseInt(NUMSEQ);
		NotProperMappedPairs.findMapped(numSeq, READ1, file11, file21);
		NotProperMappedPairs.findMapped(numSeq, READ2, file12, file22);
	}
}
