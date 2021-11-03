import java.util.Arrays;

public abstract class HashTable<T> {
	protected HashObject<T>[] hashTable;
	private int tableSize;
	protected int totalProbes;
	protected int totalInserts;
	protected int totalDuplicates;
	
	@SuppressWarnings("unchecked")
	public HashTable(int tableSize) {
		hashTable = (HashObject<T>[]) new HashObject[tableSize];
		this.tableSize = tableSize;
		this.totalProbes = 0;
		this.totalInserts = 0;
	}
	
	public int getTableSize() {
		return tableSize;
	}
	
	public int getTotalProbes() {
		return totalProbes;
	}
	
	public int getTotalInserts() {
		return totalInserts;
	}
	
	public int getTotalDuplicates() {
		return totalDuplicates;
	}
	
	public HashObject<T>[] getHashTable() {
		return hashTable;
	}
	
	public HashObject<T> getObject(int index) {
		return hashTable[index];
	}
	
	public boolean isEmpty() {
		return totalInserts == 0;
	}
	
	public String toString() {
		return Arrays.toString(hashTable);
	}
	
	/**
	 * positiveMod - makes sure the key value is always positive
	 * @param dividend
	 * @param divisor
	 * @return A positive value if our key is negative
	 */
	protected int positiveMod (int dividend, int divisor) {
		int value = dividend % divisor;
		
		if (value < 0) {
			value += divisor;
		}
		return value;
	}
	
	protected abstract int hashFunction(HashObject<T> hashObject, int index);
	
	public void hashInsert(T object) {
		HashObject<T> hashObject = new HashObject<T>(object);
		int i = 0;
		while (i != tableSize) {
			int j = hashFunction(hashObject, i);
			hashObject.increaseObjectProbe();
			totalProbes++;
			if (hashTable[j] == null) {
				hashTable[j] = hashObject;
				totalInserts++;
				return;
			} else {
				i++;
			}
		}
	}
	
	public boolean isDuplicate(T object) {
		HashObject<T> hashObject = new HashObject<T>(object);
		int i = 0;
		while (i != tableSize) {
			int j = hashFunction(hashObject, i);
			if (hashTable[j] == null) {
				return false;
			}
			else if (hashTable[j].equals(hashObject)) {
				totalDuplicates++;
				totalProbes++;
				hashTable[j].increaseFrequency();
				return true;
			} else {
				i++;
			}
		}
		return false;
	}
}
