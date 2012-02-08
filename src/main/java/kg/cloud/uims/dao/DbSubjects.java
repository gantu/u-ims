package kg.cloud.uims.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import kg.cloud.uims.domain.Subjects;

public class DbSubjects extends BaseDb {

	public ArrayList<Subjects> q;

	public DbSubjects() throws Exception {
		super();
	}

	public void execSQL() throws SQLException {
		String sql = "select t1.id,t1.name,t1.hours,t1.code,t2.code,t2.name,t1.stdyear,t3.semester,t1.credit, t1.status "
				+ "from subjects as t1 "
				+ "left join department as t2 on t1.dept_id=t2.id "
				+ "left join semester as t3 on t1.sem_id=t3.id "
				+ "left join student as t4 on t2.id=t4.dept_id order by t1.name asc ;";

		q = new ArrayList<Subjects>();

		PreparedStatement stat = dbCon.prepareStatement(sql);
		ResultSet result = stat.executeQuery();
		while (result.next()) {
			q.add(new Subjects(result.getInt("t1.id"), result
					.getString("t1.name"), result.getString("t1.hours"), result
					.getString("t1.code"), result.getString("t2.code"), result
					.getString("t2.name"), result.getString("t1.stdyear"),
					result.getString("t3.semester"), result
							.getString("t1.credit"), result.getInt("t1.status")));
		}
	}

	public void execSQL_Active() throws SQLException {
		String sql = "select t1.id,t1.name,t1.hours,t1.code,t2.code,t2.name,t1.stdyear,t3.semester,t1.credit, t1.status "
				+ "from subjects as t1 "
				+ "left join department as t2 on t1.dept_id=t2.id "
				+ "left join semester as t3 on t1.sem_id=t3.id "
				+ "left join student as t4 on t2.id=t4.dept_id "
				+ "where t1.status=1 order by t1.name asc ;";

		q = new ArrayList<Subjects>();

		PreparedStatement stat = dbCon.prepareStatement(sql);
		ResultSet result = stat.executeQuery();
		while (result.next()) {
			q.add(new Subjects(result.getInt("t1.id"), result
					.getString("t1.name"), result.getString("t1.hours"), result
					.getString("t1.code"), result.getString("t2.code"), result
					.getString("t2.name"), result.getString("t1.stdyear"),
					result.getString("t3.semester"), result
							.getString("t1.credit"), result.getInt("t1.status")));
		}
	}

	public void execSQL(String sid) throws SQLException {
		String sql = "select t1.id,t1.name,t1.hours,t1.code,t2.code,t2.name,t1.stdyear,t3.semester,t1.credit, t1.status "
				+ "from subjects as t1 "
				+ "left join department as t2 on t1.dept_id=t2.id "
				+ "left join semester as t3 on t1.sem_id=t3.id "
				+ "left join student as t4 on t2.id=t4.dept_id "
				+ "where t4.id=? and t1.status=1 order by t1.name asc ;";

		q = new ArrayList<Subjects>();

		PreparedStatement stat = dbCon.prepareStatement(sql);
		stat.setString(1, sid);
		ResultSet result = stat.executeQuery();
		while (result.next()) {
			q.add(new Subjects(result.getInt("t1.id"), result
					.getString("t1.name"), result.getString("t1.hours"), result
					.getString("t1.code"), result.getString("t2.code"), result
					.getString("t2.name"), result.getString("t1.stdyear"),
					result.getString("t3.semester"), result
							.getString("t1.credit"), result.getInt("t1.status")));
		}
	}

	public void execSQL_byDeprt(String dID, String year, String sem)
			throws SQLException {
		String sql;
		int semID = Integer.parseInt(sem);
		sql = "select t1.id,t1.name,t1.hours,t1.code,t2.code,t2.name,t1.stdyear,t3.semester,t1.credit, t1.status "
				+ "from subjects as t1 "
				+ "left join department as t2 on t1.dept_id=t2.id "
				+ "left join semester as t3 on t1.sem_id=t3.id "
				+ "where t2.id=? and t1.stdyear =? and t3.id = ? order by t1.name asc ;";

		q = new ArrayList<Subjects>();

		PreparedStatement stat = dbCon.prepareStatement(sql);
		stat.setInt(1, Integer.parseInt(dID));
		stat.setString(2, year);
		stat.setInt(3, semID);
		ResultSet result = stat.executeQuery();
		while (result.next()) {
			q.add(new Subjects(result.getInt("t1.id"), result
					.getString("t1.name"), result.getString("t1.hours"), result
					.getString("t1.code"), result.getString("t2.code"), result
					.getString("t2.name"), result.getString("t1.stdyear"),
					result.getString("t3.semester"), result
							.getString("t1.credit"), result.getInt("t1.status")));
		}
	}

