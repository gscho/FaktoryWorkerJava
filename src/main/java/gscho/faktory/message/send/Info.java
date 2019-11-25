package gscho.faktory.message.send;

import com.fasterxml.jackson.annotation.JsonIgnore;

import gscho.faktory.message.Message;

public class Info extends Message{
	
//	{"faktory":{"queues":{},"tasks":{"Busy":{"reaped":0,"size":0},"Dead":{"cycles":4,"enqueued":0,"size":0,"wall_time_sec":0.000613151},"Retries":{"cycles":51,"enqueued":0,"size":0,"wall_time_sec":0.008712981},"Scheduled":{"cycles":51,"enqueued":0,"size":0,"wall_time_sec":0.013554958},"Workers":{"reaped":0,"size":0}},"total_enqueued":0,"total_failures":0,"total_processed":0,"total_queues":0},"server":{"command_count":3,"connections":1,"description":"Faktory","faktory_version":"1.1.0","uptime":257,"used_memory_mb":"68 MB"},"server_utc_time":"11:18:44 UTC"}


	@Override
	@JsonIgnore
	public String getPreamble(){
		return "INFO";
	}
}
