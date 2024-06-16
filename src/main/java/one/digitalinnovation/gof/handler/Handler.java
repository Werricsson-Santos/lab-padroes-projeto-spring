package one.digitalinnovation.gof.handler;

import one.digitalinnovation.gof.model.Cliente;

public interface Handler {
    void setNextHandler(Handler handler);
    void handle(Cliente cliente);
}