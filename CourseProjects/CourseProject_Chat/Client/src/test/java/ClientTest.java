import org.junit.jupiter.api.Test;

import java.io.IOException;

import java.net.Socket;


import static org.junit.jupiter.api.Assertions.*;

public class ClientTest {

    private Client c;

    @Test
    public void testClientConnectionToServer() throws IOException {
        String path = "src/main/resources/application.properties";
        ClientProperties clientProperties = new ClientProperties(path);
        c = new Client(clientProperties);
        Client.SocketThread thread = c.getSocketThread();
        c.connection = new Connection(new Socket(clientProperties.getHost(),clientProperties.getPort()));

        assertEquals("localhost/127.0.0.1:8000",c.connection.getRemoteSocketAddress().toString());
    }
}