package model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;


/**
 * The persistent class for the student database table.
 * 
 */
@Entity
@NamedQuery(name="Student.findAll", query="SELECT s FROM Student s")
public class Student implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idStudent;
	private String sFirstName;
	private String sEmail;
	private String sLastName;
	private String sPassword;

	//bi-directional many-to-one association to LT_StudentExam
	@OneToMany(mappedBy="student")
	private List<LT_StudentExam> ltStudentExams;

	public Student() {
	}

	public int getIdStudent() {
		return this.idStudent;
	}

	public void setIdStudent(int idStudent) {
		this.idStudent = idStudent;
	}

	public String getsEmail() {
		return this.sEmail;
	}

	public void setSEmail(String sEmail) {
		this.sEmail = sEmail;
	}

	public String getsFirstName() {
		return this.sFirstName;
	}

	public void setSFirstName(String sFirstName) {
		this.sFirstName = sFirstName;
	}

	public String getsLastName() {
		return this.sLastName;
	}

	public void setSLastName(String sLastName) {
		this.sLastName = sLastName;
	}

	public String getSPassword() {
		return this.sPassword;
	}

	public void setSPassword(String sPassword) {
		this.sPassword = sPassword;
	}

	public List<LT_StudentExam> getLtStudentExams() {
		return this.ltStudentExams;
	}

	public void setLtStudentExams(List<LT_StudentExam> ltStudentExams) {
		this.ltStudentExams = ltStudentExams;
	}

	public LT_StudentExam addLtStudentExam(LT_StudentExam ltStudentExam) {
		getLtStudentExams().add(ltStudentExam);
		ltStudentExam.setStudent(this);

		return ltStudentExam;
	}

	public LT_StudentExam removeLtStudentExam(LT_StudentExam ltStudentExam) {
		getLtStudentExams().remove(ltStudentExam);
		ltStudentExam.setStudent(null);

		return ltStudentExam;
	}

}