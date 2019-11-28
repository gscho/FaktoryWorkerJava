package gscho.faktory.message;

public class Beat extends Message{

	String wid;

	public Beat(){}

	public Beat( String wid ){
		this.wid = wid;
	}

	public String getWid(){
		return wid;
	}

	public void setWid( String wid ){
		this.wid = wid;
	}

	@Override
	public String getPreamble(){
		return "BEAT";
	}

}
