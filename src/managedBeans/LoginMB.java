package managedBeans;

import java.io.IOException;
import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.context.RequestContext;

import model.Student;
import model.Teacher;
import beans.StudentBean;
import beans.TeacherBean;


@ManagedBean
@SessionScoped
public class LoginMB implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String username;
    private String password;
    private Teacher resultTeacher;
    private Student resultStudent;
    
    public LoginMB(){
    }
    
    @EJB
	private TeacherBean theTeacher;
    @EJB
    private StudentBean theStudent;
    
    public void login(ActionEvent actionEvent) throws IOException {
    	FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        
        resultTeacher = theTeacher.findTeacher(username);
        resultStudent = theStudent.findStudent(username);
        
        if (resultTeacher.gettPassword().equals("error") && resultStudent.getSPassword().equals("error" )){
        	setUsername("");
        	setPassword("");
        	context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Identification failed", "Wrong username or password"));
    		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"HINT", "Your username is your e-mail"));
        }//Login teacher
        else if(password.equals(resultTeacher.gettPassword())){
        	System.out.println("Identification TEACHER succeed");
        	
        	//Put user in sessionMap to know he is logged in and retrieve it later
        	externalContext.getSessionMap().put("teacher", resultTeacher);
        	
            externalContext.redirect(externalContext.getRequestContextPath() + "/index.xhtml");
        	
        }//Login student
        else if(password.equals(resultStudent.getSPassword())){
        	System.out.println("Identification STUDENT succeed");
        	
        	externalContext.getSessionMap().put("student", resultStudent);
        	
            externalContext.redirect(externalContext.getRequestContextPath() + "/indexStudent.xhtml");
        }
        else {
        	System.out.println("Wrong password");
        	setUsername("");
        	setPassword("");
        	context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Identification failed", "Wrong username or password"));
        }
    	
    }
    
    public void logout() throws IOException {
    	setUsername("");
    	setPassword("");
    	setresultTeacher(null);
    	setresultStudent(null);
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.invalidateSession();
        externalContext.redirect(externalContext.getRequestContextPath() + "/loginPage.xhtml");
    }
    
    
    public void reset(){
    	setUsername("");
    	setPassword("");
    	RequestContext.getCurrentInstance().reset("form:panel");
    }

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Teacher getresultTeacher() {
		return resultTeacher;
	}

	public void setresultTeacher(Teacher resultTeacher) {
		this.resultTeacher = resultTeacher;
	}

	public Student getresultStudent() {
		return resultStudent;
	}

	public void setresultStudent(Student resultStudent) {
		this.resultStudent = resultStudent;
	}
	
	
    
}
