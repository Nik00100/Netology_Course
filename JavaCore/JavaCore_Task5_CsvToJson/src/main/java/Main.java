import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.opencsv.CSVReader;
import com.opencsv.bean.*;
import com.opencsv.exceptions.CsvException;
import java.io.*;
import java.lang.reflect.Type;
import java.util.List;

public class Main {
    public static List<Employee> parseCSV(String[] columnMapping, String fileName) {
        try (CSVReader csvReader = new CSVReader(new FileReader(fileName))){
            ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy<>();
            strategy.setType(Employee.class);
            strategy.setColumnMapping(columnMapping);
            CsvToBean csvToBean = new CsvToBeanBuilder(csvReader).
                    withMappingStrategy(strategy).
                    build();
            return (List<Employee>) csvToBean.parse();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String listToJson(List<Employee> list) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Type listType = new TypeToken<List<Employee>>() {}.getType();
        return gson.toJson(list,listType);
    }

    public static void writeString(String fileName, String json) {
        File file = new File(fileName);
        try {
            file.createNewFile();
            FileWriter writer = new FileWriter(file);
            writer.write(json);
            writer.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException, CsvException {
        String[] columnMapping = {"id", "firstName", "lastName", "country", "age"};
        String fileNameCsv = "src/main/resources/data.csv";
        String fileNameJson = "c:/Games/json.txt";
        List<Employee> list = parseCSV(columnMapping, fileNameCsv);

        list.stream().forEach(System.out::println);

        System.out.println("--------");
        System.out.println(listToJson(list));

        writeString(fileNameJson,listToJson(list));
    }
}
