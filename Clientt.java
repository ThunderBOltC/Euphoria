import java.net.*;
import java.io.*;
public class Clientt {
    Socket socket;
     BufferedReader br;
    PrintWriter out;
    public Clientt(){
        try{
            System.out.print("Finding you a nearby Volunteer\n");
        socket=new Socket("localhost",7777);
        System.out.println("Match found");
         br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
        //server message pathaillo
        out=new PrintWriter(socket.getOutputStream());
        startReading();
        startWriting();
        
    }catch(Exception e){
        
    }
    }
      public void startReading(){
        //ei thread read kore dibe
        Runnable r1=()->{
        System.out.println("Your Volunteer is here, I'm listening");
        while(true){
            try{
           String msg= br.readLine();
           if(msg.equals("exit")){
            System.out.println("Volunteer terminated the chat");
            break;
           }
           System.out.println("Volunteer : "+msg);

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
        };
        new Thread(r1).start();    

    }
       public void startWriting(){
// ei thread client ke message send koredibe
Runnable r2=()->{
System.out.println("");
while(true){
    try{
        BufferedReader br1=new BufferedReader(new InputStreamReader(System.in));
       
        String content=br1.readLine();

        out.println(content);
        out.flush();

    }
    catch(Exception e){
        e.printStackTrace();
    }
}
        };
        new Thread(r2).start();
    }
    public static void main(String[] args) {
        System.out.println("Welcome to Euphoria\nYour mental health matters");
        new Clientt();

    }
}
