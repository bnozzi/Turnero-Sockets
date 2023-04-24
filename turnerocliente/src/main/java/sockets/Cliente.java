/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sockets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author bruno
 */
public class Cliente extends ConexionSockets {

    public Cliente() throws IOException {
        super("Cliente");
    }

    // iniciar cliente
    public void StartCliente() {
        try {
            // flujo de datos para el servidor
            salidaServidor = new DataOutputStream(socketCliente.getOutputStream());
            //respuestas del servidor 
            DataInputStream entradaServidor = new DataInputStream(socketCliente.getInputStream());

  

            
            // para escribir por consola el mensaje 
            Scanner scanner = new Scanner(System.in);
            String mensaje;
            do {
                if (this.socketCliente.isClosed()){
                    break;
                }
                System.out.println("Escribe un mensaje para el servidor (o 'fin' para terminar): ");
                mensaje = scanner.nextLine();
                if(mensaje=="fin" ){
                    break;
                }
                // Enviar mensaje al servidor
                salidaServidor.writeUTF(mensaje+"\n");
                
                // Recibir respuesta del servidor
                System.out.println("mensaje del servidor: " +entradaServidor.readUTF());
                
            } while (!mensaje.equals("fin"));

            // cerrar el socket
            cerrarSocketCliente();
            
        

        } catch (IOException e) {
            System.out.println(e.getMessage());

        } 
        
    }
    public void cerrarSocketCliente() {
        try {
            this.socketCliente.close();
        } catch (IOException e) {
            e.printStackTrace();
        }            
    }
}
