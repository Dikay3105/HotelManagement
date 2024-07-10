/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import BUS.NhanVienBUS;
import DTO.NhanVienDTO;
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
public class AddNhanVienGUI{
    
    String gt[] = {"Giới Tính","Nam", "Nữ"};
    String phongban[] = {"Phòng Ban","Quản Lý", "Lễ Tân", "Buồng Phòng", "Bảo Vệ", "Bếp", "Kế Toán"};
    
    private JFrame frBackground = new JFrame();
   
    private JLabel lbMaNhanVien, lbHoTen, lbMatKhau, lbGTinh, lbPBan, lbHeso, lbNgaySinh, lbEmail;
    private JTextField jtfMaNhanVien, jtfHoTen, jtfMatKhau, jtfHeso, jtfEmail;
    private JComboBox cbGioiTinh, cbPhongBan;
    private JDateChooser jdNgaySinh;
    private JButton jbOK, jbCANCEL; 
    private JLabel lbTitle = new JLabel("Thêm Nhân Viên");
    private JPanel pnContent = new JPanel();
    private Font sgUI25b = new Font("Segoe UI", Font.BOLD, 25);
    private Font sgUI15b = new Font("Segoe UI", Font.BOLD, 15);
    private Font sgUI15 = new Font("Segoe UI", Font.PLAIN, 15);
    static NhanVienBUS nv = new NhanVienBUS();
    private ArrayList<NhanVienDTO> NVlist;
    DefaultTableModel model;
    PanelNhanVien PNNV= new PanelNhanVien();
    private JTable tableADD;
    public AddNhanVienGUI(JTable tablePANEL){
        tableADD = tablePANEL;
        initComponents(tablePANEL);
    }
    public void initComponents(JTable tablePANEL){
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
        lbTitle.setHorizontalAlignment(JLabel.CENTER);
        lbTitle.setFont(sgUI25b);
        lbTitle.setBackground(Color.white);
        
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
        if(count()<10)
            jtfMaNhanVien.setText("NV0"+count());
        else
            jtfMaNhanVien.setText("NV"+count());
        jtfMaNhanVien.setEnabled(false);
        
        lbHoTen = new JLabel("Họ & Tên: ");
        lbHoTen.setFont(sgUI15b);
        lbHoTen.setBounds(50, 90, 100, 50);
        jtfHoTen = new JTextField();
        jtfHoTen.setBounds(160, 95, 230, 40);
        jtfHoTen.setFont(sgUI15);
        
        lbMatKhau = new JLabel("Mật Khẩu: ");
        lbMatKhau.setFont(sgUI15b);
        lbMatKhau.setBounds(50, 150, 100, 50);
        jtfMatKhau = new JTextField();
        jtfMatKhau.setBounds(160, 155, 230, 40);       
        jtfMatKhau.setFont(sgUI15);
        
        lbGTinh = new JLabel("Giới Tính: ");
        lbGTinh.setFont(sgUI15b);
        lbGTinh.setBounds(50, 210, 100, 50);
        cbGioiTinh = new JComboBox(gt);
        cbGioiTinh.setBackground(Color.decode("#FAFAFA"));
        cbGioiTinh.setBorder(new MatteBorder(1, 1, 1, 1, Color.black));
        cbGioiTinh.setBounds(160, 215, 230, 40);
        cbGioiTinh.setFont(sgUI15);
        
//        lbGTinh = new JLabel("Giới Tính: ");
//        lbGTinh.setFont(sgUI15b);
//        lbGTinh.setBounds(50, 260, 100, 50);
//        cbGioiTinh = new JComboBox(gt);
//        cbGioiTinh.setBackground(Color.decode("#FAFAFA"));
//        cbGioiTinh.setBorder(new MatteBorder(1, 1, 1, 1, Color.black));
//        cbGioiTinh.setBounds(160, 265, 230, 40);
//        cbGioiTinh.setFont(sgUI15);
        
        
        
        lbNgaySinh = new JLabel("Ngày sinh ");
        lbNgaySinh.setFont(sgUI15b);
        lbNgaySinh.setBounds(50, 270, 100, 50);
        jdNgaySinh = new JDateChooser();
        LocalDate dateDefault = java.time.LocalDate.now();
        Date date = java.sql.Date.valueOf(dateDefault);
        jdNgaySinh.setDate(date);
        jdNgaySinh.setDateFormatString("yyyy-MM-dd");
        jdNgaySinh.setBackground(Color.decode("#FAFAFA"));
        jdNgaySinh.setBorder(new MatteBorder(1, 1, 1, 1, Color.black));
        jdNgaySinh.setBounds(160, 275, 230, 40);
        jdNgaySinh.setFont(sgUI15);
        
        lbPBan = new JLabel("Phòng Ban : ");
        lbPBan.setFont(sgUI15b);
        lbPBan.setBounds(50, 330, 100, 50);
        cbPhongBan = new JComboBox(phongban);
        cbPhongBan.setBackground(Color.decode("#FAFAFA"));
        cbPhongBan.setBorder(new MatteBorder(1, 1, 1, 1, Color.black));
        cbPhongBan.setBounds(160, 335, 230, 40);
        cbPhongBan.setFont(sgUI15);
        
        lbEmail = new JLabel("Email: ");
        lbEmail.setFont(sgUI15b);
        lbEmail.setBounds(50, 390, 100, 50);
        jtfEmail = new JTextField();
        jtfEmail.setBounds(160, 395, 230, 40);       
        jtfEmail.setFont(sgUI15);
        
        lbHeso = new JLabel("Hệ Số: ");
        lbHeso.setFont(sgUI15b);
        lbHeso.setBounds(50, 450, 100, 50);
        jtfHeso = new JTextField();
        jtfHeso.setBounds(160, 455, 230, 40);       
        jtfHeso.setFont(sgUI15);
        
        jbOK = new JButton("OK");
        jbOK.setBounds(100, 535, 100, 40);
        jbOK.setFont(sgUI15b);
        jbOK.setBackground(Color.decode("#89EA73"));
        jbOK.setForeground(Color.white);
        
        jbCANCEL = new JButton("CANCEL");
        jbCANCEL.setBounds(240, 535, 100, 40);
        jbCANCEL.setFont(sgUI15b);
        jbCANCEL.setBackground(Color.decode("#B9B8B8"));
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
        pnContent.add(jbOK);
        pnContent.add(jbCANCEL);
        
        jbOK.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                jbOK.setBackground(Color.decode("#61EC41"));
            }

