package urn6636272;

public class Process {


	private int Reference_number;
	private SegmentTable table;

	//Here I included a refNum to identify the process, and table will include segment table of the process
	public Process(int ref){
		this.Reference_number = ref;
		table = new SegmentTable();

	}

	//In this method segNum is the segment number which will be updated
	//base is the base address of the segment in memory
	//limit is the size of segment, either to allocate or deallocate and
	//is limit of segment from the base
	public int upDateSegement(int segNum,int base, int limit) {
		Segment seg = null;
		if(segNum == -1) {

			seg = new Segment(table.getTable().size() +1, base, limit);
			System.out.println("Successfully allocated the memory");
			table.addSegment(seg);
			System.out.println(seg.toString());
			return 1;
		}else {
			seg = table.getSegmentbyNum(segNum);
		}
		if(seg == null && limit > 0) {
			System.out.println("Allocating memory for new segment " + segNum);
			seg = new Segment(table.getTable().size()+1, base, limit);
			System.out.println("Successfully allocated the memory");
			table.addSegment(seg);
			System.out.println(seg.toString());
			return 1;
		}
		int res;
		if(limit < 0) {
			System.out.println("DeAllocating "+ limit + " for "+ seg.toString());
			res = seg.dealocate(limit);
			if(res == 0) {
				System.out.println("Removing Segment "+ seg.getSegNum());
				table.removeSegment(seg);
				return 1;
			}else if(res ==-1) {
				System.out.println("Unable to Dealocate the memory");
				return -1;
			}else {
				System.out.println(seg.toString());
				return 1;
			}
		}else {
			System.out.println("Allocating " + limit + " for " + seg.toString());
			res = seg.allocate(base, limit);
			if(res == -1) {
				System.out.println("Unable to Alocate the memory");
				return -1;
			}else {
				System.out.println(seg.toString());
				return 1;
			}
		}
	}


	//In this method segNum is the segment number to get the segment base address
	//and -1will be returned if the segment is not in the table or the base address

	public int getSegmentBaseAddress(int segNum) {
		if(segNum <= table.getTable().size()) {
			return table.getTable().get(segNum-1).getBase();
		}else {
			return -1;
		}
	}
	public void displaySegmentTable() {
		System.out.println("Segments for Process P" + Reference_number);
		table.displayTable();
	}

	public int getReference_number() {
		return this.Reference_number;
	}
}