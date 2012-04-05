package kg.cloud.uims.ui;

import java.util.ArrayList;

import kg.cloud.uims.MyVaadinApplication;
import kg.cloud.uims.dao.DbYear;
import kg.cloud.uims.domain.Year;
import kg.cloud.uims.i18n.UimsMessages;
import kg.cloud.uims.resources.DataContainers;
import kg.cloud.uims.util.ExportReporttoPDF;

import com.vaadin.data.util.IndexedContainer;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ChameleonTheme;

public class SuccessReportWindow extends Window implements ClickListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Table reportTable = new Table();

	Button toPDF = new Button();
	IndexedContainer reportDatasource;
	MyVaadinApplication app;

	private String studentId = null;
	private String studentFullName = null;
	private String sem_id, year_id, year_name, sem_name;
	private ArrayList<Year> prevYearDetails;

	public SuccessReportWindow(MyVaadinApplication app, String studentId,
			String studentFullName) {
		// super(app.getMessage(UimsMessages.RegistrationHeader+" : "+studentFullName));
		this.setModal(true);
		this.setCaption(app.getMessage(UimsMessages.SuccessReportHeader) + " "
				+ studentFullName);
		this.setWidth("80%");
		this.setHeight("100%");
		this.app = app;
		this.studentId = studentId;
		this.studentFullName = studentFullName;

		this.year_name=app.getCurrentYear().getYear();
		this.sem_id = Integer.toString(app.getCurrentSemester().getId());
		this.year_id = Integer.toString(app.getCurrentYear().getId());
		if ("2".equals(sem_id)) {
			sem_id = "1";
			sem_name = "fall";
		} else {
			sem_id = "2";
			sem_name="spring";
			year_id = Integer.toString(Integer.parseInt(year_id) - 1);
			
			try {

				DbYear dbYear = new DbYear();
				dbYear.connect();
				dbYear.execSQL(year_id);
				prevYearDetails = dbYear.getArray();
				dbYear.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			year_name=prevYearDetails.get(0).getYear();
		}

		reportTable.setWidth("100%");
		reportTable.setHeight("60%");
		reportTable
				.setCaption(app.getMessage(UimsMessages.SuccessReportHeader));
		reportTable.setStyleName(ChameleonTheme.TABLE_STRIPED);

		ThemeResource iconPDF = new ThemeResource("../runo/icons/16/document-pdf.png");
		toPDF.setCaption(app.getMessage(UimsMessages.ButtonMakePDF));
		toPDF.setIcon(iconPDF);
		toPDF.addListener((Button.ClickListener) this);

		VerticalLayout mainLayout = new VerticalLayout();
		GridLayout infoLayout = new GridLayout(3, 1);
		Label studInfo = new Label(app.getMessage(UimsMessages.LabelStudent)
				+ " : " + studentFullName);
		Label yearInfo = new Label(app.getMessage(UimsMessages.LabelYear)
				+ " : " + year_name);
		Label semInfo = new Label(app.getMessage(UimsMessages.LabelSemester)
				+ " : " + sem_name);
		mainLayout.setSpacing(true);
		infoLayout.setSizeFull();
		infoLayout.setSpacing(true);
		infoLayout.addComponent(studInfo, 0, 0);
		infoLayout.addComponent(yearInfo, 1, 0);
		infoLayout.addComponent(semInfo, 2, 0);
		mainLayout.addComponent(infoLayout);
		mainLayout.addComponent(reportTable);
		mainLayout.addComponent(toPDF);

		DataContainers dc = new DataContainers(app);
		reportDatasource = dc.getStudentSuccessReport(studentId, sem_id,
				year_id);
		reportTable.setContainerDataSource(reportDatasource);

		addComponent(mainLayout);

	}

	public void buttonClick(ClickEvent event) {
		final Button source = event.getButton();
		if (source == toPDF) {
			new ExportReporttoPDF(app, studentId, studentFullName,
					reportDatasource, year_name, sem_name);
		}
	}

}