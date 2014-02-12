package beans;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import model.Student;

/**
 * Session Bean implementation class TestListExams
 */
@Stateless
@LocalBean
@Startup
public class StudentBean {

	@PersistenceContext(name="ExaminationPlatform") // From persistence.xml 
	EntityManager em;
	
    public StudentBean() {
    }
    
    public List<Student> findAllStudents(){
    	TypedQuery<Student> theQuery = em.createQuery("SELECT s FROM Student s", Student.class);
    	return theQuery.getResultList();
    }
    
    public void doInsert(Student newStudent){
    	em.persist(newStudent);
    }
    
    public void doUpdate(Student updtStudent) {
        em.merge(updtStudent);
    }
    
    public void doRemove(String email) {
    	Student student = findStudent(email);
        if (student != null) {
            em.remove(student);
        }
    }
      
    public void doRemove(Student student) {
        if (student != null && student.getsEmail()!=null && em.contains(student)) {
            em.remove(student);
        }
    }
    
    public Student findStudent(int idStudent){
    	/*Teacher searchTeacher = new Teacher();
    	try {
    		searchTeacher = (Teacher)em.createQuery("SELECT t FROM Teacher t WHERE t.idTeacher=:qIdTeacher").setParameter("qIdTeacher", idTeacher).getSingleResult();
    	} catch(Exception e){
    		System.out.println("user does not exist");
    		searchTeacher.settPassword("error");
    		searchTeacher.settFirstName("error");
    		searchTeacher.settLastName("error");
    		searchTeacher.settEmail("error");
    		System.out.println("set parameters to error");
    	}*/
    	    	
    	return em.find(Student.class, idStudent);
    }
    
    public Student findStudent(String username){
    	System.out.println("Enter function search Student with email/username");

    	Student searchStudent = new Student();
    	try {
    		searchStudent = (Student)em.createQuery("SELECT s FROM Student s WHERE s.sEmail=:qUsername").setParameter("qUsername", username).getSingleResult();
    	} catch(Exception e){
    		System.out.println("user does not exist");
    		searchStudent.setSPassword("error");
    		System.out.println("setPassword to error");
    	}

    	System.out.println("query search student with name done");

    	return searchStudent;
    }
    
    
}
