package kg.cloud.uims.ui;

import com.vaadin.data.Item;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ChameleonTheme;
import java.text.NumberFormat;
import java.text.ParseException;
import kg.cloud.uims.MyVaadinApplication;
import kg.cloud.uims.i18n.UimsMessages;
import kg.cloud.uims.resources.DataContainers;
import kg.cloud.uims.util.ExportTranscriptToPDF;

public class TranscriptStudView extends VerticalLayout implements Button.ClickListener {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private double total;
    private double creditSum;
    private Table transcriptTable = new Table();
    private Button toPDF = new Button();
    private IndexedContainer transcriptDatasource;
    private MyVaadinApplication app;
    private String studentId = null;
    private String studentFullName = null;
    private VerticalLayout header, body;

    public TranscriptStudView(MyVaadinApplication app) {

        this.app = app;
        this.studentId = Integer.toString(app.getStudent().getID());
        this.studentFullName = app.getStudent().getName() + " "
                + app.getStudent().getSurname();

        setSpacing(true);
        buildHeader();
        buildBody();
    }

    public void buildBody() {

        transcriptTable.setWidth("100%");
        transcriptTable.setHeight("60%");
        transcriptTable.setFooterVisible(true);
        transcriptTable.setCaption(app.getMessage(UimsMessages.TranscriptHeader));
        transcriptTable.setStyleName(ChameleonTheme.TABLE_STRIPED);

        ThemeResource iconPDF = new ThemeResource("../runo/icons/16/document-pdf.png");
        toPDF.setCaption(app.getMessage(UimsMessages.ButtonMakePDF));
        toPDF.setIcon(iconPDF);
        toPDF.addListener((Button.ClickListener) this);
        toPDF.setStyleName("default");

        body = new VerticalLayout();
        Label studInfo = new Label(app.getMessage(UimsMessages.LabelStudent) + " : " + studentFullName);
        body.setSpacing(true);
        body.addComponent(studInfo);
        body.addComponent(transcriptTable);
        body.addComponent(toPDF);

        DataContainers dc = new DataContainers(app);
        transcriptDatasource = dc.getStudentTranscript(studentId);
        transcriptTable.setContainerDataSource(transcriptDatasource);

        for (int i = 0; i < transcriptDatasource.size(); i++) {
            Item item = transcriptDatasource.getItem(transcriptDatasource.getIdByIndex(i));
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
        addComponent(body);
        transcriptTable.setColumnFooter(
                app.getMessage(UimsMessages.SubjectCredit),
                app.getMessage(UimsMessages.SubjectAverage));
        transcriptTable.setColumnFooter(
                app.getMessage(UimsMessages.SubjectAverage),
                Double.toString(Math.round(total / creditSum)));
        addComponent(body);
    }

    public void buildHeader() {
        header = new VerticalLayout();
        Label headerLabel = new Label("<h1>"
                + app.getMessage(UimsMessages.TranscriptHeader) + "</h1>");
        headerLabel.setContentMode(Label.CONTENT_XHTML);
        header.setMargin(false, false, false, true);
        header.addComponent(headerLabel);
        addComponent(header);
    }

    public void buttonClick(Button.ClickEvent event) {
        final Button source = event.getButton();
        if (source == toPDF) {

            new ExportTranscriptToPDF(app, studentId, studentFullName, total,
                    creditSum, transcriptDatasource);
        }
    }
}