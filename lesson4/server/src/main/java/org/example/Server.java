package org.example;

import java.io.IOException;
import java.net.*;

public class Server extends Thread{
    private DatagramSocket socket;

    private boolean running;
    private byte[] buf = new byte[256];

    public Server() {
        try {
            socket = new DatagramSocket(24553);
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
    }

    public void run() {
        running = true;

        while(running) {
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            try {

                socket.receive(packet);
                InetAddress address = packet.getAddress();
                int port = packet.getPort();
                packet = new DatagramPacket(buf, buf.length, address, port);
                String received = new String(packet.getData(), 0, packet.getLength());
                if (received.equals("Stop")) {
                    running = false;
                }
                socket.send(packet);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        socket.close();

    }
}
