package kg.cloud.uims.dao;

/*
 * DbUsers.java
 * Created on 29.5.2009
 */

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import kg.cloud.uims.domain.Users;
import kg.cloud.uims.domain.Year;

/**
 * @author focus
 */
public class DbUsers extends BaseDb{
    public ArrayList<Users> q;
    public DbUsers() throws Exception{
        super();
    }
    public void execSQL() throws SQLException{
        String sql = "select u.user_name, u.user_name, u.status, u.user_name from users as u" +
                " left join user_roles as r on u.user_name=r.user_name";
        q = new ArrayList<Users>();

        PreparedStatement stat = dbCon.prepareStatement(sql);
        ResultSet result = stat.executeQuery();
        while (result.next()) {
            q.add(new Users(result.getString("u.user_name"),result.getString("u.user_name"),result.getString("u.user_name"),result.getInt("u.status")));
        }
    }

    public void execSQL_status() throws SQLException{
        String sql = "select r.role_name,users.status,count(*) as sum from users " +
                "inner join user_roles as r on users.user_name=r.user_name group by r.role_name,users.status order by users.status,r.role_name;";
        q = new ArrayList<Users>();
        PreparedStatement statement = dbCon.prepareStatement(sql);
        ResultSet result = statement.executeQuery();
        while(result.next()){
            q.add(new Users(result.getString("r.role_name"),result.getInt("users.status"),result.getInt("sum")));
        }
    }
    
    public void execSQL_pass(String u_id) throws SQLException{
        String sql = "select u.user_name, u.user_pass, u.status, r.role_name from users as u " +
        		" inner join user_roles as r on u.user_name=r.user_name " +
                " where u.user_name=?";
        q = new ArrayList<Users>();

		PreparedStatement stat = dbCon.prepareStatement(sql);
		stat.setString(1, u_id);
		ResultSet result = stat.executeQuery();
        while(result.next()){
            q.add(new Users(result.getString("u.user_name"),result.getString("u.user_pass"),result.getString("r.role_name"),result.getInt("u.status")));
        }
    }
    
    public void editPass(String u_name, String pass) throws SQLException {
		String sql = "update users set user_pass=? where user_name=?;";

		PreparedStatement stat = dbCon.prepareStatement(sql);
		stat.setString(1, pass);
		stat.setString(2, u_name);
		stat.executeUpdate();
	}

    public ArrayList<Users> getArray() {
        return q;
    }
}