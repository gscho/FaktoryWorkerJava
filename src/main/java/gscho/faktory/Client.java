package gscho.faktory;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Faktory Client
 *
 */
public class Client{

	private Socket clientSocket;
	private PrintWriter out;
	private BufferedReader in;

	public void startConnection( String ip, int port ) throws UnknownHostException, IOException, InterruptedException{
		clientSocket = new Socket( ip, port );
		out = new PrintWriter( clientSocket.getOutputStream(), true );
		in = new BufferedReader( new InputStreamReader( clientSocket.getInputStream() ) );
	}

	public void sendMessage( String msg ) throws IOException, InterruptedException{
		out.println( msg );
	}

	public void stopConnection() throws IOException{
		in.close();
		out.close();
		clientSocket.close();
	}

	public String getMessage() throws IOException{
		String resp = in.readLine();
		return resp;
	}
}
