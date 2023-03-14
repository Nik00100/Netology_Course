import com.google.gson.*;
import org.json.simple.JSONArray;
import org.json.simple.parser.*;
import java.io.*;
import java.util.*;

public class Main {

    public static String readString(String fileName) {
        StringBuilder sb = new StringBuilder();
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            while (reader.ready()) {
                sb.append(reader.readLine()).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public static List<Employee> jsonToList(String json) throws ParseException {
        Gson gson = new GsonBuilder().create();
        List<Employee> employees = new ArrayList<>();
        JSONArray jsonArray = (JSONArray) new JSONParser().parse(json);
                for (int i=0; i<jsonArray.size(); i++) {
            employees.add(gson.fromJson(jsonArray.get(i).toString(),Employee.class));
        }
        return employees;
    }

    public static void main(String[] args) throws ParseException {
        String fileNameJson = "c:/Games/json.txt";
        String json = readString(fileNameJson);
        jsonToList(json).stream().forEach(System.out::println);
    }
}
