/**
 * File: DoubleHashing.java
 * @author Mario Torres
 * Date: October 25, 2021
 * Description: Creates the hash function for double hashing for a hash table
 * @param <T> Generic Type
 */
public class DoubleHashing<T> extends HashTable<T> {
	
	/**
	 * Hash table created using double hashing
	 *  
	 * @param tableSize
	 */
	public DoubleHashing(int tableSize) {
		super(tableSize);
	}
	
	/**
	 * primaryHash - Returns the primary hash of the double hashing hash function
	 * 
	 * @param hashObject - Object used to retrieve hashCode used
	 * @return int location of the primary hash
	 */
	private int primaryHash(HashObject<T> hashObject) {
		return positiveMod(hashObject.getKey().hashCode(), getTableSize());
	}
	
	/**
	 * secondaryHash - Returns the secondary hash of the double hashing hash function
	 * 
	 * @param hashObject - Object used to retrieve hashCode used
	 * @return int location of the secondary hash
	 */
	private int secondaryHash(HashObject<T> hashObject) {
		return (1 + positiveMod(hashObject.getKey().hashCode(), getTableSize() - 2));
	}
	
	/**
	 * Returns the index position of our table after implementing a double hashing hash function. Uses both primaryHash and secondaryHash along with given index
	 */
	protected int hashFunction(HashObject<T> hashObject, int index) {
		return positiveMod(primaryHash(hashObject) + (index * secondaryHash(hashObject)), getTableSize());
	}

}
