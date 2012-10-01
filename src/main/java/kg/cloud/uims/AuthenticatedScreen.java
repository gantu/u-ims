package kg.cloud.uims;

import com.vaadin.data.Item;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.HierarchicalContainer;
import com.vaadin.terminal.Resource;
import com.vaadin.terminal.Sizeable;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ChameleonTheme;
import java.util.logging.Level;
import java.util.logging.Logger;
import kg.cloud.uims.i18n.UimsMessages;
import kg.cloud.uims.ui.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

public class AuthenticatedScreen extends VerticalLayout implements
        Button.ClickListener {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private MyVaadinApplication app;
    private HorizontalSplitPanel horizontalPanel;
    private VerticalLayout statusLayout;
    private VerticalLayout navigationLayout;
    private Tree navTree;
    private Button userDetails, help;
    private Subject currentUser = SecurityUtils.getSubject();
    private String regStatus = "";
    private String name, surname;

    public AuthenticatedScreen(MyVaadinApplication app) {

        super();
        this.app = app;

        horizontalPanel = new HorizontalSplitPanel();
        horizontalPanel.setSplitPosition(20, Sizeable.UNITS_PERCENTAGE);
        horizontalPanel.setSizeFull();
        horizontalPanel.setLocked(true);

        statusLayout = new VerticalLayout();

        setSizeFull();
        try {
            if (currentUser.hasRole("student")) {
                app.studWorkingDetails(currentUser.getPrincipal().toString());
            } else {
                app.workingDetails(currentUser.getPrincipal().toString());
            }
        } catch (Exception ex) {
            Logger.getLogger(AuthenticatedScreen.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
        if (app.getCurrentSemester().getRegStatus() == 1) {
            regStatus = app.getMessage(UimsMessages.RegistrationOpened);
        } else {
            regStatus = app.getMessage(UimsMessages.RegistrationClosed);
        }
        GridLayout logLayout = new GridLayout(2, 2);
        HorizontalLayout userlog = new HorizontalLayout();

        Label logLabel = new Label("<b>"
                + app.getMessage(UimsMessages.LogInAsLabel) + ": </b>");
        logLabel.setContentMode(Label.CONTENT_XHTML);

        ThemeResource iconUser = new ThemeResource("../runo/icons/16/user.png");
        ThemeResource iconHelp = new ThemeResource("../runo/icons/16/help.png");

        userDetails = new Button(currentUser.getPrincipal().toString());
        userDetails.setStyleName(ChameleonTheme.BUTTON_LINK);
        userDetails.setDescription(app.getMessage(UimsMessages.ButtonTooltip));
        userDetails.setIcon(iconUser);
        userDetails.addListener((Button.ClickListener) this);

        help = new Button("Help");
        help.setStyleName(ChameleonTheme.BUTTON_LINK);
        help.setIcon(iconHelp);
        help.addListener((Button.ClickListener) this);

        Button logout = new Button(app.getMessage(UimsMessages.LogoutButton));
        logout.addListener(new MyVaadinApplication.LogoutListener(this.app));
        logout.setStyleName(ChameleonTheme.BUTTON_SMALL);

        userlog.addComponent(logLabel);
        userlog.addComponent(userDetails);
        logLayout.setSizeFull();
        logLayout.setComponentAlignment(help, Alignment.MIDDLE_RIGHT);
        logLayout.addComponent(help, 1, 0);
        logLayout.addComponent(logout, 0, 0);
        logLayout.addComponent(userlog, 0, 1);
        if (currentUser.hasRole("student")) {
            name = app.getStudent().getName();
            surname = app.getStudent().getSurname();
        } else {
            name = app.getName();
            surname = app.getSurname();
        }
        Label label = new Label("<b>"
                + app.getMessage(UimsMessages.FullName) + ": </b> <i>"
                + name + " " + surname + "</i><br/>" + "<b>"
                + app.getMessage(UimsMessages.CurYearLabel) + ": </b> <i>"
                + app.getCurrentYear().getYear() + "</i><br/>" + "<b>"
                + app.getMessage(UimsMessages.CurSemesterLabel) + ": </b> <i>"
                + app.getCurrentSemester().getSemester() + "</i><br/>" + "<b>"
                + app.getMessage(UimsMessages.CurWeekLabel) + ": </b> <i>"
                + app.getCurrentWeek().getWeek() + "</i>" + "</i><br/>" + "<b>"
                + app.getMessage(UimsMessages.CurExamLabel) + ": </b> <i>"
                + app.getCurrentExam().getExam() + "</i><br/>" + "<b>"
                + app.getMessage(UimsMessages.RegistrationHeader) + ": </b> <i>"
                + regStatus + "</i>");

        label.setContentMode(Label.CONTENT_XHTML);



        statusLayout.addComponent(logLayout);
        statusLayout.addComponent(label);
        //statusLayout.addComponent(logout);
        statusLayout.addComponent(buildTree());
        if (app.getuserStatus() == 0) {
            userDetails.setEnabled(false);
            Label warning = new Label("<br><h2>"
                    + app.getMessage(UimsMessages.SystemClosedNotif) + "</h2>");
            warning.setContentMode(Label.CONTENT_XHTML);
            statusLayout.addComponent(warning);
        }

        navigationLayout = new VerticalLayout();
        navigationLayout.addComponent(statusLayout);

        navigationLayout.setSpacing(true);
        navigationLayout.setSizeFull();
        horizontalPanel.setFirstComponent(navigationLayout);
        horizontalPanel.setSecondComponent(new HelpView(app));
        this.addComponent(horizontalPanel);

    }

    public Tree buildTree() {

        /**
         * , app.getMessage(UimsMessages.TSBSuccessReport)
         *
         *
         */
        String[] supervisor = {app.getMessage(UimsMessages.TBSupervisor),
            app.getMessage(UimsMessages.TSBRegistration),
            app.getMessage(UimsMessages.TSBTranscript),
            app.getMessage(UimsMessages.TSBSuccessReport)};
        String[] instructor = {app.getMessage(UimsMessages.TBInstructor),
            app.getMessage(UimsMessages.TSBAttendance),
            app.getMessage(UimsMessages.TSBExam),};

        String[] secretary = {app.getMessage(UimsMessages.TBSecretary),
            app.getMessage(UimsMessages.TSBAttendance),
            app.getMessage(UimsMessages.TSBExam),
            app.getMessage(UimsMessages.TSBStudentList),
            app.getMessage(UimsMessages.TSBSuccessReport)};

        String[] student = {app.getMessage(UimsMessages.TBStudent),
            app.getMessage(UimsMessages.TSBTranscript),
            app.getMessage(UimsMessages.TSBSuccessReport),
            app.getMessage(UimsMessages.TSBMyInfo)};

        int size = 0;
        if (currentUser.hasRole("supervisor")) {
            size++;
        }
        if (currentUser.hasRole("instructor")) {
            size++;
        }
        if (currentUser.hasRole("secretary")) {
            size++;
        }
        if (currentUser.hasRole("student")) {
            size++;
        }

        String[][] navigation = new String[size][];
        int controller = 0;
        if (currentUser.hasRole("supervisor")) {
            navigation[controller++] = supervisor;
        }
        if (currentUser.hasRole("instructor")) {
            navigation[controller++] = instructor;
        }
        if (currentUser.hasRole("secretary")) {
            navigation[controller++] = secretary;
        }
        if (currentUser.hasRole("student")) {
            navigation[controller++] = student;
        }

        Object propertyName = "name";
        Object propertyIcon = "icon";
        Item item = null;
        int itemId = 0;

        HierarchicalContainer navContainer = new HierarchicalContainer();
        navContainer.addContainerProperty(propertyName, String.class, null);
        navContainer.addContainerProperty(propertyIcon, Resource.class, null);

        for (int i = 0; i < navigation.length; i++) {
            // Add new item
            item = navContainer.addItem(itemId);
            // Add name property for item
            item.getItemProperty(propertyName).setValue(navigation[i][0]);
            // Allow children
            navContainer.setChildrenAllowed(itemId, true);
            itemId++;
            for (int j = 1; j < navigation[i].length; j++) {
                if (j == 1) {
                    item.getItemProperty(propertyIcon).setValue(
                            new ThemeResource("../runo/icons/16/folder.png"));
                }
                // Add child items
                item = navContainer.addItem(itemId);
                item.getItemProperty(propertyName).setValue(navigation[i][j]);
                navContainer.setParent(itemId, itemId - j);
                navContainer.setChildrenAllowed(itemId, false);

                itemId++;
            }
        }
        navTree = new Tree();
        navTree.setContainerDataSource(navContainer);
        navTree.setItemCaptionPropertyId("name");
        navTree.setItemIconPropertyId("icon");
        navTree.setItemCaptionMode(Tree.ITEM_CAPTION_MODE_PROPERTY);
        navTree.setImmediate(true);

        if (app.getuserStatus() == 0) {
            navTree.setEnabled(false);
        }

        navTree.addListener(new ValueChangeListener() {

            /**
             *
             */
            private static final long serialVersionUID = 1L;

            public void valueChange(ValueChangeEvent event) {
                if (event.getProperty().getValue() != null) {
                    // If something is selected from the tree, get it's 'name'
                    // and
                    // insert it into the textfield
                    String eventPressed = navTree.getItem(event.getProperty().
                            getValue()).getItemProperty("name").toString();

                    if (eventPressed.equals(app.getMessage(UimsMessages.TSBRegistration))) {
                        // getWindow().showNotification(eventPressed);
                        horizontalPanel.setSecondComponent(new RegistrationView(app));

                    } else if (eventPressed.equals(app.getMessage(UimsMessages.TSBTranscript))
                            && !currentUser.hasRole("student")) {
                        // getWindow().showNotification(eventPressed);
                        horizontalPanel.setSecondComponent(new TranscriptView(
                                app));

                    } else if (eventPressed.equals(app.getMessage(UimsMessages.TSBSuccessReport))
                            && !currentUser.hasRole("student")) {
                        // getWindow().showNotification(eventPressed);
                        horizontalPanel.setSecondComponent(new SuccessReportView(app));

                    } else if (eventPressed.equals(app.getMessage(UimsMessages.TSBAttendance))) {
                        // getWindow().showNotification(eventPressed);
                        horizontalPanel.setSecondComponent(new AttendanceView(
                                app));

                    } else if (eventPressed.equals(app.getMessage(UimsMessages.TSBExam))) {
                        // getWindow().showNotification(eventPressed);
                        horizontalPanel.setSecondComponent(new ExamView(app));

                    } else if (eventPressed.equals(app.getMessage(UimsMessages.TSBStudentList))) {
                        // getWindow().showNotification(eventPressed);
                        horizontalPanel.setSecondComponent(new StudentListView(
                                app));
                    } else if (eventPressed.equals(app.getMessage(UimsMessages.TSBSuccessReport))) {
                        // getWindow().showNotification(eventPressed);
                        horizontalPanel.setSecondComponent(new SuccessReportStudView(
                                app));
                    } else if (eventPressed.equals(app.getMessage(UimsMessages.TSBTranscript))) {
                        // getWindow().showNotification(eventPressed);
                        horizontalPanel.setSecondComponent(new TranscriptStudView(app));

                    } else if (eventPressed.equals(app.getMessage(UimsMessages.TSBMyInfo))) {
                        // getWindow().showNotification(eventPressed);
                        horizontalPanel.setSecondComponent(new MyInfoView(app));

                    } else if (eventPressed.equals(app.getMessage(UimsMessages.TBSupervisor))
                            || eventPressed.equals(app.getMessage(UimsMessages.TBInstructor))
                            || eventPressed.equals(app.getMessage(UimsMessages.TBSecretary))
                            || eventPressed.equals(app.getMessage(UimsMessages.TBStudent))) {

                        if (navTree.isExpanded(event.getProperty().getValue())) {

                            navTree.collapseItem(event.getProperty().getValue());
                        } else {
                            navTree.expandItem(event.getProperty().getValue());
                        }
                    }
                }

            }
        });
        return navTree;

    }

    public void buttonClick(Button.ClickEvent event) {
        final Button source = event.getButton();
        if (source == userDetails) {
            this.horizontalPanel.setSecondComponent(new ChangeUserData(app));
        }

        if (source == help) {
            this.horizontalPanel.setSecondComponent(new HelpView(app));
        }
    }
}
