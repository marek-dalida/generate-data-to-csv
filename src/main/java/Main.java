import com.github.javafaker.Faker;

import java.io.IOException;



public class Main {
    public static void main(String[] args) throws IOException {
        Faker faker = new Faker();


        StagingAreaHelper.generateStationsUK(faker);
        //StagingAreaHelper.generateLocationsUK(faker);
        //UkAirHelper.generateMeasures(faker);
        //UkAirHelper.generateStations(faker);
        //UkAirHelper.generateLocations(faker);
    }

}
