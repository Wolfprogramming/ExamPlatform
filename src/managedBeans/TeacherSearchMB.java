package managedBeans;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import beans.TeacherBean;
import model.Teacher;


@Named
@SessionScoped
public class TeacherSearchMB implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@EJB
	private TeacherBean theTeacher;
	
	private int searchId;
	private Teacher resultTeacher;
	
	public TeacherSearchMB(){
		
	}
	
	public int getSearchId() {
		return searchId;
	}

	public void setSearchId(int searchId) {
		this.searchId = searchId;
	}

	public Teacher getResultTeacher() {
		return resultTeacher;
	}

	public void setResultTeacher(Teacher resultTeacher) {
		this.resultTeacher = resultTeacher;
	}
	
	public Teacher searchTeacher(){
		resultTeacher = theTeacher.findTeacher(searchId);

		return null;
	}

}
