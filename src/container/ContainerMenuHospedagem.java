/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package container;

/**
 *
 * @author Elyneker Luciani
 */
public class ContainerMenuHospedagem extends javax.swing.JPanel {

    /**
     * Creates new form ContainerMenu
     */
    public ContainerMenuHospedagem() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnHospedar = new rsbuttom.RSButtonSub();
        btnServicos = new rsbuttom.RSButtonSub();
        btnListar = new rsbuttom.RSButtonSub();
        btnHistorico = new rsbuttom.RSButtonSub();
        jPanel2 = new javax.swing.JPanel();

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnHospedar.setForeground(new java.awt.Color(51, 51, 51));
        btnHospedar.setText("Hospedar");
        btnHospedar.setColorTextHover(new java.awt.Color(51, 51, 51));
        btnHospedar.setColorTextNormal(new java.awt.Color(51, 51, 51));
        btnHospedar.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        btnHospedar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jPanel1.add(btnHospedar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 130, -1));

        btnServicos.setForeground(new java.awt.Color(51, 51, 51));
        btnServicos.setText("Serviços");
        btnServicos.setColorTextHover(new java.awt.Color(51, 51, 51));
        btnServicos.setColorTextNormal(new java.awt.Color(51, 51, 51));
        btnServicos.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        btnServicos.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jPanel1.add(btnServicos, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 130, -1));

        btnListar.setForeground(new java.awt.Color(51, 51, 51));
        btnListar.setText("Listar");
        btnListar.setColorTextHover(new java.awt.Color(51, 51, 51));
        btnListar.setColorTextNormal(new java.awt.Color(51, 51, 51));
        btnListar.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        btnListar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jPanel1.add(btnListar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 130, -1));

        btnHistorico.setForeground(new java.awt.Color(51, 51, 51));
        btnHistorico.setText("Histórico");
        btnHistorico.setColorTextHover(new java.awt.Color(51, 51, 51));
        btnHistorico.setColorTextNormal(new java.awt.Color(51, 51, 51));
        btnHistorico.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        btnHistorico.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jPanel1.add(btnHistorico, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 130, -1));

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 664, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 479, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rsbuttom.RSButtonSub btnHistorico;
    private rsbuttom.RSButtonSub btnHospedar;
    private rsbuttom.RSButtonSub btnListar;
    private rsbuttom.RSButtonSub btnServicos;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
