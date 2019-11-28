package gscho.faktory.worker;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class FaktoryWorkerJavaTestSuite extends TestCase{

	public static Test suite(){
		return new TestSuite( ConnectionTest.class, ClientTest.class );
	}
}
