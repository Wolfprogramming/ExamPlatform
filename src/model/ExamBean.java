package model;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;


/**
 * Session Bean implementation class ExamBean
 */
@Stateless
public class ExamBean {

	@PersistenceContext(name="ExaminationPlatform")
	EntityManager em;

    public ExamBean() {
    }
    
    public List<Exam> getListOfExams(){
    	TypedQuery<Exam> theQuery = em.createQuery("SELECT e FROM Exam e", Exam.class);
    	List<Exam> result = theQuery.getResultList();
    	
    	//List<Exam> result = (List<Exam>)em.createQuery("SELECT e FROM ExamPlatformDB.Exam e", Exam.class).getResultList();
    	
    	
    	return result;
    }
    
    public void doInsert(Exam newExam){
    	em.persist(newExam);
    }
    
    public Exam getExam(int idSearchExam){
    	Exam searchExam = new Exam();
    	searchExam = (Exam)em.createQuery("select e from Exam e where e.idExam = :qId").setParameter("qId", idSearchExam).getSingleResult();
    	
    	return searchExam;
    }

}
