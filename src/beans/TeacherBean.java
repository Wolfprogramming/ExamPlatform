package beans;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.Startup;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import model.Teacher;

/**
 * Session Bean implementation class TestListExams
 */
@Stateless
@LocalBean
@Startup
public class TeacherBean {

	@PersistenceContext(name="ExaminationPlatform") // From persistence.xml 
	EntityManager em;
	
    public TeacherBean() {
    }
    
    public List<Teacher> findAllTeachers(){
    	TypedQuery<Teacher> theQuery = em.createQuery("SELECT t FROM Teacher t", Teacher.class);
    	return theQuery.getResultList();
    }
    
    public void doInsert(Teacher newTeacher){
    	em.persist(newTeacher);
    }
    
    public void doUpdate(Teacher updtTeacher) {
        em.merge(updtTeacher);
    }
    
    public void doRemove(String email) {
    	Teacher teacher = findTeacher(email);
        if (teacher != null) {
            em.remove(teacher);
        }
    }
      
    public void doRemove(Teacher teacher) {
        if (teacher != null && teacher.gettEmail()!=null && em.contains(teacher)) {
            em.remove(teacher);
        }
    }
    
    public Teacher findTeacher(int idTeacher){
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
    	    	
    	return em.find(Teacher.class, idTeacher);
    }
    
    public Teacher findTeacher(String username){
    	System.out.println("Enter function search Teacher with email/username");

    	Teacher searchTeacher = new Teacher();
    	try {
    		searchTeacher = (Teacher)em.createQuery("SELECT t FROM Teacher t WHERE t.tEmail=:qUsername").setParameter("qUsername", username).getSingleResult();
    	} catch(Exception e){
    		System.out.println("user does not exist");
    		searchTeacher.settPassword("error");
    		System.out.println("setPassword to error");
    	}

    	System.out.println("query search teacher with name done");

    	return searchTeacher;
    }
    
    public Teacher getCurrentTeacher(){
    	Teacher t = null;
    	
    	FacesContext fc = FacesContext.getCurrentInstance();
    	ExternalContext externalContext = fc.getExternalContext();
    	
    	if (externalContext.getUserPrincipal() == null){
    		System.out.println("current principal is null"); 
    	}
    	else{
    		String name = externalContext.getUserPrincipal().getName();
    		try {
    			TeacherBean tb = null;
    			t = tb.findTeacher(name); 
	    	}
	    	catch (Exception e){
	    		System.out.println("error");
	    	} 
    	}
    	return t;
    }
    
}
