package kg.cloud.uims.dao;

import java.sql.*;
import java.util.*;
import kg.cloud.uims.domain.Student;

public class DbStudent extends BaseDb {
    public ArrayList<Student> q;
    
    public DbStudent() throws Exception{
        super();
    }

    public void execSQL_GR(String gr_id) throws SQLException{
        
        String sql = "select t1.id, t1.name, t1.surname, t1.rollnum, t1.edu_order_number1, t1.awards, t1.attestat, t1.permadr," +
                " t1.curradr, t1.birthpl, t1.phone, t1.birth_date, t1.passport, t1.email, t1.photo," +
                " t1.mother_name, t1.mother_surname, t1.mother_workpl, t1.mother_phone," +
                " t1.father_name, t1.father_surname, t1.father_workpl, t1.father_phone," +
                " t1.note_1, t1.note_2, t1.note_3, t1.note_4, t2.name, t3.name, t3.code, t4.name, t5.name, t6.name," +
                " t7.name, t8.year, t9.name, t10.name, t11.name, t12.name, t13.name, t14.code," +
                " t15.nationality, t16.code, t17.user_pass, t17.status, t1.grad_project,t1.edu_order_number2, t1.edu_order_number3,t18.status" +
                " from student as t1" +
                " left join sinif as t2 on t1.group_id=t2.id" +
                " left join department as t3 on t1.dept_id=t3.id" +                
                " left join education as t4 on t1.edu_status_id=t4.id" +
                " left join academic as t5 on t1.acad_status_id=t5.id" +
                " left join grad_school_type as t6 on t1.grad_school_type_id=t6.id" +
                " left join grad_school_language as t7 on t1.grad_school_language_id=t7.id" +
                " left join year as t8 on t1.enter_year_id=t8.id" +
                " left join remark as t9 on t1.remark_id=t9.id" +
                " left join school as t10 on t1.school_id=t10.id" +
                " left join country as t11 on t1.country_id=t11.id" +
                " left join region as t12 on t1.region_id=t12.id" +
                " left join oblast as t13 on t1.oblast_id=t13.id" +
                " left join blood as t14 on t1.blood_id=t14.id" +
                " left join nationality as t15 on t1.nationality_id=t15.id" +
                " left join gender as t16 on t1.gender_id=t16.id" +
                " left join users as t17 on t1.rollnum=t17.user_name" +
                " left join accounting as t18 on t1.acc_status=t18.id "+
                " where t1.group_id=? and (t1.edu_status_id=1 or t1.edu_status_id=3 or t1.edu_status_id=5) order by t1.name asc;";
        q = new ArrayList<Student>();        
        PreparedStatement stat = dbCon.prepareStatement(sql);
        stat.setString(1,gr_id);
        ResultSet result = stat.executeQuery();
        while (result.next()) { 
            q.add(new Student(result.getInt("t1.id"),result.getString("t1.name"),result.getString("t1.surname"),
                    result.getString("t1.rollnum"),result.getString("t1.edu_order_number1"),result.getString("t1.awards"),
                    result.getString("t1.attestat"),result.getString("t1.permadr"),result.getString("t1.curradr"),result.getString("t1.birthpl"),
                    result.getString("t1.phone"),result.getDate("t1.birth_date"),
                    result.getString("t1.passport"),result.getString("t1.email"),result.getString("t1.photo"),
                    result.getString("t1.mother_name"),result.getString("t1.mother_surname"),
                    result.getString("t1.mother_workpl"),result.getString("t1.mother_phone"),
                    result.getString("t1.father_name"),result.getString("t1.father_surname"),
                    result.getString("t1.father_workpl"),result.getString("t1.father_phone"),
                    result.getString("t1.note_1"),result.getString("t1.note_2"),result.getString("t1.note_3"),result.getString("t1.note_4"),
                    result.getString("t2.name"),result.getString("t3.name"),result.getString("t3.code"),result.getString("t4.name"),
                    result.getString("t5.name"),result.getString("t6.name"),result.getString("t7.name"),result.getString("t8.year"),
                    result.getString("t9.name"),result.getString("t10.name"),result.getString("t11.name"),result.getString("t12.name"),
                    result.getString("t13.name"),result.getString("t14.code"),result.getString("t15.nationality"),
                    result.getString("t16.code"),result.getString("t17.user_pass"), result.getString("t1.grad_project"),
                    result.getString("t17.status"),result.getString("t1.edu_order_number2"),result.getString("t1.edu_order_number3"),
                    result.getString("t18.status")));
        }
    }

