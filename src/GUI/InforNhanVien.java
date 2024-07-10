package GUI;

import BUS.NhanVienBUS;
import DAO.NhanVienDAO;
import DTO.NhanVienDTO;
import static GUI.AddNhanVienGUI.isValidEmail;
import com.toedter.calendar.JDateChooser;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ANH KHOA
 */
public class InforNhanVien {

    String gt[] = {"Giới Tính", "Nam", "Nữ"};
    String phongban[] = {"Phòng Ban", "Quản Lý", "Lễ Tân", "Buồng Phòng", "Bảo Vệ", "Bếp", "Kế Toán"};

    private JFrame frBackground = new JFrame();

    private JLabel lbMaNhanVien, lbHoTen, lbMatKhau, lbGTinh, lbPBan, lbHeso, lbNgaySinh, lbEmail;
    private JTextField jtfMaNhanVien, jtfHoTen, jtfMatKhau, jtfHeso, jtfEmail;
    private JComboBox cbGioiTinh, cbPhongBan;
    private JDateChooser jdNgaySinh;
    private JButton jbDELETE, jbEDIT, jbCANCEL;
    private JLabel lbTitle = new JLabel("Thêm Nhân Viên");
    private JPanel pnContent = new JPanel();
    private Font sgUI25b = new Font("Segoe UI", Font.BOLD, 25);
    private Font sgUI15b = new Font("Segoe UI", Font.BOLD, 15);
    private Font sgUI15 = new Font("Segoe UI", Font.PLAIN, 15);
    static NhanVienBUS nv = new NhanVienBUS();
    private ArrayList<NhanVienDTO> NVlist;
    DefaultTableModel model;
    PanelNhanVien PNNV = new PanelNhanVien();
    private JTable tableADD;
    
    
    public InforNhanVien(String maNV, String passNV, String nameNV, String gtinh, String nsinh, String pban,String email, String hso, JTable tablePANEL, int SelectRow) throws ParseException {
        tableADD = tablePANEL;
        initComponents(maNV, passNV, nameNV, gtinh, nsinh, pban, email, hso, tablePANEL, SelectRow);
    }

