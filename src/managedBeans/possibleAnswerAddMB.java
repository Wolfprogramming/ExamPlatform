package managedBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import model.PossibleAnswer;
import model.Question;
import beans.PossibleAnswerBean;
import beans.QuestionBean;


@Named
@SessionScoped
public class possibleAnswerAddMB implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<Boolean> paIsCorrect = new ArrayList<Boolean>(3);
	private List<String> paName = new ArrayList<String>(3);
	private Question question;
	
	
	@PostConstruct
    public void initList() {
		paName.add("");
		paName.add("");
		paName.add("");
		paIsCorrect.add(false);
		paIsCorrect.add(false);
		paIsCorrect.add(false);
	}
	
	public possibleAnswerAddMB(){
    }
	
	@EJB
	private PossibleAnswerBean thePA;
	@EJB
	private QuestionBean theQuestion;

	public List<Boolean> getPaIsCorrect() {
		return paIsCorrect;
	}
	public void setPaIsCorrect(List<Boolean> paIsCorrect) {
		this.paIsCorrect = paIsCorrect;
	}
	public List<String> getPaName() {
		return paName;
	}
	public void setPaName(List<String> paName) {
		this.paName = paName;
	}
	
	public void setPaName(int index, String val){
			this.paName.set(index, val);
	}
	
	public void setPaIsCorrect(int index, Boolean val){
			this.paIsCorrect.set(index, val);
	}
	
	public String savePA(){ 
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		question = (Question) ec.getSessionMap().get("question");
		
		System.out.println("enter savePA");

		if(!(question.getqType().equals("text"))){
			
			for(int i=0; i<paName.size(); i++)
			{
				if(paName.get(i)==null)
				{
					System.out.println("NULL VALUE");
				}
				else{
					PossibleAnswer tmp = new PossibleAnswer();
					tmp.setPaIsCorrect(paIsCorrect.get(i));
					tmp.setPaName(paName.get(i));
					tmp.setQuestion(question);
					thePA.doInsert(tmp);
					
					question.addPossibleAnswer(tmp);
					theQuestion.doUpdate(question);
					System.out.println("value saved: " + i + " - " + paName.get(i));
					
					//Clear variable after adding
					paName.set(i, "");
					paIsCorrect.set(i, false);
				}
			}
		}
		
		return ec.getRequestParameterMap().get("returnPage");
	}
	
	public String savePA(Question editQuestion){ 
		
		System.out.println("enter savePA with argument");

		if(!(editQuestion.getqType().equals("text")) && !(paName.get(0).equals(""))){
			
			for(int i=0; i<paName.size(); i++)
			{
				if(paName.get(i)==null || paName.get(i).equals(""))
				{
					System.out.println("NULL VALUE");
				}
				else{
					PossibleAnswer tmp = new PossibleAnswer();
					tmp.setPaIsCorrect(paIsCorrect.get(i));
					tmp.setPaName(paName.get(i));
					tmp.setQuestion(editQuestion);
					thePA.doInsert(tmp);
					
					editQuestion.getPossibleAnswers().add(tmp);
					theQuestion.doUpdate(question);
					System.out.println("value saved: " + i + " - " + paName.get(i));
					
					paName.set(i, "");
					paIsCorrect.set(i, false);
				}
			}
		}
		return "viewExam";
	}
	
}
