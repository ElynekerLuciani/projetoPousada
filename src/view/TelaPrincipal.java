package view;

import container.ContainerMenuCliente;
import container.ContainerMenuConfigurar;
import container.ContainerMenuFinanceiro;
import container.ContainerMenuHospedagem;
import container.ContainerBloco;
import controller.PrincipalController;
import javax.swing.JPanel;

/**
 *
 * @author Elyneker Luciani
 */
public class TelaPrincipal extends javax.swing.JFrame {

    private ContainerBloco containerQuarto;
    private ContainerMenuHospedagem containerMenu;
    private ContainerMenuCliente containerCliente;
    private ContainerMenuFinanceiro containerFinanceiro;
    private ContainerMenuConfigurar containerConfigurar;
    private TelaReservaQuarto containerCadastro;
    private TelaDadosReserva dadosReserva;
    private TelaCadastroCliente cadastroCliente;
    private TelaHistoricoCliente historicoCliente;
    private final PrincipalController principal = PrincipalController.getInstancia();

    /**
     * Creates new form Principal
     */
    @SuppressWarnings("LeakingThisInConstructor")
    public TelaPrincipal() {
        initComponents();
        this.setExtendedState(MAXIMIZED_BOTH);
        principal.setPrincipal(this);
        //Exibimos o ContainerBloco configurado para instanciar os blocos
        //componentes para representar os quartos e as reservas.
        principal.exibirContainerQuarto();

    }

    //getter de ContainerBloco Inicial
    public ContainerBloco getJPanelContainerQuarto() {
        return containerQuarto;
    }

    //setter de ContainerBloco Inicial
    public void setJPanelContainerQuarto(ContainerBloco container) {
        this.containerQuarto = container;
    }

    //getter de ContainerMenuHospedagem
    public ContainerMenuHospedagem getContainerMenuHospedagem() {
        return containerMenu;
    }

    //setter de ContainerMenuHospedagem
    public void setContainerMenuHospedagem(ContainerMenuHospedagem container) {
        this.containerMenu = container;
    }

    //getter ContainerMenuCliente
    public ContainerMenuCliente getContainerMenuCliente() {
        return containerCliente;
    }

    //setter ContainerMenuCliente
    public void setContainerMenuCliente(ContainerMenuCliente containerCliente) {
        this.containerCliente = containerCliente;
    }

    //getter jPanelPrincipal
    public JPanel getjPanelPrincipal() {
        return jPanelPrincipal;
    }

    public ContainerMenuFinanceiro getContainerMenuFinanceiro() {
        return containerFinanceiro;
    }

    public void setContainerMenuFinanceiro(ContainerMenuFinanceiro containerFinanceiro) {
        this.containerFinanceiro = containerFinanceiro;
    }

    public ContainerMenuConfigurar getContainerMenuConfigurar() {
        return containerConfigurar;
    }

    public void setContainerMenuConfigurar(ContainerMenuConfigurar containerConfigurar) {
        this.containerConfigurar = containerConfigurar;
    }

    public TelaReservaQuarto getContainerCadastro() {
        return containerCadastro;
    }

    public void setContainerCadastro(TelaReservaQuarto containerCadastro) {
        this.containerCadastro = containerCadastro;
    }

    public TelaDadosReserva getDadosReserva() {
        return dadosReserva;
    }

    public void setDadosReserva(TelaDadosReserva dadosReserva) {
        this.dadosReserva = dadosReserva;
    }

    public TelaCadastroCliente getCadastroCliente() {
        return cadastroCliente;
    }

    public void setCadastroCliente(TelaCadastroCliente cadastroCliente) {
        this.cadastroCliente = cadastroCliente;
    }

    public TelaHistoricoCliente getHistoricoCliente() {
        return historicoCliente;
    }

