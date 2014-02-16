package managedBeans;

import java.io.Serializable;
import java.util.List;

import javax.inject.Named;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;

import beans.StudentBean;
import model.Student;
import model.StudentDataModel;

/**
 * Session Bean implementation class TeacherListMB
 */
@Named
@SessionScoped
public class StudentListMB implements Serializable {


	private static final long serialVersionUID = 1L;
	private List<Student> allStudents;
	private List<Student> filteredStudents;
	private Student[] selectedStudents;
	private StudentDataModel studentsModel;
	
	

	@EJB
	private StudentBean theStudents;
	
	@PostConstruct
	public void init(){
    	allStudents = theStudents.findAllStudents();
		studentsModel = new StudentDataModel(allStudents);
	}
	
	
    public StudentListMB() {
		
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

	public Student[] getSelectedStudents() {
		return selectedStudents;
	}

	public void setSelectedStudents(Student[] selectedStudents) {
		this.selectedStudents = selectedStudents;
	}

	public StudentDataModel getStudentsModel() {
		return studentsModel;
	}

	public void setStudentsModel(StudentDataModel studentsModel) {
		this.studentsModel = studentsModel;
	}
    

}
