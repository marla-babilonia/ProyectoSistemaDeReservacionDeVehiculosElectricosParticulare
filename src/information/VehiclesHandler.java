package information;

import java.util.HashSet;
import java.util.Set;

public class VehiclesHandler {
  protected HashSet<Vehicles> availableVehicles = new HashSet<>();
    protected HashSet<Vehicles> allVehicles = new HashSet<>();
  
    // Agregar vehículos iniciales si están disponibles
    public void AvailableVehicles(Set<Vehicles> allVehicles) {
      for (Vehicles v : allVehicles) {
          if (v.getavailable()) {
              availableVehicles.add(v);
          }
      }
  }

  // Actualizar disponibilidad de un vehículo
  public void updateAvailability(Vehicles vehicle) {
      if (vehicle.getavailable()) {
          availableVehicles.add(vehicle);
      } else {
          availableVehicles.remove(vehicle);
      }
  }

  // Buscar vehículo por ID
  public Vehicles findById(int id) {
      for (Vehicles v : availableVehicles) {
          if (v.getID() == id) {
              return v;
          }
      }
      return null;
  }

  // Editar vehículo (por ejemplo, cambiar tipo o descripción)
  public boolean editVehicle(int id, Vehicles.VEHICLE_TYPE Type, String Description) {
      Vehicles v = findById(id);
      if (v != null) {
          v.setVehicleType(Type);
          v.setdescription(Description);
          return true;
      }
      return false;
  }

  // Remover vehículo manualmente
  public boolean removeVehicle(int id) {
      Vehicles exists = findById(id);
      if (exists != null) {
          availableVehicles.remove(exists);
          return true;
      }
      return false;
  }

  // Obtener todos los vehículos disponibles
  public Set<Vehicles> getAvailableVehicles() {
      return availableVehicles;
  }
  //metodo para crear set de available vehicles
// editar el set available method para que si es falso quitarlo de la lista y si es cierto anadirlo a la lista

//para marla - buscar manera de editar, remover, y almacenar los vehiculos respondientes  
}
