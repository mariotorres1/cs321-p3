/**
 * File: HashObject.java
 * @author Mario Torres
 * Date: October 25, 2021
 * Description: Creates a generic object. Methods to get information about the object created
 * @param <T> Generic Type
 */
public class HashObject<T> {
	private final Object key;
	private int frequencyCount;
	private int probeCount;
	
	/**
	 * Constructor - creates a HashObject
	 * @param key - Our key Object
	 */
	public HashObject(Object key) {
		this.key = key;
		this.frequencyCount = 0;
		this.probeCount = 0;
	}
	
	/**
	 * Gets and returns the key of our object
	 * @return key
	 */
	public Object getKey() {
		return key;
	}

	/**
	 * Gets and returns the frequency of the object
	 * @return frequencyCount
	 */
	public int getFrequencyCount() {
		return this.frequencyCount;
	}

	/**
	 * Gets and returns the probe count for the object
	 * @return probeCount
	 */
	public int getProbeCount() {
		return this.probeCount;
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
		return this.key + " " + this.frequencyCount + " " + this.probeCount;
	}
	
	/**
	 * Compares two Objects keys to see if they're the same
	 * @param hashObject - Object to compare against
	 * @return true if keys are the same, false if not
	 */
	public boolean equals(HashObject<T> hashObject) {
		return this.getKey().equals(hashObject.getKey());
	}
}
