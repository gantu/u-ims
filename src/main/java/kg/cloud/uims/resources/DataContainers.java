package kg.cloud.uims.resources;

import java.util.ArrayList;

import kg.cloud.uims.MyVaadinApplication;
import kg.cloud.uims.dao.DbStudLess;
import kg.cloud.uims.dao.DbStudent;
import kg.cloud.uims.dao.DbSubjects;
import kg.cloud.uims.domain.StudLess;
import kg.cloud.uims.domain.Student;
import kg.cloud.uims.domain.Subjects;
import kg.cloud.uims.i18n.UimsMessages;

import com.vaadin.data.Item;
import com.vaadin.data.util.IndexedContainer;

public class DataContainers {
	private MyVaadinApplication app;
	public String studContainer_PROPERTY_NAME;
	public String studContainer_PROPERTY_SURNAME;
	public String studContainer_PROPERTY_STATUS;

	public String PROPERTY_SUBJECT_NAME;
	public String PROPERTY_SUBJECT_CODE;
	public String PROPERTY_SUBJECT_CREDIT;
	public String PROPERTY_SUBJECT_HOUR;
	public String PROPERTY_DEPARTMENT_CODE;
	public String PROPERTY_DEPARTMENT_NAME;
	public String PROPERTY_SEMESTER;
	public String PROPERTY_STDYEAR;
	public String PROPERTY_SUBJECT_REGISTRATION_STATUS;
	public String studContainer_PROPERTY_ACC_STATUS;

	public DataContainers(MyVaadinApplication app) {
		this.app = app;
		studContainer_PROPERTY_NAME = app
				.getMessage(UimsMessages.RegistrationStudentName);
		studContainer_PROPERTY_SURNAME = app
				.getMessage(UimsMessages.RegistrationStudentSurname);
		studContainer_PROPERTY_STATUS = app
				.getMessage(UimsMessages.RegistrationStudentEduStatus);
		PROPERTY_SUBJECT_NAME = app.getMessage(UimsMessages.SubjectName);
		PROPERTY_SUBJECT_CODE = app.getMessage(UimsMessages.SubjectCode);
		PROPERTY_SUBJECT_CREDIT = app.getMessage(UimsMessages.SubjectCredit);
		PROPERTY_SUBJECT_HOUR = app.getMessage(UimsMessages.SubjectHour);
		PROPERTY_DEPARTMENT_CODE = app.getMessage(UimsMessages.DepartmentCode);
		PROPERTY_DEPARTMENT_NAME = app.getMessage(UimsMessages.DepartmentName);
		PROPERTY_SEMESTER = app.getMessage(UimsMessages.Semester);
		PROPERTY_STDYEAR = app.getMessage(UimsMessages.StudyYear);
		PROPERTY_SUBJECT_REGISTRATION_STATUS = app
				.getMessage(UimsMessages.SubjectRegistrationStatus);
		studContainer_PROPERTY_ACC_STATUS = app.getMessage(UimsMessages.AccountingStatus);
	}

	public DataContainers() {
	}

