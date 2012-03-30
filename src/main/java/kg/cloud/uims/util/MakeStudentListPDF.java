package kg.cloud.uims.util;

import kg.cloud.uims.MyVaadinApplication;
import kg.cloud.uims.dao.DbStudent;
import kg.cloud.uims.domain.Student;

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

public class MakeStudentListPDF {

	private ByteArrayOutputStream reportBuffer = null;
	private byte[] b = null;
	private StreamResource.StreamSource source1 = null;
	ByteArrayOutputStream buffer = null;
	StreamResource resource = null;
	private Image img;

	private String group_id = null;
	private ArrayList<Student> studList;
	private MyVaadinApplication app;

	public MakeStudentListPDF(final MyVaadinApplication app, String gr_id) {
		this.app = app;
		this.group_id = gr_id;

		source1 = new StreamResource.StreamSource() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			Document document = new Document(PageSize.A4, 10, 10, 10, 10);

			public InputStream getStream() {

				buffer = new ByteArrayOutputStream();
				try {

					DbStudent dbStudent = new DbStudent();
					dbStudent.connect();
					dbStudent.execSQL_GR_Active(group_id);
					studList = dbStudent.getArray();
					dbStudent.close();

					PdfWriter writer = PdfWriter.getInstance(document, buffer);

					document.open();

		            PdfContentByte punder = writer.getDirectContentUnder();
		            img = Image.getInstance("/usr/local/images/iaauLogoT.png");
		            img.setAbsolutePosition(document.getPageSize().getWidth() / 4, document.getPageSize().getHeight() / 3);
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

		            Paragraph iaau = new Paragraph("INTERNATIONAL ATATURK ALATOO UNIVERSITY", title_font);
		            iaau.setAlignment(Element.ALIGN_CENTER);
		            Paragraph sif = new Paragraph("STUDENT LIST", big_font);
		            sif.setAlignment(Element.ALIGN_CENTER);
		            document.add(iaau);
		            document.add(sif);
		            document.add(new Paragraph(15, " "));

		            if (!studList.isEmpty()) {
		                float[] Thead_colsWidth = {0.8f, 1.5f, 0.5f, 1.5f};
		                PdfPTable Thead = new PdfPTable(4);
		                Thead.setWidthPercentage(90f);
		                Thead.setWidths(Thead_colsWidth);
		                Thead.getDefaultCell().setBorder(0);
		                Thead.addCell(new Phrase("Department:", in_font));
		                Thead.addCell(new Phrase(studList.get(0).getDepartName(), text_font));
		                Thead.addCell(new Phrase("Group:", in_font));
		                Thead.addCell(new Phrase(studList.get(0).getGroupName(), text_font));
		                document.add(Thead);
		                document.add(new Paragraph(15, " "));

		                float[] Tbody_colsWidth = {0.2f, 1.5f, 0.5f, 0.5f, 0.5f, 0.5f, 0.5f};
		                PdfPTable Tbody = new PdfPTable(7);
		                Tbody.setWidthPercentage(90f);
		                Tbody.setWidths(Tbody_colsWidth);
		                Tbody.getDefaultCell().setFixedHeight(16);
		                Tbody.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		                Tbody.addCell(new Phrase("#", in_font));
		                Tbody.addCell(new Phrase("Name Surname", in_font));
		                Tbody.addCell(new Phrase("status", in_font));
		                Tbody.addCell(new Phrase("1", in_font));
		                Tbody.addCell(new Phrase("2", in_font));
		                Tbody.addCell(new Phrase("3", in_font));
		                Tbody.addCell(new Phrase("4", in_font));

		                for (int i = 0; i < studList.size(); i++) {
		                    Tbody.addCell(new Phrase(Integer.toString(i + 1), text_font));
		                    Tbody.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		                    Tbody.addCell(new Phrase(studList.get(i).getName() + " " + studList.get(i).getSurname(), text_font));
		                    Tbody.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		                    Tbody.addCell(new Phrase(studList.get(i).getEducation(), text_font));
		                    Tbody.addCell(" ");
		                    Tbody.addCell(" ");
		                    Tbody.addCell(" ");
		                    Tbody.addCell(" ");
		                }
		                document.add(Tbody);
		                document.add(new Paragraph(15, " "));
		            } else {
		                document.add(new Phrase("No records found", warning));
		            }

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