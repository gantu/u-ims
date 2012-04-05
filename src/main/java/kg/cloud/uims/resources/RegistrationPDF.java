package kg.cloud.uims.resources;

import java.awt.Color;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import kg.cloud.uims.dao.DbInstructor;
import kg.cloud.uims.dao.DbStudLess;
import kg.cloud.uims.domain.Instructor;
import kg.cloud.uims.domain.StudLess;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.vaadin.terminal.StreamResource.StreamSource;

public class RegistrationPDF implements StreamSource {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final ByteArrayOutputStream os = new ByteArrayOutputStream();
	private DbStudLess studLess;
	private DbInstructor dbInst;
	private DbInstructor dbHod;
	private Instructor instructor;
	private Instructor hod;
	private Subject currentUser = SecurityUtils.getSubject();

	public RegistrationPDF(String studentId, String subjectID, String semID,
			String yearID) {
		Document document = null;
		int totalHr = 0;
		int totalCr = 0;
		Image img;

		try {
			studLess = new DbStudLess();
			dbInst = new DbInstructor();
			dbHod=new DbInstructor();
			SimpleDateFormat sdf = new SimpleDateFormat("dd.MMMMMMMMMMM yyyy");
			document = new Document(PageSize.A4, 10, 10, 10, 10);
			PdfWriter writer = PdfWriter.getInstance(document, os);

			document.open();
			PdfContentByte punder = writer.getDirectContentUnder();
			img = Image.getInstance("/usr/local/images/iaauLogoT.png");
			img.setAbsolutePosition(document.getPageSize().getWidth() / 4,
					document.getPageSize().getHeight() / 3);
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
					"INTERNATIONAL ATATURK ALATOO UNIVERSITY", title_font);
			iaau.setAlignment(Element.ALIGN_CENTER);
			Paragraph sif = new Paragraph("SUBJECT REGISTRATION FORM", big_font);
			sif.setAlignment(Element.ALIGN_CENTER);
			document.add(iaau);
			document.add(sif);
			document.add(new Paragraph(15, " "));

			studLess.connect();
			studLess.execSQL(studentId, semID, yearID);
			ArrayList<StudLess> list = studLess.getArray();
			

			dbInst.connect();
			instructor = dbInst.execSQLRN(currentUser
					.getPrincipal().toString());
			
			
			if (!list.isEmpty()) {
				dbHod.connect();
				hod = dbHod.execSQLRD("hod", list.get(0).getStudDepID());
							
				float[] Thead_colsWidth = { 1.2f, 1.5f, 1.3f, 1.5f };
				PdfPTable Thead = new PdfPTable(4);
				Thead.setWidthPercentage(90f);
				Thead.setWidths(Thead_colsWidth);
				Thead.getDefaultCell().setBorder(0);
				Thead.addCell(new Phrase("Academic Year:", in_font));
				Thead.addCell(new Phrase(list.get(0).getStudYear(), text_font));
				Thead.addCell(new Phrase("Name:", in_font));
				Thead.addCell(new Phrase(list.get(0).getStudName(), text_font));
				Thead.addCell(new Phrase("Semester:", in_font));
				Thead.addCell(new Phrase(list.get(0).getSemester(), text_font));
				Thead.addCell(new Phrase("Surname:", in_font));
				Thead.addCell(new Phrase(list.get(0).getStudSurname(),
						text_font));
				Thead.addCell(new Phrase("Faculty:", in_font));
				Thead.addCell(new Phrase(list.get(0).getFacultyName(),
						text_font));
				Thead.addCell(new Phrase("Student`s Group:", in_font));
				Thead.addCell(new Phrase(list.get(0).getGrpName(), text_font));
				Thead.addCell(new Phrase("Department:", in_font));
				Thead.addCell(new Phrase(list.get(0).getStudDepartment(),
						text_font));
				Thead.addCell(new Phrase("Date:", in_font));
				Thead.addCell(new Phrase(sdf.format(new java.util.Date()),
						text_font));
				document.add(Thead);
				document.add(new Paragraph(15, " "));

				float[] Tbody_colsWidth = { 0.2f, 0.6f, 2f, 0.5f, 0.6f, 0.6f,
						0.5f, 0.7f };
				PdfPTable Tbody = new PdfPTable(8);
				Tbody.setWidthPercentage(90f);
				Tbody.setWidths(Tbody_colsWidth);
				Tbody.getDefaultCell().setFixedHeight(16);
				Tbody.getDefaultCell().setHorizontalAlignment(
						Element.ALIGN_CENTER);
				Tbody.addCell(new Phrase("#", in_font));
				Tbody.addCell(new Phrase("Code", in_font));
				Tbody.addCell(new Phrase("Subject Name", in_font));
				Tbody.addCell(new Phrase("Hour", in_font));
				Tbody.addCell(new Phrase("Credit", in_font));
				Tbody.addCell(new Phrase("Status", in_font));
				Tbody.addCell(new Phrase("Year", in_font));
				Tbody.addCell(new Phrase("Semester", in_font));

				for (int i = 0; i < list.size(); i++) {
					Tbody.addCell(new Phrase(Integer.toString(i + 1), text_font));
					Tbody.addCell(new Phrase(list.get(i).getSubjCode(),
							text_font));
					Tbody.getDefaultCell().setHorizontalAlignment(
							Element.ALIGN_LEFT);
					Tbody.addCell(new Phrase(list.get(i).getSubjName(),
							text_font));
					Tbody.getDefaultCell().setHorizontalAlignment(
							Element.ALIGN_CENTER);
					Tbody.addCell(new Phrase(list.get(i).getSubHour(),
							text_font));
					Tbody.addCell(new Phrase(list.get(i).getSubCredit(),
							text_font));
					Tbody.addCell(new Phrase(Integer.toString(list.get(i)
							.getStatus()), text_font));
					Tbody.addCell(new Phrase(list.get(i).getEdYear(), text_font));
					Tbody.addCell(new Phrase(list.get(i).getSemester(),
							text_font));

					totalHr += Integer.parseInt(list.get(i).getSubHour());
					totalCr += Integer.parseInt(list.get(i).getSubCredit());
				}

				Tbody.addCell(new Phrase(" "));
				Tbody.addCell(new Phrase(" "));
				Tbody.addCell(new Phrase(" "));
				Tbody.addCell(new Phrase(Integer.toString(totalHr), text_font));
				Tbody.addCell(new Phrase(Integer.toString(totalCr), text_font));
				Tbody.addCell(new Phrase(" "));
				Tbody.addCell(new Phrase(" "));
				Tbody.addCell(new Phrase(" "));
				document.add(Tbody);
				document.add(new Paragraph(15, " "));

				float[] Tfoot_colsWidth = { 1f, 1.5f, 1.5f };
				PdfPTable Tfoot = new PdfPTable(3);
				Tfoot.setWidthPercentage(90f);
				Tfoot.setWidths(Tfoot_colsWidth);
				Tfoot.getDefaultCell().setHorizontalAlignment(
						Element.ALIGN_LEFT);
				Tfoot.addCell(new Phrase(" "));
				Tfoot.addCell(new Phrase("Name Surname", in_font));
				Tfoot.addCell(new Phrase("Signature", in_font));
				Tfoot.addCell(new Phrase("Student", in_font));
				Tfoot.addCell(new Phrase(list.get(0).getStudName() + " "
						+ list.get(0).getStudSurname(), text_font));
				Tfoot.addCell(new Phrase(" "));
				Tfoot.addCell(new Phrase("Supervisor", in_font));
				Tfoot.addCell(new Phrase(instructor.getInstructorName() + " "
						+ instructor.getInstructorSurname(), text_font));
				Tfoot.addCell(new Phrase(" "));
				Tfoot.addCell(new Phrase("HOD", in_font));
				Tfoot.addCell(new Phrase(hod.getInstructorName() +" " + hod.getInstructorSurname(), text_font));
				Tfoot.addCell(new Phrase(" "));
				document.add(Tfoot);
				dbHod.close();
			} else {
				document.add(new Phrase("No records found", warning));
			}
			studLess.close();
			dbInst.close();
			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (document != null) {
				document.close();
			}

		}

	}

	public InputStream getStream() {
		// TODO Auto-generated method stub
		return new ByteArrayInputStream(os.toByteArray());
	}

}
