package managedBeans;

import java.io.Serializable;
import java.security.Principal;
import java.util.Date;

import javax.inject.Named;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.enterprise.context.SessionScoped;

import model.Exam;
import model.Teacher;
import beans.ExamBean;
import beans.TeacherBean;


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
	private ExamBean theExam;
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
		teach = theTeacher.getCurrentTeacher();
		
		tmp.setTeacher(teach);
		theExam.doInsert(tmp);
		
		return "index";
	}
    
}