    public void execSQL_GR_Active(String gr_id) throws SQLException{

        String sql = "select t1.id, t1.name, t1.surname, t1.rollnum, t1.edu_order_number1, t1.awards, t1.attestat, t1.permadr," +
                " t1.curradr, t1.birthpl, t1.phone, t1.birth_date, t1.passport, t1.email, t1.photo," +
                " t1.mother_name, t1.mother_surname, t1.mother_workpl, t1.mother_phone," +
                " t1.father_name, t1.father_surname, t1.father_workpl, t1.father_phone," +
                " t1.note_1, t1.note_2, t1.note_3, t1.note_4, t2.name, t3.name, t3.code, t4.name, t5.name, t6.name," +
                " t7.name, t8.year, t9.name, t10.name, t11.name, t12.name, t13.name, t14.code," +
                " t15.nationality, t16.code, t17.user_pass, t17.status, t1.grad_project,t1.edu_order_number2, t1.edu_order_number3" +
                " from student as t1" +
                " left join sinif as t2 on t1.group_id=t2.id" +
                " left join department as t3 on t1.dept_id=t3.id" +
                " left join education as t4 on t1.edu_status_id=t4.id" +
                " left join academic as t5 on t1.acad_status_id=t5.id" +
                " left join grad_school_type as t6 on t1.grad_school_type_id=t6.id" +
                " left join grad_school_language as t7 on t1.grad_school_language_id=t7.id" +
                " left join year as t8 on t1.enter_year_id=t8.id" +
                " left join remark as t9 on t1.remark_id=t9.id" +
                " left join school as t10 on t1.school_id=t10.id" +
                " left join country as t11 on t1.country_id=t11.id" +
                " left join region as t12 on t1.region_id=t12.id" +
                " left join oblast as t13 on t1.oblast_id=t13.id" +
                " left join blood as t14 on t1.blood_id=t14.id" +
                " left join nationality as t15 on t1.nationality_id=t15.id" +
                " left join gender as t16 on t1.gender_id=t16.id" +
                " left join users as t17 on t1.rollnum=t17.user_name" +
                " where t1.group_id=? and t1.edu_status_id=1 order by t1.name asc;";
        q = new ArrayList<Student>();
        PreparedStatement stat = dbCon.prepareStatement(sql);
        stat.setString(1,gr_id);
        ResultSet result = stat.executeQuery();
        while (result.next()) {
            q.add(new Student(result.getInt("t1.id"),result.getString("t1.name"),result.getString("t1.surname"),
                    result.getString("t1.rollnum"),result.getString("t1.edu_order_number1"),result.getString("t1.awards"),
                    result.getString("t1.attestat"),result.getString("t1.permadr"),result.getString("t1.curradr"),result.getString("t1.birthpl"),
                    result.getString("t1.phone"),result.getDate("t1.birth_date"),
                    result.getString("t1.passport"),result.getString("t1.email"),result.getString("t1.photo"),
                    result.getString("t1.mother_name"),result.getString("t1.mother_surname"),
                    result.getString("t1.mother_workpl"),result.getString("t1.mother_phone"),
                    result.getString("t1.father_name"),result.getString("t1.father_surname"),
                    result.getString("t1.father_workpl"),result.getString("t1.father_phone"),
                    result.getString("t1.note_1"),result.getString("t1.note_2"),result.getString("t1.note_3"),result.getString("t1.note_4"),
                    result.getString("t2.name"),result.getString("t3.name"),result.getString("t3.code"),result.getString("t4.name"),
                    result.getString("t5.name"),result.getString("t6.name"),result.getString("t7.name"),result.getString("t8.year"),
                    result.getString("t9.name"),result.getString("t10.name"),result.getString("t11.name"),result.getString("t12.name"),
                    result.getString("t13.name"),result.getString("t14.code"),result.getString("t15.nationality"),
                    result.getString("t16.code"),result.getString("t17.user_pass"), result.getString("t1.grad_project"),
                    result.getString("t17.status"),result.getString("t1.edu_order_number2"),result.getString("t1.edu_order_number3")));
        }
    }

