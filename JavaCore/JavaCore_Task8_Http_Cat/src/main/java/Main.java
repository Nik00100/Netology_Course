import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static String getJsonFromHttpResponse (String url) {
        String jsonData = null;
        CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setConnectTimeout(5000)    // максимальное время ожидание подключения к серверу
                        .setSocketTimeout(30000)    // максимальное время ожидания получения данных
                        .setRedirectsEnabled(false) // возможность следовать редиректу в ответе
                        .build())
                .build();
        HttpGet request = new HttpGet(url);
        try {
            CloseableHttpResponse response = httpClient.execute(request);
            HttpEntity entity = response.getEntity();
            jsonData = EntityUtils.toString(entity, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonData;
    }

    public static List<Cat> jsonToList(String json) {
        Gson gson = new GsonBuilder().create();
        List<Cat> cats = new ArrayList<>();
        JSONArray jsonArray = null;
        try {
            jsonArray = (JSONArray) new JSONParser().parse(json);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        for (int i=0; i<jsonArray.size(); i++) {
            cats.add(gson.fromJson(jsonArray.get(i).toString(),Cat.class));
        }
        return cats;
    }

    public static void main(String[] args) {
        String url = "https://raw.githubusercontent.com/netology-code/jd-homeworks/master/http/task1/cats";
        System.out.println("Upvotes не равно null:");
        String json = getJsonFromHttpResponse(url);
        //System.out.println(json);

        List<Cat> list = jsonToList(json);

        list.stream().
                filter(cat -> cat.getUpVotes()!=null && cat.getUpVotes()>0).
                forEach(System.out::println);
    }

}
