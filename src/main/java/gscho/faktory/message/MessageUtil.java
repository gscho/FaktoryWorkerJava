package gscho.faktory.message;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class MessageUtil{

	private static final ObjectMapper mapper = new ObjectMapper().setSerializationInclusion( Include.NON_NULL ).configure( SerializationFeature.FAIL_ON_EMPTY_BEANS, false );


	public static String toString( Message message ){
		try{
			String json = mapper.writeValueAsString( message );
			if( json.equals( "{}" ) ){
				return String.format( "%s", message.getPreamble() );
			}
			else{
				return String.format( "%s %s", message.getPreamble(), json );
			}
		}
		catch( JsonProcessingException jsonProcessing ){
			jsonProcessing.printStackTrace();
		}
		return null;

	}

}
