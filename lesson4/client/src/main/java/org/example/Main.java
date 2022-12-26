package org.example;

public class Main {
    public static void main(String[] args) {

        Client client = new Client();
        String echo = client.send("Hello");
        System.out.println(echo);
    }
}