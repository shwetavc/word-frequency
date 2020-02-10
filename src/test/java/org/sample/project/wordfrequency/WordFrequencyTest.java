package org.sample.project.wordfrequency;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Scanner;

import org.junit.BeforeClass;
import org.junit.Test;

public class WordFrequencyTest {
	protected static WordFrequency wordFrequency;
	protected static String seederFileName = "seederfile";
	
	@BeforeClass
	public static void loadMap() throws IOException {
		wordFrequency = new WordFrequency(seederFileName);
	}
	
	@Test
	public void testInit() throws IOException {
		assertEquals(Integer.valueOf(6), wordFrequency.wordFrequencyMap.get("half").get("inputfile5"));
	}
	
	@Test(expected = FileNotFoundException.class)
	public void testInitIncorrectSeederFile() throws Exception{
		WordFrequency wf = new WordFrequency("seederfileincorrect");
	}
	
	@Test
	public void testLoadWordFrequencyMapIncorectFile() throws IOException {
		WordFrequency wf = new WordFrequency("seederfile");
		wf.wordFrequencyMap=new HashMap<>();
		wf.loadWordFrequencyMap("nonexistingfile");
		assertEquals(true,wf.wordFrequencyMap.isEmpty());
	}
	
	@Test
	public void testLoadWordFrequencyMapIndividualFile() throws IOException {
		WordFrequency wf = new WordFrequency("seederfile");
		wf.wordFrequencyMap=new HashMap<>();
		wf.loadWordFrequencyMap("inputfile1");
		
		PrintStream out = new PrintStream(new FileOutputStream("test-output.txt"));
		System.setOut(out);
		
		wf.printWordFrequency();
		InputStream inputStream = new FileInputStream("test-output.txt");
		Scanner sc = new Scanner(inputStream);
		while(sc.hasNext()) {
			if(sc.nextLine().equals("half")) {
				assertEquals("total frequency -> 5", sc.nextLine());
			}
		}
		sc.close();
		out.close();
		File file = new File("test-output.txt");
		file.delete();
	}
	
	@Test
	public void testPrintWordFrequency() throws FileNotFoundException {
		PrintStream out = new PrintStream(new FileOutputStream("test-output.txt"));
		System.setOut(out);
		
		wordFrequency.printWordFrequency();
		InputStream inputStream = new FileInputStream("test-output.txt");
		Scanner sc = new Scanner(inputStream);
		while(sc.hasNext()) {
			if(sc.nextLine().equals("half")) {
				assertEquals("total frequency -> 21", sc.nextLine());
			}
		}
		sc.close();
		out.close();
		File file = new File("test-output.txt");
		file.delete();
	}
	
	@Test
	public void testPrintWordFrequencyEmptyWordFreqMap() throws IOException {
		PrintStream out = new PrintStream(new FileOutputStream("test-output.txt"));
		System.setOut(out);
		WordFrequency wf = new WordFrequency("seederfile");
		wf.wordFrequencyMap = null;
		wf.printWordFrequency();
		InputStream inputStream = new FileInputStream("test-output.txt");
		Scanner sc = new Scanner(inputStream);
		assertEquals(false,(sc.hasNext()));
		
		sc.close();
		out.close();
		File file = new File("test-output.txt");
		file.delete();
	}
	
	@Test
	public void testPrintWordFrequencyDetails() throws FileNotFoundException {
		PrintStream out = new PrintStream(new FileOutputStream("test-output.txt"));
		System.setOut(out);
		
		wordFrequency.printWordFrequencyDetails("half");
		InputStream inputStream = new FileInputStream("test-output.txt");
		Scanner sc = new Scanner(inputStream);
		boolean allAssertionDone = false;
		assertEquals(true, sc.next().contains("half"));
		while(sc.hasNext()) {
			String content = sc.nextLine();
			if(content.contains("inputfile3")) {
				assertEquals("frequency in inputfile3 -> 4",content);
				allAssertionDone = true;
			}
		}
		assertEquals(true, allAssertionDone);
		sc.close();
		out.close();
		File file = new File("test-output.txt");
		file.delete();
	}
	
	@Test
	public void testPrintWordFrequencyDetailsZero() throws FileNotFoundException {
		PrintStream out = new PrintStream(new FileOutputStream("test-output.txt"));
		System.setOut(out);
		
		wordFrequency.printWordFrequencyDetails("halfff");
		InputStream inputStream = new FileInputStream("test-output.txt");
		Scanner sc = new Scanner(inputStream);
		boolean allAssertionDone = false;
	
		while(sc.hasNext()) {
			String content = sc.nextLine();
			assertEquals("total frequency -> 0",content);
			allAssertionDone = true;
		}
		assertEquals(true, allAssertionDone);
		sc.close();
		out.close();
		File file = new File("test-output.txt");
		file.delete();
	}
	
	@Test
	public void testPrintWordFrequencyDetailsMapNull() throws IOException {
		PrintStream out = new PrintStream(new FileOutputStream("test-output.txt"));
		System.setOut(out);
		
		WordFrequency wf = new WordFrequency("seederFile");
		wf.wordFrequencyMap = null;
		wf.printWordFrequencyDetails("halfff");
		InputStream inputStream = new FileInputStream("test-output.txt");
		Scanner sc = new Scanner(inputStream);
		assertEquals(false, sc.hasNext());
		sc.close();
		out.close();
		File file = new File("test-output.txt");
		file.delete();
	}
	
}
