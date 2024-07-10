package GUI;

import BUS.KhachHangBUS;
import DTO.KhachHangDTO;
import java.awt.Font;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import javax.swing.border.MatteBorder;

/**
 *
 * @author duyli
 */
public class InfoKhachHang {

    String gt[] = {"Giới Tính", "Nam", "Nữ"};

    private JFrame frBackground = new JFrame();

    private JLabel lbMaKH, lbHoTen, lbCmnd, lbGTinh, lbSdt;
    private JTextField jtfMaKH, jtfHoTen, jtfCmnd, jtfSdt;
    private JComboBox cbGioiTinh;

    private JButton jbDELETE, jbEDIT, jbCANCEL;
    private JLabel lbTitle = new JLabel("KHÁCH HÀNG");
    private JPanel pnContent = new JPanel();
    private Font sgUI25b = new Font("Segoe UI", Font.BOLD, 25);
    private Font sgUI15b = new Font("Segoe UI", Font.BOLD, 15);
    private Font sgUI15 = new Font("Segoe UI", Font.PLAIN, 15);
    static KhachHangBUS kh = new KhachHangBUS();
    private ArrayList<KhachHangDTO> KHList;
    DefaultTableModel model;
    //   PanelKhachHang PNKH = new PanelKhachHang();
    private JTable table;

    public InfoKhachHang(String maKH, String nameKH, String cmnd, String gtinh, String sdt, JTable tablePANEL, int SelectRow) throws ParseException {
        table = tablePANEL;
        initComponents(maKH, nameKH, cmnd, gtinh, sdt, tablePANEL, SelectRow);
    }

