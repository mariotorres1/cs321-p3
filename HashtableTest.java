import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

/**
 * File: HashtableTest.java
 * @author Mario Torres
 * Date: October 25, 2021
 * Description: Creates and tests our HashTable.java class using different data values
 */
public class HashtableTest {

	// Variable used outside and inside of main
	private static double loadFactor;
	
	public static void main(String[] args) throws InterruptedException, HashTableOverflowException {
		// Variables for main
		int tableSize;
		TwinPrimeGenerator primeNumber = new TwinPrimeGenerator();
		tableSize = primeNumber.getTwinPrime(95500, 96000);
		int inputNumber = 0;

		// If no debug level is entered in command line arguments, defaults to debug level 0
		if (args.length == 2) {
			try {
				// Retrieving loadFactor
				loadFactor = Double.parseDouble(args[1]);
				// Verifying loadFactor
				if (loadFactor < 1 && loadFactor > 0) {
					inputNumber = (int) (Math.ceil(loadFactor * tableSize));
				} else {
					System.out.println("Load factor should be a less than 1 and greater than zero ");
					printUsage();
					System.exit(1);
				}
				// Set debug level to zero
				int debug = 0;
				// Random int simulation chosen
				if (args[0].equals("1")) {
					simulationRandomInts(inputNumber, tableSize, debug);
				} else if (args[0].equals("2")) { // Random long simulation chosen
					simulationLong(inputNumber, tableSize, debug);
				} else if (args[0].equals("3")) { // String simulation chosen
					simulationString(inputNumber, tableSize, debug);
				} else { // Something was entered incorrectly, print usage
					printUsage();
					System.exit(1);
				}
			} catch (NumberFormatException e) { // If a number is not entered for loadFactor
				System.out.println("Load factor should be a number");
				printUsage();
				System.exit(1);
			}
		} else if (args.length == 3) { // If a debug level is entered from the command line
			try {
				// Retrieves the loadFactor and verifies it
				loadFactor = Double.parseDouble(args[1]);
				if (loadFactor < 1 && loadFactor > 0) {
					inputNumber = (int) (Math.ceil(loadFactor * tableSize));
				} else {
					System.out.println("Load factor should be a less than 1 and greater than zero ");
					printUsage();
					System.exit(1);
				}
				// Retrieves the debug level entered
				int debug = Integer.parseInt(args[2]);
				// Random int simulation chosen
				if (args[0].equals("1")) {
					simulationRandomInts(inputNumber, tableSize, debug);
				} else if (args[0].equals("2")) { // Random long simulation chosen
					simulationLong(inputNumber, tableSize, debug);
				} else if (args[0].equals("3")) { // String simulation chosen
					simulationString(inputNumber, tableSize, debug);
				} else { // Something not entered correctly, print usage
					printUsage();
					System.exit(1);
				}
			} catch (NumberFormatException e) { // If loadFactor entered is not a number
				System.out.println("Load factor and debug should be numbers");
				printUsage();
				System.exit(1);
			}
		} else { // Incorrect amount of command line arguments entered, print usage
			printUsage();
			System.exit(1);
		}

	}

	/**
	 * generalPrint - Print that should be printed every time the program is run
	 * 
	 * @param data - A string of the data type being run in the simulation
	 * @param tableSize - The size of the hash table
	 */
	private static void generalPrint(String data, int tableSize) {
		System.out.println("\nTwin prime table size found in the range[95500 .. 96000]: " + tableSize);
		System.out.println();
		System.out.println("Data source type " + data);
		System.out.println();
	}

	/**
	 * printUsage - Prints to console the required command line format for running th program correctly
	 */
	private static void printUsage() {
		System.out.println("Usage: java HashtableTest <input type> <load factor> [<debug level>]");
		System.out.println("\tinput type = 1 for random integers, 2 for system time, 3 for word list");
		System.out.println("\tdebug = 0 ==> print summary of experiment");
		System.out.println("\tdebug = 1 ==> save the two hash tables to a file at the end");
		System.out.println("\tdebug = 2 ==> print debugging output for each insert");
	}

