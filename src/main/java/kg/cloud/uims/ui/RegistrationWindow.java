package kg.cloud.uims.ui;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;

import kg.cloud.uims.MyVaadinApplication;
import kg.cloud.uims.dao.DbStudLess;
import kg.cloud.uims.i18n.UimsMessages;
import kg.cloud.uims.resources.DataContainers;

import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class RegistrationWindow extends Window implements Button.ClickListener,
		Property.ValueChangeListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Table notTakenSubjects = new Table("Not Taken Subjects :");
	Table currentSubjects = new Table("Current Subjects");
	Button toPDF = new Button("Make PDF");
	Button save = new Button("Save");
	Button moveDown = new Button("Move Down");
	Button moveUp = new Button("Move Up");
	IndexedContainer registeredDatasource;
	IndexedContainer notTakenDatasource;
	int hourSum = 0;
	private Item selectedTableItem1 = null;
	private Item selectedTableItem2 = null;
	private String subjectID;
	private String subjectIDselected1;
	private Boolean saveStatus = false;
	private String studentId;

	MyVaadinApplication app;

	public RegistrationWindow(MyVaadinApplication app, String studentId,
			String studentFullName) {
		// super(app.getMessage(UimsMessages.RegistrationHeader+" : "+studentFullName));
		this.setModal(true);
		this.setWidth("80%");
		this.setHeight("100%");
		this.app = app;
		this.studentId = studentId;

		currentSubjects.setWidth("100%");
		currentSubjects.setHeight("60%");
		currentSubjects.setSelectable(true);
		currentSubjects.setImmediate(true);
		currentSubjects.setFooterVisible(true);

		notTakenSubjects.setWidth("100%");
		notTakenSubjects.setHeight("20%");
		notTakenSubjects.setSelectable(true);
		notTakenSubjects.setImmediate(true);

		VerticalLayout mainLayout = new VerticalLayout();
		VerticalLayout tablesLayout = new VerticalLayout();
		HorizontalLayout controlButtons = new HorizontalLayout();
		HorizontalLayout controlLayout = new HorizontalLayout();

		mainLayout.setSpacing(true);
		tablesLayout.addComponent(notTakenSubjects);
		tablesLayout.addComponent(controlButtons);
		tablesLayout.addComponent(currentSubjects);

		currentSubjects.addListener((ValueChangeListener) this);
		notTakenSubjects.addListener((ValueChangeListener) this);
		controlButtons.setWidth("100%");
		controlButtons.setHeight("10%");

		moveDown.addListener((Button.ClickListener) this);
		moveUp.addListener((Button.ClickListener) this);
		save.addListener((Button.ClickListener) this);

		controlButtons.addComponent(moveDown);
		controlButtons.addComponent(moveUp);

		controlLayout.addComponent(toPDF);
		controlLayout.addComponent(save);

		fillTables();

		Label studInfo = new Label("Student : " + studentFullName);
		mainLayout.addComponent(studInfo);
		mainLayout.addComponent(tablesLayout);
		mainLayout.addComponent(controlLayout);
		addComponent(mainLayout);

	}

	public void buttonClick(ClickEvent event) {
		final Button source = event.getButton();
		if (source == moveDown) {

			if (selectedTableItem1 != null) {
				hourSum += Integer.parseInt(selectedTableItem1.getItemProperty(
						app.getMessage(UimsMessages.SubjectHour)).toString());

				if (hourSum < 40) {
					Item item = registeredDatasource.addItem(subjectID);
					item.getItemProperty(
							app.getMessage(UimsMessages.SubjectCode)).setValue(
							selectedTableItem1.getItemProperty(app
									.getMessage(UimsMessages.SubjectCode)));
					item.getItemProperty(
							app.getMessage(UimsMessages.SubjectName)).setValue(
							selectedTableItem1.getItemProperty(app
									.getMessage(UimsMessages.SubjectName)));
					item.getItemProperty(
							app.getMessage(UimsMessages.SubjectCredit))
							.setValue(
									selectedTableItem1.getItemProperty(app
											.getMessage(UimsMessages.SubjectCredit)));
					item.getItemProperty(
							app.getMessage(UimsMessages.SubjectHour)).setValue(
							selectedTableItem1.getItemProperty(app
									.getMessage(UimsMessages.SubjectHour)));
					item.getItemProperty(app.getMessage(UimsMessages.StudyYear))
							.setValue(
									selectedTableItem1.getItemProperty(app
											.getMessage(UimsMessages.StudyYear)));
					item.getItemProperty(app.getMessage(UimsMessages.Semester))
							.setValue(
									selectedTableItem1.getItemProperty(app
											.getMessage(UimsMessages.Semester)));
					item.getItemProperty(
							app.getMessage(UimsMessages.SubjectRegistrationStatus))
							.setValue(new String("0"));

					currentSubjects
							.setContainerDataSource(registeredDatasource);

					notTakenDatasource.removeItem(subjectID);
					notTakenSubjects.setContainerDataSource(notTakenDatasource);
					currentSubjects.setColumnFooter(
							app.getMessage(UimsMessages.SubjectHour),
							Integer.toString(hourSum));

					saveStatus = true;

					selectedTableItem1 = null;
				} else {
					hourSum -= Integer.parseInt(selectedTableItem1
							.getItemProperty(
									app.getMessage(UimsMessages.SubjectHour))
							.toString());
					getWindow().showNotification(
							"Sum of Subject hours can not exceed 40!");
				}
			} else {
				getWindow().showNotification("Nothing is selected");
			}
		}

		if (source == moveUp) {
			if (selectedTableItem2 != null) {
				if (selectedTableItem2
						.getItemProperty(
								app.getMessage(UimsMessages.SubjectRegistrationStatus))
						.toString().equals("0")) {
					Item item = notTakenDatasource.addItem(subjectIDselected1);
					item.getItemProperty(
							app.getMessage(UimsMessages.SubjectCode)).setValue(
							selectedTableItem2.getItemProperty(app
									.getMessage(UimsMessages.SubjectCode)));
					item.getItemProperty(
							app.getMessage(UimsMessages.SubjectName)).setValue(
							selectedTableItem2.getItemProperty(app
									.getMessage(UimsMessages.SubjectName)));
					item.getItemProperty(
							app.getMessage(UimsMessages.SubjectCredit))
							.setValue(
									selectedTableItem2.getItemProperty(app
											.getMessage(UimsMessages.SubjectCredit)));
					item.getItemProperty(
							app.getMessage(UimsMessages.SubjectHour)).setValue(
							selectedTableItem2.getItemProperty(app
									.getMessage(UimsMessages.SubjectHour)));
					item.getItemProperty(app.getMessage(UimsMessages.StudyYear))
							.setValue(
									selectedTableItem2.getItemProperty(app
											.getMessage(UimsMessages.StudyYear)));
					item.getItemProperty(app.getMessage(UimsMessages.Semester))
							.setValue(
									selectedTableItem2.getItemProperty(app
											.getMessage(UimsMessages.Semester)));

					hourSum -= Integer.parseInt(selectedTableItem2
							.getItemProperty(
									app.getMessage(UimsMessages.SubjectHour))
							.toString());
					currentSubjects.setColumnFooter(
							app.getMessage(UimsMessages.SubjectHour),
							Integer.toString(hourSum));

					registeredDatasource.removeItem(subjectIDselected1);
					notTakenSubjects.setContainerDataSource(notTakenDatasource);
					currentSubjects
							.setContainerDataSource(registeredDatasource);
					selectedTableItem2 = null;
					// getWindow().showNotification("The item "+selectedTableItem2.getItemProperty(app.getMessage(UimsMessages.SubjectCode)).toString()+"will be removed");
				} else {
					getWindow().showNotification(
							"You can not remove this Subject!");
				}
			}

		}
		if (source == save) {
			if (registeredDatasource != null) {
				if (saveStatus) {
					try {
						DbStudLess dbsl = new DbStudLess();
						dbsl.connect();
						final String status = "1";
						final String semester = Integer.toString(app
								.getCurrentSemester().getId());
						final String year = Integer.toString(app
								.getCurrentYear().getId());

						for (int i = 0; i < registeredDatasource.size(); i++) {
							Item item = registeredDatasource
									.getItem(registeredDatasource
											.getIdByIndex(i));

							Object value = item
									.getItemProperty(
											app.getMessage(UimsMessages.SubjectRegistrationStatus))
									.getValue();
							if (value.toString().equals("0")) {
								final String subject = registeredDatasource
										.getIdByIndex(i).toString();
								dbsl.execRegistration(studentId, subject,
										status, year, semester);
								item.getItemProperty(
										app.getMessage(UimsMessages.SubjectRegistrationStatus))
										.setValue("1");

							}
						}
						saveStatus = false;
						getWindow().showNotification(
								"Thank you registration is completed!");
						dbsl.close();

					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					currentSubjects
							.setContainerDataSource(registeredDatasource);
					save.setEnabled(false);

				}
				// getWindow().showNotification(
				// registeredDatasource.getIdByIndex(0).toString());
			}

		}

	}

	public void fillTables() {
		DataContainers dc = new DataContainers(app);
		notTakenDatasource = dc.getStudentNotTookYetSubjects(studentId,
				Integer.toString(app.getCurrentSemester().getId()));
		notTakenSubjects.setContainerDataSource(notTakenDatasource);

		registeredDatasource = dc.getStudentRegisteredSubjects(studentId,
				Integer.toString(app.getCurrentSemester().getId()),
				Integer.toString(app.getCurrentYear().getId()));
		currentSubjects.setContainerDataSource(registeredDatasource);

		for (int i = 0; i < registeredDatasource.size(); i++) {
			Item item = registeredDatasource.getItem(registeredDatasource
					.getIdByIndex(i));
			Object value = item.getItemProperty(
					app.getMessage(UimsMessages.SubjectHour)).getValue();

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
		currentSubjects.setColumnFooter(
				app.getMessage(UimsMessages.SubjectName),
				app.getMessage(UimsMessages.SubjectHoursSum));
		currentSubjects.setColumnFooter(
				app.getMessage(UimsMessages.SubjectHour),
				Integer.toString(hourSum));

	}

	public void valueChange(ValueChangeEvent event) {
		Property property = event.getProperty();
		if (property == notTakenSubjects) {
			selectedTableItem1 = notTakenDatasource.getItem(notTakenSubjects
					.getValue());
			if (selectedTableItem1 != null)
				subjectID = notTakenSubjects.getValue().toString();
		}
		if (property == currentSubjects) {
			selectedTableItem2 = registeredDatasource.getItem(currentSubjects
					.getValue());
			if (selectedTableItem2 != null)
				subjectIDselected1 = currentSubjects.getValue().toString();
		}

	}

}
