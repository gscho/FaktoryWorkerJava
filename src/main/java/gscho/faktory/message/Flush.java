package gscho.faktory.message;

public class Flush extends Message{
	
	@Override
	public String getPreamble(){
		return "FLUSH";
	}

}
