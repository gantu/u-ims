
package kg.cloud.uims.util;

import kg.cloud.uims.MyVaadinApplication;
import kg.cloud.uims.dao.DbStudent;
import kg.cloud.uims.domain.Student;
import kg.cloud.uims.i18n.UimsMessages;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.PdfContentByte;
import com.vaadin.terminal.StreamResource;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.ui.Embedded;
import com.vaadin.data.Item;

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


public class ExportReporttoPDF{
	
	private ByteArrayOutputStream reportBuffer = null;
    private byte[] b=null;
    private StreamResource.StreamSource source1=null;
    ByteArrayOutputStream buffer=null;
    StreamResource resource=null;
    private Image img;
    SimpleDateFormat df = new SimpleDateFormat("dd/MM/yy");
    
    private String studentId=null;
    private String studentFullName=null;
	private ArrayList<Student> studDetails;
    private IndexedContainer reportDatasource;
    private MyVaadinApplication app;
    
    
    
	public ExportReporttoPDF(final MyVaadinApplication app, String sid,
			String stud_fname, IndexedContainer repDatasource){
		this.app=app;
		this.studentId=sid;
		this.studentFullName=stud_fname;
		this.reportDatasource=repDatasource;
		
		source1 = new StreamResource.StreamSource() {

            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			Document document = new Document(PageSize.A4, 10, 10, 10, 10);

            public InputStream getStream() {

                buffer = new ByteArrayOutputStream();
                try {
                	
                	try{
                		
                		DbStudent dbStud = new DbStudent();
                		dbStud.connect();
                		dbStud.execSQL(studentId);
                		studDetails=dbStud.getArray();
                		dbStud.close();
                		}catch (Exception e) {
                			e.printStackTrace();
                		}
                	
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
                    Font warning = new Font(Font.COURIER, 12, Font.BOLD);
                    warning.setColor(new Color(0xFF, 0x00, 0x00));
                    Font in_font = new Font(Font.COURIER, 12, Font.BOLD);
                    Font text_font = new Font(Font.TIMES_ROMAN, 11, Font.NORMAL);
                    Paragraph iaau = new Paragraph("INTERNATIONAL ATATURK ALATOO UNIVERSITY", title_font);
                    iaau.setAlignment(Element.ALIGN_CENTER);
                    Paragraph sif = new Paragraph("STUDENT`S SUCCESS CONTROL FORM", big_font);
                    sif.setAlignment(Element.ALIGN_CENTER);
                    document.add(iaau);
                    document.add(sif);
                    document.add(new Paragraph(10, " "));

                    if (reportDatasource!=null) {
                        float[] Thead_colsWidth = {1.2f, 1.5f, 0.8f, 1.5f};
                        PdfPTable Thead = new PdfPTable(4);
                        Thead.setWidthPercentage(90f);
                        Thead.setWidths(Thead_colsWidth);
                        Thead.getDefaultCell().setBorder(0);
                        Thead.addCell(new Phrase("Group:", in_font));
                        Thead.addCell(new Phrase(studDetails.get(0).getGroupName(), text_font));
                        Thead.addCell(new Phrase("Student:", in_font));
                        Thead.addCell(new Phrase(studentFullName, text_font));
                        Thead.addCell(new Phrase("Academic Year:", in_font));
                        Thead.addCell(new Phrase(app.getCurrentYear().getYear(), text_font));
                        Thead.addCell(new Phrase("Semester:", in_font));
                        Thead.addCell(new Phrase(app.getCurrentSemester().getSemester(), text_font));
                        document.add(Thead);
                        document.add(new Paragraph(10, " "));

                        float[] Tbody_colsWidth = {0.1f, 1f, 0.3f, 0.4f, 0.3f, 0.4f, 0.4f, 0.5f};
                        PdfPTable Tbody = new PdfPTable(8);
                        Tbody.setWidthPercentage(90f);
                        Tbody.setWidths(Tbody_colsWidth);
                        Tbody.getDefaultCell().setFixedHeight(16);
                        Tbody.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
                        Tbody.addCell(new Phrase("#", in_font));
                        Tbody.addCell(new Phrase("Subjects", in_font));
                        Tbody.addCell(new Phrase("Hours", in_font));
                        Tbody.addCell(new Phrase("Midterm", in_font));
                        Tbody.addCell(new Phrase("Final", in_font));
                        Tbody.addCell(new Phrase("Make Up", in_font));
                        Tbody.addCell(new Phrase("Average", in_font));
                        Tbody.addCell(new Phrase("Attendance", in_font));

                        for (int i = 0; i < reportDatasource.size(); i++) {
                        	Item item = reportDatasource.getItem(reportDatasource
                					.getIdByIndex(i));
                            
                            Tbody.addCell(new Phrase(Integer.toString(i + 1), text_font));
                            Tbody.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
                            Tbody.addCell(new Phrase(item.getItemProperty(app.getMessage(UimsMessages.SubjectName)).toString(), text_font));
                            Tbody.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
                            Tbody.addCell(new Phrase(item.getItemProperty(app.getMessage(UimsMessages.SubjectHour)).toString(), text_font));
                            Tbody.addCell(new Phrase(item.getItemProperty(app.getMessage(UimsMessages.StudentMidterm)).toString(), text_font));
                            Tbody.addCell(new Phrase(item.getItemProperty(app.getMessage(UimsMessages.StudentFinal)).toString(), text_font));
                            Tbody.addCell(new Phrase(item.getItemProperty(app.getMessage(UimsMessages.StudentMakeup)).toString(), text_font));
                            Tbody.addCell(new Phrase(item.getItemProperty(app.getMessage(UimsMessages.SubjectAverage)).toString(), text_font));
                            Tbody.addCell(new Phrase(item.getItemProperty(app.getMessage(UimsMessages.StudentAttandance)).toString(), text_font));

                        }
                        document.add(Tbody);
                        document.add(new Paragraph(10, " "));


                        PdfPTable Tfoot = new PdfPTable(1);
                        Tfoot.getDefaultCell().setBorder(0);
                        Tfoot.setWidthPercentage(90f);
                        Tfoot.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
                        Tfoot.addCell(new Phrase("ALEX", in_font));
                        document.add(Tfoot);

                    } else {
                        document.add(new Phrase("no records found"));
                    }

                    document.close();

                } catch (DocumentException ex) {
                    //Logger.getLogger(ReportEngine.class.getName()).log(Level.SEVERE, null, ex);
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

        //Embedded e = new Embedded();
        //e.setSizeFull();
        //e.setType(Embedded.TYPE_BROWSER);
        
        resource = new StreamResource(source1, "TokenReport.pdf", app);
        resource.setMIMEType("application/pdf");

         
        
        //e.setSource(resource);
        //e.setParameter("Content-Disposition", "attachment; filename=" + resource.getFilename());
       

        //app.addComponent(e);
        app.getMainWindow().open(resource);
	}
	
	
}