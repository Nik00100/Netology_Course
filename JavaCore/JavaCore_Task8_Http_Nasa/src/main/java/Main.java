import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

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

    public static Nasa jsonToObject(String json) {
        Gson gson = new GsonBuilder().create();

        JSONObject jsonObject = null;
        try {
            jsonObject = (JSONObject) new JSONParser().parse(json);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return (Nasa) gson.fromJson(jsonObject.toString(), Nasa.class);
    }

    public static void downloadFileByUrl(String urlLink, String filePath) {
        try (BufferedInputStream in = new BufferedInputStream(new URL(urlLink).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(filePath)) {
            byte dataBuffer[] = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
        } catch (IOException e) {
            // handle exception
        }

    }

    public static void main(String[] args) {
        String url = "https://api.nasa.gov/planetary/apod?api_key=C5DAmY9g6paNOtTteEyQevXZva4bcUWhqp9ZQVma";

        String json = getJsonFromHttpResponse(url);

        String urlLink = jsonToObject(json).getUrl();
        String filePath = "d:/img.jpg";
        downloadFileByUrl(urlLink,filePath);
        System.out.println(jsonToObject(json).getExplanation());
    }

}
