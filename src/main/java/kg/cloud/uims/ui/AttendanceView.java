package kg.cloud.uims.ui;

import java.text.NumberFormat;
import java.text.ParseException;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import kg.cloud.uims.MyVaadinApplication;
import kg.cloud.uims.i18n.UimsMessages;
import kg.cloud.uims.resources.DataContainers;

import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.ui.AbstractSelect;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Select;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ChameleonTheme;
import com.vaadin.ui.themes.Reindeer;
import com.vaadin.ui.themes.Runo;

public class AttendanceView extends VerticalLayout implements
		Property.ValueChangeListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Subject currentUser = SecurityUtils.getSubject();
	private MyVaadinApplication app;
	private VerticalLayout header, body;
	private Table instLessonTable = new Table();
	private String curr_Sem_id, curr_Year_id;
	private String department_id, year;
	private int hourSum;
	private Select deptSelect = new Select("Department");
	private Select yearSelect = new Select("Year");
	private DataContainers container;

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
		header.setMargin(false, false, false, true);
		header.addComponent(headerLabel);
		addComponent(header);
	}

	public void buildBody() {
		body = new VerticalLayout();
		this.curr_Sem_id = Integer.toString(app.getCurrentSemester().getId());
		this.curr_Year_id = Integer.toString(app.getCurrentYear().getId());
		container = new DataContainers(app);
		if (currentUser.hasRole("secretary")) {
			HorizontalLayout selectLay = new HorizontalLayout();
			selectLay.setSpacing(true);

			deptSelect.setWidth("2cm");
			deptSelect.setNullSelectionAllowed(false);
			deptSelect
					.setFilteringMode(AbstractSelect.Filtering.FILTERINGMODE_CONTAINS);
			deptSelect.setItemCaptionMode(Select.ITEM_CAPTION_MODE_PROPERTY);
			deptSelect.setItemCaptionPropertyId(app
					.getMessage(UimsMessages.DepartmentCode));
			deptSelect.setImmediate(true);
			deptSelect.addListener(this);
			IndexedContainer deptContainer = container.getDeptList(app
					.getFacultyId());
			deptSelect.setContainerDataSource(deptContainer);

			yearSelect.setWidth("2cm");
			yearSelect.setNullSelectionAllowed(false);
			yearSelect
					.setFilteringMode(AbstractSelect.Filtering.FILTERINGMODE_CONTAINS);
			yearSelect.setImmediate(true);
			yearSelect.addListener(this);

			for (int y = 1; y <= 5; y++) {
				yearSelect.addItem(y);
			}

			selectLay.addComponent(deptSelect);
			selectLay.addComponent(yearSelect);
			body.addComponent(selectLay);

		}
		body.addComponent(instLessonTable);
		instLessonTable.setWidth("100%");
		instLessonTable.setSelectable(true);
		instLessonTable.setImmediate(true);
		instLessonTable.setFooterVisible(true);
		instLessonTable.setStyleName(ChameleonTheme.TABLE_STRIPED);

		if (currentUser.hasRole("instructor")) {
			IndexedContainer instLessonContainer = container
					.getInstructor_Lesson(curr_Year_id, curr_Sem_id,
							currentUser.getPrincipal().toString());

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

			instLessonTable.setColumnFooter(
					app.getMessage(UimsMessages.StudyYear),
					app.getMessage(UimsMessages.SubjectHoursSum));
			instLessonTable.setColumnFooter(
					app.getMessage(UimsMessages.SubjectHoursPerWeek),
					Integer.toString(hourSum));
		}
		instLessonTable.addListener((Property.ValueChangeListener) this);

		addComponent(body);
	}

	public void valueChange(ValueChangeEvent event) {

		Property property = event.getProperty();

		if (property == instLessonTable) {

			if (null == event.getProperty().getValue()) {

			} else {
				String SubjName = instLessonTable
						.getItem(instLessonTable.getValue())
						.getItemProperty(
								app.getMessage(UimsMessages.SubjectName))
						.getValue().toString();
				getWindow().addWindow(
						new AttendanceWindow(app, instLessonTable.getValue()
								.toString(), SubjName));
			}
		}
		if (property == deptSelect) {

			if (null == event.getProperty().getValue()) {

			} else {
				department_id = event.getProperty().getValue().toString();
				if(year!=null){
					IndexedContainer lessonContainer = container.getLessonList_byDept(
							department_id, year, curr_Sem_id);

					instLessonTable.setContainerDataSource(lessonContainer);
					}
			}
		}
		if (property == yearSelect) {

			if (null == event.getProperty().getValue()) {

			} else {
				year = event.getProperty().getValue().toString();
				if(department_id!=null){
				IndexedContainer lessonContainer = container.getLessonList_byDept(
						department_id, year, curr_Sem_id);

				instLessonTable.setContainerDataSource(lessonContainer);
				}
			}
		}
	}

}