package kg.cloud.uims.dao;

/*
 * DbStudent_Attendance.java
 * Created on January 26, 2008, 12:55 AM
 */


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import kg.cloud.uims.domain.Group;
import kg.cloud.uims.domain.Stud_Attendance;

/**
 * @author mantya
 */
public class DbStudent_Attendance extends BaseDb {
    private ArrayList<Stud_Attendance> data = null;
    
    /** Creates a new instance of DbStudent_Attendance */
    public DbStudent_Attendance() throws Exception {
        super();
    }
    
    public int execSQL_All(String s_id, String sem, String year, int subj_id) 
    		throws SQLException{
    	int attandence=0;
        query = "select if (sum(attendance)>0,sum(attendance),0) as att "
                + "from attendance where student_id =? and subject_id =? and semester_id=? and year_id=? ";
        
        PreparedStatement statement = dbCon.prepareStatement(query);
        statement.setString(1, s_id);
        statement.setInt(2, subj_id);
        statement.setString(3, sem);
        statement.setString(4, year);
        ResultSet result = statement.executeQuery();
        data = new ArrayList<Stud_Attendance>();
        while (result.next()) {
            attandence=result.getInt("att");
        }
        return attandence;
    }

    public void execSt_Subj(int year_id, int semester_id, int week_id, int subject_id) throws SQLException{
        query = "select distinct(attendance.id) as id, student.id as student_id, student.name as student_name, student.surname as student_surname, " +
                "attendance.attendance as attendance, sinif.name as student_group, less_stud.status as subject_status from attendance " +
                "inner join student on student.id=attendance.student_id " +
                "left join sinif on student.group_id=sinif.id " +
                "left join less_stud on less_stud.student_id=student.id " +
                "where less_stud.year_id=attendance.year_id and less_stud.sem_id=attendance.semester_id and attendance.year_id = ? and attendance.semester_id = ? and attendance.weeks_id = ? " +
                "and attendance.subject_id = ? and less_stud.subject_id = ? order by student_name,student_surname;";

        PreparedStatement statement = dbCon.prepareStatement(query);
        statement.setInt(1,year_id);
        statement.setInt(2,semester_id);
        statement.setInt(3,week_id);
        statement.setInt(4,subject_id);
        statement.setInt(5,subject_id);
        ResultSet result = statement.executeQuery();
        data = new ArrayList<Stud_Attendance>();
        while(result.next()){
            int id = result.getInt("id"),
                    student_id = result.getInt("student_id"),
                    attendance = result.getInt("attendance"),
                    subject_status = result.getInt("subject_status");
                    String student_name = result.getString("student_name"),
                    student_surname = result.getString("student_surname"),
                    student_group = result.getString("student_group");
            data.add(new Stud_Attendance(id,student_id,subject_id,year_id,semester_id,week_id,attendance,student_name,student_surname,subject_status,student_group));
        }
    }
    
    public void updateSQL(int id, int attendance) throws SQLException {
        query = "update attendance set attendance=? where id=?";
        PreparedStatement statement = dbCon.prepareStatement(query);
        statement.setInt(1,attendance);
        statement.setInt(2,id);
        statement.executeUpdate();
    }
    
    public void changeAttStatus(int studID, int subjID, int year, int sem) throws SQLException {
        query = "update less_stud set status = 4 where student_id=? and subject_id=? and year_id=? and sem_id=? ;";
        PreparedStatement statement = dbCon.prepareStatement(query);
        statement.setInt(1,studID);
        statement.setInt(2,subjID);
        statement.setInt(3,year);
        statement.setInt(4,sem);
        statement.executeUpdate();
    }
    
    public void changeMarkStatus(int studID, int subjID, int year, int sem) throws SQLException {
        query = "update less_stud set status = 5 where student_id=? and subject_id=? and year_id=? and sem_id=? ;";
        PreparedStatement statement = dbCon.prepareStatement(query);
        statement.setInt(1,studID);
        statement.setInt(2,subjID);
        statement.setInt(3,year);
        statement.setInt(4,sem);
        statement.executeUpdate();
    }
    
