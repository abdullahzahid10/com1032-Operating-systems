package urn6636272;

import java.util.ArrayList;

public class SegmentTable {
	private ArrayList<Segment> table;
	public SegmentTable() {
		table = new ArrayList <Segment>();
	}
	public void addSegment(Segment seg) {
		table.add(seg);
	}
	public ArrayList <Segment> getTable() {
		return table;
	}
	public Segment getSegment(int base) {
		for(int i=0; i < table.size(); i++) {
			if(base == table.get(i).getBase()) {
				return table.get(i);
			}
		}
		return null;	
	}

	public Segment getSegmentbyNum(int num) {
		if(num > table.size()) {
			return null;
		}else {
			return table.get(num-1);
		}
	}
	public void removeSegment(Segment seg) {
		table.remove(seg);
	}
	public void ArrangeSegment() {
		for(int i = 0;i < table.size(); i++) {
			table.get(i).setSegNum(i+1);
		}
	}
	public void displayTable() {
		for(Segment i:table) {
			System.out.println(i.toString());
		}
	}
}