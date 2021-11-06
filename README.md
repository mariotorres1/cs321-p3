# Project #: Project 3 Hash Table Playground

* Author: Mario Torres
* Class: CS321 Section #003
* Semester: Fall 2021

## Overview

Implements a hash table to store objects, uses two different 
hash fuctions, linear probing and double hashing. Compares 
the difference between the two hash functions by inserting differnt
data types into our hash table using both functions.

## Reflection

First time implementing a hash table so it was nice to see how to
do that. It was nice using inheritence as well and making a parent 
class in HashTable.java and only having the differences between 
linear probing and double hashing in to the child classes
LinearProbing.java and DoubleHashing.java. 

This was also the first time I have worked with debugging levels, 
so it was nice to implement those into my program as well. It's a 
cool way to see different outputs of our program to help see what
our data is actually doing. 

One thing that was giving me some trouble was the system time 
simulation. Everytime I used the Thread.sleep() method, I would get
an average probes more than 1.0 compared to not using the sleep method.
I would get a lot of duplicates that way but for every loadFactor, I 
was able to get an average probes of 1.0 like the expected result 
wanted. Everything else seemed to run and work correctly so I just left
the Thread.sleep() method off and had a bunch of duplicates on the 
system time simulation.

## Compiling and Using

This program takes in command line arguments to be run successfully.
The user should input an input type, load factor, and an optional
debug level.

java HashtableTest [<input type>] [<load factor>] [<debug level>]
    input type = 1 for random numbers, 2 for system time, 3 for word list
    debug = 0 ==> print summary of experiment
    debug = 1 ==> save the two hash tables to a file at the end
    debug = 2 ==> print debugging output for each insert

## Results 

Input source 1: random number

|alpha  |  linear  |  double  |
|-------|----------|----------|
|0.5    | 1.502    | 1.386    |
|0.6    | 1.751    | 1.525    |
|0.7    | 2.183    | 1.718    |
|0.8    | 2.951    | 2.009    |
|0.9    | 5.443    | 2.553    |
|0.95   | 11.235   | 3.152    |
|0.98   | 22.351   | 4.051    |
|0.99   | 45.784   | 4.671    |


Input source 2: current time

|alpha  |  linear  |  double  |
|-------|----------|----------|
|0.5    | 1.0      | 1.0      |
|0.6    | 1.0      | 1.0      |
|0.7    | 1.0      | 1.0      |
|0.8    | 1.0      | 1.0      |
|0.9    | 1.0      | 1.0      |
|0.95   | 1.0      | 1.0      |
|0.98   | 1.0      | 1.0      |
|0.99   | 1.0      | 1.0      |


Input source 3: word-list

|alpha  |  linear  |  double  |
|-------|----------|----------|
|0.5    | 1.597    | 1.390    |
|0.6    | 2.149    | 1.534    |
|0.7    | 3.604    | 1.721    |
|0.8    | 6.708    | 2.016    |
|0.9    | 19.815   | 2.569    |
|0.95   | 110.594  | 3.186    |
|0.98   | 324.206  | 4.020    |
|0.99   | 471.671  | 4.696    |

## Sources used

I mainly used the textbook for the course, Introduction To Algorithms
and my notes taken from class to go over the insert method for a 
hash table as well as the hash functions for both linear probing and 
double hashing. I looked up the technique to see if a number is prime
on google then converted that into java code in my TwinPrimeGenerator.
java class. I went back and forth and how to do my HashtableTest.java 
class, I was deciding between making private methods within the class
to use in my main so it wouldn't be so clustered or to just implement
everything inside of main. I ended up going with the creating private
methods approach similar to my Project 1 so I also referenced that 
assignment as well. 

------------------------------------------------------------------------
