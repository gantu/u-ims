/*
 * Student.java
 * Created on December 18, 2007, 1:32 PM
 */
package kg.cloud.uims.domain;

import java.io.Serializable;

/**
 * @author opensky
 */
public class StudLess implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private int studID;
	private int studDepID;
	private int subjID;
	private int groupID;
	private int status;
	private String studName;
	private String studSurname;
	private String subjName;
	private String subjCode;
	private String groupName;
	private String studYear;
	private String semester;
	private String edYear;
	private String subHour;
	private String subCredit;
	private String studDepartment;
	private String facultyName;
	private String yearID;
	private String semesterID;
	private String avMark;
	private String stRollNum;

	public StudLess(int i, int st_id, int sub_id, int grp_id, int sts,
			String std_name, String std_surname, String std_dep,
			String sub_name, String sub_code, String grp_name, String stdY,
			String edY, String sem, String hour, String credit,
			String facultyName, int studDepID) {
		this.id = i;
		this.studID = st_id;
		this.subjID = sub_id;
		this.groupID = grp_id;
		this.status = sts;
		this.studName = std_name;
		this.studSurname = std_surname;
		this.studDepartment = std_dep;
		this.subjName = sub_name;
		this.subjCode = sub_code;
		this.groupName = grp_name;
		this.studYear = stdY;
		this.edYear = edY;
		this.semester = sem;
		this.subHour = hour;
		this.subCredit = credit;
		this.facultyName = facultyName;
		this.studDepID = studDepID;
	}

	public StudLess(int i, int sub_id, String year_id, String sem_id,
			String sub_code, String sub_name, String credit, String stdY,
			String sem, String av_mark) {
		this.id = i;
		this.subjID = sub_id;
		this.yearID = year_id;
		this.semesterID = sem_id;
		this.subjCode = sub_code;
		this.subjName = sub_name;
		this.subCredit = credit;
		this.studYear = stdY;
		this.semester = sem;
		this.avMark = av_mark;

	}

	public StudLess(int i, int st_id, int sub_id, int grp_id, int sts,
			String std_name, String std_surname, String std_dep,
			String sub_name, String sub_code, String grp_name, String stdY,
			String edY, String sem, String hour, String credit,
			String facultyName, int studDepID, String rollNum) {
		this.id = i;
		this.studID = st_id;
		this.subjID = sub_id;
		this.groupID = grp_id;
		this.status = sts;
		this.studName = std_name;
		this.studSurname = std_surname;
		this.studDepartment = std_dep;
		this.subjName = sub_name;
		this.subjCode = sub_code;
		this.groupName = grp_name;
		this.studYear = stdY;
		this.edYear = edY;
		this.semester = sem;
		this.subHour = hour;
		this.subCredit = credit;
		this.facultyName = facultyName;
		this.studDepID = studDepID;
		this.stRollNum = rollNum;
	}

	public int getID() {
		return id;
	}

	public int getStudID() {
		return studID;
	}

	public int getSubjID() {
		return subjID;
	}

	public int getGrpID() {
		return groupID;
	}

	public int getStatus() {
		return status;
	}

	public String getStudName() {
		return studName;
	}

	public String getStudSurname() {
		return studSurname;
	}

	public String getStudDepartment() {
		return studDepartment;
	}

	public String getSubjName() {
		return subjName;
	}

	public String getSubjCode() {
		return subjCode;
	}

	public String getGrpName() {
		return groupName;
	}

	public String getStudYear() {
		return studYear;
	}

	public String getEdYear() {
		return edYear;
	}

	public String getSubHour() {
		return subHour;
	}

	public String getSubCredit() {
		return subCredit;
	}

	public String getSemester() {
		return semester;
	}

	public String getFacultyName() {
		return facultyName;
	}

	public int getStudDepID() {
		return studDepID;
	}

	public String getyearID() {
		return yearID;
	}

	public String getsemesterID() {
		return semesterID;
	}

	public String getavMark() {
		return avMark;
	}
	
	public String getRollNum() {
		return stRollNum;
	}
}