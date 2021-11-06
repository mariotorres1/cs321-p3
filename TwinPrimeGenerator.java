

/**
 * File: TwinPrimeGenerator.java
 * @author Mario Torres
 * Date: October 25, 2021
 * Description: This class checks to see if a number is prime and then returns the second of 
 * 		twin prime numbers between a given range of integers
 */
public class TwinPrimeGenerator {
	
	/**
	 * isPrime - checks to see if a number is prime
	 * 
	 * @param number - number to be checked if prime
	 * @return true if number is prime, false if not
	 */
	public boolean isPrime(int number) {
		// makes sure the number passed isn't 0 or 1
		if (number == 0 || number == 1) {
			return false;
		}
		
		// checking to see if the number passed in is prime or not
		for (int i = 2; i <= Math.sqrt(number); i++) {
			// if has no remainder for any mod i value then number is not prime
			if (number % i == 0) {
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * getTwinPrime - Finds twin prime numbers within a given range of numbers. If no twin primes found,
	 * 		returns 0
	 * 
	 * @param start - beginning value of range
	 * @param end - end value of range
	 * @return the second of the twin prime values if a twin prime is found, 0 if no twin prime found
	 */
	public int getTwinPrime(int start, int end) {
		int firstPrime = 0;
		int secondPrime = 0;
		// Makes sure start is an odd number to begin
		if (start % 2 == 0) {
			start ++;
		}
		
		// Checks the odd numbers
		while (start <= end) {
			// Checks to see if prime
			if (isPrime(start)) {
				// stores first prime value
				firstPrime = start;
				// initializes the second prime to be 2 more than the first prime found
				secondPrime = firstPrime + 2;
				// checks to see if that value is actually prime
				if (isPrime(secondPrime)) {
					// returns the second of the twin prime numbers
					return secondPrime;
				} else {
					start += 2;
				}
			} else {
				start += 2;
			}
		}
		return 0;
	}

}
