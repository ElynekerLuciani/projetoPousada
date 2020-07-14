package controller;

import container.ContainerMenuCliente;
import container.ContainerMenuFinanceiro;
import container.ContainerMenuHospedagem;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import container.ContainerQuarto;
import java.awt.event.ActionEvent;
import view.TelaHospedarCliente;
import view.TelaPrincipal;

/**
 *
 * @author Elyneker Luciani
 */
public class PrincipalController {
    private static PrincipalController principal;
    private TelaPrincipal telaInicial;
    private ContainerQuarto quartoComponente;
    
    private PrincipalController(){
        super();
    }
    
    public static PrincipalController getInstancia(){
        if(principal == null){
            principal = new PrincipalController();
        }
        return principal;
    }
    
    /**
     * Neste método atribuimos uma TelaPrincipal ao atributo privado 
     * desta classe.
     * @param tela
     */
    public void setPrincipal(TelaPrincipal tela) {
        this.telaInicial = tela;
    }
    
    public void setQuartoComponente(ContainerQuarto quartoComponente) {
        this.quartoComponente = quartoComponente;
    }
    
    /**
     * Este método é responsável por identificar qual botão na tela inicial foi
     * selecionado para poder chamar o menu correspondente na tela principal.
     */
    public void executa(ActionEvent evt) {
        switch(evt.getActionCommand()) {
            case "Inicial":
                exibirContainerQuarto();
                break;
             case "Clientes":
                 exibirContainerCliente();
                 break;
            case "Hospedagem":
                exibirContainerMenuHospedagem();
                break;
            case "Financeiro":
                exibirContainerMenuFinanceiro();
                break;
        }
    }
    
    /**
     * Neste método instanciamos o ContainerQuarto responsável por
     * apresentar as views na tela principal.
    */
    public void exibirContainerQuarto() {
        telaInicial.setJPanelContainerQuarto(new ContainerQuarto());
        telaInicial.getJPanelContainerQuarto().setBounds(5, 5, 200, 200);
        exibirJPanel(telaInicial.getJPanelContainerQuarto());
    }
    
    private void exibirContainerCliente() {
        telaInicial.setContainerMenuCliente(new ContainerMenuCliente());
        telaInicial.getContainerMenuCliente().setBounds(5, 5, 200, 200);
        exibirJPanel(telaInicial.getContainerMenuCliente());
    }
    
    private void exibirContainerMenuHospedagem() {
        telaInicial.setContainerMenuHospedagem(new ContainerMenuHospedagem());
        telaInicial.getContainerMenuHospedagem().setBounds(5, 5, 200, 200);
        exibirJPanel(telaInicial.getContainerMenuHospedagem());
    }
    
    private void exibirContainerMenuFinanceiro() {
        telaInicial.setContainerMenuFinanceiro(new ContainerMenuFinanceiro());
        telaInicial.getContainerMenuFinanceiro().setBounds(5, 5, 200, 200);
        exibirJPanel(telaInicial.getContainerMenuFinanceiro());
    }
    
    private void exibirJPanel(JPanel jPanel) {
        telaInicial.getjPanelPrincipal().removeAll();
        telaInicial.getjPanelPrincipal().add(jPanel, BorderLayout.CENTER);
        telaInicial.getjPanelPrincipal().revalidate();
        telaInicial.getjPanelPrincipal().repaint();
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void teste(int i) {
        System.out.println("controller.PrincipalController.teste()" + i);
    }
    
    public void exibitPainelCadastrarCliente(int i, int j) {
        telaInicial.setContainerCadastro(new TelaHospedarCliente(i, j));
        telaInicial.getContainerCadastro().setBounds(5, 5, 200, 200);
        exibirJPanel(telaInicial.getContainerCadastro());
    }
    
     
    
}