    public void initComponents(String maKH, String nameKH, String cmnd, String gtinh, String sdt, JTable tablePANEL, int SelectRow) throws ParseException {
        KHList = new ArrayList<>();
        try {
            KHList = new KhachHangBUS().LoadKH();
        } catch (SQLException ex) {
            Logger.getLogger(AdminGUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(AdminGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        frBackground.setVisible(true);
        frBackground.setSize(450, 700);
        frBackground.setLocationRelativeTo(null);
        frBackground.setLayout(new BorderLayout());
        frBackground.setResizable(false);
        frBackground.add(lbTitle, BorderLayout.NORTH);
        lbTitle.setPreferredSize(new Dimension(frBackground.getWidth(), 50));
        lbTitle.setOpaque(true);
        lbTitle.setHorizontalAlignment(JLabel.CENTER);
        lbTitle.setFont(sgUI25b);

        frBackground.add(pnContent);
        pnContent.setBackground(Color.white);
        pnContent.setLayout(null);
        frBackground.add(pnContent, BorderLayout.CENTER);

        lbMaKH = new JLabel("Mã khách hàng: ");
        lbMaKH.setFont(sgUI15b);
        lbMaKH.setBounds(50, 50, 150, 50);
        jtfMaKH = new JTextField();
        jtfMaKH.setBounds(160, 55, 230, 40);
        jtfMaKH.setFont(sgUI15b);
        jtfMaKH.setText(maKH);
        jtfMaKH.setEnabled(false);

        lbHoTen = new JLabel("Họ & Tên: ");
        lbHoTen.setFont(sgUI15b);
        lbHoTen.setBounds(50, 120, 100, 50);
        jtfHoTen = new JTextField();
        jtfHoTen.setBounds(160, 125, 230, 40);
        jtfHoTen.setFont(sgUI15);
        jtfHoTen.setText(nameKH);

        lbCmnd = new JLabel("CMND: ");
        lbCmnd.setFont(sgUI15b);
        lbCmnd.setBounds(50, 190, 100, 50);
        jtfCmnd = new JTextField();
        jtfCmnd.setBounds(160, 195, 230, 40);
        jtfCmnd.setFont(sgUI15);
        jtfCmnd.setText(cmnd);

        lbGTinh = new JLabel("Giới Tính: ");
        lbGTinh.setFont(sgUI15b);
        lbGTinh.setBounds(50, 260, 100, 50);
        cbGioiTinh = new JComboBox(gt);
        cbGioiTinh.setBackground(Color.decode("#FAFAFA"));
        cbGioiTinh.setBorder(new MatteBorder(1, 1, 1, 1, Color.black));
        cbGioiTinh.setBounds(160, 265, 230, 40);
        cbGioiTinh.setFont(sgUI15);
        if (gtinh.equals("Nữ")) {
            cbGioiTinh.setSelectedItem(gt[2]);
        } else {
            cbGioiTinh.setSelectedItem(gt[1]);
        }

        lbSdt = new JLabel("SĐT: ");
        lbSdt.setFont(sgUI15b);
        lbSdt.setBounds(50, 320, 100, 50);
        jtfSdt = new JTextField();
        jtfSdt.setBounds(160, 325, 230, 40);
        jtfSdt.setFont(sgUI15);
        jtfSdt.setText(sdt);

        jbDELETE = new JButton("Xóa");
        jbDELETE.setBounds(30, 535, 100, 40);
        jbDELETE.setFont(sgUI15b);
        jbDELETE.setBackground(Color.decode("#388E3C"));
        jbDELETE.setForeground(Color.white);

        jbEDIT = new JButton("Sửa");
        jbEDIT.setBounds(160, 535, 100, 40);
        jbEDIT.setFont(sgUI15b);
        jbEDIT.setBackground(Color.decode("#FF3D00"));
        jbEDIT.setForeground(Color.white);

        jbCANCEL = new JButton("Thoát");
        jbCANCEL.setBounds(290, 535, 100, 40);
        jbCANCEL.setFont(sgUI15b);
        jbCANCEL.setBackground(Color.decode("#FF3D00"));
        jbCANCEL.setForeground(Color.white);
        jbCANCEL.addActionListener((ActionEvent e) -> {
            frBackground.setVisible(false);
        });

        pnContent.add(lbMaKH);
        pnContent.add(jtfMaKH);
        pnContent.add(lbHoTen);
        pnContent.add(jtfHoTen);
        pnContent.add(lbCmnd);
        pnContent.add(jtfCmnd);
        pnContent.add(lbGTinh);
        pnContent.add(cbGioiTinh);
        pnContent.add(lbSdt);
        pnContent.add(jtfSdt);
        pnContent.add(jbDELETE);
        pnContent.add(jbEDIT);
        pnContent.add(jbCANCEL);

        jbDELETE.addActionListener((ActionEvent e) -> {

            KhachHangDTO KHtemp = new KhachHangDTO();
            KHtemp.setXuLy(0);
            KHtemp.setMaKH(jtfMaKH.getText());
            try {
                new KhachHangBUS().DeleteKH(KHtemp);
            } catch (SQLException ex) {
                Logger.getLogger(AdminGUI.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(AdminGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(null, "Xóa thành công");
            PanelKhachHang.loaddata(table);
            frBackground.setVisible(false);
        });
        jbEDIT.addActionListener((ActionEvent e) -> {

            if (jtfHoTen.getText().isEmpty() || jtfCmnd.getText().isEmpty() || jtfSdt.getText().isEmpty() || cbGioiTinh.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(null, "Vui lòng nhập đủ thông tin cần sửa");
            } else if (checknonNum(jtfCmnd.getText()) || checknonNum(jtfSdt.getText())) {
                JOptionPane.showMessageDialog(null, "CMND và số điện thoại phải là chữ số");
            } else if (jtfCmnd.getText().length() > 10 || jtfSdt.getText().length() != 10) {
                JOptionPane.showMessageDialog(null, "CMND và số điện thoại phải gồm 10 chữ số");
            } else {
                KhachHangDTO KH = new KhachHangDTO();
                KH.setXuLy(0);
                KH.setMaKH(jtfMaKH.getText());
                KH.setTenKH(jtfHoTen.getText());
                KH.setCmnd(jtfCmnd.getText());
                KH.setSdt(jtfSdt.getText());

                if (cbGioiTinh.getSelectedIndex() == 1) {
                    KH.setGioiTinh("Nam");
                } else if (cbGioiTinh.getSelectedIndex() == 2) {
                    KH.setGioiTinh("Nữ");
                }
                try {
                    new KhachHangBUS().UpdateKH(KH);
                } catch (SQLException ex) {
                    Logger.getLogger(AdminGUI.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(AdminGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                JOptionPane.showMessageDialog(null, "Sửa thành công");
                PanelKhachHang.loaddata(table);
                frBackground.setVisible(false);
            }

        });
    }

    public boolean find(String a) {
        ArrayList<KhachHangDTO> KHList = new ArrayList<>(); //xóa arraylist để update cái mới
        try {
            KHList = new KhachHangBUS().LoadKH();
        } catch (SQLException ex) {
            Logger.getLogger(AdminGUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(AdminGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (int i = 0; i < KHList.size(); i++) {
            if (KHList.get(i).getMaKH().equals(a) && KHList.get(i).getXuLy() == 0) {

                return true;
            }
        }
        return false;
    }

    public boolean checknonNum(String a) {
        try {
            double d = Double.parseDouble(a);
        } catch (NumberFormatException nfe) {
            return true;
        }
        return false;
    }

}