    public void execSQL_GR_All(String gr_id) throws SQLException{

        String sql = "select t1.id, t1.name, t1.surname, t1.rollnum, t1.edu_order_number1, t1.awards, t1.attestat, t1.permadr," +
                " t1.curradr, t1.birthpl, t1.phone, t1.birth_date, t1.passport, t1.email, t1.photo," +
                " t1.mother_name, t1.mother_surname, t1.mother_workpl, t1.mother_phone," +
                " t1.father_name, t1.father_surname, t1.father_workpl, t1.father_phone," +
                " t1.note_1, t1.note_2, t1.note_3, t1.note_4, t2.name, t3.name, t3.code, t4.name, t5.name, t6.name," +
                " t7.name, t8.year, t9.name, t10.name, t11.name, t12.name, t13.name, t14.code," +
                " t15.nationality, t16.code, t17.user_pass, t17.status, t1.grad_project,t1.edu_order_number2, t1.edu_order_number3" +
                " from student as t1" +
                " left join sinif as t2 on t1.group_id=t2.id" +
                " left join department as t3 on t1.dept_id=t3.id" +
                " left join education as t4 on t1.edu_status_id=t4.id" +
                " left join academic as t5 on t1.acad_status_id=t5.id" +
                " left join grad_school_type as t6 on t1.grad_school_type_id=t6.id" +
                " left join grad_school_language as t7 on t1.grad_school_language_id=t7.id" +
                " left join year as t8 on t1.enter_year_id=t8.id" +
                " left join remark as t9 on t1.remark_id=t9.id" +
                " left join school as t10 on t1.school_id=t10.id" +
                " left join country as t11 on t1.country_id=t11.id" +
                " left join region as t12 on t1.region_id=t12.id" +
                " left join oblast as t13 on t1.oblast_id=t13.id" +
                " left join blood as t14 on t1.blood_id=t14.id" +
                " left join nationality as t15 on t1.nationality_id=t15.id" +
                " left join gender as t16 on t1.gender_id=t16.id" +
                " left join users as t17 on t1.rollnum=t17.user_name" +
                " where t1.group_id=? order by t1.name asc;";
        q = new ArrayList<Student>();
        PreparedStatement stat = dbCon.prepareStatement(sql);
        stat.setString(1,gr_id);
        ResultSet result = stat.executeQuery();
        while (result.next()) {
            q.add(new Student(result.getInt("t1.id"),result.getString("t1.name"),result.getString("t1.surname"),
                    result.getString("t1.rollnum"),result.getString("t1.edu_order_number1"),result.getString("t1.awards"),
                    result.getString("t1.attestat"),result.getString("t1.permadr"),result.getString("t1.curradr"),result.getString("t1.birthpl"),
                    result.getString("t1.phone"),result.getDate("t1.birth_date"),
                    result.getString("t1.passport"),result.getString("t1.email"),result.getString("t1.photo"),
                    result.getString("t1.mother_name"),result.getString("t1.mother_surname"),
                    result.getString("t1.mother_workpl"),result.getString("t1.mother_phone"),
                    result.getString("t1.father_name"),result.getString("t1.father_surname"),
                    result.getString("t1.father_workpl"),result.getString("t1.father_phone"),
                    result.getString("t1.note_1"),result.getString("t1.note_2"),result.getString("t1.note_3"),result.getString("t1.note_4"),
                    result.getString("t2.name"),result.getString("t3.name"),result.getString("t3.code"),result.getString("t4.name"),
                    result.getString("t5.name"),result.getString("t6.name"),result.getString("t7.name"),result.getString("t8.year"),
                    result.getString("t9.name"),result.getString("t10.name"),result.getString("t11.name"),result.getString("t12.name"),
                    result.getString("t13.name"),result.getString("t14.code"),result.getString("t15.nationality"),
                    result.getString("t16.code"),result.getString("t17.user_pass"), result.getString("t1.grad_project"),
                    result.getString("t17.status"),result.getString("t1.edu_order_number2"),result.getString("t1.edu_order_number3")));
        }
    }

