
package kg.cloud.uims.domain;

import java.io.Serializable;

/**
 * @author Mirlan
 */
public class Faculty implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
    private String code;
    private String name;
    
    public Faculty(){}
    public Faculty(int id, String code, String name) {
        this.id = id;
        this.code = code;
        this.name = name;
    }
    
    public int getFacultyId(){
        return id;
    }
    
    public String getFacultyCode(){
        return code;
    }
    
    public String getFacultyName(){
        return name;
    }
}