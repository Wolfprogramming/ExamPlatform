package beans;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import model.Exam;
import model.LT_StudentExam;
import model.Student;

@Stateless
public class LT_StudentExamBean {
	
	@PersistenceContext(name="ExaminationPlatform")
	EntityManager em;

	public LT_StudentExamBean(){
	}
	
	
	public List<LT_StudentExam> findAllLT_StudentExam(){
    	TypedQuery<LT_StudentExam> theQuery = em.createQuery("SELECT lt FROM LT_StudentExam lt", LT_StudentExam.class);
    	
    	return theQuery.getResultList();
    }
    
    public List<LT_StudentExam> findAllStudentsOfExam(Exam exam){
    	@SuppressWarnings("unchecked")
		TypedQuery<LT_StudentExam> theQuery = (TypedQuery<LT_StudentExam>)em.createQuery("SELECT lt FROM LT_StudentExam lt WHERE lt.exam=:qExam")
    			.setParameter("qExam", exam);
    	return theQuery.getResultList();
    }
    
    public List<LT_StudentExam> findAllExamsOfStudent(Student student){
    	@SuppressWarnings("unchecked")
		TypedQuery<LT_StudentExam> theQuery = (TypedQuery<LT_StudentExam>)em.createQuery("SELECT lt FROM LT_StudentExam lt WHERE lt.student=:qStudent")
    			.setParameter("qStudent", student);
    	return theQuery.getResultList();
    }
    
    public List<LT_StudentExam> findAllPendingExamsOfStudent(Student student){
    	@SuppressWarnings("unchecked")
		TypedQuery<LT_StudentExam> theQuery = (TypedQuery<LT_StudentExam>)em.createQuery("SELECT lt FROM LT_StudentExam lt "
				+ "WHERE lt.student=:qStudent AND lt.ltStudentStatus=:qStudentStatus")
    			.setParameter("qStudent", student)
    			.setParameter("qStudentStatus", "pending");
    	return theQuery.getResultList();
    }
    
    public List<LT_StudentExam> findAllSubmittedExamsOfStudent(Student student){
    	@SuppressWarnings("unchecked")
		TypedQuery<LT_StudentExam> theQuery = (TypedQuery<LT_StudentExam>)em.createQuery("SELECT lt FROM LT_StudentExam lt "
				+ "WHERE lt.student=:qStudent AND lt.ltStudentStatus=:qStudentStatus")
    			.setParameter("qStudent", student)
    			.setParameter("qStudentStatus", "submitted");
    	return theQuery.getResultList();
    }
    
    public List<LT_StudentExam> findAllPendingExamsOfTeacher(Exam exam){
    	@SuppressWarnings("unchecked")
		TypedQuery<LT_StudentExam> theQuery = (TypedQuery<LT_StudentExam>)em.createQuery("SELECT lt FROM LT_StudentExam lt "
				+ "WHERE lt.exam=:qExam AND lt.ltTeacherStatus=:qTeacherStatus")
    			.setParameter("qExam", exam)
    			.setParameter("qTeacherStatus", "pending");
    	return theQuery.getResultList();
    }
    
    public List<LT_StudentExam> findAllCorrectedExamsOfTeacher(Exam exam){
    	@SuppressWarnings("unchecked")
		TypedQuery<LT_StudentExam> theQuery = (TypedQuery<LT_StudentExam>)em.createQuery("SELECT lt FROM LT_StudentExam lt "
				+ "WHERE lt.exam=:qExam AND lt.ltTeacherStatus=:qTeacherStatus")
    			.setParameter("qExam", exam)
    			.setParameter("qTeacherStatus", "corrected");
    	return theQuery.getResultList();
    }

    public LT_StudentExam findLT_StudentExam(Exam exam, Student student){
    	@SuppressWarnings("unchecked")
		TypedQuery<LT_StudentExam> theQuery = (TypedQuery<LT_StudentExam>)em.createQuery("SELECT lt FROM LT_StudentExam lt "
				+ "WHERE lt.exam=:qExam AND lt.student=:qStudent")
    			.setParameter("qExam", exam)
    			.setParameter("qStudent", student);
		
		try{
    		theQuery.getSingleResult();
    		return theQuery.getSingleResult();
    	}catch(Exception e){
    		return null;
    	}
    }
    
    public void doInsert(LT_StudentExam newLT_StudentExam){
    	em.persist(newLT_StudentExam);
    }
    
    public void doUpdate(LT_StudentExam updtLT_StudentExam) {
        em.merge(updtLT_StudentExam);
    }
    
    public void doRemove(int idLT_StudentExam) {
    	LT_StudentExam lt_StudentExam = findLT_StudentExam(idLT_StudentExam);
        if (lt_StudentExam != null) {
            em.remove(lt_StudentExam);
        }
    }
      
    public void doRemove(LT_StudentExam lt_StudentExam) {
        if (lt_StudentExam != null && em.contains(lt_StudentExam)) {
            em.remove(lt_StudentExam);
        }
    }
    
    public LT_StudentExam findLT_StudentExam(int idLT_StudentExam){
    	return em.find(LT_StudentExam.class, idLT_StudentExam);
    }
}