    public void updateAttStatus(int studID, int subjID, int year, int sem) throws SQLException {
        query = "update less_stud set status = 2 where student_id=? and subject_id=? and year_id=? and sem_id=? ;";
        PreparedStatement statement = dbCon.prepareStatement(query);
        statement.setInt(1,studID);
        statement.setInt(2,subjID);
        statement.setInt(3,year+1);
        statement.setInt(4,sem);
        statement.executeUpdate();
    }
    
    public void updateMarkStatus(int studID, int subjID, int year, int sem) throws SQLException {
        query = "update less_stud set status = 3 where student_id=? and subject_id=? and year_id=? and sem_id=? ;";
        PreparedStatement statement = dbCon.prepareStatement(query);
        statement.setInt(1,studID);
        statement.setInt(2,subjID);
        statement.setInt(3,year+1);
        statement.setInt(4,sem);
        statement.executeUpdate();
    }
    
    public void setStatus(int studID, int subjID, int year, int sem, int status) throws SQLException {
        query = "update less_stud set status=? where student_id=? and subject_id=? and year_id=? and sem_id=? ;";
        PreparedStatement statement = dbCon.prepareStatement(query);
        statement.setInt(1,status);
        statement.setInt(2,studID);
        statement.setInt(3,subjID);
        statement.setInt(4,year);
        statement.setInt(5,sem);
        statement.executeUpdate();
    }
    
    public void deleteSQL(int id) throws SQLException {
        query = "delete from attendance where id=?";
        PreparedStatement statement = dbCon.prepareStatement(query);
        statement.setInt(1,id);
        statement.executeUpdate();
    }
    
    public int insertAttendance(String subject_id, String year_id, String semester_id,
            String week_id, String student_id, String att) throws SQLException{
        query = "insert ignore into attendance(subject_id, year_id, semester_id, weeks_id, student_id, attendance) values(?,?,?,?,?,?);";
        PreparedStatement statement = dbCon.prepareStatement(query);
        statement.setString(1,subject_id);
        statement.setString(2,year_id);
        statement.setString(3,semester_id);
        statement.setString(4,week_id);
        statement.setString(5,student_id);
        statement.setString(6,att);
        int p=statement.executeUpdate();
     
        return p;
    }
    
    
    public void execSQL(int year_id, int semester_id, int week_id, int subject_id) throws SQLException{
        query = "select distinct(attendance.id) as id, student.id as student_id, student.name as student_name, student.surname as student_surname, " +
                "attendance.attendance as attendance, sinif.name as student_group, less_stud.status as subject_status from attendance " +
                "inner join student on student.id=attendance.student_id " +
                "left join sinif on student.group_id=sinif.id " +
                "left join less_stud on less_stud.student_id=student.id " +
                "where less_stud.year_id=attendance.year_id and less_stud.sem_id=attendance.semester_id and attendance.year_id = ? and attendance.semester_id = ? and attendance.weeks_id = ? " +
                "and attendance.subject_id = ? and less_stud.subject_id = ? order by student_name,student_surname;";
        
        PreparedStatement statement = dbCon.prepareStatement(query);
        statement.setInt(1,year_id);
        statement.setInt(2,semester_id);
        statement.setInt(3,week_id);
        statement.setInt(4,subject_id);
        statement.setInt(5,subject_id);
        ResultSet result = statement.executeQuery();
        data = new ArrayList<Stud_Attendance>();
        while(result.next()){
            int id = result.getInt("id"), 
                    student_id = result.getInt("student_id"), 
                    attendance = result.getInt("attendance"),                    
                    subject_status = result.getInt("subject_status");
                    String student_name = result.getString("student_name"), 
                    student_surname = result.getString("student_surname"), 
                    student_group = result.getString("student_group");
            data.add(new Stud_Attendance(id,student_id,subject_id,year_id,semester_id,week_id,attendance,student_name,student_surname,subject_status,student_group));
        }
    }
    
    public ArrayList<Stud_Attendance> getData() {
        return data;
    }
}