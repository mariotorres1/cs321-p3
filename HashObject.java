/**
 * Creates a generic object. Methods to get important information of object. 
 * @author Mario Torres
 *
 * @param <T> Generic Type
 */
public class HashObject<T> {
	Object object;
	private int key;
	private int frequencyCount;
	private int probeCount;
	
	/**
	 * Constructor - creates a HashObject
	 * @param key - Our key Object
	 * @param frequencyCount - How many times this object has appeared
	 * @param probeCount - How many times the object looked for a space in our hashTable
	 */
	public HashObject(Object object) {
		this.object = object;
		this.key = object.hashCode();
		this.frequencyCount = 0;
		this.probeCount = 0;
	}
	
	/**
	 * Gets and returns the key of our object
	 * @return key
	 */
	public int getKey() {
		return key;
	}
	
	/**
	 * Gets the type of our object
	 * @return object
	 */
	public Object getObject() {
		return object;
	}
	
	/**
	 * Increases the frequency of our Object
	 */
	public void increaseFrequency() {
		this.frequencyCount++;
	}
	
	/**
	 * Increases the probes of a certain object
	 */
	public void increaseObjectProbe() {
		this.probeCount++;
	}
	
	/**
	 * Prints information of our Object
	 */
	public String toString() {
		String retVal = this.object + " " + this.frequencyCount + " " + this.probeCount;
		return retVal;
	}
	
	/**
	 * Compares two Objects keys to see if they're the same
	 * @param hashObject - Object to compare against
	 * @return true if keys are the same, false if not
	 */
	public boolean equals(HashObject<T> hashObject) {
		if (this.getObject().equals(hashObject.getObject())) {
			return true;
		} else {
			return false;
		}
	}
}
