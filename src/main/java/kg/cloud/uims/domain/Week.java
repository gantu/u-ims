
package kg.cloud.uims.domain;

import java.io.Serializable;

public class Week implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
    private  String week;
    private int current;
    
    public Week(){}
    
    
    public Week(int i,int c,String wk) {
        this.id = i;
        this.current = c;
        this.week = wk;
    }
    
    public String getWeek() {
        return week;
    }
    
    public int getID(){
        return id;
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


	public void setWeek(String week) {
		this.week = week;
	}


	public void setCurrent(int current) {
		this.current = current;
	}
    
    
}