/*
 * Student.java
 * Created on December 18, 2007, 1:32 PM
 */
package kg.cloud.uims.domain;

import java.io.Serializable;

/**
 * @author opensky
 */
public class Subjects implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String subjInstID;
	private String subjName;
	private String subjHrs;
	private String code;
	private String deptCode;
	private String deptName;
	private String stdYear;
	private String semester;
	private String credit;
	private int status;

	public Subjects(int i, String sbjN, String sbjH, String cd, String deptC,
			String deptN, String stdyr, String sem, String crdt, int status) {
		this.id = i;
		this.subjName = sbjN;
		this.subjHrs = sbjH;
		this.code = cd;
		this.deptCode = deptC;
		this.stdYear = stdyr;
		this.semester = sem;
		this.credit = crdt;
		this.deptName = deptN;
		this.status = status;
	}

	public Subjects(int i, String sbjN, String sbjH, String cd, String deptC,
			String deptN, String stdyr, String sem, String crdt,
			String subjInstID, int status) {
		this.id = i;
		this.subjInstID = subjInstID;
		this.subjName = sbjN;
		this.subjHrs = sbjH;
		this.code = cd;
		this.deptCode = deptC;
		this.stdYear = stdyr;
		this.semester = sem;
		this.credit = crdt;
		this.deptName = deptN;
		this.status = status;
	}

	public int getID() {
		return id;
	}

	public String getSubjInstID() {
		return subjInstID;
	}

	public String getSubjectName() {
		return subjName;
	}

	public String getSubjHrs() {
		return subjHrs;
	}

	public String getSubjCode() {
		return code;
	}

	public String getDepartment() {
		return deptCode;
	}

	public String getStdYear() {
		return stdYear;
	}

	public String getSemester() {
		return semester;
	}

	public String getSubjCredit() {
		return credit;
	}

	public String getDeptName() {
		return deptName;
	}

	public int getSubjStatus() {
		return status;
	}
}