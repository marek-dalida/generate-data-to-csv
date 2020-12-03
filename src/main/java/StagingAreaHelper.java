import com.github.javafaker.Faker;
import org.joda.time.DateTime;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

public class StagingAreaHelper {

    //Stations ID
    //UK 1- 75
    //Polska 126-200
    //US 201-300

    public static void generateLocationsUK(Faker faker) throws IOException {

        FileWriter csvWriter = new FileWriter("locationsUK.csv");

        csvWriter.append("LOCATION_NAME");
        csvWriter.append(";");
        csvWriter.append("REGION_ID");
        csvWriter.append(";");
        csvWriter.append("LONGITUDE");
        csvWriter.append(";");
        csvWriter.append("LATITUDE");
        csvWriter.append("\n");

        for (int i = 1; i <= 75; i++) {
            String locationName = faker.address().cityName();
            Integer regionId = faker.random().nextInt(1, 4);
            String longitude = faker.address().longitude();
            longitude.replace('.', ',');
            String latitude = faker.address().latitude();
            latitude.replace('.', ',');
            csvWriter.append( locationName + ";" + regionId + ";" + longitude + ";" + latitude);
            csvWriter.append("\n");
        }
        csvWriter.flush();
        csvWriter.close();
    }


    public static void generateStationsUK(Faker faker) throws IOException {
        FileWriter csvWriter = new FileWriter("stationsUK.csv");

        String[] stationTypeOptions = {"urban", "suburban", "rural"};

        csvWriter.append("STATION_NAME");
        csvWriter.append(";");
        csvWriter.append("STATION_TYPE");
        csvWriter.append("\n");

        for (int i = 1; i <= 75; i++) {
            String stationName = "MS " + faker.address().cityName();
            String stationType = stationTypeOptions[faker.random().nextInt(stationTypeOptions.length)];
            csvWriter.append( stationName + ";" + stationType);
            csvWriter.append("\n");
        }

        csvWriter.flush();
        csvWriter.close();
    }


    public static void generateMeasures(Faker faker) throws IOException {

        Map<Integer, Integer> substanceLimit = new HashMap<Integer, Integer>() {{
            put(1, 160);
            put(2, 400);
            put(3, 532);
            put(4, 53);
            put(5, 75);
        }};

        int month = 1;

        FileWriter csvWriter = new FileWriter("measures.csv");

        csvWriter.append("MEASURE_TIMESTAMP;STATION_ID;SUBSTANCE_ID;MEASURE_VALUE");
        csvWriter.append("\n");


        DateTime startDate = new DateTime(2018, 1, 1, 0, 0);

        DateTime endDate = new DateTime(2019, 1, 1, 0, 0);
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

}
