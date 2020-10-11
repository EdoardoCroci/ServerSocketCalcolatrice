package com.mycompany.ServerSocketCalcolatrice;

import java.io.*;
import java.net.*;

public class Server {
    ServerSocket server = null;
    Socket client = null;
    String receivedString = null;
    String modifiedString = null;
    BufferedReader inputClient; //input dal cliente
    DataOutputStream outputClient;  //output verso cliente
    int tot;
    
    public Socket attendi() {
        try
        {
            System.out.println("1, server in esecuzione...");
            server = new ServerSocket(8000);
            client = server.accept();
            server.close();
            inputClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
            outputClient = new DataOutputStream(client.getOutputStream());
        }
        catch(Exception ex) 
        {
            System.out.println(ex.getMessage());
            System.out.println("Errore durante l'istanza del server!");
            System.exit(1);
        }
        return client;
    }
    
    public void comunica() {
        try
        {
            while(true) {
                System.out.println("3, benvenuto client, inserisci il primo valore. Attendo...");
                receivedString = inputClient.readLine();
                int op1 = Integer.decode(receivedString);
                System.out.println("3.1, benvenuto client, inserisci il secondo valore. Attendo...");
                receivedString = inputClient.readLine();
                int op2 = Integer.decode(receivedString);
                System.out.println("3.2, benvenuto client, scrivi S(per somma), D(per divisione), M(per sottrazione), X(per moltiplicazione). Attendo...");
                receivedString = inputClient.readLine();
                switch(receivedString) {
                    case "S": 
                        tot = op1 + op2;
                        System.out.println("Il risultato della somma tra " + op1 + " e " + op2 + " è: " + (op1+op2));
                        break;
                    case "D": 
                        tot = op1 / op2;
                        System.out.println("Il risultato della divisione tra " + op1 + " e " + op2 + " è: " + (op1/op2));
                        break;
                    case "M": 
                        tot = op1 - op2;
                        System.out.println("Il risultato della sottrazione tra " + op1 + " e " + op2 + " è: " + (op1-op2));
                        break;
                    case "X": 
                        tot = op1 * op2;
                        System.out.println("Il risultato della Moltiplicazione tra " + op1 + " e " + op2 + " è: " + (op1*op2));
                        break;
                    default: 
                        break;
                }
                break;
            }
            
            outputClient.writeBytes(String.valueOf(tot) + '\n');
            System.out.println("9, SERVER: fine elaborazione... buona notte!");
            client.close();
        }
        catch (Exception ex) 
        {
            System.out.println(ex.getMessage());
        }
    }
    
    public static void main(String args[]) { 
        Server server = new Server();
        server.attendi();
        server.comunica();
    }
}