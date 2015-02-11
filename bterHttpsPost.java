import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.Mac;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;

class bterHttpsPost{
	private static final String KEY = "";
	private static final String SECRET = "";
	private static final String HMAC_SHA1_ALGORITHM = "HmacSHA512";
	
	public static String getSignature(String data,String mSECRET) throws Exception {
		 
		// get an hmac_sha1 key from the raw key bytes
		SecretKeySpec signingKey = new SecretKeySpec(SECRET.getBytes(), HMAC_SHA1_ALGORITHM);
		 
		// get an hmac_sha1 Mac instance and initialize with the signing key
		Mac mac = Mac.getInstance(HMAC_SHA1_ALGORITHM);
		mac.init(signingKey);
		 
		// compute the hmac on input data bytes
		byte[] rawHmac = mac.doFinal(data.getBytes());
		 
		return bytArrayToHex(rawHmac);
	}
	private static String bytArrayToHex(byte[] a) {
		StringBuilder sb = new StringBuilder();
		for(byte b: a)
			sb.append(String.format("%02x", b&0xff));
		return sb.toString();
	}
	public static String bter_query(String path,String mData) throws Exception{
		String mt = ""+(System.currentTimeMillis()*1000);
		URL obj = new URL("https://bter.com/api/" + path);
		HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
		String sign = getSignature(mData,SECRET);
		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
		con.setRequestProperty("KEY", KEY);
		con.setRequestProperty("SIGN", sign);
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(mData);
		wr.flush();
		wr.close();
		
		int responseCode = con.getResponseCode();
		
		BufferedReader in = new BufferedReader(
			       new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			 
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}

			in.close();
			 
			//return result
			return response.toString();
	}
}
