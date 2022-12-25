package lesson3;

import com.sun.net.httpserver.HttpServer;
import lesson3.ExampleHTTPHandler;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

public class Server {
    public static void main(String[] args) {
        final HttpServer server;
        try {
            server = HttpServer.create(new InetSocketAddress("localhost", 8089), 0);
        } catch (IOException e) {
            throw new RuntimeException("Invalid login");
        }
        server.setExecutor(Executors.newFixedThreadPool(10));
        server.createContext("/example", new ExampleHTTPHandler());
        server.start();
    }
}
