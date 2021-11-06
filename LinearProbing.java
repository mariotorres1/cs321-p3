/**
 * File: LinearProbing.java
 * @author Mario Torres
 * Date: October 25, 2021
 * Description: Creates the hash function for linear probing for a hash table
 * @param <T> Generic Type
 */
public class LinearProbing<T> extends HashTable<T> {

	/**
	 * Hash table created using linear probing
	 * @param tableSize - size of our hash table
	 */
	public LinearProbing(int tableSize) {
		super(tableSize);
	}
	
	/**
	 * Returns the index position of our table after implementing a linear probing hash function
	 */
	protected int hashFunction(HashObject<T> hashObject, int index) {
		return positiveMod((hashObject.getKey().hashCode() + index), getTableSize());
	}

}
