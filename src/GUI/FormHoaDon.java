package GUI;

import BUS.ChiTietThueBUS;
import BUS.DichVuBUS;
import BUS.HoaDonBUS;
import BUS.KhachHangBUS;
import BUS.NhanVienBUS;
import BUS.PhongBUS;
import BUS.SuDungDichVuBUS;
import BUS.ThuePhongBUS;
import DTO.ChiTietThueDTO;
import DTO.DichVuDTO;
import DTO.HoaDonDTO;
import DTO.KhachHangDTO;
import DTO.NhanVienDTO;
import DTO.PhongDTO;
import DTO.SuDungDichVuDTO;
import DTO.ThuePhongDTO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;

public class FormHoaDon extends JFrame {

    private JFrame frBackground = new JFrame();
    private JLabel lbTitle = new JLabel("HÓA ĐƠN");
    private JPanel pnTitle = new JPanel();
    private JPanel pnContentCenter = new JPanel();

    private JPanel pnContentCenterKH = new JPanel();
    private JLabel lbTitleCenterKH = new JLabel("THÔNG TIN KHÁCH THUÊ", JLabel.CENTER);
    private JPanel pnTitleCenterKH = new JPanel();
    private JPanel pnCenterKH = new JPanel();
    private JPanel pnSouthKH = new JPanel();
    private JPanel pnWestKH = new JPanel();

    private JPanel pnContentCenterDV = new JPanel();
    private JLabel lbTitleCenterDV = new JLabel("CHI TIẾT DỊCH VỤ", JLabel.CENTER);
    private JPanel pnTitleCenterDV = new JPanel();
    private JPanel pnTablePhong = new JPanel();
    private JPanel pnGiaPhong = new JPanel();
    private JPanel pnTableDV = new JPanel();
    private JPanel pnGiaDV = new JPanel();
    private JPanel pnCenterDV = new JPanel();
    private JTable tablePhong, tableDV;
    private JScrollPane jscPhong, jscDV;

    private JPanel pnContentEast = new JPanel();
    private JLabel lbTitleEast = new JLabel("THANH TOÁN", JLabel.CENTER);
    private JPanel pnTitleEast = new JPanel();
    private JPanel pnEast = new JPanel();
    private JPanel pnInput = new JPanel();
    private JPanel pnBton = new JPanel();

    private JLabel lbtenKH, lbmaKH, lbcmnd, lbGt, lbSdt, lbLanThue, lbGiaPhongTC, lbTongGiaDv, lbCoc, lbTongCong, lbKhach, lbThoi, txtlbCoc, txtlbTongCong, lbKhuyeMai, txtlbKhuyenMai, lbThanhTien, txtlbThanhTien;
    private JLabel textlbtenKH, textlbmaKH, textlbcmnd, textlbGt, textlbSdt, textlbLanThue, textlbGiaPhong, textlbKhuyenMai, textlbGiaPhongTC, textlbTongGiaDv, textlbThue, textlbTongCong, textlbKhach, textlbThoi;
    private JButton btnThanhToan, btnThoat;
    private JTextField txtenKH, txmaKH, txcmnd, txGt, txSdt, txtKhach, txtThoi;
    Font sgUI15 = new Font("Segoe UI", Font.BOLD, 15);
    Font sgUI15p = new Font("Segoe UI", Font.PLAIN, 15);
    Font sgUI15b = new Font("Segoe UI", Font.BOLD, 15);
    Font sgUI13 = new Font("Segoe UI", Font.PLAIN, 13);
    Font sgUI13b = new Font("Segoe UI", Font.BOLD, 13);
    Font sgUI11b = new Font("Segoe UI", Font.BOLD, 11);
    Font sgUI18b = new Font("Segoe UI", Font.BOLD, 20);
    DefaultTableModel model;

    ArrayList<KhachHangDTO> KhachHangList = KhachHangBUS.getListKhachHang();
    ArrayList<ThuePhongDTO> ThuePhongList = ThuePhongBUS.getListTP();
    ArrayList<SuDungDichVuDTO> SuDungDichVuList = SuDungDichVuBUS.getListSuDungDichVu();
    ArrayList<DichVuDTO> DichVuList = DichVuBUS.getListDichVu();
    ArrayList<NhanVienDTO> NhanVienList = new NhanVienBUS().getListNhanVien();
    ArrayList<PhongDTO> PhongList = PhongBUS.getListPhong();
    ArrayList<ChiTietThueDTO> CTTList = ChiTietThueBUS.getListCTT();
    ArrayList<HoaDonDTO> HDList = HoaDonBUS.getListHD();

    public FormHoaDon(ChiTietThueDTO CTT) {
        initComponents(CTT);
    }

