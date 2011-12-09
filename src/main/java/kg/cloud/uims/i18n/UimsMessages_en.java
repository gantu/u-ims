package kg.cloud.uims.i18n;

public class UimsMessages_en extends UimsMessages{
	
	static final Object[][] contents_en={
		{OkKey,"OK"},
		{CancelKey,"Cancel"},
		//Application
		{AppTitle,"University Information Management System"},
		{Username,"Username"},
		{Password,"Password"},
		{Login,"Login"},
		{LoginButton,"Login"},
	
		//Authenticated Screen
		
		{LogoutButton,"Logout"},

		//Navigation Tree Branches
		{TBEduProcess,"Educational Process"},
		
		//Navigation Tree Sub-branches
		{TSBRegistration,"Registration"},
		{TSBMySubjects,"My Subjects"},
		{TSBMySuccess,"My Success"},
		{TSBMyTranscript,"My Transcript"}
	};
	
	 @Override
	    public Object[][] getContents() {
	        return contents_en;
	    }


}
