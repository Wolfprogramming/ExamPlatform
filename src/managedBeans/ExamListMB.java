package managedBeans;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;
import java.util.List;

import javax.inject.Named;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
//import javax.faces.context.ExternalContext;
//import javax.faces.context.FacesContext;

import beans.ExamBean;
import model.Exam;

@Named
@SessionScoped
public class ExamListMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Exam> allExams;
	private Exam selectedExam;
	private List<Exam> filteredExams;
	
	@EJB
	private ExamBean theExams;

		
	@PostConstruct
	public void init(){
		allExams = theExams.findAllExams();
	}

	public Exam getSelectedExam() {
		return selectedExam;
	}

	public void setSelectedExam(Exam selectedExam) {
		this.selectedExam = selectedExam;
	}

	
	public List<Exam> getfilteredExams() {
		return filteredExams;
	}

	public void setfilteredExams(List<Exam> filteredExams) {
		this.filteredExams = filteredExams;
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
	
	public List<Exam> getAllExams(){
		return allExams;
	}
	
	public String editExam(){ 
		//ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		//teach = (Teacher) ec.getSessionMap().get("user");
		System.out.println("enter editExam");
		Time startHour = new Time(selectedExam.geteStartHour().getTime());
		Time endHour = new Time(selectedExam.geteEndHour().getTime());
		Date eModify  = new Date(System.currentTimeMillis());
		
		//Exam tmp = new Exam(); 
		selectedExam.seteName(selectedExam.geteName());
		selectedExam.seteDesc(selectedExam.geteDesc());
		selectedExam.seteModifyDate(eModify);
		selectedExam.seteDate(selectedExam.geteDate());
		selectedExam.seteStartHour(startHour);
		selectedExam.seteEndHour(endHour);
		theExams.doUpdate(selectedExam);
		
		//ec.getSessionMap().put("exam", tmp);
		
		return "viewExam";
	}

    
}
