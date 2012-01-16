package kg.cloud.uims.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import kg.cloud.uims.domain.Instructor;

public class DbInstructor extends BaseDb {
    public ArrayList <Instructor> q = null;
  
    
    public DbInstructor() throws Exception{
        super();
    }
    
    public void execSQL() throws SQLException{
        
        String sql = "select t1.id,t1.name,t1.surname,t2.name,t3.name,t1.rollnum,t4.name, t5.name,t6.name," +
                " t1.phone, t1.permadr, t1.curradr,t1.birthpl, t7.code,t8.nationality, t9.code,t1.birth_date," +
                " t1.passport,t1.email, t1.photo, t10.id,t10.code, t11.id, t11.code, t12.id, t12.name, t13.user_pass, t13.status, t14.role_name" +
                " from instructor as t1" +
                " left join level as t2 on t1.level_id=t2.id" +
                " left join status as t3 on t1.status_id=t3.id" +                                
                " left join country as t4 on t1.country_id=t4.id" +
                " left join region as t5 on t1.region_id=t5.id" +
                " left join oblast as t6 on t1.oblast_id=t6.id" +
                " left join blood as t7 on t1.blood_id=t7.id" +
                " left join nationality as t8 on t1.nationality_id=t8.id" +
                " left join gender as t9 on t1.gender_id=t9.id" +
                " left join faculty as t10 on t1.faculty_id=t10.id" +
                " left join department as t11 on t1.dept_id=t11.id" +
                " left join sinif as t12 on t1.group_id=t12.id" +
                " left join users as t13 on t1.rollnum=t13.user_name" +
                " left join user_roles as t14 on t1.rollnum=t14.user_name group by t1.id order by t1.name,t1.surname ;";
                
        
        PreparedStatement stat = dbCon.prepareStatement(sql);
        ResultSet result = stat.executeQuery();
        q = new ArrayList<Instructor>();
        
        while (result.next()) {
            q.add(new Instructor(result.getInt("t1.id"),result.getString("t1.name"),result.getString("t1.surname"),
                    result.getString("t2.name"), result.getString("t3.name"),
                    result.getString("t1.rollnum"),result.getString("t4.name"),
                    result.getString("t5.name"),result.getString("t6.name"),result.getString("t1.phone"),
                    result.getString("t1.permadr"),result.getString("t1.curradr"),
                    result.getString("t1.birthpl"),result.getString("t7.code"),
                    result.getString("t8.nationality"), result.getString("t9.code"),
                    result.getDate("t1.birth_date"),result.getString("t1.passport"),result.getString("t1.email"),
                    result.getString("t1.photo"), result.getString("t10.code"),
                    result.getString("t11.code"),result.getString("t12.name"),result.getString("t13.user_pass"),
                    result.getString("t14.role_name"), result.getString("t13.status"),
                    result.getInt("t10.id"),result.getInt("t11.id"),result.getInt("t12.id")));
        }
    }
    
    public Instructor execSQLRN(String instRollnum) throws SQLException{
        Instructor instructor=null;
        String sql = "select t1.id,t1.name,t1.surname,t2.name,t3.name,t1.rollnum,t4.name, t5.name,t6.name," +
                " t1.phone, t1.permadr, t1.curradr,t1.birthpl, t7.code,t8.nationality, t9.code,t1.birth_date," +
                " t1.passport,t1.email, t1.photo, t10.id, t10.code, t11.id, t11.code, t12.id, t12.name, t13.user_pass, t13.status, t14.role_name" +
                " from instructor as t1" +
                " left join level as t2 on t1.level_id=t2.id" +
                " left join status as t3 on t1.status_id=t3.id" +
                " left join country as t4 on t1.country_id=t4.id" +
                " left join region as t5 on t1.region_id=t5.id" +
                " left join oblast as t6 on t1.oblast_id=t6.id" +
                " left join blood as t7 on t1.blood_id=t7.id" +
                " left join nationality as t8 on t1.nationality_id=t8.id" +
                " left join gender as t9 on t1.gender_id=t9.id" +
                " left join faculty as t10 on t1.faculty_id=t10.id" +
                " left join department as t11 on t1.dept_id=t11.id" +
                " left join sinif as t12 on t1.group_id=t12.id" +
                " left join users as t13 on t1.rollnum=t13.user_name" +
                " left join user_roles as t14 on t1.rollnum=t14.user_name" +
                " where t1.rollnum=?;";
        
       
        
        PreparedStatement stat = dbCon.prepareStatement(sql);
        stat.setString(1,instRollnum);
        ResultSet result = stat.executeQuery();
        while (result.next()) {
           instructor=new Instructor(result.getInt("t1.id"),result.getString("t1.name"),result.getString("t1.surname"),
                    result.getString("t2.name"), result.getString("t3.name"),
                    result.getString("t1.rollnum"),result.getString("t4.name"),
                    result.getString("t5.name"),result.getString("t6.name"),result.getString("t1.phone"),
                    result.getString("t1.permadr"),result.getString("t1.curradr"),
                    result.getString("t1.birthpl"),result.getString("t7.code"),
                    result.getString("t8.nationality"), result.getString("t9.code"),
                    result.getDate("t1.birth_date"),result.getString("t1.passport"),result.getString("t1.email"),
                    result.getString("t1.photo"), result.getString("t10.code"),
                    result.getString("t11.code"),result.getString("t12.name"),result.getString("t13.user_pass"),
                    result.getString("t14.role_name"), result.getString("t13.status"),
                    result.getInt("t10.id"),result.getInt("t11.id"),result.getInt("t12.id"));
        }
        return instructor;
    }

