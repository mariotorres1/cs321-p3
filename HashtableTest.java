import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

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


	private static void simulationRandomInts(int maxInsert, int tableSize, int debug) {
		LinearProbing<Integer> intLinearTable = new LinearProbing<Integer>(tableSize);
		DoubleHashing<Integer> intDoubleTable = new DoubleHashing<Integer>(tableSize);
		
		Random rand = new Random();
		int intRand;
		while (intLinearTable.getTotalInserts() < maxInsert || intDoubleTable.getTotalInserts() < maxInsert) {
			intRand = rand.nextInt();
			
			if (!intLinearTable.isDuplicate(intRand)) {
				intLinearTable.hashInsert(intRand);
			}
			
			if (!intDoubleTable.isDuplicate(intRand)) {
				intDoubleTable.hashInsert(intRand);
			}
		}
		
		if (debug == 0) {
			zeroDebug(intLinearTable, "Linear");
			zeroDebug(intDoubleTable, "Double");
		}
	}
	
	/**
	 * Simulates two types of hash tables, linear and double using Long type
	 * 
	 * @param inputNumber is the highest number of inputs in hash table of the
	 *                    corresponding load factor
	 * @param tableSize   is the size of the hash table
	 * @param debug       is the debug level of the simulation output
	 * @throws InterruptedException 
	 */
	private static void simulationLong(int inputNumber, int tableSize, int debug) throws InterruptedException {
		// highest time needed in this simulation as so many duplicates
		LinearProbing<Long> lngLinearTable = new LinearProbing<Long>(tableSize); // creating linear hash table
		DoubleHashing<Long> lngDoubleTable = new DoubleHashing<Long>(tableSize);
		Long systemTime;
		
		while (lngLinearTable.getTotalInserts() < inputNumber && lngDoubleTable.getTotalInserts() < inputNumber) {
			systemTime = System.nanoTime();
			
			if (!lngLinearTable.isDuplicate(systemTime)) {
				lngLinearTable.hashInsert(systemTime);
			}
			if (!lngDoubleTable.isDuplicate(systemTime)) {
				lngDoubleTable.hashInsert(systemTime);
			}
		}

		if (debug == 0) {
			zeroDebug(lngLinearTable, "Linear");
			zeroDebug(lngDoubleTable, "Double");
		}
	}
	
	/**
	 * Simulates two types of hash tables, linear and double using String type
	 * 
	 * @param inputNumber is the highest number of inputs in hash table of the
	 *                    corresponding load factor
	 * @param tableSize   is the size of the hash table
	 * @param debug       is the debug level of the simulation output
	 */
	private static void simulationString(int inputNumber, int tableSize, int debug) {
		// medium amount of time needed among the three simulations, as it is a word
		// list and may have lot of duplicates
		LinearProbing<String> strLinearTable = new LinearProbing<String>(tableSize); // creating linear hash table
		DoubleHashing<String> strDoubleTable = new DoubleHashing<String>(tableSize);

		try {
			File file = new File("word-list");
			Scanner scan = new Scanner(file);
			
			count = 0;
			while(scan.hasNextLine()) {
				String word = scan.nextLine();

				if (strLinearTable.getTotalInserts() < inputNumber && strDoubleTable.getTotalInserts() < inputNumber) {
					if (!strLinearTable.isDuplicate(word)) {
						strLinearTable.hashInsert(word);
					}
					if (!strDoubleTable.isDuplicate(word)) {
						strDoubleTable.hashInsert(word);
					}
					count++;
				} else {
					break;
				}
			}
			scan.close();

			if (debug == 0) {
				zeroDebug(strLinearTable, "Linear");
				zeroDebug(strDoubleTable, "Double");
			}
			
			if (debug == 1) {
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
	 * Prints a summary of the current simulation
	 * 
	 * @param hashTable is the hash table
	 * @param tableType is the type of hashing
	 */
	private static <T> void zeroDebug(HashTable<T> hashTable, String tableType) {
		double probes = ((double) hashTable.getTotalProbes() - hashTable.getTotalDuplicates()) / (double) (hashTable.getTotalInserts());
		int totalInput = hashTable.getTotalInserts() + hashTable.getTotalDuplicates();
		System.out.println("Count: " + count + ", Probes: " + hashTable.getTotalProbes());
		System.out.println("Using " + tableType + " Hashing...");
		System.out.print("Input " + totalInput + " elements");
		System.out.println(" , of which " + hashTable.getTotalDuplicates() + " are duplicates");
		System.out.println("Load Factor = " + loadFactor + ", Avg. no. of probes " + probes + "\n\n");
	}
	
	/**
	 * Prints a summary of the current simulation in the console and prints a dump
	 * file of the current simulation of the current hashing
	 * 
	 * @param hashTable is hash table
	 * @param tableType is the type of hashing
	 * @param tableSize is the size of the hash table
	 */
	private static <T> void oneDebug(HashTable<T> hashTable, String tableType, int tableSize) {
		zeroDebug(hashTable, tableType);
		String fileName = tableType + "-dump";

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

}
