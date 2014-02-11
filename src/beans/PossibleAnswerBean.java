package beans;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import model.PossibleAnswer;


/**
 * Session Bean implementation class ExamBean
 */
@Stateless
public class PossibleAnswerBean {
	@PersistenceContext(name="ExaminationPlatform")
	EntityManager em;

    public PossibleAnswerBean() {
    }
    
    public List<PossibleAnswer> findAllPA(){
    	TypedQuery<PossibleAnswer> theQuery = em.createQuery("SELECT pa FROM PossibleAnswer pa", PossibleAnswer.class);
    	
    	return theQuery.getResultList();
    }
    
    public List<PossibleAnswer> findAllPAOfQuestion(int idQuestion){
    	@SuppressWarnings("unchecked")
		TypedQuery<PossibleAnswer> theQuery = (TypedQuery<PossibleAnswer>)em.createQuery("SELECT pa FROM PossibleAnswer pa WHERE pa.FK_idQuestion=:qIdQuestion")
    			.setParameter("qIdQuestion", idQuestion);
    	return theQuery.getResultList();
    }
    
    public void doInsert(PossibleAnswer newPA){
    	em.persist(newPA);
    }
    
    public void doUpdate(PossibleAnswer updtPA) {
        em.merge(updtPA);
    }
    
    public void doRemove(int idPossibleAnswer) {
    	PossibleAnswer pa = findPA(idPossibleAnswer);
        if (pa != null) {
            em.remove(pa);
        }
    }
      
    public void doRemove(PossibleAnswer pa) {
        if (pa != null && pa.getPaName()!=null && em.contains(pa)) {
            em.remove(pa);
        }
    }
    
    public PossibleAnswer findPA(int idSearchPA){
    	return em.find(PossibleAnswer.class, idSearchPA);
    }
}
