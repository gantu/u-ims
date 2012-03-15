package kg.cloud.uims.util;

import kg.cloud.uims.MyVaadinApplication;
import kg.cloud.uims.dao.DbExam;
import kg.cloud.uims.dao.DbInstructor;
import kg.cloud.uims.dao.DbStudLess;
import kg.cloud.uims.domain.Exam;
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

public class MakeAverageList {

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

	public MakeAverageList(final MyVaadinApplication app, String subj_id) {
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

					DbStudLess dbStudLess = new DbStudLess();
					dbStudLess.connect();
					dbStudLess.execSQL_Exam(subjectId, year_id, sem_id);
					studLessList = dbStudLess.getArray();

					DbExam dbExam = new DbExam();
					dbExam.connect();

					DbInstructor dbInstructor = new DbInstructor();
					dbInstructor.connect();
					dbInstructor.execSQL_byRole(currentUser.getPrincipal()
							.toString());
					inst = dbInstructor.getArray();
					dbInstructor.close();

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
					Paragraph sif = new Paragraph("EXAMINATION AVERAGE LIST",
							big_font);
					sif.setAlignment(Element.ALIGN_CENTER);
					document.add(iaau);
					document.add(sif);
					document.add(new Paragraph(10, " "));

					if (!studLessList.isEmpty()) {
						float[] Thead_colsWidth = { 1.2f, 1.5f, 0.8f, 1.5f };
						PdfPTable Thead = new PdfPTable(4);
						Thead.setWidthPercentage(90f);
						Thead.setWidths(Thead_colsWidth);
						Thead.getDefaultCell().setBorder(0);
						Thead.addCell(new Phrase("Department:", in_font));
						Thead.addCell(new Phrase(studLessList.get(0)
								.getStudDepartment(), text_font));
						Thead.addCell(new Phrase("Subject:", in_font));
						Thead.addCell(new Phrase(studLessList.get(0)
								.getSubjName(), text_font));
						Thead.addCell(new Phrase("Academic Year:", in_font));
						Thead.addCell(new Phrase(
								app.getCurrentYear().getYear(), text_font));
						Thead.addCell(new Phrase("Semester:", in_font));
						Thead.addCell(new Phrase(app.getCurrentSemester()
								.getSemester(), text_font));
						document.add(Thead);
						document.add(new Paragraph(10, " "));

						float[] Tbody_colsWidth = { 0.1f, 1f, 0.3f, 0.4f, 0.4f,
								0.4f, 0.4f, 0.4f };
						PdfPTable Tbody = new PdfPTable(8);
						Tbody.setWidthPercentage(90f);
						Tbody.setWidths(Tbody_colsWidth);
						Tbody.getDefaultCell().setFixedHeight(16);
						Tbody.getDefaultCell().setHorizontalAlignment(
								Element.ALIGN_CENTER);
						Tbody.addCell(new Phrase("#", in_font));
						Tbody.addCell(new Phrase("Name Surname", in_font));
						Tbody.addCell(new Phrase("Group", in_font));
						Tbody.addCell(new Phrase("Midterm", in_font));
						Tbody.addCell(new Phrase("Final", in_font));
						Tbody.addCell(new Phrase("Make Up", in_font));
						Tbody.addCell(new Phrase("Average", in_font));
						Tbody.addCell(new Phrase("Status", in_font));

						for (int i = 0; i < studLessList.size(); i++) {
							Double av = dbStudLess.calcAverage_Report(Integer
									.toString(studLessList.get(i).getStudID()),
									sem_id, year_id, Integer
											.parseInt(subjectId));
							dbExam.execStudentExamDetails(Integer
									.toString(studLessList.get(i).getStudID()),
									sem_id, year_id, Integer
											.parseInt(subjectId));
							ArrayList<Exam> examList = dbExam.getArray();
							double marks[] = new double[3];
							if (examList != null) {
								for (int j = 0; j < examList.size(); j++) {
									if (examList.get(j).getExam()
											.equals("Midterm")) {
										marks[0] = examList.get(j)
												.getPercentage();
									} else if (examList.get(j).getExam()
											.equals("Final")) {
										marks[1] = examList.get(j)
												.getPercentage();
									} else if (examList.get(j).getExam()
											.equals("MakeUp")) {
										marks[2] = examList.get(j)
												.getPercentage();
									}
								}
							}

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
							Tbody.addCell(new Phrase(Double.toString(marks[0]),
									text_font));
							Tbody.addCell(new Phrase(Double.toString(marks[1]),
									text_font));
							Tbody.addCell(new Phrase(Double.toString(marks[2]),
									text_font));
							Tbody.addCell(new Phrase(Double.toString(av),
									text_font));
							if (av < 49.5) {
								Tbody.addCell(new Phrase("Failed", text_font));
							} else if (av >= 49.5) {
								Tbody.addCell(new Phrase("Passed", text_font));
							}
						}
						document.add(Tbody);
						document.add(new Paragraph(10, " "));

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
						document.add(new Phrase("no records found"));
					}

					dbStudLess.close();
					dbExam.close();

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