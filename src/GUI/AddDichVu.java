package GUI;

import BUS.DichVuBUS;
import DTO.DichVuDTO;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.plaf.ColorUIResource;
// link icon icons8.com

public class AddDichVu extends JFrame {

    private JLabel lbTitle = new JLabel("Thêm dịch vụ");

    private JTextField txtMaDV = new JTextField("");
    private JTextField txtTenDV = new JTextField("Tên dịch vụ...");
    private JComboBox cbLoaiDV = new JComboBox();
    private JTextField txtGiaDV = new JTextField("Giá dịch vụ...");

    private TitledBorder tbMaDV = new TitledBorder(new MatteBorder(2, 2, 2, 2, Color.green), "Mã dịch vụ");
    private TitledBorder tbTenDV = new TitledBorder(new MatteBorder(2, 2, 2, 2, Color.black), "");
    private TitledBorder tbLoaiDV = new TitledBorder(new MatteBorder(2, 2, 2, 2, Color.green), "Loại dịch vụ");
    private TitledBorder tbGiaDV = new TitledBorder(new MatteBorder(2, 2, 2, 2, Color.black), "");

    private JPanel pnBorder = new JPanel();
    private JPanel pnMaDV = new JPanel();
    private JPanel pnTenDV = new JPanel();
    private JPanel pnLoaiDV = new JPanel();
    private JPanel pnGiaDV = new JPanel();

    private JButton btnCancel = new JButton("Huỷ");
    private JButton btnAdd = new JButton("Thêm");

    Font sgUI15 = new Font("Segoe UI", Font.BOLD, 15);
    Font sgUI15p = new Font("Segoe UI", Font.PLAIN, 15);
    Font sgUI13 = new Font("Segoe UI", Font.PLAIN, 13);
    Font sgUI13b = new Font("Segoe UI", Font.BOLD, 13);
    Font sgUI18b = new Font("Segoe UI", Font.BOLD, 18);

    public AddDichVu() {
        initComponents();
    }

