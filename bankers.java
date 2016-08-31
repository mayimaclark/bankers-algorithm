
package the.bankers.algorithm;

import java.util.Scanner;

public class Bankers {

	private static int need[][];
	private static int allocated_resources[][];
	private static int max[][];
	private static int available_resources[][];
	private static int num_processes;
	private static int num_resources;

	public static void main(String[] args) {
		checkSafety();
	}

	private static void getInput() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter no. of processes: ");
		
		num_processes = scanner.nextInt(); // no. of process
		System.out.print("Enter no. of resources: ");

		num_resources = scanner.nextInt(); // no. of resources

		// initializing arrays
		need = new int[num_processes][num_resources];
		max = new int[num_processes][num_resources];
		allocated_resources = new int[num_processes][num_resources];
		available_resources = new int[1][num_resources];

		// Get Allocation input
		System.out.println("Enter allocation matrix -->");
		for (int i = 0; i < num_processes; i++) {
			System.out.println("Row " + (i + 1) + " (P" + i + "): ");
			for (int j = 0; j < num_resources; j++) {
				allocated_resources[i][j] = scanner.nextInt(); // allocation matrix
			}
		}

		// Get Max Matrix input
		System.out.println("Enter max matrix -->");
		for (int i = 0; i < num_processes; i++) {
			System.out.println("Row " + (i + 1) + " (P" + i + "): ");
			for (int j = 0; j < num_resources; j++) {
				max[i][j] = scanner.nextInt(); // max matrix
			}
		}

		// Get available matrix input
		System.out.println("Enter available matrix -->");
		for (int j = 0; j < num_resources; j++)
			available_resources[0][j] = scanner.nextInt(); // available matrix
		
		scanner.close();

		// Display matrices
		System.out.println("\nAllocation Matrix:");
		printArray(allocated_resources);
		System.out.println("\nMax Matrix:");
		printArray(allocated_resources);
		System.out.println("\nNeed Matrix:");
		printArray(calc_need());
	}
	static void printArray(int[][] array) {
		for (int i = 0; i < array.length; i++) {
			System.out.println("");
			for (int j = 0; j < array[0].length; j++) {
				System.out.print(array[i][j] + " ");
			}
		}
		System.out.println("");
	}

	//
	private static int[][] calc_need() {
		for (int i = 0; i < num_processes; i++)
			for (int j = 0; j < num_resources; j++)
				// calculating need matrix
				need[i][j] = max[i][j] - allocated_resources[i][j];
		return need;
	}

	private static boolean check_allocatable(int i) {
		// checking if all resources for ith process can be allocated
		for (int j = 0; j < num_resources; j++)
			if (available_resources[0][j] < need[i][j])
			return false;
		return true;
	}

	public static void checkSafety() {
		getInput();
		calc_need();
		boolean done[] = new boolean[num_processes];
		int j = 0;

		while (j < num_processes) { // until all process allocated
		boolean allocated = false;
		for (int i = 0; i < num_processes; i++)
			if (!done[i] && check_allocatable(i)) { // trying to allocate

				for (int k = 0; k < num_resources; k++)
					available_resources[0][k] = available_resources[0][k]
						- need[i][k] + max[i][k];
				System.out.println("Allocated process : " + i);
				allocated = done[i] = true;
				j++;
			}
			if (!allocated) // no allocation
				break;
		}
		if (j == num_processes) // all processes are allocated
			System.out.println("\nSAFE STATE!");
		else
			System.out.println("DEADLOCK!");
	}
}
