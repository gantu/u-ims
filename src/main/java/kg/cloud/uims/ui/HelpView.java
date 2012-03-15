package kg.cloud.uims.ui;

import kg.cloud.uims.MyVaadinApplication;
import kg.cloud.uims.i18n.UimsMessages;

import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.VerticalLayout;

import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TabSheet.SelectedTabChangeEvent;
import com.vaadin.ui.TabSheet.Tab;
import com.vaadin.ui.themes.ChameleonTheme;

public class HelpView extends VerticalLayout implements
		TabSheet.SelectedTabChangeListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MyVaadinApplication app;
	private VerticalLayout header, body;

	private static final ThemeResource icon1 = new ThemeResource(
			"../runo/icons/16/note.png");
	private TabSheet t;

	public HelpView(MyVaadinApplication app) {
		this.app = app;
		setSpacing(true);
		buildHeader();
		buildBody();

	}

	public void buildHeader() {
		header = new VerticalLayout();
		Label headerLabel = new Label("<h1>"
				+ app.getMessage(UimsMessages.HelpHeader) + "</h1>");
		headerLabel.setContentMode(Label.CONTENT_XHTML);
		headerLabel.setSizeFull();
		header.addComponent(headerLabel);
		addComponent(header);
	}

	public void buildBody() {
		body = new VerticalLayout();
		// Tab 1 content
		VerticalLayout statLay = new VerticalLayout();
		statLay.setMargin(true);
		Label statusesLabel = new Label(
				"<h3>"
						+ "Academic Statuses"
						+ "</h3><p>"
						+ "<ul>"
						+ "<li>Undergraduate - Lisans eğitimine devam eden öğrenciler</li>"
						+ "<li>Master - Yüksek lisans</li>"
						+ "<li>Phd - Doktora</li>"
						+ "</ul>"
						
						+ "<h3>"
						+ "Education Statuses"
						+ "</h3><p>"
						+ "<ul>"
						+ "<li>Active - Son döneme ders kaydını yaptırmış olan öğrenci</li>"
						+ "<li>Graduate - Üniversitemizden mezun olan öğrenci</li>"
						+ "<li>Not Registered - Son dönemde ders kaydı yaptırmamış olan öğrenci</li>"
						+ "<li>Out of - Üniversitemizden herhangi bir sebepten ilişiği kesilmiş olan öğrenci</li>"
						+ "<li>Suspension - Eğitimini 1 veya 2 dönem dondurmuş olan öğrenci</li>"
						+ "</ul>"
						
						+ "<h3>"
						+ "Lesson Statuses"
						+ "</h3><p>"
						+ "<ul>"
						+ "<li>1 - İlk defa alınan ders, devamlılık mecburieti vardır</li>"
						+ "<li>2 - Devamsızlıktan kalınan ve devamlılık mecburieti olan ders statusudur</li>"
						+ "<li>3 - Sınavdan kalınan ve devamlılık mecburieti olan ders statusudur</li>"
						+ "</ul>"
						
						+ "<h3>"
						+ "Account Statuses"
						+ "</h3><p>"
						+ "<ul>"
						+ "<li>Rector - Rektörlük</li>"
						+ "<li>Student Affairs - Öğrenci İşleri Daire Daskanlığı</li>"
						+ "<li>Dean - Dekanlık</li>"
						+ "<li>Head of Department - Bölüm Başkanlığı</li>"
						+ "<li>Supervisor - Sınıf Danışmanları</li>"
						+ "<li>Instructor - Öğretim Görevlileri</li>"
						+ "<li>Sekretary - Dekan veya Bölüm Sekreterlikleri</li>"
						+ "<li>Student - Öğrenci</li>"
						+ "</ul>");
		statusesLabel.setSizeFull();
		statusesLabel.setContentMode(Label.CONTENT_XHTML);
		statLay.addComponent(statusesLabel);
		// Tab 2 content
		VerticalLayout deptLay = new VerticalLayout();
		deptLay.setMargin(true);
		Label depLabel = new Label(
				"<h3>"
						+ "Codes of Faculties"
						+ "</h3><p>"
						+ "<ul>"
						+ "<li>EAF - Economy and Administration</li>"
						+ "<li>HNF - Humanities and Natural Science</li>"
						+ "<li>NTF - New Technologies</li>"
						+ "<li>ISN - Institute of Social and Natural Sciences</li>"
						+ "</ul>"
						
						+ "<h3>"
						+ "Codes of Departments"
						+ "</h3><p>"
						+ "<ul>"
						+ "<li>ASU - American Studies</li>"
						+ "<li>CHN - Chinese Language</li>"
						+ "<li>COM - Computer Engineering</li>"
						+ "<li>DOK - Language Teaching Department</li>"
						+ "<li>ELL - English Language and Literature</li>"
						+ "</ul>"			
						);
		depLabel.setSizeFull();
		depLabel.setContentMode(Label.CONTENT_XHTML);
		deptLay.addComponent(depLabel);

		t = new TabSheet();
		t.setSizeFull();
		// t.addStyleName(ChameleonTheme.TABSHEET_BORDERLESS);
		t.addTab(statLay, "Statuses", icon1);
		t.addTab(deptLay, "Codes", icon1);
		t.addListener(this);

		body.addComponent(t);

		/*
		 * GridLayout helpContent = new GridLayout(2,2);
		 * helpContent.setSizeFull();
		 * 
		 * Label helpHeaderLabel = new Label("<h3>" + "STATUSES" + "</h3>");
		 * helpHeaderLabel.setSizeFull();
		 * helpHeaderLabel.setContentMode(Label.CONTENT_XHTML);
		 * 
		 * Label helpHeaderLabel2 = new Label("<h3>" +
		 * "FACULTIES AND DEPARTMENTS" + "</h3>");
		 * helpHeaderLabel2.setContentMode(Label.CONTENT_XHTML);
		 * helpHeaderLabel2.setSizeFull();
		 * 
		 * TextArea statusesTA = new TextArea(); statusesTA.
		 * 
		 * helpContent.addComponent(helpHeaderLabel,0,0);
		 * helpContent.addComponent(helpHeaderLabel2,1,0);
		 * helpContent.setComponentAlignment(helpHeaderLabel,
		 * Alignment.BOTTOM_CENTER);
		 * helpContent.setComponentAlignment(helpHeaderLabel2,
		 * Alignment.MIDDLE_CENTER); body.addComponent(helpContent);
		 */
		addComponent(body);
	}

	public void selectedTabChange(SelectedTabChangeEvent event) {

	}

}
