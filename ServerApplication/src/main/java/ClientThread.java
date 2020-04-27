import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

class ClientThread extends Thread {
    private Socket socket = null ;
    public ClientThread (Socket socket) { this.socket = socket ; }
    public void run () {
        try {
            // Get the request from the input stream: client → server

            //crearea capetelor pentru socket
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream());
            while(true)
            {
            String request = in.readLine();
            //primirea request-urilor de la client si tratarea lor
            String raspuns;
            System.out.println("Received from client:"+request);
                if(request.equals("stop"))
                {
                    raspuns="Server stopped";
                    out.println(raspuns);
                    System.out.println("Server has stopped");
                    out.flush();
                    socket.close();
                    System.exit(0);
                }
                else if(request.compareTo("exit")==0)
                {
                    raspuns="Client stopped";
                    System.out.println("Client has stopped");
                    socket.close();
                    System.exit(0);
                }
            else
            raspuns = "Server received the request " + request + "!";
            out.println(raspuns);
            out.flush();}
        } catch (IOException e) {
            System.err.println("Communication error... " + e);
        } finally {
            try {
                socket.close();
            } catch (IOException e) { System.err.println (e); }
        }
    }
}