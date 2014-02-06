package managedBeans;

import java.io.Serializable;

import javax.inject.Named;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;

import model.Exam;
import model.Question;
import beans.ExamBean;
import beans.QuestionBean;


@Named
@SessionScoped
public class QuestionAddMB implements Serializable {

	private static final long serialVersionUID = 1L;
	private String qHelp;
	private String qName;
	private int qPosition = 1;
	private String qType;
	private short qValue;
	private Exam exam;
	
	public QuestionAddMB(){
    }
	
	@EJB
	private QuestionBean theQuestion;
	@EJB
	private ExamBean theExam;

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


	public ExamBean getTheExam() {
		return theExam;
	}

	public void setTheExam(ExamBean theExam) {
		this.theExam = theExam;
	}


	public String saveQuestion(){ 
		Question tmp = new Question();
		
		//search id of the exam
		exam = theExam.findExam(1);
		tmp.setExam(exam);
		
		tmp.setqHelp(qHelp);
		tmp.setqName(qName);
		tmp.setqPosition(qPosition);
		tmp.setqType(qType);
		tmp.setqValue(qValue);
		theQuestion.doInsert(tmp);
		
		return null; //returns the same page
	}
}
