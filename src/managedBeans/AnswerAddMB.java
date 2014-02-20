package managedBeans;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Named;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import model.Answer;
import model.Exam;
import model.Question;
import model.Student;
import beans.AnswerBean;


@Named
@SessionScoped
public class AnswerAddMB implements Serializable{
	
	private static final long serialVersionUID = 1L; 
	private String aName;
	private boolean aIsCorrect;
	private String aComment;
	private float aPoint;
	private Student student;
	private Exam selectedExam;
	private List<String> selectedAnswers;
	private String selectedAnswer;
	private Question selectedQuestion;
	private Map<String, String> possibleAnswers;
	private String prevAnswer;
	private int nbAnsweredQuestion;
	private int nbPointMaxAnswered;
	private Answer editAnswer;
	private Student selectedStudent;
	private float nbTotalPoint;

	@PostConstruct
	public void init(){
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		setStudent((Student) ec.getSessionMap().get("student"));
		setSelectedExam((Exam) ec.getSessionMap().get("exam"));
	}
	
	public AnswerAddMB(){
	}
	
	@EJB
	private AnswerBean theAnswer;

	public String getaName() {
		return aName;
	}

	public void setaName(String aName) {
		this.aName = aName;
	}

	public boolean isaIsCorrect() {
		return aIsCorrect;
	}

	public void setaIsCorrect(boolean aIsCorrect) {
		this.aIsCorrect = aIsCorrect;
	}

	public String getaComment() {
		return aComment;
	}

	public void setaComment(String aComment) {
		this.aComment = aComment;
	}

	public float getaPoint() {
		return aPoint;
	}

