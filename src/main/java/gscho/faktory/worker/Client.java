package gscho.faktory.worker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Logger;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import gscho.faktory.message.Beat;
import gscho.faktory.message.Fetch;
import gscho.faktory.message.Flush;
import gscho.faktory.message.Hello;
import gscho.faktory.message.Hi;
import gscho.faktory.message.Info;
import gscho.faktory.message.Job;
import gscho.faktory.message.Message;
import gscho.faktory.message.MessageUtil;

/**
 * Faktory Client
 *
 */
public class Client{

	private static final ObjectMapper mapper = new ObjectMapper().setSerializationInclusion( Include.NON_NULL ).configure( SerializationFeature.FAIL_ON_EMPTY_BEANS, false );

	@FunctionalInterface
	interface Hasher{

		String hash( int iter, String pwd, String salt );
	}

	private static final Logger L = Logger.getLogger( Client.class.getName() );

	private final static int DEFAULT_TIMEOUT_MILLISECONDS = 5000;
	private final static int DEFAULT_SOCKET_READ_TIMEOUT_MILLISECONDS = 3000; //Faktory blocks for 2 seconds when no jobs are available
	private Socket clientSocket;
	private PrintWriter out;
	private BufferedReader in;

	private final static Hasher HASHER = ( int iter, String pwd, String salt ) -> "";

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

	public boolean startConnection( Connection connection ) throws UnknownHostException, IOException, InterruptedException{
		InetSocketAddress inet = new InetSocketAddress( connection.getIp(), connection.getPort() );
		clientSocket = new Socket();
		clientSocket.setKeepAlive( true );
		clientSocket.setSoTimeout( DEFAULT_SOCKET_READ_TIMEOUT_MILLISECONDS );
		clientSocket.connect( inet, DEFAULT_TIMEOUT_MILLISECONDS );
		out = new PrintWriter( clientSocket.getOutputStream(), true );
		in = new BufferedReader( new InputStreamReader( clientSocket.getInputStream() ) );
		Hi hi = receiveHi();
		if( hi.getS() != null ){
			return sendHello( hi );
		}
		else{
			return sendHelloNoPassword();
		}
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

	public Message getMessage(){
		String preamble = null;
		String body = null;
		try{
			int first = in.read();
			char chr = (char) first;
			String message = in.readLine();
			int firstWhitespace = message.indexOf( " " );
			boolean hasBody = firstWhitespace > -1;

			if( chr == '+' ){
				if( hasBody ){
					preamble = message.substring( 0, firstWhitespace ).trim();
					body = message.substring( firstWhitespace, message.length() ).trim();
				}
				else{
					preamble = message.substring( 0, message.length() ).trim();
				}
			}
			else if( chr == '$' ){
				return getMessage();

			}
			else if( chr == '-' ){
				System.err.println( "Error received" );
			}
			else if( chr == '{' ){
				Message m = new Message();
				m.setBody( "{" + message );
				return m;
			}
			else{
				System.err.println( "Parse Error!" );
				System.err.println( message.toString() );
			}

			return new Message( preamble, body );
		}
		catch( IOException io ){
			return null;
		}
		catch( Exception e ){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public Hi receiveHi(){
		try{
			return mapper.readValue( getMessage().getBody(), Hi.class );
		}
		catch( IOException e ){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public boolean receiveOk(){
		Message ok = getMessage();
		return ok != null && ok.getPreamble().equals( "OK" );
	}

	public boolean receiveOkOrState(){
		Message ok = getMessage();
		System.out.println( ok.toString() );
		return ok != null && ok.getPreamble().equals( "OK" );
	}

	public Info receiveInfo(){
		sendMessage( new Info() );
		Message info = getMessage();
		try{
			return mapper.readValue( info.getBody(), Info.class );
		}
		catch( IOException e ){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public Job receiveJob(){
		sendMessage( new Fetch() );
		Message resp = getMessage();
		if( resp == null ){
			return null;
		}
		try{
			return mapper.readValue( resp.getBody(), Job.class );
		}
		catch( IOException e ){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public boolean sendJob( Job job ){
		sendMessage( job );
		return receiveOk();
	}

	public boolean sendHello( Hi hi ){
		// TODO Auto-generated method stub
		return receiveOk();
	}

	public boolean sendHelloNoPassword(){
		sendMessage( new Hello( 2 ) );
		return receiveOk();
	}

	public boolean sendFlush(){
		sendMessage( new Flush() );
		return receiveOk();
	}

	public boolean sendBeat( String wid ){
		sendMessage( new Beat( wid ) );
		return receiveOkOrState();
	}
}
