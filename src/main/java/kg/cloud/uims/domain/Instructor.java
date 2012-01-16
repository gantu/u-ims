package kg.cloud.uims.domain;
import java.io.Serializable;
import java.util.*;

public class Instructor implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
    private String name;
    private String surname;
    private String level;
    private String status;
    private String rollnum;
    private String country;
    private String region;
    private String oblast;
    private String phone;
    private String permAdd;
    private String currAdd;
    private String birthPl;
    private String blood;
    private String nationality;
    private String gender;
    private Date dob;    
    private String passport;    
    private String email;
    private String photo;
    private String faculty;
    private String dept;
    private String group;
    private String password;
    private String role;
    private String account;
    private int f_id;
    private int d_id;
    private int g_id;
    
    public Instructor(int id,String name, String surname, String level,String status, String rollnum, String country, String region,
            String oblast,  String phone, String permAdd, String currAdd, String birthPl, String blood, String nationality,
            String gender, Date dob, String passport, String email, String photo, String faculty, String dept, String group,
            String password, String role, String account, int f_id, int d_id, int g_id) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.rollnum = rollnum;
        this.country = country;
        this.region = region;
        this.oblast = oblast;
        this.birthPl = birthPl;
        this.dob = dob;
        this.gender = gender;
        this.passport = passport;
        this.permAdd = permAdd;
        this.currAdd = currAdd;
        this.email = email;
        this.nationality = nationality;        
        this.phone = phone;
        this.blood = blood;
        this.password = password;
        this.level = level;
        this.status = status;
        this.role = role;
        this.faculty = faculty;
        this.photo = photo;
        this.dept = dept;
        this.group = group;
        this.account = account;
        this.f_id = f_id;
        this.d_id = d_id;
        this.g_id = g_id;
    }    
    public Instructor() {
		
	}
	public int getInstructorID(){
        return id;
    }
    public String getInstructorName() {
        return name;
    }
    public String getInstructorSurname() {
        return surname;
    }
    public String getInstructorRollNum(){
        return rollnum;
    }
    public String getInstructorCountry(){
        return country;
    }
    public String getInstructorRegion(){
        return region;
    }
    public String getInstructorBirthPl(){
        return birthPl;
    }
    public Date getInstructorDoB(){
        return dob;
    }
    public String getInstructorGender(){
        return gender;
    }
    public String getInstructorPassport(){
        return passport;
    }
    public String getInstructorPermAdd(){
        return permAdd;
    }
    public String getInstructorCurrAdd(){
        return currAdd;
    }
    public String getInstructorEmail(){
        return email;
    }
    public String getInstructorNationality(){
        return nationality;
    }
    public String getInstructorPhone(){
        return phone;
    }
    public String getInstructorBlood(){
        return blood;
    }
    public String getInstructorPassword(){
        return password;
    }
    public String getInstructorLevel() {
        return level;
    }
    public String getInstructorStatus(){
        return status;
    }
    public String getInstructorRole(){
        return role;
    }
    public String getInstructorOblast(){
        return oblast;
    }
    public String getInstructorPhoto(){
        return photo;
    }
    public String getInstructorFaculty(){
        return faculty;
    }
    public String getInstructorDept(){
        return dept;
    }
    public String getInstructorGroup(){
        return group;
    }
    public String getInstructorAccount(){
        return account;
    }
    public int getInstructorF_id(){
        return f_id;
    }
    public int getInstructorD_id(){
        return d_id;
    }
    public int getInstructorG_id(){
        return g_id;
    }
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setRollnum(String rollnum) {
		this.rollnum = rollnum;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public void setOblast(String oblast) {
		this.oblast = oblast;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public void setPermAdd(String permAdd) {
		this.permAdd = permAdd;
	}
	public void setCurrAdd(String currAdd) {
		this.currAdd = currAdd;
	}
	public void setBirthPl(String birthPl) {
		this.birthPl = birthPl;
	}
	public void setBlood(String blood) {
		this.blood = blood;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public void setPassport(String passport) {
		this.passport = passport;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public void setFaculty(String faculty) {
		this.faculty = faculty;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public void setF_id(int f_id) {
		this.f_id = f_id;
	}
	public void setD_id(int d_id) {
		this.d_id = d_id;
	}
	public void setG_id(int g_id) {
		this.g_id = g_id;
	}
}