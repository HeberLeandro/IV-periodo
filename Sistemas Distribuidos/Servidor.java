import java.io.*;
import java.net.*;
import java.util.*;

public class Servidor {

  public String cesar(String mensagem, int grau, boolean encripta){
    mensagem = mensagem.toLowerCase();

		String aux = "";
  
    if (encripta) {
      for (int i = 0; i < mensagem.length(); i++) {
        aux += Character.toString((char) ((int) (mensagem.charAt(i)) + grau)); 
      }
    }else {
      System.out.println("cripto: " + mensagem);
      for (int i = 0; i < mensagem.length(); i++) {
        aux += Character.toString((char) ((int) (mensagem.charAt(i)) - grau)); 
      }
    }

    mensagem = String.valueOf(aux);
    System.out.println("descripto: " + mensagem);
		return mensagem;	
  }
  
  public static void main(String[] args) {
    Servidor serv = new Servidor();
    ServerSocket s;
    try {
      s = new ServerSocket(6789);
      System.out.println("Servidor iniciado na porta 6789");
      while (true) {
        Socket cliente = s.accept();
        System.out.println("Conexão estabelecida " + "(" + cliente + ")");
        DataInputStream in = new DataInputStream(cliente.getInputStream());
        DataOutputStream out = new DataOutputStream(cliente.getOutputStream());
        double numero, soma = 0.0;
        String texto = serv.cesar(in.readUTF(), 3, false);
        while (texto != "stop") {
          //soma += numero;
          System.out.println("Valor recebido: " + texto);
          texto = serv.cesar(in.readUTF(), 3, false);
          //numero = Double.valueOf(serv.cesar(in.readUTF(), 3, false));
        }
        out.writeUTF(serv.cesar("Bye!", 3, true));
        cliente.close();
        System.out.println("Conexão encerrada.");
      }
    } catch (Exception e) {
      System.out.println("Erro: " + e.getMessage());
    }
  }
}
