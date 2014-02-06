package managedBeans;

import java.io.IOException;
import java.io.Serializable;
import java.security.Principal;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;

import model.Teacher;
import beans.TeacherBean;


@ManagedBean
@SessionScoped
public class LoginMB implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String username;
    private String password;
    private Teacher resultTeacher;
    
    public LoginMB(){
    }
    
    @EJB
	private TeacherBean theTeacher;
    
    public void login2(ActionEvent actionEvent) throws IOException {
    	FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();

        try {
            request.login(username, password);
            resultTeacher = theTeacher.findTeacher(username);
            externalContext.getSessionMap().put("user", resultTeacher);
            externalContext.redirect(externalContext.getRequestContextPath() + "/index.xhtml");
        } catch (Exception e) {
            // Handle unknown username/password in request.login().
            context.addMessage(null, new FacesMessage("Unknown login"));
        }
    }
    
    
    public void login(ActionEvent actionEvent) throws IOException {
    	resultTeacher = theTeacher.findTeacher(username);
    	
    	if(resultTeacher.gettPassword().equals("error")){
    		System.out.println("Wrong username");
    		
    		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Identification failed", "Wrong username or password"));
    		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"HINT", "Your username is your e-mail"));
    	}
    	else if(password.equals(resultTeacher.gettPassword())){
        	System.out.println("Identification succeed");
        	/*
        	Principal principal = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
            if (principal != null) {
                resultTeacher = theTeacher.getSearchTeacher(principal.getName()); // Find User by j_username.
                System.out.println("teacher:" + resultTeacher.gettFirstName());
            }
            else{
            	System.out.println("user principal failed");
            }*/
        	
        	ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
            externalContext.redirect(externalContext.getRequestContextPath() + "/index.xhtml");
        }
        else {
        	System.out.println("Wrong password");

        	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Identification failed", "Wrong username or password"));
        }
    }
    

    public void logout() throws IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.invalidateSession();
        externalContext.redirect(externalContext.getRequestContextPath() + "/loginPage.xhtml");
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

    
}