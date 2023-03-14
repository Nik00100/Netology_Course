package ru.netology.sender;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MessageSenderImplTest {

    @ParameterizedTest
    @CsvSource({
            "172.123.12.19, Moscow, RUSSIA, Добро пожаловать",
            "96.1.1.1, New York, USA, Welcome"
    })
    void send(String ip, String city, String country, String out){
        GeoService geoService = Mockito.mock(GeoServiceImpl.class);
        LocalizationService localizationService = Mockito.mock(LocalizationServiceImpl.class);
        MessageSender messageSender = new MessageSenderImpl(geoService,localizationService);

        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, ip);

        Mockito.when(geoService.byIp(ip)).thenReturn(new Location(city, Country.valueOf(country), null, 0));
        Mockito.when(localizationService.locale(Country.valueOf(country))).thenReturn(out);

        assertEquals(out,messageSender.send(headers));
    }


}