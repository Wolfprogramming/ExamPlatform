package managedBeans;

import java.io.Serializable;
import java.util.List;

import javax.inject.Named;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;

import beans.QuestionBean;
import model.Question;

@Named
@SessionScoped
public class QuestionListMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Question selectedQuestion;
	private String isDisplay="false";
	private String typePrec;
	
	@EJB
	private QuestionBean theQuestions;
	
	public Question getSelectedQuestion() {
		return selectedQuestion;
	}

	public void setSelectedQuestion(Question selectedQuestion) {
		this.selectedQuestion = selectedQuestion;
		this.typePrec=selectedQuestion.getqType();
	}

	public List<Question> getTheQuestions(){
		return theQuestions.findAllQuestions();
	}
	
	public String getisDisplay() {
		return isDisplay;
	}

	public void setisDisplay(String isDisplay) {
		this.isDisplay = isDisplay;
	}
	
	public String gettypePrec() {
		return typePrec;
	}
	
	public void subjectSelectionChanged(){
		if(selectedQuestion.getqType().equals("singleChoice") || selectedQuestion.getqType().equals("multipleChoice"))
			isDisplay="true";
		else
			isDisplay="false";
	}
	
	public String editQuestion(){
		System.out.println("enter editQuestion");
		
		selectedQuestion.setqHelp(selectedQuestion.getqHelp());
		selectedQuestion.setqName(selectedQuestion.getqName());
		selectedQuestion.setqPosition(selectedQuestion.getqPosition());
		selectedQuestion.setqType(selectedQuestion.getqType());
		selectedQuestion.setqValue(selectedQuestion.getqValue());
		theQuestions.doUpdate(selectedQuestion);
				
		System.out.println("question modified");
		
		return "viewExam";
	}
}
