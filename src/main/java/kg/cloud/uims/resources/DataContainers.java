package kg.cloud.uims.resources;

import java.util.ArrayList;

import kg.cloud.uims.MyVaadinApplication;
import kg.cloud.uims.dao.DbDepartment;
import kg.cloud.uims.dao.DbExam;
import kg.cloud.uims.dao.DbGroup;
import kg.cloud.uims.dao.DbStudLess;
import kg.cloud.uims.dao.DbStudent;
import kg.cloud.uims.dao.DbStudent_Attendance;
import kg.cloud.uims.dao.DbSubjects;
import kg.cloud.uims.domain.Department;
import kg.cloud.uims.domain.Exam;
import kg.cloud.uims.domain.Group;
import kg.cloud.uims.domain.StudLess;
import kg.cloud.uims.domain.Student;
import kg.cloud.uims.domain.Subjects;
import kg.cloud.uims.i18n.UimsMessages;
import kg.cloud.uims.ui.TextFieldValidated;

import com.vaadin.data.Item;
import com.vaadin.data.util.IndexedContainer;

public class DataContainers {
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
	public String PROPERTY_SUBJECT_HOURS_WEEK;
	public String PROPERTY_SUBJECT_REGISTRATION_STATUS;

	public String studContainer_PROPERTY_ACC_STATUS;
	public String PROPERTY_SUBJECT_AVERAGE;
	public String PROPERTY_STUDENT_MIDTERM;
	public String PROPERTY_STUDENT_FINAL;
	public String PROPERTY_STUDENT_MAKE_UP;
	public String PROPERTY_STUDENT_ATTENDANCE;

	public String PROPERTY_GROUP_NAME;
	public String PROPERTY_FACULTY_CODE;
	public String PROPERTY_TOTAL_STUDENTS;

	public String PROPERTY_STUDENT_MARK;

