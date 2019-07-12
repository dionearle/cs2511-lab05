package unsw.enrolment;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MarkLogger implements Observer {
	
	Subject subject;
	String course;
	String term;
	String student;
	String assessment;
	int mark;

	@Override
	public void update(Subject obj, String assessment, int mark) {
		this.assessment = assessment;
		this.mark = mark;
		if (obj instanceof Enrolment) {
			update((Enrolment) obj);
		}
	}
	
	public void update(Enrolment obj) {
		this.course = obj.getCourseName();
		this.term = obj.getTerm();
		this.student = obj.getStudentName();
		logMarks();
	}
	
	public void logMarks() {
		// append all gathered information to the file
		String date = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
		String textToAppend = date + ' ' + assessment + ' ' + mark;
	    String filename = course + '-' + term + '-' + student;
	    FileWriter fileWriter;
		try {
			fileWriter = new FileWriter(filename, true);
			PrintWriter printWriter = new PrintWriter(fileWriter);
		    printWriter.println(textToAppend);
		    printWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
