package kg.cloud.uims.dao;



import java.sql.*;
import java.util.*;

import kg.cloud.uims.domain.Exam;

public class DbExam extends BaseDb {
    public ArrayList<Exam> q;
    
    public DbExam() throws Exception {
        super();
    }
    
    public void execSQL() throws SQLException{
        String sql = "select * from exam ;";
        q = new ArrayList<Exam>();
        
        PreparedStatement stat = dbCon.prepareStatement(sql);
        ResultSet result = stat.executeQuery();
        while (result.next()) {
            q.add(new Exam(result.getInt("exam_id"),result.getInt("curr"),
                    result.getString("exam_name"),result.getInt("percentage")));
        }
    }
    
    public void execSQL_currExam() throws SQLException{
        
        String sql = "select * from exam where curr=1;";
        q = new ArrayList<Exam>();
        
        PreparedStatement stat = dbCon.prepareStatement(sql);
        ResultSet result = stat.executeQuery();
        while (result.next()) {
            q.add(new Exam(result.getInt("exam_id"),result.getInt("curr"),
                    result.getString("exam_name"),result.getInt("percentage")));
        }
    }
    
    public void execSQL_Exam(String ex) throws SQLException{
        
        String sql = "select * from exam where exam_id=? ;";
        q = new ArrayList<Exam>();
        
        PreparedStatement stat = dbCon.prepareStatement(sql);
        stat.setString(1,ex);
        ResultSet result = stat.executeQuery();
        while(result.next()){
            q.add(new Exam(result.getInt("exam_id"),result.getInt("curr"),
                    result.getString("exam_name"),result.getInt("percentage")));
        }
    }
    
    public void execSetCurr_Exam(String currExam) throws SQLException{
        String sql = "update exam set curr = 0 ;";
        PreparedStatement stat = dbCon.prepareStatement(sql);
        stat.executeUpdate();
        
        sql = "update exam set curr = 1 where exam_id =? ;";
        stat = dbCon.prepareStatement(sql);
        stat.setString(1,currExam);
        stat.executeUpdate();
    }
    
    public void execStudentExamDetails(String s_id, String sem, String year,
            int subj_id) throws SQLException {
        String sql = "select se.mark, e.exam_name from less_stud as ls "
                + "inner join subj_exam as se on se.stud_less_id=ls.id "
                + "inner join exam as e on e.exam_id=se.exam_id "
                + "where ls.subject_id=? and ls.student_id=? "
                + "and ls.year_id=? and ls.sem_id=? ";

        q = new ArrayList<Exam>();

        PreparedStatement stat = dbCon.prepareStatement(sql);
        stat.setInt(1, subj_id);
        stat.setString(2, s_id);
        stat.setString(3, year);
        stat.setString(4, sem);
        ResultSet result = stat.executeQuery();
        ResultSetMetaData rsm = result.getMetaData();

        if (rsm.getColumnCount() != 0) {
        	while (result.next()) {
                q.add(new Exam(result.getString("e.exam_name"), result.getInt("se.mark")));
            }
        }
        
    }
    
    public ArrayList<Exam> getArray() {
        return q;
    }
}