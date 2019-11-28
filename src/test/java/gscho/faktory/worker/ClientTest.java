package gscho.faktory.worker;

import static org.junit.Assert.assertArrayEquals;

import org.junit.After;

import gscho.faktory.message.Info;
import gscho.faktory.message.Job;
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

	@After
	public void teardownTest(){
		Client client = new Client();
		client.sendFlush();
		client.stopConnection();
	}

	public void testCreateClient(){
		Client client = new Client();
		client.stopConnection();
	}

	public void testGetInfo(){
		Client client = new Client();
		Info info = client.receiveInfo();
		client.stopConnection();
		assertNotNull( info );
		assertNotNull( info.getServer_utc_time() );
	}

	public void testSendAndReceive(){
		Client client = new Client();
		boolean ok = client.sendJob( new Job( "123456789dcba", "testReceiveJob", new Object[]{1, 2, "hello"} ) );
		assertTrue( ok == true );
		Job job = client.receiveJob();
		client.stopConnection();
		assertEquals( "123456789dcba", job.getJid() );
		assertEquals( "testReceiveJob", job.getJobtype() );
		assertArrayEquals( new Object[]{1, 2, "hello"}, job.getArgs() );
		client.stopConnection();

	}

	public void testReceiveNoJob(){
		Client client = new Client();
		Job job = client.receiveJob();
		client.stopConnection();
		assertNull( job );
	}

	//	public void testBeat() throws InterruptedException{
	//		Client client = new Client();
	//		boolean ok = client.sendBeat("1");
	//		assertTrue( ok == true );
	//		Thread.sleep( 1000 );
	//		ok = client.sendBeat("1");
	//		assertTrue( ok == true );
	//		Thread.sleep( 1000 );
	//		ok = client.sendBeat("1");
	//		assertTrue( ok == true );
	//		client.stopConnection();
	//	}

}
