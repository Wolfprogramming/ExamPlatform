package beans;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import model.Exam;
import model.Teacher;


/**
 * Session Bean implementation class ExamBean
 */
@Stateless
public class ExamBean {

	@PersistenceContext(name="ExaminationPlatform")
	EntityManager em;

    public ExamBean() {
    }
    
    public List<Exam> findAllExams(){
    	TypedQuery<Exam> theQuery = em.createQuery("SELECT e FROM Exam e", Exam.class);
    	
    	return theQuery.getResultList();
    }
    
    public void doInsert(Exam newExam){
    	em.persist(newExam);
    }
    
    public void doUpdate(Exam updtExam) {
        em.merge(updtExam);
    }
    
    public void doRemove(int idExam) {
    	Exam exam = findExam(idExam);
        if (exam != null) {
            em.remove(exam);
        }
    }
      
    public void doRemove(Exam exam) {
        if (exam != null && exam.geteCreationDate()!=null && em.contains(exam)) {
            em.remove(exam);
        }
    }
    
    public Exam findExam(int idSearchExam){
    	return em.find(Exam.class, idSearchExam);
    }

}
