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
	
	@EJB
	private QuestionBean theQuestions;
	
	public Question getSelectedQuestion() {
		return selectedQuestion;
	}

	public void setSelectedQuestion(Question selectedQuestion) {
		this.selectedQuestion = selectedQuestion;
	}

	public List<Question> getTheQuestions(){
		return theQuestions.findAllQuestions();
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
