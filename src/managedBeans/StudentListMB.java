package managedBeans;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.primefaces.event.SelectEvent;

import beans.LT_StudentExamBean;
import beans.QuestionBean;
import beans.StudentBean;
import model.Exam;
import model.LT_StudentExam;
import model.Question;
import model.Student;
import model.StudentDataModel;


@Named
@SessionScoped
public class StudentListMB implements Serializable {


	private static final long serialVersionUID = 1L;
	private List<Student> subscribedStudent;
	private List<Student> allStudents;
	private List<Student> filteredStudents;
	private Student[] selectedStudents;
	private StudentDataModel studentsModel;
	private Exam selectedExam;
	private List<LT_StudentExam> listLink;
	private Student deleteStudent;
	private SelectItem[] statusExamOptions;
	private StudentDataModel subscribedStudentsModel;
	private Student selectedStudent;
	private int nbPointExam;
	private int nbCorrectedExam;
	private int nbTotalExam;
	private float nbPointAllStudents;
	private float minGrade;
	private float maxGrade;	
	
	@EJB
	private StudentBean theStudents;
	@EJB
	private LT_StudentExamBean theLink;
	@EJB
	private QuestionBean theQuestion;
	
	@PostConstruct
	public void init(){
    	allStudents = theStudents.findAllStudents();
		studentsModel = new StudentDataModel(allStudents);
		
		statusExamOptions = new SelectItem[3];
		statusExamOptions[0] = new SelectItem("", "Select");
		statusExamOptions[1] = new SelectItem("pending", "Pending");
		statusExamOptions[2] = new SelectItem("corrected", "Corrected");
	}
	
	
    public StudentListMB() {
    	
    }

	public List<Student> getfilteredStudents() {
		return filteredStudents;
	}

	public void setfilteredStudents(List<Student> filteredStudents) {
		this.filteredStudents = filteredStudents;
	}

	public Student[] getSelectedStudents() {
		return selectedStudents;
	}

	public void setSelectedStudents(Student[] selectedStudents) {
		this.selectedStudents = selectedStudents;
	}

	public StudentDataModel getStudentsModel() {
		return studentsModel;
	}

	public void setStudentsModel(StudentDataModel studentsModel) {
		this.studentsModel = studentsModel;
	}

	public Exam getSelectedExam() {
		return selectedExam;
	}

	public void setSelectedExam(Exam selectedExam) {
		this.selectedExam = selectedExam;
		if(selectedExam!=null){
			setListLink(theLink.findAllStudentsOfExam(selectedExam));
			List<Student> studentList = new ArrayList<Student>();
			for(int i=0;i<listLink.size();i++){
				studentList.add(listLink.get(i).getStudent());
			}
			setsubscribedStudent(studentList);
			subscribedStudentsModel = new StudentDataModel(subscribedStudent);
			List<Question> q = theQuestion.findQuestionsOfExams(selectedExam);
			nbPointExam = 0;
			for(int i=0; i<q.size();i++)
				nbPointExam += q.get(i).getqValue();
			try{
			List<LT_StudentExam> lt = theLink.findAllCorrectedExamsOfTeacher(selectedExam);
			nbCorrectedExam = lt.size();
			nbTotalExam = listLink.size();
			minGrade=lt.get(0).getltFinalGrade();
			maxGrade=lt.get(0).getltFinalGrade();
			nbPointAllStudents=0;
			for(int i =0;i<nbCorrectedExam;i++){
				nbPointAllStudents += lt.get(i).getltFinalGrade();
				if(lt.get(i).getltFinalGrade()<minGrade)
					minGrade = lt.get(i).getltFinalGrade();
				if(lt.get(i).getltFinalGrade()>maxGrade)
					maxGrade = lt.get(i).getltFinalGrade();
			}
			}
			catch(Exception e){
				nbCorrectedExam = 0;
				nbTotalExam = 0;
				minGrade = 0;
				maxGrade = 0;
				nbPointAllStudents = 0;
			}
		}
	}

	public List<Student> getsubscribedStudent() {
		return subscribedStudent;
	}

	public void setsubscribedStudent(List<Student> subscribedStudent) {
		this.subscribedStudent = subscribedStudent;
	}

	public List<Student> getAllStudents() {
		return allStudents;
	}

	public List<LT_StudentExam> getListLink() {
		return listLink;
	}

