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
        Label eduinfo = new Label("<h3>" + app.getMessage(UimsMessages.EduStatusLabel)
                + "</h3><p>" + "<ul>"
                + "<li>" + app.getMessage(UimsMessages.EducationLabel)
                + ": " + app.getStudent().getEducation() + "</li>"
                + "<li>" + app.getMessage(UimsMessages.RegistrationHeader)
                + ": " + app.getStudent().getReg_stat() + "</li></ul>");
        eduinfo.setSizeFull();
        eduinfo.setContentMode(Label.CONTENT_XHTML);
        Label accinfo = new Label("<h3>" + app.getMessage(UimsMessages.AccountingStatuses)
                + "</h3><p>" + "<ul>"
                + "<li>" + app.getMessage(UimsMessages.RegistrationHeader)
                + ": " + app.getStudent().getAcc_reg_stat() + "</li>"
                + "<li>" + app.getMessage(UimsMessages.StudentMidterm)
                + ": " + app.getStudent().getAcc_mid_stat() + "</li>"
                + "<li>" + app.getMessage(UimsMessages.StudentFinal)
                + ": " + app.getStudent().getAcc_fin_stat() + "</li></ul>");
        accinfo.setSizeFull();
        accinfo.setContentMode(Label.CONTENT_XHTML);
        body.addComponent(eduinfo);
        body.addComponent(accinfo);
        addComponent(body);
    }
}
