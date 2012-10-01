/*
 * Copyright 2009 IT Mill Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package kg.cloud.uims;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Locale;
import java.util.ResourceBundle;

import kg.cloud.uims.dao.BaseDb;
import kg.cloud.uims.domain.Exam;
import kg.cloud.uims.domain.Semester;
import kg.cloud.uims.domain.Week;
import kg.cloud.uims.domain.Year;
import kg.cloud.uims.i18n.UimsMessages;
import kg.cloud.uims.ui.ViewManager;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

import com.vaadin.Application;
import com.vaadin.service.ApplicationContext;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Window;
import kg.cloud.uims.domain.Student;

/**
 * The Application's "main" class
 */
@SuppressWarnings("serial")
public class MyVaadinApplication extends Application implements
        ApplicationContext.TransactionListener {

    private static ThreadLocal<MyVaadinApplication> currentApplication = new ThreadLocal<MyVaadinApplication>();
    private Window window;
    private Semester currentSemester;
    private Year currentYear;
    private Week currentWeek;
    private Exam currentExam;
    private Student student;
    private int facultyId;
    private int departmentId;
    private int groupId;
    private String name;
    private String surname;
    private int userStatus;
    private ResourceBundle i18nBundle;
    ViewManager viewManager;

    @Override
    public void init() {
        setTheme("good_cham");
        final ResourceBundle i18n = ResourceBundle.getBundle(
                UimsMessages.class.getName(), getLocale());
        this.getContext().addTransactionListener(this);
        this.window = new Window(i18n.getString(UimsMessages.AppTitle));
        this.setMainWindow(window);
        viewManager = new ViewManager(window);
        viewManager.switchScreen(LoginScreen.class.getName(), new LoginScreen(
                this));
    }

    public void logout() {
        getMainWindow().getApplication().close();

        Subject currentUser = SecurityUtils.getSubject();

        if (currentUser.isAuthenticated()) {
            currentUser.logout();
        }
    }

    public void transactionStart(Application application, Object transactionData) {
        if (application == MyVaadinApplication.this) {
            MyVaadinApplication.currentApplication.set(this);
        }
    }

    public void transactionEnd(Application application, Object transactionData) {
        if (application == MyVaadinApplication.this) {
            MyVaadinApplication.currentApplication.set(null);

            MyVaadinApplication.currentApplication.remove();
        }
    }

    public Window getMainWindow() {
        return window;
    }

    public static MyVaadinApplication getInstance() {
        return MyVaadinApplication.currentApplication.get();
    }

    public void login(String username, String password) {
        UsernamePasswordToken token;

        token = new UsernamePasswordToken(username, password);
        // ”Remember Me” built-in, just do this:
        token.setRememberMe(true);

        // With most of Shiro, you'll always want to make sure you're working
        // with the currently executing user,
        // referred to as the subject
        Subject currentUser = SecurityUtils.getSubject();

        // Authenticate
        currentUser.login(token);

    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public static class LogoutListener implements Button.ClickListener {

        private static final long serialVersionUID = 1L;
        private MyVaadinApplication app;

        public LogoutListener(MyVaadinApplication app) {
            this.app = app;
        }

        public void buttonClick(ClickEvent event) {
            this.app.logout();
        }
    }

    public void workingDetails(String username) throws Exception {
        String query = "select y.id,y.year,s.id,s.semester,s.registration_status,w.id,"
                + " w.week,e.exam_id,e.exam_name,"
                + "e.percentage,inst.faculty_id,inst.dept_id,inst.group_id, "
                + "inst.name, inst.surname, u.status from year as y,"
                + "semester as s,weeks as w,exam as e, instructor as inst, "
                + "users as u where inst.rollnum=u.user_name and y.curr=? and "
                + "s.curr=? and w.curr=? and e.curr=? and u.user_name=?";

        BaseDb base = new BaseDb();
        Connection conn = base.getConnection();
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1, 1);
        statement.setInt(2, 1);
        statement.setInt(3, 1);
        statement.setInt(4, 1);
        statement.setString(5, username);
        ResultSet result = statement.executeQuery();
        currentYear = new Year();
        currentSemester = new Semester();
        currentWeek = new Week();
        currentExam = new Exam();

        while (result.next()) {
            currentYear.setId(result.getInt("y.id"));
            currentYear.setYear(result.getString("y.year"));
            currentYear.setCurrent(1);
            currentSemester.setId(result.getInt("s.id"));
            currentSemester.setSemester(result.getString("s.semester"));
            currentSemester.setCurrent(1);
            currentSemester.setRegStatus(result.getInt("s.registration_status"));
            currentWeek.setId(result.getInt("w.id"));
            currentWeek.setWeek(result.getString("w.week"));
            currentWeek.setCurrent(1);
            currentExam.setId(result.getInt("e.exam_id"));
            currentExam.setExam(result.getString("e.exam_name"));
            currentExam.setPercentage(result.getInt("e.percentage"));
            currentExam.setCurrent(1);
            facultyId = result.getInt("inst.faculty_id");
            departmentId = result.getInt("inst.dept_id");
            groupId = result.getInt("inst.group_id");
            userStatus = result.getInt("u.status");
            name = result.getString("inst.name");
            surname = result.getString("inst.surname");
        }
        base.close();
    }

    public void studWorkingDetails(String username) throws Exception {
        String query = "select y.id, y.year, s.id, s.semester, "
                + "s.registration_status,w.id, w.week, e.exam_id, "
                + "e.exam_name, e.percentage, stud.id, stud.dept_id, "
                + "stud.group_id, stud.name, stud.surname, u.status, "
                + "acc.reg_status, acc.mid_status, acc.fin_status, "
                + "reg.status, edu.name, dep.faculty_id "
                + "from year as y, semester as s, weeks as w, exam as e, "
                + "users as u, student as stud "
                + "left join stud_accounting as acc on stud.id=acc.student_id "
                + "left join stud_reg as reg on stud.id=reg.student_id "
                + "left join education as edu on stud.edu_status_id=edu.id "
                + "left join department as dep on stud.dept_id=dep.id "
                + "where stud.rollnum=u.user_name and y.curr=? and "
                + "s.curr=? and w.curr=? and e.curr=? and u.user_name=? "
                + "and acc.year_id=y.id and acc.sem_id=s.id and reg.year_id=y.id "
                + "and reg.sem_id=s.id";

        BaseDb base = new BaseDb();
        Connection conn = base.getConnection();
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1, 1);
        statement.setInt(2, 1);
        statement.setInt(3, 1);
        statement.setInt(4, 1);
        statement.setString(5, username);
        ResultSet result = statement.executeQuery();
        currentYear = new Year();
        currentSemester = new Semester();
        currentWeek = new Week();
        currentExam = new Exam();

        while (result.next()) {
            currentYear.setId(result.getInt("y.id"));
            currentYear.setYear(result.getString("y.year"));
            currentYear.setCurrent(1);
            currentSemester.setId(result.getInt("s.id"));
            currentSemester.setSemester(result.getString("s.semester"));
            currentSemester.setCurrent(1);
            currentSemester.setRegStatus(result.getInt("s.registration_status"));
            currentWeek.setId(result.getInt("w.id"));
            currentWeek.setWeek(result.getString("w.week"));
            currentWeek.setCurrent(1);
            currentExam.setId(result.getInt("e.exam_id"));
            currentExam.setExam(result.getString("e.exam_name"));
            currentExam.setPercentage(result.getInt("e.percentage"));
            currentExam.setCurrent(1);
            facultyId = result.getInt("dep.faculty_id");
            departmentId = result.getInt("stud.dept_id");
            groupId = result.getInt("stud.group_id");
            userStatus = result.getInt("u.status");
            student=new Student(result.getInt("stud.id"),
                    result.getString("stud.name"), result.getString("stud.surname"),
                    result.getString("edu.name"), result.getInt("reg.status"), 
                    result.getInt("acc.reg_status"), result.getInt("acc.mid_status"), 
                    result.getInt("acc.fin_status"));
        }
        base.close();
    }

    public Semester getCurrentSemester() {
        return currentSemester;
    }

    public Year getCurrentYear() {
        return currentYear;

    }

    public Exam getCurrentExam() {
        return currentExam;
    }

    public Week getCurrentWeek() {

        return currentWeek;
    }

    public void setLocale(Locale locale) {
        super.setLocale(locale);
        i18nBundle = ResourceBundle.getBundle(UimsMessages.class.getName(),
                getLocale());
    }

    public ResourceBundle getBundle() {

        return i18nBundle;
    }

    public String getMessage(String key) {

        return i18nBundle.getString(key);
    }

    public ViewManager getViewManager() {
        return viewManager;
    }

    public int getFacultyId() {
        return facultyId;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public int getGroupId() {
        return groupId;
    }

    public String getName() {

        return name;
    }

    public String getSurname() {

        return surname;
    }

    public int getuserStatus() {

        return userStatus;
    }
}
