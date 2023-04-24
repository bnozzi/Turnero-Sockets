
package sockets;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import java.lang.Thread;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Servidor extends ConexionSockets {
    private int numeroTurno = 0;
    private boolean activo = true;
    private List<ClienteThread> clientes = new ArrayList<>();

    public Servidor() throws IOException {
        super("servidor");
    }

    public void StartServer() throws IOException {

        try {
            System.out.println("Turno actual: " + numeroTurno);

            while (true) {
                // Espera a que un cliente se conecte
                socketCliente = socketServidor.accept();

                // Crea una nueva instancia de la clase ClienteThread para manejar la
                // comunicación con este cliente
                ClienteThread clienteThread = new ClienteThread(socketCliente, this);

                // Agrega el cliente a la lista de clientes
                clientes.add(clienteThread);
                // Inicia un nuevo hilo para manejar la comunicación con este cliente
                new Thread(clienteThread).start();


            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    // getter numeroTurno
    public int getNumeroTurno() {
        return this.numeroTurno;
    }

    // aumentar en uno numeroTurno
    public void aumentarTurno() {
        this.numeroTurno++;
    }

    
    public void cerrarClientes() {
        System.out.println("Cerrando conexiones...");
        for (ClienteThread cliente : clientes) {
            try {
                cliente.getSocket().close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        System.out.println("Servidor cerrado");

    
    }

}

class ClienteThread extends Thread {

    private Socket socketCliente;
    private DataOutputStream salidaCliente;
    private BufferedReader entrada;
    private Servidor server;

    public ClienteThread(Socket socketCliente, Servidor server) {
        this.socketCliente = socketCliente;
        this.server = server;
    }

    public Socket getSocket() {
        return socketCliente;
    }

    public void run() {
        try {
            // Se obtiene el flujo de salida del cliente
            salidaCliente = new DataOutputStream(socketCliente.getOutputStream());
            // Se obtiene el flujo entrante desde el cliente
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
            String mensajeServidor;

            // Mientras haya mensajes del cliente
            while ((mensajeServidor = entrada.readLine()) != null) {
                // Mostramos los mensajes recibidos
                //System.out.println(mensajeServidor);
                //aumentamos el turno y le notificamos al cliente 
                server.aumentarTurno();
                salidaCliente.writeUTF("turno aumentado exitosamente \n Turno actual: " + this.server.getNumeroTurno());
                //mostramos el turno actual por pantalla
                System.out.println("Turno  actual: " + server.getNumeroTurno() + "\n");
                // Una vez que termino de leer los mensajes
                // Finalizamos la conexión
            }
        
        server.socketServidor.close();
        server.cerrarClientes();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}
