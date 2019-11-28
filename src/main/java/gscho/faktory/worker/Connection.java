package gscho.faktory.worker;

import java.net.URI;
import java.util.Optional;

public class Connection{

	private final static String FAKTORY_PROVIDER = "FAKTORY_PROVIDER";
	private final static String DEFAULT_URI = "tcp://localhost:7419";
	private URI uri;

	public Connection(){
		String provider = System.getenv( FAKTORY_PROVIDER );
		if( provider != null ){
			uri = URI.create( Optional.of( System.getenv( provider ) ).orElse( DEFAULT_URI ) );
		}
		else{
			uri = URI.create( DEFAULT_URI );
		}
	}

	public URI getUri(){
		return uri;
	}

	public void setUri( URI uri ){
		this.uri = uri;
	}

	public String getIp(){
		return this.uri.getHost();
	}

	public int getPort(){
		return this.uri.getPort();
	}

}
