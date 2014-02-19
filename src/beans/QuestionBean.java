package beans;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import model.Exam;
import model.Question;


@Stateful
public class QuestionBean {

	@PersistenceContext(name="ExaminationPlatform")
	EntityManager em;

    public QuestionBean() {
    }
    
    public List<Question> findAllQuestions(){
    	TypedQuery<Question> theQuery = em.createQuery("SELECT q FROM Question q", Question.class);
    	
    	return theQuery.getResultList();
    }
    
    public void doInsert(Question newQuestion){
    	em.persist(newQuestion);
    }
    
    public void doUpdate(Question updtQuestion) {
        em.merge(updtQuestion);
    }
    
    public void doRemove(int idQuestion) {
    	Question q = findQuestion(idQuestion);
        if (q != null) {
            em.remove(q);
        }
    }
      
    public void doRemove(Question q) {
        if (q != null && q.getqName()!=null && em.contains(q)) {
            em.remove(q);
        }
    }
    
    public Question findQuestion(int idSearchQuestion){
    	return em.find(Question.class, idSearchQuestion);
    }
    
    public List<Question> findQuestionsOfExams(Exam exam){
    	@SuppressWarnings("unchecked")
		TypedQuery<Question> theQuery = (TypedQuery<Question>)em.createQuery("SELECT q FROM Question q WHERE q.exam=:qExam ORDER BY q.qPosition")
    			.setParameter("qExam", exam);
    	return theQuery.getResultList();
    }

}
