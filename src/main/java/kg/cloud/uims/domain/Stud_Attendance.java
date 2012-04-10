package kg.cloud.uims.domain;

import java.io.Serializable;

public class Stud_Attendance implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
    private int student_id;
    private int subject_id;
    private int year_id;
    private int semester_id;
    private int weeks_id;
    private int attendance;
    
    private String student_name;
    private String student_surname;
    private int subject_status;
    private String student_group;
    
    /** Creates a new instance of Stud_Attendance */
    public Stud_Attendance(int id, int student_id, int subject_id, int year_id, int semester_id, int weeks_id, int attendance, String student_name, String student_surname, int subject_status, String student_group) {
	this.id = id;
	this.student_id = student_id;
	this.subject_id = subject_id;
	this.year_id = year_id;
	this.semester_id = semester_id;
	this.weeks_id = weeks_id;
	this.attendance = attendance;
	this.student_name = student_name;
	this.student_surname = student_surname;
	this.subject_status = subject_status;
	this.student_group = student_group;
    }
    
    public int getId() {
	return id;
    }
    
    public int getStudent_id() {
	return student_id;
    }
    
    public int getSubject_id() {
	return subject_id;
    }
    
    public int getYear_id() {
	return year_id;
    }
    
    public int getSemester_id() {
	return semester_id;
    }
    
    public int getWeeks_id() {
	return weeks_id;
    }
    
    public int getAttendance() {
	return attendance;
    }
    
    public String getStudent_name() {
	return student_name;
    }
    
    public String getStudent_surname() {
	return student_surname;
    }
    
    public int getSubject_status() {
	return subject_status;
    }
    
    public String getStudent_group() {
	return student_group;
    }
    
}