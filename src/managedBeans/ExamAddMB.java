package managedBeans;

import java.io.Serializable;
import java.util.Date;

import javax.inject.Named;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

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
		FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
		//System.out.println("User : " + ((Teacher) externalContext.getSessionMap().get("user")).gettFirstName());
		
		Exam tmp = new Exam(); 
		tmp.seteName(theName); 
		tmp.seteDesc(theDesc);
		tmp.seteCreationDate(theCreation);
		tmp.seteModifyDate(theModify);
		teach = (Teacher) externalContext.getSessionMap().get("user");
		
		tmp.setTeacher(teach);
		theExam.doInsert(tmp);
		
		return "index";
	}
    
}
