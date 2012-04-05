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
		header.setMargin(false, false, false, true);
		header.addComponent(headerLabel);
		addComponent(header);
	}

	public void buildBody() {
		body = new VerticalLayout();
		// Tab 1 content
		GridLayout statLay = new GridLayout(2, 1);
		statLay.setMargin(true);
		statLay.setSizeFull();
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
						+ "</ul>");
		statusesLabel.setSizeFull();
		statusesLabel.setContentMode(Label.CONTENT_XHTML);
		statLay.addComponent(statusesLabel);

		Label statusesLabel2 = new Label("<h3>" + "Account Statuses"
				+ "</h3><p>" + "<ul>" + "<li>Rector - Rektörlük</li>"
				+ "<li>Student Affairs - Öğrenci İşleri Daire Daskanlığı</li>"
				+ "<li>Dean - Dekanlık</li>"
				+ "<li>Head of Department - Bölüm Başkanlığı</li>"
				+ "<li>Supervisor - Sınıf Danışmanları</li>"
				+ "<li>Instructor - Öğretim Görevlileri</li>"
				+ "<li>Sekretary - Dekan veya Bölüm Sekreterlikleri</li>"
				+ "<li>Student - Öğrenci</li>" + "</ul>");
		statusesLabel2.setSizeFull();
		statusesLabel2.setContentMode(Label.CONTENT_XHTML);
		statLay.addComponent(statusesLabel2, 1, 0);
		// Tab 2 content
		GridLayout deptLay = new GridLayout(2, 1);
		deptLay.setMargin(true);
		deptLay.setSizeFull();
		Label depLabel = new Label("<h3>" + "NTF - New Technologies"
				+ "</h3><p>" + "<ul>" + "<li>COM - Computer Engineering</li>"
				+ "<li>ELT - Industrial Electronics Engineering</li>"
				+ "<li>IDE - Industrial Engineering</li>"
				+ "<li>MAT - Applied Math&Informatics</li>" + "</ul>"

				+ "<h3>" + "ISN - Institute of Social & Natural Science"
				+ "</h3><p>" + "<ul>"+ " <li>PCO - Psychologic Consulting</li>"
				+ " <li>EST - Elementary School Teaching</li>" + "</ul>"

				 + "<h3>" + "Master and Phd"
				 + "</h3><p>" + "<ul>" +"<li>PIM - PhD of IT Management for Economic&S</li>" 
				 +"<li>PMC - PhD of Mathematical and Software Ap</li>" 
				 +"<li>MES - Master of English Studies</li>"           
				 +"<li>MCS - Master of Computer Science </li>"         
				 +"<li>MIR - Master of International Relations</li>"   
				 +"<li>MBA - Master of Business Administration</li>" + "</ul>");
		depLabel.setSizeFull();
		depLabel.setContentMode(Label.CONTENT_XHTML);
		deptLay.addComponent(depLabel, 0, 0);

		Label depLabel2 = new Label("<h3>" + "EAF - Economy and Administration" + "</h3><p>"
				+ "<ul>" + " <li>MAN - Management</li>"
				+ " <li>INT - International Relations</li>"
				+ " <li>FIN - Finance and Banking</li>"
				+ " <li>WEC - World Economy</li>" + "</ul>"

				+ "<h3>" + "HNF - Humanities and Natural Science" + "</h3><p>"
				+ "<ul>" + "<li>KGZ - Kyrgyz Language</li>"
				+ "<li>ASU - American Studies</li>"
				+ " <li>DOK - Language Teaching Department</li>"
				+ " <li>CHN - Chinese Language</li>"
				+ " <li>SYT - Synchronic Translation</li>"
				+ " <li>ELL - English Language Literature</li>"
				+ " <li>TUR - Turkish Language</li>" + "</ul>"
				);
		depLabel2.setSizeFull();
		depLabel2.setContentMode(Label.CONTENT_XHTML);
		deptLay.addComponent(depLabel2, 1, 0);

		t = new TabSheet();
		t.setSizeFull();
		// t.addStyleName(ChameleonTheme.TABSHEET_BORDERLESS);
		t.addTab(statLay, app.getMessage(UimsMessages.StatusesTab), icon1);
		t.addTab(deptLay, app.getMessage(UimsMessages.DepartmentsTab), icon1);
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
