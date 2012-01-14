/*
 * Group.java
 * Created on December 18, 2007, 3:06 PM
 */
package kg.cloud.uims.domain;

import java.io.Serializable;

/**
 * @author Mirlan
 */
public class Group implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
    private  String group_name;
    private int dept_id;
    private  String dept_code;
    private int faculty_id;
    private String faculty_code;
    
    public Group(int gr_id, String gr_name, int dept_id) {
        this.id = gr_id;
        this.group_name = gr_name;
        this.dept_id = dept_id;
    }

    public Group(int fid, String fcode, int did, String dcode, int sid, String sname) {
        this.faculty_id = fid;
        this.faculty_code = fcode;
        this.dept_id = did;
        this.dept_code = dcode;
        this.id = sid;
        this.group_name = sname;
    }
    
    public int getID() {
        return id;
    }
    
    public String getGr_Name() {
        return group_name;
    }

    public int getGroupDept_id(){
        return dept_id;
    }

    public int getF_ID(){
        return faculty_id;
    }
    public String getF_Code(){
        return faculty_code;
    }
    public int getD_ID(){
        return dept_id;
    }
    public String getD_Code(){
        return dept_code;
    }
}