	public void execSQL_byDeprtActive(String dID, String year, String sem)
			throws SQLException {
		String sql;
		sql = "select t1.id,t1.name,t1.hours,t1.code,t2.code,t2.name,t1.stdyear,t3.semester,t1.credit, t1.status "
				+ "from subjects as t1 "
				+ "left join department as t2 on t1.dept_id=t2.id "
				+ "left join semester as t3 on t1.sem_id=t3.id "
				+ "where t2.id=? and t1.stdyear =? and t3.id = ? and t1.status=1 order by t1.name asc ;";

		q = new ArrayList<Subjects>();

		PreparedStatement stat = dbCon.prepareStatement(sql);
		stat.setString(1, dID);
		stat.setString(2, year);
		stat.setString(3, sem);
		ResultSet result = stat.executeQuery();
		while (result.next()) {
			q.add(new Subjects(result.getInt("t1.id"), result
					.getString("t1.name"), result.getString("t1.hours"), result
					.getString("t1.code"), result.getString("t2.code"), result
					.getString("t2.name"), result.getString("t1.stdyear"),
					result.getString("t3.semester"), result
							.getString("t1.credit"), result.getInt("t1.status")));
		}
	}

	public void execSQL_byDeprtActive(String dID) throws SQLException {
		String sql;
		sql = "select t1.id,t1.name,t1.hours,t1.code,t2.code,t2.name,t1.stdyear,t3.semester,t1.credit, t1.status "
				+ "from subjects as t1 "
				+ "left join department as t2 on t1.dept_id=t2.id "
				+ "left join semester as t3 on t1.sem_id=t3.id "
				+ "where t2.id=? and t1.status=1 order by t1.stdyear,t3.semester,t1.name asc ;";

		q = new ArrayList<Subjects>();

		PreparedStatement stat = dbCon.prepareStatement(sql);
		stat.setString(1, dID);
		ResultSet result = stat.executeQuery();
		while (result.next()) {
			q.add(new Subjects(result.getInt("t1.id"), result
					.getString("t1.name"), result.getString("t1.hours"), result
					.getString("t1.code"), result.getString("t2.code"), result
					.getString("t2.name"), result.getString("t1.stdyear"),
					result.getString("t3.semester"), result
							.getString("t1.credit"), result.getInt("t1.status")));
		}
	}

	public void execSQL_subj(String subjID) throws SQLException {
		int sid = Integer.parseInt(subjID);
		String sql = "select t1.id,t1.name,t1.hours,t1.code,t2.code,t2.name,t1.stdyear,t3.semester,t1.credit, t1.status "
				+ "from subjects as t1 "
				+ "left join department as t2 on t1.dept_id=t2.id "
				+ "left join semester as t3 on t1.sem_id=t3.id "
				+ "where t1.id=? order by t1.name asc;";

		q = new ArrayList<Subjects>();

		PreparedStatement stat = dbCon.prepareStatement(sql);
		stat.setInt(1, sid);
		ResultSet result = stat.executeQuery();
		while (result.next()) {
			q.add(new Subjects(result.getInt("t1.id"), result
					.getString("t1.name"), result.getString("t1.hours"), result
					.getString("t1.code"), result.getString("t2.code"), result
					.getString("t2.name"), result.getString("t1.stdyear"),
					result.getString("t3.semester"), result
							.getString("t1.credit"), result.getInt("t1.status")));
		}
	}

