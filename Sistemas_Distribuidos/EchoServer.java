import java.io.*;
import java.net.*;
import java.util.*;

public class EchoServer {
	
	public String cesar(String mensagem, int grau){
		mensagem = mensagem.toLowerCase();
		String aux = "";
		
		for (int i = 0; i < mensagem.length(); i++) {
			aux += Character.toString((char) ((int) (mensagem.charAt(i)) + grau)); 
		}

		mensagem = String.valueOf(aux);
		return mensagem;	
	}

    public static void main (String[] args) {
		EchoServer server = new EchoServer();
		String so = System.getProperty("os.name");
		String java = System.getProperty("java.version");
		long hdSize = new File("/").getTotalSpace();
		hdSize = hdSize / 1000000000;

		try {
	    	ServerSocket s = new ServerSocket(8189);
	    	Socket incoming = s.accept();
	    	try {
	       		InputStream inStream = incoming.getInputStream();
				OutputStream outStream = incoming.getOutputStream();

				Scanner in = new Scanner(inStream);
				PrintWriter out = new PrintWriter(outStream, true /*autoFlush*/);
				out.println("Hello! Enter BYE to exit.");
				out.println("informações para o SO, Enter SO.");
				out.println("informações para o HD, Enter HD.");
				out.println("Nossa versão do java, Enter Java.");
				out.flush();

				//echo client input
				boolean done = false;
				while(!done && in.hasNextLine()){
					String line = in.nextLine();

					if (line.equals("SO") || line.equals("so") || line.equals("os") || line.equals("OS")) out.println("Nosso SO é o " + so);
					else if (line.equals("hd") || line.equals("HD") || line.equals("Hd")) out.println("Nosso HD é de " + hdSize + " GB");
					else if (line.equals("java") || line.equals("JAVA") || line.equals("Java")) out.println("Nosso java está na versão " + java);
					else out.println("Echo: " + server.cesar(line, 3));

					System.out.println("Client say: "+ server.cesar(line, 3));

		    		if (line.trim().equals("BYE") || line.trim().equals("bye")) done = true;
				}
				out.println("Até Logo!");
			} 
			finally {
	        	incoming.close();   
	    	}
		}
		catch (IOException e){
			e.printStackTrace(); 
		}
    }
}
