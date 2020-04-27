import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class GameClient {
    public static void main (String[] args) throws IOException {
        String serverAddress = "127.0.0.1"; // The server's IP address
        int PORT = 8100; // The server's port
        try ( //deschidem socketul in ambele capete
                Socket socket = new Socket(serverAddress, PORT);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream())) ) {
            //folosim scannerul pentru a putea introduce requesturi prin linia de comanda
            Scanner scanner = new Scanner(System.in);
            //while(true) este folosit pentru a permite introducerea comenzilor una dupa alta pana la inchiderea conexiunii
            // prin exit sau stop
            while (true){
                //daca socketul este inchis,atunci nu mai putem trimite request-uri
                if (socket.isClosed())
                    break;
                String request = scanner.nextLine();
                //daca folosim request-ul exit,inchidem conexiunea clientului cu serverul
                if (request.compareTo("exit")==0){
                    out.println(request);
                    socket.close();
                    System.exit(0);
                }

                out.println(request);

                String response = in.readLine();
                System.out.println(response);
            }
        } catch (UnknownHostException e) {
            System.err.println("No server listening... " + e);
        }
    }
}