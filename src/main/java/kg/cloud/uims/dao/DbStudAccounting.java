package kg.cloud.uims.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import kg.cloud.uims.domain.StudAccounting;

public class DbStudAccounting extends BaseDb {
	public ArrayList<StudAccounting> q;

	public DbStudAccounting() throws Exception {
		super();
	}

	public void execSQL(String std_id, String semID, String yearID)
			throws SQLException {

		String sql = "select * from stud_accounting where student_id=? and sem_id=? and year_id=? ;";

		q = new ArrayList<StudAccounting>();

		PreparedStatement stat = dbCon.prepareStatement(sql);
		stat.setString(1, std_id);
		stat.setString(2, semID);
		stat.setString(3, yearID);
		ResultSet result = stat.executeQuery();
		while (result.next()) {
			q.add(new StudAccounting(result.getInt("id"), result
					.getInt("student_id"), result.getString("year_id"), result
					.getString("sem_id"), result.getInt("reg_status"), result
					.getInt("mid_status"), result.getInt("fin_status")));
		}
	}

	public ArrayList<StudAccounting> getArray() {
		return q;
	}
}
