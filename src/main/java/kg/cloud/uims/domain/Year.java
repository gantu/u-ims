/*
 * Student.java
 * Created on December 18, 2007, 1:32 PM
 */
package kg.cloud.uims.domain;

import java.io.Serializable;

/**
 * @author mirlan
 */
public class Year implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
    private int current;
    private  String year;
    
    public Year(){}
    
    public Year(int i,int c,String yr) {
        this.id = i;
        this.current = c;
        this.year = yr;
    }
    
    public String getYear() {
        return year;
    }
  
    public int getCurrent(){
        return current;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setCurrent(int current) {
		this.current = current;
	}

	public void setYear(String year) {
		this.year = year;
	}
    
}