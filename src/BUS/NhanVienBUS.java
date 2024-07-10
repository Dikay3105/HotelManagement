package BUS;

import DAO.NhanVienDAO;
import DTO.NhanVienDTO;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Set;
import java.util.TreeSet;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class NhanVienBUS {

    private ArrayList<NhanVienDTO> NVlist;
    DefaultTableModel model;
    ArrayList<NhanVienDTO> listNhanVien;
    ArrayList<NhanVienDTO> listTemp;

    public NhanVienBUS() {

    }

    public void showTable(JTable table) {
        int i = 1;
        NVlist = new NhanVienDAO().LoadData();
        model = (DefaultTableModel) table.getModel();
        model.setColumnIdentifiers(new Object[]{
            "STT", "Mã Nhân Viên", "Mật Khẩu", "Họ Tên", "Giới Tính", "Ngày Sinh", "Phòng Ban", "Email", "Hệ Số"
        });
        for (NhanVienDTO s : NVlist) {
            if (s.getXuLy() != 1) {
                model.addRow(new Object[]{
                    i++, s.getMaNV(), s.getmKNV(), s.getTenNV(), s.getGioiTinh(), s.getNgaySinhString(), s.getTenPB(), s.getEmail(), s.getHeSoLuong()
                });

            }
        }
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.getColumnModel().getColumn(7).setCellRenderer(rightRenderer);
        table.getColumnModel().getColumn(5).setCellRenderer(rightRenderer);
        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(0).setPreferredWidth(10);
    }

    public ArrayList<NhanVienDTO> getListNhanVien() {
        ArrayList<NhanVienDTO> listNhanVien = new ArrayList<>();
        for (NhanVienDTO x : NhanVienDAO.LoadData()) {
            if (x.getXuLy() == 0) {
                listNhanVien.add(x);
            }
        }
        return listNhanVien;
    }

    public boolean insertNhanVien(NhanVienDTO nv) throws SQLException, ParseException {
        if (new NhanVienDAO().InsertNhanVien(nv)) {
            return true;
        }
        return false;
    }

    public boolean editNhanVien(NhanVienDTO nv) throws SQLException, ParseException {
        if (new NhanVienDAO().UpdateNhanVien(nv)) {
            return true;
        }
        return false;
    }

    public boolean deleteNhanVieṇ̣̣̣̣̣̣(NhanVienDTO s) throws SQLException, ParseException {
        if (new NhanVienDAO().DelNhanVien(s)) {
            return true;
        }
        return false;
    }

    public void findNhanVien(JTable table, String mnv, String namenv, String gtinh, String pban, String hso) {
        model = (DefaultTableModel) table.getModel();
        Set<String> IdDelete = new TreeSet<String>();
        int numberRow;
        numberRow = table.getRowCount();
        String key, id;
        if (!mnv.isEmpty() && !mnv.equals("Mã Nhân Viên...")) {
            if (IDisEmty(mnv) == true) {
                for (int i = 0; i < numberRow; i++) {
                    key = model.getValueAt(i, 1) + "";
                    if (!key.equals(mnv)) {
                        IdDelete.add(String.valueOf(model.getValueAt(i, 1) + ""));
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "MÃ NHÂN VIÊN KHÔNG TỒN TẠI TRONG HỆ THỐNG", "Thông Báo", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (!namenv.isEmpty() && !namenv.equals("Họ Tên...")) {
            if (NAMEisEmty(namenv) == true) {
                for (int i = 0; i < numberRow; i++) {
                    key = model.getValueAt(i, 3) + "";
                    if (!namenv.equals(key)) {
                        IdDelete.add(String.valueOf(model.getValueAt(i, 1) + ""));
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "TÊN NHÂN VIÊN KHÔNG TỒN TẠI", "Thông Báo", JOptionPane.OK_OPTION);
            }
        }
        if (!pban.isEmpty() && !pban.equals("Phòng Ban")) {
            for (int i = 0; i < numberRow; i++) {
                key = model.getValueAt(i, 6) + "";
                if (!pban.equals(key)) {
                    IdDelete.add(String.valueOf(model.getValueAt(i, 1) + ""));
                }
            }
        }
        if (!gtinh.isEmpty() && !gtinh.equals("Giới Tính")) {
            for (int i = 0; i < numberRow; i++) {
                key = model.getValueAt(i, 4) + "";
                if (!gtinh.equals(key)) {
                    IdDelete.add(String.valueOf(model.getValueAt(i, 1) + ""));
                }
            }
        }
        if (!hso.isEmpty() && !hso.equals("Hệ Số Lương...")) {
            if (HESOisEmty(hso) == true) {
                for (int i = 0; i < numberRow; i++) {
                    key = model.getValueAt(i, 7) + "";
                    if (!hso.equals(key)) {
                        IdDelete.add(String.valueOf(model.getValueAt(i, 1) + ""));
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "HỆ SỐ KHÔNG TỒN TẠI", "Thông Báo", JOptionPane.OK_OPTION);
            }
        }
        for (String x : IdDelete) {
            numberRow = table.getRowCount();
            for (int j = 0; j < numberRow; j++) {
                id = model.getValueAt(j, 1) + "";
                if (id.equals(x)) {
                    try {
                        model.removeRow(j);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                }
            }
        }
    }

    public void resetTable(JTable table) {
        model = (DefaultTableModel) table.getModel();
        int numberRow;
        while (true) {
            numberRow = table.getRowCount();
            if (numberRow == 0) {
                break;
            } else
                try {
                model.removeRow(0);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        showTable(table);

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

    public boolean checkInput(String mnv, String namenv, String mknv, String hso) {
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

        return true;
    }

    public boolean IDisEmty(String id) {
        try {
            for (NhanVienDTO nv : NVlist) {
                if (nv.getMaNV().equals(id)) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean NAMEisEmty(String name) {
        for (NhanVienDTO nv : NVlist) {
            if (nv.getTenNV().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public boolean HESOisEmty(String heso) {
        for (NhanVienDTO nv : NVlist) {
            if (nv.getHeSoLuong() == Integer.parseInt(heso)) {
                return true;
            }
        }
        return false;
    }

    //Vĩnh Khoa
    public ArrayList<NhanVienDTO> LoadNV() {
        NVlist = new NhanVienDAO().LoadData();
        ArrayList<NhanVienDTO> a = new ArrayList<>();
        for (NhanVienDTO nv : NVlist) {
            if (nv.getXuLy() == 0) {
                a.add(nv);
            }
        }
        return a;
    }

    ////////////////////
    public static String getTenNV(String maNV) {
        return NhanVienDAO.getTenNV(maNV);
    }

    public static void updateNV(NhanVienDTO nv) throws SQLException, ParseException {
        new NhanVienDAO().UpdateNhanVien(nv);
    }

    public static NhanVienDTO getNV(String maNV) {
        return NhanVienDAO.getNV(maNV);
    }
    ////////////////////////////////////////////////////////////////    
    
    public static int getSize() {
        return NhanVienDAO.LoadData().size()+1;
    }
    
////////////////////////////////////////////////////////////////   
}
