package kg.cloud.uims.ui;

import kg.cloud.uims.MyVaadinApplication;
import kg.cloud.uims.i18n.UimsMessages;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class HelpView extends VerticalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MyVaadinApplication app;
	private VerticalLayout header, body;
	
	public HelpView(MyVaadinApplication app) {
		this.app = app;
		setSpacing(true);
		buildHeader();
		buildBody();

	}

	public void buildHeader() {
		header = new VerticalLayout();
		Label headerLabel = new Label("<h1>"
				+ app.getMessage(UimsMessages.HelpHeader) + "</h1>");
		headerLabel.setContentMode(Label.CONTENT_XHTML);
		headerLabel.setSizeFull();
		header.addComponent(headerLabel);
		addComponent(header);
	}

	public void buildBody() {
		body = new VerticalLayout();
		Label testlabel = new Label("Test help");
		body.addComponent(testlabel);
		addComponent(body);
	}

	

}
