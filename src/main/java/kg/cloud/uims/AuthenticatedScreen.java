package kg.cloud.uims;

import com.vaadin.terminal.Sizeable;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalSplitPanel;

public class AuthenticatedScreen extends VerticalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MyVaadinApplication app;
	private HorizontalSplitPanel horizontalPanel;
	private GridLayout controlsLayout;
	private VerticalLayout statusLayout;
	private VerticalLayout navigationLayout;

	public AuthenticatedScreen(MyVaadinApplication app) {
		super();
		this.app = app;
		horizontalPanel = new HorizontalSplitPanel();
		horizontalPanel.setSplitPosition(20, Sizeable.UNITS_PERCENTAGE);
		horizontalPanel.setSizeFull();
		horizontalPanel.setLocked(true);

		controlsLayout = new GridLayout(1, 3);
		controlsLayout.setSizeFull();

		// controlsLayout.setStyleName("segment-alternate");

		statusLayout = new VerticalLayout();
		setSizeFull();
		try {
			app.workingDetails();
		} catch (Exception ex) {
			Logger.getLogger(AuthenticatedScreen.class.getName()).log(
					Level.SEVERE, null, ex);
		}

		Subject currentUser = SecurityUtils.getSubject();
		Label label = new Label("<b>Logged in as </b> <i>"
				+ currentUser.getPrincipal().toString() + "</i><br/>"

				+ "<b>Current Semester: </b><i>" + app.getCurrentSemester()
				+ "</i><br/>" + "<b>Current Year: </b><i>"
				+ app.getCurrentYear() + "</i>");
		label.setContentMode(Label.CONTENT_XHTML);

		Button admin = new Button("For administrators only");
		admin.addListener(new Button.ClickListener() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void buttonClick(ClickEvent event) {

			}
		});
		Button user = new Button("For users only");
		if (!currentUser.hasRole("admin")) {
			admin.setEnabled(false);
		} else if (!currentUser.hasRole("user")) {
			user.setEnabled(false);
		}

		Button perm = new Button("For all with permission 'permission_2' only");
		if (!currentUser.isPermitted("permission_2")) {
			perm.setEnabled(false);
		}

		Button logout = new Button("logout");
		logout.addListener(new MyVaadinApplication.LogoutListener(this.app));

		statusLayout.addComponent(label);
		statusLayout.addComponent(logout);

		controlsLayout.addComponent(admin, 0, 0);
		controlsLayout.addComponent(user, 0, 1);
		controlsLayout.addComponent(perm, 0, 2);

		navigationLayout = new VerticalLayout();
		navigationLayout.addComponent(statusLayout);
		navigationLayout.addComponent(controlsLayout);
		navigationLayout.setSpacing(true);
		navigationLayout.setSizeFull();
		horizontalPanel.setFirstComponent(navigationLayout);
		this.addComponent(horizontalPanel);

	}

}
