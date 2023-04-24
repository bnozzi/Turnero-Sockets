package logica;
import java.io.IOException;

import sockets.Cliente;

public class SocketCaja {
    
public static void main(String[] args) throws IOException {
    //new client 
    Cliente cliente = new Cliente();

    //start client , send msg and close connection      
    cliente.StartCliente();
    
    
}

}
