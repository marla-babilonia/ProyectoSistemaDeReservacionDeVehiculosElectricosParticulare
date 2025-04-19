package information;
import information.AvailableStations.LOCATION;

public class EnumsHandler {
    
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
}
