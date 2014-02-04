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

	public String gettEmail() {
		return this.tEmail;
	}

	public void settEmail(String tEmail) {
		this.tEmail = tEmail;
	}

	public String gettFirstName() {
		return this.tFirstName;
	}

	public void settFirstName(String tFirstName) {
		this.tFirstName = tFirstName;
	}

	public String gettLastName() {
		return this.tLastName;
	}

	public void settLastName(String tLastName) {
		this.tLastName = tLastName;
	}

	public String gettPassword() {
		return this.tPassword;
	}

	public void settPassword(String tPassword) {
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