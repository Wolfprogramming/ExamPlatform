package model;

import java.io.Serializable;
import java.util.Date;

import javax.inject.Named;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;


@Named
@SessionScoped
public class ExamAddMB implements Serializable {

	private static final long serialVersionUID = 1L; 
	private String theName;
	private String theDesc;
	private Date theCreation = new Date(System.currentTimeMillis());
	private Date theModify  = new Date(System.currentTimeMillis());
	private Teacher teach;
	
	public ExamAddMB(){
    }
	
	@EJB
	private ExamBean theExams;
	@EJB
	private TeacherBean theTeacher;
	
	public String getTheName() { 
		return theName;
	}
	
	public void setTheName(String theName) {
		this.theName = theName; 
	}
	
	public String getTheDesc() { 
		return theDesc;
	}
	
	public void setTheDesc(String theDesc) {
		this.theDesc = theDesc; 
	}
	
	public String saveExam(){ 
		Exam tmp = new Exam(); 
		tmp.seteName(theName); 
		tmp.seteDesc(theDesc);
		tmp.seteCreationDate(theCreation);
		tmp.seteModifyDate(theModify);
		teach = theTeacher.getSearchTeacher(2);
		tmp.setteacher(teach);
		theExams.doInsert(tmp);
		
		return "index";
	}
    
}
