package kg.cloud.uims.util;

import kg.cloud.uims.MyVaadinApplication;
import kg.cloud.uims.dao.DbInstructor;
import kg.cloud.uims.dao.DbStudLess;
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
import com.lowagie.text.Image;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPTable;
import java.util.ArrayList;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

public class MakeAttendanceForm {

	private ByteArrayOutputStream reportBuffer = null;
	private byte[] b = null;
	private StreamResource.StreamSource source1 = null;
	ByteArrayOutputStream buffer = null;
	StreamResource resource = null;
	private Image img;
	private Subject currentUser = SecurityUtils.getSubject();

	private String subjectId = null;
	private ArrayList<StudLess> studLessList;
	private ArrayList<Instructor> inst;
	private MyVaadinApplication app;
	private String year_id, sem_id;

	public MakeAttendanceForm(final MyVaadinApplication app, String subj_id) {
		this.app = app;
		this.subjectId = subj_id;
		this.sem_id = Integer.toString(app.getCurrentSemester().getId());
		this.year_id = Integer.toString(app.getCurrentYear().getId());

		source1 = new StreamResource.StreamSource() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			Document document = new Document(PageSize.A4, 10, 10, 10, 10);

			public InputStream getStream() {

				buffer = new ByteArrayOutputStream();
				try {

					try {

						DbStudLess dbStudLess = new DbStudLess();
						dbStudLess.connect();
						dbStudLess.execSQL_Subject(subjectId, year_id, sem_id);
						studLessList = dbStudLess.getArray();
						dbStudLess.close();
						DbInstructor dbInstructor = new DbInstructor();
						dbInstructor.connect();
						dbInstructor.execSQL_byRole(currentUser.getPrincipal().toString());
						inst = dbInstructor.getArray();
						dbStudLess.close();
					} catch (Exception e) {
						e.printStackTrace();
					}

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

					PdfPTable cells = new PdfPTable(10);
					cells.getDefaultCell().setFixedHeight(10);
					cells.getDefaultCell().setBorderWidthBottom(0);
					cells.getDefaultCell().setBorderWidthLeft(0);
					cells.getDefaultCell().setBorderWidthRight(1);
					cells.getDefaultCell().setBorderWidthTop(0);
					for (int c = 0; c < 9; c++) {
						cells.addCell(" ");
					}
					cells.getDefaultCell().setFixedHeight(10);
					cells.getDefaultCell().setBorderWidthBottom(0);
					cells.getDefaultCell().setBorderWidthLeft(0);
					cells.getDefaultCell().setBorderWidthRight(0);
					cells.getDefaultCell().setBorderWidthTop(0);
					cells.addCell(" ");

					Paragraph iaau = new Paragraph(
							"INTERNATIONAL ATATURK ALATOO UNIVERSITY",
							title_font);
					iaau.setAlignment(Element.ALIGN_CENTER);
					Paragraph sif = new Paragraph("ATTENDANCE FORM", big_font);
					sif.setAlignment(Element.ALIGN_CENTER);
					document.add(iaau);
					document.add(sif);
					document.add(new Paragraph(15, " "));

					if (studLessList.size() != 0) {

						float[] Thead_colsWidth = { 1f, 1f };
						PdfPTable Thead = new PdfPTable(2);
						Thead.setWidthPercentage(90f);
						Thead.setWidths(Thead_colsWidth);
						Thead.getDefaultCell().setBorder(0);
						Thead.addCell(new Phrase("Department: "
								+ studLessList.get(0).getStudDepartment(),
								in_font));
						Thead.addCell(new Phrase("Subject: "
								+ studLessList.get(0).getSubjName(), in_font));
						Thead.addCell(new Phrase("Academic Year: "
								+ app.getCurrentYear().getYear(), in_font));
						Thead.addCell(new Phrase("Semester: "
								+ app.getCurrentSemester().getSemester(),
								in_font));
						document.add(Thead);
						document.add(new Paragraph(15, " "));

						float[] Tbody_colsWidth = { 0.2f, 1.5f, 0.5f, 2f };
						PdfPTable Tbody = new PdfPTable(4);
						Tbody.setWidthPercentage(90f);
						Tbody.setWidths(Tbody_colsWidth);
						Tbody.getDefaultCell().setFixedHeight(16);
						Tbody.addCell(new Phrase("#", in_font));
						Tbody.addCell(new Phrase("Name Surname", in_font));
						Tbody.addCell(new Phrase("Group", in_font));
						Tbody.addCell(cells);

						for (int i = 0; i < studLessList.size(); i++) {
							Tbody.addCell(new Phrase(Integer.toString(i + 1),
									text_font));
							Tbody.addCell(new Phrase(studLessList.get(i)
									.getStudName()
									+ " "
									+ studLessList.get(i).getStudSurname(),
									text_font));
							Tbody.addCell(new Phrase(studLessList.get(i).getGrpName(),
									text_font));
							Tbody.addCell(cells);
						}
						document.add(Tbody);
						document.add(new Paragraph(15, " "));

						float[] Tat_colsWidth = { 0.5f, 2f };
						PdfPTable Tat = new PdfPTable(2);
						Tat.setWidthPercentage(90f);
						Tat.getDefaultCell().setHorizontalAlignment(
								Element.ALIGN_CENTER);
						Tat.setWidths(Tat_colsWidth);
						Tat.getDefaultCell().setBorder(0);
						Tat.addCell(new Phrase("Attention: ", warning));
						Tat.addCell(new Phrase(
								"Absent students are marked as '-'(minus sign)",
								warning));
						Tat.addCell(new Phrase(" "));
						Tat.addCell(new Phrase(
								"Present students are marked as '/'(back slash)",
								warning));
						document.add(Tat);
						document.add(new Paragraph(15, " "));

						float[] Tfoot_colsWidth = { 1.5f, 1f };
						PdfPTable Tfoot = new PdfPTable(2);
						Tfoot.setWidthPercentage(90f);
						Tfoot.setWidths(Tfoot_colsWidth);
						Tfoot.getDefaultCell().setHorizontalAlignment(
								Element.ALIGN_LEFT);
						Tfoot.addCell(new Phrase("Name Surname :"
								+ inst.get(0).getInstructorName() + " "
								+ inst.get(0).getInstructorSurname(), in_font));
						Tfoot.addCell(new Phrase("Signature :", in_font));
						document.add(Tfoot);
					} else {
						document.add(new Phrase("No records found", warning));
					}

					document.close();

				} catch (DocumentException ex) {
					// Logger.getLogger(ReportEngine.class.getName()).log(Level.SEVERE,
					// null, ex);
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
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