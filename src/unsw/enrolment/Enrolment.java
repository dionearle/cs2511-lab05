package unsw.enrolment;

import java.util.ArrayList;
import java.util.List;

public class Enrolment implements Subject {

    private CourseOffering offering;
    private Component grade;
    private Student student;
    private List<Session> sessions;
    private ArrayList<Observer> listObservers = new ArrayList<Observer>();

    public Enrolment(CourseOffering offering, Student student, Session... sessions) {
        this.offering = offering;
        this.student = student;
        this.grade = new Composite("Grade",0, 100);
        student.addEnrolment(this);
        offering.addEnrolment(this);
        this.sessions = new ArrayList<>();
        for (Session session : sessions) {
            this.sessions.add(session);
        }
    }
    
    @Override
    public void registerObserver(Observer o) {
		if(! listObservers.contains(o)) { listObservers.add(o); }
	}

	@Override
	public void removeObserver(Observer o) {
		listObservers.remove(o);
	}

	@Override
	public void notifyObservers(String assessment, int mark) {
		for(Observer obs : listObservers) {
			obs.update(this, assessment, mark);
		}
	}
    
    public Course getCourse() {
        return offering.getCourse();
    }
    
    public String getCourseName() {
        return offering.getCourseName();
    }
    
    public String getStudentName() {
        return student.getZID();
    }

    public String getTerm() {
        return offering.getTerm();
    }

    public boolean hasPassed() {
        return grade.isPassing();
    }
    
    public int getGrade() {
    	return grade.getMark();
    }
    
    public void setGrade() {
    	int result = grade.calculateSumMark();
    	notifyObservers(grade.getName(), result);
    }
    
    public int getAverageGrade(String name) {
    	Component parent = grade.getChild(name);
    	int result = parent.calculateAverageMark();
    	notifyObservers(parent.getName(), result);
        return result;
    }
    
    public int getSumGrade(String name) {
    		Component parent = grade.getChild(name);
    		int result = parent.calculateSumMark();
    		notifyObservers(parent.getName(), result);
    		return result;
    }
    
    public void addGrade(String name, int mark, int max, String type, String parent) {
    	
    	Component newMark;
    	
    	if (type == "leaf") {
    		newMark = new Leaf(name, mark, max);
    	} else {
    		newMark = new Composite(name, mark, max);
    	}
    	
    	if (parent != "none") {
    		Component newParent = grade.getChild(parent);
    		newParent.add(newMark);
    	} else {
    		grade.add(newMark);
    	}
    	
    	// every time we add a new grade we should recalculate the grade
    	grade.calculateSumMark();
    	notifyObservers(newMark.getName(), newMark.getMark());
    }
    
    public void addChild(String parent, String child, String type) {
    	
    	Component newParent = grade.getChild(parent);
    	Component newChild = grade.getChild(child);
    	newParent.add(newChild);
    	grade.remove(newChild);
    	
    	if (type == "sum") {
    		this.getSumGrade(parent);
    	} else {
    		this.getAverageGrade(parent);
    	}
    }
    
    public String printGrade() {
    	return grade.nameString();
    }

}
