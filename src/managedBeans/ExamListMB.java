package managedBeans;

import java.io.Serializable;
import java.util.List;

import javax.inject.Named;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;

import beans.ExamBean;
import model.Exam;

@Named
@SessionScoped
public class ExamListMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@EJB
	private ExamBean theExams;
	
	private Exam selectedExam;
	
	public List<Exam> getTheExams(){
		return theExams.findAllExams();
	}

	public Exam getSelectedExam() {
		return selectedExam;
	}

	public void setSelectedExam(Exam selectedExam) {
		this.selectedExam = selectedExam;
	}

	public int getNbPoint() {
		int nbPoint;
		try{
			nbPoint = theExams.findNbPoint(selectedExam);
		}catch(Exception e){
			nbPoint = 0;
		}
		return nbPoint;
	}

    
}
