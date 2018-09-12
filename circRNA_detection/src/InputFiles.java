/** Read and input variables from a command line **/
public class InputFiles {
	public static String[] readInputs_1(String argv[]) {	
		String NUMSEQ = "-";
		String READ1 = "-";
		String READ2 = "-";
		String[] input = new String[3];
		if ((argv.length < 3) || (argv.length > 4)) {
			help1();
		}
		else {
			for (int i=0; i<argv.length; i++) {
				if (argv[i].contains("NUMSEQ")) {
					NUMSEQ = argv[i].replace("NUMSEQ=", "");
				}
				else if (argv[i].contains("READ1")) {
					READ1 = argv[i].replace("READ1=", "");
				}
				else if (argv[i].contains("READ2")) {
					READ2 = argv[i].replace("READ2=", "");
				}
			}
		}
		if (NUMSEQ.equals("-")) {
			help1();
		}
		if (READ1.equals("-")) {
			help1();
		}
		if (READ2.equals("-")) {
			help1();
		}		
		input[0] = NUMSEQ;
		input[1] = READ1;
		input[2] = READ2;
		return input;
	}
	public static String[] readInputs_2(String argv[]) {	
		String ANNOTATION = "-";
		String UNMAPPED1 = "-";
		String UNMAPPED2 = "-";
		String MAPPED1 = "-";
		String MAPPED2 = "-";
		String[] input = new String[5];
		if ((argv.length < 5) || (argv.length > 5)) {
			help2();
		}
		else {
			for (int i=0; i<argv.length; i++) {
				if (argv[i].contains("ANNOTATION")) {
					ANNOTATION = argv[i].replace("ANNOTATION=", "");
				}
				else if (argv[i].contains("UNMAPPED1")) {
					UNMAPPED1 = argv[i].replace("UNMAPPED1=", "");
				}
				else if (argv[i].contains("UNMAPPED2")) {
					UNMAPPED2 = argv[i].replace("UNMAPPED2=", "");
				}
				else if (argv[i].contains("MAPPED1")) {
					MAPPED1 = argv[i].replace("MAPPED1=", "");
				}
				else if (argv[i].contains("MAPPED2")) {
					MAPPED2 = argv[i].replace("MAPPED2=", "");
				}
			}
		}
		if (ANNOTATION.equals("-")) {
			help2();
		}		
		if (UNMAPPED1.equals("-")) {
			help2();
		}
		if (UNMAPPED1.equals("-")) {
			help2();
		}		
		if (MAPPED1.equals("-")) {
			help2();
		}
		if (MAPPED1.equals("-")) {
			help2();
		}		
		input[0] = ANNOTATION;
		input[1] = UNMAPPED1;
		input[2] = UNMAPPED2;
		input[3] = MAPPED1;
		input[4] = MAPPED2;		
		return input;
	}
	public static void help1() {
		System.out.println ();
		System.out.println ("Please provide read length and the name of input files - read 1 and read 2");
		System.out.println ("Required: NUMSEQ=xx - sequence read lengrh");
		System.out.println ("Required: READ1=xx - file name for read 1");
		System.out.println ("Required: READ2=xx - file name for read 2");
		System.out.println ();
		System.exit(0);
	}
	public static void help2() {
		System.out.println ();
		System.out.println ("Please provide the exon annotation file and 4 input files");
		System.out.println ("Required: ANNOTATION=xx - .bed format file contacting location of exon junctions");
		System.out.println ("Required: UNMAPPED1=xx - unmapped-read1-sorted.sam");
		System.out.println ("Required: UNMAPPED2=xx - unmapped-read2-sorted.sam");
		System.out.println ("Required: MAPPED1=xx - mapped-read1-sorted.sam");
		System.out.println ("Required: MAPPED2=xx - mapped-read2-sorted.sam");
		System.out.println ();
		System.exit(0);
	}
}
