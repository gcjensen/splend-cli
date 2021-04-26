package uk.gcjensen.splend;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.URI;
import java.net.http.HttpResponse;

public class SplendAPI {
    private final HttpClient client = HttpClient.newHttpClient();
    private final String baseURL;
    private final Config config;

    public SplendAPI(Config config) {
        this.config = config;
        baseURL = config.getApiURL();
    }

    public String getSummary() {
       return makeRequest(String.format("/user/%d/summary", config.getID()));
    }

    private String makeRequest(String path) {
        HttpRequest request = HttpRequest.newBuilder(URI.create(baseURL + path))
            .header("accept", "application/json")
            .header("Token", config.getToken())
            .build();

        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            System.out.println("Unable to reach Splend API.");
            System.exit(1);
        }

        return response.body();
    }
}
