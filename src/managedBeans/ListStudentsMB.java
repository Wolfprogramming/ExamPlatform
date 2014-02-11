package managedBeans;

import java.io.Serializable;
import java.util.List;

import javax.inject.Named;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;

import beans.StudentBean;
import model.Student;

/**
 * Session Bean implementation class TeacherListMB
 */
@Named
@SessionScoped
public class ListStudentsMB implements Serializable {


	private static final long serialVersionUID = 1L;
	private List<Student> allStudents;
	private List<Student> filteredStudents;
	
	
	
	@PostConstruct
	public void init()
	{
		allStudents = theStudents.findAllStudents();
	}
	
	@EJB
	private StudentBean theStudents;
	
	
	
    public ListStudentsMB() {
        // TODO Auto-generated constructor stub
    }
    
    public List<Student> getAllStudents(){
		return allStudents;
	}

	public List<Student> getfilteredStudents() {
		return filteredStudents;
	}

	public void setfilteredStudents(List<Student> filteredStudents) {
		this.filteredStudents = filteredStudents;
	}
    

}
