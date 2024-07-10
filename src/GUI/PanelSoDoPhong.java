/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import BUS.PhongBUS;
import DAO.NhanVienDAO;
import DAO.PhongDAO;
import DTO.NhanVienDTO;
import DTO.PhongDTO;
import static GUI.PanelPhong.renderTBPhong;
import static GUI.PanelPhong.tbPhong;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author ANH KHOA
 */
public class PanelSoDoPhong extends JPanel{
    public PanelSoDoPhong(){
        initComponents();
    }
    public PanelSoDoPhong(int i) {
        
    }
    private JLabel lbTitle = new JLabel("SƠ ĐỒ PHÒNG");
    private JPanel pnTitle = new JPanel();
    private JPanel pncontainer = new JPanel();
    private JPanel pnContent = new JPanel();
    private JPanel pnContentTop = new JPanel();
    private JPanel pnContentTopLoai = new JPanel();
    private JPanel pnContentTopTinhTrang = new JPanel();
    private JPanel pnContentLeft = new JPanel();
    private JPanel pnContentRight = new JPanel();
    private JPanel pnContentBot = new JPanel();
    private Font sgUI15 = new Font("Segoe UI", Font.BOLD, 15);
    private Font sgUI13 = new Font("Segoe UI", Font.PLAIN, 13);
    private Font sgUI13b = new Font("Segoe UI", Font.BOLD, 13);
    private Font sgUI18b = new Font("Segoe UI", Font.BOLD, 18);
    private Font sgUI25b = new Font("Segoe UI", Font.BOLD, 25);
    private JScrollPane jsc = new JScrollPane();
    private JRadioButton jrdVIP, jrdNORMAL, jrdTrong, jrdThue, jrdDon;
    private ButtonGroup groupLoaiP, groupTTrang;
    private JButton btnfilter, btnreset;
    private JTable table;
    private ShowInforGUI show;
    ArrayList<PhongDTO> list = PhongBUS.getListPhong();;
    ArrayList<PhongDTO> listPhong;
    ArrayList<PhongDTO> listTemp;
    ArrayList<String> listTrong;
    ArrayList<String> listThue;
    ArrayList<String> listDon;
    static DefaultTableModel model;

