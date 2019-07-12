package com.fuzzySpell;

import java.util.Scanner;

public class SpellCheckerApplication {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
	
		// You can change your file path accordingly.
		MySpellChecker spellChecker = new MySpellChecker("C:\\OxygenWorkspace\\SocialQA\\my_dictionary.txt");
		
		System.out.println("Enter the search keyword....");
		
		// Reading Input from User
		String line = "what is computer ";
		
		System.out.println("Before Correction : "+line);
		
		// Method Invocation for Spelling Correction
		line = spellChecker.doCorrection(line.toLowerCase());

		System.out.println("After Correction : "+line);
		
		scanner.close();
		
	}
	/**
	 * 
	 * @param Mohan
	 * @return
	 */
	
public static String spellCheck(String line) {
		
		Scanner scanner = new Scanner(System.in);
	
		// You can change your file path accordingly.
		MySpellChecker spellChecker = new MySpellChecker("C:\\OxygenWorkspace\\SocialQA\\my_dictionary.txt");
		
//		System.out.println("Enter the search keyword....");
		
		// Reading Input from User
//		String line = scanner.nextLine();
		
		System.out.println("Before Correction : "+line);
		
		// Method Invocation for Spelling Correction
		line = spellChecker.doCorrection(line.toLowerCase());

		System.out.println("After Correction : "+line);
		
		
		scanner.close();
		
		return line;
		
	}


}
