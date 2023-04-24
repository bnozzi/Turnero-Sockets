/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sockets;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author bruno
 */
public class ConexionSockets {
    private final int puerto = 8086; // Puerto para conectar
    private final String host = "localhost"; // dirección ip para la conexión
    protected String mensajeServidor; // variable para msjes recibidos en el servidor
    protected ServerSocket socketServidor; // Socket del servidor
    protected Socket socketCliente; // Socket del cliente
    protected DataOutputStream salidaServidor, salidaCliente;

    public ConexionSockets(String tipo) throws IOException // Constructor
    {
        if (tipo.equalsIgnoreCase("servidor")) { // Creamos socket para el servidor
                                                 // se usa el puerto que configuramos anteriormente
            socketServidor = new ServerSocket(puerto);
            // creamos el socket para el cliente
            socketCliente = new Socket();
        } else {
            // Creamos socket para el cliente
            // le pasamos como parámetro el host y el puerto socketCliente = new Socket
            // (host, puerto);
            socketCliente = new Socket(host, puerto);

        }
    }

    // variables de salida
}
