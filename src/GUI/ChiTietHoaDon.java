package GUI;

import BUS.ChiTietThueBUS;
import BUS.HoaDonBUS;
import BUS.KhachHangBUS;
import BUS.NhanVienBUS;
import BUS.SuDungDichVuBUS;
import BUS.ThuePhongBUS;
import DTO.ChiTietThueDTO;
import DTO.DichVuDTO;
import DTO.KhachHangDTO;
import DTO.PhongDTO;
import DTO.SuDungDichVuDTO;
import DTO.ThuePhongDTO;
import java.awt.BorderLayout;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.plaf.ColorUIResource;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class ChiTietHoaDon extends JFrame {
    
    JLabel lbTiTle = new JLabel("CHI TIẾT HÓA ĐƠN");
    JPanel pnTop = new JPanel();
    JPanel pnBottom = new JPanel();
    Font sgUI15 = new Font("Segoe UI", Font.BOLD, 15);
    Font sgUI15p = new Font("Segoe UI", Font.PLAIN, 15);
    Font sgUI13 = new Font("Segoe UI", Font.PLAIN, 13);
    Font sgUI13b = new Font("Segoe UI", Font.BOLD, 13);
    Font sgUI18b = new Font("Segoe UI", Font.BOLD, 18);
    JPanel pnBottomTop = new JPanel();
    JPanel pnBottomContent = new JPanel();
    JPanel pnBottomTop1 = new JPanel();
    JPanel pnBottomTop2 = new JPanel();
    
    JPanel pnMaHD = new JPanel();
    JPanel pnMaCTT = new JPanel();
    JPanel pnMaNVThanhToan = new JPanel();
    JPanel pnNguoiThanhToan = new JPanel();
    JPanel pnNgayThanhToan = new JPanel();
    JPanel pnTienPhong = new JPanel();
    JPanel pnTienDV = new JPanel();
    JPanel pnGiamGia = new JPanel();
    JPanel pnTongTien = new JPanel();
    
    JPanel pnMaNV = new JPanel();
    JPanel pnTenNV = new JPanel();
    JPanel pnDatCoc = new JPanel();
    JPanel pnTinhTrangXuLy = new JPanel();
    
    JLabel lbMaHD = new JLabel("Mã hóa đơn:");
    JLabel lbMaCTT = new JLabel("Mã chi tiết thuê:");
    JLabel lbMaNVThanhToan = new JLabel("Mã NV thanh toán");
    JLabel lbNguoiThanhToan = new JLabel("NV thanh toán:");
    JLabel lbNgayThanhToan = new JLabel("Ngày thanh toán:");
    JLabel lbTienPhong = new JLabel("Tiền phòng:");
    JLabel lbTienDV = new JLabel("Tiền dịch vụ:");
    JLabel lbGiamGia = new JLabel("Giảm giá:");
    JLabel lbTongTien = new JLabel("Tổng cộng:");
    JLabel lbMaNV = new JLabel("Mã NV cho thuê:");
    JLabel lbTenNV = new JLabel("Tên NV cho thuê:");
    JLabel lbTienCoc = new JLabel("Tiền cọc:");
    JLabel lbTinhTrangXuLy = new JLabel("Tình trạng xử lý:");
    
    JTextField txtMaHD = new JTextField();
    JTextField txtMaCTT = new JTextField();
    JTextField txtMaNVThanhToan = new JTextField();
    JTextField txtNguoiThanhToan = new JTextField();
    JTextField txtNgayThanhToan = new JTextField();
    JTextField txtTienPhong = new JTextField();
    JTextField txtTienDV = new JTextField();
    JTextField txtGiamGia = new JTextField();
    JTextField txtTongTien = new JTextField();
    JTextField txtMaNV = new JTextField();
    JTextField txtTenNV = new JTextField();
    JTextField txtTienCoc = new JTextField();
    JTextField txtTinhTrangXuLy = new JTextField();
    
    JPanel pnLeft = new JPanel();
    JLabel lbLeft = new JLabel("Thông tin khách hàng");
    JLabel lbLeftTien = new JLabel("Thông tin thanh toán");
    JPanel pnLeftTop = new JPanel();
    JPanel pnLeftCenter = new JPanel();
    JPanel pnLeftKH = new JPanel();
    JPanel pnLeftTien = new JPanel();
    
    JPanel pnMaKH = new JPanel();
    JPanel pnTenKH = new JPanel();
    JPanel pnSDT = new JPanel();
    JPanel pnCMND = new JPanel();
    JPanel pnGioiTinh = new JPanel();
    
    JLabel lbMaKH = new JLabel("Mã khách hàng:");
    JLabel lbTenKH = new JLabel("Tên khách hàng:");
    JLabel lbSDT = new JLabel("Số điện thoại:");
    JLabel lbCMND = new JLabel("Chứng minh nhân dân:");
    JLabel lbGioiTinh = new JLabel("Giới tính");
    
    JTextField txtMaKH = new JTextField();
    JTextField txtTenKH = new JTextField();
    JTextField txtSDT = new JTextField();
    JTextField txtCMND = new JTextField();
    JTextField txtGioiTinh = new JTextField();
    JPanel pnContent1 = new JPanel();
    JPanel pnContent2 = new JPanel();
    JPanel pnContentCenter = new JPanel();
    
    JScrollPane scpP = new JScrollPane();
    JLabel lbPhong = new JLabel("Danh sách phòng");
    JTable tbP = new JTable() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    
    JScrollPane scpDV = new JScrollPane();
    JTable tbDV = new JTable() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    JLabel lbDV = new JLabel("Danh sách dịch vụ");
    ArrayList<PhongDTO> listPhong = new ArrayList<>();
    ArrayList<ThuePhongDTO> listThuePhongDTO = new ArrayList<>();
    
    ArrayList<DichVuDTO> listDV = new ArrayList<>();
    ArrayList<SuDungDichVuDTO> listSuDungDV = new ArrayList<>();
    
    public ChiTietHoaDon(String maHD, String maCTT, String ngayThanhToan, String tenNV, String tienPhong, String tienDV, String giamGia, String tong) {
        listThuePhongDTO = ThuePhongBUS.getListTP_P(maCTT, listPhong);
        listSuDungDV = SuDungDichVuBUS.getListSDDV_maDV(maCTT, listDV);
        initComponents(maHD, maCTT, ngayThanhToan, tenNV, tienPhong, tienDV, giamGia, tong);
    }
    
    public void initComponents(String maHD, String maCTT, String ngayThanhToan, String tenNV, String tienPhong, String tienDV, String giamGia, String tong) {
        UIManager.put("TextField.inactiveBackground", new ColorUIResource(new Color(255, 255, 255)));
        txtCMND.setEditable(false);
        txtGiamGia.setEditable(false);
        txtGioiTinh.setEditable(false);
        txtMaCTT.setEditable(false);
        txtMaHD.setEditable(false);
        txtMaKH.setEditable(false);
        txtMaNV.setEditable(false);
        txtMaNVThanhToan.setEditable(false);
        txtNgayThanhToan.setEditable(false);
        txtNguoiThanhToan.setEditable(false);
        txtSDT.setEditable(false);
        txtTenKH.setEditable(false);
        txtTenNV.setEditable(false);
        txtTienCoc.setEditable(false);
        txtTienDV.setEditable(false);
        txtTienPhong.setEditable(false);
        txtTinhTrangXuLy.setEditable(false);
        txtTongTien.setEditable(false);
        
        txtCMND.setFont(sgUI15p);
        txtGiamGia.setFont(sgUI15p);
        txtGioiTinh.setFont(sgUI15p);
        txtMaCTT.setFont(sgUI15p);
        txtMaHD.setFont(sgUI15p);
        txtMaKH.setFont(sgUI15p);
        txtMaNV.setFont(sgUI15p);
        txtMaNVThanhToan.setFont(sgUI15p);
        txtNgayThanhToan.setFont(sgUI15p);
        txtNguoiThanhToan.setFont(sgUI15p);
        txtSDT.setFont(sgUI15p);
        txtTenKH.setFont(sgUI15p);
        txtTenNV.setFont(sgUI15p);
        txtTienCoc.setFont(sgUI15p);
        txtTienDV.setFont(sgUI15p);
        txtTienPhong.setFont(sgUI15p);
        txtTinhTrangXuLy.setFont(sgUI15p);
        txtTongTien.setFont(sgUI15p);
        
        String maKH = "";
        txtMaNVThanhToan.setText(HoaDonBUS.getMaNV(maHD));
        ChiTietThueDTO ctt = ChiTietThueBUS.LoadCTT(maCTT);
        if (ctt != null) {
            txtMaNV.setText(ctt.getMaNV());
            txtTenNV.setText(NhanVienBUS.getTenNV(ctt.getMaNV()));
            if (ctt.getTinhTrangXuLy() == 0) {
                txtTinhTrangXuLy.setText("Chưa xử lý");
            } else {
                txtTinhTrangXuLy.setText("Đã xử lý");
            }
            txtTienCoc.setText(ctt.getTienDatCoc() + "");
            maKH = ctt.getMaKH();
        }
        KhachHangDTO khDTO = KhachHangBUS.getKH_MaKH(maKH);
        if (khDTO != null) {
            txtMaKH.setText(khDTO.getMaKH());
            txtTenKH.setText(khDTO.getTenKH());
            txtCMND.setText(khDTO.getCmnd());
            txtSDT.setText(khDTO.getSdt());
            txtGioiTinh.setText(khDTO.getGioiTinh());
        }
        setSize(1300, 700);
        setLayout(new BorderLayout());
        setVisible(true);
        setLocationRelativeTo(null);
        add(pnTop, BorderLayout.NORTH);
        add(pnBottom, BorderLayout.CENTER);
        lbTiTle.setFont(sgUI18b);
        lbTiTle.setHorizontalAlignment(JLabel.CENTER);
        pnTop.setLayout(new BorderLayout());
        pnTop.add(lbTiTle, BorderLayout.CENTER);
        pnBottom.setLayout(new BorderLayout(5, 5));
        pnBottom.setBackground(Color.white);
        pnBottom.add(pnBottomTop, BorderLayout.NORTH);
        pnBottomTop.setBorder(new EmptyBorder(5, 5, 0, 5));
        pnBottomTop.setBackground(Color.white);
        pnBottomContent.setBackground(Color.white);
        pnBottomContent.setBorder(new EmptyBorder(0, 5, 5, 5));
        pnBottom.add(pnBottomContent, BorderLayout.CENTER);
        pnBottomTop.setLayout(new GridLayout(2, 1, 5, 5));
        pnBottomTop.add(pnBottomTop1);
        pnBottomTop.add(pnBottomTop2);
        pnBottomTop.setBackground(Color.white);
        pnTop.setBackground(Color.white);
        
        pnBottomTop1.setLayout(new GridLayout(1, 5));
        pnBottomTop2.setLayout(new GridLayout(1, 4));
        pnBottomTop1.add(pnMaHD);
        pnMaHD.setLayout(new BorderLayout());
        pnMaHD.add(lbMaHD, BorderLayout.WEST);
        pnMaHD.add(txtMaHD, BorderLayout.CENTER);
        txtMaHD.setText(maHD);
        
        pnBottomTop1.add(pnMaCTT);
        pnMaCTT.setLayout(new BorderLayout());
        pnMaCTT.add(lbMaCTT, BorderLayout.WEST);
        pnMaCTT.add(txtMaCTT, BorderLayout.CENTER);
        txtMaCTT.setText(maCTT);
        
        pnBottomTop1.add(pnMaNVThanhToan);
        pnMaNVThanhToan.setLayout(new BorderLayout());
        pnMaNVThanhToan.add(lbMaNVThanhToan, BorderLayout.WEST);
        pnMaNVThanhToan.add(txtMaNVThanhToan, BorderLayout.CENTER);
        
        pnBottomTop1.add(pnNguoiThanhToan);
        pnNguoiThanhToan.setLayout(new BorderLayout());
        pnNguoiThanhToan.add(lbNguoiThanhToan, BorderLayout.WEST);
        pnNguoiThanhToan.add(txtNguoiThanhToan, BorderLayout.CENTER);
        txtNguoiThanhToan.setText(tenNV);
        
        pnBottomTop1.add(pnNgayThanhToan);
        pnNgayThanhToan.setLayout(new BorderLayout());
        pnNgayThanhToan.add(lbNgayThanhToan, BorderLayout.WEST);
        pnNgayThanhToan.add(txtNgayThanhToan, BorderLayout.CENTER);
        txtNgayThanhToan.setText(ngayThanhToan);
        
        pnBottomTop2.add(pnMaNV);
        pnMaNV.setLayout(new BorderLayout());
        pnMaNV.add(lbMaNV, BorderLayout.WEST);
        pnMaNV.add(txtMaNV, BorderLayout.CENTER);
        
        txtTienPhong.setText(tienPhong);
        txtTienDV.setText(tienDV);
        txtGiamGia.setText(giamGia);
        txtTongTien.setText(tong);
        
        pnBottomTop2.add(pnTenNV);
        pnTenNV.setLayout(new BorderLayout());
        pnTenNV.add(lbTenNV, BorderLayout.WEST);
        pnTenNV.add(txtTenNV, BorderLayout.CENTER);
        
        pnBottomTop2.add(pnDatCoc);
        pnDatCoc.setLayout(new BorderLayout());
        pnDatCoc.add(lbTienCoc, BorderLayout.WEST);
        pnDatCoc.add(txtTienCoc, BorderLayout.CENTER);
        
        pnBottomTop2.add(pnTinhTrangXuLy);
        pnTinhTrangXuLy.setLayout(new BorderLayout());
        pnTinhTrangXuLy.add(lbTinhTrangXuLy, BorderLayout.WEST);
        pnTinhTrangXuLy.add(txtTinhTrangXuLy, BorderLayout.CENTER);
        
        lbMaHD.setFont(sgUI15);
        lbMaCTT.setFont(sgUI15);
        lbMaNVThanhToan.setFont(sgUI15);
        lbNgayThanhToan.setFont(sgUI15);
        lbMaNV.setFont(sgUI15);
        lbTenNV.setFont(sgUI15);
        lbTienCoc.setFont(sgUI15);
        lbTinhTrangXuLy.setFont(sgUI15);
        lbNguoiThanhToan.setFont(sgUI15);
        
        pnLeft.setLayout(new GridLayout(2, 1));
        pnLeft.add(pnLeftTop);
        pnLeftTop.setLayout(new BorderLayout());
        pnLeftTop.add(lbLeft, BorderLayout.NORTH);
        pnLeftTop.add(pnLeftKH, BorderLayout.CENTER);
        pnLeftKH.setLayout(new GridLayout(6, 1));
        
        pnLeftKH.add(pnMaKH);
        pnMaKH.setLayout(new BorderLayout());
        pnMaKH.add(lbMaKH, BorderLayout.NORTH);
        pnMaKH.add(txtMaKH, BorderLayout.CENTER);
        
        pnLeftKH.add(pnTenKH);
        pnTenKH.setLayout(new BorderLayout());
        pnTenKH.add(lbTenKH, BorderLayout.NORTH);
        pnTenKH.add(txtTenKH, BorderLayout.CENTER);
        
        pnLeftKH.add(pnSDT);
        pnSDT.setLayout(new BorderLayout());
        pnSDT.add(lbSDT, BorderLayout.NORTH);
        pnSDT.add(txtSDT, BorderLayout.CENTER);
        
        pnLeftKH.add(pnCMND);
        pnCMND.setLayout(new BorderLayout());
        pnCMND.add(lbCMND, BorderLayout.NORTH);
        pnCMND.add(txtCMND, BorderLayout.CENTER);
        
        pnLeftKH.add(pnGioiTinh);
        pnGioiTinh.setLayout(new BorderLayout());
        pnGioiTinh.add(lbGioiTinh, BorderLayout.NORTH);
        pnGioiTinh.add(txtGioiTinh, BorderLayout.CENTER);
        
        pnLeftCenter.setLayout(new BorderLayout());
        pnLeftCenter.add(lbLeftTien, BorderLayout.NORTH);
        pnLeftCenter.add(pnLeftTien, BorderLayout.CENTER);
        pnLeft.add(pnLeftCenter);
        
        pnLeftTien.setLayout(new GridLayout(6, 1));
        pnLeftTien.add(pnTienPhong);
        pnTienPhong.setLayout(new BorderLayout());
        pnTienPhong.add(lbTienPhong, BorderLayout.NORTH);
        pnTienPhong.add(txtTienPhong, BorderLayout.CENTER);
        
        pnLeftTien.add(pnTienDV);
        pnTienDV.setLayout(new BorderLayout());
        pnTienDV.add(lbTienDV, BorderLayout.NORTH);
        pnTienDV.add(txtTienDV, BorderLayout.CENTER);
        
        pnLeftTien.add(pnGiamGia);
        pnGiamGia.setLayout(new BorderLayout());
        pnGiamGia.add(lbGiamGia, BorderLayout.NORTH);
        pnGiamGia.add(txtGiamGia, BorderLayout.CENTER);
        
        pnLeftTien.add(pnTongTien);
        pnTongTien.setLayout(new BorderLayout());
        pnTongTien.add(lbTongTien, BorderLayout.NORTH);
        pnTongTien.add(txtTongTien, BorderLayout.CENTER);
        
        pnBottomContent.setLayout(new BorderLayout(5, 5));
        pnBottomContent.add(pnLeft, BorderLayout.WEST);
        
        pnBottomContent.add(pnContentCenter, BorderLayout.CENTER);
        pnContentCenter.setLayout(new GridLayout(2, 1));
        
        pnContentCenter.add(pnContent1);
        pnContentCenter.add(pnContent2);
        pnContent1.setLayout(new BorderLayout());
        pnContent1.add(lbPhong, BorderLayout.NORTH);
        pnContent1.add(scpP, BorderLayout.CENTER);
        scpP.setViewportView(tbP);
        lbPhong.setFont(sgUI15);
        lbPhong.setBackground(Color.decode("#F0FFF0"));
        lbPhong.setOpaque(true);
        lbPhong.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(1, 1, 1, 1, Color.decode("#98FB98")), new EmptyBorder(3, 5, 3, 5)));
        
        lbDV.setFont(sgUI15);
        lbDV.setBackground(Color.decode("#F0FFF0"));
        lbDV.setOpaque(true);
        lbDV.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(1, 1, 1, 1, Color.decode("#98FB98")), new EmptyBorder(3, 5, 3, 5)));
        
        pnContent2.setLayout(new BorderLayout());
        pnContent2.add(lbDV, BorderLayout.NORTH);
        pnContent2.add(scpDV, BorderLayout.CENTER);
        scpDV.setViewportView(tbDV);
        lbLeft.setFont(sgUI15);
        lbLeft.setBackground(Color.decode("#F0FFF0"));
        lbLeft.setOpaque(true);
        lbLeft.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(1, 1, 1, 1, Color.decode("#98FB98")), new EmptyBorder(3, 5, 3, 5)));
        lbLeftTien.setFont(sgUI15);
        lbLeftTien.setBackground(Color.decode("#F0FFF0"));
        lbLeftTien.setOpaque(true);
        lbLeftTien.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(1, 1, 1, 1, Color.decode("#98FB98")), new EmptyBorder(3, 5, 3, 5)));
        
        lbMaKH.setFont(sgUI15);
        lbTenKH.setFont(sgUI15);
        lbSDT.setFont(sgUI15);
        lbCMND.setFont(sgUI15);
        lbGioiTinh.setFont(sgUI15);
        pnMaNV.setBackground(Color.white);
        pnTenNV.setBackground(Color.white);
        
        lbTienPhong.setFont(sgUI15);
        lbTienDV.setFont(sgUI15);
        lbGiamGia.setFont(sgUI15);
        lbTongTien.setFont(sgUI15);
        renderTBphong(tbP);
        renderTBDichVu(tbDV);
        pnBottomTop1.setBackground(Color.white);
        pnBottomTop2.setBackground(Color.white);
        pnMaCTT.setBackground(Color.white);
        pnMaHD.setBackground(Color.white);
        pnNgayThanhToan.setBackground(Color.white);
        pnNguoiThanhToan.setBackground(Color.white);
        pnDatCoc.setBackground(Color.white);
        pnMaNVThanhToan.setBackground(Color.white);
        pnTinhTrangXuLy.setBackground(Color.white);
    }
    
    public void renderTBphong(JTable tb) {
        DefaultTableModel dtm = new DefaultTableModel();
        dtm.addColumn("STT");
        dtm.addColumn("Mã phòng");
        dtm.addColumn("Tên phòng");
        dtm.addColumn("Tình trạng");
        dtm.addColumn("Loại hình thuê");
        dtm.addColumn("Ngày thuê");
        dtm.addColumn("Ngày trả");
        dtm.addColumn("Ngày CheckOut");
        dtm.addColumn("Giá phòng");
        dtm.addColumn("Giá thực");
        int i = 0;
        for (ThuePhongDTO x : listThuePhongDTO) {
            String tinhTrang = "";
            if (x.getTinhTrang() == 0) {
                tinhTrang = "Đang xử lý";
            } else if (x.getTinhTrang() == 1) {
                tinhTrang = "Đang được thuê";
            } else {
                tinhTrang = "Đã trả phòng";
            }
            Object row[] = {i + 1, x.getMaP(), listPhong.get(i).getTenP(), tinhTrang, x.getLoaiHinhThue(), x.getNgayThue(), x.getNgayTra(), x.getNgayCheckOut(), listPhong.get(i).getGiaP(), x.getGia()};
            dtm.addRow(row);
            i++;
        }
        tb.setModel(dtm);
        tb.setShowGrid(false);
        tb.setIntercellSpacing(new Dimension(0, 0));
        tb.setRowHeight(30);
        tb.getColumnModel().getColumn(0).setPreferredWidth(5);
        tb.getColumnModel().getColumn(1).setPreferredWidth(50);
        tb.getTableHeader().setPreferredSize(new Dimension(1, 30));
        tb.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        tb.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        DefaultTableCellRenderer renderBorder = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                JLabel lb = (JLabel) c;
                lb.setHorizontalAlignment(JLabel.CENTER);
                if (isSelected) {
                    lb.setBackground(Color.decode("#F5F5F5"));
                } else {
                    lb.setBackground(Color.decode("#FFFFFF"));
                }
                if (column == tb.getColumnModel().getColumnIndex("STT")) {
                    lb.setBorder(new MatteBorder(0, 0, 1, 1, Color.decode("#99FF99")));
                    lb.setBackground(Color.decode("#99FF99"));
                } else {
                    lb.setBorder(new MatteBorder(0, 0, 1, 0, Color.decode("#DDDDDD")));
                }
                
                if (column == tb.getColumnModel().getColumnIndex("Tình trạng")) {
                    if (lb.getText().equals("Đang được thuê")) {
                        lb.setForeground(Color.red);
                    } else if (lb.getText().equals("Đã trả phòng")) {
                        lb.setForeground(Color.decode("#FFD700"));
                    } else {
                        lb.setForeground(Color.decode("#228B22"));
                    }
                } else {
                    lb.setForeground(Color.black);
                }
                
                return lb;
            }
        };
        for (int j = 0; j < tb.getColumnCount(); j++) {
            tb.getColumnModel().getColumn(j).setCellRenderer(renderBorder);
        }
    }
    
    public void renderTBDichVu(JTable tbDV) {
        DefaultTableModel dtm = new DefaultTableModel();
        dtm.addColumn("STT");
        dtm.addColumn("Mã dịch vụ");
        dtm.addColumn("Tên dịch vụ");
        dtm.addColumn("Ngày sử dụng");
        dtm.addColumn("Số lượng");
        dtm.addColumn("Đơn giá");
        dtm.addColumn("Giá dịch vụ");
        int i = 0;
        for (SuDungDichVuDTO x : listSuDungDV) {
            Object row[] = {i + 1, x.getMaDV(), listDV.get(i).getTenDV(), x.getNgaySuDungString(), x.getSoLuong(), listDV.get(i).getGiaDV(), x.getDonGia()};
            dtm.addRow(row);
            i++;
        }
        tbDV.setModel(dtm);
        tbDV.setShowGrid(false);
        tbDV.setIntercellSpacing(new Dimension(0, 0));
        tbDV.setRowHeight(30);
        tbDV.getColumnModel().getColumn(0).setPreferredWidth(5);
        tbDV.getColumnModel().getColumn(1).setPreferredWidth(50);
        tbDV.getTableHeader().setPreferredSize(new Dimension(1, 32));
        tbDV.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        tbDV.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        DefaultTableCellRenderer renderBorder = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                JLabel lb = (JLabel) c;
                lb.setHorizontalAlignment(JLabel.CENTER);
                if (isSelected) {
                    lb.setBackground(Color.decode("#F5F5F5"));
                } else {
                    lb.setBackground(Color.decode("#FFFFFF"));
                }
                if (column == tbDV.getColumnModel().getColumnIndex("STT")) {
                    lb.setBorder(new MatteBorder(0, 0, 1, 1, Color.decode("#99FF99")));
                    lb.setBackground(Color.decode("#99FF99"));
                } else {
                    lb.setBorder(new MatteBorder(0, 0, 1, 0, Color.decode("#DDDDDD")));
                }
                return lb;
            }
        };
        for (int j = 0; j < tbDV.getColumnCount(); j++) {
            tbDV.getColumnModel().getColumn(j).setCellRenderer(renderBorder);
        }
    }
}
