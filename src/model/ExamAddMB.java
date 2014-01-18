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
	private TeacherBean theTeacher = (TeacherBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("em");
	private Teacher teach = theTeacher.getSearchTeacher(2);
	
	
	
	@EJB
	private ExamBean theExams;
	
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
		tmp.setteacher(teach);
		theExams.doInsert(tmp);
		
		return "index";
	}
    
}
