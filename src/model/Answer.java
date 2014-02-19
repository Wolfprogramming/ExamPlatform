package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Answer database table.
 * 
 */
@Entity
@NamedQuery(name="Answer.findAll", query="SELECT a FROM Answer a")
public class Answer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idAnswer;

	@Lob
	private String aComment;

	private boolean aIsCorrect;

	@Lob
	private String aName;

	private float aPoint;

	//bi-directional many-to-one association to Exam
	@ManyToOne
	@JoinColumn(name="FK_idExam")
	private Exam exam;

	//bi-directional many-to-one association to Question
	@ManyToOne
	@JoinColumn(name="FK_idQuestion")
	private Question question;

	//bi-directional many-to-one association to Student
	@ManyToOne
	@JoinColumn(name="FK_idStudent")
	private Student student;

	public Answer() {
	}

	public int getIdAnswer() {
		return this.idAnswer;
	}

	public void setIdAnswer(int idAnswer) {
		this.idAnswer = idAnswer;
	}

	public String getaComment() {
		return this.aComment;
	}

	public void setaComment(String aComment) {
		this.aComment = aComment;
	}

	public boolean getaIsCorrect() {
		return this.aIsCorrect;
	}

	public void setaIsCorrect(boolean aIsCorrect) {
		this.aIsCorrect = aIsCorrect;
	}

	public String getaName() {
		return this.aName;
	}

	public void setaName(String aName) {
		this.aName = aName;
	}

	public float getaPoint() {
		return this.aPoint;
	}

	public void setaPoint(float i) {
		this.aPoint = i;
	}

	public Exam getExam() {
		return this.exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}

	public Question getQuestion() {
		return this.question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public Student getStudent() {
		return this.student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

}