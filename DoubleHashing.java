
public class DoubleHashing<T> extends HashTable<T> {

	public DoubleHashing(int tableSize) {
		super(tableSize);
		// TODO Auto-generated constructor stub
	}
	
	protected int hashFunction(HashObject<T> hashObject, int index) {
		return (positiveMod(positiveMod(hashObject.getKey(), getTableSize()) + index * (1 + positiveMod(hashObject.getKey(), getTableSize() - 2)), getTableSize()));
	}
	
//	public int doubleHashingSearch(T object) {
//		HashObject<T> hashObject = new HashObject<T>(object);
//		int i = 0;
//		int j = 0;
//		do {
//			j = doubleHashingFunction(hashObject, i);
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
