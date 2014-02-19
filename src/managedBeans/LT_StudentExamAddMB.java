package managedBeans;

import java.io.Serializable;

import javax.inject.Named;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;

import model.Exam;
import model.Student;
import beans.LT_StudentExamBean;


@Named
@SessionScoped
public class LT_StudentExamAddMB implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Student student;
	private Exam exam;
	private String ltStudentStatus;
	private String ltTeacherStatus;
	private int ltFinalGrade;

	public LT_StudentExamAddMB(){
	}
	
	@EJB
	private LT_StudentExamBean theLink;

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Exam getExam() {
		return exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}

	public String getLtStudentStatus() {
		return ltStudentStatus;
	}

	public void setLtStudentStatus(String ltStudentStatus) {
		this.ltStudentStatus = ltStudentStatus;
	}

	public String getLtTeacherStatus() {
		return ltTeacherStatus;
	}

	public void setLtTeacherStatus(String ltTeacherStatus) {
		this.ltTeacherStatus = ltTeacherStatus;
	}

	public int getLtFinalGrade() {
		return ltFinalGrade;
	}

	public void setLtFinalGrade(int ltFinalGrade) {
		this.ltFinalGrade = ltFinalGrade;
	}
}
