package kg.cloud.uims.i18n;

import java.io.Serializable;
import java.util.ListResourceBundle;

public class UimsMessages extends ListResourceBundle implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String OkKey = generateId();
	public static final String CancelKey = generateId();

	// Application
	public static final String AppTitle = generateId();

	// LoginScreen
	public static final String Username = generateId();
	public static final String Password = generateId();
	public static final String Login = generateId();
	public static final String LoginButton = generateId();
	public static final String RegisterNewUser = generateId();
	public static final String ForgotPassword = generateId();
	public static final String InvalidPassword = generateId();
	public static final String InvalidPasswordLong = generateId();
	public static final String DemoUsernameHint = generateId();

	// AuthenticatedScreen
	public static final String LogoutButton = generateId();
	public static final String LogInAsLabel = generateId();
	public static final String CurYearLabel = generateId();
	public static final String CurSemesterLabel = generateId();
	public static final String CurWeekLabel = generateId();
	public static final String CurExamLabel = generateId();
	public static final String ButtonTooltip = generateId();

	// RegistrationView
	public static final String RegistrationHeader = generateId();
	public static final String RegistrationStudentName = generateId();
	public static final String RegistrationStudentSurname = generateId();
	public static final String RegistrationStudentEduStatus = generateId();

	public static final String SubjectName = generateId();
	public static final String SubjectCode = generateId();
	public static final String SubjectCredit = generateId();
	public static final String SubjectHour = generateId();
	public static final String SubjectRegistrationStatus = generateId();

	public static final String AccountingStatus = generateId();

	public static final String SubjectHoursSum = generateId();

	public static final String Semester = generateId();

	public static final String TableNotTakenSubjects = generateId();
	public static final String TableCurrentSubjects = generateId();

	public static final String StudyYear = generateId();
	public static final String DepartmentName = generateId();
	public static final String DepartmentCode = generateId();

	public static final String MoveUpButton = generateId();
	public static final String MoveDownButton = generateId();
	public static final String SaveButton = generateId();

	public static final String NotifSumOFSubjExceed = generateId();
	public static final String NotifNothingSelected = generateId();
	public static final String NotifCantRemoveSubj = generateId();
	public static final String NotifThankYouRegistr = generateId();

	// TranscriptView
	public static final String TranscriptHeader = generateId();
	public static final String SubjectAverage = generateId();
	public static final String SubjectCreditSum = generateId();
	public static final String LabelStudent = generateId();
	public static final String ButtonMakePDF = generateId();

	// SuccessReportView
	public static final String LabelYear = generateId();
	public static final String LabelSemester = generateId();
	public static final String SuccessReportHeader = generateId();
	public static final String StudentMidterm = generateId();
	public static final String StudentFinal = generateId();
	public static final String StudentMakeup = generateId();
	public static final String StudentAttandance = generateId();

	// HelpView
	public static final String HelpHeader = generateId();

	// ChangeUserDataView
	public static final String ChangeUserDataHeader = generateId();
	public static final String FormCaptionUser = generateId();
	public static final String FormDescription = generateId();
	public static final String FormFiledCurPassword = generateId();
	public static final String FormFiledNewPassword = generateId();
	public static final String FormFiledConfPassword = generateId();

	public static final String RequiredErrorCurrPassword = generateId();
	public static final String RequiredErrorNewPassword = generateId();
	public static final String RequiredErrorConfPassword = generateId();
	public static final String RegexpValidatorError = generateId();
	public static final String NotifDontMatch = generateId();
	public static final String NotifWrongCurrPassword = generateId();
	public static final String NotifSuccessfulChange = generateId();

	// Navigation Tree Branches
	public static final String TBEduProcess = generateId();

	// Navigation Tree Sub-branches

	// Supervisor Branches
	public static final String TBSupervisor = generateId();
	public static final String TSBRegistration = generateId();
	public static final String TSBTranscript = generateId();
	public static final String TSBSuccessReport = generateId();

	// Instructor Branches
	public static final String TBInstructor = generateId();
	public static final String TSBAttendance = generateId();
	public static final String TSBExam = generateId();

	public static final String TSBMySubjects = generateId();
	public static final String TSBMySuccess = generateId();
	public static final String TSBMyTranscript = generateId();

	private static String generateId() {
		return new Integer(ids++).toString();
	}

	static int ids = 0;

	@Override
	protected Object[][] getContents() {
		// TODO Auto-generated method stub
		return null;
	}

}
