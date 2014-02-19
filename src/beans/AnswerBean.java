package beans;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import model.Answer;
import model.Exam;
import model.Question;
import model.Student;

@Stateless
public class AnswerBean {
	
	@PersistenceContext(name="ExaminationPlatform")
	EntityManager em;
	
	public AnswerBean(){
	}
	
	public List<Answer> findAllAnswers(){
    	TypedQuery<Answer> theQuery = em.createQuery("SELECT a FROM Answer a", Answer.class);
    	
    	return theQuery.getResultList();
    }
    
    public List<Answer> findAllAnswersOfQuestion(Question question){
    	@SuppressWarnings("unchecked")
		TypedQuery<Answer> theQuery = (TypedQuery<Answer>)em.createQuery("SELECT a FROM Answer a WHERE a.question=:qQuestion")
    			.setParameter("qQuestion", question);
    	return theQuery.getResultList();
    }
    
    public Answer findAnswerOfQuestionStudent(Question question, Student student){
    	@SuppressWarnings("unchecked")
		TypedQuery<Answer> theQuery = (TypedQuery<Answer>)em.createQuery("SELECT a FROM Answer a WHERE a.question=:qQuestion AND a.student=:qStudent")
    			.setParameter("qQuestion", question)
    			.setParameter("qStudent", student);
    	try{
    		theQuery.getSingleResult();
    		return theQuery.getSingleResult();
    	}catch(Exception e){
    		return null;
    	}
    }
    
    public int findNbAnsweredQuestionOfExam(Exam exam, Student student){
    	@SuppressWarnings("unchecked")
		TypedQuery<Answer> theQuery = (TypedQuery<Answer>)em.createQuery("SELECT a FROM Answer a WHERE a.exam=:qExam AND a.student=:qStudent")
    			.setParameter("qExam", exam)
    			.setParameter("qStudent", student);
    	
		return theQuery.getResultList().size();
    }
    
    public float findNbPointOfExam(Exam exam, Student student){
    	@SuppressWarnings("unchecked")
		TypedQuery<Answer> theQuery = (TypedQuery<Answer>)em.createQuery("SELECT a FROM Answer a WHERE a.exam=:qExam AND a.student=:qStudent")
    			.setParameter("qExam", exam)
    			.setParameter("qStudent", student);
    	float nb = 0;
    	for(int i=0; i<theQuery.getResultList().size();i++){
    		nb+=theQuery.getResultList().get(i).getaPoint();
    	}
		return nb;
    }
    
    public int findNbPointMaxAnswered(Exam exam, Student student){
    	@SuppressWarnings("unchecked")
		TypedQuery<Answer> theQuery = (TypedQuery<Answer>)em.createQuery("SELECT a FROM Answer a WHERE a.exam=:qExam AND a.student=:qStudent")
    			.setParameter("qExam", exam)
    			.setParameter("qStudent", student);
    	int nb = 0;
    	for(int i=0; i<theQuery.getResultList().size();i++){
    	@SuppressWarnings("unchecked")
		TypedQuery<Question> theQuery2 = (TypedQuery<Question>)em.createQuery("SELECT q FROM Question q WHERE q=:qQuestion")
    			.setParameter("qQuestion", theQuery.getResultList().get(i).getQuestion());
    		nb+=theQuery2.getSingleResult().getqValue();
    	}
		return nb;
    }
    
    public void doInsert(Answer newAnswer){
    	em.persist(newAnswer);
    }
    
    public void doUpdate(Answer updtAnswer) {
        em.merge(updtAnswer);
    }
    
    public void doRemove(int idAnswer) {
    	Answer answer = findAnswer(idAnswer);
        if (answer != null) {
            em.remove(answer);
        }
    }
      
    public void doRemove(Answer answer) {
        if (answer != null && answer.getaName()!=null && em.contains(answer)) {
            em.remove(answer);
        }
    }
    
    public Answer findAnswer(int idSearchAnswer){
    	return em.find(Answer.class, idSearchAnswer);
    }

}
