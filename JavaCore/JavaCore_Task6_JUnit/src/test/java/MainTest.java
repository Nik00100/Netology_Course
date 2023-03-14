import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void parseCSV() {
        String[] columnMapping = {"id", "firstName", "lastName", "country", "age"};
        String fileNameCsv = "src/main/resources/data.csv";
        List<Employee> listExpected = Main.parseCSV(columnMapping, fileNameCsv);

        List<Employee> listActual = new ArrayList<>();
        Employee smith = new Employee(1, "John","Smith","USA",25);
        Employee petrov = new Employee(2, "Ivan","Petrov","RU",23);
        listActual.add(smith);
        listActual.add(petrov);

        assertEquals(listExpected.toString(),listActual.toString());
    }

    @Test
    void listToJson() {
        List<Employee> listActual = new ArrayList<>();
        Employee smith = new Employee(1, "John","Smith","USA",25);
        Employee petrov = new Employee(2, "Ivan","Petrov","RU",23);
        listActual.add(smith);
        listActual.add(petrov);

        String jsonString = "[\n" +
                "  {\n" +
                "    \"id\": 1,\n" +
                "    \"firstName\": \"John\",\n" +
                "    \"lastName\": \"Smith\",\n" +
                "    \"country\": \"USA\",\n" +
                "    \"age\": 25\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": 2,\n" +
                "    \"firstName\": \"Ivan\",\n" +
                "    \"lastName\": \"Petrov\",\n" +
                "    \"country\": \"RU\",\n" +
                "    \"age\": 23\n" +
                "  }\n" +
                "]";

        assertEquals(jsonString,Main.listToJson(listActual));
    }

}