	public void execSQL_subj_inst(String instID, String year)
			throws SQLException {
		String sql = "select t1.id,t1.name,t1.hours,t1.code,t1.credit,t1.stdyear,t1.dept_id,t2.code,t2.name,t3.semester, t5.id, t1.status from subjects as t1"
				+ " left join department as t2 on t1.dept_id=t2.id "
				+ " left join semester as t3 on t1.sem_id=t3.id"
				+ " left join subj_instructor as t5 on t5.subj_id=t1.id"
				+ " left join instructor as t4 on t4.id=t5.inst_id"
				+ " where t1.id=t5.subj_id and t4.id=? and t5.year_id=? order by t3.semester,t1.name asc";

		q = new ArrayList<Subjects>();

		PreparedStatement stat = dbCon.prepareStatement(sql);
		stat.setString(1, instID);
		stat.setString(2, year);
		ResultSet result = stat.executeQuery();
		while (result.next()) {
			q.add(new Subjects(result.getInt("t1.id"), result
					.getString("t1.name"), result.getString("t1.hours"), result
					.getString("t1.code"), result.getString("t2.code"), result
					.getString("t2.name"), result.getString("t1.stdyear"),
					result.getString("t3.semester"), result
							.getString("t1.credit"), result.getString("t5.id"),
					result.getInt("t1.status")));
		}
	}

	public void execSQL_subj_inst(String dept, String year, String sem,
			String rollnum, String year_id) throws SQLException {
		String sql = "select t1.id,t1.name,t1.hours,t1.code,t1.stdyear,t1.dept_id,t1.credit, t2.code, t2.name, t3.semester, "
				+ " t5.id, t1.status from subjects as t1"
				+ " left join department as t2 on t1.dept_id=t2.id"
				+ " left join semester as t3 on t1.sem_id=t3.id"
				+ " left join subj_instructor as t5 on t5.subj_id=t1.id"
				+ " left join instructor as t4 on t4.id=t5.inst_id "
				+ " where t4.rollnum=? "
				+ " and t1.sem_id=? and t1.stdyear=? and t1.dept_id=? and t5.year_id=? order by t3.semester,t1.name asc ;";

		q = new ArrayList<Subjects>();

		PreparedStatement stat = dbCon.prepareStatement(sql);
		stat.setString(1, rollnum);
		stat.setString(2, sem);
		stat.setString(3, year);
		stat.setString(4, dept);
		stat.setString(5, year_id);
		ResultSet result = stat.executeQuery();
		while (result.next()) {
			q.add(new Subjects(result.getInt("t1.id"), result
					.getString("t1.name"), result.getString("t1.hours"), result
					.getString("t1.code"), result.getString("t2.code"), result
					.getString("t2.name"), result.getString("t1.stdyear"),
					result.getString("t3.semester"), result
							.getString("t1.credit"), result.getString("t5.id"),
					result.getInt("t1.status")));
		}
	}
	
	public void execSQL_subj_inst_new(String year_id, String sem_id,
			String rollnum) throws SQLException {
		String sql = "select t1.id,t1.name,t1.hours,t1.code,t1.stdyear,t1.dept_id,t1.credit, t2.code, t2.name, t3.semester, "
				+ " t5.id, t1.status from subjects as t1"
				+ " left join department as t2 on t1.dept_id=t2.id"
				+ " left join semester as t3 on t1.sem_id=t3.id"
				+ " left join subj_instructor as t5 on t5.subj_id=t1.id"
				+ " left join instructor as t4 on t4.id=t5.inst_id "
				+ " where t4.rollnum=? and t1.sem_id=? and t5.year_id=? order by t1.name asc ;";

		q = new ArrayList<Subjects>();

		PreparedStatement stat = dbCon.prepareStatement(sql);
		stat.setString(1, rollnum);
		stat.setString(2, sem_id);
		stat.setString(3, year_id);
		ResultSet result = stat.executeQuery();
		while (result.next()) {
			q.add(new Subjects(result.getInt("t1.id"), result
					.getString("t1.name"), result.getString("t1.hours"), result
					.getString("t1.code"), result.getString("t2.code"), result
					.getString("t2.name"), result.getString("t1.stdyear"),
					result.getString("t3.semester"), result
							.getString("t1.credit"), result.getString("t5.id"),
					result.getInt("t1.status")));
		}
	}