    public void initComponents() {
        setLayout(null);
        setResizable(false);
        setSize(310, 500);
        setLocationRelativeTo(null);
        setTitle("Thêm dịch vụ");
        getContentPane().setBackground(Color.white);

        lbTitle.setBounds(0, 0, 300, 50);
        lbTitle.setFont(sgUI18b);
        lbTitle.setHorizontalAlignment(JLabel.CENTER);

        pnMaDV.setBounds(22, 80, 250, 60);
        pnMaDV.setLayout(new BorderLayout());
        pnMaDV.add(txtMaDV, BorderLayout.CENTER);
        pnMaDV.setBorder(BorderFactory.createCompoundBorder(tbMaDV, new EmptyBorder(4, 8, 8, 8)));
        pnMaDV.setBackground(Color.white);

        pnTenDV.setBackground(Color.white);
        pnTenDV.setBounds(22, 150, 250, 60);
        pnTenDV.setBorder(BorderFactory.createCompoundBorder(tbTenDV, new EmptyBorder(4, 8, 8, 8)));
        pnTenDV.setLayout(new BorderLayout());
        pnTenDV.add(txtTenDV, BorderLayout.CENTER);
        txtTenDV.setBorder(null);
        txtTenDV.setFont(sgUI13);

        pnLoaiDV.setBounds(22, 220, 250, 60);
        pnLoaiDV.setBorder(BorderFactory.createCompoundBorder(tbLoaiDV, new EmptyBorder(4, 8, 4, 8)));
        pnLoaiDV.setLayout(new BorderLayout());
        pnLoaiDV.add(cbLoaiDV, BorderLayout.CENTER);
        pnLoaiDV.setBackground(Color.white);
        cbLoaiDV.setBackground(Color.white);
        cbLoaiDV.addItem("Ăn uống");
        cbLoaiDV.addItem("Chăm sóc sắc đẹp");
        cbLoaiDV.addItem("Giải trí");
        cbLoaiDV.addItem("Tiện ích");
        cbLoaiDV.addItem("Tổ chức tiệc");

        UIManager.put("TextField.inactiveBackground", new ColorUIResource(new Color(255, 255, 255)));

        pnGiaDV.setBackground(Color.white);
        pnGiaDV.setBounds(22, 290, 250, 60);
        pnGiaDV.setBorder(BorderFactory.createCompoundBorder(tbGiaDV, new EmptyBorder(4, 8, 8, 8)));
        pnGiaDV.setLayout(new BorderLayout());
        pnGiaDV.add(txtGiaDV, BorderLayout.CENTER);
        txtGiaDV.setFont(sgUI13);
        txtGiaDV.setBorder(null);

        txtMaDV.setFont(sgUI13);
        txtMaDV.setEditable(false);
        txtMaDV.setBorder(null);

        pnBorder.setBackground(new Color(51, 51, 51));
        pnBorder.setBounds(22, 50, 250, 2);

        ImageIcon iconCancel = new ImageIcon(new ImageIcon(getClass().getResource("/GUI/asset/cancel-144.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        btnCancel.setIcon(iconCancel);
        btnCancel.setBounds(30, 380, 100, 50);
        btnCancel.setBackground(Color.white);

        ImageIcon iconAdd = new ImageIcon(new ImageIcon(getClass().getResource("/GUI/asset/plus.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        btnAdd.setIcon(iconAdd);
        btnAdd.setBounds(162, 380, 100, 50);
        btnAdd.setBackground(Color.white);

        updateMaDV();

        add(btnCancel);
        add(btnAdd);

        add(lbTitle);
        add(pnMaDV);
        add(pnTenDV);
        add(pnLoaiDV);
        add(pnGiaDV);
        add(pnBorder);

        txtTenDV.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txtTenDV.getText().equals("Tên dịch vụ...")) {
                    txtTenDV.setText("");
                }
                tbTenDV = new TitledBorder(new MatteBorder(2, 2, 2, 2, Color.green), "Tên dịch vụ");
                pnTenDV.setBorder(BorderFactory.createCompoundBorder(tbTenDV, new EmptyBorder(4, 8, 8, 8)));
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (txtTenDV.getText().trim().length() == 0) {
                    txtTenDV.setText("Tên dịch vụ...");
                    tbTenDV = new TitledBorder(new MatteBorder(2, 2, 2, 2, Color.red), "");
                    pnTenDV.setBorder(BorderFactory.createCompoundBorder(tbTenDV, new EmptyBorder(4, 8, 8, 8)));
                }
            }
        });

        txtGiaDV.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txtGiaDV.getText().equals("Giá dịch vụ...")) {
                    txtGiaDV.setText("");
                }
                tbGiaDV = new TitledBorder(new MatteBorder(2, 2, 2, 2, Color.green), "Giá dịch vụ");
                pnGiaDV.setBorder(BorderFactory.createCompoundBorder(tbGiaDV, new EmptyBorder(8, 8, 8, 8)));
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (txtGiaDV.getText().trim().length() == 0) {
                    txtGiaDV.setText("Giá dịch vụ...");
                    tbGiaDV = new TitledBorder(new MatteBorder(2, 2, 2, 2, Color.red), "");
                    pnGiaDV.setBorder(BorderFactory.createCompoundBorder(tbGiaDV, new EmptyBorder(8, 8, 8, 8)));
                } else if (txtGiaDV.getText().trim().length() != 0) {
                    try {
                        int GiaDV = Integer.parseInt(txtGiaDV.getText());
                        tbGiaDV = new TitledBorder(new MatteBorder(2, 2, 2, 2, Color.green), "Giá dịch vụ");
                        pnGiaDV.setBorder(BorderFactory.createCompoundBorder(tbGiaDV, new EmptyBorder(8, 8, 8, 8)));
                    } catch (Exception ex) {
                        tbGiaDV = new TitledBorder(new MatteBorder(2, 2, 2, 2, Color.red), "Giá dịch vụ");
                        pnGiaDV.setBorder(BorderFactory.createCompoundBorder(tbGiaDV, new EmptyBorder(8, 8, 8, 8)));
                    }
                }
            }
        });

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                reset();
            }
        });

        btnCancel.setMnemonic(KeyEvent.VK_C);
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int answer = JOptionPane.showConfirmDialog(null, "Bạn có muốn thoát không", "Thông tin", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if (answer == JOptionPane.OK_OPTION) {
                    setVisible(false);
                    reset();
                }
            }
        });

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String info = "";
                if (txtTenDV.getText().equals("Tên dịch vụ...")) {
                    info += "Mời nhập tên dịch vụ \n";
                }
                if (txtGiaDV.getText().equals("Giá dịch vụ...")) {
                    info += "Mời nhập giá dịch vụ";
                }
                if (info.equals("")) {
                    try {
                        int giaDV = Integer.parseInt(txtGiaDV.getText());
                        DichVuDTO dvDTO = new DichVuDTO();
                        dvDTO.setMaDV(txtMaDV.getText());
                        dvDTO.setTenDV(txtTenDV.getText());
                        dvDTO.setTenLoaiDV(cbLoaiDV.getSelectedItem().toString());
                        dvDTO.setGiaDV(giaDV);
                        dvDTO.setXuLy(0);
                        if (DichVuBUS.insertDV(dvDTO)) {
                            int accept = JOptionPane.showConfirmDialog(null, "Thêm thành công bạn muốn thoát không?", "Thành công", JOptionPane.INFORMATION_MESSAGE);
                            if (accept == 0) {
                                setVisible(false);
                                reset();
                                try {
                                PanelDichVu.loadTable(PanelDichVu.tbDichVu);
                                    
                                } catch (Exception ex) {
                                }
                            } else {
                                reset();
                                PanelDichVu.loadTable(PanelDichVu.tbDichVu);
                            }
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Giá là một số nguyên", "Báo lỗi", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, info, "Báo lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public void reset() {
        updateMaDV();
        txtGiaDV.setText("Giá dịch vụ...");
        tbGiaDV = new TitledBorder(new MatteBorder(2, 2, 2, 2, Color.black), "");
        pnGiaDV.setBorder(BorderFactory.createCompoundBorder(tbGiaDV, new EmptyBorder(8, 8, 8, 8)));
        cbLoaiDV.setSelectedIndex(0);
        txtTenDV.setText("Tên dịch vụ...");
        tbTenDV = new TitledBorder(new MatteBorder(2, 2, 2, 2, Color.black), "");
        pnTenDV.setBorder(BorderFactory.createCompoundBorder(tbTenDV, new EmptyBorder(4, 8, 8, 8)));
    }

    public void updateMaDV() {
        ArrayList<DichVuDTO> listDV = DichVuBUS.getListDV();
        int length = listDV.size() + 1;
        if (length < 10) {
            txtMaDV.setText("DV0" + length);
        } else {
            txtMaDV.setText("DV" + length);
        }
    }
}
