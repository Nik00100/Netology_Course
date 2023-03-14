import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import javax.xml.parsers.*;
import java.io.*;
import java.lang.reflect.Type;
import java.util.*;

public class Main {

    // Получение атрибутов каждого элемента
    public static String getString(String tagName, Element element) {
        NodeList list = element.getElementsByTagName(tagName);
        if (list != null && list.getLength() > 0) {
            NodeList subList = list.item(0).getChildNodes();

            if (subList != null && subList.getLength() > 0) {
                return subList.item(0).getNodeValue();
            }
        }
        return null;
    }
   public static List<Employee> parseXML(String fileName) {
       List<Employee> employees = new ArrayList<>();
       DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
       try {
           DocumentBuilder builder = factory.newDocumentBuilder();
           Document document = builder.parse(fileName);
           NodeList stuff = document.getDocumentElement().getChildNodes();

           for (int i = 0; i < stuff.getLength(); i++) {
               if (stuff.item(i).hasChildNodes()){
                   Element element = (Element) stuff.item(i);
                   employees.add(new Employee(Long.parseLong(getString("id",element)),getString("firstName",element),
                           getString("lastName",element),getString("country",element),
                           Integer.parseInt(getString("age",element))));
               }
           }
       } catch (IOException | SAXException | ParserConfigurationException e) {
           e.printStackTrace();
       }
       return employees;
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

    public static void main(String[] args) {
        String fileNameXml = "src/main/resources/data.xml";
        String fileNameJson = "c:/Games/json.txt";
        List<Employee> list = parseXML(fileNameXml);

        list.stream().forEach(System.out::println);

        System.out.println("--------");
        System.out.println(listToJson(list));

        writeString(fileNameJson,listToJson(list));
    }
}
