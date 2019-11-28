package gscho.faktory.message;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Info extends Message{

	//	{"faktory":{"queues":{},"tasks":{"Busy":{"reaped":0,"size":0},"Dead":{"cycles":4,"enqueued":0,"size":0,"wall_time_sec":0.000613151},"Retries":{"cycles":51,"enqueued":0,"size":0,"wall_time_sec":0.008712981},"Scheduled":{"cycles":51,"enqueued":0,"size":0,"wall_time_sec":0.013554958},"Workers":{"reaped":0,"size":0}},"total_enqueued":0,"total_failures":0,"total_processed":0,"total_queues":0},"server":{"command_count":3,"connections":1,"description":"Faktory","faktory_version":"1.1.0","uptime":257,"used_memory_mb":"68 MB"},"server_utc_time":"11:18:44 UTC"}

	static class Faktory{

		public Map<String, Object> queues;

		public Tasks tasks;

		Integer total_enqueued;

		Integer total_failures;

		Integer total_processed;

		Integer total_queues;

		public Faktory(){}

		public Map<String, Object> getQueues(){
			return queues;
		}

		public void setQueues( Map<String, Object> queues ){
			this.queues = queues;
		}

		public Tasks getTasks(){
			return tasks;
		}

		public void setTasks( Tasks tasks ){
			this.tasks = tasks;
		}

		public Integer getTotal_enqueued(){
			return total_enqueued;
		}

		public void setTotal_enqueued( Integer total_enqueued ){
			this.total_enqueued = total_enqueued;
		}

		public Integer getTotal_failures(){
			return total_failures;
		}

		public void setTotal_failures( Integer total_failures ){
			this.total_failures = total_failures;
		}

		public Integer getTotal_processed(){
			return total_processed;
		}

		public void setTotal_processed( Integer total_processed ){
			this.total_processed = total_processed;
		}

		public Integer getTotal_queues(){
			return total_queues;
		}

		public void setTotal_queues( Integer total_queues ){
			this.total_queues = total_queues;
		}

	}

	static class Tasks{

		@JsonProperty( "Busy" )
		TaskDetails busy;

		@JsonProperty( "Dead" )
		TaskDetails dead;

		@JsonProperty( "Retries" )
		TaskDetails retries;

		@JsonProperty( "Scheduled" )
		TaskDetails scheduled;

		@JsonProperty( "Workers" )
		TaskDetails workers;

		public Tasks(){}

		static class TaskDetails{

			Integer cycles;

			Integer enqueued;

			Integer reaped;

			Float wall_time_sec;

			Integer size;

			public TaskDetails(){}

			public Integer getCycles(){
				return cycles;
			}

			public void setCycles( Integer cycles ){
				this.cycles = cycles;
			}

			public Integer getEnqueued(){
				return enqueued;
			}

			public void setEnqueued( Integer enqueued ){
				this.enqueued = enqueued;
			}

			public Integer getReaped(){
				return reaped;
			}

			public void setReaped( Integer reaped ){
				this.reaped = reaped;
			}

			public Float getWall_time_sec(){
				return wall_time_sec;
			}

			public void setWall_time_sec( Float wall_time_sec ){
				this.wall_time_sec = wall_time_sec;
			}

			public Integer getSize(){
				return size;
			}

			public void setSize( Integer size ){
				this.size = size;
			}

		}

		public TaskDetails getBusy(){
			return busy;
		}

		public void setBusy( TaskDetails busy ){
			this.busy = busy;
		}

		public TaskDetails getDead(){
			return dead;
		}

		public void setDead( TaskDetails dead ){
			this.dead = dead;
		}

		public TaskDetails getRetries(){
			return retries;
		}

		public void setRetries( TaskDetails retries ){
			this.retries = retries;
		}

		public TaskDetails getScheduled(){
			return scheduled;
		}

		public void setScheduled( TaskDetails scheduled ){
			this.scheduled = scheduled;
		}

		public TaskDetails getWorkers(){
			return workers;
		}

		public void setWorkers( TaskDetails workers ){
			this.workers = workers;
		}

	}

	static class Server{

		Integer command_count;
		Integer connections;
		String description;
		String faktory_version;
		Integer uptime;
		String used_memory_mb;

		public Server(){}

		public Integer getCommand_count(){
			return command_count;
		}

		public void setCommand_count( Integer command_count ){
			this.command_count = command_count;
		}

		public Integer getConnections(){
			return connections;
		}

		public void setConnections( Integer connections ){
			this.connections = connections;
		}

		public String getDescription(){
			return description;
		}

		public void setDescription( String description ){
			this.description = description;
		}

		public String getFaktory_version(){
			return faktory_version;
		}

		public void setFaktory_version( String faktory_version ){
			this.faktory_version = faktory_version;
		}

		public Integer getUptime(){
			return uptime;
		}

		public void setUptime( Integer uptime ){
			this.uptime = uptime;
		}

		public String getUsed_memory_mb(){
			return used_memory_mb;
		}

		public void setUsed_memory_mb( String used_memory_mb ){
			this.used_memory_mb = used_memory_mb;
		}

	}

	public Faktory faktory;

	public Server server;

	public String server_utc_time;

	public Info(){}

	public Faktory getFaktory(){
		return faktory;
	}

	public void setFaktory( Faktory faktory ){
		this.faktory = faktory;
	}

	public Server getServer(){
		return server;
	}

	public void setServer( Server server ){
		this.server = server;
	}

	public String getServer_utc_time(){
		return server_utc_time;
	}

	public void setServer_utc_time( String server_utc_time ){
		this.server_utc_time = server_utc_time;
	}

	@Override
	public String getPreamble(){
		return "INFO";
	}
}
