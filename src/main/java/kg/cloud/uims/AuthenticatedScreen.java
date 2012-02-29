package kg.cloud.uims;

import java.util.logging.Level;
import java.util.logging.Logger;

import kg.cloud.uims.i18n.UimsMessages;
import kg.cloud.uims.ui.AttendanceView;
import kg.cloud.uims.ui.ChangeUserData;
import kg.cloud.uims.ui.ExamView;
import kg.cloud.uims.ui.HelpView;
import kg.cloud.uims.ui.RegistrationView;
import kg.cloud.uims.ui.SuccessReportView;
import kg.cloud.uims.ui.TranscriptView;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.vaadin.data.Item;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.HierarchicalContainer;
import com.vaadin.terminal.Resource;
import com.vaadin.terminal.Sizeable;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.Tree;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ChameleonTheme;

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
	private Subject currentUser = SecurityUtils.getSubject();

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
			app.workingDetails(currentUser.getPrincipal().toString());
		} catch (Exception ex) {
			Logger.getLogger(AuthenticatedScreen.class.getName()).log(
					Level.SEVERE, null, ex);
		}

		HorizontalLayout logLayout = new HorizontalLayout();

		Label logLabel = new Label("<b>"
				+ app.getMessage(UimsMessages.LogInAsLabel) + ": </b>");
		logLabel.setContentMode(Label.CONTENT_XHTML);

	
		ThemeResource iconUser = new ThemeResource("../runo/icons/16/user.png");

		Button userDetails = new Button(currentUser.getPrincipal().toString());
		userDetails.setStyleName(ChameleonTheme.BUTTON_LINK);
		userDetails.setDescription(app.getMessage(UimsMessages.ButtonTooltip));
		userDetails.setIcon(iconUser);
		userDetails.addListener((Button.ClickListener) this);

		logLayout.setSpacing(true);
		logLayout.addComponent(logLabel);
		logLayout.addComponent(userDetails);

		Label label = new Label("<b>"
				+ app.getMessage(UimsMessages.CurYearLabel) + ": </b> <i>"
				+ app.getCurrentYear().getYear() + "</i><br/>" + "<b>"
				+ app.getMessage(UimsMessages.CurSemesterLabel) + ": </b> <i>"
				+ app.getCurrentSemester().getSemester() + "</i><br/>" + "<b>"
				+ app.getMessage(UimsMessages.CurWeekLabel) + ": </b> <i>"
				+ app.getCurrentWeek().getWeek() + "</i>" + "</i><br/>" + "<b>"
				+ app.getMessage(UimsMessages.CurExamLabel) + ": </b> <i>"
				+ app.getCurrentExam().getExam() + "</i>");
		label.setContentMode(Label.CONTENT_XHTML);

		Button logout = new Button(app.getMessage(UimsMessages.LogoutButton));
		logout.addListener(new MyVaadinApplication.LogoutListener(this.app));

		statusLayout.addComponent(logLayout);
		statusLayout.addComponent(label);
		statusLayout.addComponent(logout);
		statusLayout.addComponent(buildTree());

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
		 * */
		String[] supervisor = { app.getMessage(UimsMessages.TBSupervisor),
				app.getMessage(UimsMessages.TSBRegistration),
				app.getMessage(UimsMessages.TSBTranscript),
				app.getMessage(UimsMessages.TSBSuccessReport)};
		String[] instructor = { app.getMessage(UimsMessages.TBInstructor),
				app.getMessage(UimsMessages.TSBAttendance),
				app.getMessage(UimsMessages.TSBExam) 
				};

		int size = 0;
		if (currentUser.hasRole("supervisor"))
			size++;
		if (currentUser.hasRole("instructor"))
			size++;

		String[][] navigation = new String[size][];
		int controller = 0;
		if (currentUser.hasRole("supervisor"))
			navigation[controller++] = supervisor;
		if (currentUser.hasRole("instructor"))
			navigation[controller++] = instructor;

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
					String eventPressed = navTree
							.getItem(event.getProperty().getValue())
							.getItemProperty("name").toString();

					if (eventPressed.equals(app
							.getMessage(UimsMessages.TSBRegistration))) {
						// getWindow().showNotification(eventPressed);
						horizontalPanel
								.setSecondComponent(new RegistrationView(app));

					} else if (eventPressed.equals(app
							.getMessage(UimsMessages.TSBTranscript))) {
						// getWindow().showNotification(eventPressed);
						horizontalPanel.setSecondComponent(new TranscriptView(
								app));

					} else if (eventPressed.equals(app
							.getMessage(UimsMessages.TSBSuccessReport))) {
						// getWindow().showNotification(eventPressed);
						horizontalPanel
								.setSecondComponent(new SuccessReportView(app));

					} else if (eventPressed.equals(app
							.getMessage(UimsMessages.TSBAttendance))) {
						// getWindow().showNotification(eventPressed);
						horizontalPanel.setSecondComponent(new AttendanceView(
								app));

					} else if (eventPressed.equals(app
							.getMessage(UimsMessages.TSBExam))) {
						// getWindow().showNotification(eventPressed);
						horizontalPanel.setSecondComponent(new ExamView(
								app));

					}
				}

			}
		});
		return navTree;

	}

	public void buttonClick(Button.ClickEvent event) {
		this.horizontalPanel.setSecondComponent(new ChangeUserData(app));
	}

}
