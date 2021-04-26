package uk.gcjensen.splend;

import org.yaml.snakeyaml.Yaml;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;

public class Config {
    private final String filePath = System.getProperty("user.home") + "/.splend/config.yaml";

    private final String apiURL;
    private final String token;
    private final int id;

    public Config() throws FileNotFoundException {
        InputStream inputStream = new FileInputStream(filePath);
        Yaml yaml = new Yaml();
        Map<String, Object> data = yaml.load(inputStream);

        id = (int) data.get("id");
        token = data.get("token").toString();
        apiURL = data.get("api-url").toString();
    }

    public int getID() {
        return id;
    }

    public String getApiURL() {
        return apiURL;
    }

    public String getToken() {
        return token;
    }
}
