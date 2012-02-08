package kg.cloud.uims.ui;

import kg.cloud.uims.MyVaadinApplication;
import kg.cloud.uims.i18n.UimsMessages;
import kg.cloud.uims.resources.DataContainers;
import kg.cloud.uims.util.ExportReporttoPDF;
import kg.cloud.uims.util.ExportTranscriptToPDF;

import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.themes.ChameleonTheme;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.data.Item;

import java.text.NumberFormat;
import java.text.ParseException;


public class SuccessReportWindow extends Window implements ClickListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Table reportTable = new Table();

	Button toPDF = new Button();
	IndexedContainer reportDatasource;
	MyVaadinApplication app;
	
	private String studentId=null;
    private String studentFullName=null; 
    private String curr_Sem_id, curr_Year_id;
    
    

	public SuccessReportWindow(MyVaadinApplication app, String studentId,
			String studentFullName) {
		// super(app.getMessage(UimsMessages.RegistrationHeader+" : "+studentFullName));
		this.setModal(true);
		this.setCaption(app.getMessage(UimsMessages.SuccessReportHeader) + " " + studentFullName);
		this.setWidth("80%");
		this.setHeight("100%");
		this.app = app;
		this.studentId=studentId;
		this.studentFullName=studentFullName;
		this.curr_Sem_id=Integer.toString(app.getCurrentSemester().getId());
		this.curr_Year_id=Integer.toString(app.getCurrentYear().getId());
				
		reportTable.setWidth("100%");
		reportTable.setHeight("60%");
		reportTable.setCaption(app.getMessage(UimsMessages.SuccessReportHeader));
		reportTable.setStyleName(ChameleonTheme.TABLE_STRIPED);

		toPDF.setCaption(app.getMessage(UimsMessages.ButtonMakePDF));
		toPDF.addListener((Button.ClickListener) this);

		VerticalLayout mainLayout = new VerticalLayout();
		GridLayout infoLayout = new GridLayout(3,1);
		Label studInfo = new Label(app.getMessage(UimsMessages.LabelStudent) + " : " + studentFullName);
		Label yearInfo = new Label(app.getMessage(UimsMessages.LabelYear) + " : " + app.getCurrentYear().getYear());
		Label semInfo = new Label(app.getMessage(UimsMessages.LabelSemester) + " : " + app.getCurrentSemester().getSemester());
		mainLayout.setSpacing(true);
		infoLayout.setSizeFull();
		infoLayout.setSpacing(true);
		infoLayout.addComponent(studInfo,0,0);
		infoLayout.addComponent(yearInfo,1,0);
		infoLayout.addComponent(semInfo,2,0);
		mainLayout.addComponent(infoLayout);
		mainLayout.addComponent(reportTable);
		mainLayout.addComponent(toPDF);

		DataContainers dc = new DataContainers(app);
		reportDatasource = dc.getStudentSuccessReport(studentId, curr_Sem_id, curr_Year_id);
		reportTable.setContainerDataSource(reportDatasource);

		
		addComponent(mainLayout);

	}

	public void buttonClick(ClickEvent event) {
		final Button source = event.getButton();
		if (source == toPDF) {

			new ExportReporttoPDF(app, studentId, studentFullName, reportDatasource);
		}
	}
		
}