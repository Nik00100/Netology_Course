import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ServerTest {
    private Server s;

    @Test
    public void testClientConnectionToServer() throws IOException {

        s = new Server();
        ServerSocket serverSocket = new ServerSocket(8000);
        Socket socket = serverSocket.accept();
        Server.sendBroadcastMessage(new Message(MessageType.TEXT,"TEST"));
        assertEquals("127.0.0.1",socket.getInetAddress().getHostAddress());
    }
}