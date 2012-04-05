package kg.cloud.uims;


import java.util.Locale;

import kg.cloud.uims.i18n.UimsMessages;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;

import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.LoginForm;
import com.vaadin.ui.LoginForm.LoginEvent;
import com.vaadin.ui.Window.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

public class LoginScreen extends VerticalLayout{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MyVaadinApplication app;
	
	public LoginScreen(MyVaadinApplication app){
		this.app=app;
		
		app.getMainWindow().setCaption(app.getMessage(UimsMessages.AppTitle));
		setSizeFull();
		final HorizontalLayout languageBar=new HorizontalLayout();
		languageBar.setHeight("50px");
		addComponent(languageBar);
		setComponentAlignment(languageBar, Alignment.TOP_RIGHT);
		
		ThemeResource iconKG = new ThemeResource("../runo/icons/16/Kyrgyzstan16.png");
		Button kyrgyz=new Button("Kyrgyz");
		kyrgyz.addListener(new SwitchLanguage(app));
		kyrgyz.setIcon(iconKG);
		kyrgyz.setEnabled(!app.getLocale().getLanguage().equals("ky"));
		languageBar.addComponent(kyrgyz);
		
		ThemeResource iconUK = new ThemeResource("../runo/icons/16/United-Kingdom16.png");
		Button english=new Button("English");
		english.addListener(new SwitchLanguage(app));
		english.setIcon(iconUK);
		english.setEnabled(!app.getLocale().getLanguage().equals("en"));
		languageBar.addComponent(english);
		
		Panel loginPanel=new Panel(app.getMessage(UimsMessages.Login));
		loginPanel.setWidth("400px");
		
		LoginForm loginForm=new LoginForm();
		loginForm.setPasswordCaption(app.getMessage(UimsMessages.Password));
		loginForm.setUsernameCaption(app.getMessage(UimsMessages.Username));
		loginForm.setLoginButtonCaption(app.getMessage(UimsMessages.LoginButton));

		loginForm.setHeight("150px");
		loginForm.addListener(new MyLoginListener(this.app, loginForm));
		loginPanel.addComponent(loginForm);

		addComponent(loginPanel);
		setComponentAlignment(loginPanel, Alignment.MIDDLE_CENTER);

		HorizontalLayout footer = new HorizontalLayout();
		footer.setHeight("50px");
		addComponent(footer);
		
		

		
	}
	
	private static class MyLoginListener implements LoginForm.LoginListener{
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private MyVaadinApplication app;
		private LoginForm loginForm;		
		
		public MyLoginListener(MyVaadinApplication app,LoginForm loginForm){
			this.app=app;
			this.loginForm=loginForm;			
		}
		
		public void onLogin(LoginEvent event){
			String username=event.getLoginParameter("username");
			String password=event.getLoginParameter("password");
			try
			{
				MyVaadinApplication.getInstance().login(username, password);
                                
				// Switch to the protected view
				app.getMainWindow().setContent(new AuthenticatedScreen(app));
			}
			catch (UnknownAccountException uae)
			{
				this.loginForm.getWindow().showNotification("Invalid User", Notification.TYPE_ERROR_MESSAGE);
			}
			catch (IncorrectCredentialsException ice)
			{
				this.loginForm.getWindow().showNotification("Invalid User", Notification.TYPE_ERROR_MESSAGE);
			}
			catch (LockedAccountException lae)
			{
				this.loginForm.getWindow().showNotification("Invalid User", Notification.TYPE_ERROR_MESSAGE);
			}
			catch (ExcessiveAttemptsException eae)
			{
				this.loginForm.getWindow().showNotification("Invalid User", Notification.TYPE_ERROR_MESSAGE);
			}
			catch (AuthenticationException ae)
			{
				this.loginForm.getWindow().showNotification("Invalid User", Notification.TYPE_ERROR_MESSAGE);
			}
			catch (Exception ex)
			{
				this.loginForm.getWindow().showNotification("Exception " + ex.getMessage(), Notification.TYPE_ERROR_MESSAGE);
			}
		}
		
	}
	
	class SwitchLanguage implements Button.ClickListener{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private MyVaadinApplication app;
		
		public SwitchLanguage(MyVaadinApplication app){
			this.app=app;
		}
		public void buttonClick(ClickEvent event) {
			if(app.getLocale().getLanguage().equals("ky"))
				app.setLocale(new Locale("en"));
			else
				app.setLocale(new Locale("ky"));
			
			app.getViewManager().switchScreen(LoginScreen.class.getName(), new LoginScreen(app));
		}
		
	}

}