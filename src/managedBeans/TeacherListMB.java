package managedBeans;

import java.io.Serializable;
import java.util.List;

import javax.inject.Named;
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
	
	@EJB
	private TeacherBean theTeachers;
	
    public TeacherListMB() {
        // TODO Auto-generated constructor stub
    }
    
    public List<Teacher> getAllTeachers(){
		return theTeachers.findAllTeachers();
	}

}
