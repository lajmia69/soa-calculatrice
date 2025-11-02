package serveur;

import jakarta.xml.ws.Endpoint;
import service.Calculatrice;

public class ServeurJWS {
    public static void main(String[] args) {
        String url = "http://localhost:8888/ws/calculatrice";
        Endpoint.publish(url, new Calculatrice());
        System.out.println("Calculatrice SOAP Web Service deployed at: " + url + "?wsdl");
    }
}
