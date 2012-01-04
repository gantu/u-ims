
package kg.cloud.uims.domain;

import java.io.Serializable;

/**
 * @author Mirlan
 */

public class Department implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
    private String code;
    private String name;
    private int facultyId;
    private String facultyCode;
    
    public Department(int d_id, String d_code,String d_name, int faculty_id, String f_code ) {
        this.id = d_id;
        this.code = d_code;
        this.name = d_name;
        this.facultyId = faculty_id;
        this.facultyCode = f_code;
    }
    
    public int getID() {
        return id;
    }
    
    public String getDprt_Code(){
        return code;
    }
    
    public String getDprt_Name() {
        return name;
    }

    public int getFaculty_id(){
        return facultyId;
    }
    public String getFaculty_code(){
        return facultyCode;
    }
}