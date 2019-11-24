package gscho.faktory.message.receive;

import com.fasterxml.jackson.annotation.JsonIgnore;

import gscho.faktory.message.Message;

public class Hi extends Message{

	Integer v;
	String s;
	Integer i;

	public Integer getV(){
		return v;
	}

	public void setV( Integer v ){
		this.v = v;
	}

	public String getS(){
		return s;
	}

	public void setS( String s ){
		this.s = s;
	}

	public Integer getI(){
		return i;
	}

	public void setI( Integer i ){
		this.i = i;
	}

	@Override
	@JsonIgnore
	public String getPreamble(){
		return "HI";
	}

}
