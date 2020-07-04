/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package componentes;

import java.awt.Color;

/**
 *
 * @author Elyneker Luciani
 */
public class QuartoComponent extends javax.swing.JPanel {

    private static final Color COR_QUARTO_LIVRE = new Color(255, 255, 255);
    //private static final Color COR_QUARTO_OCUPADO = new Color(255, 139, 130);
    private static final Color COR_QUARTO_OCUPADO = new Color(248,215,218);

    /**
     * Creates new form QuartoComponent
     */
    // Construtor usado quando não há quartos cadastrados
    public QuartoComponent() {
        initComponents();
        this.status.setText("Não existe quarto cadastrado");
        this.status.setForeground(Color.RED);
        this.btnLimpar.setVisible(false);
        this.numero.setVisible(false);
        this.icone.setVisible(false);
        this.setBackground(new Color(209, 205, 205));
    }

    //Construtor usado quando existe quartos cadastrados, recebe o número do
    // quarto e o status tru= ocupado, false= livre
    public QuartoComponent(int i, boolean b) {
        initComponents();
        this.numero.setText("Número: " + i);
        alterarStatus(b);
    }

    private void alterarStatus(boolean a) {
        //se a variável for true, o quarto está ocupado, senão está livre
        if (a) {
            this.setBackground(COR_QUARTO_OCUPADO);
            this.status.setText("Ocupado");
            this.btnLimpar.setVisible(false);
        } else {
            this.setBackground(COR_QUARTO_LIVRE);
            this.status.setText("Livre");
            this.btnLimpar.setVisible(false);

        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rSButtonMetro1 = new rsbuttom.RSButtonMetro();
        icone = new javax.swing.JLabel();
        numero = new javax.swing.JLabel();
        status = new javax.swing.JLabel();
        btnLimpar = new rsbuttom.RSButtonMetro();

        rSButtonMetro1.setText("rSButtonMetro1");

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createEtchedBorder());
        setMinimumSize(new java.awt.Dimension(114, 17));
        setPreferredSize(new java.awt.Dimension(305, 85));
        setLayout(new java.awt.GridLayout(2, 2, 1, 0));

        icone.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        icone.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8-door-hanger-48.png"))); // NOI18N
        add(icone);

        numero.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        numero.setText("jLabel3");
        add(numero);

        status.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        status.setText("jLabel2");
        add(status);

        btnLimpar.setText("Limpar");
        btnLimpar.setMinimumSize(new java.awt.Dimension(45, 8));
        add(btnLimpar);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rsbuttom.RSButtonMetro btnLimpar;
    private javax.swing.JLabel icone;
    private javax.swing.JLabel numero;
    private rsbuttom.RSButtonMetro rSButtonMetro1;
    private javax.swing.JLabel status;
    // End of variables declaration//GEN-END:variables
}
