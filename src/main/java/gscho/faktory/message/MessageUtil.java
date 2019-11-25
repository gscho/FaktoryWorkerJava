package gscho.faktory.message;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class MessageUtil{

	private static final ObjectMapper mapper = new ObjectMapper().setSerializationInclusion( Include.NON_NULL ).configure( SerializationFeature.FAIL_ON_EMPTY_BEANS, false );

	public static Message fromString( String message, Class<Message> klass ) throws Exception{
		if( message.equals( "" ) || message == null ){
			return null; //nil response is common when fetching work
		}
		String preamble = null;
		String body = null;
		int firstWhitespace = message.indexOf( " " );
		int firstPlusSign = message.indexOf( "+" ) == -1 ? 0 : message.indexOf( "+" ) + 1;
		boolean hasBody = firstWhitespace > -1;
		if( hasBody ){
			// The 0th index is always '+' so skip it.
			preamble = message.substring( firstPlusSign, firstWhitespace ).trim();
			body = message.substring( firstWhitespace, message.length() ).trim();
		}
		else{
			preamble = message.substring( firstPlusSign, message.length() ).trim();
		}

		try{
			Message msg = (Message) klass.getConstructor().newInstance();
			if( !preamble.equals( msg.getPreamble() ) ){
				throw new Exception( String.format( "Expected %s but received %s", msg.getPreamble(), preamble ) );
			}
			if( body != null ){
				msg = mapper.readValue( body, klass );
			}
			return msg;
		}
		catch( JsonParseException e ){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch( JsonMappingException e ){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch( IOException e ){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch( InstantiationException e ){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch( IllegalAccessException e ){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch( IllegalArgumentException e ){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch( InvocationTargetException e ){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch( NoSuchMethodException e ){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch( SecurityException e ){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

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
