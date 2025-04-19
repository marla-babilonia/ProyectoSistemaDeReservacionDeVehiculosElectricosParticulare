package information;

import java.util.ArrayList;
import java.util.List;


/* 
 * This class is made in a stadard way to do class,
 * we have the variables at the begining,
 * followed by the constroctor and the the setters and getters.
 */

public class Users {
    
    int studentid;
    String studentname;
    String studentemail;
    int studentphone;
    private OWNER_OR_CLIENT userType;
    public static List<Users> listaUsuarios = new ArrayList<>();

    /*
     * We use ENUMS in order to decide if the USER is a client, owner or both.
     */

    public static enum OWNER_OR_CLIENT {
        CLIENT,
        OWNER,
        OWNERANDCLIENT,
    }

    public Users(String studentname, int studentid, String studentemail, int studentphone, OWNER_OR_CLIENT userType) {
        this.studentname = studentname;
        this.studentid = studentid;
        this.studentemail = studentemail;
        this.studentphone = studentphone;
        this.userType = userType;
        
    }

    // Setters and Getters.

    public static List<Users> getListaUsuarios() {
        return listaUsuarios;
    }

    public String getstudentname() {
        return studentname;
    }

    public void setstudentname(String studentname) {
        this.studentname = studentname;
    }

    public int getstudentid() {
        return studentid;
    }

    public void setstudentid(int studentid) {
        this.studentid = studentid;
    }

    public String getstudentemail() {
        return studentemail;
    }

    public void setstudentemail(String studentemail) {
        this.studentemail = studentemail;
    }

    public int getstudentphone() {
        return studentphone;
    }

    public void setstudentphone(int studentphone) {
        this.studentphone = studentphone;
    }

    public OWNER_OR_CLIENT getTipo() {
        return userType;
    }

    public void setTipo(OWNER_OR_CLIENT userType) {
        this.userType = userType;
    }


}
