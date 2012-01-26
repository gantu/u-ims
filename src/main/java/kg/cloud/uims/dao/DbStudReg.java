package kg.cloud.uims.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbStudReg extends BaseDb {
	private String status = "pusto";

	public DbStudReg() throws Exception {
		super();
	}

	public String execSQL(String std_id, int semID, int yearID)
			throws SQLException {

		String sql = "select * from stud_reg where student_id=? and sem_id=? and year_id=?;";

		PreparedStatement stat = dbCon.prepareStatement(sql);
		stat.setString(1, std_id);
		stat.setInt(2, semID);
		stat.setInt(3, yearID);
		ResultSet result = stat.executeQuery();
		while (result.next()) {
		status = result.getString("status");
		}

		return status;
	}
	
	public void editStatus(String std_id, int semID, int yearID) throws SQLException {
		String sql = "update stud_reg set status=1 where student_id=? and sem_id=? and year_id=?;";

		PreparedStatement stat = dbCon.prepareStatement(sql);
		stat.setString(1, std_id);
		stat.setInt(2, semID);
		stat.setInt(3, yearID);
		stat.executeUpdate();
	}

}
