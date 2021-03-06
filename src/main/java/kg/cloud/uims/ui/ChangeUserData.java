package kg.cloud.uims.ui;

import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.*;
import java.util.ArrayList;
import kg.cloud.uims.MyVaadinApplication;
import kg.cloud.uims.dao.DbUsers;
import kg.cloud.uims.domain.Users;
import kg.cloud.uims.i18n.UimsMessages;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.subject.Subject;

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
    private String name, surname;

    public ChangeUserData(MyVaadinApplication app) {
        this.app = app;
        if (currentUser.hasRole("student")) {
            name = app.getStudent().getName();
            surname = app.getStudent().getSurname();
        } else {
            name = app.getName();
            surname = app.getSurname();
        }
        setSpacing(true);
        buildHeader();
        buildBody();

    }

    public void buildHeader() {
        header = new VerticalLayout();
        Label headerLabel = new Label("<h1>"
                + app.getMessage(UimsMessages.ChangeUserDataHeader) + "</h1>");
        headerLabel.setContentMode(Label.CONTENT_XHTML);
        header.setMargin(false, false, false, true);
        header.addComponent(headerLabel);
        addComponent(header);
    }

    public void buildBody() {
        body = new FormLayout();
        userForm = new Form();
        ThemeResource iconOK = new ThemeResource("../runo/icons/16/ok.png");
        Button save = new Button(app.getMessage(UimsMessages.SaveButton));
        save.setIcon(iconOK);
        save.addListener((Button.ClickListener) this);
        save.setStyleName("default");

        userForm.setCaption(app.getMessage(UimsMessages.FormCaptionUser) + ": "
                + currentUser.getPrincipal().toString());
        userForm.setDescription(app.getMessage(UimsMessages.FormDescription));
        userForm.setImmediate(true);

        userForm.addField(
                "name",
                new TextField(app.getMessage(UimsMessages.RegistrationStudentName),
                name));
        // userForm.getField("name").setRequired(true);
        // userForm.getField("name").setRequiredError("Please enter your First Name!");
        // userForm.getField("name").addValidator(new StringLengthValidator
        // ("First Name must be 3-25 characters", 3, 25, false));
        userForm.getField("name").setEnabled(false);

        userForm.addField(
                "surname",
                new TextField(app.getMessage(UimsMessages.RegistrationStudentSurname),
                surname));
        // userForm.getField("surname").setRequired(true);
        // userForm.getField("surname").setRequiredError("Please enter your Last Name!");
        // userForm.getField("surname").addValidator(new StringLengthValidator
        // ("First Name must be 3-50 characters", 3, 50, false));
        userForm.getField("surname").setEnabled(false);

        userForm.addField(
                "pass",
                new PasswordField(app.getMessage(UimsMessages.FormFiledCurPassword)));
        userForm.getField("pass").setRequired(true);
        userForm.getField("pass").setRequiredError(
                app.getMessage(UimsMessages.RequiredErrorCurrPassword));

        userForm.addField(
                "new_pass",
                new PasswordField(app.getMessage(UimsMessages.FormFiledNewPassword)));
        userForm.getField("new_pass").setRequired(true);
        userForm.getField("new_pass").setRequiredError(
                app.getMessage(UimsMessages.RequiredErrorNewPassword));
        userForm.getField("new_pass").addValidator(
                new RegexpValidator("[a-zA-Z0-9!@#$%^&*().,]{6,20}", app.getMessage(UimsMessages.RegexpValidatorError)));

        userForm.addField(
                "conf_pass",
                new PasswordField(app.getMessage(UimsMessages.FormFiledConfPassword)));
        userForm.getField("conf_pass").setRequired(true);
        userForm.getField("conf_pass").setRequiredError(
                app.getMessage(UimsMessages.RequiredErrorConfPassword));
        // userForm.getField("conf_pass").addValidator(confPassValidator);

        userForm.getFooter().addComponent(save);

        body.addComponent(userForm);
        addComponent(body);

    }

    public void buttonClick(ClickEvent event) {
        try {
            userForm.commit();
            if (!userForm.getField("new_pass").toString().equals(userForm.getField("conf_pass").toString())) {
                getWindow().showNotification(
                        app.getMessage(UimsMessages.NotifDontMatch));
            } else {
                try {
                    DbUsers dbUser = new DbUsers();
                    dbUser.connect();
                    dbUser.execSQL_pass(currentUser.getPrincipal().toString());
                    ArrayList<Users> userList = dbUser.getArray();

                    if (new Sha256Hash(userForm.getField("pass").toString()).toString().equals(userList.get(0).getUserPass())) {

                        dbUser.editPass(currentUser.getPrincipal().toString(),
                                new Sha256Hash(userForm.getField("new_pass").toString()).toString());
                        getWindow().showNotification(
                                app.getMessage(UimsMessages.NotifSuccessfulChange));

                    } else {
                        getWindow().showNotification(
                                app.getMessage(UimsMessages.NotifWrongCurrPassword));
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
}