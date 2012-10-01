package kg.cloud.uims.ui;

import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ChameleonTheme;
import kg.cloud.uims.MyVaadinApplication;
import kg.cloud.uims.i18n.UimsMessages;
import kg.cloud.uims.resources.DataContainers;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

public class SuccessReportView extends VerticalLayout implements
        Property.ValueChangeListener {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private MyVaadinApplication app;
    private VerticalLayout header, body;
    private Table groupStudentTable = new Table();
    private Subject currentUser = SecurityUtils.getSubject();
    private String group_id;
    private Select groupSelect = new Select();
    private DataContainers container;

    public SuccessReportView(MyVaadinApplication app) {
        this.app = app;
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
        container = new DataContainers(app);
        body = new VerticalLayout();
        if (currentUser.hasRole("secretary")) {
            HorizontalLayout selectLay = new HorizontalLayout();
            selectLay.setSpacing(true);

            groupSelect.setCaption(app.getMessage(UimsMessages.Group));
            groupSelect.setWidth("2cm");
            groupSelect.setNullSelectionAllowed(false);
            groupSelect.setFilteringMode(AbstractSelect.Filtering.FILTERINGMODE_CONTAINS);
            groupSelect.setItemCaptionMode(Select.ITEM_CAPTION_MODE_PROPERTY);
            groupSelect.setItemCaptionPropertyId(app.getMessage(UimsMessages.GroupName));
            groupSelect.setImmediate(true);
            groupSelect.addListener(this);
            IndexedContainer groupContainer = container.getGroupList_forSelect(app.getFacultyId());
            groupSelect.setContainerDataSource(groupContainer);

            selectLay.addComponent(groupSelect);
            body.addComponent(selectLay);

        }
        body.addComponent(groupStudentTable);
        groupStudentTable.setWidth("100%");
        groupStudentTable.setSelectable(true);
        groupStudentTable.setImmediate(true);
        groupStudentTable.setStyleName(ChameleonTheme.TABLE_STRIPED);

        if (currentUser.hasRole("instructor")) {

            groupStudentTable.setContainerDataSource(
                    container.getStudentByGroupContainer(
                    Integer.toString(app.getGroupId())));
        }
        groupStudentTable.addListener((Property.ValueChangeListener) this);

        addComponent(body);
    }

    public void valueChange(ValueChangeEvent event) {
        Property property = event.getProperty();

        if (property == groupStudentTable) {
            if (null == event.getProperty().getValue()) {
            } else {
                String fullName = groupStudentTable.getItem(groupStudentTable.getValue()).getItemProperty(
                        app.getMessage(UimsMessages.RegistrationStudentName)).getValue()
                        + " "
                        + groupStudentTable.getItem(groupStudentTable.getValue()).getItemProperty(
                        app.getMessage(UimsMessages.RegistrationStudentSurname)).getValue();
                getWindow().addWindow(
                        new SuccessReportWindow(app, groupStudentTable.getValue().toString(), fullName));

            }
        }
        if (property == groupSelect) {

            if (null == event.getProperty().getValue()) {
            } else {
                group_id = event.getProperty().getValue().toString();

                groupStudentTable.setContainerDataSource(container.getStudentByGroupContainer(
                        group_id));

            }
        }
    }
}