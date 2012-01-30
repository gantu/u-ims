package kg.cloud.uims.util;

import kg.cloud.uims.MyVaadinApplication;
import kg.cloud.uims.dao.DbStudent;
import kg.cloud.uims.dao.DbYear;
import kg.cloud.uims.domain.Student;
import kg.cloud.uims.domain.Year;
import kg.cloud.uims.i18n.UimsMessages;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPCell;
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


public class ExportTranscriptToPDF{
	
	private ByteArrayOutputStream reportBuffer = null;
    private byte[] b=null;
    private StreamResource.StreamSource source1=null;
    ByteArrayOutputStream buffer=null;
    StreamResource resource=null;
    private Image img;
    SimpleDateFormat df = new SimpleDateFormat("dd/MM/yy");
    
    private String studentId=null;
    private String studentFullName=null;
	private double total;
	private double creditSum;
    private ArrayList<Year> yearCount;
    private ArrayList<Student> studDetails;
    private IndexedContainer transcriptDatasource;
    private MyVaadinApplication app;
    
    
    
	public ExportTranscriptToPDF(final MyVaadinApplication app, String sid,
			String stud_fname, Double t, Double c_sum, IndexedContainer trspDatasource){
		this.app=app;
		this.studentId=sid;
		this.studentFullName=stud_fname;
		this.total=t;
		this.creditSum=c_sum;
		this.transcriptDatasource=trspDatasource;
		
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
                		DbYear dbYear = new DbYear();
                		dbYear.connect();
                		dbYear.year_count(studentId);
                		yearCount = dbYear.getArray();
                		dbYear.close();
                		DbStudent dbStud = new DbStudent();
                		dbStud.connect();
                		dbStud.execFaculty(studentId);
                		studDetails=dbStud.getArray();
                		dbStud.close();
                		}catch (Exception e) {
                			e.printStackTrace();
                		}
                	
                	PdfWriter writer = PdfWriter.getInstance(document, buffer);
                	
                    document.open();
                    
                    PdfContentByte punder = writer.getDirectContentUnder();
                    img = Image.getInstance("/usr/local/images/notV.png");
                    img.setAbsolutePosition(document.getPageSize().getWidth() / 5, document.getPageSize().getHeight() / 4);
                    img.scaleAbsolute(400, 400);

                    punder.addImage(img);
                    
                    Font title_font = new Font(Font.COURIER, 10, Font.BOLD);
                    title_font.setColor(new Color(0x92, 0x90, 0x83));
                    Font text_font = new Font(Font.TIMES_ROMAN, 8, Font.NORMAL);

                    Paragraph h = new Paragraph(studDetails.get(0).getFaculty_name() +" Faculty - Department of " + studDetails.get(0).getDepartName(), title_font);
                    h.setAlignment(Element.ALIGN_CENTER);
                    Paragraph st = new Paragraph("Transcript of " + studentFullName + "\t Date:" + df.format(new java.util.Date()), title_font);
                    st.setAlignment(Element.ALIGN_CENTER);
                    document.add(h);
                    document.add(st);
                    document.add(new Paragraph(5, " "));
                    
                    float[] table_colsWidth = {2f, 2f};
                    PdfPTable table = new PdfPTable(2);
                    table.setWidthPercentage(100f);
                    table.setWidths(table_colsWidth);
                    
                    
                    
