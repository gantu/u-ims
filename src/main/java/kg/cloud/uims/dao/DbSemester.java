package kg.cloud.uims.dao;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import kg.cloud.uims.domain.Semester;


public class DbSemester extends BaseDb {
    public ArrayList<Semester> q;
    
    public DbSemester() throws Exception{
        super();
    }
    
    public void execSQL() throws SQLException{
        
        String sql = "select * from semester ;";
        
        q = new ArrayList<Semester>();
        
        PreparedStatement stat = dbCon.prepareStatement(sql);
        ResultSet result = stat.executeQuery();
        while (result.next()) {
            q.add(new Semester(result.getInt("id"),result.getInt("curr"),result.getString("semester"),result.getInt("registration_status")));
        }
    }

    public void execSQL(String id) throws SQLException{

        String sql = "select * from semester where id=?;";

        q = new ArrayList<Semester>();

        PreparedStatement stat = dbCon.prepareStatement(sql);
        stat.setString(1,id);
        ResultSet result = stat.executeQuery();
        while (result.next()) {
            q.add(new Semester(result.getInt("id"),result.getInt("curr"),result.getString("semester")));
        }
    }
    
    public Semester execSQL_currSem() throws SQLException{
        
        String sql = "select * from semester where curr=1;";
        
        
        Semester currentSemester=null;
        PreparedStatement stat = dbCon.prepareStatement(sql);
        ResultSet result = stat.executeQuery();
        while (result.next()) {
            currentSemester=new Semester(result.getInt("id"),result.getInt("curr"),result.getString("semester"));
        }
        
        return currentSemester;
    }    
    
    public void execUpdate(String currSem) throws SQLException{
        String sql = "update semester set curr = 0 ;";
        
        PreparedStatement statement = dbCon.prepareStatement(sql);
        statement.executeUpdate();       
        
        sql = "update semester set curr = 1 where id=? ;";
        
        statement = dbCon.prepareStatement(sql);
        statement.setString(1,currSem);
        statement.executeUpdate();        
    }
    
    public ArrayList<Semester> getArray() {
        return q;
    }
}
