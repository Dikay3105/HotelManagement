package GUI;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class ImageSliderPanel extends JPanel {
    private static final int SLIDE_DELAY = 5000; // Thời gian chuyển đổi ảnh
    private Image[] images;
    private int currentImageIndex;
    private Timer timer;

    public ImageSliderPanel(String[] imagePaths, int panelWidth, int panelHeight) {
        images = new Image[imagePaths.length];
        for (int i = 0; i < imagePaths.length; i++) {
            ImageIcon icon = new ImageIcon(getClass().getResource(imagePaths[i]));
            images[i] = icon.getImage().getScaledInstance(panelWidth, panelHeight, Image.SCALE_SMOOTH);
        }
        currentImageIndex = 0;
        timer = new Timer(SLIDE_DELAY, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentImageIndex = (currentImageIndex + 1) % images.length;
                repaint();
            }
        });
        timer.start();
        setPreferredSize(new Dimension(panelWidth, panelHeight));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(images[currentImageIndex], 0, 0, this);
    }
}
