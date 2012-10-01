/*
 * Student.java
 * Created on December 18, 2007, 1:32 PM
 */
package kg.cloud.uims.domain;

import java.io.Serializable;
import java.util.Date;

public class Student implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private int id;
    private String name;
    private String surname;
    private String group_name;
    private String dept_name;
    private String dept_code;
    private String rollnum;
    private String education;
    private String academic;
    private String ghsType;
    private String ghsLanguage;
    private String enterY;
    private String remark;
    private String edu_order_num1;
    private String awards;
    private String attestat;
    private String school;
    private String country;
    private String oblast;
    private String region;
    private String phone;
    private String purmAdr;
    private String currAdr;
    private String birthPl;
    private Date dob;
    private String blood;
    private String nation;
    private String gender;
    private String passport;
    private String email;
    private String photo;
    private String motherName;
    private String motherSurname;
    private String motherWorkPl;
    private String motherph;
    private String fatherName;
    private String fatherSurname;
    private String fatherWorkPl;
    private String fatherph;
    private String password;
    private String note1;
    private String note2;
    private String note3;
    private String note4;
    private String gradProject;
    private String status;
    private String edu_order_num2;
    private String edu_order_num3;
    private String acc_status;
    private String fullName;
    private String faculty_name;
    private int reg_stat;
    private int acc_reg_stat;
    private int acc_mid_stat;
    private int acc_fin_stat;

    public Student(int i, String nam, String sur, String rollnum, String edu_order_num1, String awards, String attestat,
            String purmAdr, String currAdr, String birthPl, String phone, Date dob, String passport, String email, String photo,
            String motherName, String motherSurname, String motherWorkPl, String motherPh,
            String fatherName, String fatherSurname, String fatherWorkPl, String fatherPh,
            String note1, String note2, String note3, String note4, String grp_nam, String dpt_nam, String dpt_code,
            String education, String academic, String ghsType, String ghsLanguage, String enterY,
            String remark, String school, String country, String region, String oblast, String blood, String nation, String gender,
            String password, String grad_project, String status, String edu_order_num2, String edu_order_num3) {
        this.id = i;
        this.name = nam;
        this.surname = sur;
        this.group_name = grp_nam;
        this.dept_name = dpt_nam;
        this.dept_code = dpt_code;
        this.rollnum = rollnum;
        this.education = education;
        this.academic = academic;
        this.ghsType = ghsType;
        this.ghsLanguage = ghsLanguage;
        this.enterY = enterY;
        this.remark = remark;
        this.edu_order_num1 = edu_order_num1;
        this.awards = awards;
        this.attestat = attestat;
        this.school = school;
        this.country = country;
        this.oblast = oblast;
        this.region = region;
        this.phone = phone;
        this.purmAdr = purmAdr;
        this.currAdr = currAdr;
        this.birthPl = birthPl;
        this.dob = dob;
        this.blood = blood;
        this.nation = nation;
        this.gender = gender;
        this.passport = passport;
        this.email = email;
        this.photo = photo;
        this.motherName = motherName;
        this.motherSurname = motherSurname;
        this.motherWorkPl = motherWorkPl;
        this.motherph = motherPh;
        this.fatherName = fatherName;
        this.fatherSurname = fatherSurname;
        this.fatherWorkPl = fatherWorkPl;
        this.fatherph = fatherPh;
        this.password = password;
        this.note1 = note1;
        this.note2 = note2;
        this.note3 = note3;
        this.note4 = note4;
        this.gradProject = grad_project;
        this.status = status;
        this.edu_order_num2 = edu_order_num2;
        this.edu_order_num3 = edu_order_num3;
    }

    public Student(int i, String nam, String sur, String rollnum, String edu_order_num1, String awards, String attestat,
            String purmAdr, String currAdr, String birthPl, String phone, Date dob, String passport, String email, String photo,
            String motherName, String motherSurname, String motherWorkPl, String motherPh,
            String fatherName, String fatherSurname, String fatherWorkPl, String fatherPh,
            String note1, String note2, String note3, String note4, String grp_nam, String dpt_nam, String dpt_code,
            String education, String academic, String ghsType, String ghsLanguage, String enterY,
            String remark, String school, String country, String region, String oblast, String blood, String nation, String gender,
            String password, String grad_project, String status, String edu_order_num2, String edu_order_num3, String acc_status) {
        this.id = i;
        this.name = nam;
        this.surname = sur;
        this.group_name = grp_nam;
        this.dept_name = dpt_nam;
        this.dept_code = dpt_code;
        this.rollnum = rollnum;
        this.education = education;
        this.academic = academic;
        this.ghsType = ghsType;
        this.ghsLanguage = ghsLanguage;
        this.enterY = enterY;
        this.remark = remark;
        this.edu_order_num1 = edu_order_num1;
        this.awards = awards;
        this.attestat = attestat;
        this.school = school;
        this.country = country;
        this.oblast = oblast;
        this.region = region;
        this.phone = phone;
        this.purmAdr = purmAdr;
        this.currAdr = currAdr;
        this.birthPl = birthPl;
        this.dob = dob;
        this.blood = blood;
        this.nation = nation;
        this.gender = gender;
        this.passport = passport;
        this.email = email;
        this.photo = photo;
        this.motherName = motherName;
        this.motherSurname = motherSurname;
        this.motherWorkPl = motherWorkPl;
        this.motherph = motherPh;
        this.fatherName = fatherName;
        this.fatherSurname = fatherSurname;
        this.fatherWorkPl = fatherWorkPl;
        this.fatherph = fatherPh;
        this.password = password;
        this.note1 = note1;
        this.note2 = note2;
        this.note3 = note3;
        this.note4 = note4;
        this.gradProject = grad_project;
        this.status = status;
        this.edu_order_num2 = edu_order_num2;
        this.edu_order_num3 = edu_order_num3;
        this.acc_status = acc_status;
    }

    public Student(String fname, String rollnum, String fct_nam, String dpt_nam) {

        this.fullName = fname;
        this.rollnum = rollnum;
        this.dept_name = dpt_nam;
        this.faculty_name = fct_nam;


    }

    public Student(int id, String name, String surname,
            String edu) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.education = edu;

    }
    
    public Student(int id, String name, String surname,
            String edu, int reg_s, int acc_reg, int acc_mid, int acc_fin) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.education = edu;
        this.reg_stat = reg_s;
        this.acc_reg_stat = acc_reg;
        this.acc_mid_stat = acc_mid;
        this.acc_fin_stat = acc_fin;

    }

    public int getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getGroupName() {
        return group_name;
    }

    public String getDepartName() {
        return dept_name;
    }

    public String getStRollnum() {
        return rollnum;
    }

    public String getDepartCode() {
        return dept_code;
    }

    public String getEducation() {
        return education;
    }

    public String getAcademic() {
        return academic;
    }

    public String getGhsType() {
        return ghsType;
    }

    public String getGhsLanguage() {
        return ghsLanguage;
    }

    public String getEnterY() {
        return enterY;
    }

    public String getRemark() {
        return remark;
    }

    public String getEnterOrderNumber() {
        return edu_order_num1;
    }

    public String getAcademOrderNumber() {
        return edu_order_num2;
    }

    public String getGraduateOrderNumber() {
        return edu_order_num3;
    }

    public String getAwards() {
        return awards;
    }

    public String getdocs() {
        return attestat;
    }

    public String getSchool() {
        return school;
    }

    public String getCountry() {
        return country;
    }

    public String getOblast() {
        return oblast;
    }

    public String getRegion() {
        return region;
    }

    public String getPhoneNumber() {
        return phone;
    }

    public String getPurmAddress() {
        return purmAdr;
    }

    public String getCurrAddress() {
        return currAdr;
    }

    public String getBirthPlace() {
        return birthPl;
    }

    public Date getDoB() {
        return dob;
    }

    public String getBlood() {
        return blood;
    }

    public String getNationality() {
        return nation;
    }

    public String getGender() {
        return gender;
    }

    public String getPassport() {
        return passport;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoto() {
        return photo;
    }

    public String getMotherName() {
        return motherName;
    }

    public String getMotherSurname() {
        return motherSurname;
    }

    public String getMotherWorkPlace() {
        return motherWorkPl;
    }

    public String getMotherPh() {
        return motherph;
    }

    public String getFatherName() {
        return fatherName;
    }

    public String getFatherSurname() {
        return fatherSurname;
    }

    public String getFatherWorkPlace() {
        return fatherWorkPl;
    }

    public String getFatherPh() {
        return fatherph;
    }

    public String getPassword() {
        return password;
    }

    public String getNote1() {
        return note1;
    }

    public String getNote2() {
        return note2;
    }

    public String getNote3() {
        return note3;
    }

    public String getNote4() {
        return note4;
    }

    public String getGraduateProject() {
        return gradProject;
    }

    public String getStatus() {
        return status;
    }

    /**
     * @return the acc_status
     */
    public String getAcc_status() {
        return acc_status;
    }

    public String getFull_name() {
        return fullName;
    }

    public String getFaculty_name() {
        return faculty_name;
    }

    /**
     * @param acc_status the acc_status to set
     */
    public void setAcc_status(String acc_status) {
        this.acc_status = acc_status;
    }

    public int getReg_stat() {
        return reg_stat;
    }

    public void setReg_stat(int reg_stat) {
        this.reg_stat = reg_stat;
    }

    public int getAcc_reg_stat() {
        return acc_reg_stat;
    }

    public void setAcc_reg_stat(int acc_reg_stat) {
        this.acc_reg_stat = acc_reg_stat;
    }

    public int getAcc_mid_stat() {
        return acc_mid_stat;
    }

    public void setAcc_mid_stat(int acc_mid_stat) {
        this.acc_mid_stat = acc_mid_stat;
    }

    public int getAcc_fin_stat() {
        return acc_fin_stat;
    }

    public void setAcc_fin_stat(int acc_fin_stat) {
        this.acc_fin_stat = acc_fin_stat;
    }
}
