package gscho.faktory;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class ConnectionTest extends TestCase{

	/**
	 * Create the test case
	 *
	 * @param testName name of the test case
	 */
	public ConnectionTest( String testName ){
		super( testName );
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite(){
		return new TestSuite( ConnectionTest.class, ClientTest.class );
	}

	public void testWithoutFaktoryProvider(){
		Connection connection = new Connection();
		assertEquals( "localhost", connection.getIp() );
		assertEquals( 7419, connection.getPort() );
	}
}
