package kg.cloud.uims.i18n;

public class UimsMessages_ky extends UimsMessages {

	static final Object[][] contents_ky={
		{OkKey,"Мейли"},
		{CancelKey,"Жок"},
		//Application
		{AppTitle,"Университетти башкаруу системасы"},
		{Username,"Колдонуучу"},
		{Password,"Сыр сөз"},
		{Login,"Кир"},
		{LoginButton,"Кир"},
		
		//Authenticated screen
		{LogoutButton,"Чык"},
		
		//Navigation Tree Branches
	   {TBEduProcess,"Окуу Процеси"},
				
		//Navigation Tree Sub-branches
		
		{TSBMySubjects,"Сабактарым"},
		{TSBMySuccess,"Жетишкендиктерим"},
		{TSBMyTranscript,"Транскриптим"},
		
		//Supervisor Branches
		{TBSupervisor,"Куратор"},
		{TSBRegistration,"Каттоо"},
		{TSBTranscript,"Транскрипт"},
		{TSBSuccessReport,"Жетишкендик"},
		//Instructor Branches
		{TBInstructor,"Окутуучу"},
		{TSBAttendance,"Жоктоо"},
		{TSBExam,"Сынак"},
		//Registration View
		{RegistrationHeader,"Регистрация"},
		{RegistrationStudentName,"Аты"},
		{RegistrationStudentSurname,"Фамилиясы"},
		{RegistrationStudentEduStatus,"Окуу Статусу"},
		
		{SubjectName,"Сабактын Аты"},
		{SubjectCode,"Сабактын Коду"},
		{SubjectCredit,"Кредити"},
		{SubjectHour,"Сааты"},
		{Semester,"Семестр"},
		{StudyYear,"Жыл"},
		{DepartmentCode,"Бөлүмдүн Коду"},
		{DepartmentName,"Бөлүмдүн Аты"},
		{SubjectRegistrationStatus,"Катталуу Статусу"},
		{SubjectHoursSum,"Саатардын Саны"}
	};
	
	 @Override
	    public Object[][] getContents() {
	        return contents_ky;
	    }

}