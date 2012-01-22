package kg.cloud.uims.domain;

import java.io.Serializable;

/**
 * @author focus
 */
public class Users implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String user_name;
    String user_pass;
    String role_name;
    int status;
    int sum;

    public Users(String role,int st, int sum) {
        this.role_name = role;
        this.sum = sum;
        this.status = st;
    }

    public Users(String un, String up, String rn, int st) {
        this.user_name = un;
        this.user_pass = up;
        this.role_name = rn;
        this.status = st;
    }

    public String getUserName() {
        return user_name;
    }

    public String getUserPass() {
        return user_pass;
    }

    public String getRoleName() {
        return role_name;
    }

    public int getUserStatus() {
        return status;
    }

    public int getRoleSum(){
        return sum;
    }
}