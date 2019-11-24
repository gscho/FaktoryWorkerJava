package gscho.faktory.message.send;

import com.fasterxml.jackson.annotation.JsonIgnore;

import gscho.faktory.message.Message;

public class Hello extends Message{

	String hostname;
	String wid;
	Integer pid;
	String[] labels;
	String pwdhash;
	Integer v;

	public Hello(){}

	public Hello( Integer v ){
		this.v = v;
	}

	public String getHostname(){
		return hostname;
	}

	public void setHostname( String hostname ){
		this.hostname = hostname;
	}

	public String getWid(){
		return wid;
	}

	public void setWid( String wid ){
		this.wid = wid;
	}

	public Integer getPid(){
		return pid;
	}

	public void setPid( Integer pid ){
		this.pid = pid;
	}

	public String[] getLabels(){
		return labels;
	}

	public void setLabels( String[] labels ){
		this.labels = labels;
	}

	public String getPwdhash(){
		return pwdhash;
	}

	public void setPwdhash( String pwdhash ){
		this.pwdhash = pwdhash;
	}

	public Integer getV(){
		return v;
	}

	public void setV( Integer v ){
		this.v = v;
	}

	@Override
	@JsonIgnore
	public String getPreamble(){
		return "HELLO";
	}

}
