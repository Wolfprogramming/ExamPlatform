package managedBeans;

import java.io.Serializable;
import java.util.List;

import javax.inject.Named;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import beans.PossibleAnswerBean;
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
	private String isDisplay;
	private String typePrec;
	
	@EJB
	private QuestionBean theQuestions;
	@EJB
	private PossibleAnswerBean thePA;
	
	public Question getSelectedQuestion() {
		return selectedQuestion;
	}

	public void setSelectedQuestion(Question selectedQuestion) {
		this.selectedQuestion=selectedQuestion;
		this.typePrec=selectedQuestion.getqType();
		if(!selectedQuestion.getqType().equals("text"))
			isDisplay="true";
		else
			isDisplay="false";
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
		if(!selectedQuestion.getqType().equals("text"))
			isDisplay="true";
		else
			isDisplay="false";
	}
	
	public String editQuestion(ActionEvent event){
		System.out.println("enter editQuestion");
		
		selectedQuestion.setqHelp(selectedQuestion.getqHelp());
		selectedQuestion.setqName(selectedQuestion.getqName());
		selectedQuestion.setqPosition(selectedQuestion.getqPosition());
		selectedQuestion.setqType(selectedQuestion.getqType());
		selectedQuestion.setqValue(selectedQuestion.getqValue());
		theQuestions.doUpdate(selectedQuestion);
		
		System.out.println("question modified");
		
		if(!(typePrec.equals("text")) && !(selectedQuestion.getqType().equals("text"))){
			System.out.println("prec 'Other', new 'Other'");
			
			for(int i=0; i<selectedQuestion.getPossibleAnswers().size(); i++)
			{
				if(selectedQuestion.getPossibleAnswers().get(i)==null)
				{
					System.out.println("NULL VALUE");
				}
				else{
					selectedQuestion.getPossibleAnswers().get(i).setPaIsCorrect(selectedQuestion.getPossibleAnswers().get(i).getPaIsCorrect());
					selectedQuestion.getPossibleAnswers().get(i).setPaName(selectedQuestion.getPossibleAnswers().get(i).getPaName());
					thePA.doUpdate(selectedQuestion.getPossibleAnswers().get(i));
					
					System.out.println("value edited: " + i + " - " + selectedQuestion.getPossibleAnswers().get(i).getPaName());
				}
			}
		}
		else if(!(typePrec.equals("text")) && selectedQuestion.getqType().equals("text")){
			System.out.println("prec 'Other', new 'Text'");
			
			for(int i=0; i<selectedQuestion.getPossibleAnswers().size(); i++)
			{
				thePA.doRemove(selectedQuestion.getPossibleAnswers().get(i).getIdPossibleAnswer());
				System.out.println("value deleted: " + i);
				
			}
		}
		
		return "viewExam";
	}
	
	public String deleteQuestion(){
		System.out.println("Enter delete question");
		
		if(!(selectedQuestion.getqType().equals("text"))){
			System.out.println("type != text");
			
			for(int i=0; i<selectedQuestion.getPossibleAnswers().size(); i++)
			{
				thePA.doRemove(selectedQuestion.getPossibleAnswers().get(i).getIdPossibleAnswer());
				System.out.println("value deleted: " + i);
				
			}
		}
		System.out.println("question : " + selectedQuestion.getqName());
		
		selectedQuestion.getExam().removeQuestion(selectedQuestion);
		theQuestions.doRemove(selectedQuestion.getIdQuestion());
		
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Question deleted",  "Question deleted with success!");  		
		FacesContext.getCurrentInstance().addMessage(null, message);
		
		return "viewExam";
	}
	
	
}
