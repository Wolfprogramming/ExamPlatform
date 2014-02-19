package model;

import java.io.Serializable;
import javax.persistence.*;
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
	
	@Temporal(TemporalType.TIME)
	private Date eEndHour;

	@Temporal(TemporalType.TIMESTAMP)
	private Date eModifyDate;

	private String eName;

	@Temporal(TemporalType.TIME)
	private Date eStartHour;
	
	private boolean eIsCorrected;

	//bi-directional many-to-one association to Teacher
	@ManyToOne
	@JoinColumn(name="FK_idTeacher", nullable=false)
	private Teacher teacher;

	//bi-directional many-to-one association to Question
	@OneToMany(mappedBy="exam")
	private List<Question> questions;
	
	//bi-directional many-to-one association to LT_StudentExam
	@OneToMany(mappedBy="exam")
	private List<LT_StudentExam> ltStudentExams;

	//bi-directional many-to-one association to Answer
	@OneToMany(mappedBy="exam")
	private List<Answer> answers;

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

	public Date geteEndHour() {
		return this.eEndHour;
	}

	public void seteEndHour(Date eEndHour) {
		this.eEndHour = eEndHour;
	}

	public boolean geteIsCorrected() {
		return this.eIsCorrected;
	}

	public void seteIsCorrected(boolean eIsCorrected) {
		this.eIsCorrected = eIsCorrected;
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

	public Date geteStartHour() {
		return this.eStartHour;
	}

	public void seteStartHour(Date eStartHour) {
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
	
	public List<LT_StudentExam> getLtStudentExams() {
		return this.ltStudentExams;
	}

	public void setLtStudentExams(List<LT_StudentExam> ltStudentExams) {
		this.ltStudentExams = ltStudentExams;
	}

	public LT_StudentExam addLtStudentExam(LT_StudentExam ltStudentExam) {
		getLtStudentExams().add(ltStudentExam);
		ltStudentExam.setExam(this);

		return ltStudentExam;
	}

	public LT_StudentExam removeLtStudentExam(LT_StudentExam ltStudentExam) {
		getLtStudentExams().remove(ltStudentExam);
		ltStudentExam.setExam(null);

		return ltStudentExam;
	}

	public List<Answer> getAnswers() {
		return this.answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	public Answer addAnswer(Answer answer) {
		getAnswers().add(answer);
		answer.setExam(this);

		return answer;
	}

	public Answer removeAnswer(Answer answer) {
		getAnswers().remove(answer);
		answer.setExam(null);

		return answer;
	}

}