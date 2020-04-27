import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class GameServer {
    // Definirea port-ului
    public static final int PORT = 8100;
    public GameServer() throws IOException {
        ServerSocket serverSocket = null ;
        try {
            serverSocket = new ServerSocket(PORT);
            while (true) {
                //server-ul asteapta ca un client sa se conecteze la port-ul "PORT"
                System.out.println ("Waiting at Port ..."+PORT);
                Socket socket = serverSocket.accept();
                // executarea comenzilor data de client intr-un thread nou
                new ClientThread(socket).start();
            }
        } catch (IOException e) {
            System.err. println ("Ooops... " + e);
        } finally {
            serverSocket.close();
        }
    }

}