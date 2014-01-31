package model;

import java.io.IOException;
import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;


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
    
    
    public void login(ActionEvent actionEvent) throws IOException {
    	System.out.println("entre fonction login");

    	resultTeacher = theTeacher.getSearchTeacher(username);
    	System.out.println("recuperation teacher");

    	if(resultTeacher.getTPassword().equals("error")){
    		System.out.println("Wrong username");
    		
    		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Identification failed", "Wrong username or password"));
    		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"HINT", "Your username is your e-mail"));
    	}
    	else if(password.equals(resultTeacher.getTPassword())){
        	System.out.println("Identification succeed");
        	
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