package managedBeans;

import java.io.Serializable;
import java.util.List;

import javax.inject.Named;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;

import beans.TeacherBean;
import model.Teacher;

/**
 * Session Bean implementation class TeacherListMB
 */
@Named
@SessionScoped
public class TeacherListMB implements Serializable {


	private static final long serialVersionUID = 1L;
	private List<Teacher> allTeachers;
	private List<Teacher> filteredTeachers;
	
	@PostConstruct
	public void init()
	{
		allTeachers = theTeachers.findAllTeachers();
	}
	
	@EJB
	private TeacherBean theTeachers;
	
	
    public TeacherListMB() {
        // TODO Auto-generated constructor stub
    }
    
    public List<Teacher> getAllTeachers(){
		return allTeachers;
	}

	public List<Teacher> getfilteredTeachers() {
		return filteredTeachers;
	}

	public void setfilteredTeachers(List<Teacher> filteredTeachers) {
		this.filteredTeachers = filteredTeachers;
	}
    

}
