package kg.cloud.uims.ui;

import java.util.ArrayList;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import kg.cloud.uims.MyVaadinApplication;
import kg.cloud.uims.dao.DbUsers;
import kg.cloud.uims.domain.Users;
import kg.cloud.uims.i18n.UimsMessages;

import com.vaadin.data.Validator;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Form;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import java.io.IOException;  
import java.util.logging.FileHandler;  
import java.util.logging.Level;  
import java.util.logging.Logger;  
import java.util.logging.SimpleFormatter; 

public class ChangeUserData extends VerticalLayout implements
		Button.ClickListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Subject currentUser = SecurityUtils.getSubject();
	private MyVaadinApplication app;
	private VerticalLayout header;
	private FormLayout body;
	private Form userForm;
	private Label lb2;
	
	private Logger logger = Logger.getLogger("MyLog");  
	private FileHandler fh;

	public ChangeUserData(MyVaadinApplication app) {
		this.app = app;
		setSpacing(true);
		buildHeader();
		buildBody();

	}

	public void buildHeader() {
		header = new VerticalLayout();
		Label headerLabel = new Label("<h1>"
				+ app.getMessage(UimsMessages.ChangeUserDataHeader) + "</h1>");
		headerLabel.setContentMode(Label.CONTENT_XHTML);
		headerLabel.setSizeFull();
		header.addComponent(headerLabel);
		addComponent(header);
	}

	public void buildBody() {
		body = new FormLayout();
		userForm = new Form();
		Button save = new Button("Save");
		save.addListener((Button.ClickListener) this);

		userForm.setCaption("User: " + currentUser.getPrincipal().toString());
		userForm.setDescription("Set a complex password 6-20 characters long - numbers, latin letters and special symbols.");
		userForm.setImmediate(true);
		userForm.setValidationVisible(false);

		userForm.addField("name",
				new TextField("First Name", app.getInstName()));
		userForm.getField("name").setRequired(true);
		userForm.getField("name").setRequiredError(
				"Please enter your First Name!");
		userForm.getField("name").addValidator(
				new StringLengthValidator("First Name must be 3-25 characters",
						3, 25, false));

		userForm.addField("surname",
				new TextField("Last Name", app.getInstSurname()));
		userForm.getField("surname").setRequired(true);
		userForm.getField("surname").setRequiredError(
				"Please enter your Last Name!");
		userForm.getField("surname").addValidator(
				new StringLengthValidator("First Name must be 3-50 characters",
						3, 50, false));

		userForm.addField("pass", new PasswordField("Current Password"));
		userForm.getField("pass").setRequired(true);
		userForm.getField("pass").setRequiredError(
				"Please enter your Current Password!");

		userForm.addField("new_pass", new PasswordField("New Password"));
		userForm.getField("new_pass").setRequired(true);
		userForm.getField("new_pass").setRequiredError(
				"Please enter New Password!");
		userForm.getField("new_pass").addValidator(
				new RegexpValidator("[a-zA-Z0-9!@#$%^&*().,]{6,20}",
						"The password must be at least 6 symbols"));

		userForm.addField("conf_pass", new PasswordField("Confirm Password"));
		userForm.getField("conf_pass").setRequired(true);
		userForm.getField("conf_pass").setRequiredError(
				"Please confirm your New Password!");
		// userForm.getField("conf_pass").addValidator(confPassValidator);

		Label lb = new Label(userForm.getField("conf_pass"));
		lb.setImmediate(true);

		lb2 = new Label(userForm.getField("new_pass"));
		lb.setImmediate(true);
		userForm.getFooter().addComponent(save);

		body.addComponent(userForm);

		body.addComponent(lb);
		body.addComponent(lb2);
		addComponent(body);

	}

	public void buttonClick(ClickEvent event) {
		try {
			userForm.commit();
			if (!userForm.getField("new_pass").toString()
					.equals(userForm.getField("conf_pass").toString())) {
				getWindow().showNotification(
						"Passwords don't match, please confirm them!");
			} else {
				try {
					DbUsers dbUser = new DbUsers();
					dbUser.connect();
					dbUser.execSQL_pass(currentUser.getPrincipal().toString());
					ArrayList<Users> userList = dbUser.getArray();

					if (userForm.getField("pass").toString()
							.equals(userList.get(0).getUserPass())) {

						dbUser.editPass(currentUser.getPrincipal().toString(),
								userForm.getField("new_pass").toString());
						getWindow().showNotification(
								"Password is changed successfully!");
						try {  
				              
				            // This block configure the logger with handler and formatter  
				            fh = new FileHandler("home/alex/images/MyLogFile.log");  
				            logger.addHandler(fh);  
				            //logger.setLevel(Level.ALL);  
				            SimpleFormatter formatter = new SimpleFormatter();  
				            fh.setFormatter(formatter);  
				              
				            // the following statement is used to log any messages  
				            logger.info("My first log");  
				              
				        } catch (SecurityException e) {  
				            e.printStackTrace();  
				        } catch (IOException e) {  
				            e.printStackTrace();  
				        } 
					} else {
						getWindow().showNotification(
								"Enter correctly your Current Password!");
					}
					dbUser.close();
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	Validator confPassValidator = new Validator() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		// The isValid() method returns simply a boolean value, so
		// it can not return an error message.
		public boolean isValid(Object value) {
			if (value == null || !(value instanceof String)) {
				return false;
			}
			return (value.toString().equals(userForm.getField("new_pass")
					.toString()));
		}

		// Upon failure, the validate() method throws an exception
		// with an error message.
		public void validate(Object value) throws InvalidValueException {
			if (!isValid(value)) {

				throw new InvalidValueException("Dont match");

			}

		}
	};

}
