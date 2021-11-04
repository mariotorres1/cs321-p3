import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

/**
 * Creates and tests our hash table for different data values
 * @author Mario Torres
 *
 */
public class HashtableTest {
	
	private static double loadFactor;
	private static int count;
	
	public static void main(String[] args) throws InterruptedException {
		int tableSize;
		TwinPrimeGenerator primeNumber = new TwinPrimeGenerator();
		tableSize = primeNumber.getTwinPrime(95500, 96000);
		int inputNumber = 0;
		
		if (args.length == 2) {
			try {
				loadFactor = Double.parseDouble(args[1]);
				if (loadFactor < 1 && loadFactor > 0) {
					inputNumber = (int) (Math.ceil(loadFactor * tableSize));
				} else {
					System.out.println("Load factor should be a less than 1 and greater than zero ");
					printUsage();
					System.exit(1);
				}
				if (args[0].equals("1")) {
					//generalPrint("Integer:java.util.Random", tableSize);
					simulationRandomInts(inputNumber, tableSize, 0);
				} else if (args[0].equals("2")) {
					//generalPrint("Long:System.currentTimeMillis()", tableSize);
					//simulationLong(inputNumber, tableSize, 0);

				} else if (args[0].equals("3")) {
					//generalPrint("String:File Name -> word-list", tableSize);
					//simulationString(inputNumber, tableSize, 0);
				} else {
					printUsage();
					System.exit(1);
				}
			} catch (NumberFormatException e) {
				System.out.println("Load factor should be a number");
				printUsage();
				System.exit(1);
			}
		} else if (args.length == 3) {
			try {
				loadFactor = Double.parseDouble(args[1].toString());
				if (loadFactor < 1 && loadFactor > 0) {
					inputNumber = (int) (Math.ceil(loadFactor * tableSize));
				} else {
					System.out.println("Load factor should be a less than 1 and greater than zero ");
					printUsage();
					System.exit(1);
				}
				int debug = Integer.parseInt(args[2]);
				if (args[0].equals("1")) {
					//generalPrint("Integer:java.util.Random", tableSize);
					simulationRandomInts(inputNumber, tableSize, debug);

				} else if (args[0].equals("2")) {
					//generalPrint("Long:System.currentTimeMillis()", tableSize);
					simulationLong(inputNumber, tableSize, debug);

				} else if (args[0].equals("3")) {
					//generalPrint("String:File Name -> word-list", tableSize);
					simulationString(inputNumber, tableSize, debug);
				} else {
					printUsage();
					System.exit(1);
				}
			} catch (NumberFormatException e) {
				System.out.println("Load factor and debug should be numbers");
				printUsage();
				System.exit(1);
			}
		} else {
			printUsage();
			System.exit(1);
		}

	}
	
	
	private static void printUsage() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * simulationRandomInts - Simulates two types of hash tables, linear and double using int type of random ints
	 * @param maxInsert - The number of inputs in the hash table found from using tableSize and the loadFactor
	 * @param tableSize - The size of the hash table
	 * @param debug - The debug level of the simulation for output
	 */
	private static void simulationRandomInts(int maxInsert, int tableSize, int debug) {
		LinearProbing<Integer> intLinearTable = new LinearProbing<Integer>(tableSize);
		DoubleHashing<Integer> intDoubleTable = new DoubleHashing<Integer>(tableSize);
		
		Random rand = new Random();
		int intRand;
		while (intLinearTable.getTotalInserts() < maxInsert || intDoubleTable.getTotalInserts() < maxInsert) {
			intRand = rand.nextInt();
			
			//if (!intLinearTable.isDuplicate(intRand)) {
				intLinearTable.hashInsert(intRand);
			//}
			
			//if (!intDoubleTable.isDuplicate(intRand)) {
				intDoubleTable.hashInsert(intRand);
			//}
		}
		
		if (debug == 0) {
			zeroDebug(intLinearTable, "Linear");
			zeroDebug(intDoubleTable, "Double");
		}
	}
	
	/**
	 * simulationLong - Simulates two types of hash tables, linear and double using Long type of System.nanoTime()
	 * 
	 * @param maxInsert - The number of inputs in hash table found from using tableSize and the loadFactor
	 * @param tableSize - The size of the hash table
	 * @param debug - The debug level of the simulation for output
	 * @throws InterruptedException 
	 */
	private static void simulationLong(int maxInsert, int tableSize, int debug) throws InterruptedException {
		// highest time needed in this simulation as so many duplicates
		LinearProbing<Long> longLinearTable = new LinearProbing<Long>(tableSize); // creating linear hash table
		DoubleHashing<Long> longDoubleTable = new DoubleHashing<Long>(tableSize);
		long systemTime;
		
		while (longLinearTable.getTotalInserts() < maxInsert || longDoubleTable.getTotalInserts() < maxInsert) {
			Thread.sleep(10);
			systemTime = System.currentTimeMillis();
			
			//if (!lngLinearTable.isDuplicate(systemTime)) {
				longLinearTable.hashInsert(systemTime);
			//}
			//if (!lngDoubleTable.isDuplicate(systemTime)) {
				longDoubleTable.hashInsert(systemTime);
			//}
		}

		if (debug == 0) {
			zeroDebug(longLinearTable, "Linear");
			zeroDebug(longDoubleTable, "Double");
		} else if (debug == 1) {
			oneDebug(longLinearTable, "Linear", tableSize);
			oneDebug(longDoubleTable, "Double", tableSize);
		} else if (debug == 2) {
			twoDebug(longLinearTable, "Linear", tableSize);
			twoDebug(longDoubleTable, "Double", tableSize);
		}
	}
	
