import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class NewServlet {
    public static final String GET_METHOD = "GET";
    public static final String POST_METHOD = "POST";
    public void service(NewRequest request, NewResponse response){
        if(GET_METHOD.equalsIgnoreCase(request.getMethod())){
            doGet(request, response);
        }else if(POST_METHOD.equalsIgnoreCase(request.getMethod())){
            doPost(request, response);
        }else{
            errorHandler(request, response);
        }
    }

    public void doGet(NewRequest request, NewResponse response){
        try{
            StringBuffer sb = new StringBuffer();
            sb.append("http method:["+request.getMethod()+"]");
            sb.append("/r/n");

            String [] params = request.getParamArray();
            sb.append("request params:[");
            if(params != null) {

                for (int i = 0; i<params.length - 1 ; i++){
                    sb.append(params[i]);
                    sb.append(" ");
                }

                sb.append(params[params.length - 1]+"]");
                sb.append("/r/n");
            }else{
                sb.append("]");
            }

            sb.append("timestamp:["+System.currentTimeMillis()+"]");
            OutputStream  outputStream = response.getOutputStream();
            outputStream.write(sb.toString().getBytes(StandardCharsets.UTF_8));
            outputStream.flush();
            outputStream.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void doPost(NewRequest request, NewResponse response){
        //todo
        doGet(request, response);
    }

    public void errorHandler(NewRequest request, NewResponse response){
        try{
            StringBuffer sb = new StringBuffer();
            sb.append("method not support");
            sb.append("/r/n");

            sb.append("timestamp:["+System.currentTimeMillis()+"]");
            OutputStream  outputStream = response.getOutputStream();
            outputStream.write(sb.toString().getBytes(StandardCharsets.UTF_8));
            outputStream.flush();
            outputStream.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