	/**
	 * simulationRandomInts - Simulates two types of hash tables, linear and double using int type of random ints
	 * 
	 * @param maxInsert - The number of inputs in the hash table found from using tableSize and the loadFactor
	 * @param tableSize - The size of the hash table
	 * @param debug - The debug level of the simulation for output
	 * @throws HashTableOverflowException 
	 */
	private static void simulationRandomInts(int maxInsert, int tableSize, int debug) throws HashTableOverflowException {
		// Two hash tables created, one for linear probing and one for double hashing
		LinearProbing<Integer> intLinearTable = new LinearProbing<>(tableSize);
		DoubleHashing<Integer> intDoubleTable = new DoubleHashing<>(tableSize);

		// Creates variable to store random ints
		Random rand = new Random();
		int intRand;
		// Inserts random int to hash tables until they fill up specified size from loadFactor
		while (intLinearTable.getTotalInserts() < maxInsert || intDoubleTable.getTotalInserts() < maxInsert) {
			// Gets random int
			intRand = rand.nextInt();

			// Inserts random int in both linear probing hash table and double hashing hash table
			intLinearTable.hashInsert(intRand);
			intDoubleTable.hashInsert(intRand);
		}

		generalPrint("--> random numbers", tableSize);
		// If debug level is zero
		if (debug == 0) {
			// Prints debug information for linear probing hash table and double hashing hash table
			zeroDebug(intLinearTable, "linear");
			zeroDebug(intDoubleTable, "double");
		} else if (debug == 1) { // If debug level is one
			// Prints debug information for linear probing hash table and double hashing hash table
			oneDebug(intLinearTable, "linear", tableSize);
			oneDebug(intDoubleTable, "double", tableSize);
		} else if (debug == 2) { // If debug level is two
			// Prints debug information for linear probing hash table and double hashing hash table
			twoDebug(intLinearTable, "linear", tableSize);
			twoDebug(intDoubleTable, "double", tableSize);
		}

	}
	
