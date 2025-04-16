package information;

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
    public int getID() {
        return id;
    }

    public void setID(int ID) {
        this.id = id;
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
