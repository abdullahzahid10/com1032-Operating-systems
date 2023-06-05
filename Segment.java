package urn6636272;

//The segment class job will be to hold the base and limit of the memory
public class Segment {
	private int segmentNum;
	private int base;
	private int limit;

	//Default Constructor will set the segment number, base and the limit
	public Segment(int segmentNum, int base, int limit) {
		this.base = base;
		this.limit = limit;
		this.segmentNum  = segmentNum;
	}
	
	//Here, the size of the memory will be removed from the segment
	//If there is still some memory, '1' will be returned after deallocation, however '0' will be returned if there is zero memory
	//Segment should be removed and will return -1 if an invalid memory arises.
	public int dealocate(int size) {
		if(limit + size < 0) {
			return -1;
		}else {
			limit = limit + size;
			if (limit == 0) {
				return 0;
			}else {
				return 1;
			}
		}
	}
	
	//Here, the base is the starting address of the segment and size is the limit of segment
	//'1' will be returned which means the operation has been completed.
	public int allocate(int base,int size) {
		this.base = base;
		limit += size;
		return 1;
	}
	public int getBase() {
		return base;
	}
	public int getLimit() {
		return limit;
	}
	public void setSegNum(int se) {
		this.segmentNum = se;
	}
	public int getSegNum() {
		return segmentNum;
	}
	public String toString() {
		String st = "Segment No: " + segmentNum + "  Segment size: " + limit + "  Base: " + base + "  Limit: " + limit;
		return st;
	}
}