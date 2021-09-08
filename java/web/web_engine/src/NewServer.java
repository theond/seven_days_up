import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class NewServer{

    private static void initServer(){
        try {
            UrlMapping pathMap = UrlMapping.getInstance();
            pathMap.addMap("/test", (NewServlet) Class.forName("NewServlet").newInstance());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try{
            System.out.println("web engine start.");
            System.out.println("web engine init....");
            ServerSocket socket = new ServerSocket(8090);
            initServer();
            System.out.println("web engine finish.");
            while(Boolean.TRUE){
                Socket  accept = socket.accept();
                Thread thread = new ServerProcess(accept);
                thread.start();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
