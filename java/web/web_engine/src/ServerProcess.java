import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class ServerProcess extends Thread{

    private static final String SUCCESS = "200";
    private static final String NOT_FOUND = "404";

    private Socket socket;
    private String address;
    private Integer port;
    private String status;
    private String url;

    public ServerProcess(Socket socket){
        this.socket = socket;
        InetAddress inetAddress = socket.getInetAddress();
        this.address = inetAddress.getHostAddress();
        this.port = socket.getLocalPort();
    }

    @Override
    public void run(){
        try{
            NewRequest request = new NewRequest(socket.getInputStream());
            NewResponse response = new NewResponse(socket.getOutputStream());


        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
