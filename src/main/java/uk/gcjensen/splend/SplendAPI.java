package uk.gcjensen.splend;

import java.io.IOException;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.URI;
import java.net.http.HttpResponse;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class SplendAPI {
    private final Charset CHAR_SET = StandardCharsets.UTF_8;

    private final HttpClient client = HttpClient.newHttpClient();
    private final String baseURL;
    private final Config config;

    public SplendAPI(Config config) {
        this.config = config;
        baseURL = config.getApiURL();
    }

    public String getOutgoings(Map<String, Object> params) {
        // Build up query string from map of params
        String queryString = params.entrySet().stream()
            .map(p -> p.getKey() + "=" + URLEncoder.encode(p.getValue().toString(), CHAR_SET))
            .reduce((p1, p2) -> p1 + "&" + p2)
            .orElse("");

        return makeRequest(String.format("/user/%d/outgoings?%s", config.getID(), queryString));
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
