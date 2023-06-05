package urn6636272;

public class Driver {
	//Here is the process and required memory
	private static int[][] allocation = { 
		{1, 200, 100,10},
		{1, 200, -40,-10},
		{2, 10, 50,20},	
		{2, 20,  -50,200}
	};

	//This is the main method, This method will be used to create the object of Memory Management
	public static void main(String args[]) {
		System.out.println("Start A.1");
		MemoryManagement memorymanagementt = new MemoryManagement(4048);
		memorymanagementt.Allocate(allocation);
		System.out.println("End A.1");
	}
}