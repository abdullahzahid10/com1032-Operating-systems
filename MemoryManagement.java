package urn6636272;

import java.util.ArrayList;

public class MemoryManagement {
	private Memory mem;
	private ArrayList<Process> processes;

	public MemoryManagement(int size) {
		mem = new Memory(size);
		processes=new ArrayList<Process>();
	}
	public void Allocate(int [][] aloccate) {
		Process temp;
		int base; 
		int res;
		for(int i=0;i < aloccate.length; i++) {
			System.out.print("Input ");
			for(int e = 0; e < aloccate[i].length; e++) {
				System.out.print(aloccate[i][e] + " ");
			}
			System.out.println();
			temp=getProcess(aloccate[i][0]);
			if(temp == null) {
				temp = new Process(aloccate[i][0]);	
				processes.add(temp);
			}

			for(int e = 1; e < aloccate[i].length; e++) {
				if(aloccate[i][e] >= 0) {
					base = temp.getSegmentBaseAddress(e);
					if(base == -1) {
						base = mem.Allocate(aloccate[i][e]);
					}else {
						base = mem.Allocate(base,aloccate[i][e]);
					}
					if(base == -1) {
						System.out.println("Unable to allocate the memory requested of "+ aloccate[i][e]+ " On segment " + e + " On process "+ temp.getReference_number());
					}else {
						res = temp.upDateSegement(e, base, aloccate[i][e]);
						if(res == -1) {
							mem.deallocate(base, aloccate[i][e]);
						}
					}

				}else {

					base = temp.getSegmentBaseAddress(e);
					if(base == -1) {
						System.out.println("Unable to dellocate the memory requested of "+ aloccate[i][e]+ " On segment " + e + " On process "+ temp.getReference_number());
					}else {
						mem.deallocate(base, -1 * aloccate[i][e]);
						res = temp.upDateSegement(e, base, aloccate[i][e]);
					}

				}
			}
			temp.displaySegmentTable();
			mem.displayMemory();
		}
		System.out.println("Done\n Diplaying the segment tables of all process and Memory");
		for(Process i : processes) {
			i.displaySegmentTable();
		}
		mem.displayMemory();
	}
	public Process getProcess(int ref) {
		for(Process i : processes) {
			if(i.getReference_number() == ref) {
				return i;
			}
		}
		return null;
	}
}