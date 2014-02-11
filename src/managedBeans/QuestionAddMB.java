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
	private String isDisplay="false";
	
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
		if(qType.equals("singleChoice") || qType.equals("multipleChoice"))
			isDisplay="true";
		else
			isDisplay="false";
	}
	
	public String saveQuestion(ActionEvent event){ 
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		exam = (Exam) ec.getSessionMap().get("exam");
		
		System.out.println("enter saveQuestion");
		
		Question tmp = new Question();
		tmp.setExam(exam);
		tmp.setqHelp(qHelp);
		tmp.setqName(qName);
		tmp.setqPosition(qPosition);
		tmp.setqType(qType);
		tmp.setqValue(qValue);
		theQuestion.doInsert(tmp);
		
		ec.getSessionMap().put("question", tmp);
		
		System.out.println("question saved");
		
		return null;
	}
	
	
}
