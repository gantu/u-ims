package kg.cloud.uims.ui;

import kg.cloud.uims.MyVaadinApplication;
import kg.cloud.uims.i18n.UimsMessages;
import kg.cloud.uims.resources.DataContainers;
import kg.cloud.uims.util.ExportTranscriptToPDF;

import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.themes.ChameleonTheme;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.data.Item;

import java.text.NumberFormat;
import java.text.ParseException;


public class TranscriptWindow extends Window implements ClickListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double total;
	private double creditSum;
	Table transcriptTable = new Table();

	Button toPDF = new Button();
	IndexedContainer transcriptDatasource;
	MyVaadinApplication app;
	
	private String studentId=null;
    private String studentFullName=null; 
    

	public TranscriptWindow(MyVaadinApplication app, String studentId,
			String studentFullName) {
		// super(app.getMessage(UimsMessages.RegistrationHeader+" : "+studentFullName));
		this.setModal(true);
		this.setCaption(app.getMessage(UimsMessages.TranscriptHeader) + " " + studentFullName);
		this.setWidth("80%");
		this.setHeight("100%");
		this.app = app;
		this.studentId=studentId;
		this.studentFullName=studentFullName;

		transcriptTable.setWidth("100%");
		transcriptTable.setHeight("60%");
		transcriptTable.setFooterVisible(true);
		transcriptTable.setCaption(app.getMessage(UimsMessages.TranscriptHeader));
		transcriptTable.setStyleName(ChameleonTheme.TABLE_STRIPED);

		ThemeResource iconPDF = new ThemeResource("../runo/icons/16/document-pdf.png");
		toPDF.setCaption(app.getMessage(UimsMessages.ButtonMakePDF));
		toPDF.setIcon(iconPDF);
		toPDF.addListener((Button.ClickListener) this);

		VerticalLayout mainLayout = new VerticalLayout();
		Label studInfo = new Label(app.getMessage(UimsMessages.LabelStudent) + " : " + studentFullName);
		mainLayout.setSpacing(true);
		mainLayout.addComponent(studInfo);
		mainLayout.addComponent(transcriptTable);
		mainLayout.addComponent(toPDF);

		DataContainers dc = new DataContainers(app);
		transcriptDatasource = dc.getStudentTranscript(studentId);
		transcriptTable.setContainerDataSource(transcriptDatasource);

		for (int i = 0; i < transcriptDatasource.size(); i++) {
			Item item = transcriptDatasource.getItem(transcriptDatasource
					.getIdByIndex(i));
			Object value = item.getItemProperty(
					app.getMessage(UimsMessages.SubjectCredit)).getValue();
			Object avrValue = item.getItemProperty(
					app.getMessage(UimsMessages.SubjectAverage)).getValue();

			Number amount;
			Number amount2;
			try {

				if (!avrValue.toString().equals("IP")) {
					amount = NumberFormat.getNumberInstance().parse(
							value.toString());
					creditSum += amount.intValue();
					amount2 = NumberFormat.getNumberInstance().parse(
							avrValue.toString());
					total += (amount.intValue()) * (amount2.intValue());
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		transcriptTable.setColumnFooter(
				app.getMessage(UimsMessages.SubjectName),
				app.getMessage(UimsMessages.SubjectCreditSum));
		transcriptTable.setColumnFooter(app.getMessage(UimsMessages.Semester),
				Double.toString(Math.round(creditSum)));
		addComponent(mainLayout);
		transcriptTable.setColumnFooter(
				app.getMessage(UimsMessages.SubjectCredit),
				app.getMessage(UimsMessages.SubjectAverage));
		transcriptTable.setColumnFooter(
				app.getMessage(UimsMessages.SubjectAverage),
				Double.toString(Math.round(total / creditSum)));
		addComponent(mainLayout);

	}

	public void buttonClick(ClickEvent event) {
		final Button source = event.getButton();
		if (source == toPDF) {

			new ExportTranscriptToPDF(app, studentId, studentFullName, total, 
					creditSum, transcriptDatasource);
		}
	}
		
}