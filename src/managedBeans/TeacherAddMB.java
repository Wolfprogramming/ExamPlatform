package managedBeans;

import java.io.Serializable;

import javax.inject.Named;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;

import beans.TeacherBean;
import model.Teacher;

@Named
@SessionScoped
public class TeacherAddMB implements Serializable {

	private static final long serialVersionUID = 1L; 
	private String firstname;
	private String lastname;
	private String email;
	private String password;
	
	@EJB
	private TeacherBean theTeacher;
	
	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String saveTeacher(){ 
		Teacher tmp = new Teacher(firstname, lastname, email, password);		
		theTeacher.doInsert(tmp);
				
		return "index"; 
	}
    
}
