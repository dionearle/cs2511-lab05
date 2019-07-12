package unsw.enrolment;

public interface Component {
	
	public String nameString();
	public boolean isPassing();
	public int calculateAverageMark();
	public int calculateSumMark();
	
	public boolean add(Component child);
	public boolean remove(Component child);
	Component getChild(String name);
	
	public String getName();
	public void setName(String name);
	public int getMark();
	public void setMark(int mark);
}
