import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
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
				System.out.print("Cannot open " + fName + " , it may not exist");
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
	

	public static int knapsackSum(int[] w, int n, int limit) {
		if(limit < 1 || n < 1) {
			return 0;
		}
		
		
		if(w[n-1] > limit) {
			return knapsackSum(w, n - 1, limit);
		}else {
			
		int a = knapsackSum(w, n - 1, limit - w[n-1])  +  w[n-1];
		int b = knapsackSum(w, n - 1, limit)  +  w[n-1];
		
		if(a >= b) {
			if(a <=limit) {
				return a;
			} 
			
		}else {
			if(b <=limit) {
				return b;
			}
		}
		}
		return 0;
	}
	
	
	
	
	public static int knapsackSum(int[] w, int n, int limit, List<Integer> list) {
		if(limit < 1 || n < 1) {
			return 0;
		}
		
		if(w[n-1] > limit) {
			return knapsackSum(w, n - 1, limit, list);
		}
		
		ArrayList<Integer> box1 = (ArrayList<Integer>) list;
		ArrayList<Integer> box2 = (ArrayList<Integer>) list;
		
		return 0;
		
		
	}
	
	
	public static void addFiles(Scanner list) {
		String line;
		while(list.hasNextLine()){
			line = list.nextLine();
			files.add(line);
		}
	}
	
	public static int size(Scanner list) {
		int count = -1;
		while (list.hasNextLine()) {
		    count++;
		    if(list.hasNextLine()) {   
		    	list.nextLine();
		    }
		}
		return count;
	}
	
	public static int spot(ArrayList<Integer> box) {
		int k =0;
		while(box.get(k) != null) {
			k++;
		}
		return k;
	}
	
	public static String runFile(Scanner list, int f, int c, List<Integer> box) {
		String limit = list.nextLine();
		String line = list.nextLine();
		int[] melons = new int[c];
		String nums = limit + "    ";
		for(int i = 0; i < c; i++) {
			melons[i] = Integer.parseInt(line);
			if(list.hasNextLine()) {
				line = list.nextLine();
			}
		}
		for(int j = 0; j < melons.length; j++) {
			nums += melons[j] + ", ";
		}
		nums = nums.substring(0, nums.length() - 2);
		
		int total = knapsackSum(melons, c, Integer.parseInt(limit), box);
		
		return (files.get(f) + "   " + nums + "   " + total);
		
	}
	
	public static String runFile(Scanner list, int f, int c) {
		String limit = list.nextLine();
		String line = list.nextLine();
		int[] melons = new int[c];
		String nums = limit + "    ";
		for(int i = 0; i < c; i++) {
			melons[i] = Integer.parseInt(line);
			if(list.hasNextLine()) {
				line = list.nextLine();
			}
		}
		for(int j = 0; j < melons.length; j++) {
			nums += melons[j] + ", ";
		}
		nums = nums.substring(0, nums.length() - 2);
		
		int total = knapsackSum(melons, c, Integer.parseInt(limit));
		
		return (files.get(f) + "   " + nums + "   " + total);
		
	}
	
	
	public static void main(String[] args) {
		PrintWriter out = outputFile("knapsack.txt");
		Scanner file = openWords(args[0], 1, out);
		Scanner keyboard = new Scanner(System.in);
		addFiles(file);
		int k;
		ArrayList<Integer> list;
		
		if(files.size() < 1) {
			System.out.println("Enter a file name: ");
			files.set(0, keyboard.nextLine());
		}
		
		for(int i = 0; i < files.size(); i++) {	
			if(size(file)>=0) {
				file = openWords(files.get(i), (i + 1), out);			
				k = (size(file));
				file = openWords(files.get(i), (i + 1), out);
				list = new ArrayList<Integer>();
				out.println(runFile(file, i, k) + "\n");
		
			}
		}
		
		file.close();
		out.close();
		
	}	
}