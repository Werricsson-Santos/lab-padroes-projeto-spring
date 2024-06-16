package one.digitalinnovation.gof.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import one.digitalinnovation.gof.model.Cliente;
import one.digitalinnovation.gof.model.Endereco;
import one.digitalinnovation.gof.model.EnderecoRepository;
import one.digitalinnovation.gof.service.ViaCepService;

@Component
public class ViaCepHandler implements Handler {
    private Handler nextHandler;
    private ViaCepService viaCepService;
    private EnderecoRepository enderecoRepository;

    @Autowired
    public ViaCepHandler(ViaCepService viaCepService, EnderecoRepository enderecoRepository) {
        this.viaCepService = viaCepService;
        this.enderecoRepository = enderecoRepository;
    }

    @Override
    public void setNextHandler(Handler handler) {
        this.nextHandler = handler;
    }

    @Override
    public void handle(Cliente cliente) {
        if (cliente.getEndereco().getBairro() == null) {
            String cep = cliente.getEndereco().getCep();
            Endereco novoEndereco = viaCepService.consultarCep(cep);
            enderecoRepository.save(novoEndereco);
            cliente.setEndereco(novoEndereco);
        }
        if (nextHandler != null) {
            nextHandler.handle(cliente);
        }
    }
}

