package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Question database table.
 * 
 */
@Entity
@NamedQuery(name="Question.findAll", query="SELECT q FROM Question q")
public class Question implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idQuestion;

	@Lob
	private String qHelp;

	private String qName;

	private int qPosition;

	private String qType;

	private short qValue;

	//bi-directional many-to-one association to PossibleAnswer
	@OneToMany(mappedBy="question")
	private List<PossibleAnswer> possibleAnswers;

	//bi-directional many-to-one association to Exam
	@ManyToOne
	@JoinColumn(name="FK_idExam", nullable=false)
	private Exam exam;

	public Question() {
	}

	public int getIdQuestion() {
		return this.idQuestion;
	}

	public void setIdQuestion(int idQuestion) {
		this.idQuestion = idQuestion;
	}

	public String getqHelp() {
		return this.qHelp;
	}

	public void setqHelp(String qHelp) {
		this.qHelp = qHelp;
	}

	public String getqName() {
		return this.qName;
	}

	public void setqName(String qName) {
		this.qName = qName;
	}

	public int getqPosition() {
		return this.qPosition;
	}

	public void setqPosition(int qPosition) {
		this.qPosition = qPosition;
	}

	public String getqType() {
		return this.qType;
	}

	public void setqType(String qType) {
		this.qType = qType;
	}

	public short getqValue() {
		return this.qValue;
	}

	public void setqValue(short qValue) {
		this.qValue = qValue;
	}

	public List<PossibleAnswer> getPossibleAnswers() {
		return this.possibleAnswers;
	}

	public void setPossibleAnswers(List<PossibleAnswer> possibleAnswers) {
		this.possibleAnswers = possibleAnswers;
	}

	public PossibleAnswer addPossibleAnswer(PossibleAnswer possibleAnswer) {
		getPossibleAnswers().add(possibleAnswer);
		possibleAnswer.setQuestion(this);

		return possibleAnswer;
	}

	public PossibleAnswer removePossibleAnswer(PossibleAnswer possibleAnswer) {
		getPossibleAnswers().remove(possibleAnswer);
		possibleAnswer.setQuestion(null);

		return possibleAnswer;
	}

	public Exam getExam() {
		return this.exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}

}