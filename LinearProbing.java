
public class LinearProbing<T> extends HashTable<T> {

	public LinearProbing(int tableSize) {
		super(tableSize);
	}
	
	protected int hashFunction(HashObject<T> hashObject, int index) {
		return positiveMod(hashObject.getKey() + index, getTableSize());
	}
	
//	public int linearHashSearch(T object) {
//		HashObject<T> hashObject = new HashObject<T>(object);
//		int i = 0;
//		int j = 0;
//		do {
//			j = linearHashFunction(hashObject, i);
//			if (hashTable[j] == null) {
//				return 0;
//			}
//			else if (hashTable[j].equals(hashObject)) {
//				totalDuplicates++;
//				hashTable[j].increaseFrequency();
//				return 1;
//			} else {
//				i++;
//			}
//		} while (i != getTableSize());
//		
//		return 0;
//	}

}
