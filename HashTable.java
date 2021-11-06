import java.util.Arrays;

/**
 * Creates a generic type hash table. Methods to get information about our hash table.
 * @author Mario Torres
 *
 * @param <T> Generic Type
 */
public abstract class HashTable<T> {
	protected HashObject<T>[] hashTable;
	protected int tableSize;
	protected int totalProbes;
	protected int totalInserts;
	protected int totalDuplicates;
	
	/**
	 * Constructor - creates our hash table with a given size, initializes other important values to 0.
	 * @param tableSize - size of our hash table
	 */
	@SuppressWarnings("unchecked")
	public HashTable(int tableSize) {
		hashTable = (HashObject<T>[]) new HashObject[tableSize];
		this.tableSize = tableSize;
		this.totalProbes = 0;
		this.totalInserts = 0;
	}
	
	/**
	 * getTableSize - Gets the table size of our hash table
	 * @return tableSize
	 */
	public int getTableSize() {
		return tableSize;
	}
	
	/**
	 * getTotalProbes - Gets the total probes of our hash table
	 * @return totalProbes
	 */
	public int getTotalProbes() {
		return totalProbes;
	}
	
	/**
	 * getTotalInserts - Gets the total inserts to our hash table
	 * @return totalInserts
	 */
	public int getTotalInserts() {
		return totalInserts;
	}
	
	/**
	 * getTotalDuplicates - Gets the total duplicates in our hash table
	 * @return totalDuplicates
	 */
	public int getTotalDuplicates() {
		return totalDuplicates;
	}
	
	/**
	 * getHashTable - gets the hash table
	 * @return hashTable
	 */
	public HashObject<T>[] getHashTable() {
		return hashTable;
	}
	
	/**
	 * getObject - Gets the object at a specific index location in our hash table
	 * @param index - location of object in our hash table
	 * @return object in our hash table a location of index
	 */
	public HashObject<T> getObject(int index) {
		return hashTable[index];
	}
	
	/**
	 * isEmpty - Tells us if our hash table is empty or not
	 * @return true if nothing has been inserted into our table, false otherwise
	 */
	public boolean isEmpty() {
		return totalInserts == 0;
	}
	
	/**
	 * toString - Prints our hash table
	 */
	public String toString() {
		return Arrays.toString(hashTable);
	}
	
	/**
	 * positiveMod - Makes sure the key value is always positive
	 * @param dividend - k value of k mod m
	 * @param divisor - m value of k mod m
	 * @return A positive value if our key is negative
	 */
	protected int positiveMod (int dividend, int divisor) {
		int value = dividend % divisor;
		
		if (value < 0) {
			value += divisor;
		}
		return value;
	}
	
	/**
	 * hashFunction - Method prototype to be used in child classes to get specific hash function for each child 
	 * @param hashObject - Object information used to be stored/retrieved/found in our hash table
	 * @param index - position used in our hash function 
	 * @return a position in our hash table to attempt to store/retrieve/find object in our hash table
	 */
	protected abstract int hashFunction(HashObject<T> hashObject, int index);
	
	/**
	 * hashInsert - Inserts an object into our hash table 
	 * @param object - Data to be stored into our hash table
	 */
	public void hashInsert(T object) {
		HashObject<T> hashObject = new HashObject<>(object);
		int i = 0;
		int count = 0;
		while (i != tableSize) {
			int j = hashFunction(hashObject, i);
			hashObject.increaseObjectProbe();
			//count++;
			if (hashTable[j] == null) {
				hashTable[j] = hashObject;
				totalInserts++;
				count++;
				totalProbes  += count;
				return;
			} else {
				if (hashTable[j].equals(hashObject)) {
					hashTable[j].increaseFrequency();
					totalDuplicates++;
					return;
				}
				i++;
				count++;
			}
		}
	}
}
