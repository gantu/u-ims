package kg.cloud.uims.util;

import kg.cloud.uims.MyVaadinApplication;
import kg.cloud.uims.dao.DbStudAccounting;
import kg.cloud.uims.dao.DbStudLess;
import kg.cloud.uims.domain.StudAccounting;
import kg.cloud.uims.domain.StudLess;
import com.lowagie.text.Document;
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
import java.io.InputStream;
import com.lowagie.text.Image;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPTable;
import java.util.ArrayList;

public class MakeResultList {

	private byte[] b = null;
	private StreamResource.StreamSource source1 = null;
	ByteArrayOutputStream buffer = null;
	StreamResource resource = null;
	private Image img;

	private String subjectId = null;
	private ArrayList<StudLess> studLessList;
	private MyVaadinApplication app;
	private String year_id, sem_id;
	private Document document = null;

	public MakeResultList(final MyVaadinApplication app, String subj_id) {
		this.app = app;
		this.subjectId = subj_id;
		this.sem_id = Integer.toString(app.getCurrentSemester().getId());
		this.year_id = Integer.toString(app.getCurrentYear().getId());

		source1 = new StreamResource.StreamSource() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public InputStream getStream() {

				buffer = new ByteArrayOutputStream();

				try {

					DbStudLess dbStudLess = new DbStudLess();
					dbStudLess.connect();
					dbStudLess.execSQL_Exam(subjectId, year_id, sem_id);
					studLessList = dbStudLess.getArray();

					DbStudAccounting dbAccounting = new DbStudAccounting();
					dbAccounting.connect();
					document = new Document(PageSize.A4, 10, 10, 10, 10);
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
					Paragraph sif = new Paragraph("EXAMINATION RESULT LIST",
							big_font);
					sif.setAlignment(Element.ALIGN_CENTER);
					document.add(iaau);
					document.add(sif);
					document.add(new Paragraph(10, " "));

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
						Thead.addCell(new Phrase("Examination: "
								+ app.getCurrentExam().getExam(), in_font));
						Thead.addCell(new Phrase("Date: ", in_font));
						document.add(Thead);
						document.add(new Paragraph(10, " "));

						float[] Tbody_colsWidth = { 0.14f, 1f, 0.5f, 0.4f, 0.5f };
						PdfPTable Tbody = new PdfPTable(5);
						Tbody.setWidthPercentage(90f);
						Tbody.setWidths(Tbody_colsWidth);
						Tbody.getDefaultCell().setFixedHeight(16);
						Tbody.addCell(new Phrase("#", in_font));
						Tbody.addCell(new Phrase("Name Surname", in_font));
						Tbody.addCell(new Phrase("Accounting", in_font));
						Tbody.addCell(new Phrase("Group", in_font));
						Tbody.addCell(new Phrase("Mark", in_font));

						int count = 0;
						for (int i = 0; i < studLessList.size(); i++) {
							String stud_id = Integer.toString(studLessList.get(
									i).getStudID());
							dbAccounting.execSQL(stud_id, sem_id, year_id);
							ArrayList<StudAccounting> account = dbAccounting
									.getArray();
							if ((app.getCurrentExam().getID() == 1)
									|| (app.getCurrentExam().getID() == 2)) {
								count++;
								Tbody.addCell(new Phrase(Integer
										.toString(count), text_font));
								if (account.size()==0
										|| ((account.get(0).getFinStatus() != 1 && app.getCurrentExam().getID() == 2))
										|| ((account.get(0).getMidStatus() != 1 && app.getCurrentExam().getID() == 1))) {
									Tbody.addCell(new Phrase("*"
											+ studLessList.get(i).getStudName()
											+ " "
											+ studLessList.get(i)
													.getStudSurname(),
											text_font));
									Tbody.addCell(new Phrase("WBI",text_font));
								} else {
									Tbody.addCell(new Phrase(studLessList
											.get(i).getStudName()
											+ " "
											+ studLessList.get(i)
													.getStudSurname(),
											text_font));
									Tbody.addCell(new Phrase("OK",text_font));
								}
								Tbody.addCell(new Phrase(studLessList.get(i)
										.getGrpName(), text_font));
								Tbody.addCell(" ");
							} else {
								if ((dbStudLess.calcAverage_Report(Integer
										.toString(studLessList.get(i)
												.getStudID()), sem_id, year_id,
										Integer.parseInt(subjectId))) < 49.5) {
									count++;
									Tbody.addCell(new Phrase(Integer
											.toString(count), text_font));
									if (account.size()==0
											|| account.get(0).getFinStatus() != 1) {
										Tbody.addCell(new Phrase("*"
												+ studLessList.get(i)
														.getStudName()
												+ " "
												+ studLessList.get(i)
														.getStudSurname(),
												text_font));
										Tbody.addCell(new Phrase("WBI",text_font));
									} else {
										Tbody.addCell(new Phrase(studLessList
												.get(i).getStudName()
												+ " "
												+ studLessList.get(i)
														.getStudSurname(),
												text_font));
										Tbody.addCell(new Phrase("OK",text_font));
									}
									Tbody.addCell(new Phrase(studLessList
											.get(i).getGrpName(), text_font));
									Tbody.addCell(" ");
								}
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
								+ app.getName() + " "
								+ app.getSurname(), in_font));
						Tfoot.addCell(new Phrase("Signature :", in_font));
						document.add(Tfoot);
					} else {
						document.add(new Phrase("No records found", warning));
					}

					dbStudLess.close();
					dbAccounting.close();

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