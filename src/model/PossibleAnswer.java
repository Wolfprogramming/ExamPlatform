package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the PossibleAnswer database table.
 * 
 */
@Entity
@NamedQuery(name="PossibleAnswer.findAll", query="SELECT p FROM PossibleAnswer p")
public class PossibleAnswer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idPossibleAnswer;

	private byte paIsCorrect;

	private String paName;

	//bi-directional many-to-one association to Question
	@ManyToOne
	@JoinColumn(name="FK_idQuestion", nullable=false)
	private Question question;

	public PossibleAnswer() {
	}

	public int getIdPossibleAnswer() {
		return this.idPossibleAnswer;
	}

	public void setIdPossibleAnswer(int idPossibleAnswer) {
		this.idPossibleAnswer = idPossibleAnswer;
	}

	public byte getPaIsCorrect() {
		return this.paIsCorrect;
	}

	public void setPaIsCorrect(byte paIsCorrect) {
		this.paIsCorrect = paIsCorrect;
	}

	public String getPaName() {
		return this.paName;
	}

	public void setPaName(String paName) {
		this.paName = paName;
	}

	public Question getQuestion() {
		return this.question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

}