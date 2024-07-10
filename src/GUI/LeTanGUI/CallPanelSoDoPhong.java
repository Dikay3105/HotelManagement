package GUI.LeTanGUI;

import GUI.PanelSoDoPhong;
import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
public class CallPanelSoDoPhong extends JFrame{
    public CallPanelSoDoPhong(){
        initComponents();
    }
    public void initComponents(){
        setSize(1300, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(5, 5));
        add(new PanelSoDoPhong());
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Quay lại JFrame trước đó
                new ReceptionistGUI().setVisible(true);
                dispose(); // Đóng JFrame hiện tại
            }
        });
        setVisible(true);
    }
   
}
