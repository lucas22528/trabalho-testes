package item;

public class LimiteItensExcedidoException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public LimiteItensExcedidoException(final String mensagem) {
        super(mensagem);
    }
}

