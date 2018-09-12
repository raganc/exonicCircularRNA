import java.io.File;
public class Files {
	public static void createDir(String name) {	
		boolean findFile = false;
		try {
			File dir = new File(name);
			if(!(dir.exists())) {
				findFile = dir.mkdir();
				if (!findFile) {
					System.out.println("Error creating a diretory: " + dir);
					System.exit(0);
				}
			}  
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
			System.exit(0);
		}
	}	
	public static void deleteDirectory(String name) {
		boolean findFile = false;
		File dir = new File(name);
		try {			
			findFile = deleteDirs(dir);
			if (!findFile) {
				System.out.println("Error deleting a diretory: " + dir);
			}
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
			System.exit(0);
		}
	}
	/* delete child directories and files if any */
	public static boolean deleteDirs(File dir) {
		if (dir.isDirectory()) {
			String[] children = dir.list();
			for (int i=0; i<children.length; i++) {
				boolean success = deleteDirs(new File(dir, children[i]));
				if (!success) {
					return false;
				}
			}
		}
		return (dir.delete());
	}
	public static void  deleteFile(String name) {
		boolean findFile = false;
		File dir = new File(name);
		try {			
			findFile = dir.delete();
			if (!findFile) {
				System.out.println("Error deleting a diretory: " + dir);
			}
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
			System.exit(0);
		}
	}
}