	/**
	 * simulationString - Simulates two types of hash tables, linear and double using String type of words from a text file
	 * 
	 * @param maxInsert - The number of inputs in hash table found from using tableSize and the loadFactor
	 * @param tableSize - The size of the hash table
	 * @param debug - The debug level of the simulation for output
	 */
	private static void simulationString(int maxInsert, int tableSize, int debug) {
		LinearProbing<String> strLinearTable = new LinearProbing<String>(tableSize);
		DoubleHashing<String> strDoubleTable = new DoubleHashing<String>(tableSize);

		try {
			File file = new File("word-list");
			Scanner scan = new Scanner(file);
			
			count = 0;
			while(scan.hasNextLine()) {
				String word = scan.nextLine();

				if (strLinearTable.getTotalInserts() < maxInsert || strDoubleTable.getTotalInserts() < maxInsert) {
					//if (!strLinearTable.isDuplicate(word)) {
						strLinearTable.hashInsert(word);
					//}
					//if (!strDoubleTable.isDuplicate(word)) {
						strDoubleTable.hashInsert(word);
					//}
					count++;
				} else {
					break;
				}
			}
			scan.close();

			if (debug == 0) {
				zeroDebug(strLinearTable, "Linear");
				zeroDebug(strDoubleTable, "Double");
			} else if (debug == 1) {
				oneDebug(strLinearTable, "Linear", tableSize);
				oneDebug(strDoubleTable, "Double", tableSize);
			}

		} catch (IOException e) {
			System.out.println("File not Found");
			printUsage();
			System.exit(1);
		}
	}

	
	/**
	 * zeroDebug - Prints a basic summary of the current simulation
	 * 
	 * @param hashTable - The hash table
	 * @param tableType - The type of hashing used in our hash table
	 */
	private static <T> void zeroDebug(HashTable<T> hashTable, String tableType) {
		double probes = ((double) hashTable.getTotalProbes()) / (double) (hashTable.getTotalInserts());
		int totalInput = hashTable.getTotalInserts() + hashTable.getTotalDuplicates();
		System.out.println("Count: " + count + ", Probes: " + hashTable.getTotalProbes());
		System.out.println("Using " + tableType + " Hashing...");
		System.out.print("Input " + totalInput + " elements");
		System.out.println(" , of which " + hashTable.getTotalDuplicates() + " are duplicates");
		System.out.println("Load Factor = " + loadFactor + ", Avg. no. of probes " + probes + "\n\n");
	}
	
	/**
	 * oneDebug - Prints a summary of the current simulation in the console and prints a dump file of the current simulation for each hashing
	 * 
	 * @param hashTable - The hash table
	 * @param tableType - The type of hashing used in our hash table
	 * @param tableSize - The size of the hash table
	 */
	private static <T> void oneDebug(HashTable<T> hashTable, String tableType, int tableSize) {
		zeroDebug(hashTable, tableType);
		String fileName = tableType + "-dump.txt";

		PrintWriter out;
		try {
			out = new PrintWriter(new FileWriter(fileName)); // use FileWriter instead System.setout (PrintStream)
			// then only one file for output stream and the console output is not printed

			for (int i = 0; i < tableSize; i++) {
				if (hashTable.getObject(i) != null) {
					out.println("table[" + i + "]: " + hashTable.getObject(i).toString());
				}
			}
			out.close(); // must must must use it

		} catch (IOException e) {
			System.out.println("Cannot Open file");
			printUsage();
			System.exit(1);
		}
	}
	
	/**
	 * twoDebug - Prints a detailed summary of the simulation to the console for each object stored in our hash table
	 *
	 * @param hashTable - The hash table
	 * @param tableType - The type of hashing used in our hash table
	 * @param tableSize - The size of the hash table
	 */
	private static <T> void twoDebug(HashTable<T> hashTable, String tableType, int tableSize) {
		zeroDebug(hashTable, tableType);
		for (int i = 0; i < tableSize; i++) {
			if (hashTable.getObject(i) != null) {
				System.out.println("Table[" + i + "]: " + hashTable.getObject(i).toString());
			}
		}
	}

}
