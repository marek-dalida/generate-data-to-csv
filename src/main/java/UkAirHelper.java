import com.github.javafaker.Faker;
import org.joda.time.DateTime;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

public class UkAirHelper {

    public static void generateMeasures(Faker faker) throws IOException {

        Map<Integer, Integer> substanceLimit = new HashMap<Integer, Integer>() {{
            put(1, 160);
            put(2, 400);
            put(3, 532);
            put(4, 53);
            put(5, 75);
        }};

        int month = 1;

        FileWriter csvWriter = new FileWriter("measures_01.csv");

        csvWriter.append("MEASURE_TIMESTAMP;STATION_ID;SUBSTANCE_ID;MEASURE_VALUE");
        csvWriter.append("\n");


        DateTime startDate = new DateTime(2018, month, 1, 0, 0);

        DateTime endDate = new DateTime(2018, month + 1, 1, 0, 0);
        for (; startDate.toLocalDateTime().isBefore(endDate.toLocalDateTime()); startDate = startDate.plusHours(1)) {
            Date date = Date.valueOf(startDate.toString());
            for (int stationId = 1; stationId <= 75; stationId++) {
                for (int substanceId = 1; substanceId <= 5; substanceId++) {
                    Integer measureValue = faker.number().numberBetween(0, substanceLimit.get(substanceId));
                    csvWriter.append(date + ";" + stationId + ";" + substanceId + ";" + measureValue);
                    csvWriter.append("\n");
                }
            }
            csvWriter.flush();
        }

        csvWriter.close();

    }


    public static void generateStations(Faker faker) throws IOException {


        FileWriter csvWriter = new FileWriter("stations.csv");

        csvWriter.append("Station_Id,Station_Name,Location_Id");
        csvWriter.append("\n");

        String[] locationTypeOptions = {"urban", "suburban", "rural"};

        for (int i = 1; i <= 75; i++) {

            String stationName = "Station " + faker.address().streetName();
            csvWriter.append("999," + stationName + "," + i);
            csvWriter.append("\n");
        }

        csvWriter.flush();
        csvWriter.close();

    }

    public static void generateLocations(Faker faker) throws IOException {
        String[] coutryOptions = {"England", "Northern Ireland", "Scotland", "Wales"};
        String[] locationTypeOptions = {"urban", "suburban", "rural"};

        FileWriter csvWriter = new FileWriter("locations.csv");

        csvWriter.append("Location_Id");
        csvWriter.append(",");
        csvWriter.append("Country");
        csvWriter.append(",");
        csvWriter.append("County");
        csvWriter.append(",");
        csvWriter.append("Location_Type");
        csvWriter.append(",");
        csvWriter.append("Address");
        csvWriter.append(",");
        csvWriter.append("Zip_Code");
        csvWriter.append("\n");

        for (int i = 0; i < 1000; i++) {

            String address = faker.address().streetAddress();
            String zipCode = faker.address().zipCode();
            String county = faker.address().cityName();
            String country = coutryOptions[faker.random().nextInt(coutryOptions.length)];
            String locationType = locationTypeOptions[faker.random().nextInt(locationTypeOptions.length)];
            csvWriter.append("999," + country + "," + county + "," +
                    locationType + "," + address + "," + zipCode);
            csvWriter.append("\n");
        }

        csvWriter.flush();
        csvWriter.close();
    }
}

