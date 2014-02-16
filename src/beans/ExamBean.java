package beans;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import model.Exam;
import model.Question;
import model.Student;
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
    
    public List<Exam> findAllExams(Student student){
    	@SuppressWarnings("unchecked")
		TypedQuery<Exam> theQuery = (TypedQuery<Exam>) em.createQuery("SELECT e FROM Exam e, LT_StudentExam l WHERE e=l.exam AND l.student=:qStudent")
    			.setParameter("qStudent", student);
    	
    	return theQuery.getResultList();
    }
    
    public List<Exam> findAllExams(Teacher teacher){
    	@SuppressWarnings("unchecked")
		TypedQuery<Exam> theQuery = (TypedQuery<Exam>) em.createQuery("SELECT e FROM Exam e WHERE e.teacher=:qTeacher")
    			.setParameter("qTeacher", teacher);
    	
    	return theQuery.getResultList();
    }
    
    public List<Exam> findAllExamsBefore(Student student, Date date){
    	@SuppressWarnings("unchecked")
		TypedQuery<Exam> theQuery = (TypedQuery<Exam>) em.createQuery("SELECT e FROM Exam e, LT_StudentExam l WHERE e=l.exam AND l.student=:qStudent AND e.eDate<:qDate")
    			.setParameter("qStudent", student)
    			.setParameter("qDate", date);
    	
    	return theQuery.getResultList();
    }
    
    public List<Exam> findAllExamsBefore(Teacher teacher, Date date){
    	@SuppressWarnings("unchecked")
		TypedQuery<Exam> theQuery = (TypedQuery<Exam>) em.createQuery("SELECT e FROM Exam e WHERE e.teacher=:qTeacher AND e.eDate<:qDate")
    			.setParameter("qTeacher", teacher)
    			.setParameter("qDate", date);
    	
    	return theQuery.getResultList();
    }
    
    public List<Exam> findAllExamsAfter(Student student, Date date){
    	@SuppressWarnings("unchecked")
		TypedQuery<Exam> theQuery = (TypedQuery<Exam>) em.createQuery("SELECT e FROM Exam e, LT_StudentExam l WHERE e=l.exam AND l.student=:qStudent AND e.eDate>=:qDate")
    			.setParameter("qStudent", student)
    			.setParameter("qDate", date);
    	
    	return theQuery.getResultList();
    }
    
    public List<Exam> findAllExamsAfter(Teacher teacher, Date date){
    	@SuppressWarnings("unchecked")
		TypedQuery<Exam> theQuery = (TypedQuery<Exam>) em.createQuery("SELECT e FROM Exam e WHERE e.teacher=:qTeacher AND e.eDate>=:qDate")
    			.setParameter("qTeacher", teacher)
    			.setParameter("qDate", date);
    	
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
