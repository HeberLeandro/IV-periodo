import java.io.*;
import java.net.*;
import java.util.*;

public class EchoServer {
    public static void main (String[] args) {
		//System.getProperties().list(System.out);
		String so = System.getProperty("os.name");
		String java = System.getProperty("java.version");
		long hdSize = new File("/").getTotalSpace();
		hdSize = hdSize / 1000000000;
		System.out.println(hdSize);

		try {
	    	ServerSocket s = new ServerSocket(8189);
	    	Socket incoming = s.accept();
	    	try {
	       		InputStream inStream = incoming.getInputStream();
				OutputStream outStream = incoming.getOutputStream();

				Scanner in = new Scanner(inStream);
				PrintWriter out = new PrintWriter(outStream, true /*autoFlush*/);
				out.println("Hello! Enter BYE to exit.");

				//echo client input
				boolean done = false;
				while(!done && in.hasNextLine()){
					String line = in.nextLine();
					if (line.equals("SO") || line.equals("so") || line.equals("os") || line.equals("OS")) out.println("Nosso SO é o " + so);
					else if (line.equals("hd") || line.equals("HD") || line.equals("Hd")) out.println("Nosso HD é de " + hdSize + " GB");
					else if (line.equals("java") || line.equals("JAVA") || line.equals("Java")) out.println("Nosso java está na versão " + java);
					else out.println("Echo: " + line);
					System.out.println("Client say: "+ line);
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
