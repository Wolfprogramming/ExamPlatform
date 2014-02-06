package beans;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import model.Question;


/**
 * Session Bean implementation class ExamBean
 */
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
    
    public Question findQuestion(int idSearchQuestion){
    	return em.find(Question.class, idSearchQuestion);
    }

}
