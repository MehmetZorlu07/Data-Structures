import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;
import java.util.Random;

public class TestDataStructures {

	// readFile method that reads the integers in the file and puts them in an
	// array.
	public static int[] readFile(String fileName) throws Exception {

		// Number of lines counter.
		File file = new File(fileName);
		BufferedReader reader = new BufferedReader(new FileReader(file));
		int lines = 0;
		while (reader.readLine() != null)
			lines++;
		reader.close();

		// File to array of integers.
		Scanner sc = new Scanner(file);
		int array[] = new int[lines];
		int i = 0;
		while (sc.hasNextInt()) {
			array[i] = sc.nextInt();
			i++;
		}
		sc.close();
		return (array);
	}

	public static void main(String args[]) throws Exception {
		// Replace the fileName with the path and file name required to use a different
		// file.
		String fileName = "src/int20k.txt";

		// Setting up the dynamic sets.
		int fileNumbers[] = readFile(fileName);
		DoublyLinkedList testDLL = new DoublyLinkedList();
		BinarySearchTree testBST = new BinarySearchTree();

		// Inserting the integers into the sets.
		for (int number : fileNumbers) {
			testDLL.add(number);
			testBST.add(number);
		}

		// Random number generator
		int randomArray[] = new int[100];
		int i = 0;
		Random rand = new Random();
		while (i < randomArray.length) {
			int n = rand.nextInt(49999);
			randomArray[i] = n;
			i++;
		}

		// Testing the time for DLL
		double totalTime = 0;
		for (int number : randomArray) {
			double timeBefore = System.nanoTime();
			testDLL.isElement(number);
			double timeAfter = System.nanoTime();
			double timeTaken = (timeAfter - timeBefore) / 1000000;
			totalTime += timeTaken;
		}
		double avgTimeDLL = totalTime / 100;

		// Testing the time for BST
		totalTime = 0;
		for (int number : randomArray) {
			double timeBefore = System.nanoTime();
			testBST.isElement(number);
			double timeAfter = System.nanoTime();
			double timeTaken = (timeAfter - timeBefore) / 1000000;
			totalTime += timeTaken;
		}
		double avgTimeBST = totalTime / 100;

		System.out.println("Average running time of is element method of DLL:\n " + avgTimeDLL + "\n");
		System.out.println("Average running time of is element method of BST:\n " + avgTimeBST + "\n");
		double ratio = avgTimeDLL / avgTimeBST;
		System.out.println("Ratio of the two running time: " + ratio + "\n");

		System.out.println("Size of the BST set: " + testBST.setSize(testBST.root));
		System.out.println("Size of the DLL set: " + testDLL.setSize());

		System.out.println("\nHeight of the BST set: " + testBST.height(testBST.root));
	}
}
