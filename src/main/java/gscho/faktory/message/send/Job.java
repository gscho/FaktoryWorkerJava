package gscho.faktory.message.send;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;

import gscho.faktory.message.Message;

public class Job extends Message{

	String jid;
	String jobType;
	Object[] args;
	String queue;
	String reserve_for;
	String at;
	Integer retry;
	Integer backtrace;
	String created_at;
	String enqueued_at;
	Map<String, Object> failure;
	Map<String, Object> custom;

	public Job( String jid, String jobType, Object[] args ){
		this.jid = jid;
		this.jobType = jobType;
		this.args = args;
	}

	public String getJid(){
		return jid;
	}

	public void setJid( String jid ){
		this.jid = jid;
	}

	public String getJobType(){
		return jobType;
	}

	public void setJobType( String jobType ){
		this.jobType = jobType;
	}

	public Object[] getArgs(){
		return args;
	}

	public void setArgs( Object[] args ){
		this.args = args;
	}

	public String getQueue(){
		return queue;
	}

	public void setQueue( String queue ){
		this.queue = queue;
	}

	public String getReserve_for(){
		return reserve_for;
	}

	public void setReserve_for( String reserve_for ){
		this.reserve_for = reserve_for;
	}

	public String getAt(){
		return at;
	}

	public void setAt( String at ){
		this.at = at;
	}

	public Integer getRetry(){
		return retry;
	}

	public void setRetry( Integer retry ){
		this.retry = retry;
	}

	public Integer getBacktrace(){
		return backtrace;
	}

	public void setBacktrace( Integer backtrace ){
		this.backtrace = backtrace;
	}

	public String getCreated_at(){
		return created_at;
	}

	public void setCreated_at( String created_at ){
		this.created_at = created_at;
	}

	public String getEnqueued_at(){
		return enqueued_at;
	}

	public void setEnqueued_at( String enqueued_at ){
		this.enqueued_at = enqueued_at;
	}

	public Map<String, Object> getFailure(){
		return failure;
	}

	public void setFailure( Map<String, Object> failure ){
		this.failure = failure;
	}

	public Map<String, Object> getCustom(){
		return custom;
	}

	public void setCustom( Map<String, Object> custom ){
		this.custom = custom;
	}

	@Override
	@JsonIgnore
	public String getPreamble(){
		return "PUSH";
	}

}
