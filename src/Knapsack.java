import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Knapsack {

	static ArrayList<String> files = new ArrayList<String>();
	
	
	/**
	 * 
	 * @param fName
	 * @return
	 */
	public static PrintWriter outputFile(String fName) {
			File fileName = new File(fName);
			PrintWriter output = null;
			try {
				output = new PrintWriter(fileName);
			} catch (FileNotFoundException ex) {
				System.out.print("Cannot open " + fName);
				return null;
			}
			return output;
	}
	
	
	/**
	 * 
	 * @param fname
	 * @param fnum
	 * @param out
	 * @return
	 */
	public static Scanner openWords(String fname, int fnum, PrintWriter out) {
		File file = new File(fname);
		Scanner input = null;
		try {
			input = new Scanner(file);
		} catch (FileNotFoundException ex) {
			out.println("Part " + fnum + ": Unable to Open File");
			return null;
		}
		return input;	
	}
	
	/*
	 * ----------------------------------------------------------------------------------------------------------
	 * 
	 * RECURSIVE PART YA DUMMY
	 * 
	 * 
	 * 
	 */
	public int knapsackSum(int[] w, int n, int limit) {
		int r = 0;
		
		
		return r;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	
	public static void addFiles(Scanner list) {
		String line;
		while(list.hasNextLine()){
			line = list.nextLine();
			files.add(line);
		}
	}
	
	public static int size(Scanner list) {
		int count = 0;
		while (list.hasNextLine()) {
		    count++;
		    list.nextLine();
		}
		return count;
	}
	

	
	public static String runFile(Scanner list, int f, int c) {
		String limit = list.nextLine();
		String line = list.nextLine();
		int[] melons = new int[c];
		for(int i = 0; i < c; i++) {
			melons[i] = Integer.parseInt(line);
			line = list.nextLine();
		}
		return(files.get(f) + limit + " " + melons);
		//return files.get(f) + "        " + limit + "        " + line;
	}
	
	
	
	public static void main(String[] args) {
		PrintWriter out = outputFile("knapsack.txt");
		Scanner file = openWords(args[0], 1, out);
		addFiles(file);
		
		int k;
		
		for(int i = 0; i < files.size(); i++) {
			file = openWords(files.get(i), (i + 1), out);
			k = (size(file));
			file = openWords(files.get(i), (i + 1), out);
			out.println(runFile(file, i, k));
		}
		
		
		
		file.close();
		out.close();
		
	}
	
	
}











