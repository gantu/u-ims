package kg.cloud.uims.dao;

import java.sql.*;
import java.util.*;

import kg.cloud.uims.domain.Group;

public class DbGroup extends BaseDb {

    public ArrayList<Group> q;

    public DbGroup() throws Exception {
        super();
    }

    public void execSQL() throws SQLException {
        String sql = "select * from sinif order by name asc ;";

        q = new ArrayList<Group>();

        PreparedStatement stat = dbCon.prepareStatement(sql);
        ResultSet result = stat.executeQuery();
        while (result.next()) {
            q.add(new Group(result.getInt("id"), result.getString("name"), result.getInt("dept_id")));
        }
    }

    public void execSQL_byDept(String deptID) throws SQLException {
        String sql = "select id, name, dept_id from sinif "
                + "where dept_id=? order by name;";

        q = new ArrayList<Group>();

        PreparedStatement stat = dbCon.prepareStatement(sql);
        stat.setString(1, deptID);
        ResultSet result = stat.executeQuery();
        while (result.next()) {
            q.add(new Group(result.getInt("id"), result.getString("name"), result.getInt("dept_id")));
        }
    }

    public void execSQL_byID(String id) throws SQLException {
        String sql = "select id, name, dept_id from sinif where id=? ;";

        q = new ArrayList<Group>();

        PreparedStatement stat = dbCon.prepareStatement(sql);
        stat.setString(1, id);
        ResultSet result = stat.executeQuery();
        while (result.next()) {
            q.add(new Group(result.getInt("id"), result.getString("name"), result.getInt("dept_id")));
        }
    }

    public void execSQL_byFID(int f_id) throws SQLException {
        String sql = "select f.id, f.code, d.id, d.code, s.id, s.name from faculty as f "
                + "inner join department as d on d.faculty_id=f.id "
                + "inner join sinif as s on s.dept_id=d.id "
                + "where f.id=? ";
        q = new ArrayList<Group>();

        PreparedStatement stat = dbCon.prepareStatement(sql);
        stat.setInt(1, f_id);
        ResultSet result = stat.executeQuery();
        while (result.next()) {
            q.add(new Group(result.getInt("f.id"), result.getString("f.code"),
                    result.getInt("d.id"), result.getString("d.code"),
                    result.getInt("s.id"), result.getString("s.name")));
        }
    }

    public void execAddGroup(String groupName, String dept_id) throws SQLException {
        String sql = "insert into sinif(name,dept_id) values(?,?);";

        PreparedStatement stat = dbCon.prepareStatement(sql);
        stat.setString(1, groupName);
        stat.setInt(2, Integer.parseInt(dept_id));
        stat.executeUpdate();
    }

    public void updateGroup(String grID, String grName, String dept_id) throws SQLException {
        String sql = "update sinif set name=?, dept_id=? where id=?;";

        PreparedStatement stat = dbCon.prepareStatement(sql);
        stat.setString(1, grName);
        stat.setInt(2, Integer.parseInt(dept_id));
        stat.setString(3, grID);

        stat.executeUpdate();
    }

    public ArrayList<Group> getArray() {
        return q;
    }
}