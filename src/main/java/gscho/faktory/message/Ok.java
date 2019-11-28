package gscho.faktory.message;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Ok extends Message{

	@Override
	@JsonIgnore
	public String getPreamble(){
		return "OK";
	}
}