    public void initComponents(ChiTietThueDTO CTT) {
        frBackground.setSize(1400, 700);
        frBackground.setLocationRelativeTo(null);
        //frBackground.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frBackground.setLayout(new BorderLayout());

        lbTitle.setHorizontalAlignment(JLabel.CENTER);
        lbTitle.setFont(sgUI18b);
        pnTitle.setPreferredSize(new Dimension(1200, 50));
        pnTitle.setLayout(new BorderLayout());
        pnTitle.add(lbTitle, BorderLayout.CENTER);
        pnTitle.setBackground(Color.white);
        pnTitle.setBorder(new MatteBorder(0, 0, 1, 0, Color.black));
        frBackground.add(pnTitle, BorderLayout.NORTH);
//Content Thông tin Khách        
        pnContentCenter.setPreferredSize(new Dimension(1100, 600));
        pnContentCenter.setBackground(Color.WHITE);
        pnContentCenter.setBorder(new MatteBorder(10, 7, 7, 7, Color.WHITE));
        pnContentCenter.setLayout(new BorderLayout());

        pnContentCenterKH.setPreferredSize(new Dimension(275, 600));
        pnContentCenterKH.setLayout(new BorderLayout());
        pnContentCenterKH.setBorder(new MatteBorder(1, 1, 1, 1, Color.black));

        lbTitleCenterKH.setFont(sgUI18b);
        pnTitleCenterKH.setPreferredSize(new Dimension(275, 25));
        pnTitleCenterKH.setLayout(new BorderLayout());
        pnTitleCenterKH.setBackground(Color.decode("#98FB98"));
        pnTitleCenterKH.setBorder(new MatteBorder(0, 0, 1, 0, Color.black));
        pnTitleCenterKH.add(lbTitleCenterKH, BorderLayout.CENTER);

        pnCenterKH.setPreferredSize(new Dimension(275, 250));
        pnCenterKH.setLayout(new GridLayout(5, 2));
        pnCenterKH.setBackground(Color.white);

        lbmaKH = new JLabel("Mã Khách Hàng:");
        lbmaKH.setForeground(Color.black);
        lbmaKH.setFont(sgUI15b);
        lbmaKH.setPreferredSize(new Dimension(150, 50));
        pnCenterKH.add(lbmaKH);

        textlbmaKH = new JLabel("" + CTT.getMaKH());
        textlbmaKH.setForeground(Color.red);
        textlbmaKH.setFont(sgUI15b);
        textlbmaKH.setPreferredSize(new Dimension(100, 50));
        pnCenterKH.add(textlbmaKH);

        for (KhachHangDTO KH : KhachHangList) {
            if (CTT.getMaKH().equals(KH.getMaKH())) {

                lbtenKH = new JLabel("Họ Tên:");
                lbtenKH.setForeground(Color.black);
                lbtenKH.setFont(sgUI15b);
                lbtenKH.setPreferredSize(new Dimension(150, 50));
                pnCenterKH.add(lbtenKH);

                textlbtenKH = new JLabel("" + KH.getTenKH());
                textlbtenKH.setForeground(Color.red);
                textlbtenKH.setFont(sgUI15b);
                textlbtenKH.setPreferredSize(new Dimension(100, 50));
                pnCenterKH.add(textlbtenKH);

                lbGt = new JLabel("Giới Tính:");
                lbGt.setForeground(Color.black);
                lbGt.setFont(sgUI15b);
                lbGt.setPreferredSize(new Dimension(150, 50));
                pnCenterKH.add(lbGt);

                textlbGt = new JLabel("" + KH.getGioiTinh());
                textlbGt.setForeground(Color.red);
                textlbGt.setFont(sgUI15b);
                textlbGt.setPreferredSize(new Dimension(100, 50));
                pnCenterKH.add(textlbGt);

                lbcmnd = new JLabel("CMND:");
                lbcmnd.setForeground(Color.black);
                lbcmnd.setFont(sgUI15b);
                lbcmnd.setPreferredSize(new Dimension(150, 50));
                pnCenterKH.add(lbcmnd);

                textlbcmnd = new JLabel("" + KH.getCmnd());
                textlbcmnd.setForeground(Color.red);
                textlbcmnd.setFont(sgUI15b);
                textlbcmnd.setPreferredSize(new Dimension(100, 50));
                pnCenterKH.add(textlbcmnd);

                lbLanThue = new JLabel("Lần Thuê Trước:");
                lbLanThue.setForeground(Color.black);
                lbLanThue.setFont(sgUI15b);
                lbLanThue.setPreferredSize(new Dimension(100, 50));
                pnCenterKH.add(lbLanThue);

                textlbLanThue = new JLabel("" + solanthuetruoc(CTT.getMaKH()));
                textlbLanThue.setForeground(Color.red);
                textlbLanThue.setFont(sgUI15b);
                textlbLanThue.setPreferredSize(new Dimension(100, 50));
                pnCenterKH.add(textlbLanThue);

            }
        }

        pnContentCenterKH.add(pnTitleCenterKH, BorderLayout.NORTH);
        pnContentCenterKH.add(pnCenterKH, BorderLayout.CENTER);

        pnSouthKH.setPreferredSize(new Dimension(290, 325));
        pnSouthKH.setBackground(Color.white);
        pnSouthKH.setLayout(new BorderLayout());
        ImageIcon icon = new ImageIcon(new ImageIcon(getClass().getResource("/GUI/asset/FHDThongTinKhach.png")).getImage().getScaledInstance(290, 325, Image.SCALE_SMOOTH));
        JLabel imageLabel = new JLabel(icon);
        JPanel imagePanel = new JPanel();
        imagePanel.setLayout(new BorderLayout());
        imagePanel.add(imageLabel, BorderLayout.CENTER);
        pnSouthKH.add(imageLabel, BorderLayout.CENTER);
        pnContentCenterKH.add(pnSouthKH, BorderLayout.SOUTH);

        pnWestKH.setPreferredSize(new Dimension(10, 575));
        pnWestKH.setBackground(Color.white);
        pnContentCenterKH.add(pnWestKH, BorderLayout.WEST);

        pnContentCenter.add(pnContentCenterKH, BorderLayout.WEST);
//        
//Content Thông tin Dv           
        pnContentCenterDV.setPreferredSize(new Dimension(800, 600));
        pnContentCenterDV.setBorder(new MatteBorder(1, 0, 1, 1, Color.black));
        pnContentCenterDV.setLayout(new BorderLayout(1, 1));

        lbTitleCenterDV.setFont(sgUI18b);
        pnTitleCenterDV.setPreferredSize(new Dimension(400, 25));
        pnTitleCenterDV.setLayout(new BorderLayout());
        pnTitleCenterDV.setBackground(Color.decode("#98FB98"));
        pnTitleCenterDV.setBorder(new MatteBorder(0, 0, 1, 0, Color.black));
        pnTitleCenterDV.add(lbTitleCenterDV, BorderLayout.CENTER);

        pnCenterDV.setPreferredSize(new Dimension(500, 575));
        pnCenterDV.setLayout(new GridLayout(2, 1));

        //Table Phòng    
        tablePhong = new JTable() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        model = (DefaultTableModel) tablePhong.getModel();
        model.setColumnIdentifiers(new Object[]{
            "STT", "Mã Phòng", "Giá(VNĐ)", "Loại Hình", "Ngày Thuê", "Ngày Trả", "Ngày CheckOut"
        });
        int i = 1;
        ArrayList<ThuePhongDTO> ListTP = new ArrayList<>();
        for (ThuePhongDTO ThuePhong : ThuePhongList) {
            if (CTT.getMaChiTietThue().equals(ThuePhong.getMaChiTietThue())) {
                ListTP.add(ThuePhong);
            }
        }
        for (int k = 0; k < ListTP.size() - 1; k++) {
            for (int h = k + 1; h < ListTP.size(); h++) {
                if (ListTP.get(k).getMaP().equals(ListTP.get(h).getMaP())) {
                    ListTP.remove(h);
                    h--;
                }
            }
        }
        for (ThuePhongDTO ThuePhong : ListTP) {
            model.addRow(new Object[]{
                i++, ThuePhong.getMaP(), ThuePhong.getGia() + "", ThuePhong.getLoaiHinhThue() + "", ThuePhong.getNgayThue(), ThuePhong.getNgayTra(), ThuePhong.getNgayCheckOut()
            });
        }

        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        TableColumn column1 = tablePhong.getColumnModel().getColumn(1);
        column1.setPreferredWidth(50);
        TableColumn column2 = tablePhong.getColumnModel().getColumn(2);
        column2.setPreferredWidth(50);
        TableColumn column3 = tablePhong.getColumnModel().getColumn(3);
        column3.setPreferredWidth(50);
        TableColumn column4 = tablePhong.getColumnModel().getColumn(4);
        column4.setPreferredWidth(85);
        TableColumn column5 = tablePhong.getColumnModel().getColumn(5);
        column5.setPreferredWidth(85);

        tablePhong.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tablePhong.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tablePhong.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        tablePhong.getColumnModel().getColumn(0).setPreferredWidth(10);

        tablePhong.setPreferredSize(new Dimension(400, 227));
        jscPhong = new JScrollPane(tablePhong);
        jscPhong.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        jscPhong.setViewportView(tablePhong);
        tablePhong.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablePhong.setBackground(Color.white);
        tablePhong.getTableHeader().setFont(sgUI15b);
        tablePhong.getTableHeader().setBackground(Color.decode("#1E90FF"));
        tablePhong.setFont(sgUI13);
        tablePhong.getTableHeader().setForeground(Color.white);
        tablePhong.getTableHeader().setPreferredSize(new Dimension(50, 30));
        tablePhong.setRowHeight(30);

        pnTablePhong.setPreferredSize(new Dimension(500, 250));
        pnTablePhong.setLayout(new BorderLayout(1, 1));
        pnTablePhong.setBorder(new MatteBorder(7, 7, 7, 7, Color.white));
        pnTablePhong.add(jscPhong, BorderLayout.CENTER);
        pnCenterDV.add(pnTablePhong);
        //Tổng giá Phòng   
        pnGiaPhong.setPreferredSize(new Dimension(500, 97));
        pnGiaPhong.setBackground(Color.white);
        pnGiaPhong.setLayout(new GridLayout(3, 1, 1, 1));

        JPanel pn1 = new JPanel();
        pn1.setLayout(new FlowLayout(FlowLayout.LEFT));
        pn1.setBackground(Color.white);
        lbGiaPhongTC = new JLabel("Tổng Tiền Phòng:", JLabel.LEFT);
        lbGiaPhongTC.setForeground(Color.black);
        lbGiaPhongTC.setFont(sgUI15b);
        lbGiaPhongTC.setPreferredSize(new Dimension(150, 20));
        pn1.add(lbGiaPhongTC);

        JLabel TienPhong = new JLabel(TongTienPhong(CTT) + " VNĐ");
        TienPhong.setForeground(Color.decode("#32CD32"));
        TienPhong.setFont(sgUI15b);
        TienPhong.setPreferredSize(new Dimension(150, 20));
        pn1.add(TienPhong);
        pnGiaPhong.add(pn1);

        pnTablePhong.add(pnGiaPhong, BorderLayout.SOUTH);
        //Table Phòng
        tableDV = new JTable() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        model = (DefaultTableModel) tableDV.getModel();

        model.setColumnIdentifiers(new Object[]{
            "STT", "Mã DV", "Ngày Nhập", "Tên DV", "Loại", "Số Lượng", "Giá(VNĐ)", "Tổng(VNĐ)"
        });
        int j = 1;
        ArrayList<SuDungDichVuDTO> ListDV = new ArrayList<>();
        for (SuDungDichVuDTO sddv : SuDungDichVuList) {
            if (CTT.getMaChiTietThue().equals(sddv.getMaChiTietThue())) {
                ListDV.add(sddv);
            }
        }
        for (int k = 0; k < ListDV.size() - 1; k++) {
            for (int h = k + 1; h < ListDV.size(); h++) {
                if (ListDV.get(k).getMaDV().equals(ListDV.get(h).getMaDV()) && ListDV.get(k).getNgaySuDungString().equals(ListDV.get(h).getNgaySuDungString()) && ListDV.get(k).getSoLuong() == ListDV.get(h).getSoLuong()) {
                    ListDV.remove(h);
                    h--;
                }
            }
        }
        for (SuDungDichVuDTO SuDungDichVu : ListDV) {
            for (DichVuDTO DichVu : DichVuList) {
                if (CTT.getMaChiTietThue().equals(SuDungDichVu.getMaChiTietThue()) && SuDungDichVu.getXuLy() != 1 && SuDungDichVu.getMaDV().equals(DichVu.getMaDV())) {
                    model.addRow(new Object[]{
                        j++, SuDungDichVu.getMaDV(), SuDungDichVu.getNgaySuDungString(), DichVu.getTenDV(), DichVu.getTenLoaiDV(), SuDungDichVu.getSoLuong(), DichVu.getGiaDV() + "", SuDungDichVu.getDonGia() + ""
                    });
                }
            }
        }

        tableDV.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tableDV.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tableDV.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        tableDV.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        tableDV.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
        tableDV.getColumnModel().getColumn(0).setPreferredWidth(10);
        TableColumn columnDV0 = tableDV.getColumnModel().getColumn(0);
        columnDV0.setPreferredWidth(10);
        TableColumn columnDV1 = tableDV.getColumnModel().getColumn(1);
        columnDV1.setPreferredWidth(20);
        TableColumn columnDV2 = tableDV.getColumnModel().getColumn(2);
        columnDV2.setPreferredWidth(85);
//        TableColumn columnDV3 = tableDV.getColumnModel().getColumn(3);
//        columnDV3.setPreferredWidth(25);
//        TableColumn columnDV4 = tableDV.getColumnModel().getColumn(4);
//        columnDV4.setPreferredWidth(25);
        TableColumn columnDV5 = tableDV.getColumnModel().getColumn(5);
        columnDV5.setPreferredWidth(35);
        TableColumn columnDV6 = tableDV.getColumnModel().getColumn(6);
        columnDV6.setPreferredWidth(40);
        TableColumn columnDV7 = tableDV.getColumnModel().getColumn(7);
        columnDV7.setPreferredWidth(50);

        tableDV.setPreferredSize(new Dimension(500, 274));
        jscDV = new JScrollPane(tableDV);
        jscDV.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        jscDV.setViewportView(tableDV);
        tableDV.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableDV.setBackground(Color.white);
        tableDV.getTableHeader().setFont(sgUI15b);
        tableDV.getTableHeader().setBackground(Color.decode("#1E90FF"));
        tableDV.setFont(sgUI13);
        tableDV.getTableHeader().setForeground(Color.white);
        tableDV.getTableHeader().setPreferredSize(new Dimension(50, 30));
        tableDV.setRowHeight(30);

//        TableColumnModel columnModel = tableDV.getColumnModel();
//        TableColumn column = columnModel.getColumn(0);
//        column.setPreferredWidth(100);
        pnTableDV.setPreferredSize(new Dimension(500, 250));
        pnTableDV.setLayout(new BorderLayout(1, 1));
        pnTableDV.setBorder(new MatteBorder(7, 7, 7, 7, Color.white));
        pnTableDV.add(jscDV, BorderLayout.CENTER);
        pnCenterDV.add(pnTableDV);

        //Tổng giá DV   
        pnGiaDV.setPreferredSize(new Dimension(500, 50));
        pnGiaDV.setBackground(Color.white);
        pnGiaDV.setLayout(new GridLayout(1, 1, 1, 1));

        JPanel pn4 = new JPanel();
        pn4.setLayout(new FlowLayout(FlowLayout.LEFT));
        pn4.setBackground(Color.white);
        lbTongGiaDv = new JLabel("Tổng Tiền Dịch Vụ:", JLabel.LEFT);
        lbTongGiaDv.setForeground(Color.black);
        lbTongGiaDv.setFont(sgUI15b);
        lbTongGiaDv.setPreferredSize(new Dimension(150, 50));
        pn4.add(lbTongGiaDv);

        JLabel lbTienDv = new JLabel(TongTienDichVu(CTT) + " VNĐ");
        lbTienDv.setForeground(Color.decode("#32CD32"));
        lbTienDv.setFont(sgUI15b);
        lbTienDv.setPreferredSize(new Dimension(150, 50));
        pn4.add(lbTienDv);
        pnGiaDV.add(pn4);

        pnTableDV.add(pnGiaDV, BorderLayout.SOUTH);

        pnContentCenterDV.add(pnTitleCenterDV, BorderLayout.NORTH);
        pnContentCenterDV.add(pnCenterDV);
        pnContentCenter.add(pnContentCenterDV, BorderLayout.CENTER);
        frBackground.add(pnContentCenter, BorderLayout.CENTER);
//Content Thanh Toan       
        pnContentEast.setPreferredSize(new Dimension(300, 600));
        pnContentEast.setBorder(new MatteBorder(7, 7, 7, 7, Color.white));
        pnContentEast.setBackground(Color.white);
        pnContentEast.setLayout(new BorderLayout());

        lbTitleEast.setFont(sgUI18b);
        pnTitleEast.setPreferredSize(new Dimension(300, 25));
        pnTitleEast.setBorder(new MatteBorder(1, 1, 0, 1, Color.black));
        pnTitleEast.setLayout(new BorderLayout());
        pnTitleEast.setBackground(Color.decode("#98FB98"));
        pnTitleEast.add(lbTitleEast, BorderLayout.CENTER);

        pnEast.setPreferredSize(new Dimension(300, 575));
        pnEast.setBorder(new MatteBorder(1, 1, 1, 1, Color.black));
        //pnEast.setBackground(Color.white);
        pnEast.setLayout(new BorderLayout());
        pnInput.setPreferredSize(new Dimension(300, 475));
        pnInput.setLayout(new GridLayout(6, 2));
        pnInput.setBackground(Color.white);
        pnInput.setBorder(new MatteBorder(0, 0, 14, 0, Color.white));

        lbTongCong = new JLabel("Tổng Cộng:");
        lbTongCong.setFont(sgUI15b);
        lbTongCong.setBorder(new MatteBorder(0, 7, 0, 0, Color.white));
        lbTongCong.setForeground(Color.black);
        lbTongCong.setPreferredSize(new Dimension(100, 40));
        pnInput.add(lbTongCong);

        txtlbTongCong = new JLabel((TongTienPhong(CTT) + TongTienDichVu(CTT)) + " VNĐ");
        txtlbTongCong.setFont(sgUI15b);
        txtlbTongCong.setForeground(Color.red);
        txtlbTongCong.setPreferredSize(new Dimension(150, 40));
        txtlbTongCong.setFocusable(false);
        pnInput.add(txtlbTongCong);

        lbCoc = new JLabel("Tiền cọc: ");
        lbCoc.setFont(sgUI15b);
        lbCoc.setBorder(new MatteBorder(0, 7, 0, 0, Color.white));
        lbCoc.setForeground(Color.black);
        lbCoc.setPreferredSize(new Dimension(100, 40));
        pnInput.add(lbCoc);

        txtlbCoc = new JLabel(CTT.getTienDatCoc() + " VNĐ");
        txtlbCoc.setFont(sgUI15b);
        txtlbCoc.setForeground(Color.red);
        txtlbCoc.setPreferredSize(new Dimension(150, 40));
        txtlbCoc.setFocusable(false);
        pnInput.add(txtlbCoc);

        lbKhuyeMai = new JLabel("Khuyến Mãi: ");
        lbKhuyeMai.setFont(sgUI15b);
        lbKhuyeMai.setBorder(new MatteBorder(0, 7, 0, 0, Color.white));
        lbKhuyeMai.setForeground(Color.black);
        lbKhuyeMai.setPreferredSize(new Dimension(100, 40));
        pnInput.add(lbKhuyeMai);

        txtlbKhuyenMai = new JLabel(TienKhuyenMai(CTT) + " VNĐ");
        txtlbKhuyenMai.setFont(sgUI15b);
        txtlbKhuyenMai.setForeground(Color.red);
        txtlbKhuyenMai.setPreferredSize(new Dimension(150, 40));
        txtlbKhuyenMai.setFocusable(false);
        pnInput.add(txtlbKhuyenMai);

        lbThanhTien = new JLabel("THÀNH TIỀN: ");
        lbThanhTien.setFont(sgUI15b);
        lbThanhTien.setBorder(new MatteBorder(0, 7, 0, 0, Color.white));
        lbThanhTien.setForeground(Color.black);
        lbThanhTien.setPreferredSize(new Dimension(100, 40));
        pnInput.add(lbThanhTien);

        txtlbThanhTien = new JLabel(ThanhTien(CTT) + " VNĐ");
        txtlbThanhTien.setFont(sgUI15b);
        txtlbThanhTien.setForeground(Color.red);
        txtlbThanhTien.setPreferredSize(new Dimension(150, 40));
        txtlbThanhTien.setFocusable(false);
        pnInput.add(txtlbThanhTien);

        lbKhach = new JLabel("Khách Đưa:");
        lbKhach.setFont(sgUI15b);
        lbKhach.setBorder(new MatteBorder(0, 7, 7, 7, Color.white));
        lbKhach.setForeground(Color.black);
        lbKhach.setPreferredSize(new Dimension(100, 40));
        pnInput.add(lbKhach);

        JPanel pntxtKhach = new JPanel(new BorderLayout());
        pntxtKhach.setPreferredSize(new Dimension(150, 10));
        pntxtKhach.setBorder(new MatteBorder(0, 0, 7, 7, Color.white));
        txtKhach = new JTextField();
        txtKhach.setFont(sgUI15b);
        txtKhach.setForeground(Color.decode("#32CD32"));
        txtKhach.setPreferredSize(new Dimension(150, 10));
        pntxtKhach.add(txtKhach, BorderLayout.CENTER);
        pnInput.add(pntxtKhach);

        lbThoi = new JLabel("Tiền Thối:");
        lbThoi.setFont(sgUI15b);
        lbThoi.setForeground(Color.black);
        lbThoi.setBorder(new MatteBorder(0, 7, 0, 7, Color.white));
        lbThoi.setPreferredSize(new Dimension(100, 40));
        pnInput.add(lbThoi);

        JPanel pntxtThoi = new JPanel(new BorderLayout());
        pntxtThoi.setPreferredSize(new Dimension(150, 10));
        pntxtThoi.setBorder(new MatteBorder(7, 0, 0, 7, Color.white));
        txtThoi = new JTextField();
        txtThoi.setFont(sgUI15b);
        txtThoi.setForeground(Color.red);
        txtThoi.setPreferredSize(new Dimension(150, 20));
        txtThoi.setFocusable(false);

        pntxtThoi.add(txtThoi, BorderLayout.CENTER);
        pnInput.add(pntxtThoi);

        pnBton.setPreferredSize(new Dimension(300, 275));
        pnBton.setLayout(new BorderLayout());
        pnBton.setBackground(Color.white);

        JPanel pnThanhT_Thoat = new JPanel(new GridLayout(1, 2));
        pnThanhT_Thoat.setPreferredSize(new Dimension(150, 100));
        pnThanhT_Thoat.setBorder(new MatteBorder(2, 0, 0, 0, Color.black));

        btnThanhToan = new JButton("Thanh Toán");
        btnThanhToan.setFont(sgUI13b);
        btnThanhToan.setBackground(Color.decode("#98FB98"));
        btnThanhToan.setBorder(new MatteBorder(30, 30, 30, 30, Color.white));
        btnThanhToan.setPreferredSize(new Dimension(125, 50));
        pnThanhT_Thoat.add(btnThanhToan);
        btnThanhToan.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnThanhToan.setBackground(Color.decode("#48F431"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnThanhToan.setBackground(Color.decode("#98FB98"));
            }
        });

