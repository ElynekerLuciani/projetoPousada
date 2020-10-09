/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import componentes.BlocoComponent;
import dao.QuartoDAO;
import dao.ReservaDAO;
import java.awt.Color;
import java.awt.event.ActionEvent;

/**
 *
 * @author Elyneker Luciani
 */
public class BlocoComponentController {
    private BlocoComponent component = new BlocoComponent();
     private static final Color COR_QUARTO_LIVRE = new Color(255, 255, 255);
    private static final Color COR_QUARTO_OCUPADO = new Color(248,215,218);
    private static final Color COR_QUARTO_SUJO = new Color(254, 153, 0);
    private final PrincipalController principal = PrincipalController.getInstancia();
    private final QuartoDAO quartoDAO = new QuartoDAO();
    private final ReservaDAO reservaDAO = new ReservaDAO();
    
    
    
    public BlocoComponentController(BlocoComponent component) {
        this.component = component;
    }
    
     public void executa(ActionEvent evt) {
        switch(evt.getActionCommand()) {
            case "Hospedar":
                realizarReserva();
                break;
            case "Informações":
                exibirInformacaoDeReserva();
                break;
            case "Limpar":
                limparQuarto();
                break;
                
        }
    }
    
    
     public void alterarStatus(boolean statusQuarto, boolean sujo) {
        //se a variável for true, o quarto está ocupado, senão está livre
//        if (sujo && statusQuarto) {
//            component.setBackground(COR_QUARTO_OCUPADO);
//            component.getStatus().setText("Sujo");
//            component.getBtnLimpar().setVisible(true);
//            component.getBtnLimpar().setText("Limpar");
//            
//            
//        } else if(statusQuarto) {
//            component.setBackground(COR_QUARTO_OCUPADO);
//            component.getStatus().setText("Ocupado");
//            component.getBtnLimpar().setVisible(true);
//            component.getBtnLimpar().setText("Informações");
//        } else {
//            component.setBackground(COR_QUARTO_LIVRE);
//            component.getStatus().setText("Livre");
//            component.getBtnLimpar().setText("Hospedar");
//            component.getBtnLimpar().setVisible(true);
//        }
        
        if(statusQuarto) {
            if(sujo) {
                component.setBackground(COR_QUARTO_SUJO);
                component.getStatus().setText("Sujo");
                component.getBtnLimpar().setVisible(true);
                component.getBtnLimpar().setText("Limpar");
            }else {
                component.setBackground(COR_QUARTO_OCUPADO);
                component.getStatus().setText("Ocupado");
                component.getBtnLimpar().setVisible(true);
                component.getBtnLimpar().setText("Informações");
            }
        } else {
            component.setBackground(COR_QUARTO_LIVRE);
            component.getStatus().setText("Livre");
            component.getBtnLimpar().setText("Hospedar");
            component.getBtnLimpar().setVisible(true);
        }
        
        
       
    }
    
     private void realizarReserva() {
         principal.exibirPainelCadastrarCliente(
                 component.getQuarto().getIdQuarto(),
                 component.getQuarto().getNumeroQuarto(), 
                 component.getQuarto().getCategoria().getIdCategoriaQuarto());
     }

    private void exibirInformacaoDeReserva() {
        principal.exibirPainelDadosReserva(component.getQuarto().getIdQuarto(), component.getQuarto().getNumeroQuarto());
        System.out.println(component.getQuarto().getNumeroQuarto());
       
    }
    
    private void limparQuarto() {
        System.out.println("limpar quarto " + component.getQuarto().getIdQuarto());
        try {
            quartoDAO.removerManutencaoQuarto(component.getQuarto().getIdQuarto());
            reservaDAO.alterarStatusReserva(component.getQuarto().getNumeroQuarto());
            principal.exibirContainerQuarto();
        } catch (Exception e) {
            System.out.println("controller.BlocoComponentController.limparQuarto " + e);
        }
    }
    
      
   
     
     
}
