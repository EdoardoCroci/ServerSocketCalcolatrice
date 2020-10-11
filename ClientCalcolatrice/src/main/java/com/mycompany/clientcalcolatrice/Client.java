package com.mycompany.clientcalcolatrice;

import java.io.*;
import java.net.*;

public class Client {
   int portaServer = 8000;
   Socket mySocket;
   BufferedReader tastiera;
   String userString;
   String serverString;
   DataOutputStream outputServer; //output verso server
   BufferedReader inputServer; //input dal server
   
   public Socket connetti() {
       System.out.println("2, Client in esecuzione...");
       try
       {
           tastiera = new BufferedReader(new InputStreamReader(System.in));
           mySocket = new Socket(InetAddress.getLocalHost(), portaServer);
           outputServer = new DataOutputStream(mySocket.getOutputStream());
           inputServer = new BufferedReader(new InputStreamReader(mySocket.getInputStream()));
       }
       catch(Exception ex) 
       {
           System.out.println(ex.getMessage());
           System.out.println("Errore durante la connessione");
           System.exit(1);
       }
       
       return mySocket;
   }
   
   public void comunica() {
       try
       {
           System.out.println("4, inserisci il primo valore da trasmettere al server");
           userString = tastiera.readLine();
           
           System.out.println("5, invio il valore al server e attendo...");
           outputServer.writeBytes(userString + '\n');
           
           System.out.println("4.1, inserisci il secondo valore da trasmettere al server");
           userString = tastiera.readLine();
           
           System.out.println("5.1, invio il valore al server e attendo...");
           outputServer.writeBytes(userString + '\n');
           
           System.out.println("4.2, inserisci l'operatore da trasmettere al server");
           userString = tastiera.readLine();
           
           System.out.println("5.2, invio il valore al server e attendo...");
           outputServer.writeBytes(userString + '\n');
           
           serverString = inputServer.readLine();
           System.out.println("8, risposta dal server" + '\n' + serverString);
           
           System.out.println("9 CLIENT: termina elaborazione e chiude connessione");
           mySocket.close();
       }
       catch(Exception ex) 
       {
           System.out.println(ex.getMessage());
           System.out.println("Errore durante la comunicazione col server!");
           System.exit(1);
       }
   }
   
   public static void main(String args[]) {
       Client cliente = new Client();
       cliente.connetti();
       cliente.comunica();
   }
}
