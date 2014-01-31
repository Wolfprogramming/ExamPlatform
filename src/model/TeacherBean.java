package model;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * Session Bean implementation class TestListExams
 */
@Singleton
@LocalBean
@Startup
public class TeacherBean {

	@PersistenceContext(name="ExaminationPlatform") // From persistence.xml 
	EntityManager em;
	
    public TeacherBean() {
    }
    
    public List<Teacher> getListOfTeachers(){
    	TypedQuery<Teacher> theQuery = em.createQuery("SELECT t FROM Teacher t", Teacher.class);
    	List<Teacher> result = theQuery.getResultList();
    	
    	return result;
    }
    
    public void doInsert(Teacher newTeacher){
    	em.persist(newTeacher);
    }
    
    public Teacher getSearchTeacher(int idTeacher){
    	Teacher searchTeacher = new Teacher();
    	try {
    		searchTeacher = (Teacher)em.createQuery("SELECT t FROM Teacher t WHERE t.idTeacher=:qIdTeacher").setParameter("qIdTeacher", idTeacher).getSingleResult();
    	} catch(Exception e){
    		System.out.println("user does not exist");
    		searchTeacher.setTPassword("error");
    		searchTeacher.setTFirstName("error");
    		searchTeacher.setTLastName("error");
    		searchTeacher.setTEmail("error");
    		System.out.println("set parameters to error");
    	}
    		
    	return searchTeacher;
    }
    
    public Teacher getSearchTeacher(String username){
    	System.out.println("Enter function search Teacher with name");

    	Teacher searchTeacher = new Teacher();
    	try {
    		searchTeacher = (Teacher)em.createQuery("SELECT t FROM Teacher t WHERE t.tEmail=:qUsername").setParameter("qUsername", username).getSingleResult();
    	} catch(Exception e){
    		System.out.println("user does not exist");
    		searchTeacher.setTPassword("error");
    		System.out.println("setPassword to error");
    	}

    	System.out.println("query search teacher with name done");

    	return searchTeacher;
    }

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}
    
}
