package gscho.faktory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Logger;

import gscho.faktory.message.Message;
import gscho.faktory.message.MessageUtil;

/**
 * Faktory Client
 *
 */
public class Client{

	private static final Logger L = Logger.getLogger( Client.class.getName() );

	private final static int DEFAULT_TIMEOUT_MILLISECONDS = 5000;
	private Socket clientSocket;
	private PrintWriter out;
	private BufferedReader in;

	public Client(){
		this( new Connection() );
	}

	public Client( Connection connection ){
		try{
			this.startConnection( connection );
		}
		catch( UnknownHostException unknownHost ){
			L.severe( String.format( "Unknown host: %s", connection.getIp() ) );
			L.severe( unknownHost.getMessage() );
			System.exit( 1 );
		}
		catch( IOException io ){
			L.severe( String.format( "Failed to start connection to: %s:%s", connection.getIp(), connection.getPort() ) );
			L.severe( io.getMessage() );
			System.exit( 1 );
		}
		catch( InterruptedException interrupt ){
			L.severe( "Received Interrupt!" );
			L.severe( interrupt.getMessage() );
			System.exit( 1 );
		}
	}

	public void startConnection( Connection connection ) throws UnknownHostException, IOException, InterruptedException{
		InetSocketAddress inet = new InetSocketAddress( connection.getIp(), connection.getPort() );
		clientSocket = new Socket();
		clientSocket.connect( inet, DEFAULT_TIMEOUT_MILLISECONDS );
		out = new PrintWriter( clientSocket.getOutputStream(), true );
		in = new BufferedReader( new InputStreamReader( clientSocket.getInputStream() ) );
	}

	public void sendMessage( Message msg ){
		out.println( MessageUtil.toString( msg ) );
	}

	public void stopConnection(){
		try{
			in.close();
		}
		catch( IOException io ){
			L.severe( "Failed to close \"in\" stream" );
			L.severe( io.getMessage() );
		}
		out.close();
		try{
			clientSocket.close();
		}
		catch( IOException io ){
			L.severe( "Failed to close \"client\" socket" );
			L.severe( io.getMessage() );
		}
	}

	public Message getMessage( Class klass ){
		try{
			String resp = in.readLine();
			return MessageUtil.fromString( resp, klass );
		}
		catch( IOException io ){
			L.warning( "Failed to read from \"in\" stream" );
			L.warning( io.getMessage() );
		}
		catch( Exception e ){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public String getMessage(){
		try{
			return in.readLine();
		}
		catch( IOException e ){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}
}
