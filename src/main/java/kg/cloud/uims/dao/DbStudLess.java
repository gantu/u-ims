package kg.cloud.uims.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import kg.cloud.uims.domain.StudLess;


public class DbStudLess extends BaseDb{
    public ArrayList<StudLess> q;
    
    public DbStudLess() throws Exception{
        super();
    }
    
    public void execSQL(String std_id,String semID,String yearID) throws SQLException{
        
        String sql = "select t0.id,t1.id,t1.name,t1.surname,t6.name,t2.name,t2.id,t2.code,t2.stdyear,t2.hours,t2.credit,t3.id," +
                "t3.name,t4.year,t5.semester,t0.status, t7.name, t1.dept_id from less_stud as t0 " +
                "left join student as t1 on t0.student_id=t1.id " +
                "left join department as t6 on t1.dept_id=t6.id " +
                "left join subjects as t2 on t0.subject_id=t2.id " +
                "left join sinif as t3 on t1.group_id=t3.id " +
                "left join year as t4 on t0.year_id=t4.id " +
                "left join semester as t5 on t0.sem_id=t5.id " +
                "left join faculty as t7 on t6.faculty_id=t7.id " +
                "where t1.id=? and t5.id =? and t4.id=? order by t1.name,t1.surname asc;";
        
        q = new ArrayList<StudLess>();
        
        PreparedStatement stat = dbCon.prepareStatement(sql);
        stat.setString(1,std_id);
        stat.setString(2,semID);
        stat.setString(3,yearID);
        ResultSet result = stat.executeQuery();
        while (result.next()) {
            q.add(new StudLess(result.getInt("t0.id"),result.getInt("t1.id"),
                    result.getInt("t2.id"),result.getInt("t3.id"),
                    result.getInt("t0.status"),result.getString("t1.name"),
                    result.getString("t1.surname"),result.getString("t6.name"),
                    result.getString("t2.name"),result.getString("t2.code"),
                    result.getString("t3.name"),result.getString("t4.year"),
                    result.getString("t2.stdyear"),result.getString("t5.semester"),
                    result.getString("t2.hours"),result.getString("t2.credit"), result.getString("t7.name"),result.getInt("t1.dept_id")));
        }
    }
    
    public void execSQL_Subject(String subj_id,String yearID,String semID) throws SQLException{
        
        String sql = "select t0.id,t1.id,t1.name,t1.surname,t6.name,t2.name,t2.id," +
                "t2.code,t2.stdyear,t2.hours,t2.credit,t3.id," +
                "t3.name,t4.year,t5.semester,t0.status,t7.name, t1.dept_id from less_stud as t0 " +
                "left join student as t1 on t0.student_id=t1.id " +
                "left join department as t6 on t1.dept_id=t6.id " +
                "left join subjects as t2 on t0.subject_id=t2.id " +
                "left join sinif as t3 on t1.group_id=t3.id " +
                "left join year as t4 on t0.year_id=t4.id " +
                "left join semester as t5 on t0.sem_id=t5.id " +
                "left join faculty as t7 on t6.faculty_id=t7.id " +
                "where t2.id=? and t5.id =? and t4.id=? and t0.status > 0 and t0.status < 3 " +
                "and t1.edu_status_id=1 order by t1.name,t1.surname asc;";
        
        q = new ArrayList<StudLess>();
        
        PreparedStatement stat = dbCon.prepareStatement(sql);
        stat.setString(1,subj_id);
        stat.setString(2,semID);
        stat.setString(3,yearID);
        ResultSet result = stat.executeQuery();
        while (result.next()) {
            q.add(new StudLess(result.getInt("t0.id"),result.getInt("t1.id"),
                    result.getInt("t2.id"),result.getInt("t3.id"),
                    result.getInt("t0.status"),result.getString("t1.name"),
                    result.getString("t1.surname"),result.getString("t6.name"),
                    result.getString("t2.name"),result.getString("t2.code"),
                    result.getString("t3.name"),result.getString("t4.year"),
                    result.getString("t2.stdyear"),result.getString("t5.semester"),
                    result.getString("t2.hours"),result.getString("t2.credit"), result.getString("t7.name"), result.getInt("t1.dept_id")));
        }
    }
    
