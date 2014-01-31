package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Teacher database table
 *  
 */
@Entity
@NamedQuery(name="Teacher.findAll", query="SELECT t FROM Teacher t")
public class Teacher implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idTeacher;

	private String tEmail;

	private String tFirstName;

	private String tLastName;

	private String tPassword;

	//bi-directional many-to-one association to Exam
	@OneToMany(mappedBy="teacher")
	private List<Exam> exams;

	public Teacher() {
	}
	
	public Teacher(String firstname, String lastname, String email, String password){
		this.tFirstName = firstname;
		this.tLastName = lastname;
		this.tEmail = email;
		this.tPassword = password;
	}

	public int getIdTeacher() {
		return this.idTeacher;
	}

	public void setIdTeacher(int idTeacher) {
		this.idTeacher = idTeacher;
	}

	public String getTEmail() {
		return this.tEmail;
	}

	public void setTEmail(String tEmail) {
		this.tEmail = tEmail;
	}

	public String getTFirstName() {
		return this.tFirstName;
	}

	public void setTFirstName(String tFirstName) {
		this.tFirstName = tFirstName;
	}

	public String getTLastName() {
		return this.tLastName;
	}

	public void setTLastName(String tLastName) {
		this.tLastName = tLastName;
	}

	public String getTPassword() {
		return this.tPassword;
	}

	public void setTPassword(String tPassword) {
		this.tPassword = tPassword;
	}

	public List<Exam> getExams() {
		return this.exams;
	}

	public void setExams(List<Exam> exams) {
		this.exams = exams;
	}

	public Exam addExam(Exam exam) {
		getExams().add(exam);
		exam.setteacher(this);

		return exam;
	}

	public Exam removeExam(Exam exam) {
		getExams().remove(exam);
		exam.setteacher(null);

		return exam;
	}

}