/*
 * Student.java
 * Created on December 18, 2007, 1:32 PM
 */
package kg.cloud.uims.domain;

import java.io.Serializable;

/**
 * @author opensky
 */
public class StudReg implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private int studID;
	private int status;
	private String yearID;
	private String semesterID;

	public StudReg(int i, int st_id, int sts, String y_id, String sem_id) {
		this.id = i;
		this.studID = st_id;
		this.status = sts;
		this.yearID = y_id;
		this.semesterID = sem_id;
	}

	public int getID() {
		return id;
	}

	public int getStudID() {
		return studID;
	}

	public int getStatus() {
		return status;
	}

	public String getSemesterID() {
		return semesterID;
	}

	public String getYearID() {
		return yearID;
	}

}