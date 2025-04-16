package information;

import java.util.ArrayList;
import java.util.List;

public class Users {
    
    int studentid;
    String studentname;
    String studentemail;
    int studentphone;
    public static CD tipo;
    public static List<Users> listaUsuarios = new ArrayList<>();

    public static enum CD {
        CLIENT,
        OWNER,
        OWNERANDCLIENT,
    }

    public void User(String studentname, int studentid, String studentemail, int studentphone, CD tipo) {
        this.studentname = studentname;
        this.studentid = studentid;
        this.studentemail = studentemail;
        this.studentphone = studentphone;
        this.tipo = tipo;
        listaUsuarios.add(this);
    }

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

    public CD getTipo() {
        return tipo;
    }

    public void setTipo(CD tipo) {
        this.tipo = tipo;
    }

    void debug_enter_USER() {
        System.out.println("you have entered as a USER");

    }


}
