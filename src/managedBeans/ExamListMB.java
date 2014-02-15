package managedBeans;

import java.io.Serializable;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.sql.Time;
import java.util.List;

import javax.inject.Named;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
//import javax.faces.context.ExternalContext;
//import javax.faces.context.FacesContext;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import beans.ExamBean;
import model.Exam;
import model.Question;

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
		allExams = theExams.findAllExams();
		
		
		upcomingExams = theExams.findAllExams();
		
		//setUpPastExams();
		
		
	}

	
	//Trying to add all the exams to the lists previous exams or upcoming exams
	public void setUpPastExams(){
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        dateFormat.format(date);
		
		
		for (int i = 0; i< allExams.size(); i++){
			
			if (allExams.get(i).geteDate().after(date)){
				
				upcomingExams.add(i, allExams.get(i));				
			}
			else if (allExams.get(i).geteDate().equals(date)){
				upcomingExams.add(i, allExams.get(i));
			}
			else {
				previousExams.add(i, allExams.get(i));
			}
			
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
		theExams.doUpdate(selectedExam);
		this.allExams.clear();
		this.allExams = theExams.findAllExams();
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
