import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Knapsack {

	static ArrayList<String> files = new ArrayList<String>();
	
	
	/**

	 * @author Aidan-S

	 * date: February 25th, 2018

	 * method: Create a print writer so that I can print out my output

	 * @param fName: name of the file I want to print to

	 * @return: A Print Writer that I can print to

	 */
	public static PrintWriter outputFile(String fName) {
			File fileName = new File(fName);
			PrintWriter output = null;
			try {
				output = new PrintWriter(fileName);
			} catch (FileNotFoundException ex) {
				System.out.print("Cannot open " + fName + ", it may not exist");
				return null;
			}
			return output;
	}
	
	
	/**

	 * @author Aidan-S

	 * date: February 25th, 2018

	 * method: Create a Scanner so that I can read in the given files

	 * @param fname: name of the file I want to read
	 * @param fnum: the number of the file I'm opening
	 * @param out: printwriter that I'm printing to

	 * @return: A Scanner of the file I send in

	 */
	public static Scanner openWords(String fname, int fnum, PrintWriter out) {
		File file = new File(fname);
		Scanner input = null;
		try {
			input = new Scanner(file);
		} catch (FileNotFoundException ex) {
			System.out.print("Cannot open " + fname + ", it may not exist");
			return null;
		}
		return input;	
	}
	
	/**

	 * @author Aidan-S

	 * date: February 25th, 2018

	 * method: recursively goes through each of the items in the list(w[n]), testing whether we would get a better value from that item being there or not while staying within the limit

	 * @param limit: how much room is left in the bag
	 * @param n: what number item we're on
	 * @param w: array of weights

	 * @return: total that we can fit in without overflow

	 */
	public static int knapsackSum(int[] w, int n, int limit) {
		//base case
		if(limit < 1 || n < 1) {
			return 0;
		}
		
		
		
		//make 2 different paths
		int with = knapsackSum(w, n - 1, limit - w[n-1])  +  w[n-1];
		int without = knapsackSum(w, n - 1, limit)  +  w[n-1];
			
		//which is better
		if(with >= without) {
			if(with <=limit) {
				return with;
			} 
			
		}else {
			if(without <=limit) {
				return without;
			}
			
		}
		return 0;
	}

	
	
	/**-------------------------------------------------------------------------------------------------------

	 * @author Aidan-S

	 * date: February 25th, 2018

	 * method: recursively goes through each of the items in the list(w[n]), testing whether we would get a better value from that item being there or not while staying within the limit  and then keeping track of the watermelons used

	 * @param limit: how much room is left in the bag
	 * @param n: what number item we're on
	 * @param w: array of weights
	 * @param list: array list to hold what items are used

	 * @return: total that we can fit in without overflow

		-------------------------------------------------------------------------------------------------------
	 */
	
	 public static int knapsackSum(int[] w, int n, int limit, List<Integer> list) {
		//base case
		 if(limit < 1 || n < 1) {
			return 0;
		}
		
		//list to keep track of watermelons
		ArrayList<Integer> box = (ArrayList<Integer>) list;
		
		//make the 2 paths to travel
		int without = knapsackSum(w, n - 1, limit, list);
		int with = knapsackSum(w, n - 1, limit - w[n-1], box);
		
		//which is better
		if(with <= knapsackSum(w, n - 1, with)) {
			//add if its good
			box.set(spot((ArrayList<Integer>) list), w[n-1]);
			return knapsackSum(w, n - 1, limit - w[n-1], box) + w[n-1];
		}else {
			return knapsackSum(w, n - 1, limit, list);
		}
		
		
	}
	
	/**

	 * @author Aidan-S

	 * date: February 25th, 2018

	 * method: put all files I need to evaluate on files list

	 * @param list: list of files to add

	 * return: none

	 */
	public static void addFiles(Scanner list) {
		String line;
		//add the files to the global list
		while(list.hasNextLine()){
			line = list.nextLine();
			files.add(line);
		}
	}
	
	/**

	 * @author Aidan-S

	 * date: February 25th, 2018

	 * method: find the number of watermelons that I can add to the sack

	 * @param list: scanner of the file with the watermelon information

	 * @return: none

	 */
	public static int size(Scanner list) {
		int count = -1;
		//how many items are in the given file
		while (list.hasNextLine()) {
		    count++;
		    if(list.hasNextLine()) {   
		    	list.nextLine();
		    }
		}
		return count;
	}
	
	/**

	 * @author Aidan-S

	 * date: February 25th, 2018

	 * method: find the nearest open slot in array list

	 * @param box: array list to be evaluated

	 * @return: int where the next spot is

	 */
	public static int spot(ArrayList<Integer> box) {
		int k=0;
		//next open spot
		if(box.size()<1) {return 0;};
		while(box.get(k) != null) {
			k++;
		}
		return k;
	}
	
	/**

	 * @author Aidan-S

	 * date: February 25th, 2018

	 * method: organize all my variables so that I can call my recursive method with a list

	 * @param  list: file with the watermelon information 
	 * @param  f: what number file in files array list im looking at
	 * @param  c: how many watermelons
	 * @param  box: array list of what watermelons will be used (to be filled later)
	 * 
	 * @return: String of what the best solution is and the watermelon information

	 */
	public static String runFile(Scanner list, int f, int c, List<Integer> box) {
		String limit = list.nextLine();
		String line = list.nextLine();
		int[] melons = new int[c];
		String nums = limit + "    ";
		
		//put together all of the watermelon weights
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
		
		//call recursive method
		int total = knapsackSum(melons, c, Integer.parseInt(limit), box);
		
		//add watermelon info + recursive answer
		return (files.get(f) + "   " + nums + "\n\n" + total);
		
	}
	
	/**

	 * @author Aidan-S

	 * date: February 25th, 2018

	 * method: organize all my variables so that I can call my recursive method without a list

	 * @param  list: file with the watermelon information 
	 * @param  f: what number file in files array list im looking at
	 * @param  c: how many watermelons         
	  
	 * @return: String of what the best solution is and the watermelon information

	 */
	public static String runFile(Scanner list, int f, int c) {
		String limit = list.nextLine();
		String line = list.nextLine();
		int[] melons = new int[c];
		String nums = limit + "    ";
		
		//put together all of the watermelon weights
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
		
		//call recursive method
		int total = knapsackSum(melons, c, Integer.parseInt(limit));
		
		//add watermelon info + recursive answer
		return (files.get(f) + "   " + nums + "\n\n" + total);
		
	}
	
	/**

	 * @author Aidan-S

	 * date: February 25th, 2018

	 * method: Run the code, take in the file to be turned into all the different files that need to be opened, calling the methods that lead to the recursive methods

	 * @param  args: arguments

	 * return: none

	 */
	public static void main(String[] args) {
		//set up scanners and get variables
		PrintWriter out = outputFile("knapsack.txt");
		Scanner file = openWords(args[0], 1, out);
		Scanner keyboard = new Scanner(System.in);
		addFiles(file);
		int k;
		ArrayList<Integer> list;
		
		//add file if none are given
		if(files.size() < 1) {
			System.out.println("Enter a file name: ");
			files.add(keyboard.nextLine());
			file = openWords(files.get(0), 1, out);
		}
		
		//activate the recursive method for all the given files
		for(int i = 0; i < files.size(); i++) {	
			if(file != null) {
				file = openWords(files.get(i), (i + 1), out);			
				k = (size(file));
				file = openWords(files.get(i), (i + 1), out);
				list = new ArrayList<Integer>();
				out.println(runFile(file, i, k, list) + "\n");
				for(int p = 0; p < list.size(); p++) {
					out.println(list.get(p) + "watermelons \n");
				}
			}
		}
		
		//close up shop
		if(file != null) {file.close();}
		
		out.close();
		
	}	
}