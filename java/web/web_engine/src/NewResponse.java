import java.io.OutputStream;

public class NewResponse {

    public static final String RESPONSE_HEADER = "HTTP/1.1 200 \r\n"
            + "Content-Type: text/html\r\n"
            + "\r\n";

    private OutputStream outputStream;

    public NewResponse(OutputStream outputStream){
        this.outputStream = outputStream;
    }

    public OutputStream getOutputStream() {
        return outputStream;
    }
}