	public IndexedContainer getStudentByGroupContainer(String groupId) {

		IndexedContainer studentContainer = new IndexedContainer();
		studentContainer.addContainerProperty(studContainer_PROPERTY_NAME,
				String.class, null);
		studentContainer.addContainerProperty(studContainer_PROPERTY_SURNAME,
				String.class, null);
		
		studentContainer.addContainerProperty(studContainer_PROPERTY_ACC_STATUS, String.class,null);
		try {
			DbStudent dbStudent = new DbStudent();
			dbStudent.connect();
			dbStudent.execSQL_GR(groupId);
			ArrayList<Student> studentList = dbStudent.getArray();
			dbStudent.close();

			for (int i = 0; i < studentList.size(); i++) {
				String id = Integer.toString(studentList.get(i).getID());
				Item item = studentContainer.addItem(id);
				item.getItemProperty(studContainer_PROPERTY_NAME).setValue(
						studentList.get(i).getName());
				item.getItemProperty(studContainer_PROPERTY_SURNAME).setValue(
						studentList.get(i).getSurname());
				
				if(studentList.get(i).getAcc_status().equals("released"))
					item.getItemProperty(studContainer_PROPERTY_ACC_STATUS).setValue("Can Register");
				else 
					item.getItemProperty(studContainer_PROPERTY_ACC_STATUS).setValue("Can not Register");

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		studentContainer.sort(new Object[] { studContainer_PROPERTY_NAME },
				new boolean[] { true });
		return studentContainer;

	}

	public IndexedContainer getStudentRegisteredSubjects(String studId,
			String semId, String yearId) {
		IndexedContainer container = new IndexedContainer();
		container.addContainerProperty(PROPERTY_SUBJECT_CODE, String.class,
				null);
		container.addContainerProperty(PROPERTY_SUBJECT_NAME, String.class,
				null);
		container.addContainerProperty(PROPERTY_SEMESTER, String.class,
				null);
		container
				.addContainerProperty(PROPERTY_STDYEAR, String.class, null);
		container.addContainerProperty(PROPERTY_SUBJECT_HOUR, String.class,
				null);
		container.addContainerProperty(PROPERTY_SUBJECT_CREDIT,
				String.class, null);
		container.addContainerProperty(
				PROPERTY_SUBJECT_REGISTRATION_STATUS, String.class, null);
		try {
			DbStudLess studLess = new DbStudLess();
			studLess.connect();
			studLess.execSQL(studId, semId, yearId);
			ArrayList<StudLess> studLessList = studLess.getArray();
			studLess.close();
			

			for (int i = 0; i < studLessList.size(); i++) {
				String id = Integer.toString(studLessList.get(i).getSubjID());
				Item item = container.addItem(id);
				item.getItemProperty(PROPERTY_SUBJECT_CODE).setValue(
						studLessList.get(i).getSubjCode());
				item.getItemProperty(PROPERTY_SUBJECT_NAME).setValue(
						studLessList.get(i).getSubjName());
				item.getItemProperty(PROPERTY_SEMESTER).setValue(
						studLessList.get(i).getSemester());
				item.getItemProperty(PROPERTY_STDYEAR).setValue(
						studLessList.get(i).getEdYear());
				item.getItemProperty(PROPERTY_SUBJECT_HOUR).setValue(
						studLessList.get(i).getSubHour());
				item.getItemProperty(PROPERTY_SUBJECT_CREDIT).setValue(
						studLessList.get(i).getSubCredit());
				item.getItemProperty(PROPERTY_SUBJECT_REGISTRATION_STATUS)
						.setValue(studLessList.get(i).getStatus());
			}
		} catch (Exception e) {
			e.printStackTrace();

		}

		return container;

	}

	public IndexedContainer getStudentNotTookYetSubjects(String studentId,
			String semId){

		
		IndexedContainer container = new IndexedContainer();
		container.addContainerProperty(PROPERTY_SUBJECT_CODE, String.class,
				null);
		container.addContainerProperty(PROPERTY_SUBJECT_NAME, String.class,
				null);
		container.addContainerProperty(PROPERTY_SEMESTER, String.class, null);
		container.addContainerProperty(PROPERTY_STDYEAR, String.class, null);
		container.addContainerProperty(PROPERTY_SUBJECT_HOUR, String.class,
				null);
		container.addContainerProperty(PROPERTY_SUBJECT_CREDIT, String.class,
				null);
		try{
			DbSubjects dbSubjects = new DbSubjects();
			dbSubjects.connect();
			dbSubjects.execSQL_NoTakenByStudent(studentId, semId);
			ArrayList<Subjects> subjectList = dbSubjects.getArray();
			dbSubjects.close();

		
		for (int i = 0; i < subjectList.size(); i++) {
			String id = Integer.toString(subjectList.get(i).getID());
			Item item = container.addItem(id);
			item.getItemProperty(PROPERTY_SUBJECT_CODE).setValue(
					subjectList.get(i).getSubjCode());
			item.getItemProperty(PROPERTY_SUBJECT_NAME).setValue(
					subjectList.get(i).getSubjectName());
			item.getItemProperty(PROPERTY_SEMESTER).setValue(
					subjectList.get(i).getSemester());
			item.getItemProperty(PROPERTY_STDYEAR).setValue(
					subjectList.get(i).getStdYear());
			item.getItemProperty(PROPERTY_SUBJECT_HOUR).setValue(
					subjectList.get(i).getSubjHrs());
			item.getItemProperty(PROPERTY_SUBJECT_CREDIT).setValue(
					subjectList.get(i).getSubjCredit());
		}
		}catch(Exception e){
			e.printStackTrace();
		}

		return container;
	}

}
