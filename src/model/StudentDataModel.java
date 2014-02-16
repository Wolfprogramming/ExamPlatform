package model;

import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

public class StudentDataModel extends ListDataModel<Student> implements SelectableDataModel<Student> {
	 
	public StudentDataModel() {
    }
  
    public StudentDataModel(List<Student> data) {
        super(data);
    }
    
    @Override
    public Student getRowData(String rowKey) {
        @SuppressWarnings("unchecked")
		List<Student> students = (List<Student>) getWrappedData();
        
        for(Student student : students) {
            if(student.getsFirstName().equals(rowKey))
                return student;
        }
        
        return null;
    }
  
    @Override
    public Object getRowKey(Student student) {
        return student.getsFirstName();
    }
}
