package kg.cloud.uims.i18n;

public class UimsMessages_en extends UimsMessages {

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

			{ SubjectName, "Subject Name" }, { SubjectCode, "Subjec Code" },
			{ SubjectCredit, "Credits" }, { SubjectHour, "Hours" },
			{ Semester, "Semester" }, { StudyYear, "Year" },
			{ DepartmentCode, "Department Code" },
			{ DepartmentName, "Department Name" },
			{ SubjectRegistrationStatus, "Registration Status" },
			{ SubjectHoursSum, "Sum of Hours" },

			// TranscriptView
			{ TranscriptHeader, "Transcript" },
			{ SubjectAverage, "Average"},
			{ SubjectCreditSum, "Sum of Credits" },
			{LabelStudent, "Student"},
			{ButtonMakePDF, "Save In PDF"},
			
	//SuccessReportView
			{LabelYear, "Academic Year"},
			{LabelSemester, "Semester"},
			{SuccessReportHeader, "Success Report"},
			{ StudentMidterm, "Midterm"},
			{ StudentFinal, "Final"},
			{ StudentMakeup, "Makeup"},
			{ StudentAttandance, "Attandance"}};

	@Override
	public Object[][] getContents() {
		return contents_en;
	}

}