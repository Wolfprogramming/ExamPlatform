package managedBeans;

import java.io.Serializable;
import java.util.Date;
import java.sql.Time;
import java.util.List;

import javax.inject.Named;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import beans.ExamBean;
import model.Exam;
import model.Question;
import model.Student;
import model.Teacher;

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
	private List<Exam> upcomingExams;
	private List<Exam> previousExams;
	private int nbPoint;
	private List<Question> questions;

	
	@EJB
	private ExamBean theExams;

		
	@PostConstruct
	public void init(){
		FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        
        Date date = new Date();
        
        if(externalContext.getSessionMap().get("teacher") != null){
        	System.out.println("Retrieve teacher's exams");
        	Teacher t = (Teacher) externalContext.getSessionMap().get("teacher");
        	
        	previousExams = theExams.findAllExamsBefore(t, date);
        	upcomingExams = theExams.findAllExamsAfter(t, date);
        }
        else if(externalContext.getSessionMap().get("student") != null){
        	System.out.println("Retrieve student's exams");
        	Student s = (Student) externalContext.getSessionMap().get("student");
        	
        	previousExams = theExams.findAllExamsBefore(s, date);
        	upcomingExams = theExams.findAllExamsAfter(s, date);
        }
	}
	
	
	public Exam getSelectedExam() {
		return selectedExam;
	}

	public void setSelectedExam(Exam selectedExam) {
		this.selectedExam = selectedExam;
		if(selectedExam!=null){
			theExams.doUpdate(selectedExam);
			setQuestions(selectedExam.getQuestions());
		}
	}

	
	public List<Exam> getfilteredExams() {
		return filteredExams;
	}

	public void setfilteredExams(List<Exam> filteredExams) {
		this.filteredExams = filteredExams;
	}


	public int getNbPoint() {
		return nbPoint;
	}
	
	public void setNbPoint(int pt){
		this.nbPoint = pt;
	}


	public List<Exam> getUpcomingExams() {
		return upcomingExams;
	}

	public void setUpcomingExams(List<Exam> upcomingExams) {
		this.upcomingExams = upcomingExams;
	}

	public List<Exam> getPreviousExams() {
		
		return previousExams;
	}

	public void setPreviousExams(List<Exam> previousExams) {
		this.previousExams = previousExams;
	}

	public List<Exam> getAllExams(){
		return allExams;
	}
	
	public void setAllExams(List<Exam> allExams){
		this.allExams = allExams;
	}

	public List<Question> getQuestions() {
		this.nbPoint=0;
		for(int i=0; i<questions.size();i++){
			this.nbPoint += questions.get(i).getqValue();
		}
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}
	
	public void clear(){
		FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        Date date = new Date();
        
		theExams.doUpdate(selectedExam);
		this.previousExams.clear();
		this.upcomingExams.clear();
		
		if(externalContext.getSessionMap().get("teacher") != null){
        	System.out.println("Retrieve teacher's exams");
        	Teacher t = (Teacher) externalContext.getSessionMap().get("teacher");
        	
        	previousExams = theExams.findAllExamsBefore(t, date);
        	upcomingExams = theExams.findAllExamsAfter(t, date);
        }
        else if(externalContext.getSessionMap().get("student") != null){
        	System.out.println("Retrieve student's exams");
        	Student s = (Student) externalContext.getSessionMap().get("student");
        	
        	previousExams = theExams.findAllExamsBefore(s, date);
        	upcomingExams = theExams.findAllExamsAfter(s, date);
        }
		
		setSelectedExam(null);
		setNbPoint(0);
	}
	
	public String editExam(){
		System.out.println("enter editExam");
		
		Time startHour = new Time(selectedExam.geteStartHour().getTime());
		Time endHour = new Time(selectedExam.geteEndHour().getTime());
		Date eModify  = new Date(System.currentTimeMillis());
		
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
	
	public String deleteExam(){
		System.out.println("Enter delete exam: " + selectedExam.geteName());
		
		allExams.remove(getSelectedExam());
		theExams.doRemove(selectedExam.getIdExam());
		
		System.out.println("Exam deleted");
		
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exam deleted",  "Exam deleted with success!");  
		FacesContext.getCurrentInstance().addMessage(null, message);
		
		return "index";
	}



    
}