    public void execSQL_Exam(String subj_id,String yearID,String semID) throws SQLException{
        
        String sql = "select t0.id,t1.id,t1.name,t1.surname,t6.name,t2.name,t2.id,t2.code,t2.stdyear,t2.hours,t2.credit,t3.id," +
                "t3.name,t4.year,t5.semester,t0.status,t7.name, t1.dept_id from less_stud as t0 " +
                "left join student as t1 on t0.student_id=t1.id " +
                "left join department as t6 on t1.dept_id=t6.id " +
                "left join subjects as t2 on t0.subject_id=t2.id " +
                "left join sinif as t3 on t1.group_id=t3.id " +
                "left join year as t4 on t0.year_id=t4.id " +
                "left join semester as t5 on t0.sem_id=t5.id " +
                "left join faculty as t7 on t6.faculty_id=t7.id " +
                "where t2.id=? and t5.id =? and t4.id=? "+
                "and t0.status > 0 and t0.status < 4 and t1.edu_status_id=1 order by t1.name,t1.surname asc;";
        
        q = new ArrayList<StudLess>();
        
        PreparedStatement stat = dbCon.prepareStatement(sql);
        stat.setString(1,subj_id);
        stat.setString(2,semID);
        stat.setString(3,yearID);
        ResultSet result = stat.executeQuery();
        while (result.next()) {
            q.add(new StudLess(result.getInt("t0.id"),result.getInt("t1.id"),
                    result.getInt("t2.id"),result.getInt("t3.id"),
                    result.getInt("t0.status"),result.getString("t1.name"),
                    result.getString("t1.surname"),result.getString("t6.name"),
                    result.getString("t2.name"),result.getString("t2.code"),
                    result.getString("t3.name"),result.getString("t4.year"),
                    result.getString("t2.stdyear"),result.getString("t5.semester"),
                    result.getString("t2.hours"),result.getString("t2.credit"), result.getString("t7.name"), result.getInt("t1.dept_id")));
        }
    }   
    
    public void execSQL_Status(String subj_id,String yearID,String semID) throws SQLException{
        
        String sql = "select t0.id,t1.id,t1.name,t1.surname,t6.name,t2.name,t2.id," +
                "t2.code,t2.stdyear,t2.hours,t2.credit,t3.id," +
                "t3.name,t4.year,t5.semester,t0.status,t7.name, t1.dept_id from less_stud as t0 " +
                "left join student as t1 on t0.student_id=t1.id " +
                "left join department as t6 on t1.dept_id=t6.id " +
                "left join subjects as t2 on t0.subject_id=t2.id " +
                "left join sinif as t3 on t1.group_id=t3.id " +
                "left join year as t4 on t0.year_id=t4.id " +
                "left join semester as t5 on t0.sem_id=t5.id " +
                "left join faculty as t7 on t6.faculty_id=t7.id " +
                "where t2.id=? and t5.id =? and t4.id=? and t1.edu_status_id=1 order by t1.name,t1.surname asc;";
        
        q = new ArrayList<StudLess>();
        
        PreparedStatement stat = dbCon.prepareStatement(sql);
        stat.setString(1,subj_id);
        stat.setString(2,semID);
        stat.setString(3,yearID);
        ResultSet result = stat.executeQuery();
        while (result.next()) {
            q.add(new StudLess(result.getInt("t0.id"),result.getInt("t1.id"),
                    result.getInt("t2.id"),result.getInt("t3.id"),
                    result.getInt("t0.status"),result.getString("t1.name"),
                    result.getString("t1.surname"),result.getString("t6.name"),
                    result.getString("t2.name"),result.getString("t2.code"),
                    result.getString("t3.name"),result.getString("t4.year"),
                    result.getString("t2.stdyear"),result.getString("t5.semester"),
                    result.getString("t2.hours"),result.getString("t2.credit"), result.getString("t7.name"), result.getInt("t1.dept_id")));
        }
    }
    

    
    public void execRegistration(String studID, String subjID, String status, String year, String sem) throws Exception{
        String sql = "insert ignore into less_stud values('',?,?,?,?,?);";
        
        PreparedStatement stat = dbCon.prepareStatement(sql);
        stat.setString(1,studID);
        stat.setString(2,subjID);
        stat.setString(3,status);
        stat.setString(4,year);
        stat.setString(5,sem);
        stat.executeUpdate();
        
    }
    
    public ArrayList<StudLess> getArray() {
        return q;
    }
}
