/*
 * Semester.java
 * Created on December 18, 2007, 1:32 PM
 */
package kg.cloud.uims.domain;

import java.io.Serializable;

/**
 * @author mirlan
 */

public class Semester implements Serializable {
	/**
	 * Stores Semester details
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String semester;
	private int current;
	private int registrationStatus;

	public Semester() {
	}

	public Semester(int i, int c, String sem) {
		this.id = i;
		this.current = c;
		this.semester = sem;
	}
	
	public Semester(int i, int c, String sem, int rt) {
		this.id = i;
		this.current = c;
		this.semester = sem;
		this.registrationStatus=rt;
	}

	public String getSemester() {
		return semester;
	}

	public int getCurrent() {
		return current;
	}

	public int getId() {
		return id;
	}
	
	public int getRegStatus() {
		return registrationStatus;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public void setCurrent(int current) {
		this.current = current;
	}

}