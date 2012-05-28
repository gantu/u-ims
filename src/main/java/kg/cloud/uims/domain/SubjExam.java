package kg.cloud.uims.domain;

import java.io.Serializable;

public class SubjExam implements Serializable{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int ID;
    private int stud_less_id;
    private String studentName;
    private String studentSurname;
    private String group;
    private String subjectName;
    private int subjectStatus;
    private String examName;
    private int examMark;
    private int studID;
    private String stRollNum;
    
    public SubjExam(int id,int s_less_id,String sName,String sSurname,String gro,
            String subName,int subStatus,String exName,int exMark,int stud_id){
        
        this.ID=id;
        this.stud_less_id=s_less_id;
        this.studentName=sName;
        this.studentSurname=sSurname;
        this.group=gro;
        this.subjectName=subName;
        this.subjectStatus=subStatus;
        this.examName=exName;
        this.examMark=exMark;
        this.studID=stud_id;
    }
    
    public SubjExam(int id,int s_less_id,String sName,String sSurname,String gro,
            String subName,int subStatus,String exName,int exMark,int stud_id, String strollnum){
        
        this.ID=id;
        this.stud_less_id=s_less_id;
        this.studentName=sName;
        this.studentSurname=sSurname;
        this.group=gro;
        this.subjectName=subName;
        this.subjectStatus=subStatus;
        this.examName=exName;
        this.examMark=exMark;
        this.studID=stud_id;
        this.stRollNum = strollnum;
    }
    
    public SubjExam(int s_less_id,String sName,String sSurname,String gro,
            String subName,int subStatus){
        this.stud_less_id=s_less_id;
        this.studentName=sName;
        this.studentSurname=sSurname;
        this.group=gro;
        this.subjectName=subName;
        this.subjectStatus=subStatus;
    }
    
    public String getStudentName() {
        return studentName;
    }
    
    public String getStudentSurname() {
        return studentSurname;
    }
    
    public String getGroup() {
        return group;
    }
    
    public String getSubjectName() {
        return subjectName;
    }
    
    public int getSubjectStatus() {
        return subjectStatus;
    }
    
    public String getExamName() {
        return examName;
    }
    
    public int getExamMark() {
        return examMark;
    }
    
    public int getID() {
        return ID;
    }
    
    public int getStud_less_id() {
        return stud_less_id;
    }
    
    public int getStudID() {
        return studID;
    }
    
    public String getStRollNum() {
        return stRollNum;
    }
}