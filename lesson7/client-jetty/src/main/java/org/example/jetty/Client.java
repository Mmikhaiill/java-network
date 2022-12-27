package org.example.jetty;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.http.HttpMethod;

import java.util.Scanner;

@Slf4j
public class Client {

    @SneakyThrows
    public static void main(String[] args) {

        var client = new HttpClient();
        client.start();

        var scanner = new Scanner(System.in);

        while (true) {
            var name = scanner.nextLine();
            log.info("name = " + name);

            if (name.isEmpty())
                break;

            var response = client.newRequest("http://localhost/service")
                    .port(8080)
                    .method(HttpMethod.GET)
                    .param("name", name)
                    .send();

            log.info(response.getContentAsString());
        }
        client.stop();

    }

}