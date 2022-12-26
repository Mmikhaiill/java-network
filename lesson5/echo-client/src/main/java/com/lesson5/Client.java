package com.lesson5;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args){
        System.out.println("Beginning connection...");

        try {
            Socket socket = new Socket("localhost", 25557);

            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(
                             socket.getInputStream()
                    )
            );
                 PrintWriter out = new PrintWriter(
                         new OutputStreamWriter(
                                 socket.getOutputStream()
                         )
                 )

            ) {
                Scanner console = new Scanner(System.in);
                String tmpStr;
                while (true) {
                    tmpStr = console.nextLine();

                    if ("STOP".equals(tmpStr))
                            break;
                    out.println(tmpStr);
                    out.flush();
                }
                out.println("hello");
                out.flush();

                System.out.println("SERVER" + br.readLine());
            } catch (IOException err) {
                System.err.println("mistake");
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
