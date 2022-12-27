package lesson2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Main {

    public static void main(String[] args) throws IOException {

        URL address = new URL("https://ya.ru");
        URLConnection connection = address.openConnection();

        System.out.println("Address: " + address);
        System.out.println("Content length: " + connection.getContentLength());
        System.out.println("Content type: " + connection.getContentType());

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        in.lines().forEach(System.out::println);

        in.close();
    }
}
