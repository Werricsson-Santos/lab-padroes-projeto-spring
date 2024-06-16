package one.digitalinnovation.gof.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import one.digitalinnovation.gof.model.Cliente;
import one.digitalinnovation.gof.model.Endereco;
import one.digitalinnovation.gof.model.EnderecoRepository;

@Component
public class AddressVerificationHandler implements Handler {
    private Handler nextHandler;
    private EnderecoRepository enderecoRepository;

    @Autowired
    public AddressVerificationHandler(EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }

    @Override
    public void setNextHandler(Handler handler) {
        this.nextHandler = handler;
    }

    @Override
    public void handle(Cliente cliente) {
        String cep = cliente.getEndereco().getCep();
        Endereco endereco = enderecoRepository.findById(cep).orElse(null);
        if (endereco != null) {
            cliente.setEndereco(endereco);
        } 
        if (nextHandler != null) {
            nextHandler.handle(cliente);
        }
    }
}

