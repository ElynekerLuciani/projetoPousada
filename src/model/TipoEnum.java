/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
