import java.net.*;
import java.io.*;

public class Server {

    // constructor for server
    ServerSocket server;
    Socket socket;
    BufferedReader br;
    PrintWriter out;

    public Server() {
        try {
            // server ready korlam
            server = new ServerSocket(7778);
            System.out.println("server ready \nwaiting for a client\n");
            // connection accec
            socket = server.accept();
            // client theke message read korlam
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // server message pathaillo
            out = new PrintWriter(socket.getOutputStream());
            startReading();
            startWriting();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void startReading() {
        // ei thread read kore dibe
        Runnable r1 = () -> {
            System.out.println("one client is here");
            while (true) {
                try {
                    String msg = br.readLine();
                    if (msg.equals("exit")) {
                        System.out.println("client terminated the chat");
                        break;
                    }
                    System.out.println("Client : " + msg);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        new Thread(r1).start();

    }

    public void startWriting() {
        // ei thread client ke message send koredibe
        Runnable r2 = () -> {
            System.out.println("start chatting");
            while (true) {
                try {
                    BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
                    String content = br1.readLine();
                    out.println(content);
                    out.flush();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        new Thread(r2).start();
    }

    public static void main(String[] args) {
        System.out.println("this is server");
        new Server();

    }
}
