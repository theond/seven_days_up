import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

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
            UrlMapping pathMap = UrlMapping.getInstance();
            NewRequest request = new NewRequest(socket.getInputStream());
            NewResponse response = new NewResponse(socket.getOutputStream());
            String requestUrl = request.getUrl();
            this.url = requestUrl;
            NewServlet servlet = (NewServlet) pathMap.getMethod(requestUrl);
            if(null != servlet){
                this.status = SUCCESS;
                servlet.service(request, response);
            }else{
                this.status = NOT_FOUND;
                OutputStream outputStream = response.getOutputStream();
                outputStream.write(new String(NewResponse.RESPONSE_HEADER +"error: Cannot find the servlet!").getBytes(StandardCharsets.UTF_8));
                outputStream.flush();
                outputStream.close();
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(null != socket){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
