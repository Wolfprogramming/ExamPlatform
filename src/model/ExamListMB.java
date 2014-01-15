package model;

import java.io.Serializable;
import java.util.List;

import javax.inject.Named;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;



@Named
@SessionScoped
public class ExamListMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@EJB
	private ExamBean theExams;
	
	public List<Exam> getTheExams(){
		return theExams.getListOfExams();
	}
    
}
