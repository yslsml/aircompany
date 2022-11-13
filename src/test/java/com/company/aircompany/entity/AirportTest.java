package com.company.aircompany.entity;

import com.company.aircompany.airport.Airport;
import com.company.aircompany.classification.ClassificationLevel;
import com.company.aircompany.classification.ExperimentalType;
import com.company.aircompany.classification.MilitaryType;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class AirportTest {
    private static Airport airport;
    private static Airport airportWithPassengerPlanes;
    private static Airport airportWithMilitaryPlanes;
    private static Airport airportWithExperimentalPlanes;
    private static final List<AbstractPlane> planes = Arrays.asList(
            new PassengerPlane("Boeing-737", 900, 12000, 60500, 164),
            new PassengerPlane("Boeing-737-800", 940, 12300, 63870, 192),
            new PassengerPlane("Boeing-747", 980, 16100, 70500, 242),
            new PassengerPlane("Airbus A320", 930, 11800, 65500, 188),
            new PassengerPlane("Airbus A330", 990, 14800, 80500, 222),
            new PassengerPlane("Embraer 190", 870, 8100, 30800, 64),
            new PassengerPlane("Sukhoi Superjet 100", 870, 11500, 50500, 140),
            new PassengerPlane("Bombardier CS300", 920, 11000, 60700, 196),
            new MilitaryPlane("B-1B Lancer", 1050, 21000, 80000, MilitaryType.BOMBER),
            new MilitaryPlane("B-2 Spirit", 1030, 22000, 70000, MilitaryType.BOMBER),
            new MilitaryPlane("B-52 Stratofortress", 1000, 20000, 80000, MilitaryType.BOMBER),
            new MilitaryPlane("F-15", 1500, 12000, 10000, MilitaryType.FIGHTER),
            new MilitaryPlane("F-22", 1550, 13000, 11000, MilitaryType.FIGHTER),
            new MilitaryPlane("C-130 Hercules", 650, 5000, 110000, MilitaryType.TRANSPORT),
            new ExperimentalPlane("Bell X-14", 277, 482, 500, ExperimentalType.HIGH_ALTITUDE, ClassificationLevel.SECRET),
            new ExperimentalPlane("Ryan X-13 Vertijet", 560, 307, 500, ExperimentalType.VTOL, ClassificationLevel.TOP_SECRET)
    );
    private static final List<AbstractPlane> planesToSort = Arrays.asList(
            new PassengerPlane("Sukhoi Superjet 100", 870, 11500, 50500, 140),
            new PassengerPlane("Bombardier CS300", 920, 11000, 60700, 196),
            new MilitaryPlane("B-1B Lancer", 1050, 21000, 80000, MilitaryType.BOMBER),
            new MilitaryPlane("B-2 Spirit", 1030, 22000, 70000, MilitaryType.BOMBER),
            new ExperimentalPlane("Bell X-14", 277, 482, 500, ExperimentalType.HIGH_ALTITUDE, ClassificationLevel.SECRET)
    );

    private static List<AbstractPlane> planesSortedByMaxSpeed = Arrays.asList(
            new ExperimentalPlane("Bell X-14", 277, 482, 500, ExperimentalType.HIGH_ALTITUDE, ClassificationLevel.SECRET),
            new PassengerPlane("Sukhoi Superjet 100", 870, 11500, 50500, 140),
            new PassengerPlane("Bombardier CS300", 920, 11000, 60700, 196),
            new MilitaryPlane("B-2 Spirit", 1030, 22000, 70000, MilitaryType.BOMBER),
            new MilitaryPlane("B-1B Lancer", 1050, 21000, 80000, MilitaryType.BOMBER)
    );

    private static List<AbstractPlane> planesSortedByMaxFlightDistance = Arrays.asList(
            new ExperimentalPlane("Bell X-14", 277, 482, 500, ExperimentalType.HIGH_ALTITUDE, ClassificationLevel.SECRET),
            new PassengerPlane("Bombardier CS300", 920, 11000, 60700, 196),
            new PassengerPlane("Sukhoi Superjet 100", 870, 11500, 50500, 140),
            new MilitaryPlane("B-1B Lancer", 1050, 21000, 80000, MilitaryType.BOMBER),
            new MilitaryPlane("B-2 Spirit", 1030, 22000, 70000, MilitaryType.BOMBER)
    );

    private static List<AbstractPlane> planesSortedByMaxLoadCapacity = Arrays.asList(
            new ExperimentalPlane("Bell X-14", 277, 482, 500, ExperimentalType.HIGH_ALTITUDE, ClassificationLevel.SECRET),
            new PassengerPlane("Sukhoi Superjet 100", 870, 11500, 50500, 140),
            new PassengerPlane("Bombardier CS300", 920, 11000, 60700, 196),
            new MilitaryPlane("B-2 Spirit", 1030, 22000, 70000, MilitaryType.BOMBER),
            new MilitaryPlane("B-1B Lancer", 1050, 21000, 80000, MilitaryType.BOMBER)
    );

    private static PassengerPlane planeWithMaxPassengerCapacity = new PassengerPlane("Boeing-747", 980, 16100, 70500, 242);

    @BeforeClass
    public void initAirport() {
        airport = new Airport(planes);
        airportWithPassengerPlanes = new Airport(planes.subList(0, 8));
        airportWithMilitaryPlanes = new Airport(planes.subList(8, 14));
        airportWithExperimentalPlanes = new Airport(planes.subList(14, 16));
    }

    @DataProvider(name = "militaryTypeAndExpectedCountProvider")
    public Object[][] militaryTypeAndExpectedCount() {
        return new Object[][] {
                {MilitaryType.BOMBER, 3},
                {MilitaryType.FIGHTER, 2},
                {MilitaryType.TRANSPORT, 1},
                {null, 0}
        };
    }

    @Test(dataProvider = "militaryTypeAndExpectedCountProvider")
    public void testGetMilitaryPlanesByType(MilitaryType type, int expectedCount) {
        List<MilitaryPlane> militaryPlanes = airport.getMilitaryPlanesByType(type);
        int actualCount = militaryPlanes.size();
        Assert.assertEquals(actualCount, expectedCount);
    }

    @Test
    public void testGetPassengerPlanes() {
        List<PassengerPlane> passengerPlanes = airport.getPassengerPlanes();
        Assert.assertEquals(passengerPlanes, airportWithPassengerPlanes.getPlanes());
    }

    @Test
    public void testGetMilitaryPlanes() {
        List<MilitaryPlane> militaryPlanes = airport.getMilitaryPlanes();
        Assert.assertEquals(militaryPlanes, airportWithMilitaryPlanes.getPlanes());
    }

    @Test
    public void testGetExperimentalPlanes() {
        List<ExperimentalPlane> experimentalPlanes = airport.getExperimentalPlanes();
        Assert.assertEquals(experimentalPlanes, airportWithExperimentalPlanes.getPlanes());
    }

    @Test
    public void testGetPassengerPlaneWithMaxCapacity() {
        Airport airport = new Airport(planes);
        PassengerPlane expectedPlaneWithMaxPassengersCapacity = airport.getPassengerPlaneWithMaxPassengersCapacity().get();
        Assert.assertEquals(planeWithMaxPassengerCapacity, expectedPlaneWithMaxPassengersCapacity);
    }

    @Test
    public void testSortByMaxDistance() {
        Airport airport = new Airport(planesToSort);
        airport.sortByMaxFlightDistance();
        Assert.assertEquals(airport.getPlanes(), planesSortedByMaxFlightDistance);
    }

    @Test
    public void testSortByMaxSpeed() {
        Airport airport = new Airport(planesToSort);
        airport.sortByMaxSpeed();
        Assert.assertEquals(airport.getPlanes(), planesSortedByMaxSpeed);
    }

    @Test
    public void testSortByMaxLoadCapacity() {
        Airport airport = new Airport(planesToSort);
        airport.sortByMaxLoadCapacity();
        Assert.assertEquals(airport.getPlanes(), planesSortedByMaxLoadCapacity);
    }
}
