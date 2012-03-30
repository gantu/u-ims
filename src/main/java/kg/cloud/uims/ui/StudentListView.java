package kg.cloud.uims.ui;

import kg.cloud.uims.MyVaadinApplication;
import kg.cloud.uims.i18n.UimsMessages;
import kg.cloud.uims.resources.DataContainers;
import kg.cloud.uims.util.ExportReporttoPDF;
import kg.cloud.uims.util.MakeStudentListPDF;

import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ChameleonTheme;

public class StudentListView extends VerticalLayout implements
		Property.ValueChangeListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MyVaadinApplication app;
	private VerticalLayout header, body;
	private Table groupTable = new Table();

	public StudentListView(MyVaadinApplication app) {
		this.app = app;
		setSpacing(true);
		buildHeader();
		buildBody();

	}

	public void buildHeader() {
		header = new VerticalLayout();
		Label headerLabel = new Label("<h1>"
				+ app.getMessage(UimsMessages.TSBStudentList) + "</h1>");
		headerLabel.setContentMode(Label.CONTENT_XHTML);
		header.setMargin(false, false, false, true);
		header.addComponent(headerLabel);
		addComponent(header);
	}

	public void buildBody() {

		body = new VerticalLayout();
		body.addComponent(groupTable);
		groupTable.setWidth("100%");
		groupTable.setSelectable(true);
		groupTable.setImmediate(true);
		groupTable.setStyleName(ChameleonTheme.TABLE_STRIPED);

		DataContainers container = new DataContainers(app);

		groupTable.setContainerDataSource(container.getGroupList(app
				.getFacultyId()));

		groupTable.addListener((Property.ValueChangeListener) this);

		addComponent(body);
	}

	public void valueChange(ValueChangeEvent event) {
		Property property = event.getProperty();

		if (property == groupTable) {
			if (null == event.getProperty().getValue()) {

			} else {
				new MakeStudentListPDF(app, groupTable.getValue().toString());
			}
		}

	}

}