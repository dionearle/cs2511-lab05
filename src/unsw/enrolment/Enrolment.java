package unsw.enrolment;

import java.util.ArrayList;
import java.util.List;

public class Enrolment {

    private CourseOffering offering;
    private Component grade;
    private Student student;
    private List<Session> sessions;

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

    public Course getCourse() {
        return offering.getCourse();
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
    	grade.calculateSumMark();
    }
    
    public int getAverageGrade(String name) {
    	Component parent = grade.getChild(name);
        return parent.calculateAverageMark();
    }
    
    public int getSumGrade(String name) {
    		Component parent = grade.getChild(name);
    		return parent.calculateSumMark();
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
    
    public void removeGrade(Component child) {
    	
    	grade.remove(child);
    }

}
