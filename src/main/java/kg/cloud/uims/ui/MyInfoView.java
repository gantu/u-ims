package kg.cloud.uims.ui;

import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import kg.cloud.uims.MyVaadinApplication;
import kg.cloud.uims.i18n.UimsMessages;

public class MyInfoView extends VerticalLayout {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private MyVaadinApplication app;
    private VerticalLayout header, body;
    private String reg_status, acc_reg_status, acc_mid_status, acc_fin_status;

    public MyInfoView(MyVaadinApplication app) {
        this.app = app;
        setSpacing(true);
        buildHeader();
        buildBody();

    }

    public void buildHeader() {
        header = new VerticalLayout();
        Label headerLabel = new Label("<h1>"
                + app.getMessage(UimsMessages.TSBMyInfo) + "</h1>");
        headerLabel.setContentMode(Label.CONTENT_XHTML);
        header.addComponent(headerLabel);
        header.setMargin(false, false, false, true);
        addComponent(header);
    }

    public void buildBody() {
        body = new VerticalLayout();
        if (app.getStudent().getReg_stat() == 1) {
            reg_status = app.getMessage(UimsMessages.Registered);
        } else {
            reg_status = app.getMessage(UimsMessages.NotRegistered);
        }
        if (app.getStudent().getAcc_reg_stat() == 1) {
            acc_reg_status = app.getMessage(UimsMessages.Released);
        } else {
            acc_reg_status = app.getMessage(UimsMessages.Freezed);
        }
        if (app.getStudent().getAcc_mid_stat() == 1) {
            acc_mid_status = "OK";
        } else {
            acc_mid_status = "WBI";
        }
        if (app.getStudent().getAcc_fin_stat() == 1) {
            acc_fin_status = "OK";
        } else {
            acc_fin_status = "WBI";
        }

        Label eduinfo = new Label("<h3>" + app.getMessage(UimsMessages.EduStatusLabel)
                + "</h3><p>" + "<ul>"
                + "<li><b><u>" + app.getMessage(UimsMessages.FacultyCode)
                + ":</b></u> " + app.getStudent().getFaculty_name() + "</li>"
                + "<li><b><u>" + app.getMessage(UimsMessages.Department)
                + ":</b></u> " + app.getStudent().getDepartName() + "</li>"
                + "<li><b><u>" + app.getMessage(UimsMessages.GroupName)
                + ":</b></u> " + app.getStudent().getGroupName() + "</li>"
                + "<li><b><u>" + app.getMessage(UimsMessages.TBSupervisor)
                + ":</b></u> " + app.getStudent().getSupervisorFullName() + "</li>"
                + "<li><b><u>" + app.getMessage(UimsMessages.EducationLabel)
                + ":</b></u> " + app.getStudent().getEducation() + "</li>"
                + "<li><b><u>" + app.getMessage(UimsMessages.RegistrationHeader)
                + ":</b></u> " + reg_status + "</li></ul>");
        eduinfo.setSizeFull();
        eduinfo.setContentMode(Label.CONTENT_XHTML);
        Label accinfo = new Label("<h3>" + app.getMessage(UimsMessages.AccountingStatuses)
                + "</h3><p>" + "<ul>"
                + "<li><b><u>" + app.getMessage(UimsMessages.RegistrationHeader)
                + ":</b></u> " + acc_reg_status + "</li>"
                + "<li><b><u>" + app.getMessage(UimsMessages.StudentMidterm)
                + ":</b></u> " + acc_mid_status + "</li>"
                + "<li><b><u>" + app.getMessage(UimsMessages.StudentFinal)
                + ":</b></u> " + acc_fin_status + "</li></ul>");
        accinfo.setSizeFull();
        accinfo.setContentMode(Label.CONTENT_XHTML);
        body.addComponent(eduinfo);
        body.addComponent(accinfo);
        addComponent(body);
    }
}
