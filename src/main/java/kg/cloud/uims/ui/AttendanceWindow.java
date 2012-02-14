package kg.cloud.uims.ui;

import kg.cloud.uims.MyVaadinApplication;
import kg.cloud.uims.dao.DbStudent_Attendance;
import kg.cloud.uims.i18n.UimsMessages;
import kg.cloud.uims.resources.DataContainers;
import kg.cloud.uims.util.MakeAttendanceForm;
import kg.cloud.uims.util.MakeAttendanceReport;

import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.themes.ChameleonTheme;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.data.Item;
import com.vaadin.data.Property;

public class AttendanceWindow extends Window implements Button.ClickListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Table studentTable = new Table();

	Button makeForm = new Button();
	Button save = new Button();
	Button report = new Button();
	IndexedContainer studentDatasource;
	MyVaadinApplication app;

	private String subject_id = null;
	private String subjectName = null;
	private String curr_Sem_id, curr_Year_id, curr_Week_id;
	private Item selectedTableItem = null;
	private int status=0;
	private String errNotif = "";

	public AttendanceWindow(MyVaadinApplication app, String subj_Id,
			String subjName) {
		// super(app.getMessage(UimsMessages.RegistrationHeader+" : "+studentFullName));
		this.setModal(true);
		this.setCaption(app.getMessage(UimsMessages.AttendanceHeader) + ": "
				+ subjName);
		this.setWidth("80%");
		this.setHeight("95%");
		this.app = app;
		this.subject_id = subj_Id;
		this.subjectName = subjName;
		this.curr_Sem_id = Integer.toString(app.getCurrentSemester().getId());
		this.curr_Year_id = Integer.toString(app.getCurrentYear().getId());
		this.curr_Week_id = Integer.toString(app.getCurrentWeek().getId());

		studentTable.setWidth("100%");
		studentTable.setHeight("60%");
		studentTable.setImmediate(true);
		studentTable.setCaption(app.getMessage(UimsMessages.AttendanceHeader));
		studentTable.addStyleName(ChameleonTheme.TABLE_STRIPED);
		studentTable.setFooterVisible(true);
		studentTable.setSelectable(false);

		makeForm.setCaption(app.getMessage(UimsMessages.ButtonMakeForm));
		makeForm.addListener((Button.ClickListener) this);
		save.setCaption(app.getMessage(UimsMessages.SaveButton));
		save.addListener((Button.ClickListener) this);
		report.setCaption(app.getMessage(UimsMessages.ButtonMakeReport));
		report.addListener((Button.ClickListener) this);

		VerticalLayout mainLayout = new VerticalLayout();
		GridLayout infoLayout = new GridLayout(3, 1);
		HorizontalLayout buttonLayout = new HorizontalLayout();
		Label studInfo = new Label(app.getMessage(UimsMessages.SubjectName)
				+ " : " + subjectName);
		Label yearInfo = new Label(app.getMessage(UimsMessages.LabelYear)
				+ " : " + app.getCurrentYear().getYear());
		Label semInfo = new Label(app.getMessage(UimsMessages.LabelSemester)
				+ " : " + app.getCurrentSemester().getSemester());
		mainLayout.setSpacing(true);
		buttonLayout.setSpacing(true);
		infoLayout.setSizeFull();
		infoLayout.setSpacing(true);
		infoLayout.addComponent(studInfo, 0, 0);
		infoLayout.addComponent(yearInfo, 1, 0);
		infoLayout.addComponent(semInfo, 2, 0);
		buttonLayout.addComponent(makeForm);
		buttonLayout.addComponent(report);
		buttonLayout.addComponent(save);
		mainLayout.addComponent(infoLayout);
		mainLayout.addComponent(studentTable);
		mainLayout.addComponent(buttonLayout);

		DataContainers dc = new DataContainers(app);
		studentDatasource = dc.getSubjectStudList(subject_id, curr_Year_id,
				curr_Sem_id);
		studentTable.setContainerDataSource(studentDatasource);

		studentTable.setColumnFooter(
				app.getMessage(UimsMessages.RegistrationStudentSurname),
				app.getMessage(UimsMessages.TotalStudents));
		studentTable.setColumnFooter(app.getMessage(UimsMessages.GroupName),
				Integer.toString(studentDatasource.size()));

		addComponent(mainLayout);

	}

	public void buttonClick(ClickEvent event) {
		final Button source = event.getButton();

		if (source == makeForm) {
			new MakeAttendanceForm(app, subject_id);
		}
		if (source == report) {
			new MakeAttendanceReport(app, subject_id, subjectName);
		}
		if (source == save) {
			for (int i = 0; i < studentDatasource.size(); i++) {
				Item item = studentDatasource.getItem(studentDatasource
						.getIdByIndex(i));
				String currentAttendance = item
						.getItemProperty(
								app.getMessage(UimsMessages.StudentAttandance))
						.getValue().toString();
				if (!currentAttendance.equals("")) {
					try {
						if (Integer.parseInt(currentAttendance) < 0) {
							errNotif = app
									.getMessage(UimsMessages.NotifNegative);
						}
					} catch (Exception e) {
						errNotif = app.getMessage(UimsMessages.NotifWrongNum);
					}
				}

			}
			if (errNotif.equals("")) {
				try {

					DbStudent_Attendance dbStAtt = new DbStudent_Attendance();
					dbStAtt.connect();

					for (int i = 0; i < studentDatasource.size(); i++) {
						Item item = studentDatasource.getItem(studentDatasource
								.getIdByIndex(i));
						String stud_id = studentDatasource.getIdByIndex(i)
								.toString();
						String currentAttendance = item
								.getItemProperty(
										app.getMessage(UimsMessages.StudentAttandance))
								.getValue().toString();
						if (currentAttendance == null
								|| currentAttendance.equals("")) {
							currentAttendance = "0";
						}
						status=dbStAtt.insertAttendance(subject_id, curr_Year_id,
								curr_Sem_id, curr_Week_id, stud_id,
								currentAttendance);
					}
					dbStAtt.close();

				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (status!=0) {
						getWindow().showNotification(
								app.getMessage(UimsMessages.NotifSuccesSave));
					} else {
						getWindow().showNotification(
								app.getMessage(UimsMessages.NotifDbError));
					}
				}
			} else {
				getWindow().showNotification(errNotif);
				errNotif = "";
			}
		}
	}

}