                    for (int i = 0; i < yearCount.size(); i++) {

                        float[] head_table_colsWidth = {2f, 3f, 0.5f, 0.5f};
                        PdfPTable head_table1 = new PdfPTable(4);
                        head_table1.setWidths(head_table_colsWidth);
                        head_table1.getDefaultCell().setBorder(0);

                        PdfPTable head_table2 = new PdfPTable(4);
                        head_table2.setWidths(head_table_colsWidth);
                        head_table2.getDefaultCell().setBorder(0);

                        head_table1.addCell(new Phrase("COURSE NAME", title_font));
                        head_table1.addCell(new Phrase(yearCount.get(i).getYear() + " FALL", title_font));
                        head_table1.addCell(new Phrase("C", title_font));
                        head_table1.addCell(new Phrase("G", title_font));

                        head_table2.addCell(new Phrase("COURSE NAME", title_font));
                        head_table2.addCell(new Phrase(yearCount.get(i).getYear() + " SPRING", title_font));
                        head_table2.addCell(new Phrase("C", title_font));
                        head_table2.addCell(new Phrase("G", title_font));

                        float[] colsWidth = {1f, 4f, 0.5f, 0.5f};
                        PdfPTable tableIn1 = new PdfPTable(4);
                        tableIn1.setWidths(colsWidth);
                        tableIn1.getDefaultCell().setBorder(0);
                        PdfPCell cell1 = new PdfPCell(head_table1);
                        cell1.setColspan(4);
                        tableIn1.addCell(cell1);

                        PdfPTable tableIn2 = new PdfPTable(4);
                        tableIn2.setWidths(colsWidth);
                        tableIn2.getDefaultCell().setBorder(0);
                        PdfPCell cell2 = new PdfPCell(head_table2);
                        cell2.setColspan(4);
                        tableIn2.addCell(cell2);

                        for (int j = 0; j < transcriptDatasource.size(); j++) {
                        	Item item = transcriptDatasource.getItem(transcriptDatasource
                					.getIdByIndex(j));
                            if (item.getItemProperty(app.getMessage(UimsMessages.StudyYear)).toString()
                            		.equals(yearCount.get(i).getYear()) 
                            		&& (item.getItemProperty(app.getMessage(UimsMessages.Semester)).toString().equals("fall"))) {
                                
                                tableIn1.addCell(new Phrase(item.getItemProperty(app.getMessage(UimsMessages.SubjectCode)).toString(), text_font));
                                tableIn1.addCell(new Phrase(item.getItemProperty(app.getMessage(UimsMessages.SubjectName)).toString(), text_font));
                                tableIn1.addCell(new Phrase(item.getItemProperty(app.getMessage(UimsMessages.SubjectCredit)).toString(), text_font));
                                tableIn1.addCell(new Phrase(item.getItemProperty(app.getMessage(UimsMessages.SubjectAverage)).toString(), text_font));
                            }
                            if (item.getItemProperty(app.getMessage(UimsMessages.StudyYear)).toString()
                            		.equals(yearCount.get(i).getYear()) 
                            		&& (item.getItemProperty(app.getMessage(UimsMessages.Semester)).toString().equals("spring"))) {
                                
                            	tableIn2.addCell(new Phrase(item.getItemProperty(app.getMessage(UimsMessages.SubjectCode)).toString(), text_font));
                                tableIn2.addCell(new Phrase(item.getItemProperty(app.getMessage(UimsMessages.SubjectName)).toString(), text_font));
                                tableIn2.addCell(new Phrase(item.getItemProperty(app.getMessage(UimsMessages.SubjectCredit)).toString(), text_font));
                                tableIn2.addCell(new Phrase(item.getItemProperty(app.getMessage(UimsMessages.SubjectAverage)).toString(), text_font));
                            }
                        } //for j
                        table.addCell(tableIn1);
                        table.addCell(tableIn2);
                    } // for i

                    document.add(table);

                    document.add(new Paragraph(5, " "));
                    float[] foot_colsWidth = {2f, 1f};
                    PdfPTable foot = new PdfPTable(2);
                    foot.setWidthPercentage(100f);
                    foot.setTotalWidth(foot_colsWidth);
                    foot.getDefaultCell().setBorder(0);
                    foot.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
                    foot.addCell(new Phrase("GRADUATE PROJECT TITLE: ", text_font));
                    foot.addCell(new Phrase("Grade Point Average: " + Math.round((total / creditSum)), text_font));
                    document.add(foot);

                    document.add(new Paragraph(5, " "));
                    float[] footer_colsWidth = {2f, 1f};
                    PdfPTable footer = new PdfPTable(2);
                    footer.setWidthPercentage(100f);
                    footer.setTotalWidth(footer_colsWidth);
                    footer.getDefaultCell().setBorder(0);
                    footer.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
                    footer.addCell(new Phrase("Askarbek Kamchiev", text_font));
                    footer.addCell(new Phrase("Total Credits Earned: " + Math.round(creditSum), text_font));
                    footer.addCell(new Phrase("Head of Student Affairs", text_font));
                    footer.addCell(new Phrase("", text_font));
                    footer.addCell(new Phrase("Date of issue: " + df.format(new java.util.Date()) + "", text_font));
                    footer.addCell(new Phrase("", text_font));
                    document.add(footer);
                   
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
        
        resource = new StreamResource(source1, "TokenReport.pdf"+System.currentTimeMillis(), app);
        resource.setMIMEType("application/pdf");

         
        
        //e.setSource(resource);
        //e.setParameter("Content-Disposition", "attachment; filename=" + resource.getFilename());
       

        //app.addComponent(e);
        app.getMainWindow().open(resource);
	}
	
	
}