package gscho.faktory;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import gscho.faktory.message.MessageUtil;
import gscho.faktory.message.receive.Hi;
import gscho.faktory.message.receive.Ok;
import gscho.faktory.message.send.Fetch;
import gscho.faktory.message.send.Hello;
import gscho.faktory.message.send.Job;
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

	public void testGetHiResponse() throws JsonParseException, JsonMappingException, IOException{
		Client client = new Client();
		Hi hi = (Hi) client.getMessage( Hi.class );
		assertEquals( "HI {\"v\":2}", MessageUtil.toString( hi ) );
		client.sendMessage( new Hello( 2 ) );
		Ok ok = (Ok) client.getMessage( Ok.class );
		assertEquals( "OK", MessageUtil.toString( ok ) );
		client.sendMessage( new Job( "123861239abnadsa", "SomeName", new Object[]{1, 2, "hello"} ) );
		System.err.println( client.getMessage() );
		client.sendMessage( new Fetch() );
		System.err.println( client.getMessage() );
		System.err.println( client.getMessage() );
		client.stopConnection();
	}
}
