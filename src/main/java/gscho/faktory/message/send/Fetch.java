package gscho.faktory.message.send;

import gscho.faktory.message.Message;

public class Fetch extends Message{

	String[] queues;

	public Fetch(){
		this.queues = new String[1];
		this.queues[0] = "default";
	}

	public Fetch( String ... queues ){
		this.queues = queues;
	}

	@Override
	public String getPreamble(){
		return String.format( "FETCH %s", String.join( " ", queues ) );
	}

}
