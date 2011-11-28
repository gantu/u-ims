package kg.cloud.uims.dao;

import java.sql.*;
import java.util.*;
import java.lang.String.*;

import kg.cloud.uims.domain.Year;


public class DbStudYears extends BaseDb {

    public ArrayList<Year> q;

    public DbStudYears() throws Exception {
        super();
    }

    public void execSQL() throws SQLException {
        String sql = "select * from year order by id;";
        q = new ArrayList<Year>();

        PreparedStatement stat = dbCon.prepareStatement(sql);
        ResultSet result = stat.executeQuery();
        while (result.next()) {
            q.add(new Year(result.getInt("id"), result.getInt("curr"), result.getString("year")));
        }
    }

    public void execSQL(String id) throws SQLException {
        String sql = "select * from year where id=? order by id;";
        q = new ArrayList<Year>();

        PreparedStatement stat = dbCon.prepareStatement(sql);
        stat.setString(1, id);
        ResultSet result = stat.executeQuery();
        while (result.next()) {
            q.add(new Year(result.getInt("id"), result.getInt("curr"), result.getString("year")));
        }
    }

    public void execSQL_currYear() throws SQLException {
        String sql = "select * from year where curr=1 order by id;";
        q = new ArrayList<Year>();

        PreparedStatement stat = dbCon.prepareStatement(sql);
        ResultSet result = stat.executeQuery();
        while (result.next()) {
            q.add(new Year(result.getInt("id"), result.getInt("curr"), result.getString("year")));
        }
    }

    public void execUpdate(String currY) throws SQLException {
        String sql = "update year set curr = 0;";

        PreparedStatement stat = dbCon.prepareStatement(sql);
        stat.executeUpdate();

        sql = "update year set curr = 1 where id =? ;";

        stat = dbCon.prepareStatement(sql);
        stat.setString(1, currY);
        stat.executeUpdate();
    }

    public void addYear(String year) throws SQLException {
        String sql = "insert into year(year,curr) values(?,?);";

        PreparedStatement stat = dbCon.prepareStatement(sql);
        stat.setString(1, year);
        stat.setInt(2, 0);
        stat.executeUpdate();
    }

    public void editYear(String year, String id) throws SQLException {
        String sql = "update year set year=? where id=?;";

        PreparedStatement stat = dbCon.prepareStatement(sql);
        stat.setString(1, year);
        stat.setString(2, id);
        stat.executeUpdate();
    }

    public ArrayList<Year> getArray() {
        return q;
    }
}
