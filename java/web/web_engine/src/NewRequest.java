import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class NewRequest {

    private String method;
    private String url;
    private String[] paramArray;

    public NewRequest(InputStream inputStream){
        BufferedReader bf = new BufferedReader(new InputStreamReader(inputStream));
        try{
            String[] split = bf.readLine().split(" ");
            if(split.length == 3){
                this.method = split[0];
                String urlAllPath = split[1];
                if(urlAllPath.contains("?")){
                    int splitIndex = urlAllPath.indexOf("?");
                    this.url = urlAllPath.substring(0, splitIndex);
                    String params = urlAllPath.substring(splitIndex+1);
                    this.paramArray = params.split("&");
                }else{
                    this.url = urlAllPath;
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public String getMethod() {
        return method;
    }

    public String getUrl() {
        return url;
    }

    public String[] getParamArray() {
        return paramArray;
    }
}