    public void initComponents(String maNV, String passNV, String nameNV, String gtinh, String nsinh, String pban, String email, String hso, JTable tablePANEL, int SelectRow) throws ParseException {
        NhanVienBUS nv = new NhanVienBUS();
        NVlist = new NhanVienBUS().getListNhanVien();
        frBackground.setVisible(true);
        frBackground.setSize(450, 700);
        frBackground.setLocationRelativeTo(null);
        frBackground.setLayout(new BorderLayout());
        frBackground.setResizable(false);
        frBackground.add(lbTitle, BorderLayout.NORTH);
        lbTitle.setPreferredSize(new Dimension(frBackground.getWidth(), 50));
        lbTitle.setOpaque(true);
        lbTitle.setBackground(Color.white);
        lbTitle.setHorizontalAlignment(JLabel.CENTER);
        lbTitle.setFont(sgUI25b);

        frBackground.add(pnContent);
        pnContent.setBackground(Color.decode("#F0FFFF"));
        pnContent.setLayout(null);
        frBackground.add(pnContent, BorderLayout.CENTER);

        lbMaNhanVien = new JLabel("Mã Nhân Viên: ");
        lbMaNhanVien.setFont(sgUI15b);
        lbMaNhanVien.setBounds(50, 30, 150, 50);
        jtfMaNhanVien = new JTextField();
        jtfMaNhanVien.setBounds(160, 35, 230, 40);
        jtfMaNhanVien.setFont(sgUI15b);
        jtfMaNhanVien.setText(maNV);
        jtfMaNhanVien.setEditable(false);

        lbMatKhau = new JLabel("Mật Khẩu: ");
        lbMatKhau.setFont(sgUI15b);
        lbMatKhau.setBounds(50, 90, 100, 50);
        jtfMatKhau = new JTextField();
        jtfMatKhau.setBounds(160, 95, 230, 40);
        jtfMatKhau.setFont(sgUI15);
        jtfMatKhau.setText(passNV);

        lbHoTen = new JLabel("Họ & Tên: ");
        lbHoTen.setFont(sgUI15b);
        lbHoTen.setBounds(50, 150, 100, 50);
        jtfHoTen = new JTextField();
        jtfHoTen.setBounds(160, 155, 230, 40);
        jtfHoTen.setFont(sgUI15);
        jtfHoTen.setText(nameNV);

        lbGTinh = new JLabel("Giới Tính: ");
        lbGTinh.setFont(sgUI15b);
        lbGTinh.setBounds(50, 210, 100, 50);
        cbGioiTinh = new JComboBox(gt);
        cbGioiTinh.setBackground(Color.decode("#FAFAFA"));
        cbGioiTinh.setBorder(new MatteBorder(1, 1, 1, 1, Color.black));
        cbGioiTinh.setBounds(160, 215, 230, 40);
        cbGioiTinh.setFont(sgUI15);
        if (gtinh.equals("Nữ")) {
            cbGioiTinh.setSelectedItem(gt[2]);
        } else {
            cbGioiTinh.setSelectedItem(gt[1]);
        }

        lbPBan = new JLabel("Phòng Ban : ");
        lbPBan.setFont(sgUI15b);
        lbPBan.setBounds(50, 270, 100, 50);
        cbPhongBan = new JComboBox(phongban);
        cbPhongBan.setBackground(Color.decode("#FAFAFA"));
        cbPhongBan.setBorder(new MatteBorder(1, 1, 1, 1, Color.black));
        cbPhongBan.setBounds(160, 275, 230, 40);
        cbPhongBan.setFont(sgUI15);
        if (pban.equals(phongban[1])) {
            cbPhongBan.setSelectedItem(phongban[1]);
        } else if (pban.equals(phongban[2])) {
            cbPhongBan.setSelectedItem(phongban[2]);
        } else if (pban.equals(phongban[3])) {
            cbPhongBan.setSelectedItem(phongban[3]);
        } else if (pban.equals(phongban[4])) {
            cbPhongBan.setSelectedItem(phongban[4]);
        } else if (pban.equals(phongban[5])) {
            cbPhongBan.setSelectedItem(phongban[5]);
        } else {
            cbPhongBan.setSelectedItem(phongban[6]);
        }

        lbNgaySinh = new JLabel("Ngày sinh ");
        lbNgaySinh.setFont(sgUI15b);
        lbNgaySinh.setBounds(50, 330, 100, 50);
        jdNgaySinh = new JDateChooser();
        LocalDate dateDefault = java.time.LocalDate.now();
        Date date = java.sql.Date.valueOf(dateDefault);
        jdNgaySinh.setDate(date);
        jdNgaySinh.setDateFormatString("yyyy-MM-dd");
        jdNgaySinh.setBackground(Color.decode("#FAFAFA"));
        jdNgaySinh.setBorder(new MatteBorder(1, 1, 1, 1, Color.black));
        jdNgaySinh.setBounds(160, 335, 230, 40);
        jdNgaySinh.setFont(sgUI15);
        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = formatter1.parse(nsinh + "");
        jdNgaySinh.setDate(date1);
        
        lbEmail = new JLabel("Email: ");
        lbEmail.setFont(sgUI15b);
        lbEmail.setBounds(50, 390, 100, 50);
        jtfEmail = new JTextField();
        jtfEmail.setBounds(160, 395, 230, 40);
        jtfEmail.setFont(sgUI15);
        jtfEmail.setText(email);

        lbHeso = new JLabel("Hệ Số: ");
        lbHeso.setFont(sgUI15b);
        lbHeso.setBounds(50, 450, 100, 50);
        jtfHeso = new JTextField();
        jtfHeso.setBounds(160, 455, 230, 40);
        jtfHeso.setFont(sgUI15);
        jtfHeso.setText(hso);

        jbDELETE = new JButton("Xóa");
        jbDELETE.setBounds(30, 535, 100, 40);
        jbDELETE.setFont(sgUI15b);
        jbDELETE.setBackground(Color.decode("#EF6F6F"));
        jbDELETE.setForeground(Color.white);

        jbEDIT = new JButton("Sửa");
        jbEDIT.setBounds(160, 535, 100, 40);
        jbEDIT.setFont(sgUI15b);
        jbEDIT.setBackground(Color.decode("#F1EF65"));
        jbEDIT.setForeground(Color.white);

        jbCANCEL = new JButton("Cancel");
        jbCANCEL.setBounds(290, 535, 100, 40);
        jbCANCEL.setFont(sgUI15b);
        jbCANCEL.setBackground(Color.decode("#D3D3D3"));
        jbCANCEL.setForeground(Color.white);

        pnContent.add(lbMaNhanVien);
        pnContent.add(jtfMaNhanVien);
        pnContent.add(lbMatKhau);
        pnContent.add(jtfMatKhau);
        pnContent.add(lbHoTen);
        pnContent.add(jtfHoTen);
        pnContent.add(lbGTinh);
        pnContent.add(cbGioiTinh);
        pnContent.add(lbNgaySinh);
        pnContent.add(jdNgaySinh);
        pnContent.add(lbPBan);
        pnContent.add(cbPhongBan);
        pnContent.add(lbEmail);
        pnContent.add(jtfEmail);
        pnContent.add(lbHeso);
        pnContent.add(jtfHeso);
        pnContent.add(jbDELETE);
        pnContent.add(jbEDIT);
        pnContent.add(jbCANCEL);

        jbDELETE.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    deleteNhanVieṇ̣̣̣̣̣̣(tablePANEL, maNV, SelectRow);
                    PanelNhanVien.loadTalbe(tableADD);
                } catch (SQLException ex) {
                    Logger.getLogger(InforNhanVien.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(InforNhanVien.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
        jbDELETE.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                jbDELETE.setBackground(Color.decode("#EA4E4E"));
            }

            @Override
            public void mouseExited(MouseEvent e) {

                jbDELETE.setBackground(Color.decode("#EF6F6F"));
            }
        });
        jbEDIT.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                jbEDIT.setBackground(Color.decode("#EFEC3F"));
            }

            @Override
            public void mouseExited(MouseEvent e) {

                jbEDIT.setBackground(Color.decode("#F1EF65"));
            }
        });
        jbEDIT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    java.util.Date date = jdNgaySinh.getDate();
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    String dateofbirth = formatter.format(date);
                    editNhanVien(jtfMaNhanVien.getText(), jtfMatKhau.getText(), jtfHoTen.getText(), cbGioiTinh.getSelectedItem() + "", dateofbirth, cbPhongBan.getSelectedItem() + "",jtfEmail.getText()+"", jtfHeso.getText(), tableADD);

                } catch (SQLException ex) {
                    Logger.getLogger(InforNhanVien.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(InforNhanVien.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        jbCANCEL.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                jbCANCEL.setBackground(Color.decode("#B9B8B8"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                jbCANCEL.setBackground(Color.decode("#D3D3D3"));
            }
        });
        jbCANCEL.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frBackground.setVisible(false);
            }
        });
    }

    public boolean checkInput(String mnv, String namenv, String mknv, String hso, String gtinh, String pban, String email) {
        if ("".equals(mnv) || "".equals(namenv) || "".equals(mknv) || "".equals(hso)) {
            JOptionPane.showMessageDialog(null, "HÃY NHẬP ĐẦY ĐỦ VÀ CHÍNH XÁC THÔNG TIN", "Thông Báo", JOptionPane.OK_OPTION);
            return false;
        } else if (isSpecialCharacters(hso) == true || isWord(hso) == true || isNumber(Integer.parseInt(hso)) == false) {
            JOptionPane.showMessageDialog(null, "LƯU Ý: HỆ SỐ LÀ SỐ TỰ NHIÊN", "Thông Báo", JOptionPane.OK_OPTION);
            return false;
        } else if (isSpecialCharacters(namenv) == true /* || isNumber(Integer.parseInt(namenv))==true*/) {
            JOptionPane.showMessageDialog(null, "LƯU Ý: HỌ TÊN KHÔNG ĐƯỢC CÓ KÍ TỰ ĐẶC BIỆT", "Thông Báo", JOptionPane.OK_OPTION);
            return false;
        }
        else if (!isValidEmail(email)) {
            JOptionPane.showMessageDialog(null, "LƯU Ý: HÃY NHẬP ĐÚNG ĐỊNH DẠNG EMAIL", "Thông Báo", JOptionPane.OK_OPTION);
            return false;
        }
        if (gtinh.equals("Giới Tính") || pban.equals("Phòng Ban")) {
            JOptionPane.showMessageDialog(null, "HÃY NHẬP ĐẦY ĐỦ VÀ CHÍNH XÁC THÔNG TIN", "Thông Báo", JOptionPane.OK_OPTION);
            return false;
        }

        return true;
    }

    public boolean isNumber(int num) {
        return num > 0 && Integer.toString(num) != null && Integer.toString(num).matches("[-+]?\\d*\\.?\\d+");
    }

    public boolean isWord(String sData) {
        for (int i = 0; i < sData.length(); i++) {
            if (Character.isLetter(sData.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    public boolean isSpecialCharacters(String name) {
        String specialCharacters = "!#$%&'()*+,-./:;<=>?@[]^_`{|}~";
        String str2[] = name.split("");
        int count = 0;
        for (int i = 0; i < str2.length; i++) {
            if (specialCharacters.contains(str2[i])) {
                count++;
            }
        }
        if (name != null && count == 0) {
            return false;
        } else {
            return true;
        }
    }
    public static boolean isValidEmail(String email) {
        // regex pattern for email validation
        String regex = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    public void deleteNhanVieṇ̣̣̣̣̣̣(JTable table, String key, int SelectRow) throws SQLException, ParseException {
        String id;
        model = (DefaultTableModel) table.getModel();
        int choice;
        choice = JOptionPane.showConfirmDialog(null, "BẠN CÓ MUỐN XÓA DÒNG ĐÃ CHỌN", "Thông Báo", JOptionPane.YES_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            for (NhanVienDTO s : NVlist) {
                if ((s.getMaNV()).equals(key) == true) {
                    model.removeRow(SelectRow);
                    s.setXuLy(1);
                    if (nv.deleteNhanVieṇ̣̣̣̣̣̣(s) == true) {
                        NVlist.remove(s);
                        JOptionPane.showMessageDialog(null, "XÓA THÀNH CÔNG", "Thông Báo", JOptionPane.OK_OPTION);
                        frBackground.setVisible(false);

                    } else {
                        JOptionPane.showMessageDialog(null, "Error", "Thông Báo", JOptionPane.OK_OPTION);
                    }
                    break;
                }
            }
        }
    }

    public void editNhanVien(String mnv, String mknv, String namenv, String gtinh, String nsinh, String pban,String email, String hso, JTable table) throws SQLException, ParseException {
        int choice = JOptionPane.showConfirmDialog(null, "Bạn Muốn Sửa Lại Thông Tin Nhân Viên", "Thông Báo", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            if (checkInput(mnv, namenv, mknv, hso,gtinh,pban,email) == true) {
                for (NhanVienDTO s : NVlist) {
                    if (s.getMaNV().equals(mnv)) {
                        s.setmKNV(mknv);
                        s.setTenNV(namenv);
                        s.setGioiTinh(gtinh);
                        s.setTenPB(pban);
                        s.setNgaySinhString(nsinh);
                        s.setEmail(email);
                        s.setHeSoLuong(Integer.parseInt(hso));
                        if (nv.editNhanVien(s) == true) {
                            JOptionPane.showMessageDialog(null, "ĐÃ SỬA THÔNG TIN NHÂN VIÊN CÓ MÃ NHÂN VIÊN: " + mnv, "Thông Báo", JOptionPane.OK_OPTION);
                            model = (DefaultTableModel) table.getModel();
                            int numberOfrows = model.getRowCount();
                            for (int k = 0; k < numberOfrows; k++) {
                                String id = model.getValueAt(k, 1) + "";
                                if (id.equals(mnv)) {
                                    model.setValueAt(mknv + "", k, 2);
                                    model.setValueAt(namenv + "", k, 3);
                                    model.setValueAt(gtinh + "", k, 4);
                                    model.setValueAt(nsinh + "", k, 5);
                                    model.setValueAt(pban + "", k, 6);
                                    model.setValueAt(email + "", k, 7);
                                    model.setValueAt(hso + "", k, 8);
                                    PanelNhanVien.loadTalbe(tableADD);
                                    frBackground.setVisible(false);
                                }
                            }
                            break;
                        } else {
                            JOptionPane.showMessageDialog(null, "Error");
                        }
                        break;
                    }
                }
            }
        }
    }
}