    public void execSQL(String sid) throws SQLException{
        
        String sql = "select t1.id, t1.name, t1.surname, t1.rollnum, t1.edu_order_number1, t1.awards, t1.attestat, t1.permadr," +
                " t1.curradr, t1.birthpl, t1.phone, t1.birth_date, t1.passport, t1.email, t1.photo," +
                " t1.mother_name, t1.mother_surname, t1.mother_workpl, t1.mother_phone," +
                " t1.father_name, t1.father_surname, t1.father_workpl, t1.father_phone," +
                " t1.note_1, t1.note_2, t1.note_3, t1.note_4, t2.name, t3.name, t3.code, t4.name, t5.name, t6.name," +
                " t7.name, t8.year, t9.name, t10.name, t11.name, t12.name, t13.name, t14.code," +
                " t15.nationality, t16.code, t17.user_pass, t17.status, t1.grad_project,t1.edu_order_number2, t1.edu_order_number3" +
                " from student as t1" +
                " left join sinif as t2 on t1.group_id=t2.id" +
                " left join department as t3 on t1.dept_id=t3.id" +
                " left join education as t4 on t1.edu_status_id=t4.id" +
                " left join academic as t5 on t1.acad_status_id=t5.id" +
                " left join grad_school_type as t6 on t1.grad_school_type_id=t6.id" +
                " left join grad_school_language as t7 on t1.grad_school_language_id=t7.id" +
                " left join year as t8 on t1.enter_year_id=t8.id" +
                " left join remark as t9 on t1.remark_id=t9.id" +
                " left join school as t10 on t1.school_id=t10.id" +
                " left join country as t11 on t1.country_id=t11.id" +
                " left join region as t12 on t1.region_id=t12.id" +
                " left join oblast as t13 on t1.oblast_id=t13.id" +
                " left join blood as t14 on t1.blood_id=t14.id" +
                " left join nationality as t15 on t1.nationality_id=t15.id" +
                " left join gender as t16 on t1.gender_id=t16.id" +
                " left join users as t17 on t1.rollnum=t17.user_name" +                
                " where t1.id=? order by t1.name asc;";
        
        q = new ArrayList<Student>();
        
        PreparedStatement stat = dbCon.prepareStatement(sql);
        stat.setString(1,sid);
        ResultSet result = stat.executeQuery();
        while (result.next()) {
            q.add(new Student(result.getInt("t1.id"),result.getString("t1.name"),result.getString("t1.surname"),
                    result.getString("t1.rollnum"),result.getString("t1.edu_order_number1"),result.getString("t1.awards"),
                    result.getString("t1.attestat"),result.getString("t1.permadr"),result.getString("t1.curradr"),result.getString("t1.birthpl"),
                    result.getString("t1.phone"),result.getDate("t1.birth_date"),
                    result.getString("t1.passport"),result.getString("t1.email"),result.getString("t1.photo"),
                    result.getString("t1.mother_name"),result.getString("t1.mother_surname"),
                    result.getString("t1.mother_workpl"),result.getString("t1.mother_phone"),
                    result.getString("t1.father_name"),result.getString("t1.father_surname"),
                    result.getString("t1.father_workpl"),result.getString("t1.father_phone"),
                    result.getString("t1.note_1"),result.getString("t1.note_2"),result.getString("t1.note_3"),result.getString("t1.note_4"),
                    result.getString("t2.name"),result.getString("t3.name"),result.getString("t3.code"),result.getString("t4.name"),
                    result.getString("t5.name"),result.getString("t6.name"),result.getString("t7.name"),result.getString("t8.year"),
                    result.getString("t9.name"),result.getString("t10.name"),result.getString("t11.name"),result.getString("t12.name"),
                    result.getString("t13.name"),result.getString("t14.code"),result.getString("t15.nationality"),
                    result.getString("t16.code"),result.getString("t17.user_pass"), result.getString("t1.grad_project"),
                    result.getString("t17.status"),result.getString("t1.edu_order_number2"),result.getString("t1.edu_order_number3")));
        }
    }
    public void deleteStudent(String id) throws SQLException{
        String query = "delete le,se,att,st,us,ur from less_stud as le " +
                "left join subj_exam as se on le.id=se.stud_less_id " +
                "left join student as st on st.id=le.student_id " +
                "left join attendance as att on att.student_id=st.id " +
                "left join users as us on st.rollnum=us.user_name " +
                "left join user_roles as ur on st.rollnum=ur.user_name " +
                "where st.id=?";        
        PreparedStatement statement = dbCon.prepareStatement(query);
        statement.setString(1,id);
        statement.executeUpdate();
    }

    public void updateEdu_status(String s_id) throws SQLException{
        String query = "update student set edu_status_id=1 where id=?";
        PreparedStatement statement = dbCon.prepareStatement(query);
        statement.setString(1, s_id);
        statement.executeUpdate();
    }
    
    public ArrayList<Student> getArray() {
        return q;
    }
}