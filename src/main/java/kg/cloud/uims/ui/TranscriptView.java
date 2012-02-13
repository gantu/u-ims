package kg.cloud.uims.ui;

import kg.cloud.uims.MyVaadinApplication;
import kg.cloud.uims.i18n.UimsMessages;
import kg.cloud.uims.resources.DataContainers;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ChameleonTheme;

public class TranscriptView extends VerticalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MyVaadinApplication app;
	private VerticalLayout header, body;
	private Table groupStudentTable = new Table();
	private Window subWindow = new Window();

	public TranscriptView(MyVaadinApplication app) {
		this.app = app;
		setSpacing(true);
		buildHeader();
		buildBody();

	}

	public void buildHeader() {
		header = new VerticalLayout();
		Label headerLabel = new Label("<h1>"
				+ app.getMessage(UimsMessages.TranscriptHeader) + "</h1>");
		headerLabel.setContentMode(Label.CONTENT_XHTML);
		headerLabel.setSizeFull();
		header.addComponent(headerLabel);
		addComponent(header);
	}

	public void buildBody() {
		body = new VerticalLayout();
		body.addComponent(groupStudentTable);
		groupStudentTable.setWidth("100%");
		groupStudentTable.setSelectable(true);
		groupStudentTable.setImmediate(true);
		groupStudentTable.setStyleName(ChameleonTheme.TABLE_STRIPED);
		DataContainers container = new DataContainers(app);

		groupStudentTable
				.setContainerDataSource(container
						.getStudentByGroupContainer(Integer.toString(app
								.getGroupId())));

		groupStudentTable.addListener(new Table.ValueChangeListener() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void valueChange(ValueChangeEvent event) {

				if (null == event.getProperty().getValue()) {
				} else {
					String fullName = groupStudentTable
							.getItem(groupStudentTable.getValue())
							.getItemProperty(
									app.getMessage(UimsMessages.RegistrationStudentName))
							.getValue()
							+ " "
							+ groupStudentTable
									.getItem(groupStudentTable.getValue())
									.getItemProperty(
											app.getMessage(UimsMessages.RegistrationStudentSurname))
									.getValue();
					getWindow().addWindow(
							new TranscriptWindow(app, groupStudentTable
									.getValue().toString(), fullName));

				}
			}
		});

		addComponent(body);
	}

}