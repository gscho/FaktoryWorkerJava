package gscho.faktory.message;

import java.util.Arrays;
import java.util.Map;

public class Job extends Message{

	String jid;
	String jobtype;
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

	public Job(){

	}

	public Job( String jid, String jobtype, Object[] args ){
		this.jid = jid;
		this.jobtype = jobtype;
		this.args = args;
	}

	public String getJid(){
		return jid;
	}

	public void setJid( String jid ){
		this.jid = jid;
	}

	public String getJobtype(){
		return jobtype;
	}

	public void setJobtype( String jobtype ){
		this.jobtype = jobtype;
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
	public String getPreamble(){
		return "PUSH";
	}

	@Override
	public String toString(){
		return "Job [jid=" + jid + ", jobType=" + jobtype + ", args=" + Arrays.toString( args ) + ", queue=" + queue + ", reserve_for=" + reserve_for + ", at=" + at + ", retry=" + retry + ", backtrace=" + backtrace + ", created_at=" + created_at + ", enqueued_at=" + enqueued_at + ", failure=" + failure + ", custom=" + custom + "]";
	}

}
