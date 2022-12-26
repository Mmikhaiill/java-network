package com.lesson5;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private static final ExecutorService pool = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors()
    );
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket;


        try {
            serverSocket = new ServerSocket(25557);

            while (true) {
                final Socket clientSocket = serverSocket.accept();

                pool.submit(
                        new AsyncWorkWithClient(clientSocket)
                );
            }
        }
         catch(IOException e){
                e.printStackTrace();
                System.err.println(e.getMessage());
            }

    }

    static class AsyncWorkWithClient implements Runnable {
        private Socket clientSocket;

        public AsyncWorkWithClient(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        @Override
        public void run() {


            try (PrintWriter out = new PrintWriter(
                    new OutputStreamWriter(clientSocket.getOutputStream())
            );

                 BufferedReader br = new BufferedReader(
                         new InputStreamReader(
                                 clientSocket.getInputStream()
                         )
                 )) {
                String line;
                while ((line = br.readLine()) != null) {
                    System.out.println("CLIENT: " + line);
                    out.println("echo" + line);
                    out.flush();
                }
            } catch (IOException err) {
                System.err.println("I/O error 1");
            }
            }
        }
    }


