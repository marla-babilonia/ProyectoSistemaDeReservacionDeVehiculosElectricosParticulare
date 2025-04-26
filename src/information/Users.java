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

    static void populateUsers() {
        listaUsuarios.add(new Users("John Doe", 1001, "john.doe@university.edu", 8011, OWNER_OR_CLIENT.CLIENT));
        listaUsuarios.add(new Users("Jane Smith", 1002, "jane.smith@university.edu", 8012, OWNER_OR_CLIENT.OWNER));
        listaUsuarios.add(new Users("Robert Johnson", 1003, "robert.j@university.edu", 8013, OWNER_OR_CLIENT.OWNERANDCLIENT));
        listaUsuarios.add(new Users("Emily Davis", 1004, "emily.d@university.edu", 8014, OWNER_OR_CLIENT.CLIENT));
        listaUsuarios.add(new Users("Michael Wilson", 1005, "michael.w@university.edu", 8015, OWNER_OR_CLIENT.OWNER));
        listaUsuarios.add(new Users("Sarah Brown", 1006, "sarah.b@university.edu", 8016, OWNER_OR_CLIENT.CLIENT));
        listaUsuarios.add(new Users("David Miller", 1007, "david.m@university.edu", 8017, OWNER_OR_CLIENT.OWNERANDCLIENT));
        listaUsuarios.add(new Users("Jennifer Lee", 1008, "jennifer.l@university.edu", 8018, OWNER_OR_CLIENT.CLIENT));
        listaUsuarios.add(new Users("Christopher Garcia", 1009, "chris.g@university.edu", 8019, OWNER_OR_CLIENT.OWNER));
        listaUsuarios.add(new Users("Amanda Taylor", 1010, "amanda.t@university.edu", 80110, OWNER_OR_CLIENT.CLIENT));
        listaUsuarios.add(new Users("Admin User", 1011, "admin@university.edu", 80111, OWNER_OR_CLIENT.OWNER));
        listaUsuarios.add(new Users("Operator User", 1012, "operator@university.edu", 80113, OWNER_OR_CLIENT.OWNERANDCLIENT));
    }
//add users to arrary 

    
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