    public Instructor execSQLRD(String role, int dept_id) throws SQLException{
    	Instructor instructor=new Instructor();
        String sql = "select t1.id,t1.name,t1.surname,t2.name,t3.name,t1.rollnum,t4.name, t5.name,t6.name," +
                " t1.phone, t1.permadr, t1.curradr,t1.birthpl, t7.code,t8.nationality, t9.code,t1.birth_date," +
                " t1.passport,t1.email, t1.photo, t10.id, t10.code, t11.id, t11.code, t12.id, t12.name, t13.user_pass, t13.status, t14.role_name" +
                " from instructor as t1" +
                " left join level as t2 on t1.level_id=t2.id" +
                " left join status as t3 on t1.status_id=t3.id" +
                " left join country as t4 on t1.country_id=t4.id" +
                " left join region as t5 on t1.region_id=t5.id" +
                " left join oblast as t6 on t1.oblast_id=t6.id" +
                " left join blood as t7 on t1.blood_id=t7.id" +
                " left join nationality as t8 on t1.nationality_id=t8.id" +
                " left join gender as t9 on t1.gender_id=t9.id" +
                " left join faculty as t10 on t1.faculty_id=t10.id" +
                " left join department as t11 on t1.dept_id=t11.id" +
                " left join sinif as t12 on t1.group_id=t12.id" +
                " left join users as t13 on t1.rollnum=t13.user_name" +
                " left join user_roles as t14 on t1.rollnum=t14.user_name" +
                " where t14.role_name=? and t1.dept_id=?;";


        PreparedStatement stat = dbCon.prepareStatement(sql);
        stat.setString(1, role);
        stat.setInt(2, dept_id);
        ResultSet result = stat.executeQuery();
        while (result.next()) {
           instructor.setId(result.getInt("t1.id"));
           instructor.setName(result.getString("t1.name"));
           instructor.setSurname(result.getString("t1.surname"));
                    instructor.setLevel(result.getString("t2.name"));
                    instructor.setStatus(result.getString("t3.name"));	
                    instructor.setRollnum(result.getString("t1.rollnum"));
                    instructor.setCountry(result.getString("t4.name"));
                    instructor.setRegion(result.getString("t5.name"));
                    instructor.setOblast(result.getString("t6.name"));
                    instructor.setPhone(result.getString("t1.phone"));
                    instructor.setPermAdd(result.getString("t1.permadr"));
                    instructor.setCurrAdd(result.getString("t1.curradr"));
                    instructor.setBirthPl(result.getString("t1.birthpl"));	
                    instructor.setBlood(result.getString("t7.code"));
                    instructor.setNationality(result.getString("t8.nationality"));
                    instructor.setGender(result.getString("t9.code"));
                    instructor.setDob(result.getDate("t1.birth_date"));
                    instructor.setPassport(result.getString("t1.passport"));
                    instructor.setEmail(result.getString("t1.email"));
                    instructor.setPhoto(result.getString("t1.photo"));
                    instructor.setFaculty(result.getString("t10.code"));
                    instructor.setDept(result.getString("t11.code"));
                    instructor.setGroup(result.getString("t12.name"));
                    instructor.setPassword(result.getString("t13.user_pass"));
                    instructor.setRole(result.getString("t14.role_name"));
                    instructor.setStatus(result.getString("t13.status"));
                    instructor.setF_id(result.getInt("t10.id"));
                    instructor.setD_id(result.getInt("t11.id"));
                    instructor.setG_id(result.getInt("t12.id"));
        }
        return instructor;
    }
    
