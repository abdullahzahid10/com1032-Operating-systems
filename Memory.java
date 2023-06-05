package urn6636272;

import java.util.ArrayList;

// This class will be used to represent memory
public class Memory {
	int size;
	ArrayList<Hole> holes;

	//This constructor will initially set the size of
	//memory and the whose memory will be set as free space
	public Memory(int size) {
		this.size = size;
		holes = new ArrayList<Hole>();
		int cur = 0;
		int step = 500;
		int total = 0;
		for(int i = 0 ; i < 10; i++) {
			holes.add(new Hole(cur,cur+step));
			cur = cur + step + 1;
			total = cur + step + 1;
			if (i % 2 == 0) {
				if(i % 3 == 0) {
					step = 500/3;
				}else {
					step = 500/2;
				}
			} else {
				step = 500;
			}
		}
		holes.add(new Hole(cur, size-total));
	}

	//This method will start to take first size which is big enough
	//for the memory which is requested
	public int Allocate(int size) {
		Hole temp = null;
		int i=0;
		for (int e = 0; e < holes.size(); e++) {
			if(holes.get(e).isFree()) {
				if(holes.get(e).getSize() > size) {
					if(holes.get(e).getSize() - 1 == size) {
						holes.get(e).setUsed();
						return holes.get(e).getStart();
					}else {
						if(i == 0 && e != 0) {
							i = 1;
							continue;
						}
						Hole t = new Hole(holes.get(e).getStart(), holes.get(e).getStart() + size);
						Hole f = new Hole(holes.get(e).getStart() + size + 1, holes.get(e).getEnd());
						t.setUsed();
						temp = holes.get(e);
						holes.add(e, t);
						holes.add(e + 1, f);
						holes.remove(temp);
						return holes.get(e).getStart();
					}
				}
			}
		}
						return -1;
	}

	//In this method, the base will represent the base
	//address of the segment currently used by that
	//process so memory can be expanded and I used to size for
	//memory to allocate
	public int Allocate(int base, int size) {
		for (int e = 0; e < holes.size(); e++) {
			if(holes.get(e).getStart() == base) {
				if(holes.get(e).getEnd() + size == holes.get(e + 1).getEnd()) {
					holes.get(e).setRange(holes.get(e + 1).getStart(), holes.get(e + 1).getEnd());
					holes.remove(holes.get(e + 1));
					return 1;
				}else {
					holes.get(e).setRange(holes.get(e).getStart(), holes.get(e).getEnd() + size);
					holes.get(e + 1).setRange(holes.get(e).getEnd() + 1, holes.get(e + 1).getEnd());
					return 1;
				}	
			}

		}
		return -1;
	}

	//The deallocate method where the base is representing base
	//address of the segment which is used by that process so that
	//memory can be shrunk to leave the other space on the next
	//hole or create a new hole and size represents size of memory to deallocate
	public void deallocate(int base,int size) {
		Hole temp = null;
		for(int i = 0; i < holes.size(); i++) {
			if(holes.get(i).getStart() == base) {
				if(holes.get(i).getStart() + size == holes.get(i).getEnd()) {
					holes.get(i).setFree();
				}else {
					if(i + 1 < holes.size()) {
						if(holes.get(i + 1).isFree()) {
							holes.get(i).setRange(holes.get(i).getStart(), holes.get(i).getStart()+size);
							holes.get(i + 1).setRange(holes.get(i).getStart() + size + 1, holes.get(i + 1).getEnd());
						}else {
							Hole f=new Hole(holes.get(i).getStart() + size + 1, holes.get(i).getEnd());
							Hole t=new Hole(holes.get(i).getStart(), holes.get(i).getStart() + size);
							t.setUsed();
							temp=holes.get(i);
							holes.add(i, t);
							holes.add(i + 1, f);
							holes.remove(temp);

						}
					}
				}
			}
		}
	}
	
	//Display Memory
	public void displayMemory() {
		System.out.println("Memory size: " + size);
		for(int i = 0; i < holes.size(); i++) {
			System.out.println(holes.get(i).toString());
		}
	}
}

class Hole {

	// Base and Limit values
	private int start;
	private int end;
	private boolean free;

	//Default constructor used to set the size of the max space, and is also free
	Hole(int end){
		start = 0;
		this.end = end;
		free = true;
	}

	//start indicates start byte of the hole and
	//end indicates end byte of the hole
	Hole(int start, int end){
		this.start = start;
		this.end   = end;
		free = true;
	}
	public void setUsed() {
		free = false;
	}
	public boolean isFree() {
		return free;
	}

	public int getStart(){
		return this.start;
	}

	public int getEnd(){
		return this.end;
	}
	public void setFree() {
		free = true;
	}

	//This method will return size of the hole
	int getSize(){
		return (end - start) + 1;
	}

	//This method will set range of the hole for the block
	//start indicates start byte of the hole and
	//end indicates end byte of the hole
	public void setRange(int start, int end){
		this.start = start;
		this.end   = end;
	}
	public String toString() {
		String h = "Start: " + start + "  End: " + end + "  " + " Is Free: " + free;
		return h;
	}

}