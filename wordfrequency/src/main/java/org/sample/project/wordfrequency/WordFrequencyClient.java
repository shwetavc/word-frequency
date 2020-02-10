package org.sample.project.wordfrequency;

import java.io.IOException;
import java.util.Scanner;

/**
 * This is a client class for Word Frequency application
 * 
 * @author Shweta
 */
public class WordFrequencyClient {

	/**
	 * main method is a user interaction client for Word Frequency.
	 * 
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		WordFrequency wordFrequency = new WordFrequency("seederfile");
		
		// Interact with user to perform operations
		Scanner sc = null;
		try {
			while(true){
	            System.out.println();
	            System.out.println("Available operations:");
	            System.out.println("1. Print all words frequency");
	            System.out.println("2. Print frequency details of a word");
	            System.out.println("3. Exit");
	            System.out.println("Enter option # to perform operation:");
	            sc = new Scanner(System.in);
	            
	            int i = 0;
	            try {
	            	i = sc.nextInt();
	            }catch(Exception e) {
	            	// do nothing, as we want to ignore incorrect user input 
	            	// and send message from default case below (as i=0 by default)
	            }
	            
			    switch(i){
	                case 1:
	                	wordFrequency.printWordFrequency();
	                    break; 
	                case 2:
	                    System.out.println("Enter word to get frequency details: ");
                    	sc = new Scanner(System.in);
                        String word = sc.next();
                        wordFrequency.printWordFrequencyDetails(word);
	                    break;
	                case 3:
	                	System.exit(0);
	                default:
	                	System.out.println("Invalid value, please enter from available choices only!");
	                	break;
	            }
	        }
		} finally {
			if(sc!=null) {
				sc.close();
			}
		}
	}
}
