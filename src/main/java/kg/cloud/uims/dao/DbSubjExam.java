package kg.cloud.uims.dao;

import java.sql.*;
import java.util.*;
import kg.cloud.uims.domain.SubjExam;

public class DbSubjExam extends BaseDb {
    public ArrayList<SubjExam> q;
    
    public DbSubjExam() throws Exception{
        super();
    }
    
    public void execSQL(String subj_id,String yearID,String semID,String exam_id) throws SQLException{
        
        String sql ="select t1.id, t1.name,t1.surname,t2.name,t3.exam_name,t4.s_exam_id,t4.stud_less_id,t4.mark,t5.status,t6.name " +
                "from less_stud as t5 left join student as t1 on t1.id=t5.student_id " +
                "left join subjects as t2 on t5.subject_id=t2.id " +
                "left join sinif as t6 on t1.group_id=t6.id " +
                "left join subj_exam as t4 on t4.stud_less_id=t5.id " +
                "left join exam as t3 on t4.s_exam_id=t3.exam_id " +
                "where t2.id=? and t4.exam_id=? and t5.year_id=? and t5.sem_id=? order by t1.name asc;";
        
        q = new ArrayList<SubjExam>();
        
        PreparedStatement stat = dbCon.prepareStatement(sql);
        stat.setString(1,subj_id);
        stat.setString(2,exam_id);
        stat.setString(3,yearID);
        stat.setString(4,semID);
        ResultSet result = stat.executeQuery();
        while (result.next()) {            
            q.add(new SubjExam(result.getInt("t4.s_exam_id"),
                    result.getInt("t4.stud_less_id"),result.getString("t1.name"),
                    result.getString("t1.surname"),result.getString("t6.name"),
                    result.getString("t2.name"),result.getInt("t5.status"),
                    result.getString("t3.exam_name"),result.getInt("t4.mark"),result.getInt("t1.id")));            
        }
    }
    
    public void execSQL_sID(String subj_id,String yearID,String semID) throws SQLException{
        
        String sql ="select distinct t4.stud_less_id,t1.name,t1.surname,t2.name,t6.name," +
                "t5.status  from less_stud as t5 left join student as " +
                "t1 on t5.student_id=t1.id left join subjects as t2 on " +
                "t5.subject_id=t2.id left join sinif as t6 on t1.group_id=t6.id " +
                "left join subj_exam as t4 on t5.id = t4.stud_less_id left join " +
                "exam as t3 on t4.exam_id=t3.exam_id where t2.id=? and " +
                "t5.year_id=? and t5.sem_id=? order by t1.name asc;";
        
        q = new ArrayList<SubjExam>();
        
        PreparedStatement stat = dbCon.prepareStatement(sql);
        stat.setString(1,subj_id);
        stat.setString(2,yearID);
        stat.setString(3,semID);
        ResultSet result = stat.executeQuery();
        while (result.next()) {
            q.add(new SubjExam(result.getInt("t4.stud_less_id"),result.getString("t1.name"),
                    result.getString("t1.surname"),result.getString("t6.name"),
                    result.getString("t2.name"),result.getInt("t5.status")));
        }
    }
    
    public void updateSQL(int s_exam_id, int mark) throws SQLException {
        query = "update subj_exam set mark=? where s_exam_id=?";
        PreparedStatement statement = dbCon.prepareStatement(query);
        statement.setInt(1,mark);
        statement.setInt(2,s_exam_id);
        statement.executeUpdate();
    }
    
    public int insertSQL(String StudLessID,String exam_id, String mark) throws SQLException {
        query = "insert ignore into subj_exam(stud_less_id,exam_id,mark) values(?,?,?) ;";
        PreparedStatement statement = dbCon.prepareStatement(query);
        statement.setString(1,StudLessID);
        statement.setString(2,exam_id);
        statement.setString(3,mark);
        int p=statement.executeUpdate();
        return p;
    }
    
    public void deleteSQL(String StudLessID,String exam) throws SQLException{
        query = "delete from subj_exam where stud_less_id=? and exam_id=?;";
        PreparedStatement statement = dbCon.prepareStatement(query);
        statement.setString(1,StudLessID);
        statement.setString(2,exam);
        statement.executeUpdate();
    }
    
    public ArrayList<SubjExam> getArray() {
        return q;
    }
}