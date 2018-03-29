package helper;

import java.nio.charset.StandardCharsets;

import org.apache.commons.codec.binary.Base64;
import org.json.JSONException;
import org.json.JSONObject;


public class AuthToken {

	public static String getAuthenticatedUser(String authHeader) {
		String token = authHeader.split(" ")[1];
		final String[] id_token_parts  = token.split("\\.");
		
		final String ID_TOKEN_HEADER = base64UrlDecode(id_token_parts[0]);
	    final String ID_TOKEN_PAYLOAD = base64UrlDecode(id_token_parts[1]);
	    final byte[] ID_TOKEN_SIGNATURE = base64UrlDecodeToBytes(id_token_parts[2]);

	    
	    JSONObject obj;
		try {
			obj = new JSONObject(ID_TOKEN_PAYLOAD);
			String username = obj.getString("sub");
			return username;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
		
	public static String base64UrlDecode(String input) {
        byte[] decodedBytes = base64UrlDecodeToBytes(input);
        String result = new String(decodedBytes, StandardCharsets.UTF_8);
        return result;
    }
	
	public static byte[] base64UrlDecodeToBytes(String input) {
        Base64 decoder = new Base64(-1, null, true);
        byte[] decodedBytes = decoder.decode(input);

        return decodedBytes;
    }
	
}
