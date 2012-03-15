package kg.cloud.uims.util;

import kg.cloud.uims.MyVaadinApplication;
import kg.cloud.uims.dao.DbInstructor;
import kg.cloud.uims.dao.DbStudLess;
import kg.cloud.uims.dao.DbStudent_Attendance;
import kg.cloud.uims.domain.Instructor;
import kg.cloud.uims.domain.StudLess;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.PdfContentByte;
import com.vaadin.terminal.StreamResource;
import java.awt.Color;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;

import com.lowagie.text.Image;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPTable;
import java.util.ArrayList;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

public class MakeAttendanceReport {

	private ByteArrayOutputStream reportBuffer = null;
	private byte[] b = null;
	private StreamResource.StreamSource source1 = null;
	ByteArrayOutputStream buffer = null;
	StreamResource resource = null;
	private Image img;
	SimpleDateFormat df = new SimpleDateFormat("dd/MM/yy");

	private String subjectId = null;
	private String subjectName = null;
	private ArrayList<StudLess> studLessList;
	private MyVaadinApplication app;
	private String curr_Sem_id, curr_Year_id;
	private Subject currentUser = SecurityUtils.getSubject();

	public MakeAttendanceReport(final MyVaadinApplication app, String subj_id,
			String subj_name) {
		this.app = app;
		this.subjectId = subj_id;
		this.subjectName = subj_name;
		this.curr_Sem_id = Integer.toString(app.getCurrentSemester().getId());
		this.curr_Year_id = Integer.toString(app.getCurrentYear().getId());

		source1 = new StreamResource.StreamSource() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			Document document = new Document(PageSize.A4, 10, 10, 10, 10);

			public InputStream getStream() {

				buffer = new ByteArrayOutputStream();

				try {

					DbStudLess dbStudLess = new DbStudLess();
					dbStudLess.connect();
					dbStudLess.execSQL_Subject(subjectId, curr_Year_id,
							curr_Sem_id);
					studLessList = dbStudLess.getArray();
					dbStudLess.close();

					DbInstructor dbInstructor = new DbInstructor();
					dbInstructor.connect();
					dbInstructor.execSQL_byRole(currentUser.getPrincipal()
							.toString());
					ArrayList<Instructor> inst = dbInstructor.getArray();
					dbInstructor.close();

					DbStudent_Attendance dbAttandance = new DbStudent_Attendance();
					dbAttandance.connect();

					PdfWriter writer = PdfWriter.getInstance(document, buffer);

					document.open();

					PdfContentByte punder = writer.getDirectContentUnder();
					img = Image.getInstance("/usr/local/images/iaauLogoT.png");
					img.setAbsolutePosition(
							document.getPageSize().getWidth() / 4, document
									.getPageSize().getHeight() / 3);
					img.scaleAbsolute(300, 300);

					punder.addImage(img);

					Font big_font = new Font(Font.COURIER, 19, Font.BOLD);
					big_font.setColor(new Color(0x92, 0x90, 0x83));
					Font title_font = new Font(Font.COURIER, 13, Font.BOLD);
					title_font.setColor(new Color(0x92, 0x90, 0x83));
					Font warning = new Font(Font.COURIER, 10, Font.BOLD);
					warning.setColor(new Color(0xFF, 0x00, 0x00));
					Font in_font = new Font(Font.COURIER, 12, Font.BOLD);
					Font text_font = new Font(Font.TIMES_ROMAN, 10, Font.NORMAL);

					Paragraph iaau = new Paragraph(
							"INTERNATIONAL ATATURK ALATOO UNIVERSITY",
							title_font);
					iaau.setAlignment(Element.ALIGN_CENTER);
					Paragraph sif = new Paragraph("ATTENDANCE REPORT", big_font);
					sif.setAlignment(Element.ALIGN_CENTER);
					document.add(iaau);
					document.add(sif);
					document.add(new Paragraph(15, " "));

					if (studLessList.size() != 0) {
						float[] Thead_colsWidth = { 1.2f, 1.5f, 0.8f, 1.5f };
						PdfPTable Thead = new PdfPTable(4);
						Thead.setWidthPercentage(90f);
						Thead.setWidths(Thead_colsWidth);
						Thead.getDefaultCell().setBorder(0);
						Thead.addCell(new Phrase("Department:", in_font));
						Thead.addCell(new Phrase(studLessList.get(0)
								.getStudDepartment(), text_font));
						Thead.addCell(new Phrase("Subject:", in_font));
						Thead.addCell(new Phrase(subjectName, text_font));
						Thead.addCell(new Phrase("Academic Year:", in_font));
						Thead.addCell(new Phrase(
								app.getCurrentYear().getYear(), text_font));
						Thead.addCell(new Phrase("Semester:", in_font));
						Thead.addCell(new Phrase(app.getCurrentSemester()
								.getSemester(), text_font));
						Thead.addCell(new Phrase("Current week:", in_font));
						Thead.addCell(new Phrase(
								app.getCurrentWeek().getWeek(), text_font));
						Thead.addCell(new Phrase(""));
						Thead.addCell(new Phrase(""));
						document.add(Thead);
						document.add(new Paragraph(15, " "));

						float[] Tbody_colsWidth = { 0.2f, 1.5f, 0.5f, 0.5f,
								0.5f, 0.5f };
						PdfPTable Tbody = new PdfPTable(6);
						Tbody.setWidthPercentage(90f);
						Tbody.setWidths(Tbody_colsWidth);
						Tbody.getDefaultCell().setFixedHeight(16);
						Tbody.getDefaultCell().setHorizontalAlignment(
								Element.ALIGN_CENTER);
						Tbody.addCell(new Phrase("#", in_font));
						Tbody.addCell(new Phrase("Name Surname", in_font));
						Tbody.addCell(new Phrase("Group", in_font));
						Tbody.addCell(new Phrase("Absents", in_font));
						Tbody.addCell(new Phrase("Left", in_font));
						Tbody.addCell(new Phrase("Status", in_font));

						int hours = Integer.parseInt(studLessList.get(0)
								.getSubHour());
						double patt = Math.round(hours * 17 * 0.18);

						for (int i = 0; i < studLessList.size(); i++) {
							String student_id = Integer.toString(studLessList
									.get(i).getStudID());
							int att = dbAttandance.execSQL_All(student_id,
									curr_Sem_id, curr_Year_id,
									Integer.parseInt(subjectId));

							Tbody.addCell(new Phrase(Integer.toString(i + 1),
									text_font));
							Tbody.getDefaultCell().setHorizontalAlignment(
									Element.ALIGN_LEFT);
							Tbody.addCell(new Phrase(studLessList.get(i)
									.getStudName()
									+ " "
									+ studLessList.get(i).getStudSurname(),
									text_font));
							Tbody.getDefaultCell().setHorizontalAlignment(
									Element.ALIGN_CENTER);
							Tbody.addCell(new Phrase(studLessList.get(i)
									.getGrpName(), text_font));
							Tbody.addCell(new Phrase(Integer.toString(att),
									text_font));
							Tbody.addCell(new Phrase(Double
									.toString((patt - att)), text_font));
							if (att > (hours * 17 * 0.18)) {
								Tbody.addCell(new Phrase("F2", text_font));
							} else {
								Tbody.addCell(new Phrase("OK", text_font));
							}
						}

						document.add(Tbody);
						document.add(new Paragraph(15, " "));

						float[] Tfoot_colsWidth = { 1.5f, 1f };
						PdfPTable Tfoot = new PdfPTable(2);
						Tfoot.setWidthPercentage(90f);
						Tfoot.setWidths(Tfoot_colsWidth);
						Tfoot.getDefaultCell().setHorizontalAlignment(
								Element.ALIGN_LEFT);
						Tfoot.addCell(new Phrase("Name Surname : "
								+ inst.get(0).getInstructorName() + " "
								+ inst.get(0).getInstructorSurname(), in_font));
						Tfoot.addCell(new Phrase("Signature :", in_font));
						document.add(Tfoot);

					} else {
						document.add(new Phrase("no records found", warning));
					}
					dbAttandance.close();

				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (document != null) {
						document.close();
					}
				}

				b = buffer.toByteArray();
				return new ByteArrayInputStream(b);

			}
		};

		// Embedded e = new Embedded();
		// e.setSizeFull();
		// e.setType(Embedded.TYPE_BROWSER);

		resource = new StreamResource(source1, "TokenReport.pdf"
				+ System.currentTimeMillis(), app);
		resource.setMIMEType("application/pdf");

		// e.setSource(resource);
		// e.setParameter("Content-Disposition", "attachment; filename=" +
		// resource.getFilename());

		// app.addComponent(e);
		app.getMainWindow().open(resource);
	}

}