package controller;

import container.ContainerMenuCliente;
import container.ContainerMenuConfigurar;
import container.ContainerMenuFinanceiro;
import container.ContainerMenuHospedagem;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import container.ContainerBloco;
import java.awt.event.ActionEvent;
import view.TelaDadosReserva;
import view.TelaReservaQuarto;
import view.TelaPrincipal;

/**
 *
 * @author Elyneker Luciani
 */
public class PrincipalController {
    private static PrincipalController principal;
    private TelaPrincipal telaInicial;
    private ContainerBloco quartoComponente;
    
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
    
    public void setQuartoComponente(ContainerBloco quartoComponente) {
        this.quartoComponente = quartoComponente;
    }
    
    /**
     * Este método é responsável por identificar qual botão na tela inicial foi
     * selecionado para poder chamar o menu correspondente na tela principal.
     * @param evt
     */
    public void executa(ActionEvent evt) {
        switch(evt.getActionCommand()) {
            case "Cancelar":
                exibirContainerQuarto();
                break;
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
            case "Configurar":
                exibirContainerMenuConfigurar();
                break;
        }
    }
    
    /**
     * Neste método instanciamos o ContainerBloco responsável por
 apresentar as views na tela principal.
    */
    public void exibirContainerQuarto() {
        telaInicial.setJPanelContainerQuarto(new ContainerBloco());
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
    
    private void exibirContainerMenuConfigurar() {
        telaInicial.setContainerMenuConfigurar(new ContainerMenuConfigurar());
        telaInicial.getContainerMenuConfigurar().setBounds(5, 5, 200, 200);
        exibirJPanel(telaInicial.getContainerMenuConfigurar());
    }
    
    /**
     * Este método é utilizado para instanciar todas os telas de painel na tela 
     * principal.
     * 
     */
    private void exibirJPanel(JPanel jPanel) {
        telaInicial.getjPanelPrincipal().removeAll();
        telaInicial.getjPanelPrincipal().add(jPanel, BorderLayout.CENTER);
        telaInicial.getjPanelPrincipal().revalidate();
        telaInicial.getjPanelPrincipal().repaint();
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * Este método é responsável por instanciar a tela de reserva de quarto
     * quando o usuário clica em hospedar no bloco componente da tela principal.
     * Passa por parâmetro o id do quarto, número e categoria referente ao
     * quarto para exibir na tela de reserva.
     * @param idQuarto
     * @param numeroQuarto
     * @param idCategoria
     */
    public void exibirPainelCadastrarCliente(int idQuarto, int numeroQuarto, int idCategoria) {
        telaInicial.setContainerCadastro(new TelaReservaQuarto(idQuarto, numeroQuarto, idCategoria));
        telaInicial.getContainerCadastro().setBounds(5, 5, 200, 200);
        exibirJPanel(telaInicial.getContainerCadastro());
    }
    
    /**
     * Este método recebe o id da reserva que está associado a reserva do
     * quarto destacado no bloco componente.
     * Com este idReserva instaciamos a tela de dados da reserva na tela
     * principal.
     * @param idReserva
     */
    public void exibirPainelDadosReserva(int idReserva) {
        telaInicial.setDadosReserva(new TelaDadosReserva(idReserva));
        telaInicial.getDadosReserva().setBounds(5, 5, 200, 200);
        exibirJPanel(telaInicial.getDadosReserva());
    }
     
    
}
