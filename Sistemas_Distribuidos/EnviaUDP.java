import java.io.*;

import java.net.*;
import java.util.Scanner;

public class Udp {

    public static void main(String[] args) throws Exception {
        InetAddress end = InetAddress.getByName("192.168.89.57");
        byte[] buffer = new byte[1024];
        DatagramPacket packete = new DatagramPacket(buffer, buffer.length);
        DatagramSocket sockete = new DatagramSocket(1234);
        Scanner scan = new Scanner(System.in);
        String aloa = "ta ai?";
        String s = "";
        boolean conected = false;
        while (!s.equals("Sair")) {
            if (!conected) {
                DatagramPacket packdope;
                packdope = new DatagramPacket(aloa.getBytes(), aloa.length(), end, 1234);
                sockete.send(packdope);
                try {
                    sockete.setSoTimeout(10000);
                    for (int i = 0; i < 5; i++) {
                        try {
                            sockete.receive(packete);
                            s = new String(buffer, 0, packete.getLength());
                            System.out.println(
                                    "From: " + packete.getAddress().getHostName() + ":" + packete.getPort() + ":" + s);
                            conected = true;
                            String ok = "ok";
                            DatagramSocket sock = new DatagramSocket();
                            packdope = new DatagramPacket(ok.getBytes(), ok.length(), end, 1234);
                            sock.send(packdope);
                            sock.close();

                            i = 5;
                        } catch (InterruptedIOException e) {
                            System.out.println("");
                        }
                        // Podemos fazer algo antes de tentar de novo
                    }
                } catch (SocketException e) {
                    System.out.println("Erro de Socket: " + e);
                }
            }

            System.out.println("Digite uma mensagem!");
            System.out.println("Digite 'Sair' caso deseje encerrar");
            s = scan.nextLine();

            byte[] msg = new byte[s.length()];
            msg = s.getBytes();

            DatagramPacket packet = new DatagramPacket(msg, msg.length, end, 1234);
            DatagramSocket socket = new DatagramSocket();
            socket.send(packet);
            socket.close();

        }

    }
}
