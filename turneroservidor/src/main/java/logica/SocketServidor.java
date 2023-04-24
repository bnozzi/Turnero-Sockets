package logica;

import java.io.IOException;

import sockets.Servidor;

public class SocketServidor {
    public static void main(String[] args) throws IOException  {
         
        //instancio servidor 
        Servidor servidor = new Servidor();
        //pruebo si inicio 
        System.out.println("Iniciando Servidor... \n"  );
        //inicio el servidor, espero mensajes y cuando recibo uno cierro la conexion 
        servidor.StartServer();

    }
}
