package unsw.enrolment;

import java.util.ArrayList;

public class Composite implements Component {
	
	private String name;
	private int mark;
	private int max;
	
	ArrayList<Component> children = new ArrayList<Component>();

	public Composite(String name, int mark, int max) {
		super();
		this.name = name;
		this.mark = mark;
		this.max = max;
	}

	@Override
	public String nameString() {
		String answer = "[" + this.getName()  + " "; 
		for(Component c : children) {
			answer = answer + " " + c.nameString();
		}	
		answer = answer + "]";
		return answer;
	}

	@Override
	public int calculateAverageMark() {
		int average = 0;
		int i = 0;
		for(Component c : children) {
			average += c.getMark();
			i++;
		}
		average = average / i;
		this.setMark(average);
		return average;
	}
	
	@Override
	public int calculateSumMark() {
		int answer = 0;
		for(Component c : children) {
			answer += c.getMark();
		}
		this.setMark(answer);
		return answer;
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
	public Component getChild(String name) {
		for(Component c : children) {
			if (name == c.getName()) {
				return c;
			}
			if (c instanceof Composite) {
				c.getChild(name);
			}
		}
		
		return null;
	}

	@Override
	public boolean add(Component child) {
		children.add(child);
		return true;
	}

	@Override
	public boolean remove(Component child) {
		children.remove(child);
		return true;
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

	public void setMark(int mark) {
		this.mark = mark;
	}
	
	

}
