package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the LT_StudentExam database table.
 * 
 */
@Entity
@NamedQuery(name="LT_StudentExam.findAll", query="SELECT l FROM LT_StudentExam l")
public class LT_StudentExam implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idLT_StudentExam;
	
	private String ltStudentStatus;
	
	private String ltTeacherStatus;
	
	private float ltFinalGrade;

	//bi-directional many-to-one association to Exam
	@ManyToOne
	@JoinColumn(name="FK_idExam")
	private Exam exam;

	//bi-directional many-to-one association to Student
	@ManyToOne
	@JoinColumn(name="FK_idStudent")
	private Student student;

	public LT_StudentExam() {
	}

	public int getIdLT_StudentExam() {
		return this.idLT_StudentExam;
	}

	public void setIdLT_StudentExam(int idLT_StudentExam) {
		this.idLT_StudentExam = idLT_StudentExam;
	}

	public Exam getExam() {
		return this.exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}

	public Student getStudent() {
		return this.student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public String getltStudentStatus() {
		return ltStudentStatus;
	}

	public void setltStudentStatus(String ltStudentStatus) {
		this.ltStudentStatus = ltStudentStatus;
	}

	public String getltTeacherStatus() {
		return ltTeacherStatus;
	}

	public void setltTeacherStatus(String ltTeacherStatus) {
		this.ltTeacherStatus = ltTeacherStatus;
	}

	public float getltFinalGrade() {
		return ltFinalGrade;
	}

	public void setltFinalGrade(float ltFinalGrade) {
		this.ltFinalGrade = ltFinalGrade;
	}

}