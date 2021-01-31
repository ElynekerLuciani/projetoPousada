package rsbuttom;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JButton;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class RSButtonSub extends JButton implements MouseListener, MouseMotionListener {

    private Color colorNormal = new Color(238, 238, 238);
    private Color colorPressed = new Color(204, 204, 204);
    private Color colorHover = new Color(38, 86, 186);

    private Border bordeMoved = javax.swing.BorderFactory.createMatteBorder(0, 20, 0, 20, new java.awt.Color(255, 255, 255));
    /**
     * Color para el texto
     */
    private Color colorTextNormal = new Color(0, 102, 153);
    private Color colorTextPressed = new Color(0, 0, 0);
    private Color colorTextHover = new Color(0, 102, 153);

    private Color subMenuPressed = new Color(238, 238, 238);
    private Color colorTextPressedMenu = new Color(238, 238, 238);

    private Font f = new Font("Tahoma", Font.BOLD, 14);

    @SuppressWarnings("LeakingThisInConstructor")
    public RSButtonSub() {
        this.setFont(f);
        this.setPreferredSize(new Dimension(150, 35));
        this.setSize(new Dimension(150, 35));
        this.setBorder(new EmptyBorder(0, 20, 0, 20));
//        this.setBorderPainted(false);
        this.setContentAreaFilled(false);
        this.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        this.setBackground(this.colorNormal);
        this.setForeground(this.colorTextNormal);
        this.setOpaque(true);
        this.setVisible(true);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    @Override
    public void mouseClicked(MouseEvent me) {

    }

    @Override
    public void mousePressed(MouseEvent me) {
        this.setForeground(this.colorTextPressedMenu);
        this.setBackground(this.colorPressed);
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        // this.setBackground(this.colorNormal);
        //   this.setForeground(this.colorTextNormal);
    }

    @Override
    public void mouseEntered(MouseEvent me) {

    }

    @Override
    public void mouseExited(MouseEvent me) {
        this.setBackground(this.colorNormal);
        this.setForeground(this.colorTextNormal);
    }

    @Override
    public void mouseDragged(MouseEvent me) {

    }

    @Override
    public void mouseMoved(MouseEvent me) {
        this.setForeground(this.subMenuPressed);
        this.setBackground(this.colorHover);
    }

    public Color getColorPressed() {
        return colorPressed;
    }

    public void setColorPressed(Color colorPressed) {
        this.colorPressed = colorPressed;
    }

    public Color getColorTextPressed() {
        return colorTextPressed;
    }

    public void setColorTextPressed(Color colorTextPressed) {
        this.colorTextPressed = colorTextPressed;
    }

    public Color getColorHover() {
        return colorHover;
    }

    public void setColorHover(Color colorHover) {
        this.colorHover = colorHover;
    }

    public Color getColorTextHover() {
        return colorTextHover;
    }

    public void setColorTextHover(Color colorTextHover) {
        this.colorTextHover = colorTextHover;
    }

    public Color getColorNormal() {
        return colorNormal;
    }

    public void setColorNormal(Color colorNormal) {
        this.setBackground(colorNormal);
        this.colorNormal = colorNormal;
    }

    public Color getColorTextNormal() {
        return colorTextNormal;
    }

    public void setColorTextNormal(Color colorTextNormal) {
        this.setForeground(colorTextNormal);
        this.colorTextNormal = colorTextNormal;
    }

    public Border getColorBorde() {
        return bordeMoved;
    }

    public void setColorBorde(Border bordeMoved) {
        // this.bordeMoved = bordeMoved;
    }

}