    public void execSQLID(String instID) throws SQLException{
        
        String sql = "select t1.id,t1.name,t1.surname,t2.name,t3.name,t1.rollnum,t4.name, t5.name,t6.name," +
                " t1.phone, t1.permadr, t1.curradr,t1.birthpl, t7.code,t8.nationality, t9.code,t1.birth_date," +
                " t1.passport,t1.email, t1.photo, t10.id, t10.code, t11.id, t11.code, t12.id, t12.name, t13.user_pass, t13.status, t14.role_name" +
                " from instructor as t1" +
                " left join level as t2 on t1.level_id=t2.id" +
                " left join status as t3 on t1.status_id=t3.id" +
                " left join country as t4 on t1.country_id=t4.id" +
                " left join region as t5 on t1.region_id=t5.id" +
                " left join oblast as t6 on t1.oblast_id=t6.id" +
                " left join blood as t7 on t1.blood_id=t7.id" +
                " left join nationality as t8 on t1.nationality_id=t8.id" +
                " left join gender as t9 on t1.gender_id=t9.id" +
                " left join faculty as t10 on t1.faculty_id=t10.id" +
                " left join department as t11 on t1.dept_id=t11.id" +
                " left join sinif as t12 on t1.group_id=t12.id" +
                " left join users as t13 on t1.rollnum=t13.user_name" +
                " left join user_roles as t14 on t1.rollnum=t14.user_name"+
                " where t1.id=?;";
        
        q = new ArrayList<Instructor>();
        
        PreparedStatement stat = dbCon.prepareStatement(sql);
        stat.setString(1,instID);
        ResultSet result = stat.executeQuery();
        while (result.next()) {
           q.add(new Instructor(result.getInt("t1.id"),result.getString("t1.name"),result.getString("t1.surname"),
                    result.getString("t2.name"), result.getString("t3.name"),
                    result.getString("t1.rollnum"),result.getString("t4.name"),
                    result.getString("t5.name"),result.getString("t6.name"),result.getString("t1.phone"),
                    result.getString("t1.permadr"),result.getString("t1.curradr"),
                    result.getString("t1.birthpl"),result.getString("t7.code"),
                    result.getString("t8.nationality"), result.getString("t9.code"),
                    result.getDate("t1.birth_date"),result.getString("t1.passport"),result.getString("t1.email"),
                    result.getString("t1.photo"), result.getString("t10.code"),
                    result.getString("t11.code"),result.getString("t12.name"),result.getString("t13.user_pass"),
                    result.getString("t14.role_name"), result.getString("t13.status"),
                    result.getInt("t10.id"),result.getInt("t11.id"),result.getInt("t12.id")));
        }
    }

    public void execSQL_F_ID(int f_id) throws SQLException{

        String sql = "select t1.id,t1.name,t1.surname,t2.name,t3.name,t1.rollnum,t4.name, t5.name,t6.name," +
                " t1.phone, t1.permadr, t1.curradr,t1.birthpl, t7.code,t8.nationality, t9.code,t1.birth_date," +
                " t1.passport,t1.email, t1.photo, t10.id, t10.code, t11.id, t11.code, t12.id, t12.name, t13.user_pass, t13.status, t14.role_name" +
                " from instructor as t1" +
                " left join level as t2 on t1.level_id=t2.id" +
                " left join status as t3 on t1.status_id=t3.id" +
                " left join country as t4 on t1.country_id=t4.id" +
                " left join region as t5 on t1.region_id=t5.id" +
                " left join oblast as t6 on t1.oblast_id=t6.id" +
                " left join blood as t7 on t1.blood_id=t7.id" +
                " left join nationality as t8 on t1.nationality_id=t8.id" +
                " left join gender as t9 on t1.gender_id=t9.id" +
                " left join faculty as t10 on t1.faculty_id=t10.id" +
                " left join department as t11 on t1.dept_id=t11.id" +
                " left join sinif as t12 on t1.group_id=t12.id" +
                " left join users as t13 on t1.rollnum=t13.user_name" +
                " left join user_roles as t14 on t1.rollnum=t14.user_name"+
                " where t1.faculty_id=? group by t1.id";

        q = new ArrayList<Instructor>();

        PreparedStatement stat = dbCon.prepareStatement(sql);
        stat.setInt(1,f_id);
        ResultSet result = stat.executeQuery();
        while (result.next()) {
           q.add(new Instructor(result.getInt("t1.id"),result.getString("t1.name"),result.getString("t1.surname"),
                    result.getString("t2.name"), result.getString("t3.name"),
                    result.getString("t1.rollnum"),result.getString("t4.name"),
                    result.getString("t5.name"),result.getString("t6.name"),result.getString("t1.phone"),
                    result.getString("t1.permadr"),result.getString("t1.curradr"),
                    result.getString("t1.birthpl"),result.getString("t7.code"),
                    result.getString("t8.nationality"), result.getString("t9.code"),
                    result.getDate("t1.birth_date"),result.getString("t1.passport"),result.getString("t1.email"),
                    result.getString("t1.photo"), result.getString("t10.code"),
                    result.getString("t11.code"),result.getString("t12.name"),result.getString("t13.user_pass"),
                    result.getString("t14.role_name"), result.getString("t13.status"),
                    result.getInt("t10.id"),result.getInt("t11.id"),result.getInt("t12.id")));
        }
    }