	public void execSQL_NoTakenByStudent(String studentId, String semId) throws SQLException {
		String sql = "select t1.id,t1.code,t1.name,t1.stdyear,t1.hours,t1.credit,t1.status," +
				"semester.semester,department.code,department.name from subjects as t1 left join " +
				"semester on t1.sem_id=semester.id " +
				"left join department on t1.dept_id=department.id " +
				"left join student on student.dept_id=t1.dept_id where t1.id not in " +
				"(select less_stud.subject_id from less_stud where sem_id=? and student_id=?) " +
				"and t1.sem_id=? and t1.status=? and student.id=? order by t1.stdyear asc;";

		q = new ArrayList<Subjects>();

		PreparedStatement stat = dbCon.prepareStatement(sql);
		stat.setString(1, semId);
		stat.setString(2, studentId);
		stat.setString(3, semId);
		stat.setInt(4, 1);
		stat.setString(5,studentId);
		ResultSet result = stat.executeQuery();
		while (result.next()) {
			q.add(new Subjects(result.getInt("t1.id"), result
					.getString("t1.name"), result.getString("t1.hours"), result
					.getString("t1.code"), result.getString("department.code"), result
					.getString("department.name"), result.getString("t1.stdyear"),
					result.getString("semester.semester"), result.getString("t1.credit"),
					result.getInt("t1.status")));
		}
	}

	public void findSubject_byCode(String code) throws SQLException {
		String sql = "select t1.id,t1.name,t1.hours,t1.code,t2.code,t2.name,t1.stdyear,t3.semester,t1.credit, t1.status "
				+ "from subjects as t1 "
				+ "left join department as t2 on t1.dept_id=t2.id "
				+ "left join semester as t3 on t1.sem_id=t3.id "
				+ "where t1.code like ? order by t1.name";
		q = new ArrayList<Subjects>();

		PreparedStatement stat = dbCon.prepareStatement(sql);
		stat.setString(1, "%" + code + "%");
		ResultSet result = stat.executeQuery();
		while (result.next()) {
			q.add(new Subjects(result.getInt("t1.id"), result
					.getString("t1.name"), result.getString("t1.hours"), result
					.getString("t1.code"), result.getString("t2.code"), result
					.getString("t2.name"), result.getString("t1.stdyear"),
					result.getString("t3.semester"), result
							.getString("t1.credit"), result.getInt("t1.status")));
		}
	}

	public void execAddSubj(String subjName, String subjHrs, String subjCode,
			String department, String year, String semester, String credit,
			int status) throws SQLException {
		String sql = "insert into subjects(name,hours,code,dept_id,stdyear,sem_id,credit,status) values(?,?,?,?,?,?,?,?);";

		PreparedStatement stat = dbCon.prepareStatement(sql);
		stat.setString(1, subjName);
		stat.setString(2, subjHrs);
		stat.setString(3, subjCode);
		stat.setString(4, department);
		stat.setString(5, year);
		stat.setString(6, semester);
		stat.setString(7, credit);
		stat.setInt(8, status);
		stat.executeUpdate();
	}

	public void execUpdate(String subjName, String subjHrs, String subjCode,
			String depID, String yearID, String semID, String credit,
			String subjID, int status) throws SQLException {

		String sql = "update subjects set name =?, hours=?, code=?, dept_id =?, stdyear =?, sem_id=?, credit=?, status=? where id=? ;";
		PreparedStatement stat = dbCon.prepareStatement(sql);
		stat.setString(1, subjName);
		stat.setString(2, subjHrs);
		stat.setString(3, subjCode);
		stat.setString(4, depID);
		stat.setString(5, yearID);
		stat.setString(6, semID);
		stat.setString(7, credit);
		stat.setInt(8, status);
		stat.setString(9, subjID);
		stat.executeUpdate();
	}

	public void deleteSubject(String s_id) throws SQLException {
		String sql = "delete su, att, ls, se from subjects as su "
				+ "left join attendance as att on att.subject_id=su.id "
				+ "left join less_stud as ls on ls.subject_id=su.id "
				+ "left join subj_exam as se on se.stud_less_id=ls.id "
				+ "where su.id=?";
		PreparedStatement stat = dbCon.prepareStatement(sql);
		stat.setString(1, s_id);
		stat.executeUpdate();
	}

	public ArrayList<Subjects> getArray() {
		return q;
	}

}
