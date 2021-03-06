package managedBeans;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

import javax.inject.Named;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import model.Exam;
import model.Teacher;
import beans.ExamBean;


@Named
@SessionScoped
public class ExamAddMB implements Serializable {

	private static final long serialVersionUID = 1L; 
	private String eName;
	private String eDesc;
	private Date eCreation;
	private Date eModify;
	private Date eDate;
	private Date eEndHour;
	private Date eStartHour;
	private boolean eIsCorrected;
	private Teacher teach;
	
	public ExamAddMB(){
    }
	
	@EJB
	private ExamBean theExam;
	
	public String geteName() { 
		return eName;
	}
	
	public void seteName(String theName) {
		this.eName = theName; 
	}
	
	public String geteDesc() { 
		return eDesc;
	}
	
	public void seteDesc(String theDesc) {
		this.eDesc = theDesc; 
	}
	
	public Date geteCreation() { 
		return eCreation;
	}
	
	public Date geteDate() {
		return eDate;
	}

	public void seteDate(Date eDate) {
		this.eDate = eDate;
	}

	public Date geteEndHour() {
		return eEndHour;
	}

	public void seteEndHour(Date eEndHour) {
		this.eEndHour = eEndHour;
	}

	public Date geteStartHour() {
		return eStartHour;
	}

	public void seteStartHour(Date eStartHour) {
		this.eStartHour = eStartHour;
	}
	

	public boolean iseIsCorrected() {
		return eIsCorrected;
	}

	public void seteIsCorrected(boolean eIsCorrected) {
		this.eIsCorrected = eIsCorrected;
	}

	public String saveExam(){ 
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		
		teach = (Teacher) ec.getSessionMap().get("teacher");
		
		eCreation = new Date(System.currentTimeMillis());
		eModify  = new Date(System.currentTimeMillis());
		
		Time startHour = new Time(eStartHour.getTime());
		Time endHour = new Time(eEndHour.getTime());
		
		Exam tmp = new Exam(); 
		tmp.seteName(eName); 
		tmp.seteDesc(eDesc);
		tmp.seteCreationDate(eCreation);
		tmp.seteModifyDate(eModify);
		tmp.seteDate(eDate);
		tmp.seteStartHour(startHour);
		tmp.seteEndHour(endHour);
		tmp.seteIsCorrected(false);
		tmp.setTeacher(teach);
		theExam.doInsert(tmp);
	
		ec.getSessionMap().put("exam", tmp);
		
		System.out.println("exam saved");
		
		seteName(null);
		seteDesc(null);
		seteDate(null);
		seteStartHour(null);
		seteEndHour(null);
		
		
		return "addQuestion";
	}
    
}
