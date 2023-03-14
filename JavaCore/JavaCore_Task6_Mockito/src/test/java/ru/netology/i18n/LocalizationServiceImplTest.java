package ru.netology.i18n;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import ru.netology.entity.Country;

import static org.junit.jupiter.api.Assertions.*;

class LocalizationServiceImplTest {

    @ParameterizedTest
    @CsvSource({
            "RUSSIA, Добро пожаловать",
            "USA, Welcome"
    })
    void locale(String country,String out) {
        LocalizationService localizationService = new LocalizationServiceImpl();
        assertEquals(localizationService.locale(Country.valueOf(country)),out);
    }
}