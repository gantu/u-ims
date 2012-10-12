package kg.cloud.uims.i18n;

public class UimsMessages_en extends UimsMessages {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    static final Object[][] contents_en = {
        {OkKey, "OK"},
        {CancelKey, "Cancel"},
        // Application
        {AppTitle, "University Information Management System"},
        {Username, "Username"},
        {Password, "Password"},
        {Login, "Login"},
        {LoginButton, "Login"},
        // Authenticated Screen

        {LogoutButton, "Logout"},
        {LogInAsLabel, "Logged in as"},
        {CurYearLabel, "Current Year"},
        {CurSemesterLabel, "Current Semester"},
        {CurWeekLabel, "Current Week"},
        {CurExamLabel, "Current Exam"},
        {RegistrationOpened, "Opened"},
        {RegistrationClosed, "Closed"},
        {ButtonTooltip, "Change your password here!"},
        {SystemClosedNotif, "Sorry! The system is under maintenance. "
            + "Please, try later!"},
        {FullName, "Fullname"},
        // Navigation Tree BranchesА
        {TBEduProcess, "Educational Process"},
        // Navigation Tree Sub-branches

        {TSBMySubjects, "My Subjects"},
        {TSBMySuccess, "My Success"},
        {TSBMyTranscript, "My Transcript"},
        // Supervisor Branches
        {TBSupervisor, "Supervisor"},
        {TSBRegistration, "Registration"},
        {TSBTranscript, "Transcript"},
        {TSBSuccessReport, "Success Report"},
        // Instructor Branches
        {TBInstructor, "Lecturer"},
        {TSBAttendance, "Attendance"},
        {TSBExam, "Exam"},
        // Secretary Branches
        {TBSecretary, "Secretary"},
        {TSBStudentList, "Student List"},
        {FacultyCode, "Faculty"},
        // Student Branches
        {TBStudent, "Student"},
        {TSBMyInfo, "My Information"},
        // My Info
        {EduStatusLabel, "General Information"},
        {EducationLabel, "Education"},
        {AccountingStatuses, "Accounting Statuses"},
        {AccountingStatuses, "Accounting Statuses"},
        {Registered, "Registered"},
        {NotRegistered, "Not Registered"},
        {Released, "Released"},
        {Freezed, "Freezed"},
        // Registration View
        {RegistrationHeader, "Registration"},
        {RegistrationStudentName, "First Name"},
        {RegistrationStudentSurname, "Last Name"},
        {RegistrationStudentEduStatus, "Education Status"},
        {AccountingStatus, "Accounting Status"},
        {TableNotTakenSubjects, "Not Taken Subjects"},
        {TableCurrentSubjects, "Current Subjects"},
        {SubjectName, "Subject Name"},
        {SubjectCode, "Subject Code"},
        {SubjectCredit, "Credits"},
        {SubjectHour, "Hours"},
        {Semester, "Semester"},
        {StudyYear, "Year"},
        {DepartmentCode, "Department Code"},
        {DepartmentName, "Department Name"},
        {SubjectRegistrationStatus, "Registration Status"},
        {SubjectHoursSum, "Sum of Hours"},
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
        {TranscriptHeader, "Transcript"},
        {SubjectAverage, "Average"},
        {SubjectCreditSum, "Sum of Credits"},
        {LabelStudent, "Student"},
        {ButtonMakePDF, "Make PDF"},
        //SuccessReportView
        {LabelYear, "Academic Year"},
        {LabelSemester, "Semester"},
        {SuccessReportHeader, "Success Report"},
        {StudentMidterm, "Midterm"},
        {StudentFinal, "Final"},
        {StudentMakeup, "Makeup"},
        {StudentAttandance, "Attandance"},
        //AttendanceView
        {AttendanceHeader, "Attendance"},
        {SubjectHoursPerWeek, "Hours/Week"},
        {GroupName, "Group Name"},
        {TotalStudents, "Total students"},
        // AttendanceWindow
        {ListOfStudents, "List of students:"},
        {ButtonMakeForm, "Attendance Form"},
        {ButtonMakeReport, "Attendance Report"},
        {NotifSuccesSave, "Attendace is successfully saved"},
        {NotifDbError, "Error! You have already saved the inputs"},
        {NotifNegative, "The numbers can not be negative"},
        {NotifWrongNum, "Wrong value! Please check the fields"},
        // ExamView
        {ExamHeader, "Exam"},
        {Department, "Department"},
        {Group, "Group"},
        {ButtonSignatureList, "Signature List"},
        {ButtonResultList, "Result List"},
        {ButtonAverageReport, "Average Report"},
        {NotifSuccesSaveExam, "Exam results are successfully saved"},
        // ExamWindow
        {StudentMark, "Mark"},
        {NotifBigger100, "The marks can not be bigger than 100"},
        //HelpView
        {HelpHeader, "Help"},
        {DepartmentsTab, "Departments"},
        {StatusesTab, "Statuses"},
        // ChangeUserDataView
        {ChangeUserDataHeader, "Change Information"},
        {FormCaptionUser, "User"},
        {
            FormDescription,
            "Set a complex password 6-20 characters long - "
            + "numbers, latin letters and special symbols."},
        {FormFiledCurPassword, "Current Password"},
        {FormFiledNewPassword, "New Password"},
        {FormFiledConfPassword, "Confirm Password"},
        {RequiredErrorCurrPassword, "Please, enter your Current Password!"},
        {RequiredErrorNewPassword, "Please, enter New Password!"},
        {RequiredErrorConfPassword, "Please, confirm your New Password!"},
        {
            RegexpValidatorError,
            "The password must be at least 6 characters "
            + "and consist of appropriate symbols"},
        {
            NotifDontMatch,
            "New Password and Confirmation Password don't match,"
            + " please, confirm them!"},
        {NotifWrongCurrPassword,
            "Please, enter your Current Password correctly!"},
        {NotifSuccessfulChange, "Password is changed successfully!"},
        {FreezedMessage, "Freezed! This student must visit Account Services."},
        {StatisticsMessage, "Statistics"}
    };

    @Override
    public Object[][] getContents() {
        return contents_en;
    }
}
