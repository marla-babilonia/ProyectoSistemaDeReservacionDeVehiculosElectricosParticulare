import information.AvailableStations;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.util.ArrayList;
import java.util.List;

public class CSVLoader {

    public static List<AvailableStations> loadStations() {
        List<AvailableStations> stations = new ArrayList<>();

        try {
            InputStream inputStream = CSVLoader.class.getResourceAsStream("/stations.csv");
            if (inputStream == null) {
                throw new RuntimeException("File not found: /stations.csv");
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String name = parts[0];
                    int maxCapacity = Integer.parseInt(parts[1]);
                    int currentCapacity = Integer.parseInt(parts[2]);

                    stations.add(new AvailableStations(name, maxCapacity, currentCapacity));
                }
            }

            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return stations;
    }
}
