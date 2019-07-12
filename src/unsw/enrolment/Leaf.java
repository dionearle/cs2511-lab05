package unsw.enrolment;

public class Leaf implements Component {
	
	private String name;
	private int mark;
	private int max;
	
	public Leaf(String name, int mark, int max) {
		super();
		this.name = name;
		this.mark = mark;
		this.max = max;
	}

	@Override
	public String nameString() {
		return this.name;
	}

	@Override
	public int calculateAverageMark() {
		return this.mark;
	}
	
	@Override
	public int calculateSumMark() {
		return this.mark;
	}
	
	@Override
	public boolean isPassing() {
		if (this.mark >= 50) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean add(Component child) {
		return false;
	}

	@Override
	public boolean remove(Component child) {
		return false;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMark() {
		return mark;
	}

	@Override
	public Component getChild(String name) {
		return null;
	}

	@Override
	public void setMark(int mark) {
		this.mark = mark;
		
	}
	

}
