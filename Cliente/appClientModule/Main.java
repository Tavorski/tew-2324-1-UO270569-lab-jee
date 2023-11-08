import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

import javax.net.ssl.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class Main {
	public static void main(String[] args) throws NoSuchAlgorithmException, KeyManagementException{
/*
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("https://opendata.gijon.es/descargar.php?id=941&tipo=JSON");
*/
		
		SSLContext sslContext = SSLContext.getInstance("TLS");
		sslContext.init(null, new TrustManager[] { new NoOpTrustManager() }, null);
		Client client = ClientBuilder.newBuilder().sslContext(sslContext).build();
		WebTarget target = client.target("https://opendata.gijon.es/descargar.php?id=941&tipo=JSON");
		String result = target.request().get().readEntity(String.class);

		System.out.println(result);

		/*
		 * El código para acceder al servicio y luego tratar los datos. Los objetos de
		 * las clases JSONObjects (mapas) y JSONArray (arrays)
		 */
		// Imprimimos todo el flujo JSON recibido en formato cadena.
		System.out.println("-----------TODOS----------------");
		System.out.println(result);
		// Procesamos el texto JSON y lo pasamos a formato SIMPLE-JSON
		
		JSONObject json = (JSONObject) JSONValue.parse(result);
		System.out.println("JSONObject parseado: "+json);
		JSONObject datos = ((JSONObject) json.get("datos"));
		System.out.println("JSONObject \"datos\": "+datos);
		JSONArray dato = ((JSONArray) datos.get("dato"));
		System.out.println("JSONArray \"dato\": "+dato);
		// Imprimimos el contacto tercero (2) transformándolo a formato cadena.
		System.out.println("----------- Equipo ----------------");
		System.out.println(dato.get(2).toString());
		System.out.println("----------- Ubicación de un equipo ----------------");
		JSONObject equipo = (JSONObject) JSONValue.parse(dato.get(2).toString());
		String ubi = (String) equipo.get("ubicacion").toString();
		System.out.println(ubi);
	}

	/*
	 * (non-Java-doc)
	 * 
	 * @see java.lang.Object#Object()
	 */
	public Main() {
		super();
	}


}

class NoOpTrustManager implements X509TrustManager {
	public X509Certificate[] getAcceptedIssuers() {
		return null;
	}

	public void checkClientTrusted(X509Certificate[] certs, String authType) {
	}

	public void checkServerTrusted(X509Certificate[] certs, String authType) {
	}
}