	/**
	 * simulationLong - Simulates two types of hash tables, linear and double using Long type of System.nanoTime()
	 * 
	 * @param maxInsert - The number of inputs in hash table found from using tableSize and the loadFactor
	 * @param tableSize - The size of the hash table
	 * @param debug - The debug level of the simulation for output
	 * @throws HashTableOverflowException 
	 */
	private static void simulationLong(int maxInsert, int tableSize, int debug) throws HashTableOverflowException {
		// Two hash tables created, one for linear probing and one for double hashing
		LinearProbing<Long> longLinearTable = new LinearProbing<>(tableSize); // creating linear hash table
		DoubleHashing<Long> longDoubleTable = new DoubleHashing<>(tableSize);
		// Variable to store the long value
		long systemTime;

		// Inserts random int to hash tables until they fill up specified size from loadFactor
		while (longLinearTable.getTotalInserts() < maxInsert || longDoubleTable.getTotalInserts() < maxInsert) {
			// Gets long value to be stored
			systemTime = System.currentTimeMillis();

			// Inserts long in both linear probing hash table and double hashing hash table
			longLinearTable.hashInsert(systemTime);
			longDoubleTable.hashInsert(systemTime);
		}

		generalPrint("--> system time", tableSize);
		// If debug level is zero
		if (debug == 0) {
			// Prints debug information for linear probing hash table and double hashing hash table
			zeroDebug(longLinearTable, "Linear");
			zeroDebug(longDoubleTable, "Double");
		} else if (debug == 1) { // If debug level is one
			// Prints debug information for linear probing hash table and double hashing hash table
			oneDebug(longLinearTable, "Linear", tableSize);
			oneDebug(longDoubleTable, "Double", tableSize);
		} else if (debug == 2) { // If debug level is two
			// Prints debug information for linear probing hash table and double hashing hash table
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
	 * @throws HashTableOverflowException 
	 */
	private static void simulationString(int maxInsert, int tableSize, int debug) throws HashTableOverflowException {
		// Two hash tables created, one for linear probing and one for double hashing
		LinearProbing<String> strLinearTable = new LinearProbing<>(tableSize);
		DoubleHashing<String> strDoubleTable = new DoubleHashing<>(tableSize);

		// Tries to open and read from file
		try {
			File file = new File("word-list");
			Scanner scan = new Scanner(file);

			// Reads the file while the file has another line to be read
			while(scan.hasNextLine()) {
				// Stores word from next line
				String word = scan.nextLine();

				// Verifies that our hash table is not full based on the loadFactor
				if (strLinearTable.getTotalInserts() < maxInsert || strDoubleTable.getTotalInserts() < maxInsert) {
					// Inserts word in both linear probing hash table and double hashing hash table
					strLinearTable.hashInsert(word);
					strDoubleTable.hashInsert(word);
				} else { // If table is full break from loop
					break;
				}
			}
			// Close file open earlier
			scan.close();

			generalPrint("--> word-list", tableSize);
			// If debug level is zero
			if (debug == 0) {
				// Prints debug information for linear probing hash table and double hashing hash table
				zeroDebug(strLinearTable, "Linear");
				zeroDebug(strDoubleTable, "Double");
			} else if (debug == 1) { // If debug level is one
				// Prints debug information for linear probing hash table and double hashing hash table
				oneDebug(strLinearTable, "Linear", tableSize);
				oneDebug(strDoubleTable, "Double", tableSize);
			} else if (debug == 2) { // If debug level is two
				// Prints debug information for linear probing hash table and double hashing hash table
				twoDebug(strLinearTable, "linear", tableSize);
				twoDebug(strDoubleTable, "double", tableSize);
			}

		} catch (IOException e) { // Error trying to find file to open and read, prints usage
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
		// Variable to find the total probes that occurred in hash table
		double probes = ((double) hashTable.getTotalProbes()) / (double) (hashTable.getTotalInserts());
		// Variable to get total inserts and duplicates for hash table
		int totalInput = hashTable.getTotalInserts() + hashTable.getTotalDuplicates();
		// Prints basic information about hash table
		System.out.println("Using " + tableType + " Hashing...");
		System.out.print("Input " + totalInput + " elements");
		System.out.println(" , of which " + hashTable.getTotalDuplicates() + " are duplicates");
		System.out.println("Load Factor = " + loadFactor + ", Avg. no. of probes " + probes + "\n");
	}
	
	/**
	 * oneDebug - Prints a summary of the current simulation in the console and prints a dump file of the current simulation for each hashing
	 * 
	 * @param hashTable - The hash table
	 * @param tableType - The type of hashing used in our hash table
	 * @param tableSize - The size of the hash table
	 */
	private static <T> void oneDebug(HashTable<T> hashTable, String tableType, int tableSize) {
		// Prints zeroDebug() information about hash table
		zeroDebug(hashTable, tableType);
		// Creates filename for data to be stored in to
		String fileName = tableType + "-dump.txt";

		// Using PrintWriter to write to file
		PrintWriter out;
		// Tries to go through our hash table, writing the data to our file
		try {
			out = new PrintWriter(new FileWriter(fileName));
			// Looping through our table writing data to file
			for (int i = 0; i < tableSize; i++) {
				if (hashTable.getObject(i) != null) {
					out.println("table[" + i + "]: " + hashTable.getObject(i).toString());
				}
			}
			// Close our file
			out.close();

		} catch (IOException e) { // Unable to write to file, prints usage
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
		// Loops through table printing each objects location in table, and important information about each object
		for (int i = 0; i < tableSize; i++) {
			if (hashTable.getObject(i) != null) {
				System.out.println("Table[" + i + "]: Object Key: " + hashTable.getObject(i).getKey() +
						", Frequency: " + hashTable.getObject(i).getFrequencyCount() + ", Probe Count: " + hashTable.getObject(i).getProbeCount());
			}
		}
		System.out.println();
		// At end, prints basic information about table using zeroDebug()
		zeroDebug(hashTable, tableType);
	}

}
