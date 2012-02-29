package kg.cloud.uims.domain;

import java.io.Serializable;

/**
 * @author opensky
 */
public class StudAccounting implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private int studID;
	private int reg_status;
	private int mid_status;
	private int fin_status;
	private String yearID;
	private String semesterID;

	public StudAccounting(int i, int st_id, String y_id, String sem_id, int r_sts, int m_sts, int f_sts) {
		this.id = i;
		this.studID = st_id;
		this.reg_status = r_sts;
		this.mid_status = m_sts;
		this.fin_status = f_sts;
		this.yearID = y_id;
		this.semesterID = sem_id;
	}

	public int getID() {
		return id;
	}

	public int getStudID() {
		return studID;
	}

	public int getRegStatus() {
		return reg_status;
	}
	
	public int getMidStatus() {
		return mid_status;
	}
	
	public int getFinStatus() {
		return fin_status;
	}

	public String getSemesterID() {
		return semesterID;
	}

	public String getYearID() {
		return yearID;
	}

}