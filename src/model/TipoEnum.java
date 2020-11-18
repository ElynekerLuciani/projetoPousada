package model;

/**
 *
 * @author Elyneker Luciani
 */
public enum TipoEnum {

    PF {
        @Override
        public ClienteStrategy cadastrarNovoCliente() {
            return new PessoaFisica();
        }
    },
    PJ {
        @Override
        public ClienteStrategy cadastrarNovoCliente() {
            return new PessoaJuridica();
        }
    },
    ES {
        @Override
        public ClienteStrategy cadastrarNovoCliente() {
            return new Estrangeiro();
        }
    };

    public abstract ClienteStrategy cadastrarNovoCliente();

}
