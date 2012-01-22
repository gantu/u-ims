package kg.cloud.uims.i18n;


public class UimsMessages_en extends UimsMessages {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static final Object[][] contents_en = {
			{ OkKey, "OK" },
			{ CancelKey, "Cancel" },
			// Application
			{ AppTitle, "University Information Management System" },
			{ Username, "Username" },
			{ Password, "Password" },
			{ Login, "Login" },
			{ LoginButton, "Login" },

			// Authenticated Screen

			{ LogoutButton, "Logout" },
			{LogInAsLabel, "Logged in as"},
			{CurYearLabel, "Current Year"},
			{CurSemesterLabel, "Current Semester"},
			{CurWeekLabel, "Current Week"},
			{CurExamLabel, "Current Exam"},
			{ButtonTooltip, "Change your password here!"},
			
			// Navigation Tree Branches
			{ TBEduProcess, "Educational Process" },

			// Navigation Tree Sub-branches

			{ TSBMySubjects, "My Subjects" },
			{ TSBMySuccess, "My Success" },
			{ TSBMyTranscript, "My Transcript" },

			// Supervisor Branches
			{ TBSupervisor, "Supervisor" },
			{ TSBRegistration, "Registration" },
			{ TSBTranscript, "Transcript" },
			{ TSBSuccessReport, "Success Report" },

			// Instructor Branches
			{ TBInstructor, "Lecturer" },
			{ TSBAttendance, "Attendance" },
			{ TSBExam, "Exam" },
			// Registration View
			{ RegistrationHeader, "Registration" },
			{ RegistrationStudentName, "First Name" },
			{ RegistrationStudentSurname, "Last Name" },
			{ RegistrationStudentEduStatus, "Education Status" },
			{AccountingStatus,"Accounting Status"},

			{TableNotTakenSubjects, "Not Taken Subjects"},
			{TableCurrentSubjects, "Current Subjects"},
			
			{ SubjectName, "Subject Name" }, 
			{ SubjectCode, "Subject Code" },
			{ SubjectCredit, "Credits" }, 
			{ SubjectHour, "Hours" },
			{ Semester, "Semester" }, 
			{ StudyYear, "Year" },
			{ DepartmentCode, "Department Code" },
			{ DepartmentName, "Department Name" },
			{ SubjectRegistrationStatus, "Registration Status" },
			{ SubjectHoursSum, "Sum of Hours" },
			
			{MoveUpButton, "Move up"},
			{MoveDownButton, "Move down"},
			{SaveButton, "Save"},

			{NotifSumOFSubjExceed, "Sum of Subject hours can not exceed 40!"},
			{NotifNothingSelected, "Nothing is selected"},
			{NotifCantRemoveSubj, "You can not remove this Subject!"},
			{NotifThankYouRegistr, "Thank you registration is completed!"},
			
			// TranscriptView
			{ TranscriptHeader, "Transcript" },
			{ SubjectAverage, "Average"},
			{ SubjectCreditSum, "Sum of Credits" },
			{LabelStudent, "Student"},
			{ButtonMakePDF, "Make PDF"},
			
	//SuccessReportView
			{LabelYear, "Academic Year"},
			{LabelSemester, "Semester"},
			{SuccessReportHeader, "Success Report"},
			{ StudentMidterm, "Midterm"},
			{ StudentFinal, "Final"},
			{ StudentMakeup, "Makeup"},
			{ StudentAttandance, "Attandance"},
			//HelpView
			{HelpHeader, "Help"},
			
			// ChangeUserDataView
			{ChangeUserDataHeader, "Change Information"}};

	@Override
	public Object[][] getContents() {
		return contents_en;
	}

}
