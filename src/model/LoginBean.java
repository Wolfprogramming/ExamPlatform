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
public class LoginBean {

	@PersistenceContext(name="ExaminationPlatform") // From persistence.xml 
	EntityManager em;
	
    public LoginBean() {
    }
    
   /* public List<Teacher> getListOfTeachers(String user){
    	TypedQuery<Teacher> theQuery = em.createQuery("SELECT t FROM Teacher WHERE user = tFirstName", Teacher.class);
    	List<Teacher> result = theQuery.getResultList();
    	
    	return result;
    }*/
    
    public Teacher getListOfTeachersTest(String user){
    	TypedQuery<Teacher> theQuery = em.createQuery("SELECT t FROM Teacher t WHERE t.tFirstName = :tfirstname", Teacher.class).setParameter("tfirstname", user);
    	Teacher result = theQuery.getSingleResult();
    	//SELECT tLastName FROM Teacher WHERE tFirstName = "Alex"
    	//SELECT t FROM Teacher t WHERE t.tFirstName = :tfirstname
    	
    	return result;
    }
    
    
    /*public Teacher getSearchTeacher(String user, String password){
    	
    	Teacher searchTeacher = new Teacher();
    	
    	Statement stmt = null;
    	
    	try {
			ResultSet rs= stmt.executeQuery("SELECT tFirstName FROM teacher WHERE user = tFirstName");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    	
    	
    	//searchTeacher = (Teacher)em.createQuery("SELECT t FROM Teacher t WHERE t.idTeacher=:qItTeacher").setParameter("qItTeacher", idTeacher).getSingleResult();
    	
    	return searchTeacher;
    }*/
    
}
