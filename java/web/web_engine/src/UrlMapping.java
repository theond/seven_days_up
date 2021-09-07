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

    private Map<String, String> urlMap = new HashMap<>();

    public void addMap(String url, String method){
        urlMap.put(url, method);
    }

    public void addBatch(Map<String ,String> map){
        urlMap.putAll(map);
    }

    public Map<String, String> getUrlMap(){
        return this.urlMap;
    }
}
