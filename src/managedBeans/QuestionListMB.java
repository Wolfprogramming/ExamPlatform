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
	
	
	@EJB
	private QuestionBean theQuestions;
	
	public List<Question> getTheQuestions(){
		return theQuestions.findAllQuestions();
	}
}
