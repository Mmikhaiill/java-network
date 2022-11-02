import java.io.IOException;
import java.net.*;
import java.util.Enumeration;


public class Lesson1 {
    public static void main(String[] args) throws IOException {

        Enumeration<NetworkInterface> interfaces =
                NetworkInterface.getNetworkInterfaces();

        while (interfaces.hasMoreElements())
        {
            NetworkInterface nif = interfaces.nextElement();
            Enumeration<InetAddress> inetAddresses = nif.getInetAddresses();
            while (inetAddresses.hasMoreElements())
            {
                inetAddresses.nextElement();

                System.out.println("*** Interface [" + nif.getName() + "] ***");

                System.out.println("display name  : " + nif.getDisplayName());
                System.out.println("loopback      : " + nif.isLoopback());
                System.out.println("up            : " + nif.isUp());
                System.out.println("virtual       : " + nif.isVirtual());
                System.out.println();

            }
        }


    }

}