	public void setaPoint(float aPoint) {
		this.aPoint = aPoint;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Exam getSelectedExam() {
		return selectedExam;
	}

	public void setSelectedExam(Exam exam) {
		this.selectedExam = exam;
		if(selectedExam!=null){
			setNbAnsweredQuestion(theAnswer.findNbAnsweredQuestionOfExam(selectedExam, student));
			setNbPointMaxAnswered(theAnswer.findNbPointMaxAnswered(selectedExam, student));
		}
	}
	
	public List<String> getSelectedAnswers() {
		return selectedAnswers;
	}

	public void setSelectedAnswers(List<String> selectedAnswers) {
		this.selectedAnswers = selectedAnswers;
	}

	public String getSelectedAnswer() {
		return selectedAnswer;
	}

	public void setSelectedAnswer(String selectedAnswer) {
		this.selectedAnswer = selectedAnswer;
	}

	public Question getSelectedQuestion() {
		return selectedQuestion;
	}

	public void setSelectedQuestion(Question selectedQuestion) {
		this.selectedQuestion = selectedQuestion;
		possibleAnswers = new HashMap<String, String>();
		if(selectedQuestion!=null){
			for(int i=0; i<selectedQuestion.getPossibleAnswers().size(); i++){
				String paName = selectedQuestion.getPossibleAnswers().get(i).getPaName();
				possibleAnswers.put(paName, paName);
			}
			if(theAnswer.findAnswerOfQuestionStudent(selectedQuestion, student) != null)
				prevAnswer = theAnswer.findAnswerOfQuestionStudent(selectedQuestion, student).getaName();
			else
				prevAnswer = "";
		}
	}
	
	public Map<String, String> getPossibleAnswers() {
		return possibleAnswers;
	}
	
	public String getPrevAnswer() {
		return prevAnswer;
	}

	public void setPrevAnswer(String prevAnswer) {
		this.prevAnswer = prevAnswer;
	}

	public int getNbAnsweredQuestion() {
		return nbAnsweredQuestion;
	}

	public void setNbAnsweredQuestion(int nbAnsweredQuestion) {
		this.nbAnsweredQuestion = nbAnsweredQuestion;
	}

	public int getNbPointMaxAnswered() {
		return nbPointMaxAnswered;
	}

	public void setNbPointMaxAnswered(int nbPointMaxAnswered) {
		this.nbPointMaxAnswered = nbPointMaxAnswered;
	}

	public Student getSelectedStudent() {
		return selectedStudent;
	}

	public void setSelectedStudent(Student selectedStudent) {
		this.selectedStudent = selectedStudent;
		nbTotalPoint = theAnswer.findNbPointOfExam(selectedExam, selectedStudent);
	}

	public Answer getEditAnswer() {
		return editAnswer;
	}

	public void setEditAnswer(Answer editAnswer) {
		this.editAnswer = editAnswer;
	}

	public float getNbTotalPoint() {
		return nbTotalPoint;
	}

	public void setNbTotalPoint(float nbTotalPoint) {
		this.nbTotalPoint = nbTotalPoint;
	}

	public boolean isQuestionAnswered(Question paramQuestion){
		boolean bool = false;
		
		if(theAnswer.findAnswerOfQuestionStudent(paramQuestion, student) != null){
			bool = true;
		}
		else{
			bool = false;
		}
		
		return bool;
	}
	
	public Answer questionAnswer(Question paramQuestion){
		if(theAnswer.findAnswerOfQuestionStudent(paramQuestion, student) != null){
			return theAnswer.findAnswerOfQuestionStudent(paramQuestion, student);
		}
		else
			return null;
	}
	
	public Answer getAnswerStudent(Question pQuestion, Student pStudent){
		if(theAnswer.findAnswerOfQuestionStudent(pQuestion, pStudent) != null){
			return theAnswer.findAnswerOfQuestionStudent(pQuestion, pStudent);
		}
		else
			return null;
	}

	public void saveAnswer(ActionEvent event) throws IOException{
		System.out.println("Enter save answer for: " + selectedQuestion.getqName());
		
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		
		Date current = new Date();
		Date endExam = new Date(selectedExam.geteDate().getTime()+selectedExam.geteEndHour().getTime()+3600000);
		
		//control that exam time is not over
		if(current.compareTo(endExam)<0){

			if(selectedQuestion.getqType().equals("multipleChoice")){
				setaName("");
				for(int i=0; i<selectedAnswers.size(); i++){
					if(i==(selectedAnswers.size()-1))
						aName += selectedAnswers.get(i);
					else
						aName += selectedAnswers.get(i).concat(";");
				}
				//Determine if selected answers are correct
				int cpt1=0;
				int cpt2=0;
				int cpt3=0;
				for(int i=0; i<selectedQuestion.getPossibleAnswers().size(); i++){
					for(int j=0; j<selectedAnswers.size(); j++){
						if(selectedQuestion.getPossibleAnswers().get(i).getPaName().equals(selectedAnswers.get(j))){
							cpt1++;
							if(selectedQuestion.getPossibleAnswers().get(i).getPaIsCorrect()){
								cpt2++;
							}
						}
					}
					if(selectedQuestion.getPossibleAnswers().get(i).getPaIsCorrect())
						cpt3++;
				}
				//If count are equal and different from 0, then answers are correct
				System.out.println("cpt1: " +cpt1);
				System.out.println("cpt2: " +cpt2);
				if(cpt3!=0 && cpt1==cpt3 && cpt1==cpt2)
					aIsCorrect = true;
				else
					aIsCorrect = false;
			}
			else{
				aName = selectedAnswer;
				aIsCorrect = false;
				if(!(selectedQuestion.getqType().equals("text"))){
					for(int i=0; i<selectedQuestion.getPossibleAnswers().size(); i++){
						if(selectedQuestion.getPossibleAnswers().get(i).getPaIsCorrect()){
							if(selectedQuestion.getPossibleAnswers().get(i).getPaName().equals(selectedAnswer)){
								aIsCorrect = true;
							}
						}
					}
				}
			}
			
			//An answer already exists
			if(isQuestionAnswered(selectedQuestion)){
				editAnswer = questionAnswer(selectedQuestion);
				
				editAnswer.setaName(aName);
				editAnswer.setaIsCorrect(aIsCorrect);
				if(aIsCorrect==true)
					editAnswer.setaPoint(selectedQuestion.getqValue());
				else
					editAnswer.setaPoint(0);
				theAnswer.doUpdate(editAnswer);
				
				System.out.println("answer edited");
			}
			else{
				Answer tmp = new Answer();
				tmp.setaName(aName);
				tmp.setaIsCorrect(aIsCorrect);
				if(aIsCorrect==true)
					tmp.setaPoint(selectedQuestion.getqValue());
				else
					tmp.setaPoint(0);
				tmp.setStudent(student);
				tmp.setExam(selectedExam);
				tmp.setQuestion(selectedQuestion);
				theAnswer.doInsert(tmp);
				
				System.out.println("answer saved");
			}	
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Answer saved!", "Answer saved successfully!");  
			context.addMessage(null, message);
			
			setaName("");
			setaIsCorrect(false);
			setSelectedQuestion(null);
			setSelectedAnswer("");
			setNbAnsweredQuestion(theAnswer.findNbAnsweredQuestionOfExam(selectedExam, student));
			setNbPointMaxAnswered(theAnswer.findNbPointMaxAnswered(selectedExam, student));
			
			if(selectedAnswers!=null)
				selectedAnswers.clear();
		}
		else{
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Time elapsed!", "Exam is over, you have to submit it now.");  
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		
		externalContext.redirect(externalContext.getRequestContextPath() + "/answerExam.xhtml");
	}
	
	
	public void saveCorrection(ActionEvent event) throws IOException{
		System.out.println("Enter save correction for: " + selectedQuestion.getqName());
		
		Answer tmp = theAnswer.findAnswerOfQuestionStudent(selectedQuestion, selectedStudent);
		tmp.setaComment(aComment);
		tmp.setaPoint(aPoint);
		theAnswer.doUpdate(tmp);
		
		System.out.println("Correction saved");
		
		setaComment("");
		setaPoint(0);
		
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		externalContext.redirect(externalContext.getRequestContextPath() + "/correctExam.xhtml");
	}
	
	public void clear(){
		setaComment("");
		setaPoint(0);
	}
	
	public float nbTotalPoint(Exam exam, Student student){
		return theAnswer.findNbPointOfExam(exam, student);
	}
	
	public float nbTotalPoint(Exam exam){
		return theAnswer.findNbPointOfExam(exam, student);
	}

}
