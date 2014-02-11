package beans;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import model.Exam;
import model.Question;


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
    
    public int findNbPoint(Exam exam){
    	int pt=0;
    	List<Question> q = em.find(Exam.class, exam.getIdExam()).getQuestions();
    	
    	for(int i=0; i<q.size();i++)
    		pt+=q.get(i).getqValue();
    	return pt;
    }

}