	private MyVaadinApplication app;

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
		studContainer_PROPERTY_ACC_STATUS = app
				.getMessage(UimsMessages.AccountingStatus);
		PROPERTY_SUBJECT_AVERAGE = app.getMessage(UimsMessages.SubjectAverage);
		PROPERTY_STUDENT_MIDTERM = app.getMessage(UimsMessages.StudentMidterm);
		PROPERTY_STUDENT_FINAL = app.getMessage(UimsMessages.StudentFinal);
		PROPERTY_STUDENT_MAKE_UP = app.getMessage(UimsMessages.StudentMakeup);
		PROPERTY_STUDENT_ATTENDANCE = app
				.getMessage(UimsMessages.StudentAttandance);
		PROPERTY_SUBJECT_HOURS_WEEK = app
				.getMessage(UimsMessages.SubjectHoursPerWeek);
		PROPERTY_GROUP_NAME = app.getMessage(UimsMessages.GroupName);
		PROPERTY_STUDENT_MARK = app.getMessage(UimsMessages.StudentMark);
		PROPERTY_FACULTY_CODE = app.getMessage(UimsMessages.FacultyCode);
		PROPERTY_TOTAL_STUDENTS = app.getMessage(UimsMessages.TotalStudents);
	}

	public DataContainers() {
	}

	public IndexedContainer getStudentByGroupContainer(String groupId) {

		IndexedContainer studentContainer = new IndexedContainer();
		studentContainer.addContainerProperty(studContainer_PROPERTY_NAME,
				String.class, null);
		studentContainer.addContainerProperty(studContainer_PROPERTY_SURNAME,
				String.class, null);

		studentContainer.addContainerProperty(
				studContainer_PROPERTY_ACC_STATUS, String.class, null);
		studentContainer.addContainerProperty(studContainer_PROPERTY_STATUS,
				String.class, null);
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

				if (studentList.get(i).getAcc_status().equals("released"))
					item.getItemProperty(studContainer_PROPERTY_ACC_STATUS)
							.setValue("Can Register");
				else
					item.getItemProperty(studContainer_PROPERTY_ACC_STATUS)
							.setValue("Can not Register");
				item.getItemProperty(studContainer_PROPERTY_STATUS).setValue(
						studentList.get(i).getEducation());

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
		container.addContainerProperty(PROPERTY_SEMESTER, String.class, null);
		container.addContainerProperty(PROPERTY_STDYEAR, String.class, null);
		container.addContainerProperty(PROPERTY_SUBJECT_HOUR, String.class,
				null);
		container.addContainerProperty(PROPERTY_SUBJECT_CREDIT, String.class,
				null);
		container.addContainerProperty(PROPERTY_SUBJECT_REGISTRATION_STATUS,
				String.class, null);
		container.addContainerProperty(PROPERTY_SEMESTER, String.class, null);
		container.addContainerProperty(PROPERTY_STDYEAR, String.class, null);
		container.addContainerProperty(PROPERTY_SUBJECT_HOUR, String.class,
				null);
		container.addContainerProperty(PROPERTY_SUBJECT_CREDIT, String.class,
				null);
		container.addContainerProperty(PROPERTY_SUBJECT_REGISTRATION_STATUS,
				String.class, null);
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
			String semId) {
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
		try {
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
		} catch (Exception e) {
			e.printStackTrace();
		}

		return container;
	}

	public IndexedContainer getStudentTranscript(String studentId) {

		IndexedContainer container = new IndexedContainer();
		container.addContainerProperty(PROPERTY_SUBJECT_CODE, String.class,
				null);
		container.addContainerProperty(PROPERTY_SUBJECT_NAME, String.class,
				null);
		container.addContainerProperty(PROPERTY_SEMESTER, String.class, null);
		container.addContainerProperty(PROPERTY_STDYEAR, String.class, null);
		container.addContainerProperty(PROPERTY_SUBJECT_CREDIT, String.class,
				null);
		container.addContainerProperty(PROPERTY_SUBJECT_AVERAGE, String.class,
				null);
		try {
			DbStudLess dbStudLess = new DbStudLess();
			dbStudLess.connect();
			dbStudLess.execSubjectsTranscript(studentId);
			ArrayList<StudLess> transcriptList = dbStudLess.getArray();
			dbStudLess.close();

			for (int i = 0; i < transcriptList.size(); i++) {
				String id = Integer.toString(transcriptList.get(i).getID());
				Item item = container.addItem(id);
				item.getItemProperty(PROPERTY_SUBJECT_CODE).setValue(
						transcriptList.get(i).getSubjCode());
				item.getItemProperty(PROPERTY_SUBJECT_NAME).setValue(
						transcriptList.get(i).getSubjName());
				item.getItemProperty(PROPERTY_SEMESTER).setValue(
						transcriptList.get(i).getSemester());
				item.getItemProperty(PROPERTY_STDYEAR).setValue(
						transcriptList.get(i).getStudYear());
				item.getItemProperty(PROPERTY_SUBJECT_CREDIT).setValue(
						transcriptList.get(i).getSubCredit());
				if (transcriptList.get(i).getavMark().equals("101")) {
					item.getItemProperty(PROPERTY_SUBJECT_AVERAGE).setValue(
							"IP");
				} else {
					item.getItemProperty(PROPERTY_SUBJECT_AVERAGE).setValue(
							transcriptList.get(i).getavMark());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return container;
	}

	public IndexedContainer getStudentSuccessReport(String stID, String semID,
			String yearID) {

		IndexedContainer container = new IndexedContainer();

		container.addContainerProperty(PROPERTY_SUBJECT_NAME, String.class,
				null);
		container.addContainerProperty(PROPERTY_SUBJECT_HOUR, String.class,
				null);
		container.addContainerProperty(PROPERTY_STUDENT_MIDTERM, String.class,
				null);
		container.addContainerProperty(PROPERTY_STUDENT_FINAL, String.class,
				null);
		container.addContainerProperty(PROPERTY_STUDENT_MAKE_UP, String.class,
				null);
		container.addContainerProperty(PROPERTY_SUBJECT_AVERAGE, String.class,
				null);
		container.addContainerProperty(PROPERTY_STUDENT_ATTENDANCE,
				String.class, null);
		try {
			DbStudLess dbStudLess = new DbStudLess();
			dbStudLess.connect();
			dbStudLess.execSQL(stID, semID, yearID);
			ArrayList<StudLess> subjectList = dbStudLess.getArray();

			DbExam dbExam = new DbExam();
			dbExam.connect();

			DbStudent_Attendance dbAttandance = new DbStudent_Attendance();
			dbAttandance.connect();

			if (subjectList != null) {
				for (int i = 0; i < subjectList.size(); i++) {
					String id = Integer.toString(subjectList.get(i).getID());
					Item item = container.addItem(id);

					item.getItemProperty(PROPERTY_SUBJECT_NAME).setValue(
							subjectList.get(i).getSubjName());
					item.getItemProperty(PROPERTY_SUBJECT_HOUR).setValue(
							subjectList.get(i).getSubHour());
					item.getItemProperty(PROPERTY_STUDENT_MIDTERM)
							.setValue("0");
					item.getItemProperty(PROPERTY_STUDENT_FINAL).setValue("0");
					item.getItemProperty(PROPERTY_STUDENT_MAKE_UP)
							.setValue("0");

					dbExam.execStudentExamDetails(stID, semID, yearID,
							subjectList.get(i).getSubjID());
					ArrayList<Exam> examList = dbExam.getArray();

					Double av = dbStudLess.calcAverage_Report(stID, semID,
							yearID, subjectList.get(i).getSubjID());
					if (examList != null) {
						for (int j = 0; j < examList.size(); j++) {
							if (examList.get(j).getExam().equals("Midterm")) {
								item.getItemProperty(PROPERTY_STUDENT_MIDTERM)
										.setValue(
												examList.get(j).getPercentage());
							} else if (examList.get(j).getExam()
									.equals("Final")) {
								item.getItemProperty(PROPERTY_STUDENT_FINAL)
										.setValue(
												examList.get(j).getPercentage());
							} else if (examList.get(j).getExam()
									.equals("MakeUp")) {
								item.getItemProperty(PROPERTY_STUDENT_MAKE_UP)
										.setValue(
												examList.get(j).getPercentage());
							}
						}
					}

					item.getItemProperty(PROPERTY_SUBJECT_AVERAGE).setValue(
							Math.round(av));

					String att = Integer.toString(dbAttandance
							.execSQL_All(stID, semID, yearID, subjectList
									.get(i).getSubjID()));

					item.getItemProperty(PROPERTY_STUDENT_ATTENDANCE).setValue(
							att);

				}
			}
			dbStudLess.close();
			dbAttandance.close();
			dbExam.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return container;
	}

	public IndexedContainer getGroupList_forSelect(int f_id) {

		IndexedContainer deptContainer = new IndexedContainer();
		deptContainer.addContainerProperty(PROPERTY_GROUP_NAME,
				String.class, null);
		
		try {
			DbGroup dbGroup = new DbGroup();
			dbGroup.connect();
			dbGroup.execSQL_byFID_active(f_id);
			ArrayList<Group> groupList = dbGroup.getArray();
			dbGroup.close();

			for (int i = 0; i < groupList.size(); i++) {
				String id = Integer.toString(groupList.get(i).getID());
				Item item = deptContainer.addItem(id);
				item.getItemProperty(PROPERTY_GROUP_NAME).setValue(
						groupList.get(i).getGr_Name());
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		deptContainer.sort(new Object[] { PROPERTY_GROUP_NAME }, new boolean[] { true });
		return deptContainer;

	}
	
	public IndexedContainer getGroupList(int f_id) {

		IndexedContainer deptContainer = new IndexedContainer();
		deptContainer.addContainerProperty(PROPERTY_GROUP_NAME,
				String.class, null);
		deptContainer.addContainerProperty(PROPERTY_DEPARTMENT_CODE,
				String.class, null);
		deptContainer.addContainerProperty(PROPERTY_FACULTY_CODE,
				String.class, null);
		deptContainer.addContainerProperty(PROPERTY_TOTAL_STUDENTS,
				String.class, null);
		
		try {
			DbGroup dbGroup = new DbGroup();
			dbGroup.connect();
			dbGroup.execSQL_byFID_active(f_id);
			ArrayList<Group> groupList = dbGroup.getArray();
			dbGroup.close();

			for (int i = 0; i < groupList.size(); i++) {
				String id = Integer.toString(groupList.get(i).getID());
				Item item = deptContainer.addItem(id);
				item.getItemProperty(PROPERTY_GROUP_NAME).setValue(
						groupList.get(i).getGr_Name());
				item.getItemProperty(PROPERTY_DEPARTMENT_CODE).setValue(
						groupList.get(i).getD_Code());
				item.getItemProperty(PROPERTY_FACULTY_CODE).setValue(
						groupList.get(i).getF_Code());
				item.getItemProperty(PROPERTY_TOTAL_STUDENTS).setValue(
						groupList.get(i).getTotal_students());
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		deptContainer.sort(new Object[] { PROPERTY_GROUP_NAME }, new boolean[] { true });
		return deptContainer;

	}
	
	public IndexedContainer getDeptList(int f_id) {

		IndexedContainer deptContainer = new IndexedContainer();
		deptContainer.addContainerProperty(PROPERTY_DEPARTMENT_CODE,
				String.class, null);
		
		try {
			DbDepartment dbDepartment = new DbDepartment();
			dbDepartment.connect();
			dbDepartment.execSQL_F_ID(f_id);
			ArrayList<Department> deptList = dbDepartment.getArray();
			dbDepartment.close();

			for (int i = 0; i < deptList.size(); i++) {
				String id = Integer.toString(deptList.get(i).getID());
				Item item = deptContainer.addItem(id);
				item.getItemProperty(PROPERTY_DEPARTMENT_CODE).setValue(
						deptList.get(i).getDprt_Code());
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		deptContainer.sort(new Object[] { PROPERTY_DEPARTMENT_CODE }, new boolean[] { true });
		return deptContainer;

	}

	public IndexedContainer getAttSubjectStudList(String subj_id,
			String yearID, String semID) {

		IndexedContainer studListContainer = new IndexedContainer();
		studListContainer.addContainerProperty(studContainer_PROPERTY_NAME,
				String.class, null);
		studListContainer.addContainerProperty(studContainer_PROPERTY_SURNAME,
				String.class, null);
		studListContainer.addContainerProperty(PROPERTY_GROUP_NAME,
				String.class, null);
		studListContainer.addContainerProperty(PROPERTY_STUDENT_ATTENDANCE,
				TextFieldValidated.class, null);

		try {
			DbStudLess dbStudLess = new DbStudLess();
			dbStudLess.connect();
			dbStudLess.execSQL_Subject(subj_id, yearID, semID);
			ArrayList<StudLess> studLessList = dbStudLess.getArray();
			dbStudLess.close();

			for (int i = 0; i < studLessList.size(); i++) {
				String id = Integer.toString(studLessList.get(i).getStudID());
				Item item = studListContainer.addItem(id);
				item.getItemProperty(studContainer_PROPERTY_NAME).setValue(
						studLessList.get(i).getStudName());
				item.getItemProperty(studContainer_PROPERTY_SURNAME).setValue(
						studLessList.get(i).getStudSurname());
				item.getItemProperty(PROPERTY_GROUP_NAME).setValue(
						studLessList.get(i).getGrpName());
				TextFieldValidated attendance = new TextFieldValidated(app);
				item.getItemProperty(PROPERTY_STUDENT_ATTENDANCE).setValue(
						attendance);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		studListContainer.sort(new Object[] { studContainer_PROPERTY_NAME },
				new boolean[] { true });
		return studListContainer;

	}

	public IndexedContainer getExamSubjectStudList(String subj_id,
			String yearID, String semID) {

		IndexedContainer studListContainer = new IndexedContainer();
		studListContainer.addContainerProperty(studContainer_PROPERTY_NAME,
				String.class, null);
		studListContainer.addContainerProperty(studContainer_PROPERTY_SURNAME,
				String.class, null);
		studListContainer.addContainerProperty(PROPERTY_GROUP_NAME,
				String.class, null);
		studListContainer.addContainerProperty(PROPERTY_STUDENT_MARK,
				TextFieldValidated.class, null);

		try {
			DbStudLess dbStudLess = new DbStudLess();
			dbStudLess.connect();
			dbStudLess.execSQL_Exam(subj_id, yearID, semID);
			ArrayList<StudLess> studLessList = dbStudLess.getArray();

			for (int i = 0; i < studLessList.size(); i++) {
				if ((app.getCurrentExam().getID() == 1)
						|| (app.getCurrentExam().getID() == 2)) {
					String id = Integer.toString(studLessList.get(i).getID());
					Item item = studListContainer.addItem(id);
					item.getItemProperty(studContainer_PROPERTY_NAME).setValue(
							studLessList.get(i).getStudName());
					item.getItemProperty(studContainer_PROPERTY_SURNAME)
							.setValue(studLessList.get(i).getStudSurname());
					item.getItemProperty(PROPERTY_GROUP_NAME).setValue(
							studLessList.get(i).getGrpName());
					TextFieldValidated attendance = new TextFieldValidated(app);
					item.getItemProperty(PROPERTY_STUDENT_MARK).setValue(
							attendance);
				} else {
					if ((dbStudLess.calcAverage_Report(
							Integer.toString(studLessList.get(i).getStudID()),
							semID, yearID, Integer.parseInt(subj_id))) < 49.5) {
						String id = Integer.toString(studLessList.get(i)
								.getID());
						Item item = studListContainer.addItem(id);
						item.getItemProperty(studContainer_PROPERTY_NAME)
								.setValue(studLessList.get(i).getStudName());
						item.getItemProperty(studContainer_PROPERTY_SURNAME)
								.setValue(studLessList.get(i).getStudSurname());
						item.getItemProperty(PROPERTY_GROUP_NAME).setValue(
								studLessList.get(i).getGrpName());
						TextFieldValidated attendance = new TextFieldValidated(
								app);
						item.getItemProperty(PROPERTY_STUDENT_MARK).setValue(
								attendance);

					}
				}
			}
			dbStudLess.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		studListContainer.sort(new Object[] { studContainer_PROPERTY_NAME },
				new boolean[] { true });
		return studListContainer;

	}
	
	public IndexedContainer getLessonList_byDept(String deptID, String year_id, String sem_id) {

		IndexedContainer instLessContainer = new IndexedContainer();
		instLessContainer.addContainerProperty(PROPERTY_SUBJECT_CODE,
				String.class, null);
		instLessContainer.addContainerProperty(PROPERTY_SUBJECT_NAME,
				String.class, null);
		instLessContainer.addContainerProperty(PROPERTY_DEPARTMENT_CODE,
				String.class, null);
		instLessContainer.addContainerProperty(PROPERTY_STDYEAR, String.class,
				null);
		instLessContainer.addContainerProperty(PROPERTY_SUBJECT_HOURS_WEEK,
				String.class, null);
		try {
			DbSubjects dbSubjects = new DbSubjects();
			dbSubjects.connect();
			dbSubjects.execSQL_byDeprtActive(deptID, year_id, sem_id);
			ArrayList<Subjects> lessonsList = dbSubjects.getArray();
			dbSubjects.close();

			for (int i = 0; i < lessonsList.size(); i++) {
				String id = Integer.toString(lessonsList.get(i).getID());
				Item item = instLessContainer.addItem(id);
				item.getItemProperty(PROPERTY_SUBJECT_CODE).setValue(
						lessonsList.get(i).getSubjCode());
				item.getItemProperty(PROPERTY_SUBJECT_NAME).setValue(
						lessonsList.get(i).getSubjectName());
				item.getItemProperty(PROPERTY_DEPARTMENT_CODE).setValue(
						lessonsList.get(i).getDepartment());
				item.getItemProperty(PROPERTY_STDYEAR).setValue(
						lessonsList.get(i).getStdYear());
				item.getItemProperty(PROPERTY_SUBJECT_HOURS_WEEK).setValue(
						lessonsList.get(i).getSubjHrs());

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		instLessContainer.sort(new Object[] { PROPERTY_DEPARTMENT_CODE,
				PROPERTY_STDYEAR }, new boolean[] { true });
		return instLessContainer;

	}

	public IndexedContainer getInstructor_Lesson(String year_id, String sem_id,
			String rollnum) {

		IndexedContainer instLessContainer = new IndexedContainer();
		instLessContainer.addContainerProperty(PROPERTY_SUBJECT_CODE,
				String.class, null);
		instLessContainer.addContainerProperty(PROPERTY_SUBJECT_NAME,
				String.class, null);
		instLessContainer.addContainerProperty(PROPERTY_DEPARTMENT_CODE,
				String.class, null);
		instLessContainer.addContainerProperty(PROPERTY_STDYEAR, String.class,
				null);
		instLessContainer.addContainerProperty(PROPERTY_SUBJECT_HOURS_WEEK,
				String.class, null);
		try {
			DbSubjects dbSubjects = new DbSubjects();
			dbSubjects.connect();
			dbSubjects.execSQL_subj_inst_new(year_id, sem_id, rollnum);
			ArrayList<Subjects> instLessList = dbSubjects.getArray();
			dbSubjects.close();

			for (int i = 0; i < instLessList.size(); i++) {
				String id = Integer.toString(instLessList.get(i).getID());
				Item item = instLessContainer.addItem(id);
				item.getItemProperty(PROPERTY_SUBJECT_CODE).setValue(
						instLessList.get(i).getSubjCode());
				item.getItemProperty(PROPERTY_SUBJECT_NAME).setValue(
						instLessList.get(i).getSubjectName());
				item.getItemProperty(PROPERTY_DEPARTMENT_CODE).setValue(
						instLessList.get(i).getDepartment());
				item.getItemProperty(PROPERTY_STDYEAR).setValue(
						instLessList.get(i).getStdYear());
				item.getItemProperty(PROPERTY_SUBJECT_HOURS_WEEK).setValue(
						instLessList.get(i).getSubjHrs());

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		instLessContainer.sort(new Object[] { PROPERTY_DEPARTMENT_CODE,
				PROPERTY_STDYEAR }, new boolean[] { true });
		return instLessContainer;

	}

}
