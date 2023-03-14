package ru.netology.geo;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

import static org.junit.jupiter.api.Assertions.*;

class GeoServiceImplTest {

    @ParameterizedTest
    @CsvSource({
            "172.123.12.19, Moscow, RUSSIA",
            "96.1.1.1, New York, USA"
    })
    void byIp(String ip, String city, String country) {
        GeoService geoService = new GeoServiceImpl();

        assertEquals(geoService.byIp(ip).toString(),
                new Location(city, Country.valueOf(country), null,  0).toString());
    }
}