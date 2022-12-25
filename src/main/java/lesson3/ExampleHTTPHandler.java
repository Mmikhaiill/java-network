package lesson3;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class ExampleHTTPHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        switch (exchange.getRequestMethod()) {
            case "GET":
                handleRequest(
                        exchange, "Hello from GET method"
                );
                break;
            case "POST":
                handleRequest(
                        exchange, "Hello from POST Method"
                );
                break;
            default:
                throw new UnsupportedOperationException("Unsupported HTTP method: " + exchange.getRequestMethod());
        }
    }

    private void handleRequest(HttpExchange exchange, String response) throws IOException {
        exchange.sendResponseHeaders(200, response.length());
        InputStreamReader isr =  new InputStreamReader(exchange.getRequestBody(),"utf-8");
        BufferedReader br = new BufferedReader(isr);

        int b;
        StringBuilder buf = new StringBuilder(512);
        while ((b = br.read()) != -1) {
            buf.append((char) b);
        }

        String[] arr =buf.toString().split("&");

        Map<String,String> map = new HashMap<>();

        for (String s : arr){
            String[] param = s.split("=");
            map.put(param[0], param[1]);
        }
        br.close();
        isr.close();

        boolean result = map.entrySet().stream()
                .filter(e -> e.getKey().equals("login"))
                .map(Map.Entry::getValue)
                .allMatch(s -> s.equals("java"));

        if (result) {
            System.out.println("Hello java");
        } else {

        }
        try (final var responseBody = exchange.getResponseBody()) {
            responseBody.write(response.getBytes(StandardCharsets.UTF_8));
            responseBody.flush();
        }
    }
}
