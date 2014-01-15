package model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Time;
import java.util.Date;


/**
 * The persistent class for the exam database table.
 * 
 */
@Entity
@NamedQuery(name="Exam.findAll", query="SELECT e FROM Exam e")
public class Exam implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
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
	@JoinColumn(name="FK_idTeacher")
	private Teacher teacher;

	public Exam() {
	}

	public int getIdExam() {
		return this.idExam;
	}

	public void setIdExam(int idExam) {
		this.idExam = idExam;
	}

	public Date getECreationDate() {
		return this.eCreationDate;
	}

	public void setECreationDate(Date eCreationDate) {
		this.eCreationDate = eCreationDate;
	}

	public Date getEDate() {
		return this.eDate;
	}

	public void setEDate(Date eDate) {
		this.eDate = eDate;
	}

	public String getEDesc() {
		return this.eDesc;
	}

	public void setEDesc(String eDesc) {
		this.eDesc = eDesc;
	}

	public Time getEEndHour() {
		return this.eEndHour;
	}

	public void setEEndHour(Time eEndHour) {
		this.eEndHour = eEndHour;
	}

	public Date getEModifyDate() {
		return this.eModifyDate;
	}

	public void setEModifyDate(Date eModifyDate) {
		this.eModifyDate = eModifyDate;
	}

	public String getEName() {
		return this.eName;
	}

	public void setEName(String eName) {
		this.eName = eName;
	}

	public Time getEStartHour() {
		return this.eStartHour;
	}

	public void setEStartHour(Time eStartHour) {
		this.eStartHour = eStartHour;
	}

	public Teacher getTeacher() {
		return this.teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

}