            @Override
            public void mouseExited(MouseEvent e) {

                jbOK.setBackground(Color.decode("#89EA73"));
            }
        });
        jbOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                String mnv = jtfMaNhanVien.getText();
                String mknv = jtfMatKhau.getText();
                String namenv = jtfHoTen.getText();
                String gtinh = cbGioiTinh.getSelectedItem().toString();
                
                java.util.Date date = jdNgaySinh.getDate();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                String nsinh = formatter.format(date);

                String pban = cbPhongBan.getSelectedItem().toString();
                String email = jtfEmail.getText();
                String hso = jtfHeso.getText();
                int ck=0;
                int choice;
                String text = "Bạn Muốn Thêm Nhân viên Có Thông Tin:"+ "\nMã Nhân Viên: "+mnv + "\nMật Khẩu: "+mknv+"\nHọ Tên: "+namenv+"\nGiới Tính: "+gtinh+"\nNgày Sinh: "+nsinh+"\nPhòng Ban: "+pban+"\nEmail: "+email+"\nHệ Số Lương: "+hso;
                choice = JOptionPane.showConfirmDialog(null,text, "Thông Báo xác Nhận", JOptionPane.YES_NO_OPTION);
                if(choice==JOptionPane.YES_NO_OPTION){
                    if(checkInput(mnv, namenv, mknv, hso, gtinh, pban, email)){
                        for(NhanVienDTO nvDTO : NVlist){
                            if(nvDTO.getMaNV().equals(mnv)){
                                 ck=1;
                                 break;
                            }
                        }
                        if(ck==0) 
                                try {
                                    addNhanVien(mnv, mknv, namenv, gtinh, nsinh, pban, email, hso);
                                    frBackground.setVisible(false);
                                    PanelNhanVien.loadTalbe(tableADD);
                                } catch (SQLException | ParseException ex) {
                                    Logger.getLogger(AddNhanVienGUI.class.getName()).log(Level.SEVERE, null, ex);
                                }
                        else    JOptionPane.showMessageDialog(null, "MÃ NHÂN VIÊN ĐÃ TỒN TẠI", "Thông Báo", JOptionPane.OK_OPTION);

                    }
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
                int cancel = JOptionPane.showConfirmDialog(pnContent, "Bạn Muốn Hủy Thêm Nhân Viên", "Thông báo", JOptionPane.OK_CANCEL_OPTION);
                if (cancel == 0) {
                    frBackground.setVisible(false);
                }
            }
        });
    }
    public boolean checkInput(String mnv, String namenv, String mknv, String hso, String gtinh, String pban, String email) {
        if ("".equals(mnv) || "".equals(namenv) || "".equals(mknv) || "".equals(hso) || email.isEmpty()) {
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
        if(gtinh.equals("Giới Tính") || pban.equals("Phòng Ban")){
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
    public static boolean isValidEmail(String email) {
        // regex pattern for email validation
        String regex = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
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
    public void addNhanVien(String mnv, String mknv, String namenv, String gtinh, String nsinh, String pban,String email, String hso) throws SQLException, ParseException{
                    NhanVienDTO nv = new NhanVienDTO();
                    nv.setMaNV(mnv);
                    nv.setmKNV(mknv);
                    nv.setTenNV(namenv);
                    nv.setGioiTinh(gtinh);
                    nv.setTenPB(pban);
                    nv.setNgaySinhString(nsinh);
                    nv.setEmail(email);
                    nv.setHeSoLuong(Integer.parseInt(hso));
                    if (new NhanVienBUS().insertNhanVien(nv)) {    
                        NVlist.add(nv); // them vao danh sach SV
                        JOptionPane.showMessageDialog(null, "THÊM THÀNH CÔNG NHÂN VIÊN CÓ MÃ NHÂN VIÊN: " + mnv);            
                    } else {
                        JOptionPane.showMessageDialog(null, "Error");
                    }


         
    }
    ////////////////////////////////////////////////////////////////        
    public  int count() {
        int count = NhanVienBUS.getSize();
        return count;
    }
    
//////////////////////////////////////////////////////////////// 
}
