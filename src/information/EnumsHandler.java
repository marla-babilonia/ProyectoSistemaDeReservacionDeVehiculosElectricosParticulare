package information;
import information.AvailableStations.LOCATION;

public class EnumsHandler {
    
    // This method receives a string of the different stations and returns the enum
    public static LOCATION getLocation(String string) {

        LOCATION location = null;

        if (string.equalsIgnoreCase("edificio stefani")){
            location = LOCATION.EDIFICIO_STEFANI;
        } else if (string.equalsIgnoreCase("centro de estudiantes")){
            location = LOCATION.CENTRO_DE_ESTUDIANTES;
        } else if (string.equalsIgnoreCase("edificio de biologia")){
            location = LOCATION.EDIFICIO_DE_BIOLOGIA;
        } else if (string.equalsIgnoreCase("edificio ingenieria quimica")){
            location = LOCATION.EDIFICIO_INGENIERIA_QUIMICA;
        } else if (string.equalsIgnoreCase("edificio administracion de empresas")){
            location = LOCATION.EDIFICIO_DE_ADMINISTRACION_DE_EMPRESAS;
        }

        return location;
    }

    //This method receives the enum of different stations and returns a string
    public static String formatStationName(LOCATION station) {
        switch (station) {
            case EDIFICIO_STEFANI:
                return "Edificio Stefani";
            case CENTRO_DE_ESTUDIANTES:
                return "Centro de Estudiantes";
            case EDIFICIO_DE_BIOLOGIA:
                return "Edificio de Biología";
            case EDIFICIO_INGENIERIA_QUIMICA:
                return "Edificio de Ingeniería Química";
            case EDIFICIO_DE_ADMINISTRACION_DE_EMPRESAS:
                return "Edificio de Administración de Empresas";
            default:
                return station.name();
        }
    }
}
