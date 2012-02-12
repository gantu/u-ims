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

			{NotifSumOFSubjExceed, "Sum of Subject hours can not exceed 39!"},
			{NotifNothingSelected, "Nothing is selected"},
			{NotifCantRemoveSubj, "You can not remove this Subject!"},
			{NotifThankYouRegistr, "Thank you registration is completed!"},
			{FilterByYearLabel, "Filter by year:"},
			{FilterByCodeLabel, "Filter by subject code:"},
			
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
			
			//AttendanceView
			{AttendanceHeader, "Attendance"},
			{SubjectHoursPerWeek, "Hours/Week"},
			{GroupName, "Group Name"},
			
			//HelpView
			{HelpHeader, "Help"},
			
			// ChangeUserDataView
			{ChangeUserDataHeader, "Change Information"},
			{ FormCaptionUser, "User" },
			{
					FormDescription,
					"Set a complex password 6-20 characters long - "
							+ "numbers, latin letters and special symbols." },
			{ FormFiledCurPassword, "Current Password" },
			{ FormFiledNewPassword, "New Password" },
			{ FormFiledConfPassword, "Confirm Password" },

			{ RequiredErrorCurrPassword, "Please, enter your Current Password!" },
			{ RequiredErrorNewPassword, "Please, enter New Password!" },
			{ RequiredErrorConfPassword, "Please, confirm your New Password!" },
			{
					RegexpValidatorError,
					"The password must be at least 6 characters "
							+ "and consist of appropriate symbols" },
			{
					NotifDontMatch,
					"New Password and Confirmation Password don't match,"
							+ " please, confirm them!" },
			{ NotifWrongCurrPassword,
					"Please, enter your Current Password correctly!" },
			{ NotifSuccessfulChange, "Password is changed successfully!" },
			{FreezedMessage,"Freezed! This student must visit Account Services."},
			{StatisticsMessage,"Statistics"}
	};

	@Override
	public Object[][] getContents() {
		return contents_en;
	}

}
