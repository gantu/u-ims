package kg.cloud.uims.i18n;

public class UimsMessages_ky extends UimsMessages {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static final Object[][] contents_ky = {
			{ OkKey, "Мейли" },
			{ CancelKey, "Жок" },
			// Application
			{ AppTitle, "Университетти башкаруу системасы" },
			{ Username, "Колдонуучу" },
			{ Password, "Сыр сөз" },
			{ Login, "Кир" },
			{ LoginButton, "Кир" },

			// Authenticated screen
			{ LogoutButton, "Чык" },
			{ LogInAsLabel, "Сиз" },
			{ CurYearLabel, "Жыл" },
			{ CurSemesterLabel, "Семестр" },
			{ CurWeekLabel, "Апта" },
			{ CurExamLabel, "Сынак" },
			{ ButtonTooltip, "Биякта сыр сөздүнү алмаштыр!" },
			{SystemClosedNotif, "Извините! Над системой ведутся ремонтные работы. Пожалуйста, попробуйте позднее!"},

			// Navigation Tree Branches
			{ TBEduProcess, "Окуу Процеси" },

			// Navigation Tree Sub-branches

			{ TSBMySubjects, "Сабактарым" },
			{ TSBMySuccess, "Жетишкендиктерим" },
			{ TSBMyTranscript, "Транскриптим" },

			// Supervisor Branches
			{ TBSupervisor, "Куратор" },
			{ TSBRegistration, "Каттоо" },
			{ TSBTranscript, "Транскрипт" },
			{ TSBSuccessReport, "Жетишкендик" },
			// Instructor Branches
			{ TBInstructor, "Окутуучу" },
			{ TSBAttendance, "Жоктоо" },
			{ TSBExam, "Сынак" },
			
			// Secretary Branches
			{ TBSecretary, "Секретарь" },
			{ TSBStudentList, "Список студентов" },
			{ FacultyCode, "Факультет" },
	
			// Registration View
			{ RegistrationHeader, "Регистрация" },
			{ RegistrationStudentName, "Аты" },
			{ RegistrationStudentSurname, "Фамилиясы" },
			{ RegistrationStudentEduStatus, "Окуу Статусу" },
			{ AccountingStatus, "Бухгалтерия" },

			{ TableNotTakenSubjects, "Алынбаган сабактар" },
			{ TableCurrentSubjects, "Кезектеги сабактар" },

			{ SubjectName, "Сабактын Аты" },
			{ SubjectCode, "Сабактын Коду" },
			{ SubjectCredit, "Кредити" },
			{ SubjectHour, "Сааты" },
			{ Semester, "Семестр" },
			{ StudyYear, "Жыл" },
			{ DepartmentCode, "Бөлүмдүн Коду" },
			{ DepartmentName, "Бөлүмдүн Аты" },
			{ SubjectRegistrationStatus, "Катталуу Статусу" },
			{ SubjectHoursSum, "Саатардын Саны" },

			{ MoveUpButton, "Өйдө жылдыр" },
			{ MoveDownButton, "Ылдый жылдыр" },
			{ SaveButton, "Сактоо" },

			{ NotifSumOFSubjExceed, "Саатардын суммасы 39 саатты ашпашы керек!" },
			{ NotifNothingSelected, "Еч нерсе тандалган эмес." },
			{ NotifCantRemoveSubj, "Сиз бул сабакты өчүрө албайсыз!" },
			{ NotifThankYouRegistr, "Рахмат, Катоо ийгиликтүү аяктады!" },
			{ FilterByYearLabel, "Жылга карап сүз:" },
			{ FilterByCodeLabel, "Сабактын кодуна карап сүз:" },

			// Transcript View
			{ TranscriptHeader, "Транскрипт" },
			{ SubjectAverage, "Ортоломо баасы" },
			{ SubjectCreditSum, "Кредиттин Саны" },
			{ LabelStudent, "Студент" },
			{ ButtonMakePDF, "PDFке айлант " },

			// SuccessReportView
			{ LabelYear, "Академик жылы" },
			{ LabelSemester, "Семестр" },
			{ SuccessReportHeader, "Жетишкендиктер" },
			{ StudentMidterm, "Мидтерм" },
			{ StudentFinal, "Финал" },
			{ StudentMakeup, "Мэйкап" },
			{ StudentAttandance, "Жоктоо" },

			// AttendanceView
			{ AttendanceHeader, "Жоктоо" },
			{ SubjectHoursPerWeek, "Апталык саат саны" },
			{ GroupName, "Группанын аты" },
			{ TotalStudents, "Студенттердин саны" },

			// AttendanceWindow
			{ListOfStudents, "Студенттердин тизмеси:"},
			{ ButtonMakeForm, "Жоктоо формасы" },
			{ ButtonMakeReport, "Жоктоо рапорту" },
			{ NotifSuccesSave, "Рахмат! Ийгиликтүү жоктодуңуз!" },
			{ NotifDbError, "Ката! Буга чейин сиздин маалыматыныз сакталган" },
			{ NotifNegative, "Терс сан жазууга болбоит!" },
			{ NotifWrongNum, "Туура эмес, кайра текшериңиз." },
			
			// ExamView
			{ ExamHeader, "Сынак" },
			{Department, "Бөлүм"},
			{Group, "Группа"},
			{ButtonSignatureList, "Лист для подписей"},
			{ButtonResultList, "Лист для результатов"},
			{ButtonAverageReport, "Репорт успеваемости"},
			
			// ExamWindow
						{StudentMark, "Баасы"},
						{NotifBigger100, "Баа 100 балдан өйдө болбоого тийиш!"},

			// HelpView
			{ HelpHeader, "Жардам" },
			{DepartmentsTab, "Бөлүмдөр"},
			{StatusesTab, "Статустар"},
			// ChangeUserDataView
			{ ChangeUserDataHeader, "Маалымат алмаштыр" },
			{ FormCaptionUser, "Колдонуучу" },
			{
					FormDescription,
					"Введите сложный пароль длинною 6-20 характеров - "
							+ "цифры, латинские буквы и специальные символы." },
			{ FormFiledCurPassword, "Азыркы Сыр Сөз" },
			{ FormFiledNewPassword, "Жаңы Сыр Сөз" },
			{ FormFiledConfPassword, "Жаңы Сыр Сөздү Кайталаңыз" },

			{ RequiredErrorCurrPassword, "Сураныч,Азыркы Сыр Сөзүңүздү Кириңиз!" },
			{ RequiredErrorNewPassword, "Сураныч,Жаңы Сыр Сөзүңүздү Кириңиз!" },
			{ RequiredErrorConfPassword,
					"Сураныч,Жаңы Сыр Сөзүңүздү Кайталаңыз!" },
			{
					RegexpValidatorError,
					"Сыр сөз эң аз 6 тамгадан туруусу зарыл!" },
			{
					NotifDontMatch,
					"Новый Пароль и Пароль подтверждения не совпадают,"
							+ " пожалуйста, подтвердите их!" },
			{ NotifWrongCurrPassword,
					"Пожалуйста, введите текущий пароль правильно!" },
			{ NotifSuccessfulChange, "Пароль успешно изменен." },
			{ FreezedMessage,
					"Тоңдурулган! Бул окуучу Бухгалтерияга баруусу зарыл!" } };

	@Override
	public Object[][] getContents() {
		return contents_ky;
	}

}
