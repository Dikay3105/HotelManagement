package GUI;

import com.toedter.calendar.JDateChooser;
import dao.DBConnect;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

public class Settings extends JFrame {

    JLabel lbTitle = new JLabel("CÀI ĐẶT");
    Font sgUI15 = new Font("Segoe UI", Font.BOLD, 15);
    Font sgUI15p = new Font("Segoe UI", Font.PLAIN, 15);
    Font sgUI13 = new Font("Segoe UI", Font.PLAIN, 13);
    Font sgUI13b = new Font("Segoe UI", Font.BOLD, 13);
    Font sgUI18b = new Font("Segoe UI", Font.BOLD, 18);
    JPanel pnContent = new JPanel();
    JPanel pnThongBao = new JPanel();
    JLabel lbThongBao = new JLabel("Cài đặt thông báo");
    JPanel pnThongBaoCenter = new JPanel();
    JPanel pnThongBaoTop = new JPanel();
    JPanel pnThongBaoContent = new JPanel();
    JPanel pnBtn = new JPanel();
    JButton btnAcept = new JButton("Đăng bài");
    JPanel pnGia = new JPanel();
    JPanel pnGia1 = new JPanel();
    JPanel pnGia2 = new JPanel();
    JLabel lbChonNgay = new JLabel("Chọn ngày đăng:");
    JDateChooser dc = new JDateChooser();
    JPanel pnTieuDe = new JPanel();
    JLabel lbTieuDe = new JLabel("Nhập tiêu đề:");
    JTextField txtTieuDe = new JTextField();
    JPanel pnArea = new JPanel();
    JTextArea txtarea = new JTextArea();
    JScrollPane scp = new JScrollPane();

    public Settings() {
        initComponents();
    }

    public void initComponents() {
        lbTitle.setFont(sgUI18b);
        setSize(1000, 600);
        setVisible(true);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(5, 5));
        add(lbTitle, BorderLayout.NORTH);
        lbTitle.setHorizontalAlignment(JLabel.CENTER);
        lbTitle.setFont(sgUI15);
        add(pnContent, BorderLayout.CENTER);
        pnContent.setLayout(new GridLayout(2, 2));
        pnContent.add(pnThongBao);
        pnContent.add(pnGia);
        pnContent.add(pnGia1);
        pnContent.add(pnGia2);
        pnThongBao.setLayout(new BorderLayout(5, 5));
        pnThongBao.add(lbThongBao, BorderLayout.NORTH);
        lbThongBao.setFont(sgUI15);
        lbThongBao.setBackground(Color.decode("#F0FFF0"));
        lbThongBao.setOpaque(true);
        lbThongBao.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(1, 1, 1, 1, Color.decode("#98FB98")), new EmptyBorder(3, 5, 3, 5)));
        lbChonNgay.setFont(sgUI15);
        lbTieuDe.setFont(sgUI15);
        pnThongBao.add(pnThongBaoCenter, BorderLayout.CENTER);
        pnThongBaoCenter.setLayout(new BorderLayout(5, 5));
        pnThongBaoCenter.add(pnThongBaoTop, BorderLayout.NORTH);
        pnThongBaoCenter.add(pnThongBaoContent, BorderLayout.CENTER);
        pnThongBaoCenter.add(pnBtn, BorderLayout.SOUTH);
        pnBtn.setLayout(new BorderLayout());
        pnBtn.add(btnAcept, BorderLayout.EAST);
        pnThongBaoTop.setLayout(new BorderLayout(5, 5));
        pnThongBaoTop.add(lbChonNgay, BorderLayout.WEST);
        pnThongBaoTop.add(dc, BorderLayout.CENTER);

        pnThongBaoContent.setLayout(new BorderLayout(5, 5));
        pnThongBaoContent.add(pnTieuDe, BorderLayout.NORTH);
        pnTieuDe.setLayout(new BorderLayout(5, 5));
        pnTieuDe.add(lbTieuDe, BorderLayout.WEST);
        pnTieuDe.add(txtTieuDe, BorderLayout.CENTER);
        pnThongBaoContent.add(scp, BorderLayout.CENTER);
        scp.setViewportView(txtarea);
        btnAcept.setFocusPainted(false);
        btnAcept.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (dc.getDate() == null) {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập ngày đăng bài", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    if (txtTieuDe.getText().trim().length() == 0) {
                        JOptionPane.showMessageDialog(null, "Vui lòng điền tiêu đề", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        if (txtTieuDe.getText().trim().length() >= 200) {
                            JOptionPane.showMessageDialog(null, "Tiêu đề không quá 200 ký tự", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            if (txtarea.getText().trim().length() == 0) {
                                JOptionPane.showMessageDialog(null, "Vui lòng nhập nội dung", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                if (txtarea.getText().length() >= 1000) {
                                    JOptionPane.showMessageDialog(null, "Nội dung không quá 1000 ký tự", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                                } else {
                                    int ans = JOptionPane.showConfirmDialog(null, "Bạn có muôn đăng không", "Thông báo", JOptionPane.YES_NO_OPTION);
                                    if (ans == JOptionPane.YES_OPTION) {
                                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                                        String date = sdf.format(dc.getDate());
                                        Date datetime = new Date();
                                        try {
                                            java.sql.Connection conn = DBConnect.getConnection();
                                            String query = "insert into ThongBao values (?,?,?,?)";
                                            PreparedStatement ps = conn.prepareCall(query);
                                            ps.setString(1, "maTB" + datetime.getHours() + datetime.getMinutes() + datetime.getSeconds());
                                            ps.setString(2, txtTieuDe.getText().trim());
                                            ps.setString(3, txtarea.getText());
                                            ps.setString(4, date);
                                            if (ps.executeUpdate() >= 1) {
                                                JOptionPane.showMessageDialog(null, "Đăng thông báo thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                                                txtTieuDe.setText("");
                                                txtarea.setText("");
                                                dc.setCalendar(null);
                                            }
                                            ps.close();
                                            conn.close();
                                        } catch (Exception ex) {
                                            JOptionPane.showMessageDialog(null, "Đăng không thành công");
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        });
    }
}
