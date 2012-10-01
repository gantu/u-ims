package kg.cloud.uims.ui;

import com.vaadin.data.Property;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ChameleonTheme;
import java.util.ArrayList;
import kg.cloud.uims.MyVaadinApplication;
import kg.cloud.uims.dao.DbYear;
import kg.cloud.uims.domain.Year;
import kg.cloud.uims.i18n.UimsMessages;
import kg.cloud.uims.resources.DataContainers;
import kg.cloud.uims.util.ExportReporttoPDF;

public class SuccessReportStudView extends VerticalLayout implements Button.ClickListener,
        Property.ValueChangeListener {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private VerticalLayout header, body;
    private Table reportTable = new Table();
    private Button toPDF = new Button();
    private DataContainers dc;
    private IndexedContainer reportDatasource;
    private MyVaadinApplication app;
    private String studentId = null;
    private String studentFullName = null;
    private String sem_id, prev_year_id, curr_year_id, year_id, curr_year_name,
            prev_year_name;
    private ArrayList<Year> prevYearDetails;
    private Select yearSelect = new Select();
    private Select semSelect = new Select();

    public SuccessReportStudView(MyVaadinApplication app) {
        this.app = app;
        dc = new DataContainers(app);

        this.studentId = Integer.toString(app.getStudent().getID());
        this.studentFullName = app.getStudent().getName() + " "
                + app.getStudent().getSurname();

        this.curr_year_name = app.getCurrentYear().getYear();
        this.sem_id = Integer.toString(app.getCurrentSemester().getId());
        this.curr_year_id = Integer.toString(app.getCurrentYear().getId());
        this.prev_year_id = Integer.toString(app.getCurrentYear().getId() - 1);
        this.year_id = curr_year_id;

        setSpacing(true);
        buildHeader();
        buildBody();


    }

    public void buildHeader() {
        header = new VerticalLayout();
        Label headerLabel = new Label("<h1>"
                + app.getMessage(UimsMessages.SuccessReportHeader) + "</h1>");
        headerLabel.setContentMode(Label.CONTENT_XHTML);
        header.setMargin(false, false, false, true);
        header.addComponent(headerLabel);
        addComponent(header);
    }

    public void buildBody() {

        try {

            DbYear dbYear = new DbYear();
            dbYear.connect();
            dbYear.execSQL(prev_year_id);
            prevYearDetails = dbYear.getArray();
            dbYear.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.prev_year_name = prevYearDetails.get(0).getYear();

        reportTable.setWidth("100%");
        reportTable.setHeight("60%");
        reportTable.setCaption(app.getMessage(UimsMessages.SuccessReportHeader));
        reportTable.setStyleName(ChameleonTheme.TABLE_STRIPED);

        ThemeResource iconPDF = new ThemeResource(
                "../runo/icons/16/document-pdf.png");
        toPDF.setCaption(app.getMessage(UimsMessages.ButtonMakePDF));
        toPDF.setIcon(iconPDF);
        toPDF.addListener((Button.ClickListener) this);
        toPDF.setStyleName("default");

        body = new VerticalLayout();
        GridLayout infoLayout = new GridLayout(3, 1);
        Label studInfo = new Label(app.getMessage(UimsMessages.LabelStudent)
                + " : " + studentFullName);
        HorizontalLayout yearLay = new HorizontalLayout();
        yearLay.setSpacing(true);

        yearSelect.setWidth("3cm");
        yearSelect.setNullSelectionAllowed(false);
        yearSelect.addItem(curr_year_name);
        yearSelect.addItem(prev_year_name);
        yearSelect.setValue(curr_year_name);
        yearSelect.setImmediate(true);
        yearSelect.addListener(this);
        Label yearInfo = new Label(app.getMessage(UimsMessages.LabelYear));
        yearLay.addComponent(yearInfo);
        yearLay.addComponent(yearSelect);

        HorizontalLayout semLay = new HorizontalLayout();
        semLay.setSpacing(true);

        semSelect.setWidth("3cm");
        yearSelect.setNullSelectionAllowed(false);
        semSelect.addItem("Fall");
        semSelect.addItem("Spring");
        if (sem_id.equals("1")) {
            semSelect.setValue("Fall");
        } else {
            semSelect.setValue("Spring");
        }
        semSelect.setImmediate(true);
        semSelect.addListener(this);
        Label semInfo = new Label(app.getMessage(UimsMessages.LabelSemester));
        semLay.addComponent(semInfo);
        semLay.addComponent(semSelect);

        body.setSpacing(true);
        infoLayout.setSizeFull();
        infoLayout.setSpacing(true);
        infoLayout.addComponent(studInfo, 0, 0);
        infoLayout.addComponent(yearLay, 1, 0);
        infoLayout.addComponent(semLay, 2, 0);
        body.addComponent(infoLayout);
        body.addComponent(reportTable);
        body.addComponent(toPDF);

        reportDatasource = dc.getStudentSuccessReport(studentId, sem_id,
                year_id);
        reportTable.setContainerDataSource(reportDatasource);

        addComponent(body);
    }

    public void buttonClick(Button.ClickEvent event) {
        final Button source = event.getButton();
        if (source == toPDF) {
            new ExportReporttoPDF(app, studentId, studentFullName,
                    reportDatasource, yearSelect.getValue().toString(),
                    semSelect.getValue().toString());
        }
    }

    public void valueChange(Property.ValueChangeEvent event) {

        Property property = event.getProperty();

        if (property == yearSelect) {

            if (null == event.getProperty().getValue()) {
            } else {
                if (yearSelect.getValue().equals(curr_year_name)) {
                    year_id = curr_year_id;
                    reportDatasource = dc.getStudentSuccessReport(studentId,
                            sem_id, year_id);
                    reportTable.setContainerDataSource(reportDatasource);
                } else {
                    year_id = prev_year_id;
                    reportDatasource = dc.getStudentSuccessReport(studentId,
                            sem_id, year_id);
                    reportTable.setContainerDataSource(reportDatasource);
                }

            }
        }
        if (property == semSelect) {

            if (null == event.getProperty().getValue()) {
            } else {
                if (semSelect.getValue().equals("Spring")) {
                    sem_id = "2";
                    reportDatasource = dc.getStudentSuccessReport(studentId,
                            sem_id, year_id);
                    reportTable.setContainerDataSource(reportDatasource);
                } else {
                    sem_id = "1";
                    reportDatasource = dc.getStudentSuccessReport(studentId,
                            sem_id, year_id);
                    reportTable.setContainerDataSource(reportDatasource);
                }
            }
        }
    }
}