        btnThanhToan.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ev) {
                if (txtKhach.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Chưa Nhập Số Tiền Khách Đưa", "Thông Báo", JOptionPane.PLAIN_MESSAGE);
                } else if (txtKhach.getText().length() > 9) {
                    JOptionPane.showMessageDialog(null, "Số Quá Lớn", "Thông Báo", JOptionPane.PLAIN_MESSAGE);
                } else if (Integer.parseInt(txtKhach.getText()) < ThanhTien(CTT)) {
                    JOptionPane.showMessageDialog(null, "Số Tiền Khách Đưa Không Đủ", "Thông Báo", JOptionPane.PLAIN_MESSAGE);
                } else if (!isNumber(Integer.parseInt(txtKhach.getText()))) {
                    JOptionPane.showMessageDialog(null, "Phải Nhập Đúng Định Dạng Chữ Số", "Thông Báo", JOptionPane.PLAIN_MESSAGE);
                } else {
                    txtThoi.setText("" + TienThoi(Integer.parseInt(txtKhach.getText()), CTT));

                    HoaDonDTO hd = new HoaDonDTO();
                    if (count() < 10) {
                        hd.setMaHD("HD0" + count());
                    } else {
                        hd.setMaHD("HD" + count());
                    }

                    hd.setGiamGia(TienKhuyenMai(CTT));
                    hd.setMaChiTietThue(CTT.getMaChiTietThue());

                    LocalDate now = LocalDate.now();
                    String formattedDate = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    hd.setNgayThanhToan(formattedDate);
                    hd.setTienDichVu(TongTienDichVu(CTT));
                    hd.setTongTien(ThanhTien(CTT));
                    hd.setGiamGia(TienKhuyenMai(CTT));
                    hd.setTienPhong(TongTienPhong(CTT));
                    hd.setMaNV(mainGUI.taiKhoan);

                    try {
                        HoaDonBUS.insertHoaDon(hd);
                    } catch (ParseException | SQLException ex) {
                        Logger.getLogger(FormHoaDon.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    JOptionPane.showMessageDialog(null, "Thanh Toán Thành Công", "Thông Báo", JOptionPane.PLAIN_MESSAGE);
                    ExportHoaDonExcel(CTT, hd.getMaHD());

                    //set lại tình trạng phòng sau khi trả
                    //////////////////////////////8361111111111111111111111111111111111
                    for (ThuePhongDTO ThuePhong : ListTP) {
                        for (PhongDTO p : PhongList) {
                            if (ThuePhong.getMaP().equals(p.getMaP())) {
                                p.setTinhTrang("Chưa dọn phòng");
                                PhongBUS.updatePhong(p);
                            }
                        }
                    }
                    if (ChiTietThueBUS.changeTT(CTT.getMaChiTietThue(), 1)) {
                    }
                    if (ThuePhongBUS.updateTTAll(CTT.getMaChiTietThue())) {
                    }
                    //////////////////////////////8361111111111111111111111111111111111
                    btnThanhToan.setEnabled(false);
                }

            }

        });

        btnThoat = new JButton("Thoát");
        btnThoat.setFont(sgUI13b);
        btnThoat.setBackground(Color.decode("#D3D3D3"));
        btnThoat.setBorder(new MatteBorder(30, 30, 30, 30, Color.white));
        btnThoat.setPreferredSize(new Dimension(75, 50));
        pnThanhT_Thoat.add(btnThoat);

        btnThoat.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnThoat.setBackground(Color.decode("#B9B8B8"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnThoat.setBackground(Color.decode("#D3D3D3"));
            }
        });

        btnThoat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int choice = JOptionPane.showConfirmDialog(null, "Bạn Muốn Thoát", "Thông Báo", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    frBackground.setVisible(false);
                }
            }
        });
        pnBton.add(pnThanhT_Thoat, BorderLayout.NORTH);

        ImageIcon iconTT = new ImageIcon(new ImageIcon(getClass().getResource("/GUI/asset/ImgThanhToan.png")).getImage().getScaledInstance(300, 275, Image.SCALE_SMOOTH));
        JLabel imageLabelTT = new JLabel(iconTT);
        JPanel imagePanelTT = new JPanel();
        imagePanelTT.setBackground(Color.white);
        imagePanelTT.setLayout(new BorderLayout());
        imagePanelTT.add(imageLabelTT, BorderLayout.CENTER);
        pnBton.add(imagePanelTT, BorderLayout.CENTER);

        pnEast.add(pnInput, BorderLayout.CENTER);
        pnEast.add(pnBton, BorderLayout.SOUTH);
        pnContentEast.add(pnTitleEast, BorderLayout.NORTH);
        pnContentEast.add(pnEast, BorderLayout.CENTER);
        frBackground.add(pnContentEast, BorderLayout.EAST);

        frBackground.setVisible(true);

    }

    public int solanthuetruoc(String makhachhang){
        ArrayList<ChiTietThueDTO> cttList = new ArrayList<ChiTietThueDTO>();
        cttList.addAll(ChiTietThueBUS.getListCTT());
        int solanthue=0;
        for(ChiTietThueDTO ctt : cttList){
            if(makhachhang.equals(ctt.getMaKH()) && ctt.getTinhTrangXuLy()==1){
                solanthue++;
            }
        }
        return solanthue;
    }

    public double GiamGia(int n) {
        if (n >= 5 && n < 10) {
            return 0.05;
        }
        if (n >= 10 && n < 15) {
            return 0.1;
        }
        if (n >= 15) {
            return 0.15;
        }
        return 0;
    }

    public int TongTienPhong(ChiTietThueDTO CTT) {
        int tongTienPhong = 0;
        ArrayList<ThuePhongDTO> ListTP = new ArrayList<>();
        for (ThuePhongDTO ThuePhong : ThuePhongList) {
            if (CTT.getMaChiTietThue().equals(ThuePhong.getMaChiTietThue())) {
                ListTP.add(ThuePhong);
            }
        }
        for (int k = 0; k < ListTP.size() - 1; k++) {
            for (int h = k + 1; h < ListTP.size(); h++) {
                if (ListTP.get(k).getMaP().equals(ListTP.get(h).getMaP())) {
                    ListTP.remove(h);
                    h--;
                }
            }
        }
        for (ThuePhongDTO tp : ListTP) {
            if (tp.getMaChiTietThue().equals(CTT.getMaChiTietThue())) {
                tongTienPhong += tp.getGia();
            }
        }
        return tongTienPhong;
    }

    public int TongTienDichVu(ChiTietThueDTO CTT) {
        int tongTienPhongDV = 0;
        ArrayList<SuDungDichVuDTO> ListDV = new ArrayList<>();
        for (SuDungDichVuDTO sddv : SuDungDichVuList) {
            if (CTT.getMaChiTietThue().equals(sddv.getMaChiTietThue())) {
                ListDV.add(sddv);
            }
        }
        for (int k = 0; k < ListDV.size() - 1; k++) {
            for (int h = k + 1; h < ListDV.size(); h++) {
                if (ListDV.get(k).getMaDV().equals(ListDV.get(h).getMaDV()) && ListDV.get(k).getNgaySuDungString().equals(ListDV.get(h).getNgaySuDungString()) && ListDV.get(k).getSoLuong() == ListDV.get(h).getSoLuong()) {
                    ListDV.remove(h);
                    h--;
                }
            }
        }
        for (SuDungDichVuDTO sddv : ListDV) {
            if (sddv.getMaChiTietThue().equals(CTT.getMaChiTietThue())) {
                tongTienPhongDV += sddv.getDonGia();
            }
        }
        return tongTienPhongDV;
    }

    public int TongCong(ChiTietThueDTO CTT) {
        int TongCong = (TongTienDichVu(CTT) + TongTienPhong(CTT) - CTT.getTienDatCoc());
        return TongCong;
    }

    public int TienKhuyenMai(ChiTietThueDTO CTT) {
        double khuyenmai = TongCong(CTT) * GiamGia(solanthuetruoc(CTT.getMaKH()));
        return (int) Math.floor(khuyenmai);
    }

    public int ThanhTien(ChiTietThueDTO CTT) {
        int ThanhTien = TongCong(CTT) - TienKhuyenMai(CTT);
        return ThanhTien;
    }

    public int TienThoi(int khach, ChiTietThueDTO CTT) {
        int Thoi = khach - ThanhTien(CTT);
        return Thoi;
    }

    public void ExportHoaDonExcel(ChiTietThueDTO CTT, String maHD) {
        try {
            XSSFWorkbook workbook = new XSSFWorkbook();

            XSSFSheet sheet = workbook.createSheet("hoadon");

            XSSFRow row = sheet.createRow(0);
            Cell CellNameHotel = row.createCell(0);
            CellNameHotel.setCellValue("Luxury Hotel");

            row = sheet.createRow(1);
            Cell CellDiaChi = row.createCell(0);
            CellDiaChi.setCellValue("Địa chỉ: 273 Đ. An D. Vương, Phường 3, Quận 5, Thành phố Hồ Chí Minh");

            row = sheet.createRow(2);
            Cell Celltitle = row.createCell(2);
            Celltitle.setCellValue("HÓA ĐƠN THANH TOÁN (MÃ: " + maHD + ")");

            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String formattedDateTime = now.format(formatter);
            row = sheet.createRow(3);
            Cell CellDate = row.createCell(0);
            CellDate.setCellValue(formattedDateTime);

            for (KhachHangDTO KH : KhachHangList) {
                if (CTT.getMaKH().equals(KH.getMaKH())) {
                    row = sheet.createRow(4);
                    Cell CellNameKH = row.createCell(0);
                    CellNameKH.setCellValue("Họ tên:");
                    Cell CellNameKHtxt = row.createCell(1);
                    CellNameKHtxt.setCellValue("" + KH.getTenKH());
                }
            }

            for (ThuePhongDTO ThuePhong : ThuePhongList) {
                if (CTT.getMaChiTietThue().equals(ThuePhong.getMaChiTietThue())) {
                    Cell CellDateIn = row.createCell(4);
                    CellDateIn.setCellValue("Ngày đến: ");
                    Cell CellDateIntxt = row.createCell(5);
                    CellDateIntxt.setCellValue("" + ThuePhong.getNgayThue());

                    row = sheet.createRow(5);
                    Cell CellDiaChiKH = row.createCell(0);
                    CellDiaChiKH.setCellValue("Địa chỉ:");
                    Cell CellDiaChiKHtxt = row.createCell(1);
                    CellDiaChiKHtxt.setCellValue("");

                    Cell CellDateOut = row.createCell(4);
                    CellDateOut.setCellValue("Ngày đi:");
                    Cell CellDateOuttxt = row.createCell(5);
                    CellDateOuttxt.setCellValue("" + ThuePhong.getNgayTra());
                }
            }

            row = sheet.createRow(6);
            Cell PhoneNum = row.createCell(0);
            PhoneNum.setCellValue("Điện Thoại:");
            Cell PhoneNumtxt = row.createCell(1);
            for (ChiTietThueDTO ctt : CTTList) {
                if (ctt.getMaChiTietThue().equals(CTT.getMaChiTietThue())) {
                    for (KhachHangDTO kh : KhachHangList) {
                        if (ctt.getMaKH().equals(kh.getMaKH())) {
                            PhoneNumtxt.setCellValue("" + kh.getSdt());
                        }
                    }
                }
            }
            Cell CellSoDem = row.createCell(4);
            CellSoDem.setCellValue("Số đêm:");
            Cell CellSoDemtxt = row.createCell(5);
            CellSoDemtxt.setCellValue("3");

            for (NhanVienDTO NhanVien : NhanVienList) {
                if (mainGUI.taiKhoan.equals(NhanVien.getMaNV())) {
                    Cell CellNameThuNgan = row.createCell(4);
                    CellNameThuNgan.setCellValue("Lễ tân:");
                    Cell CellNameThuNgantxt = row.createCell(5);
                    CellNameThuNgantxt.setCellValue("" + NhanVien.getTenNV());
                }
            }

            row = sheet.createRow(8);
            XSSFCellStyle style1 = workbook.createCellStyle();
            // Thiết lập viền cho ô style
            style1.setBorderBottom(BorderStyle.THIN);
            style1.setBottomBorderColor(IndexedColors.BLACK.getIndex());
            row.setRowStyle(style1);

            row = sheet.createRow(9);
            Cell CellNgay = row.createCell(0);
            CellNgay.setCellValue("NGÀY");
            Cell CellChiTiet = row.createCell(2);
            CellChiTiet.setCellValue("CHI TIẾT");
            Cell CellTien = row.createCell(4);
            CellTien.setCellValue("ĐƠN GIÁ (VNĐ)");
            Cell CellThanhTienDV = row.createCell(6);
            CellThanhTienDV.setCellValue("THÀNH TIỀN (VNĐ)");

            int rows = 10;
            ArrayList<SuDungDichVuDTO> ListDV = new ArrayList<>();
                    for(SuDungDichVuDTO sddv : SuDungDichVuList){
                        if(CTT.getMaChiTietThue().equals(sddv.getMaChiTietThue())){
                           ListDV.add(sddv);
                        }    
                    }
            for (int k = 0; k < ListDV.size() - 1; k++) {
                for (int h = k + 1; h < ListDV.size(); h++) {
                    if(ListDV.get(k).getMaDV().equals(ListDV.get(h).getMaDV()) && ListDV.get(k).getNgaySuDungString().equals(ListDV.get(h).getNgaySuDungString()) && ListDV.get(k).getSoLuong()==ListDV.get(h).getSoLuong()) {
                        ListDV.remove(h);
                        h--;
                    }
                }
            }
            for(SuDungDichVuDTO SuDungDichVu : ListDV){
                row = sheet.createRow(rows++);
                for(DichVuDTO DichVu: DichVuList){ 
                    if(CTT.getMaChiTietThue().equals(SuDungDichVu.getMaChiTietThue()) && SuDungDichVu.getXuLy()!=1 && SuDungDichVu.getMaDV().equals(DichVu.getMaDV())){

                        Cell CellNgaytxt= row.createCell(0);
                        CellNgaytxt.setCellValue(""+SuDungDichVu.getNgaySuDungString());

                        Cell CellChiTiettxt = row.createCell(2);
                        CellChiTiettxt.setCellValue(""+DichVu.getTenLoaiDV()+" x"+SuDungDichVu.getSoLuong());

                        Cell CellTientxt = row.createCell(4);
                        CellTientxt.setCellValue(""+DichVu.getGiaDV());

                        Cell CellThanhTientxt = row.createCell(6);
                        CellThanhTientxt.setCellValue(""+SuDungDichVu.getDonGia());
                    }
                }

            }
            row = sheet.createRow(rows++);
            XSSFCellStyle style2 = workbook.createCellStyle();
            // Thiết lập viền cho ô style
            style2.setBorderBottom(BorderStyle.THIN);
            style2.setBottomBorderColor(IndexedColors.BLACK.getIndex());
            row.setRowStyle(style2);

            row = sheet.createRow(rows++);
            Cell CellMaPhong = row.createCell(0);
            CellMaPhong.setCellValue("MÃ PHÒNG");
            Cell CellNgayThue = row.createCell(2);
            CellNgayThue.setCellValue("NGÀY THUÊ");
            Cell CellNgayTra = row.createCell(4);
            CellNgayTra.setCellValue("NGÀY TRẢ");
            Cell CellDonGia = row.createCell(6);
            CellDonGia.setCellValue("ĐƠN GIÁ (VNĐ)");
            
            ArrayList<ThuePhongDTO> ListTP = new ArrayList<>();
            for(ThuePhongDTO ThuePhong : ThuePhongList){
                if(CTT.getMaChiTietThue().equals(ThuePhong.getMaChiTietThue())){
                   ListTP.add(ThuePhong);
                }    
            }
            for (int k = 0; k < ListTP.size() - 1; k++) {
                for (int h = k + 1; h < ListTP.size(); h++) {
                    if (ListTP.get(k).getMaP().equals(ListTP.get(h).getMaP())) {
                        ListTP.remove(h);
                        h--;
                    }
                }
            }
            for(ThuePhongDTO tp : ListTP){
                row = sheet.createRow(rows++);
                    if(CTT.getMaChiTietThue().equals(tp.getMaChiTietThue()) && tp.getXuLy()!=1){

                        Cell CellMaPtxt= row.createCell(0);
                        CellMaPtxt.setCellValue(""+tp.getMaP());

                        Cell CellNgayThuetxt = row.createCell(2);
                        CellNgayThuetxt.setCellValue(""+tp.getNgayThue());

                        Cell CellNgayTratxt = row.createCell(4);
                        CellNgayTratxt.setCellValue(""+tp.getNgayTra());

                        Cell CellDonGiatxt = row.createCell(6);
                        CellDonGiatxt.setCellValue(""+tp.getGia());
                    }
            }

            row = sheet.createRow(rows++);
            XSSFCellStyle style3 = workbook.createCellStyle();
            // Thiết lập viền cho ô style
            style2.setBorderBottom(BorderStyle.THIN);
            style2.setBottomBorderColor(IndexedColors.BLACK.getIndex());
            row.setRowStyle(style2);

            row = sheet.createRow(rows++);
            Cell CellTienPhong = row.createCell(4);
            CellTienPhong.setCellValue("TỔNG TIỀN PHÒNG");
            Cell CellTienPhongtxt = row.createCell(6);
            CellTienPhongtxt.setCellValue("" + TongTienPhong(CTT) + " VNĐ");

            row = sheet.createRow(rows++);
            Cell CellTienDichVu = row.createCell(4);
            CellTienDichVu.setCellValue("TỔNG TIỀN DỊCH VỤ");
            Cell CellTienDichVutxt = row.createCell(6);
            CellTienDichVutxt.setCellValue(TongTienDichVu(CTT) + " VNĐ");

            row = sheet.createRow(rows++);
            Cell CellTienKM = row.createCell(4);
            CellTienKM.setCellValue("TIỀN KHUYẾN MÃI");
            Cell CellTienKMtxt = row.createCell(6);
            CellTienKMtxt.setCellValue(TienKhuyenMai(CTT) + " VNĐ");

            row = sheet.createRow(rows++);
            Cell CellTienCoc = row.createCell(4);
            CellTienCoc.setCellValue("TIỀN ĐẶT CỌC:");
            Cell CellTienCoctxt = row.createCell(6);
            CellTienCoctxt.setCellValue(CTT.getTienDatCoc() + " VNĐ");

            row = sheet.createRow(rows++);
            Cell CellThanhTien = row.createCell(4);
            CellThanhTien.setCellValue("THÀNH TIỀN:");
            Cell CellThanhTientxt = row.createCell(6);
            CellThanhTientxt.setCellValue(ThanhTien(CTT) + " VNĐ");

            FileOutputStream outputStream = new FileOutputStream("src\\GUI\\excel\\hoadon.xlsx");

            workbook.write(outputStream);

            workbook.close();
        } catch (IOException ex) {
//                    Logger.getLogger(FormHoaDon.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }
/////////////////////////////////    

    public int count() {
        int count = HoaDonBUS.getSize();
        return count + 1;
    }
////////////////////////////// 

    public boolean isNumber(int num) {
        return num > 0 && Integer.toString(num) != null && Integer.toString(num).matches("[-+]?\\d*\\.?\\d+");
    }
}