	public void setListLink(List<LT_StudentExam> listLink) {
		this.listLink = listLink;
	}
	
	public Student getDeleteStudent() {
		return deleteStudent;
	}

	public void setDeleteStudent(Student deleteStudent) {
		this.deleteStudent = deleteStudent;
	}


	public SelectItem[] getStatusExamOptions() {
		return statusExamOptions;
	}

	public StudentDataModel getSubscribedStudentsModel() {
		return subscribedStudentsModel;
	}


	public void setSubscribedStudentsModel(StudentDataModel subscribedStudentsModel) {
		this.subscribedStudentsModel = subscribedStudentsModel;
	}


	public Student getSelectedStudent() {
		return selectedStudent;
	}


	public void setSelectedStudent(Student selectedStudent) {
		this.selectedStudent = selectedStudent;
	}
	
	public int getNbPointExam() {
		return nbPointExam;
	}


	public void setNbPointExam(int nbPointExam) {
		this.nbPointExam = nbPointExam;
	}


	public int getNbTotalExam() {
		return nbTotalExam;
	}


	public void setNbTotalExam(int nbTotalExam) {
		this.nbTotalExam = nbTotalExam;
	}


	public int getNbCorrectedExam() {
		return nbCorrectedExam;
	}


	public void setNbCorrectedExam(int nbCorrectedExam) {
		this.nbCorrectedExam = nbCorrectedExam;
	}


	public float getNbPointAllStudents() {
		return nbPointAllStudents;
	}


	public void setNbPointAllStudents(float nbPointAllStudents) {
		this.nbPointAllStudents = nbPointAllStudents;
	}


	public float getMaxGrade() {
		return maxGrade;
	}


	public void setMaxGrade(float maxGrade) {
		this.maxGrade = maxGrade;
	}


	public float getMinGrade() {
		return minGrade;
	}


	public void setMinGrade(float minGrade) {
		this.minGrade = minGrade;
	}


	public void clear(){
		setSelectedStudent(null);
	}
	
	
	public void onRowSelect(SelectEvent event) throws IOException {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		externalContext.redirect(externalContext.getRequestContextPath() + "/correctExam.xhtml");
    }


	public String statusExam(Student student){
		String status="Unknown";
		LT_StudentExam lt = theLink.findLT_StudentExam(selectedExam, student);
		if(lt!=null){
			status = lt.getltTeacherStatus();
			status = status.substring(0, 1).toUpperCase().concat(status.substring(1));
		}
		return status;
	}
	
	public LT_StudentExam statusMarkExam(Student student, Exam exam){
		LT_StudentExam lt = theLink.findLT_StudentExam(exam, student);
		if(lt!=null){
			List<Question> q = theQuestion.findQuestionsOfExams(exam);
			nbPointExam = 0;
			for(int i=0; i<q.size();i++)
				nbPointExam += q.get(i).getqValue();
			return lt;
		}
		return null;
	}


	public String deleteStudentList(){
		System.out.println("enter delete student from list");
		if(deleteStudent!=null){
			subscribedStudent.remove(deleteStudent);
			theLink.doRemove(theLink.findLT_StudentExam(selectedExam, deleteStudent).getIdLT_StudentExam());
		}
		
		return "attributeStudent";
	}
    
	public String addStudentList(){
		System.out.println("enter add students to list");
		
		if(selectedStudents!=null){
			for(int i=0; i<selectedStudents.length; i++){
				if(theLink.findLT_StudentExam(selectedExam, selectedStudents[i])==null){
					subscribedStudent.add(selectedStudents[i]);
					LT_StudentExam tmp = new LT_StudentExam();
					tmp.setExam(selectedExam);
					tmp.setStudent(selectedStudents[i]);
					tmp.setltStudentStatus("pending");
					tmp.setltTeacherStatus("pending");
					theLink.doInsert(tmp);
				}
			}
			setSelectedStudents(null);
		}
		
		return "attributeStudent";
	}
	
	public String submitCorrection(float finalGrade){
		System.out.println("Enter submit correction ");
		
		LT_StudentExam tmp = theLink.findLT_StudentExam(selectedExam, selectedStudent);
		tmp.setltFinalGrade(finalGrade);
		tmp.setltTeacherStatus("corrected");
		theLink.doUpdate(tmp);
		
		return "listExamsCorrection";
	}

}
