package unsw.enrolment;

public interface Subject {

		public void registerObserver(Observer o);
		public void removeObserver(Observer o);
		void notifyObservers(String assessment, int mark);
}