    public void setHistoricoCliente(TelaHistoricoCliente historicoCliente) {
        this.historicoCliente = historicoCliente;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelMenuPrincipal = new javax.swing.JPanel();
        btnInicial = new rsbuttom.RSButtonMetro();
        jPanel1 = new javax.swing.JPanel();
        btnHospedagem = new rsbuttom.RSButtonMetro();
        btnClientes = new rsbuttom.RSButtonMetro();
        btnFinanceiro = new rsbuttom.RSButtonMetro();
        btnConfigurar = new rsbuttom.RSButtonMetro();
        jPanelPrincipal = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setName("Principal"); // NOI18N

        jPanelMenuPrincipal.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnInicial.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8-unit-48.png"))); // NOI18N
        btnInicial.setText("Inicial");
        btnInicial.setToolTipText("");
        btnInicial.setColorPressed(new java.awt.Color(204, 204, 204));
        btnInicial.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        btnInicial.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnInicial.setMaximumSize(new java.awt.Dimension(200, 33));
        btnInicial.setMinimumSize(new java.awt.Dimension(200, 33));
        btnInicial.setPreferredSize(new java.awt.Dimension(200, 35));
        btnInicial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInicialActionPerformed(evt);
            }
        });
        jPanelMenuPrincipal.add(btnInicial, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 200, 50));

        jPanel1.setBackground(new java.awt.Color(0, 51, 204));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jPanelMenuPrincipal.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 200, -1));

        btnHospedagem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8-schedule-48.png"))); // NOI18N
        btnHospedagem.setText("Hospedagem");
        btnHospedagem.setColorPressed(new java.awt.Color(204, 204, 204));
        btnHospedagem.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        btnHospedagem.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnHospedagem.setMaximumSize(new java.awt.Dimension(189, 33));
        btnHospedagem.setMinimumSize(new java.awt.Dimension(189, 33));
        btnHospedagem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHospedagemActionPerformed(evt);
            }
        });
        jPanelMenuPrincipal.add(btnHospedagem, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 230, 200, 50));

        btnClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8-add-user-group-man-man-48.png"))); // NOI18N
        btnClientes.setText("Clientes");
        btnClientes.setColorPressed(new java.awt.Color(204, 204, 204));
        btnClientes.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        btnClientes.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClientesActionPerformed(evt);
            }
        });
        jPanelMenuPrincipal.add(btnClientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 170, 200, 50));

        btnFinanceiro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8-tips-48.png"))); // NOI18N
        btnFinanceiro.setText("Financeiro");
        btnFinanceiro.setColorPressed(new java.awt.Color(204, 204, 204));
        btnFinanceiro.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        btnFinanceiro.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnFinanceiro.setMaximumSize(new java.awt.Dimension(189, 33));
        btnFinanceiro.setMinimumSize(new java.awt.Dimension(189, 33));
        btnFinanceiro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFinanceiroActionPerformed(evt);
            }
        });
        jPanelMenuPrincipal.add(btnFinanceiro, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 290, 200, 50));

        btnConfigurar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8-settings-48.png"))); // NOI18N
        btnConfigurar.setText("Configurar");
        btnConfigurar.setToolTipText("");
        btnConfigurar.setColorPressed(new java.awt.Color(204, 204, 204));
        btnConfigurar.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        btnConfigurar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnConfigurar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfigurarActionPerformed(evt);
            }
        });
        jPanelMenuPrincipal.add(btnConfigurar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 350, 200, 50));

        jPanelPrincipal.setBackground(new java.awt.Color(255, 255, 255));
        jPanelPrincipal.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanelPrincipal.setLayout(new java.awt.CardLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanelMenuPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, 794, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelMenuPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, 479, Short.MAX_VALUE)
            .addComponent(jPanelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnHospedagemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHospedagemActionPerformed
        principal.executa(evt);
    }//GEN-LAST:event_btnHospedagemActionPerformed

    private void btnInicialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInicialActionPerformed
        principal.executa(evt);
    }//GEN-LAST:event_btnInicialActionPerformed

    private void btnClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClientesActionPerformed
        principal.executa(evt);
    }//GEN-LAST:event_btnClientesActionPerformed

    private void btnFinanceiroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFinanceiroActionPerformed
        principal.executa(evt);
    }//GEN-LAST:event_btnFinanceiroActionPerformed

    private void btnConfigurarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfigurarActionPerformed
        principal.executa(evt);
    }//GEN-LAST:event_btnConfigurarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new TelaPrincipal().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rsbuttom.RSButtonMetro btnClientes;
    private rsbuttom.RSButtonMetro btnConfigurar;
    private rsbuttom.RSButtonMetro btnFinanceiro;
    private rsbuttom.RSButtonMetro btnHospedagem;
    private rsbuttom.RSButtonMetro btnInicial;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelMenuPrincipal;
    private javax.swing.JPanel jPanelPrincipal;
    // End of variables declaration//GEN-END:variables
}
