
/**
 * File: HashTableOverflowException.java
 * @author Mario Torres
 * Date: November 6, 2021
 * Description: Error to be thrown if hash table overflows
 */
@SuppressWarnings("serial")
public class HashTableOverflowException extends Exception {

	public HashTableOverflowException(String message) {
		super(message);
	}

}
