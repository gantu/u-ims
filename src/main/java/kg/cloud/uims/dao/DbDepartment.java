package kg.cloud.uims.dao;

import java.sql.*;
import java.util.*;
import kg.cloud.uims.domain.Department;


public class DbDepartment extends BaseDb {

    public ArrayList<Department> q;

    public DbDepartment() throws Exception {
        super();
    }

    public void execSQL() throws SQLException {

        String sql = "select d.id,d.code,d.name,d.faculty_id, f.code from department as d "
                + "left join faculty as f on d.faculty_id=f.id order by d.code";

        q = new ArrayList<Department>();

        PreparedStatement stat = dbCon.prepareStatement(sql);
        ResultSet result = stat.executeQuery();
        while (result.next()) {
            q.add(new Department(result.getInt("id"), result.getString("code"), result.getString("name"),
                    result.getInt("faculty_id"), result.getString("f.code")));
        }
    }

    public void execSQL_byDept(String dept) throws SQLException {

        String sql = "select d.id,d.code,d.name,d.faculty_id, f.code from department as d "
                + "left join faculty as f on d.faculty_id=f.id "
                + "where d.id=? order by d.code ;";

        q = new ArrayList<Department>();

        PreparedStatement stat = dbCon.prepareStatement(sql);
        stat.setString(1, dept);
        ResultSet result = stat.executeQuery();
        while (result.next()) {
            q.add(new Department(result.getInt("id"), result.getString("code"), result.getString("name"),
                    result.getInt("faculty_id"), result.getString("f.code")));
        }
    }

    public void execSQL_byID(String id) throws SQLException {

        String sql = "select d.id,d.code,d.name,d.faculty_id, f.code from department as d "
                + "left join faculty as f on d.faculty_id=f.id "
                + "where d.id=? ;";

        q = new ArrayList<Department>();

        PreparedStatement stat = dbCon.prepareStatement(sql);
        stat.setString(1, id);
        ResultSet result = stat.executeQuery();
        while (result.next()) {
            q.add(new Department(result.getInt("id"), result.getString("code"), result.getString("name"),
                    result.getInt("faculty_id"), result.getString("f.code")));
        }
    }

    public void execSQL_F_ID(int f_id) throws SQLException {

        String sql = "select d.id,d.code,d.name,d.faculty_id, f.code from department as d "
                + "left join faculty as f on d.faculty_id=f.id "
                + "where f.id=? ;";

        q = new ArrayList<Department>();

        PreparedStatement stat = dbCon.prepareStatement(sql);
        stat.setInt(1, f_id);
        ResultSet result = stat.executeQuery();
        while (result.next()) {
            q.add(new Department(result.getInt("id"), result.getString("code"), result.getString("name"),
                    result.getInt("faculty_id"), result.getString("f.code")));
        }
    }

    public void execUpdte(String deptID, String deptCode, String deptName, String faculty) throws SQLException {

        String sql = "update department set code = ?, name = ?, faculty_id = ? where id = ?;";

        q = new ArrayList<Department>();

        PreparedStatement stat = dbCon.prepareStatement(sql);
        stat.setString(1, deptCode);
        stat.setString(2, deptName);
        stat.setInt(3, Integer.parseInt(faculty));
        stat.setString(4, deptID);
        stat.executeUpdate();
    }

    public void execAddDept(String deptCode, String deptName, String faculty) throws SQLException {
        String sql = "insert into department(code,name,faculty_id) values(?,?,?);";

        PreparedStatement stat = dbCon.prepareStatement(sql);
        stat.setString(1, deptCode);
        stat.setString(2, deptName);
        stat.setInt(3, Integer.parseInt(faculty));
        stat.executeUpdate();
    }

    public ArrayList<Department> getArray() {
        return q;
    }
}