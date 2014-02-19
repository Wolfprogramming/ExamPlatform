package managedBeans;

import java.io.Serializable;

import javax.inject.Named;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import model.Exam;
import model.Question;
import beans.QuestionBean;


@Named
@SessionScoped
public class QuestionAddMB implements Serializable {

	private static final long serialVersionUID = 1L;
	private String qHelp;
	private String qName;
	private int qPosition;
	private String qType;
	private short qValue;
	private Exam exam;
	private Exam selectedExam;
	private String isDisplay="false";
	private String examState; //to know if it's a new or existing exam
	
	public QuestionAddMB(){
    }
	
	@EJB
	private QuestionBean theQuestion;

	public String getqHelp() {
		return qHelp;
	}

	public void setqHelp(String qHelp) {
		this.qHelp = qHelp;
	}

	public String getqName() {
		return qName;
	}

	public void setqName(String qName) {
		this.qName = qName;
	}

	public int getqPosition() {
		return qPosition;
	}

	public void setqPosition(int qPosition) {
		this.qPosition = qPosition;
	}

	public String getqType() {
		return qType;
	}

	public void setqType(String qType) {
		this.qType = qType;
	}

	public short getqValue() {
		return qValue;
	}

	public void setqValue(short qValue) {
		this.qValue = qValue;
	}
	
	//display the answers inputText when question contains several possible answers
	public String getisDisplay() {
		return isDisplay;
	}

	public void setisDisplay(String isDisplay) {
		this.isDisplay = isDisplay;
	}
	
	public void subjectSelectionChanged(){
		if(!(qType.equals("text")))
			isDisplay="true";
		else
			isDisplay="false";
	}
	
	public Exam getExam() {
		return exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}

	public Exam getSelectedExam() {
		return selectedExam;
	}

	public void setSelectedExam(Exam selectedExam) {
		this.selectedExam = selectedExam;
	}

	public String getExamState() {
		return examState;
	}

	public void setExamState(String examState) {
		this.examState = examState;
	}

	public String saveQuestion(ActionEvent event){ 
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		
		System.out.println("enter saveQuestion");
		
		if(examState.equals("old")){
	    	exam = selectedExam;
	    	System.out.println("existing exam: " + exam.geteName());
	    }else if(examState.equals("new")){
	    	exam = (Exam) ec.getSessionMap().get("exam");
	    	setSelectedExam(exam);
	    	System.out.println("new exam: " + exam.geteName());
	    }
		
		Question tmp = new Question();
		tmp.setExam(exam);
		tmp.setqHelp(qHelp);
		tmp.setqName(qName);
		tmp.setqPosition(qPosition);
		tmp.setqType(qType);
		tmp.setqValue(qValue);
		theQuestion.doInsert(tmp);
		
		ec.getSessionMap().put("question", tmp);
		exam.addQuestion(tmp);
		System.out.println("question saved");
		
		setqHelp(null);
		setqName(null);
		setqPosition(0);
		setqType(null);
		setqValue((short)0);
		setisDisplay("false");
		setExamState("old");
		
		return null;
	}
	
	
}
