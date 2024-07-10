/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author huhuh
 */
public class PanelHome extends JPanel {
    public PanelHome(int a, int b) {
        init(a, b);
    }

    public void init(int a, int b) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(new ImageSliderPanel(new String[]{"/GUI/asset/ks.jpg", "/GUI/asset/ks2.jpg"}, a - 220, b - 100));
        // ...Thêm các thành phần khác vào JPanel của bạn
    }
}


