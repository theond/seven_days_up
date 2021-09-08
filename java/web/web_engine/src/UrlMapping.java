import java.util.HashMap;
import java.util.Map;

public class UrlMapping {

    private enum Singleton{
        INSTANCE;

        private final UrlMapping instance;
        Singleton(){
            instance = new UrlMapping();
        }

        private UrlMapping getInstance(){
            return instance;
        }
    }

    private UrlMapping(){}

    public static UrlMapping getInstance(){
        return Singleton.INSTANCE.getInstance();
    }

    private Map<String, Object> urlMap = new HashMap<>();

    public void addMap(String url, Object method){
        urlMap.put(url, method);
    }

    public void addBatch(Map<String ,String> map){
        urlMap.putAll(map);
    }

    /*
    public Map<String, Object> getUrlMap(){
        return this.urlMap;
    }

     */

    public Object getMethod(String urlString){
        return this.urlMap.get(urlString);
    }
}
