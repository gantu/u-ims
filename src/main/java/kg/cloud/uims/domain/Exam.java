package kg.cloud.uims.domain;

import java.io.Serializable;

public class Exam implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
    private  String exam;
    private int current;
    private int percentage;
    
    public Exam(){}
    
    public Exam(int i,int c,String ex,int percent) {
        this.id = i;
        this.current = c;
        this.exam = ex;
        this.percentage=percent;
    }
    
    public Exam(String e_name, int perc) {
    	this.exam=e_name;
    	this.percentage=perc;
	}

	public String getExam() {
        return exam;
    }
    
    public int getID(){
        return id;
    }
    
    public int getCurrent(){
        return current;
    }
    
    public int getPercentage(){
        return percentage;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setExam(String exam) {
		this.exam = exam;
	}

	public void setCurrent(int current) {
		this.current = current;
	}

	public void setPercentage(int percentage) {
		this.percentage = percentage;
	}
    
}