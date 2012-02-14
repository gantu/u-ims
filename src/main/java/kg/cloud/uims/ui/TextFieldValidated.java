package kg.cloud.uims.ui;

import kg.cloud.uims.MyVaadinApplication;
import kg.cloud.uims.i18n.UimsMessages;

import com.vaadin.data.Validator;
import com.vaadin.data.validator.IntegerValidator;
import com.vaadin.ui.TextField;

public class TextFieldValidated extends TextField{

	private MyVaadinApplication app;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public TextFieldValidated(final MyVaadinApplication app){
		this.app = app;
		setWidth("25%");
		setImmediate(true);
		
		
		// Create the validator
		Validator TextFieldValidator = new Validator() {

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

		        return ((String) value).matches("[0-9]{0,2}");
		    }

		    // Upon failure, the validate() method throws an exception
		    // with an error message.
		    public void validate(Object value)
		                throws InvalidValueException {
		        if (!isValid(value)) {
		            if (value != null &&
		                value.toString().startsWith("-")) {
		                throw new InvalidValueException(app.getMessage(UimsMessages.NotifNegative));
		            } else {
		                throw new InvalidValueException(app.getMessage(UimsMessages.NotifWrongNum));
		            }
		        }
		    }
		};
		
		addValidator(TextFieldValidator);
		
	}

}