    public void initComponents(){
        this.setLayout(new BorderLayout());
        pnTitle.setPreferredSize(new Dimension (120, 50));
        lbTitle.setFont(sgUI18b);
        pnTitle.setBackground(Color.white);
        pnTitle.add(lbTitle);
        pnTitle.setBorder(new MatteBorder(0, 0, 1, 0,Color.black));
        
        pncontainer.setLayout(new BorderLayout());
        pnContentTop.setPreferredSize(new Dimension(80, 100));
        pnContentTop.setBackground(Color.decode("#FFFFF0"));
        pncontainer.add(pnContentTop, BorderLayout.NORTH);
        pnContentLeft.setPreferredSize(new Dimension(20, 600));
        pnContentLeft.setBackground(Color.decode("#FFFFF0"));
        pncontainer.add(pnContentLeft, BorderLayout.WEST);
        pnContentRight.setPreferredSize(new Dimension(20,600));
        pnContentRight.setBackground(Color.decode("#FFFFF0"));
        pncontainer.add(pnContentRight, BorderLayout.EAST);
        pnContentBot.setPreferredSize(new Dimension(80,20));
        pnContentBot.setBackground(Color.decode("#FFFFF0"));
        pncontainer.add(pnContentBot, BorderLayout.SOUTH);
        
        table = new JTable() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setColumnSelectionAllowed(false);
        table.setRowSelectionAllowed(false);
        table.setBackground(Color.white);
        table.setFont(sgUI18b);
        table.setRowHeight(150);
        table.getTableHeader().setVisible(false);
        table.getTableHeader().setPreferredSize(new Dimension(0, 0));
        TableColumn column;
        int columnWidth = 120;
        for (int i = 0; i < table.getColumnCount(); i++) {
            column = table.getColumnModel().getColumn(i);
            column.setPreferredWidth(columnWidth);
        }
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1 && e.getClickCount() == 2) {
                    int row = table.rowAtPoint(e.getPoint());
                    int col = table.columnAtPoint(e.getPoint());
                    if (row >= 0 && col >= 0) {
                        if(!isCellEmpty(table, row, col))
                            for(PhongDTO phong : list){
                                if(table.getValueAt(row, col).equals(phong.getMaP()))
                                    show = new ShowInforGUI(phong.getMaP(),phong.getTenP(),phong.getLoaiP(),phong.getGiaP()+"",phong.getTinhTrang(),phong.getHienTrang());
                            }
                    }
                }
                
            }
        });
        load();
        
        pnContentTop.setLayout(new FlowLayout(FlowLayout.CENTER));
        pnContentTopLoai.setPreferredSize(new Dimension(240, 70));
        pnContentTopLoai.setBackground(Color.decode("#FFFFF0"));
        pnContentTopLoai.setLayout(new FlowLayout(FlowLayout.CENTER));
        pnContentTopLoai.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.ORANGE,3), "Loại Phòng", TitledBorder.CENTER, TitledBorder.TOP));
        jrdNORMAL = new JRadioButton("THƯỜNG");
        jrdNORMAL.setPreferredSize(new Dimension(100, 40));
        jrdNORMAL.setBackground(Color.decode("#FFFFF0"));
        pnContentTopLoai.add(jrdNORMAL);
        jrdVIP = new JRadioButton("VIP");
        jrdVIP.setPreferredSize(new Dimension(100, 40));
        jrdVIP.setBackground(Color.decode("#FFFFF0"));
        pnContentTopLoai.add(jrdVIP);
        groupLoaiP = new ButtonGroup();
        groupLoaiP.add(jrdNORMAL);
        groupLoaiP.add(jrdVIP);
        
        pnContentTopTinhTrang.setPreferredSize(new Dimension(370, 70));
        pnContentTopTinhTrang.setBackground(Color.decode("#FFFFF0"));
        pnContentTopTinhTrang.setLayout(new GridLayout(1,3,10,10));
        pnContentTopTinhTrang.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.ORANGE,3), "Tình Trạng Phòng ", TitledBorder.CENTER, TitledBorder.TOP));
        jrdThue = new JRadioButton("Đang Thuê");
        jrdThue.setPreferredSize(new Dimension(100, 10));
        jrdThue.setBackground(Color.decode("#FFFFF0"));
        pnContentTopTinhTrang.add(jrdThue);
        jrdTrong = new JRadioButton("Đang Trống");
        jrdTrong.setPreferredSize(new Dimension(100, 10));
        jrdTrong.setBackground(Color.decode("#FFFFF0"));
        pnContentTopTinhTrang.add(jrdTrong);
        jrdDon = new JRadioButton("Chưa Dọn");
        jrdDon.setPreferredSize(new Dimension(100, 10));
        jrdDon.setBackground(Color.decode("#FFFFF0"));
        groupTTrang = new ButtonGroup();
        groupTTrang.add(jrdThue);
        groupTTrang.add(jrdTrong);
        groupTTrang.add(jrdDon);
        pnContentTopTinhTrang.add(jrdDon);
        
        
        btnreset = new JButton("Làm Mới");
        btnreset.setPreferredSize(new Dimension(120, 30));
        ImageIcon iconReset = new ImageIcon(new ImageIcon(getClass().getResource("/GUI/asset/IconReset.png")).getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH));
        btnreset.setIcon(iconReset);
        btnreset.setFocusPainted(false);
        btnreset.setBackground(Color.decode("#FFC761"));
        btnreset.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnreset.setBackground(Color.ORANGE);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                
                btnreset.setBackground(Color.decode("#FFC761"));
                }
            });
        btnreset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reset();
            }
        });
        btnfilter = new JButton("Lọc");
        btnreset.setPreferredSize(new Dimension(120, 30));
        btnfilter.setRequestFocusEnabled(false);
        btnfilter.setBackground(Color.decode("#FFC761"));
        ImageIcon iconSearch = new ImageIcon(new ImageIcon(getClass().getResource("/GUI/asset/IconFind.png")).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        btnfilter.setIcon(iconSearch);
        btnfilter.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnfilter.setBackground(Color.ORANGE);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                
                btnfilter.setBackground(Color.decode("#FFC761"));
                }
            });
        
        btnfilter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                        listPhong = PhongBUS.getListPhong();
                        listTemp = new ArrayList<>();
                        if(jrdNORMAL.isSelected() || jrdVIP.isSelected()){
                            if(jrdNORMAL.isSelected()){
                                for(PhongDTO phong: listPhong){
                                    if(phong.getLoaiP().equals("Thường")){
                                        listTemp.add(phong);
                                    }
                                }
                            }
                            else if(jrdVIP.isSelected()){
                                for(PhongDTO phong: listPhong){
                                    if(phong.getLoaiP().equals("VIP")){
                                        listTemp.add(phong);
                                    }
                                }
                            }
                            listPhong.clear();
                            for (PhongDTO phong : listTemp) {
                                listPhong.add(phong);
                            }
                            listTemp.clear();
                        }    
                        if(jrdTrong.isSelected()||jrdThue.isSelected()||jrdDon.isSelected()){
                            if (jrdTrong.isSelected()) {
                                for (PhongDTO x : listPhong) {
                                    if (x.getTinhTrang().equals("Trống")) {
                                        listTemp.add(x);
                                    }
                                }
                            } 
                            else if (jrdThue.isSelected()) {
                                for (PhongDTO x : listPhong) {
                                    if (x.getTinhTrang().equals("Đang được thuê")) {
                                        listTemp.add(x);
                                    }
                                }
                            } 
                            else if(jrdDon.isSelected()){
                                for (PhongDTO x : listPhong) {
                                    if (x.getTinhTrang().equals("Chưa dọn phòng")) {
                                        listTemp.add(x);
                                    }
                                }
                            }
                            listPhong.clear();
                            for (PhongDTO x : listTemp) {
                                listPhong.add(x);
                            }
                            listTemp.clear();
                        }    
                        if (listPhong.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Không có phòng như bạn muốn", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                            reset();
                        } else {
                            model = (DefaultTableModel) table.getModel();    
                            for(int count=0; count<model.getRowCount(); count++)
                                model.setRowCount(0);  
                            loadtable(listPhong);
                        }    
            }
        });
        
        
        pnContentTop.add(pnContentTopLoai);            
        pnContentTop.add(pnContentTopTinhTrang); 
        pnContentTop.add(btnfilter);
        pnContentTop.add(btnreset);
        
       
        this.add(pnTitle,BorderLayout.NORTH);
        
    }
    public void load(){
        list = PhongBUS.getListPhong();
        model = (DefaultTableModel) table.getModel();
        model.setColumnIdentifiers(new Object[]{
            "1", "2", "3", "4", "5"
        });  
        int numRows = (int) Math.ceil((double) list.size() / model.getColumnCount());
        // Thêm dữ liệu từ danh sách vào model
        for (int row = 0; row < numRows; row++) {
            Object[] rowData = new Object[model.getColumnCount()]; // Mảng dữ liệu cho mỗi dòng trong bảng
            for (int col = 0; col < model.getColumnCount(); col++) {
                int index = row * model.getColumnCount() + col; // Tính index của phần tử trong danh sách
                if (index < list.size()) {
                    rowData[col] = list.get(index).getMaP()+""; // Đặt dữ liệu vào cột tương ứng
                }
            }
            model.addRow(rowData);
        }
        // Khởi tạo DefaultTableCellRenderer tùy chỉnh
        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        
        listTrong = new ArrayList<>();
        listThue = new ArrayList<>();
        listDon = new ArrayList<>();
        for(PhongDTO phong : list){
            if(phong.getTinhTrang().equals("Trống"))
                listTrong.add(phong.getMaP());
            else if(phong.getTinhTrang().equals("Đang được thuê"))
                listThue.add(phong.getMaP());
            else 
                listDon.add(phong.getMaP());
        }
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                ((DefaultTableCellRenderer) cellComponent).setHorizontalAlignment(JLabel.CENTER); // Đặt canh giữa cho dữ liệu trong ô
                ((DefaultTableCellRenderer) cellComponent).setVerticalAlignment(JLabel.CENTER); // Đặt canh giữa theo chiều dọc cho dữ liệu trong ô
                JLabel lb = (JLabel) cellComponent;
                for (String maP : listTrong) {
                    if (value != null && value.equals(maP)) {
                        lb.setBackground(Color.decode("#98FB98"));
                    }
                }
                for (String maP : listThue) {
                    if (value != null && value.equals(maP)) {
                        lb.setBackground(Color.decode("#FC5139"));
                    }
                }
                for (String maP : listDon) {
                    if (value != null && value.equals(maP)) {
                        lb.setBackground(Color.decode("#FFFF66"));
                    }
                }  
                
                return lb;
            }
            
    };

        // Gán Renderer cho các cột trong bảng
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(renderer);
        }
        jsc.setViewportView(table);
        jsc.setPreferredSize(new Dimension(table.getWidth(), table.getHeight()));
        jsc.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        //pnContent.setLayout(new BorderLayout());
        //pnContent.add(jsc,BorderLayout.CENTER);
        pncontainer.add(jsc,BorderLayout.CENTER);
        this.add(pncontainer,BorderLayout.CENTER);         
    }
    public void loadtable(ArrayList<PhongDTO> listPhong){
        int numRows = (int) Math.ceil((double) listPhong.size() / model.getColumnCount());
        // Thêm dữ liệu từ danh sách vào model
        for (int row = 0; row < numRows; row++) {
            Object[] rowData = new Object[model.getColumnCount()]; // Mảng dữ liệu cho mỗi dòng trong bảng
            for (int col = 0; col < model.getColumnCount(); col++) {
                int index = row * model.getColumnCount() + col; // Tính index của phần tử trong danh sách
                if (index < listPhong.size()) {
                    rowData[col] = listPhong.get(index).getMaP()+""; // Đặt dữ liệu vào cột tương ứng
                }
            }
            model.addRow(rowData);
        }
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        centerRenderer.setBorder(BorderFactory.createMatteBorder(7, 7, 7, 7, Color.white)); // Đặt độ dày của đường kẻ
        table.setDefaultRenderer(Object.class, centerRenderer);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        listTrong = new ArrayList<>();
        listThue = new ArrayList<>();
        listDon = new ArrayList<>();
        for(PhongDTO phong : listPhong){
            if(phong.getTinhTrang().equals("Trống"))
                listTrong.add(phong.getMaP());
            else if(phong.getTinhTrang().equals("Đang được thuê"))
                listThue.add(phong.getMaP());
            else 
                listDon.add(phong.getMaP());
        }
    DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            ((DefaultTableCellRenderer) cellComponent).setHorizontalAlignment(JLabel.CENTER); // Đặt canh giữa cho dữ liệu trong ô
            ((DefaultTableCellRenderer) cellComponent).setVerticalAlignment(JLabel.CENTER); // Đặt canh giữa theo chiều dọc cho dữ liệu trong ô
            if (value != null && !value.toString().isEmpty()) {
                for (String maP : listTrong) {
                    if (value.equals(maP)) {
                        cellComponent.setBackground(Color.decode("#98FB98"));
                    }
                }
                for (String maP : listThue) {
                    if (value.equals(maP)) {
                        cellComponent.setBackground(Color.decode("#FC5139"));
                    }
                }
                for (String maP : listDon) {
                    if (value.equals(maP)) {
                        cellComponent.setBackground(Color.decode("#FFFF66"));
                    }
                }   
            }

               

                return cellComponent;
            }
    };

        // Gán Renderer cho các cột trong bảng
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(renderer);
        }
        
       
        jsc.setViewportView(table);
        jsc.setPreferredSize(new Dimension(table.getWidth(), table.getHeight()));
        //jsc.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        pncontainer.add(jsc,BorderLayout.CENTER);
        this.add(pncontainer,BorderLayout.CENTER);
        
    }
    public int count() {
        int count = 0;
        list = PhongBUS.getListPhong();
        for(PhongDTO p: list){
            if(p.getXuLy()==0) count++;
        }
        return count;
    }

    public static int getNumberOfRows(int n) {
        int rowCount = 1; // Khởi tạo số dòng bằng 1
        int elementCount = 0; // Khởi tạo số phần tử trên dòng bằng 0
        for (int i = 1; i <= n; i++) { // Lặp lại với mỗi phần tử cần thêm vào ma trận
            elementCount++; // Tăng số phần tử trên dòng lên 1
            if (elementCount > 5) { // Nếu số phần tử trên dòng vượt quá giới hạn 5 phần tử
                rowCount++; // Tăng số dòng lên 1
                elementCount = 1; // Đặt số phần tử trên dòng bằng 1
            }
        }
        return rowCount; // Trả về số dòng của ma trận
    }
    public void reset(){
        groupLoaiP.clearSelection();
        groupTTrang.clearSelection();
        model = (DefaultTableModel) table.getModel();
        for(int count=0; count<model.getRowCount(); count++)
            model.setRowCount(0); 
        load();
        
    }
    public static boolean isCellEmpty(JTable table, int row, int col) {
        // Kiểm tra xem row và col nằm trong phạm vi của bảng
        if (row < 0 || row >= table.getRowCount() || col < 0 || col >= table.getColumnCount()) {
            throw new IllegalArgumentException("Vị trí ô không hợp lệ");
        }

        // Lấy giá trị của ô từ JTable
        Object cellValue = table.getValueAt(row, col);

        // Kiểm tra giá trị của ô có là null hoặc rỗng hay không
        return cellValue == null || cellValue.toString().isEmpty();
    }
    
}