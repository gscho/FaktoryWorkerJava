package gscho.faktory.message.receive;

import com.fasterxml.jackson.annotation.JsonIgnore;

import gscho.faktory.message.Message;

public class Ok extends Message{

	@Override
	@JsonIgnore
	public String getPreamble(){
		return "OK";
	}
}
