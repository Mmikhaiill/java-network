package lesson3;

import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class Client {

    public static void main(String[] args) {
        try {
            httpPostRequest();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    private static void httpPostRequest() throws URISyntaxException, IOException, InterruptedException {
        Map<String, String> params = new HashMap<>();
        params.put("login", "java");
        params.put("password", "pass");
        HttpClient client = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();
        HttpRequest request = HttpRequest.newBuilder(new URI("http://localhost:8089/example"))
                .version(HttpClient.Version.HTTP_2)
                .POST(HttpRequest.BodyPublishers.ofString(convertion(params))).build();
//      client.send(request, HttpResponse.BodyHandlers.ofString());

        client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
    }

    public static String convertion(Map<String, String> map) throws UnsupportedEncodingException {
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<String, String> param : map.entrySet()) {
            if (stringBuilder.length() != 0) {
                stringBuilder.append('&');
            }
            stringBuilder.append(URLEncoder.encode(param.getKey(), "UTF-8"));
            stringBuilder.append('=');
            stringBuilder.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
        }
        return stringBuilder.toString();
    }
}
