package GUI;

import BUS.ChiTietThueBUS;
import BUS.DichVuBUS;
import BUS.KhachHangBUS;
import BUS.PhongBUS;
import BUS.SuDungDichVuBUS;
import BUS.ThuePhongBUS;
import DTO.ChiTietThueDTO;
import DTO.DichVuDTO;
import DTO.KhachHangDTO;
import DTO.PhongDTO;
import DTO.SuDungDichVuDTO;
import DTO.ThuePhongDTO;
import GUI.Phong.PanelSelectDichVu;
import GUI.Phong.PanelSelectPhong;
import com.toedter.calendar.JDateChooser;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.Timer;
import javax.swing.WindowConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class FrameOrderRoom extends JFrame {

    static String maCTT = "CTT1";
    Font sgUI18 = new Font("Segoe UI", Font.BOLD, 15);
    public static String MaPhong = "";
    JButton btnPay = new JButton("Thanh toán");
    public static JTextField txtSoPhong = new JTextField("", 10);
    public static int giaTmp;
    String maDVtmp = "";
    int SLtmp = 0;
    JDateChooser dcsNgaytra = new JDateChooser();
    JDateChooser dcsNgaythue = new JDateChooser();
    Time timeThue = new Time();
    Time timeTra = new Time();
    public static JDateChooser dcsDayDV = new JDateChooser();
    public static DichVuDTO dvdto = new DichVuDTO();
    public static int SL = 0;
    public static String ngayDV = "";
    public static JButton btnChonDV = new JButton("Thêm dịch vụ");
    JPanel pnSuaSL = new JPanel();
    JPopupMenu popupMenuP = new JPopupMenu();
    data datatmp = new data();
    JButton btnThem = new JButton("Thêm");
    JButton btnLuuDoiP = new JButton("Lưu thông tin");

    public static JButton btnTmp = new JButton("tạm");
    JMenuItem ItemTra = new JMenuItem("Trả phòng");
    JMenuItem ItemXoaP = new JMenuItem("Xóa phòng");
    JMenuItem ItemSuaP = new JMenuItem("Chỉnh sửa");
    JMenuItem ItemDoiP = new JMenuItem("Đổi phòng");
    JMenuItem ItemLayP = new JMenuItem("Lấy phòng");
    JRadioButton btnThueNgay = new JRadioButton("Thuê theo ngày");
    JRadioButton btnThueGio = new JRadioButton("Thuê theo giờ");
    JPopupMenu popupMenuDV = new JPopupMenu();
    JMenuItem ItemXoaDV = new JMenuItem("Xóa dịch vụ");
    JMenuItem ItemSuaDV = new JMenuItem("Chỉnh sửa");
    JTextField txtSLDV = new JTextField("0", 10);
    JLabel lblSLDV = new JLabel("Số lượng");
    JButton btnLuuSuaDV = new JButton("Lưu");
    //Các arraylist..........................
    ArrayList<SuDungDichVuDTO> listSDDVnew = new ArrayList<>();
    public static ArrayList<PhongDTO> listPFull = PhongBUS.getListPhong();
    public static ArrayList<DichVuDTO> listDVFull = DichVuBUS.getListDichVu();
    private ArrayList<SuDungDichVuDTO> listSDDVFull = SuDungDichVuBUS.LoadData();
    private ArrayList<ThuePhongDTO> listTPFull = ThuePhongBUS.LoadData();
    public static ArrayList<SuDungDichVuDTO> listSDDV = new ArrayList<>();

    private ChiTietThueDTO cttDTO = ChiTietThueBUS.LoadCTT(maCTT);
    private KhachHangDTO khDTO = new KhachHangDTO();
    private ArrayList<PhongDTO> listP = new ArrayList<>();
    private ArrayList<ThuePhongDTO> listTP = new ArrayList<>();
    private ArrayList<PhongDTO> listPnDV = new ArrayList<>();
    private ArrayList<data> listData = new ArrayList<>();
    //.........................................

    public static JTable tblP = new JTable() {
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    public static JTable tblDV = new JTable() {
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    public FrameOrderRoom() throws IOException, SQLException, ParseException {
        loadKH();
        //System.out.println(khDTO.getMaKH());
        loadListThuePhong();
        //loadListPhong();
        //loadData();
        loadListSDDV();
        setSize(1200, 800);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.decode("#EEEEEE"));
        add(new InforPane(), BorderLayout.CENTER);
        ImageIcon iconAdd = new ImageIcon(new ImageIcon(getClass().getResource("/GUI/asset/sua.png")).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        ItemSuaP.setIcon(iconAdd);
        ItemSuaDV.setIcon(iconAdd);
        iconAdd = new ImageIcon(new ImageIcon(getClass().getResource("/GUI/asset/xoa.png")).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        ItemXoaDV.setIcon(iconAdd);
        ItemXoaP.setIcon(iconAdd);
        iconAdd = new ImageIcon(new ImageIcon(getClass().getResource("/GUI/asset/BackMini.png")).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        ItemTra.setIcon(iconAdd);
        iconAdd = new ImageIcon(new ImageIcon(getClass().getResource("/GUI/asset/Refresh-icon.png")).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        ItemDoiP.setIcon(iconAdd);
        iconAdd = new ImageIcon(new ImageIcon(getClass().getResource("/GUI/asset/tick.png")).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        ItemLayP.setIcon(iconAdd);
        popupMenuP.add(ItemSuaP);
        popupMenuP.add(ItemTra);
        popupMenuP.add(ItemXoaP);
        popupMenuP.add(ItemDoiP);
        popupMenuP.add(ItemLayP);
        popupMenuDV.add(ItemSuaDV);
        popupMenuDV.add(ItemXoaDV);

        //Chỉnh các lệnh cho menu popup phòng
        ItemTra.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LocalDateTime currentDateTime = LocalDateTime.now();

                // Định dạng ngày giờ thành chuỗi ở định dạng "dd-MM-yyyy hh:mm:ss"
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                String formattedDateTime = currentDateTime.format(formatter);
                int lick = JOptionPane.showConfirmDialog(null, "Xác nhận trả phòng " + datatmp.maP + " thuê ngày " + datatmp.ngayThue + "?", "Thông Báo", 2);
                if (lick == JOptionPane.OK_OPTION) {
                    try {

                        // Xử lý sự kiện khi người dùng chọn "Edit" trên popup menu
                        ThuePhongBUS.UpdateTinhTrang(maCTT, datatmp.maP, datatmp.ngayThue, formattedDateTime, 2);
                        PhongBUS.updateTT(datatmp.maP, "Chưa được dọn");
                    } catch (SQLException | ParseException ex) {
                        Logger.getLogger(FrameOrderRoom.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    for (ThuePhongDTO tpdto : listTP) {
                        if (tpdto.getMaP().equals(datatmp.maP) && tpdto.getNgayThue().equals(datatmp.ngayThue)) {
                            tpdto.setTinhTrang(2);
                            tpdto.setNgayCheckOut(formattedDateTime);
                        }
                    }
                    loadTB();
                } else {
                    if (lick == JOptionPane.CANCEL_OPTION) {
                        //setVisible(false);
                    }
                }

            }
        });

        ItemXoaP.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int lick = JOptionPane.showConfirmDialog(null, "Xác nhận xóa phòng " + datatmp.maP + " thuê ngày " + datatmp.ngayThue + "?", "Thông Báo", 2);
                if (lick == JOptionPane.OK_OPTION) {
                    try {
                        // Xử lý sự kiện khi người dùng chọn "Edit" trên popup menu
                        ThuePhongBUS.DelThuePhong(maCTT, datatmp.maP, datatmp.ngayThue);
                    } catch (SQLException | ParseException ex) {
                        Logger.getLogger(FrameOrderRoom.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Iterator<ThuePhongDTO> iterator = listTP.iterator();
                    while (iterator.hasNext()) {
                        ThuePhongDTO element = iterator.next();
                        if (element.getMaP().equals(datatmp.maP) && element.getNgayThue().equals(datatmp.ngayThue)) {
                            iterator.remove(); // Xóa phần tử thỏa mãn điều kiện
                        }
                    }

                    loadTB();
                } else {
                    if (lick == JOptionPane.CANCEL_OPTION) {
                        //setVisible(false);
                    }
                }

            }
        });

        ItemSuaP.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int lick = JOptionPane.showConfirmDialog(null, "Xác nhận chỉnh sửa thông tin thuê phòng? \n Phòng: " + datatmp.maP + " \nThuê ngày: " + datatmp.ngayThue, "Thông Báo", 2);
                if (lick == JOptionPane.OK_OPTION) {
                    btnLuuSuaDV.setVisible(true);
                    txtSoPhong.setText(datatmp.maP);
                    btnThem.setEnabled(false);
                    btnLuuDoiP.setEnabled(true);
                    for (ThuePhongDTO tpdto : listTP) {
                        if (tpdto.getMaP().equals(datatmp.maP) && tpdto.getNgayThue().equals(datatmp.ngayThue)) {
                            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                            try {
                                Date date = format.parse(tpdto.getNgayThue());
                                dcsNgaythue.setDate(date);
                            } catch (ParseException ex) {
                                Logger.getLogger(FrameOrderRoom.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            try {
                                Date date = format.parse(tpdto.getNgayTra());
                                dcsNgaytra.setDate(date);
                            } catch (ParseException ex) {
                                Logger.getLogger(FrameOrderRoom.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            if (tpdto.getLoaiHinhThue().equals("Theo ngày")) {
                                btnThueNgay.setSelected(true);
                            } else {
                                btnThueGio.setSelected(true);
                            }

                            format = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
                            Date date;
                            try {
                                date = format.parse(tpdto.getNgayThue());
                                Calendar calendar = Calendar.getInstance();
                                calendar.setTime(date);

                                int hour = calendar.get(Calendar.HOUR);
                                int minute = calendar.get(Calendar.MINUTE);
                                timeThue.gioSpinner.setValue(hour);
                                timeThue.phutSpinner.setValue(minute);
                            } catch (ParseException ex) {
                                Logger.getLogger(FrameOrderRoom.class.getName()).log(Level.SEVERE, null, ex);
                            }

                            try {
                                date = format.parse(tpdto.getNgayTra());
                                Calendar calendar = Calendar.getInstance();
                                calendar.setTime(date);

                                int hour = calendar.get(Calendar.HOUR);
                                int minute = calendar.get(Calendar.MINUTE);
                                timeTra.gioSpinner.setValue(hour);
                                timeTra.phutSpinner.setValue(minute);
                            } catch (ParseException ex) {
                                Logger.getLogger(FrameOrderRoom.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        }
                    }

                    //loadTB();
                } else {
                    if (lick == JOptionPane.CANCEL_OPTION) {
                        //setVisible(false);
                    }
                }

            }
        });

        ItemDoiP.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int lick = JOptionPane.showConfirmDialog(null, "Xác nhận đổi phòng " + datatmp.maP + " thuê ngày " + datatmp.ngayThue + " sang phòng khác?", "Thông Báo", 2);
                if (lick == JOptionPane.OK_OPTION) {
                    for (ThuePhongDTO tpdto : listTP) {
                        if (tpdto.getMaP().equals(datatmp.maP) && tpdto.getNgayThue().equals(datatmp.ngayThue)) {
                            new PanelSelectPhong("k", dateChange(tpdto.getNgayThue()), dateChange(tpdto.getNgayTra()));
                        }
                    }
                } else {
                    if (lick == JOptionPane.CANCEL_OPTION) {
                        //setVisible(false);
                    }
                }

            }
        });

        ItemLayP.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String ngaytra = "";
                for (ThuePhongDTO tpdto : listTP) {
                    if (tpdto.getNgayThue().equals(datatmp.ngayThue) && tpdto.getMaP().equals(datatmp.maP)) {
                        ngaytra = tpdto.getNgayTra();
                        break;
                    }
                }

                int lick = JOptionPane.showConfirmDialog(null, "Xác nhận lấy phòng: " + datatmp.maP + "\nThuê ngày: " + datatmp.ngayThue + "\nTrả ngày: " + ngaytra, "Thông Báo", 2);
                if (lick == JOptionPane.OK_OPTION) {
                    for (ThuePhongDTO tpdto : listTP) {
                        if (tpdto.getMaP().equals(datatmp.maP) && tpdto.getNgayThue().equals(datatmp.ngayThue)) {
                            tpdto.setTinhTrang(1);
                            break;
                        }
                    }
                    loadTB();
                    PhongBUS.updateTT(datatmp.maP, "Đang được thuê");
                    try {
                        ThuePhongBUS.UpdateTinhTrang(maCTT, datatmp.maP, datatmp.ngayThue, ngaytra, 1);
                    } catch (SQLException ex) {
                        Logger.getLogger(FrameOrderRoom.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ParseException ex) {
                        Logger.getLogger(FrameOrderRoom.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    if (lick == JOptionPane.CANCEL_OPTION) {
                        //setVisible(false);
                    }
                }

            }
        });
////////////////////

// Chình lệnh menu popup của dịch vụ
        ItemXoaDV.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int lick = JOptionPane.showConfirmDialog(null, "Xác nhận xóa dịch vụ " + maDVtmp + " sử dụng ngày " + ngayDV + "?", "Thông Báo", 2);
                if (lick == JOptionPane.OK_OPTION) {
                    try {
                        // Xử lý sự kiện khi người dùng chọn "Edit" trên popup menu
                        SuDungDichVuBUS.DelSuDungDichVu(maCTT, maDVtmp, ngayDV);
                    } catch (SQLException | ParseException ex) {
                        Logger.getLogger(FrameOrderRoom.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Iterator<SuDungDichVuDTO> iterator = listSDDV.iterator();
                    while (iterator.hasNext()) {
                        SuDungDichVuDTO element = iterator.next();
                        if (element.getMaDV().equals(maDVtmp) && element.getNgaySuDungString().equals(ngayDV)) {
                            iterator.remove(); // Xóa phần tử thỏa mãn điều kiện
                        }
                    }

                    Iterator<SuDungDichVuDTO> iterator1 = listSDDVnew.iterator();
                    while (iterator1.hasNext()) {
                        SuDungDichVuDTO element1 = iterator1.next();
                        if (element1.getMaDV().equals(maDVtmp) && element1.getNgaySuDungString().equals(ngayDV)) {
                            iterator1.remove(); // Xóa phần tử thỏa mãn điều kiện
                        }
                    }

                    loadTBDV();
                } else {
                    if (lick == JOptionPane.CANCEL_OPTION) {
                        //setVisible(false);
                    }
                }

            }
        });

        ItemSuaDV.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int lick = JOptionPane.showConfirmDialog(null, "Thay đổi số lượng dịch vụ " + maDVtmp + " sử dụng ngày " + ngayDV + "?", "Thông Báo", 2);
                if (lick == JOptionPane.OK_OPTION) {
                    txtSLDV.setText(String.valueOf(SLtmp));
                    pnSuaSL.setVisible(true);
                } else {
                    if (lick == JOptionPane.CANCEL_OPTION) {
                        //setVisible(false);
                    }
                }

            }
        });

        /////////////
        btnPay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int ans = JOptionPane.showConfirmDialog(null, "Bạn có muốn thanh toán?", "Thông báo", JOptionPane.YES_NO_OPTION);
                if (ans == JOptionPane.YES_OPTION) {
                    boolean check = true;
                    for (ThuePhongDTO tpdto : listTP) {
                        if (tpdto.getTinhTrang() == 0) {
                            check = false;
                            break;
                        }
                    }
                    if (check) {
                        int gt = 1;
                        if (khDTO.getGioiTinh().equals("Nam")) {
                            gt = 0;
                        }
                        new LapHoaDonGUI(khDTO.getMaKH(), khDTO.getTenKH(), khDTO.getCmnd(), khDTO.getSdt(), gt, maCTT);
                        setVisible(false);
                    } else {
                        JOptionPane.showMessageDialog(null, "Còn phòng đang xử lý");
                    }
                }
            }
        });

        setVisible(true);

    }

    public class data {

        public String maP;
        public String maCTT;
        public String maDV;
        public String maKH;
        public String ngaySD;
        public String ngayThue;
    }

    public String date(Date d, int hour, int minute) {
        String date = "";
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        date += sdf.format(d);
        String time = " ";
        if (hour < 10) {
            time += "0" + hour + ":";
        } else {
            time += hour + ":";
        }
        if (minute < 10) {
            time += "0" + minute + ":00";
        } else {
            time += minute + ":00";
        }
        date += time;
        return date;
    }

    public String dateChange(String date) {
        String date1[] = date.split(" ");
        String date2[] = date1[0].split("-");
        String day = date2[0];
        String month = date2[1];
        String year = date2[2];
        return year + "-" + month + "-" + day + " " + date1[1];
    }

    public boolean checkTimeNow(Date date, int h, int m) {
        Calendar cd = Calendar.getInstance();
        cd.setTime(date);
        cd.set(Calendar.HOUR, h);
        cd.set(Calendar.MINUTE, m);
        cd.set(Calendar.SECOND, 0);
        Date d = new Date();
        System.out.println(d.toString());
        System.out.println(cd.getTime().toString());
        return cd.getTime().after(d);
    }

    public boolean checkTimeTra_Thue() {
        Calendar timeThue1 = Calendar.getInstance();
        timeThue1.setTime(dcsNgaythue.getDate());
        timeThue1.set(Calendar.HOUR, (int) timeThue.gioSpinner.getValue());
        timeThue1.set(Calendar.MINUTE, (int) timeThue.phutSpinner.getValue());
        timeThue1.set(Calendar.SECOND, 0);

        Calendar timeTra1 = Calendar.getInstance();
        timeTra1.setTime(dcsNgaytra.getDate());
        timeTra1.set(Calendar.HOUR, (int) timeTra.gioSpinner.getValue());
        timeTra1.set(Calendar.MINUTE, (int) timeTra.phutSpinner.getValue());
        timeTra1.set(Calendar.SECOND, 0);

        return timeTra1.getTime().after(timeThue1.getTime());
    }

    public void loadKH() throws SQLException, ParseException {
        ArrayList<KhachHangDTO> listtmp = new KhachHangBUS().LoadKH();
        for (KhachHangDTO khdto : listtmp) {
            if (khdto.getMaKH().equals(cttDTO.getMaKH())) {
                khDTO = khdto;
                break;
            }
        }
        System.out.println(khDTO.getMaKH());
    }

    public void loadListThuePhong() {
        listTP = ThuePhongBUS.getListTP_P(maCTT, new ArrayList<>());
    }

    public void loadListSDDV() {
        listSDDV = SuDungDichVuBUS.LoadSDDV(maCTT);
    }

    public void clearTB(JTable a) {
        DefaultTableModel dm = (DefaultTableModel) a.getModel();
        dm.getDataVector().removeAllElements();
        revalidate();
    }

    public void loadTB() {
        clearTB(tblP);
        int i = 0;
        DefaultTableModel dtm = new DefaultTableModel();
        dtm.addColumn("STT");
        dtm.addColumn("Mã phòng");
        dtm.addColumn("Tên phòng");
        dtm.addColumn("Loại phòng");
        dtm.addColumn("Loại hình thuê");
        dtm.addColumn("Ngày thuê");
        dtm.addColumn("Ngày trả");
        dtm.addColumn("Ngày check out");
        dtm.addColumn("Giá phòng/Ngày");
        dtm.addColumn("Giá thuê");
        dtm.addColumn("Tình trạng");
        tblP.setPreferredScrollableViewportSize(new Dimension(500, 10 * tblP.getRowHeight()));
        tblP.setModel(dtm);
        TableColumn column = tblP.getColumnModel().getColumn(0);
        column.setPreferredWidth(1);
        column = tblP.getColumnModel().getColumn(1);
        column.setPreferredWidth(2);
        column = tblP.getColumnModel().getColumn(3);
        column.setPreferredWidth(3);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        tblP.setDefaultRenderer(Object.class, centerRenderer);

        for (ThuePhongDTO tpdto : listTP) {
            String tmpString = "";
            for (PhongDTO pdto : listPFull) {
                if (tpdto.getMaP().equals(pdto.getMaP())) {
                    if (tpdto.getTinhTrang() == 0) {
                        tmpString = "Đang xử lý";
                    } else if (tpdto.getTinhTrang() == 1) {
                        tmpString = "Đang được thuê";
                    } else {
                        tmpString = "Đã trả phòng";
                    }

                    Object[] data = {i + 1, pdto.getMaP(), pdto.getTenP(), pdto.getLoaiP(), tpdto.getLoaiHinhThue(), tpdto.getNgayThue(),
                        tpdto.getNgayTra(), tpdto.getNgayCheckOut(), pdto.getGiaP(), tpdto.getGia(), tmpString};
                    dtm.addRow(data);
                    i++;
                    break;
                }
            }
        }

    }

    public void loadTBDV() {
        clearTB(tblDV);
        //int i=0;
        DefaultTableModel dtm = new DefaultTableModel();
        dtm.addColumn("Mã dịch vụ");
        dtm.addColumn("Tên dịch vụ");
        dtm.addColumn("Ngày sử dụng");
        dtm.addColumn("Giá");
        dtm.addColumn("Số lượng");
        dtm.addColumn("Giá tổng");
        tblDV.setModel(dtm);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        tblDV.setPreferredScrollableViewportSize(new Dimension(500, 5 * tblDV.getRowHeight()));
        tblDV.setModel(dtm);
        tblDV.setDefaultRenderer(Object.class, centerRenderer);

        for (SuDungDichVuDTO tpdto : listSDDV) {
            for (DichVuDTO pdto : listDVFull) {
                if (tpdto.getMaDV().equals(pdto.getMaDV())) {
                    Object[] data = {tpdto.getMaDV(), pdto.getTenDV(), tpdto.getNgaySuDungString(), pdto.getGiaDV(), tpdto.getSoLuong(), pdto.getGiaDV() * tpdto.getSoLuong()};
                    dtm.addRow(data);
                    break;
                }
            }

        }
    }

    public class InforPane extends JPanel {

        public InforPane() {
            setBorder(new EmptyBorder(8, 8, 8, 8));
            setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.fill = GridBagConstraints.BOTH;
            gbc.gridwidth = 2;
            gbc.weighty = 0.5;
            TitlePane title = new TitlePane();
            add(title, gbc);

            gbc.gridwidth = 1;
            gbc.gridy++;
            gbc.weighty = 1;
            gbc.weightx = 2;

            KHPane namePane = new KHPane();
            namePane.setBorder(new CompoundBorder(new TitledBorder("Chi tiết khách hàng"), new EmptyBorder(4, 4, 4, 4)));
            add(namePane, gbc);

            gbc.gridx++;
            gbc.weightx = 1;

            RoomPane emailPane = new RoomPane();
            emailPane.setBorder(new CompoundBorder(new TitledBorder("Chi tiết phòng thuê"), new EmptyBorder(4, 4, 4, 4)));
            add(emailPane, gbc);

            gbc.gridx = 0;
            gbc.gridy++;
            gbc.gridwidth = 3;
            gbc.weightx = 2;

            PnDVPane Pane = new PnDVPane();
            Pane.setBorder(new CompoundBorder(new TitledBorder("Chi tiết phòng"), new EmptyBorder(4, 4, 4, 4)));
            add(Pane, gbc);

            gbc.weightx = 1;
            gbc.gridy++;
            AddDVPane Pane1 = new AddDVPane();
            Pane1.setBorder(new CompoundBorder(new TitledBorder("Thêm dịch vụ cho phòng"), new EmptyBorder(4, 4, 4, 4)));
            add(Pane1, gbc);

            JPanel pnButton = new JPanel();
            gbc.gridy++;
            pnButton.setLayout(new FlowLayout(FlowLayout.RIGHT));

            btnPay.setPreferredSize(new Dimension(150, 40));
            btnPay.setMaximumSize(new Dimension(150, 40));
            ImageIcon iconAdd = new ImageIcon(new ImageIcon(getClass().getResource("/GUI/asset/thanhtoan.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
            btnPay.setIcon(iconAdd);
            btnPay.setFont(sgUI18);
            btnPay.setBackground(Color.white);

            pnButton.add(btnPay);
            add(pnButton, gbc);

        }

    }

    public class TitlePane extends JPanel {

        public TitlePane() {

            JLabel lblTitle = new JLabel("ĐẶT TRẢ PHÒNG");
            lblTitle.setFont(sgUI18);
            lblTitle.setForeground(Color.red);
            add(lblTitle);
        }

    }

    public class KHPane extends JPanel {

        public KHPane() {
            setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.anchor = GridBagConstraints.WEST;
            gbc.insets = new Insets(10, 10, 10, 10);
            add(new JLabel("Mã khách hàng:"), gbc);

            gbc.gridy++;
            add(new JLabel("Tên khách hàng:"), gbc);

            gbc.gridy++;
            add(new JLabel("CMND:"), gbc);

            gbc.gridx = 1;
            gbc.gridy = 0;
            gbc.fill = GridBagConstraints.BOTH;
            gbc.anchor = GridBagConstraints.WEST;
            gbc.weightx = 1;

            JTextField txtMaKH = new JTextField(khDTO.getMaKH(), 20);
            txtMaKH.setEditable(false);
            add(txtMaKH, gbc);

            JTextField txtTenKH = new JTextField(khDTO.getTenKH(), 20);
            txtTenKH.setEditable(false);
            gbc.gridy++;
            add(txtTenKH, gbc);

            gbc.gridy++;
            JTextField txtCmnd = new JTextField(khDTO.getCmnd(), 20);
            txtCmnd.setEditable(false);
            add(txtCmnd, gbc);

            gbc.gridx = 3;
            gbc.gridy = 0;
            gbc.anchor = GridBagConstraints.EAST;
            //gbc.gridwidth = 1;
            gbc.weightx = 0;

            add(new JLabel("Giới tính:"), gbc);

            gbc.gridy++;
            add(new JLabel("Số điện thoại:"), gbc);

            gbc.gridy++;
            add(new JLabel("Tiền đặt cọc:"), gbc);

            //------------------//
            gbc.gridx = 4;
            gbc.gridy = 0;
            gbc.weightx = 1;

            JTextField txtGioitinh = new JTextField(khDTO.getGioiTinh(), 20);
            txtGioitinh.setEditable(false);
            add(txtGioitinh, gbc);

            JTextField txtSdt = new JTextField(khDTO.getSdt(), 20);
            txtSdt.setEditable(false);
            gbc.gridy++;
            add(txtSdt, gbc);

            gbc.gridy++;
            JTextField txtTiencoc = new JTextField(String.valueOf(cttDTO.getTienDatCoc()), 20);
            txtTiencoc.setEditable(false);
            add(txtTiencoc, gbc);
            //System.out.println(cttDTO.getTienDatCoc());
        }
    }

    public class Time extends JPanel {

        JSpinner gioSpinner;
        JSpinner phutSpinner;

        public void init() {
            //spinner giờ
            SpinnerNumberModel gioModel = new SpinnerNumberModel(1, 0, 24, 1);
            gioSpinner = new JSpinner(gioModel);

            //spinner phút
            SpinnerNumberModel phutModel = new SpinnerNumberModel(0, 0, 59, 1);
            phutSpinner = new JSpinner(phutModel);

            add(gioSpinner);
            add(new JLabel("h:"));
            add(phutSpinner);
            add(new JLabel("p"));

        }

        public void setSee(int i) {
            if (i == 0) {
                gioSpinner.setEnabled(false);
                phutSpinner.setEnabled(false);
            }
            if (i == 1) {
                gioSpinner.setEnabled(true);
                phutSpinner.setEnabled(true);
            }
        }

        public void reset() {
            gioSpinner.setValue(0);
            phutSpinner.setValue(0);
        }

        public Time() {
            init();
        }

    }

    protected class RoomPane extends JPanel {

        JFrame sdpMini = new JFrame("Sơ đồ phòng trống");

        ButtonGroup group = new ButtonGroup();
        JLabel lblNgayTra = new JLabel("Ngày trả:");
        JLabel lblNgayThue = new JLabel("Ngày thuê:");
        LocalDate dateDefault = java.time.LocalDate.now();
        Date date = java.sql.Date.valueOf(dateDefault);

        public RoomPane() {
            //ngày trả
            btnThueNgay.setSelected(true);
            timeTra.setSee(0);
            dcsNgaytra.setDate(date);
            dcsNgaytra.setDateFormatString("dd-MM-yyyy");

            //ngày thuê
            dcsNgaythue.setDate(date);
            dcsNgaythue.setDateFormatString("dd-MM-yyyy");

            setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.anchor = GridBagConstraints.WEST;
            gbc.insets = new Insets(10, 10, 10, 5);
            add(new JLabel("Hình thức thuê:"), gbc);

            gbc.gridy++;
            add(new JLabel("Phòng đã chọn:"), gbc);

            gbc.gridy++;
            add(lblNgayThue, gbc);

            gbc.gridy++;
            add(lblNgayTra, gbc);

            gbc.gridx = 1;
            gbc.gridy = 0;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.anchor = GridBagConstraints.WEST;

            group.add(btnThueNgay);
            group.add(btnThueGio);

            btnThueNgay.addActionListener(e -> {
                timeTra.setSee(0);
            });

            btnThueGio.addActionListener(e -> {
                timeTra.setSee(1);
            });

            add(btnThueNgay, gbc);

            gbc.weightx = 0.5;
            gbc.gridx++;
            add(btnThueGio, gbc);

            gbc.gridx = 1;
            gbc.gridy++;
            gbc.weightx = 1; // sửa giá trị weightx
            gbc.fill = GridBagConstraints.BOTH;
            add(txtSoPhong, gbc);
            txtSoPhong.setEditable(false);

            gbc.gridy++;
            add(dcsNgaythue, gbc);

            gbc.gridy++;
            add(dcsNgaytra, gbc);

            gbc.gridy = 2;
            gbc.gridx = 2;
            add(timeThue, gbc);
            gbc.gridy++;
            add(timeTra, gbc);

            //gbc.weightx = 0.5;
            gbc.gridy = 6;
            gbc.gridx = 2;

            btnThem.setPreferredSize(new Dimension(150, 40));
            btnThem.setMaximumSize(new Dimension(150, 40));
            ImageIcon iconAdd = new ImageIcon(new ImageIcon(getClass().getResource("/GUI/asset/them.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
            btnThem.setIcon(iconAdd);
            btnThem.setFont(sgUI18);
            btnThem.setBackground(Color.white);

            add(btnThem, gbc);
            gbc.gridx--;

            JButton btnXemPhong = new JButton("Phòng");
            btnXemPhong.setPreferredSize(new Dimension(150, 40));
            btnXemPhong.setMaximumSize(new Dimension(150, 40));
            iconAdd = new ImageIcon(new ImageIcon(getClass().getResource("/GUI/asset/phong.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
            btnXemPhong.setIcon(iconAdd);
            btnXemPhong.setFont(sgUI18);
            btnXemPhong.setBackground(Color.white);

            add(btnXemPhong, gbc);

            btnXemPhong.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (checkNull()) {
                        int hourThue = (int) timeThue.gioSpinner.getValue();
                        int minThue = (int) timeThue.phutSpinner.getValue();
                        int hourTra = (int) timeTra.gioSpinner.getValue();
                        int minTra = (int) timeTra.gioSpinner.getValue();
                        new PanelSelectPhong(1, dateChange(date(dcsNgaythue.getDate(), hourThue, minThue)),
                                dateChange(date(dcsNgaytra.getDate(), hourTra, minTra)));
                    }
                }
            });

            btnLuuDoiP.setPreferredSize(new Dimension(150, 40));
            btnLuuDoiP.setMaximumSize(new Dimension(150, 40));
            iconAdd = new ImageIcon(new ImageIcon(getClass().getResource("/GUI/asset/save.png")).getImage().getScaledInstance(40, 30, Image.SCALE_SMOOTH));
            btnLuuDoiP.setIcon(iconAdd);
            btnLuuDoiP.setFont(sgUI18);
            btnLuuDoiP.setBackground(Color.white);
            gbc.gridx = 0;
            gbc.gridy = 6;
            add(btnLuuDoiP, gbc);
            btnLuuDoiP.setEnabled(false);

            btnLuuDoiP.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String tt = "";
                    int hThue = (int) timeThue.gioSpinner.getValue();
                    int mThue = (int) timeThue.phutSpinner.getValue();
                    int hTra = (int) timeTra.gioSpinner.getValue();
                    int mTra = (int) timeTra.phutSpinner.getValue();
                    ThuePhongDTO tptmp = new ThuePhongDTO();
                    for (ThuePhongDTO tpdto : listTP) {
                        if (tpdto.getMaP().equals(datatmp.maP) && tpdto.getNgayThue().equals(datatmp.ngayThue)) {
                            tptmp = tpdto;
                            break;
                        }
                    }
                    if (!txtSoPhong.getText().equals(tptmp.getMaP())) {
                        tt += "Phòng: " + tptmp.getMaP() + "->" + txtSoPhong.getText() + "\n";
                    }
                    if (tptmp.getLoaiHinhThue().equals("Theo ngày") && btnThueGio.isSelected()) {
                        tt += "Loại hình thuê: " + tptmp.getLoaiHinhThue() + "->" + "Theo giờ\n";
                    }
                    if (tptmp.getLoaiHinhThue().equals("Theo giờ") && btnThueNgay.isSelected()) {
                        tt += "Loại hình thuê: " + tptmp.getLoaiHinhThue() + "->" + "Theo ngày\n";
                    }
                    tt += "Ngày thuê: " + tptmp.getNgayThue() + "->" + date(dcsNgaythue.getDate(), hThue, mThue);
                    tt += "\nNgày trả: " + tptmp.getNgayTra() + "->" + date(dcsNgaytra.getDate(), hTra, mTra);

                    int lick = JOptionPane.showConfirmDialog(null, "Xác nhận chỉnh sửa thông tin thuê phòng:\n" + tt, "Thông Báo", 2);
                    if (lick == JOptionPane.OK_OPTION) {
                        for (ThuePhongDTO tpdto : listTP) {
                            if (tpdto.getMaP().equals(datatmp.maP) && tpdto.getNgayThue().equals(datatmp.ngayThue)) {
                                tpdto.setMaP(txtSoPhong.getText());
                                tpdto.setNgayThue(date(dcsNgaythue.getDate(), hThue, mThue));
                                tpdto.setNgayTra(date(dcsNgaytra.getDate(), hTra, mTra));
                                tpdto.setNgayCheckOut(date(dcsNgaytra.getDate(), hTra, mTra));

                                try {
                                    ThuePhongBUS.DelThuePhong(maCTT, datatmp.maP, datatmp.ngayThue);
                                } catch (SQLException | ParseException ex) {
                                    Logger.getLogger(FrameOrderRoom.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                try {
                                    ThuePhongBUS.InsertThuePhong(tpdto);
                                } catch (SQLException | ParseException ex) {
                                    Logger.getLogger(FrameOrderRoom.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                loadTB();
                                btnThem.setEnabled(true);
                                break;
                            }
                        }
                    } else {
                        if (lick == JOptionPane.CANCEL_OPTION) {
                            //setVisible(false);
                        }
                    }

                }
            });

            btnThem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("co bam");
                    if (!MaPhong.isBlank()) {
                        if (btnThueGio.isSelected()) {
                            thueGio();
                        }
                        if (btnThueNgay.isSelected()) {
                            thueNgay();
                        }
                        loadTB();
                        cleartxt();
                        MaPhong = "";
                        giaTmp = 0;
                        for (ThuePhongDTO tpdto : listTP) {

                            for (PhongDTO pdto : listPFull) {
                                if (pdto.getMaP().equals(tpdto.getMaP())) {
                                    pdto.setTinhTrang("Đang được thuê");
                                    //new PhongBUS().updatePhong(pdto);
                                }
                            }
                            System.out.println(tpdto.getNgayThue());
                            if (ThuePhongBUS.checkTP(maCTT, tpdto.getMaP(), tpdto.getNgayThue())) {
                                System.out.println("co");
                                try {
                                    ThuePhongBUS.UpdateThuePhong(tpdto);
                                } catch (SQLException | ParseException ex) {
                                    Logger.getLogger(FrameOrderRoom.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            } else
                                try {
                                System.out.println(tpdto.getNgayThue());
                                ThuePhongBUS.InsertThuePhong(tpdto);
                            } catch (SQLException | ParseException ex) {
                                Logger.getLogger(FrameOrderRoom.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "Bạn chưa chọn phòng");
                    }
                }
            });

        }

        public void thueNgay() {
            int h = (int) timeThue.gioSpinner.getValue();
            int m = (int) timeThue.phutSpinner.getValue();
            ThuePhongDTO tp = new ThuePhongDTO();
            tp.setMaChiTietThue(maCTT);
            tp.setMaP(MaPhong);
            tp.setNgayThue(date(dcsNgaythue.getDate(), h, m));
            tp.setNgayTra(date(dcsNgaytra.getDate(), h, m));
            tp.setLoaiHinhThue("Theo ngày");
            long diff = Math.abs(dcsNgaythue.getDate().getTime() - dcsNgaytra.getDate().getTime());
            long diffDays = diff / (24 * 60 * 60 * 1000);
            for (PhongDTO pdto : listPFull) {
                if (pdto.getMaP().equals(MaPhong)) {
                    tp.setGia((int) diffDays * pdto.getGiaP());
                    break;
                }
            }
            tp.setTinhTrang(0);
            tp.setXuLy(0);
            tp.setNgayCheckOut(date(dcsNgaytra.getDate(), h, m));
            listTP.add(tp);
        }

        public void thueGio() {
            int h = (int) timeThue.gioSpinner.getValue();
            int m = (int) timeThue.phutSpinner.getValue();
            int h1 = (int) timeTra.gioSpinner.getValue();
            int m1 = (int) timeTra.phutSpinner.getValue();
            long time = dcsNgaytra.getDate().getTime() - dcsNgaythue.getDate().getTime();
            int dayCount = (int) (time / (1000 * 60 * 60 * 24));
            ThuePhongDTO tp = new ThuePhongDTO();
            tp.setMaChiTietThue(maCTT);
            tp.setMaP(MaPhong);
            tp.setNgayThue(date(dcsNgaythue.getDate(), h, m));
            tp.setNgayTra(date(dcsNgaytra.getDate(), h1, m1));
            tp.setNgayCheckOut(date(dcsNgaytra.getDate(), h1, m1));
            tp.setLoaiHinhThue("Theo giờ");
            int hour = 0;
            int day = dayCount;
            if (hour < 0) {
                hour = 24 + hour;
                day = day - 1;
            }
            if (hour == 0) {
                tp.setGia(giaTmp * day);
            } else if (hour == 1) {
                tp.setGia(giaTmp * 45 / 100);
                if (day > 0) {
                    tp.setGia(giaTmp * day + tp.getGia());
                }
            } else if (hour == 2) {
                tp.setGia((giaTmp * 45 / 100) - 20000);
                if (day > 0) {
                    tp.setGia(giaTmp * day + tp.getGia());
                }
            } else if (hour == 3) {
                tp.setGia((giaTmp * 45 / 100) - 40000);
                if (day > 0) {
                    tp.setGia(giaTmp * day + tp.getGia());
                }
            } else if (hour >= 4 && hour <= 12) {
                tp.setGia(giaTmp / 2);
                if (day > 0) {
                    tp.setGia(giaTmp * day + tp.getGia());
                }
            } else {
                tp.setGia(giaTmp);
                if (day > 0) {
                    tp.setGia(giaTmp * day + tp.getGia());
                }
            }

            tp.setTinhTrang(0);
            tp.setXuLy(0);

            listTP.add(tp);
        }

        public void cleartxt() {
            btnThueGio.setSelected(false);
            btnThueNgay.setSelected(false);
            txtSoPhong.setText("");
            timeThue.reset();
            timeTra.reset();
            dcsNgaytra.setDate(date);
            dcsNgaytra.setDateFormatString("dd-MM-yyyy");
            dcsNgaythue.setDate(date);
            dcsNgaythue.setDateFormatString("dd-MM-yyyy");
        }

        public boolean checkNull() {
            int hThue = (int) timeThue.gioSpinner.getValue();
            int mThue = (int) timeThue.phutSpinner.getValue();
            int hTra = (int) timeTra.gioSpinner.getValue();
            int mTra = (int) timeTra.phutSpinner.getValue();
            if (!btnThueNgay.isSelected() && !btnThueGio.isSelected()) {
                JOptionPane.showMessageDialog(null, "Bạn chưa chọn hình thức thuê");
                return false;
            } else {
                if (btnThueNgay.isSelected()) {
                    if (!checkTimeNow(dcsNgaythue.getDate(), hThue, mThue)) {
                        JOptionPane.showMessageDialog(null, "Ngày, giờ thuê phải lớn hơn ngày, giờ hiện tại");
                        return false;
                    }
                    if (!checkTimeNow(dcsNgaytra.getDate(), hTra, mTra)) {
                        JOptionPane.showMessageDialog(null, "Ngày, giờ trả phải lớn hơn ngày, giờ hiện tại");
                        return false;
                    }
                    if (!checkTimeTra_Thue()) {
                        JOptionPane.showMessageDialog(null, "Ngày, giờ trả phải lớn hơn ngày, giờ thuê");
                        return false;
                    }

                }

                if (btnThueGio.isSelected()) {
                    if (!checkTimeNow(dcsNgaythue.getDate(), hThue, mThue)) {
                        JOptionPane.showMessageDialog(null, "Ngày, giờ thuê phải lớn hơn ngày, giờ hiện tại");
                        return false;
                    }
                    if (!checkTimeNow(dcsNgaytra.getDate(), hTra, mTra)) {
                        JOptionPane.showMessageDialog(null, "Ngày, giờ trả phải lớn hơn ngày, giờ hiện tại");
                        return false;
                    }
                    if (!checkTimeTra_Thue()) {
                        JOptionPane.showMessageDialog(null, "Ngày, giờ trả và phải lớn hơn ngày, giờ thuê");
                        return false;
                    }
                    Calendar dateThue = Calendar.getInstance();
                    dateThue.setTime(dcsNgaythue.getDate());
                    dateThue.set(Calendar.HOUR, (int) timeThue.gioSpinner.getValue());
                    dateThue.set(Calendar.MINUTE, (int) timeThue.phutSpinner.getValue());
                    dateThue.set(Calendar.SECOND, 0);

                    Calendar dateTra = Calendar.getInstance();
                    dateTra.setTime(dcsNgaytra.getDate());
                    dateTra.set(Calendar.HOUR, (int) timeTra.gioSpinner.getValue());
                    dateTra.set(Calendar.MINUTE, (int) timeTra.phutSpinner.getValue());
                    dateTra.set(Calendar.SECOND, 0);

                    long time = dateTra.getTime().getTime() - dateThue.getTime().getTime();
                    int hourCount = (int) (time / (60 * 60 * 1000));

                    long date = dcsNgaytra.getDate().getTime() - dcsNgaythue.getDate().getTime();
                    int dayCount = (int) (date / (1000 * 60 * 60 * 24));
                    if (hourCount == 0) {
                        if (dayCount < 1) {
                            JOptionPane.showMessageDialog(null, "Vui lòng nhập giờ thuê ít nhất 1h", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                            return false;
                        } else {
                            int ans = JOptionPane.showConfirmDialog(null, "Có lẽ bạn muốn thuê theo ngày", "Thông báo", JOptionPane.YES_NO_OPTION);
                            if (ans == JOptionPane.YES_OPTION) {
                                btnThueNgay.setSelected(true);
                            } else {
                                JOptionPane.showMessageDialog(null, "Vui lòng chọn giờ thuê ít nhất 1h", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                                return false;
                            }
                        }
                    }

                }
            }

            return true;

        }

    }

    public class PnDVPane extends JPanel {

        public PnDVPane() {
            setLayout(new BorderLayout());
            loadTB();

            add(new JScrollPane(tblP), BorderLayout.CENTER);
            add(btnTmp, BorderLayout.SOUTH);
            btnTmp.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    int lick = JOptionPane.showConfirmDialog(null, "Xác nhận đổi sang phòng " + MaPhong, "Thông Báo", 2);
                    if (lick == JOptionPane.OK_OPTION) {
                        for (ThuePhongDTO tpdto : listTP) {
                            if (tpdto.getMaP().equals(datatmp.maP) && tpdto.getNgayThue().equals(datatmp.ngayThue)) {
                                tpdto.setMaP(MaPhong);
                                try {
                                    // Xử lý sự kiện khi người dùng chọn "Edit" trên popup menu
                                    ThuePhongBUS.DelThuePhong(maCTT, datatmp.maP, datatmp.ngayThue);

                                } catch (SQLException | ParseException ex) {
                                    Logger.getLogger(FrameOrderRoom.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                try {
                                    ThuePhongBUS.InsertThuePhong(tpdto);
                                } catch (SQLException | ParseException ex) {
                                    Logger.getLogger(FrameOrderRoom.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }

                        }

                        loadTB();
                    } else {
                        if (lick == JOptionPane.CANCEL_OPTION) {
                            //setVisible(false);
                        }
                    }

                }
            });

            btnTmp.setVisible(false);

            tblP.addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent e) {
                    if (e.isPopupTrigger()) {
                        int row = tblP.rowAtPoint(e.getPoint());

                        // Lấy giá trị tại hàng được chọn
                        String tinhtrang = (String) tblP.getValueAt(row, 10); // Giá trị ở cột đầu tiên
                        String maP = (String) tblP.getValueAt(row, 1);
                        String ngayThue = (String) tblP.getValueAt(row, 5);

                        datatmp.maP = maP;
                        datatmp.ngayThue = ngayThue;
                        ItemXoaP.setVisible(true);
                        ItemSuaP.setVisible(true);
                        ItemTra.setVisible(true);
                        ItemDoiP.setVisible(true);
                        ItemLayP.setVisible(true);
                        switch (tinhtrang) {
                            case "Đang được thuê":
                                ItemXoaP.setVisible(false);
                                ItemSuaP.setVisible(false);
                                ItemLayP.setVisible(false);
                                break;
                            case "Đang xử lý":
                                ItemTra.setVisible(false);
                                ItemDoiP.setVisible(false);

                                break;
                            case "Đã trả phòng":
                                ItemSuaP.setVisible(false);
                                ItemTra.setVisible(false);
                                ItemXoaP.setVisible(false);
                                ItemDoiP.setVisible(false);
                                ItemLayP.setVisible(false);
                                break;
                            default:
                                break;
                        }

                        // Hiển thị popup menu tại vị trí chuột được nhấn
                        popupMenuP.show(e.getComponent(), e.getX(), e.getY());
                    }
                }

                public void mouseReleased(MouseEvent e) {
                    if (e.isPopupTrigger()) {
                        int row = tblP.rowAtPoint(e.getPoint());

                        // Lấy giá trị tại hàng được chọn
                        String tinhtrang = (String) tblP.getValueAt(row, 10); // Giá trị ở cột đầu tiên
                        String maP = (String) tblP.getValueAt(row, 1);
                        String ngayThue = (String) tblP.getValueAt(row, 5);

                        datatmp.maP = maP;
                        datatmp.ngayThue = ngayThue;
                        ItemXoaP.setVisible(true);
                        ItemSuaP.setVisible(true);
                        ItemTra.setVisible(true);
                        ItemDoiP.setVisible(true);
                        ItemLayP.setVisible(true);
                        switch (tinhtrang) {
                            case "Đang được thuê":
                                ItemXoaP.setVisible(false);
                                ItemSuaP.setVisible(false);
                                ItemLayP.setVisible(false);
                                break;
                            case "Đang xử lý":
                                ItemTra.setVisible(false);
                                ItemDoiP.setVisible(false);
                                break;
                            case "Đã trả phòng":
                                ItemSuaP.setVisible(false);
                                ItemTra.setVisible(false);
                                ItemXoaP.setVisible(false);
                                ItemDoiP.setVisible(false);
                                ItemLayP.setVisible(false);
                                break;
                            default:
                                break;
                        }
                        // Hiển thị popup menu tại vị trí chuột được nhấn
                        popupMenuP.show(e.getComponent(), e.getX(), e.getY());
                    }
                }
            });

        }

    }

    public class AddDVPane extends JPanel {

        public AddDVPane() {

            setLayout(new GridBagLayout());

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.fill = GridBagConstraints.BOTH;
            gbc.weightx = 1;

            add(new InforRoom(), gbc);

            //JPanel aJPanel=new JPanel();
            //aJPanel.setBackground(Color.red);
            //aJPanel.setBorder(new CompoundBorder(new TitledBorder("Chọn dịch vụ"), new EmptyBorder(4, 4, 4, 4)));
            gbc.gridx++;
            add(new ChooseDV(), gbc);

        }

    }

    public class InforRoom extends JPanel {

        boolean check = false;

        public InforRoom() {
            loadTBDV();
            tblDV.addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent e) {
                    if (e.isPopupTrigger()) {
                        int row = tblDV.rowAtPoint(e.getPoint());

                        // Lấy giá trị tại hàng được chọn
                        String maDV = (String) tblDV.getValueAt(row, 0); // Giá trị ở cột đầu tiên
                        String ngaySD = (String) tblDV.getValueAt(row, 2);
                        SLtmp = (int) tblDV.getValueAt(row, 4);
                        maDVtmp = maDV;
                        ngayDV = ngaySD;
                        ItemXoaDV.setVisible(true);
                        ItemSuaDV.setVisible(true);
                        for (SuDungDichVuDTO sddvdto : listSDDV) {
                            for (SuDungDichVuDTO newdto : listSDDVnew) {
                                if (sddvdto.getMaDV().equals(newdto.getMaDV()) && sddvdto.getNgaySuDungString().equals(newdto.getNgaySuDungString())) {
                                    check = true;
                                    break;
                                }
                            }
                        }

                        if (check) {
                            ItemXoaDV.setVisible(true);
                        } else {
                            ItemXoaDV.setVisible(false);
                        }

                        // Hiển thị popup menu tại vị trí chuột được nhấn
                        popupMenuDV.show(e.getComponent(), e.getX(), e.getY());
                    }
                }

                public void mouseReleased(MouseEvent e) {
                    if (e.isPopupTrigger()) {
                        if (e.isPopupTrigger()) {
                            int row = tblDV.rowAtPoint(e.getPoint());

                            // Lấy giá trị tại hàng được chọn
                            String maDV = (String) tblDV.getValueAt(row, 0); // Giá trị ở cột đầu tiên
                            String ngaySD = (String) tblDV.getValueAt(row, 2);
                            SLtmp = (int) tblDV.getValueAt(row, 4);
                            maDVtmp = maDV;
                            ngayDV = ngaySD;
                            ItemXoaDV.setVisible(true);
                            ItemSuaDV.setVisible(true);
                            for (SuDungDichVuDTO sddvdto : listSDDV) {
                                for (SuDungDichVuDTO newdto : listSDDVnew) {
                                    if (sddvdto.getMaDV().equals(newdto.getMaDV()) && sddvdto.getNgaySuDungString().equals(newdto.getNgaySuDungString())) {
                                        check = true;
                                        break;
                                    }
                                }
                            }

                            if (check) {
                                ItemXoaDV.setVisible(true);
                            } else {
                                ItemXoaDV.setVisible(false);
                            }

                            // Hiển thị popup menu tại vị trí chuột được nhấn
                            popupMenuDV.show(e.getComponent(), e.getX(), e.getY());
                        }
                    }
                }
            });
            setLayout(new BorderLayout());
            setBorder(new CompoundBorder(new TitledBorder("Thông tin phòng được chọn"), new EmptyBorder(4, 4, 4, 4)));
            add(new JScrollPane(tblDV), BorderLayout.CENTER);
        }

    }

    public class ChooseDV extends JPanel {

        public ChooseDV() {
            setLayout(new GridBagLayout());
            setBorder(new CompoundBorder(new TitledBorder("Chọn dịch vụ"), new EmptyBorder(4, 4, 4, 4)));
            btnChonDV.setVisible(false);

            btnLuuSuaDV.setPreferredSize(new Dimension(150, 40));
            btnLuuSuaDV.setMaximumSize(new Dimension(150, 40));
            ImageIcon iconAdd = new ImageIcon(new ImageIcon(getClass().getResource("/GUI/asset/save.png")).getImage().getScaledInstance(50, 30, Image.SCALE_SMOOTH));
            btnLuuSuaDV.setIcon(iconAdd);
            btnLuuSuaDV.setFont(sgUI18);
            btnLuuSuaDV.setBackground(Color.white);

            JButton btnAdd = new JButton("Thêm dịch vụ");
            btnAdd.setPreferredSize(new Dimension(150, 40));
            btnAdd.setMaximumSize(new Dimension(150, 40));
            iconAdd = new ImageIcon(new ImageIcon(getClass().getResource("/GUI/asset/them.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
            btnAdd.setIcon(iconAdd);
            btnAdd.setFont(sgUI18);
            btnAdd.setBackground(Color.white);

            LocalDate dateDefault = java.time.LocalDate.now();
            Date date = java.sql.Date.valueOf(dateDefault);
            dcsDayDV.setDate(date);
            dcsDayDV.setDateFormatString("dd-MM-yyyy");

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(10, 10, 10, 10);
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.fill = GridBagConstraints.BOTH;
            gbc.gridwidth = 2;
            gbc.weightx = 2;
            add(btnAdd, gbc);
            btnAdd.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new PanelSelectDichVu(1);

                }
            });

            btnLuuSuaDV.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int lick = JOptionPane.showConfirmDialog(null, "Lưu thay đổi dịch vụ?", "Thông Báo", 2);
                    if (lick == JOptionPane.OK_OPTION) {
                        int gia = 0;
                        for (DichVuDTO dvdto : listDVFull) {
                            if (dvdto.getMaDV().equals(maDVtmp)) {
                                gia = dvdto.getGiaDV();
                            }
                        }
                        for (SuDungDichVuDTO sddvdto : listSDDV) {
                            if (sddvdto.getMaDV().equals(maDVtmp) && sddvdto.getNgaySuDungString().equals(ngayDV)) {
                                sddvdto.setSoLuong(Integer.parseInt(txtSLDV.getText()));
                                sddvdto.setDonGia(gia * Integer.parseInt(txtSLDV.getText()));
                                try {
                                    SuDungDichVuBUS.UpdateSuDungDichVu(sddvdto);
                                    break;
                                } catch (SQLException | ParseException ex) {
                                    Logger.getLogger(FrameOrderRoom.class.getName()).log(Level.SEVERE, null, ex);
                                }

                            }
                        }
                        loadTBDV();
                        pnSuaSL.setVisible(false);
                    } else {
                        if (lick == JOptionPane.CANCEL_OPTION) {
                            //setVisible(false);
                        }
                    }
                }
            });

            gbc.gridx += 2;
            add(btnChonDV, gbc);

            pnSuaSL.setLayout(new FlowLayout(FlowLayout.LEFT));
            pnSuaSL.add(lblSLDV);
            pnSuaSL.add(txtSLDV);
            pnSuaSL.add(btnLuuSuaDV);
            gbc.gridx = 0;
            gbc.gridy++;
            gbc.gridwidth = 1;
            add(pnSuaSL, gbc);
            pnSuaSL.setVisible(false);

            ///////////
            btnChonDV.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (dvdto.getMaDV() != null && SL != 0 && !ngayDV.isBlank()) {
                        int lick = JOptionPane.showConfirmDialog(null, "Xác nhận thêm dịch vụ: \n Mã dịch vụ: "
                                + dvdto.getMaDV() + "\n Tên dịch vụ: " + dvdto.getTenDV() + "\n Loại dịch vụ: "
                                + dvdto.getTenLoaiDV() + "\n Ngày sử dụng: " + ngayDV
                                + "\n Đơn giá: " + dvdto.getGiaDV() + "\n Số lượng: "
                                + SL + "\n Tổng cộng: " + dvdto.getGiaDV() * SL, "Thông Báo", 2);
                        if (lick == JOptionPane.OK_OPTION) {
                            addDV(dvdto, SL, ngayDV);
                            dvdto = new DichVuDTO();
                            SL = 0;
                            ngayDV = "";
                            for (SuDungDichVuDTO sddvdto : listSDDV) {
                                if (SuDungDichVuBUS.check(maCTT, sddvdto.getMaDV(), sddvdto.getNgaySuDungString()))
                            try {
                                    SuDungDichVuBUS.UpdateSuDungDichVu(sddvdto);
                                } catch (SQLException | ParseException ex) {
                                    Logger.getLogger(FrameOrderRoom.class.getName()).log(Level.SEVERE, null, ex);
                                } else
                            try {
                                    SuDungDichVuBUS.InsertSuDungDichVu(sddvdto);
                                } catch (SQLException | ParseException ex) {
                                    Logger.getLogger(FrameOrderRoom.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        } else {
                            if (lick == JOptionPane.CANCEL_OPTION) {
                                //setVisible(false);
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Bạn chưa chọn dịch vụ cần thêm");
                    }
                }

            });

        }

    }

    public static int checkDup(String MDV, String ngaysddv) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        for (int i = 0; i < listSDDV.size(); i++) {
            if (listSDDV.get(i).getMaDV().equals(MDV)) {
                if (listSDDV.get(i).getNgaySuDungString().equals(ngaysddv)) {
                    return i;
                }
            }
        }
        return -1;
    }

    public void addDVnew(String MaDV) {
        SuDungDichVuDTO sddvdto = new SuDungDichVuDTO();
        sddvdto.setMaChiTietThue(maCTT);
        sddvdto.setMaDV(MaDV);
        sddvdto.setNgaySuDungString(ngayDV);
        sddvdto.setSoLuong(SL);
        for (DichVuDTO dv : listDVFull) {
            if (dv.getMaDV().equals(MaDV)) {
                sddvdto.setDonGia(dv.getGiaDV());
            }
        }
        sddvdto.setXuLy(0);
        listSDDV.add(sddvdto);
        listSDDVnew.add(sddvdto);
    }

    public void addDV(DichVuDTO dv, int sl, String ngaysddv) {
        if (checkDup(dv.getMaDV(), ngaysddv) == -1) {
            addDVnew(dv.getMaDV());
        } else {
            int i = checkDup(dv.getMaDV(), ngaysddv);
            int SLtmp = listSDDV.get(i).getSoLuong();
            listSDDV.get(i).setSoLuong(SLtmp + sl);
            listSDDV.get(i).setDonGia(listSDDV.get(i).getSoLuong() * listSDDV.get(i).getDonGia() / SLtmp);
        }
        loadTBDV();
    }
    
}
