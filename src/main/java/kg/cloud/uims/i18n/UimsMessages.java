package kg.cloud.uims.i18n;

import java.io.Serializable;
import java.util.ListResourceBundle;

public class UimsMessages extends ListResourceBundle implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String OkKey=generateId();
	public static final String CancelKey=generateId();
	
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
      

	//AuthenticatedScreen
    public static final String LogoutButton = generateId();
    
    //Navigation Tree Branches
    public static final String TBEduProcess=generateId();
    
    //Navigation Tree Sub-branches
	
    public static final String TSBRegistration=generateId();
    public static final String TSBMySubjects=generateId();
    public static final String TSBMySuccess=generateId();
    public static final String TSBMyTranscript=generateId();
	
	private static String generateId() {
		return new Integer(ids++).toString();
	}
	
	static int ids=0;
	
	@Override
	protected Object[][] getContents() {
		// TODO Auto-generated method stub
		return null;
	}


}
