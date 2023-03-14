import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ClientProperties {

    private final String host;
    private final int port;
    private final String clientExit;

    public ClientProperties(String path) throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream(path));
        host = properties.getProperty("HOST_NAME");
        port = Integer.parseInt(properties.getProperty("PORT"));
        clientExit = properties.getProperty("EXIT");
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public String getClientExit() {
        return clientExit;
    }



    @Override
    public String toString() {
        return "ServerSettings{" +
                "host = '" + host + '\'' +
                ", port = " + port +
                ", clientExit = '" + clientExit + '\'' +
                '}';
    }
}