package kg.cloud.uims.ui;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import kg.cloud.uims.MyVaadinApplication;
import kg.cloud.uims.dao.DbSubjExam;
import kg.cloud.uims.i18n.UimsMessages;
import kg.cloud.uims.resources.DataContainers;
import kg.cloud.uims.util.MakeAverageList;
import kg.cloud.uims.util.MakeExamMarksReport;
import kg.cloud.uims.util.MakeResultList;
import kg.cloud.uims.util.MakeSignatureList;

import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Button.ClickEvent;
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

public class ExamWindow extends Window implements Button.ClickListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Table studentTable = new Table();

	Button signList = new Button();
	Button save = new Button();
	Button resultList = new Button();
	Button midMarks = new Button();
	Button finMarks = new Button();
	Button makeUpMarks = new Button();
	Button avReport = new Button();
	IndexedContainer studentDatasource;
	MyVaadinApplication app;

	private Subject currentUser = SecurityUtils.getSubject();
	private String subject_id = null;
	private String subjectName = null;
	private String curr_Sem_id, curr_Year_id, curr_Exam_id;
	private int status = 0;
	private String errNotif = "";

	public ExamWindow(MyVaadinApplication app, String subj_Id, String subjName) {
		// super(app.getMessage(UimsMessages.RegistrationHeader+" : "+studentFullName));
		this.setModal(true);
		this.setCaption(app.getMessage(UimsMessages.ExamHeader) + ": "
				+ subjName);
		this.setWidth("80%");
		this.setHeight("95%");
		this.app = app;
		this.subject_id = subj_Id;
		this.subjectName = subjName;
		this.curr_Sem_id = Integer.toString(app.getCurrentSemester().getId());
		this.curr_Year_id = Integer.toString(app.getCurrentYear().getId());
		this.curr_Exam_id = Integer.toString(app.getCurrentExam().getID());

		studentTable.setWidth("100%");
		studentTable.setHeight("60%");
		studentTable.setImmediate(true);
		studentTable.setCaption(app.getMessage(UimsMessages.ListOfStudents));
		studentTable.addStyleName(ChameleonTheme.TABLE_STRIPED);
		studentTable.setFooterVisible(true);
		studentTable.setSelectable(false);

		ThemeResource iconPDF = new ThemeResource("../runo/icons/16/document-pdf.png");
		ThemeResource iconOK = new ThemeResource("../runo/icons/16/ok.png");
		signList.setCaption(app.getMessage(UimsMessages.ButtonSignatureList));
		signList.setIcon(iconPDF);
		signList.addListener((Button.ClickListener) this);
		save.setCaption(app.getMessage(UimsMessages.SaveButton));
		save.setIcon(iconOK);
		save.addListener((Button.ClickListener) this);
		resultList.setCaption(app.getMessage(UimsMessages.ButtonResultList));
		resultList.setIcon(iconPDF);
		resultList.addListener((Button.ClickListener) this);
		midMarks.setCaption(app.getMessage(UimsMessages.StudentMidterm));
		midMarks.setIcon(iconPDF);
		midMarks.addListener((Button.ClickListener) this);
		finMarks.setCaption(app.getMessage(UimsMessages.StudentFinal));
		finMarks.setIcon(iconPDF);
		finMarks.addListener((Button.ClickListener) this);
		makeUpMarks.setCaption(app.getMessage(UimsMessages.StudentMakeup));
		makeUpMarks.setIcon(iconPDF);
		makeUpMarks.addListener((Button.ClickListener) this);
		avReport.setCaption(app.getMessage(UimsMessages.ButtonAverageReport));
		avReport.setIcon(iconPDF);
		avReport.addListener((Button.ClickListener) this);
		
		if(currentUser.hasRole("secretary")){
			save.setEnabled(false);
		}

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
		buttonLayout.addComponent(signList);
		buttonLayout.addComponent(resultList);
		if (curr_Exam_id.equals("1")) {
			finMarks.setEnabled(false);
			makeUpMarks.setEnabled(false);
		}
		if (curr_Exam_id.equals("2")) {
			makeUpMarks.setEnabled(false);
		}
		buttonLayout.addComponent(midMarks);
		buttonLayout.addComponent(finMarks);
		buttonLayout.addComponent(makeUpMarks);
		buttonLayout.addComponent(avReport);
		buttonLayout.addComponent(save);
		mainLayout.addComponent(infoLayout);
		mainLayout.addComponent(studentTable);
		mainLayout.addComponent(buttonLayout);

		DataContainers dc = new DataContainers(app);
		studentDatasource = dc.getExamSubjectStudList(subject_id, curr_Year_id,
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

		if (source == signList) {
			new MakeSignatureList(app, subject_id);
		}
		if (source == resultList) {
			new MakeResultList(app, subject_id);
		}
		if (source == midMarks) {
			new MakeExamMarksReport(app, subject_id, "1", "Midterm");
		}
		if (source == finMarks) {
			new MakeExamMarksReport(app, subject_id, "2", "Final");
		}
		if (source == makeUpMarks) {
			new MakeExamMarksReport(app, subject_id, "3", "MakeUp");
		}
		if (source == avReport) {
			new MakeAverageList(app, subject_id);
		}
		if (source == save) {
			for (int i = 0; i < studentDatasource.size(); i++) {
				Item item = studentDatasource.getItem(studentDatasource
						.getIdByIndex(i));
				String currentAttendance = item
						.getItemProperty(
								app.getMessage(UimsMessages.StudentMark))
						.getValue().toString();
				if (!currentAttendance.equals("")) {
					try {
						if (Integer.parseInt(currentAttendance) < 0) {
							errNotif = app
									.getMessage(UimsMessages.NotifNegative);
						}
						if (Integer.parseInt(currentAttendance) > 100) {
							errNotif = app
									.getMessage(UimsMessages.NotifBigger100);
						}
					} catch (Exception e) {
						errNotif = app.getMessage(UimsMessages.NotifWrongNum);
					}
				}

			}
			if (errNotif.equals("")) {
				try {

					DbSubjExam dbSubjExam = new DbSubjExam();
					dbSubjExam.connect();

					for (int i = 0; i < studentDatasource.size(); i++) {
						Item item = studentDatasource.getItem(studentDatasource
								.getIdByIndex(i));
						String stud_less_id = studentDatasource.getIdByIndex(i)
								.toString();
						String mark = item
								.getItemProperty(
										app.getMessage(UimsMessages.StudentMark))
								.getValue().toString();
						if (mark == null || mark.equals("")) {
							mark = "0";
						}

						status = dbSubjExam.insertSQL(stud_less_id,
								curr_Exam_id, mark);
					}
					dbSubjExam.close();

				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (status != 0) {
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