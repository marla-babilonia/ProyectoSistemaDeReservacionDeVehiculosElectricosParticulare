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
    public static OC tipo;
    public static List<Users> listaUsuarios = new ArrayList<>();

    /*
     * We use ENUMS in order to decide if the USER is a client, owner or both.
     */

    public static enum OC {
        CLIENT,
        OWNER,
        OWNERANDCLIENT,
    }

    public void User(String studentname, int studentid, String studentemail, int studentphone, OC tipo) {
        this.studentname = studentname;
        this.studentid = studentid;
        this.studentemail = studentemail;
        this.studentphone = studentphone;
        this.tipo = tipo;
        listaUsuarios.add(this);
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

    public OC getTipo() {
        return tipo;
    }

    public void setTipo(OC tipo) {
        this.tipo = tipo;
    }


}
