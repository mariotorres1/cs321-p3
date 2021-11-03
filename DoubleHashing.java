/**
 * Creates the hash function for double hashing of hash table
 * @author Mario Torres
 *
 * @param <T> Generic Type
 */
public class DoubleHashing<T> extends HashTable<T> {
	
	/**
	 * Hash table created using double hashing 
	 * @param tableSize
	 */
	public DoubleHashing(int tableSize) {
		super(tableSize);
	}
	
	/**
	 * Returns the index position of our table after implementing a double hashing hash function
	 */
	protected int hashFunction(HashObject<T> hashObject, int index) {
		return (positiveMod(positiveMod(hashObject.getKey(), getTableSize()) + index * (1 + positiveMod(hashObject.getKey(), getTableSize() - 2)), getTableSize()));
	}

}
