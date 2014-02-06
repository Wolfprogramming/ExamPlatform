package model;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Time;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the Exam database table.
 * 
 */
@Entity
@NamedQuery(name="Exam.findAll", query="SELECT e FROM Exam e")
public class Exam implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idExam;

	@Temporal(TemporalType.TIMESTAMP)
	private Date eCreationDate;

	@Temporal(TemporalType.DATE)
	private Date eDate;

	@Lob
	private String eDesc;

	private Time eEndHour;

	@Temporal(TemporalType.TIMESTAMP)
	private Date eModifyDate;

	private String eName;

	private Time eStartHour;

	//bi-directional many-to-one association to Teacher
	@ManyToOne
	@JoinColumn(name="FK_idTeacher", nullable=false)
	private Teacher teacher;

	//bi-directional many-to-one association to Question
	@OneToMany(mappedBy="exam")
	private List<Question> questions;

	public Exam() {
	}

	public int getIdExam() {
		return this.idExam;
	}

	public void setIdExam(int idExam) {
		this.idExam = idExam;
	}

	public Date geteCreationDate() {
		return this.eCreationDate;
	}

	public void seteCreationDate(Date eCreationDate) {
		this.eCreationDate = eCreationDate;
	}

	public Date geteDate() {
		return this.eDate;
	}

	public void seteDate(Date eDate) {
		this.eDate = eDate;
	}

	public String geteDesc() {
		return this.eDesc;
	}

	public void seteDesc(String eDesc) {
		this.eDesc = eDesc;
	}

	public Time geteEndHour() {
		return this.eEndHour;
	}

	public void seteEndHour(Time eEndHour) {
		this.eEndHour = eEndHour;
	}

	public Date geteModifyDate() {
		return this.eModifyDate;
	}

	public void seteModifyDate(Date eModifyDate) {
		this.eModifyDate = eModifyDate;
	}

	public String geteName() {
		return this.eName;
	}

	public void seteName(String eName) {
		this.eName = eName;
	}

	public Time geteStartHour() {
		return this.eStartHour;
	}

	public void seteStartHour(Time eStartHour) {
		this.eStartHour = eStartHour;
	}

	public Teacher getTeacher() {
		return this.teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public List<Question> getQuestions() {
		return this.questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public Question addQuestion(Question question) {
		getQuestions().add(question);
		question.setExam(this);

		return question;
	}

	public Question removeQuestion(Question question) {
		getQuestions().remove(question);
		question.setExam(null);

		return question;
	}

}