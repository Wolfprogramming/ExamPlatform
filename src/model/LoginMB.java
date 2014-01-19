package model;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;



@Named
@SessionScoped
public class LoginMB implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	
	
	
	private String theUser;
	private String thePassword;
	
	
	
	public String getTheUser() {
		return theUser;
	}





	public String getThePassword() {
		return thePassword;
	}





	@EJB
	private LoginBean theLogin;
	
	@SuppressWarnings("unused")
	public void loginBtn(){
		
		System.out.println("Trying to login");
		
		Teacher resultList = theLogin.getListOfTeachersTest(theUser);
		
		if (resultList == null){
			System.out.println("Username not correct");
		}
		else if (resultList.gettPassword() == thePassword){
			System.out.println("Login Succeed");
			
			//Access to the next webpage
		}
		else System.out.println("Login failed");
		
		

		
	}





	public void setTheUser(String theUser) {
		this.theUser = theUser;
	}


	


	public void setThePassword(String thePassword) {
		this.thePassword = thePassword;
	}
	
	
    
}
