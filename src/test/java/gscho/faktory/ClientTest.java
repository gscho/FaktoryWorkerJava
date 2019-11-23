package gscho.faktory;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class ClientTest extends TestCase{

	/**
	 * Create the test case
	 *
	 * @param testName name of the test case
	 */
	public ClientTest( String testName ){
		super( testName );
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite(){
		return new TestSuite( ClientTest.class );
	}

	public void testGetHiResponse() throws UnknownHostException, IOException, InterruptedException{
		Client client = new Client();
		client.startConnection( "127.0.0.1", 7419 );
		String response = client.getMessage();
		assertEquals( "+HI {\"v\":2}", response );
		client.sendMessage( "HELLO {\"v\":2}" );
		response = client.getMessage();
		assertEquals( "+OK", response );
		client.stopConnection();
		
	}
}
