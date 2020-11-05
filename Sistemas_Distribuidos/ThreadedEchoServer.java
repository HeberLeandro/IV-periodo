import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class ThreadedEchoServer {
	static int i = 1;

	public static void main(String[] args) {
		try {
			ServerSocket s = new ServerSocket(8189);
			List<ThreadedEchoHandler> listT = new ArrayList<ThreadedEchoHandler>();

			for (;;) {
				Socket incoming = s.accept();
				System.out.println("Spawning " + i);
				ThreadedEchoHandler aux = new ThreadedEchoHandler(incoming, i, listT);
				listT.add(aux);
				aux.start();
				i++;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}

class ThreadedEchoHandler extends Thread {
	
	private Socket incoming;
	private int counter;
	private List<ThreadedEchoHandler> list;
	BufferedReader in;
	PrintWriter out;
	
	public ThreadedEchoHandler(Socket sock, int c, List<ThreadedEchoHandler> l) {
		incoming = sock;
		counter = c;
		list = l;
	}

	public void run() {
		try {
			in = new BufferedReader(new InputStreamReader(incoming.getInputStream()));
			out = new PrintWriter(incoming.getOutputStream(), true /* autoFlush */);

			out.println("Hello! Enter BYE to exit.");

			boolean done = false;
			while (!done) {
				String str = in.readLine();
				str = str.trim();
				System.out.println(getCounter() + " - " + str);
				if (str == null)
					done = true;
				else {
					out.println("Echo (" + getCounter() + "): " + str);
					sendEcho(str);
					if (str.trim().equals("BYE"))
						done = true;
				}
			}
			incoming.close();
			list.remove(this);
			this.interrupt();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private void sendEcho(String text) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getCounter() != getCounter() ) {
				list.get(i).echo(text, getCounter());
			}
		}
		
	}

	private void echo(String text, int counter) {
		System.out.println("Server ("+getCounter()+") --> Client (" +counter+") say: " + text);
		out.println("Server ("+getCounter()+") --> Client (" +counter+") say: " + text);
		
	}

	public int getCounter() {
		return counter;
	}

}
