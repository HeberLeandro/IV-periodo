import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Cliente {

    public String cesar(String mensagem, int grau, boolean encripta) {
        mensagem = mensagem.toLowerCase();
        String aux = "";

        for (int i = 0; i < mensagem.length(); i++) {

            if (encripta) {
                aux += Character.toString((char) ((int) (mensagem.charAt(i)) + grau));
            } else {
                aux += Character.toString((char) ((int) (mensagem.charAt(i)) - grau));
            }
        }

        mensagem = String.valueOf(aux);
        return mensagem;
    }

    public static void main(String[] arg) {
        String texto = "";
        Cliente cli = new Cliente();
        int c, total = (int) (10 * Math.random());
        Socket s = null;
        Scanner scan = new Scanner(System.in);
        try {
            System.out.println("Conectando...");
            s = new Socket("192.168.89.57", 6789);
            DataInputStream in = new DataInputStream(s.getInputStream());
            DataOutputStream out = new DataOutputStream(s.getOutputStream());
            System.out.println("Conectado. Enviando números...");
            while (texto != "stop") {
                texto= scan.nextLine();
                System.out.println("Enviando " + texto);
                out.writeUTF(cli.cesar(texto, 3, true));
            }
            out.flush();
            out.writeUTF(cli.cesar(texto, 3, true));
            System.out.println("Somatório = " + cli.cesar(in.readUTF(), 3, false));
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        } finally {
            try {
                if (s != null) {
                    s.close();
                }
            } catch (Exception e2) {
            }
        }
        System.out.println("Conexão encerrada");
        try {
            System.in.read();
        } catch (Exception e3) {
        }
    }
}