    public void execSQL_D_ID(int dept_id) throws SQLException{

        String sql = "select t1.id,t1.name,t1.surname,t2.name,t3.name,t1.rollnum,t4.name, t5.name,t6.name," +
                " t1.phone, t1.permadr, t1.curradr,t1.birthpl, t7.code,t8.nationality, t9.code,t1.birth_date," +
                " t1.passport,t1.email, t1.photo, t10.id, t10.code, t11.id, t11.code, t12.id, t12.name, t13.user_pass, t13.status, t14.role_name" +
                " from instructor as t1" +
                " left join level as t2 on t1.level_id=t2.id" +
                " left join status as t3 on t1.status_id=t3.id" +
                " left join country as t4 on t1.country_id=t4.id" +
                " left join region as t5 on t1.region_id=t5.id" +
                " left join oblast as t6 on t1.oblast_id=t6.id" +
                " left join blood as t7 on t1.blood_id=t7.id" +
                " left join nationality as t8 on t1.nationality_id=t8.id" +
                " left join gender as t9 on t1.gender_id=t9.id" +
                " left join faculty as t10 on t1.faculty_id=t10.id" +
                " left join department as t11 on t1.dept_id=t11.id" +
                " left join sinif as t12 on t1.group_id=t12.id" +
                " left join users as t13 on t1.rollnum=t13.user_name" +
                " left join user_roles as t14 on t1.rollnum=t14.user_name"+
                " where t1.dept_id=? group by t1.id";

        q = new ArrayList<Instructor>();

        PreparedStatement stat = dbCon.prepareStatement(sql);
        stat.setInt(1,dept_id);
        ResultSet result = stat.executeQuery();
        while (result.next()) {
           q.add(new Instructor(result.getInt("t1.id"),result.getString("t1.name"),result.getString("t1.surname"),
                    result.getString("t2.name"), result.getString("t3.name"),
                    result.getString("t1.rollnum"),result.getString("t4.name"),
                    result.getString("t5.name"),result.getString("t6.name"),result.getString("t1.phone"),
                    result.getString("t1.permadr"),result.getString("t1.curradr"),
                    result.getString("t1.birthpl"),result.getString("t7.code"),
                    result.getString("t8.nationality"), result.getString("t9.code"),
                    result.getDate("t1.birth_date"),result.getString("t1.passport"),result.getString("t1.email"),
                    result.getString("t1.photo"), result.getString("t10.code"),
                    result.getString("t11.code"),result.getString("t12.name"),result.getString("t13.user_pass"),
                    result.getString("t14.role_name"), result.getString("t13.status"),
                    result.getInt("t10.id"),result.getInt("t11.id"),result.getInt("t12.id")));
        }
    }

    public void deleteInstructor(String id) throws SQLException{
        String query = "delete from subj_instructor where inst_id=?";
        PreparedStatement statement = dbCon.prepareStatement(query);
        statement.setString(1,id);
        statement.executeUpdate();

        query = "delete from instructor,users,user_roles " +
                "using instructor " +
                "inner join users as users " +
                "inner join user_roles as user_roles " +
                "where instructor.rollnum=users.user_name and " +
                "instructor.rollnum=user_roles.user_name and instructor.id=?;";
        statement = dbCon.prepareStatement(query);
        statement.setString(1,id);
        statement.executeUpdate();
    }
    
    public ArrayList<Instructor> getArray() {
        return q;
    }

	
}