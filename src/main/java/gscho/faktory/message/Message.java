package gscho.faktory.message;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Message{

	@JsonIgnore
	public String preamble;

	@JsonIgnore
	public String body;

	public Message(){}

	public Message( String preamble, String body ){
		super();
		this.preamble = preamble;
		this.body = body;
	}

	public String getPreamble(){
		return preamble;
	}

	public void setPreamble( String preamble ){
		this.preamble = preamble;
	}

	public String getBody(){
		return body;
	}

	public void setBody( String body ){
		this.body = body;
	}

	@Override
	public String toString(){
		return "Message [preamble=" + preamble + ", body=" + body + "]";
	}

}
