package kg.cloud.uims.ui;

import java.text.NumberFormat;
import java.text.ParseException;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import kg.cloud.uims.MyVaadinApplication;
import kg.cloud.uims.i18n.UimsMessages;
import kg.cloud.uims.resources.DataContainers;

import com.vaadin.data.Item;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ChameleonTheme;

public class AttendanceView extends VerticalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Subject currentUser = SecurityUtils.getSubject();
	private MyVaadinApplication app;
	private VerticalLayout header, body;
	private Table instLessonTable = new Table();
	private Window subWindow = new Window();
	private String curr_Sem_id, curr_Year_id;
	private int hourSum;

	public AttendanceView(MyVaadinApplication app) {
		this.app = app;
		setSpacing(true);
		buildHeader();
		buildBody();

	}

	public void buildHeader() {
		header = new VerticalLayout();
		Label headerLabel = new Label("<h1>"
				+ app.getMessage(UimsMessages.AttendanceHeader) + "</h1>");
		headerLabel.setContentMode(Label.CONTENT_XHTML);
		headerLabel.setSizeFull();
		header.addComponent(headerLabel);
		addComponent(header);
	}

	public void buildBody() {
		body = new VerticalLayout();
		this.curr_Sem_id = Integer.toString(app.getCurrentSemester().getId());
		this.curr_Year_id = Integer.toString(app.getCurrentYear().getId());
		body.addComponent(instLessonTable);
		instLessonTable.setWidth("100%");
		instLessonTable.setSelectable(true);
		instLessonTable.setImmediate(true);
		instLessonTable.setFooterVisible(true);
		instLessonTable.setStyleName(ChameleonTheme.TABLE_STRIPED);
		DataContainers container = new DataContainers(app);

		IndexedContainer instLessonContainer = container.getInstructor_Lesson(
				curr_Year_id, curr_Sem_id, currentUser.getPrincipal()
						.toString());

		instLessonTable.setContainerDataSource(instLessonContainer);

		for (int i = 0; i < instLessonContainer.size(); i++) {
			Item item = instLessonContainer.getItem(instLessonContainer
					.getIdByIndex(i));
			Object value = item.getItemProperty(
					app.getMessage(UimsMessages.SubjectHoursPerWeek))
					.getValue();

			Number amount;
			try {
				amount = NumberFormat.getNumberInstance().parse(
						value.toString());
				hourSum += amount.intValue();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		instLessonTable.setColumnFooter(app.getMessage(UimsMessages.StudyYear),
				app.getMessage(UimsMessages.SubjectHoursSum));
		instLessonTable.setColumnFooter(
				app.getMessage(UimsMessages.SubjectHoursPerWeek),
				Integer.toString(hourSum));

		instLessonTable.addListener(new Table.ValueChangeListener() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void valueChange(ValueChangeEvent event) {

				if (null == event.getProperty().getValue()) {

				} else {
					String SubjName = instLessonTable
							.getItem(instLessonTable.getValue())
							.getItemProperty(
									app.getMessage(UimsMessages.SubjectName))
							.getValue().toString();
					getWindow().addWindow(
							new AttendanceWindow(app, instLessonTable
									.getValue().toString(), SubjName));
				}
			}
		});

		addComponent(body);
	}

	public Window callSubWindow(String tableValue) {

		subWindow.setModal(true);
		VerticalLayout layout = new VerticalLayout();
		Label status = new Label(tableValue);
		layout.addComponent(status);
		subWindow.addComponent(layout);
		return subWindow;
	}

}