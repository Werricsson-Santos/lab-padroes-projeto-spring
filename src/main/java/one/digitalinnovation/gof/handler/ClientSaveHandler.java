package one.digitalinnovation.gof.handler;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import one.digitalinnovation.gof.model.Cliente;
import one.digitalinnovation.gof.model.ClienteRepository;

@Component
public class ClientSaveHandler implements Handler {
    private Handler nextHandler;
    private ClienteRepository clienteRepository;

    @Autowired
    public ClientSaveHandler(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public void setNextHandler(Handler handler) {
        this.nextHandler = handler;
    }

    @Override
    public void handle(Cliente cliente) {
    	clienteRepository.save(cliente);
    	
        if (nextHandler != null) {
            nextHandler.handle(cliente);
        }
    }
}

