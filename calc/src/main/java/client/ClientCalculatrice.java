package client;

import java.net.URL;
import java.util.Scanner;
import javax.xml.namespace.QName;
import jakarta.xml.ws.Service;
import service.ICalculatrice;

public class ClientCalculatrice {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        try {
            // URL du WSDL
            URL wsdlURL = new URL("http://localhost:8888/ws/calculatrice?wsdl");
            
            // QName du service
            QName qname = new QName("http://service/", "CalculatriceService");
            
            // Créer le service
            Service service = Service.create(wsdlURL, qname);
            
            // Obtenir le port (proxy)
            ICalculatrice calculatrice = service.getPort(ICalculatrice.class);
            
            System.out.println("=== Calculatrice SOAP Web Service ===");
            System.out.println("Operations disponibles: +, -, *, /");
            System.out.println("Tapez 'stop' pour quitter\n");
            
            while (true) {
                try {
                    System.out.print("Entrez le premier nombre (ou 'stop' pour quitter): ");
                    String input1 = scanner.nextLine().trim();
                    
                    if (input1.equalsIgnoreCase("stop")) {
                        System.out.println("Au revoir!");
                        break;
                    }
                    
                    double a = Double.parseDouble(input1);
                    
                    System.out.print("Entrez l'opération (+, -, *, /): ");
                    String operation = scanner.nextLine().trim();
                    
                    if (operation.equalsIgnoreCase("stop")) {
                        System.out.println("Au revoir!");
                        break;
                    }
                    
                    System.out.print("Entrez le deuxième nombre: ");
                    String input2 = scanner.nextLine().trim();
                    
                    if (input2.equalsIgnoreCase("stop")) {
                        System.out.println("Au revoir!");
                        break;
                    }
                    
                    double b = Double.parseDouble(input2);
                    
                    double resultat = 0;
                    boolean operationValide = true;
                    
                    switch (operation) {
                        case "+":
                            resultat = calculatrice.somme(a, b);
                            System.out.println("\nRésultat: " + a + " + " + b + " = " + resultat);
                            break;
                        case "-":
                            resultat = calculatrice.soustraction(a, b);
                            System.out.println("\nRésultat: " + a + " - " + b + " = " + resultat);
                            break;
                        case "*":
                            resultat = calculatrice.multiplication(a, b);
                            System.out.println("\nRésultat: " + a + " * " + b + " = " + resultat);
                            break;
                        case "/":
                            resultat = calculatrice.division(a, b);
                            System.out.println("\nRésultat: " + a + " / " + b + " = " + resultat);
                            break;
                        default:
                            System.out.println("\nErreur: Opération invalide! Utilisez +, -, *, ou /");
                            operationValide = false;
                    }
                    
                    if (operationValide) {
                        System.out.println("------------------------------------\n");
                    }
                    
                } catch (NumberFormatException e) {
                    System.out.println("\nErreur: Veuillez entrer un nombre valide!\n");
                } catch (Exception e) {
                    System.out.println("\nErreur: " + e.getMessage() + "\n");
                }
            }
            
        } catch (Exception e) {
            System.out.println("Erreur de connexion au service: " + e.getMessage());
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}