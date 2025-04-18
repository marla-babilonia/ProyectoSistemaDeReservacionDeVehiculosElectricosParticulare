package information;


/* 
 * This class is made in a stadard way to do class,
 * we have the variables at the begining,
 * followed by the constroctor and the the setters and getters.
 */

public class Vehicles {
    int id;
    String tipo;
    String descripcion;
    int horario;
    String lugar;
    Boolean Disponible;


    public void Vehiculo(int id, String tipo, String descripcion, int horario, String lugar, Boolean Disponible) {
        this.id = id;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.horario = horario;
        this.lugar = lugar;
        this.Disponible = Disponible;
    }

    // Setters and Getters.

    public int getID() {
        return id;
    }

    public void setID(int ID) {
        this.id = ID;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getHorario() {
        return horario;
    }

    public void setHorario(int horario) {
        this.horario = horario;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }
    public Boolean getDisponible() {
        return Disponible;
    }

    public void setDisponible(Boolean Disponible) {
        this.Disponible = Disponible;
    }
}
